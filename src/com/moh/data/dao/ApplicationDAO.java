package com.moh.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.HashMap;
import java.util.Map;

import com.moh.common._ParentDAO;
import com.moh.utils.Logger;

public class ApplicationDAO extends _ParentDAO {

    private String GET_APPLICATION_SQL =
            "SELECT Email, JobID, InstitutionID, LocationID, ServiceTypeID, SupervisorName, SupervisorEmail, SupervisorPhone "
            + "FROM Application "
            + "WHERE ApplicationID = ?";
    private String ADD_APPLICATION_SQL =
            "INSERT INTO Application "
            + "( ApplicationID, Email, JobID, InstitutionID, LocationID, ServiceTypeID, SupervisorName, SupervisorEmail, SupervisorPhone, SupervisorExtension, CodeID, Comments, ApprovedByCPSO ) "
            + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
    private String UPDATE_APPLICATION_SQL =
            "UPDATE Application "
            + "SET InstitutionID = ?, LocationID = ?, ServiceTypeID = ?, SupervisorName = ?, SupervisorEmail = ?, SupervisorPhone = ?, SupervisorExtension = ? "
            + "WHERE ApplicationID = ?";
    private String GET_ABBREVIATIONS_SQL =
            "SELECT uni.Abbreviation AS UnivAbbrv, prg.Abbreviation AS ProgAbbrv "
            + "FROM University uni, Program prg, UnivProg uniprg, UserInfo usr "
            + "WHERE usr.Email = ? "
            + "AND uniprg.UnivProgID = usr.UnivProgID "
            + "AND uniprg.UniversityID = uni.UniversityID "
            + "AND uniprg.ProgramID = prg.ProgramID";
    private String GET_COUNTER_SQL =
            "SELECT Value "
            + "FROM AppCounter "
            + "WHERE CounterID = 'DAYCNT' "
            + "AND UpdatedOn = ?";
    private String NEW_COUNTER_SQL =
            "INSERT INTO AppCounter "
            + "( CounterID, UpdatedOn, Value ) "
            + "VALUES ( 'DAYCNT', ?, 1 )";
    private String UPDATE_COUNTER_SQL =
            "UPDATE AppCounter "
            + "SET Value = ( Value + 1 ) "
            + "WHERE CounterID = 'DAYCNT' "
            + "AND UpdatedOn = ?";  // ---PAIRO FIX
    private String GET_AGREEMENTS_SQL =
            "SELECT AgreementID, Description_EN "
            + "FROM Agreement";
    private String GET_AGREETO_SQL =
            "SELECT AgreementList "
            + "FROM AgreeTo "
            + "WHERE ApplicationID = ?";
    private String ADD_AGREETO_SQL =
            "INSERT INTO AgreeTo "
            + "( ApplicationID, AgreementList ) "
            + "VALUES ( ?, ? )";
    private String GET_EMPLOYERINFO_SQL =
            "SELECT lhn.Name_EN AS LhinName, ins.Name_EN AS InstitutionName, srv.Name_EN AS ServiceTypeName, "
            + "app.SupervisorName, app.SupervisorEmail, app.SupervisorPhone, app.SupervisorExtension "
            + "FROM Application app, Institution ins, InstLHIN inslhn, LHIN lhn, ServiceType srv "
            + "WHERE app.ApplicationID = ? "
            + "AND app.InstitutionID = ins.InstitutionID "
            + "AND app.ServiceTypeID = srv.ServiceTypeID "
            + "AND ins.InstitutionID = inslhn.InstitutionID "
            + "AND lhn.LHIN_ID = inslhn.LHIN_ID";
    private String ADD_APP_COMMENTS_SQL =
            "UPDATE Application "
            + "SET Comments = ? "
            + "WHERE ApplicationID = ?";
    private String GET_APP_COMMENTS_SQL =
            "SELECT Comments "
            + "FROM Application "
            + "WHERE ApplicationID = ?";
    private String newApplicationID;
    private String newAppIdErrorMessage;
    private String email;
    private String supervisorEmail;

    public ApplicationDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ApplicationDAO" );
    }

    public synchronized void generateApplicationID( String email ) {
        logger.debugMethod( "generateApplicationID" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String univAbbrv = EMPTY_STRING;
        String progAbbrv = EMPTY_STRING;
        int dayCounter = 0;
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_ABBREVIATIONS_SQL );
            preparedStatement.setString( 1, getEmail() );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                univAbbrv = resultSet.getString( "UnivAbbrv" );
                progAbbrv = resultSet.getString( "ProgAbbrv" );
            }

            preparedStatement.close();
            resultSet.close();
            
            preparedStatement = connection.prepareStatement( GET_COUNTER_SQL );
            preparedStatement.setString( 1, ( String ) getFromSession( SESSION_TODAYSDATE ) );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                dayCounter = resultSet.getInt( "Value" );
            }

            preparedStatement.close();
            resultSet.close();
            
            if( dayCounter == 0 ) {
                preparedStatement = connection.prepareStatement( NEW_COUNTER_SQL );
                preparedStatement.setString( 1, ( String ) getFromSession( SESSION_TODAYSDATE ) );

                preparedStatement.executeUpdate();

                dayCounter = 1;
            }
            else {
                preparedStatement = connection.prepareStatement( UPDATE_COUNTER_SQL );
                preparedStatement.setString( 1, ( String ) getFromSession( SESSION_TODAYSDATE ) );

                preparedStatement.executeUpdate();

                dayCounter++;
            }
            
            preparedStatement.close();
            
            setNewApplicationID( univAbbrv + "." + progAbbrv + "." + getFromSession( SESSION_TODAYSDATE ) + "." + intToString( dayCounter ) );

            if( ( univAbbrv == null || univAbbrv.trim().equals(EMPTY_STRING) ) || ( progAbbrv == null || progAbbrv.trim().equals(EMPTY_STRING) ) ) {
                addToSession( SESSION_APPLICATIONID, EMPTY_STRING );
            }
            else {
                addToSession( SESSION_APPLICATIONID, getNewApplicationID() );
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );
        }
        finally {
            try {
                if( resultSet != null ) {
                    resultSet.close();
                }
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }
    }

    public Map<String, String> getApplication( String applicationID ) {
        logger.debugMethod( "getApplication" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> application = new HashMap<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_APPLICATION_SQL );
            preparedStatement.setString( 1, applicationID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                setEmail( resultSet.getString( "Email" ) );
                String jobID = resultSet.getString( "JobID" );
                String institutionID = resultSet.getString( "InstitutionID" );
                String locationID = resultSet.getString( "LocationID" );
                String serviceTypeID = resultSet.getString( "ServiceTypeID" );
                String supervisorName = resultSet.getString( "SupervisorName" );
                String supervisorEmail = resultSet.getString( "SupervisorEmail" );
                String supervisorPhone = resultSet.getString( "SupervisorPhone" );

                application.put( "Email", getEmail() );
                application.put( "JobID", jobID );
                application.put( "InstitutionID", institutionID );
                application.put( "LocationID", locationID );
                application.put( "ServiceTypeID", serviceTypeID );
                application.put( "SupervisorName", supervisorName );
                application.put( "SupervisorEmail", supervisorEmail );
                application.put( "SupervisorPhone", supervisorPhone );
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );
        }
        finally {
            try {
                if( resultSet != null ) {
                    resultSet.close();
                }
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }

        return application;
    }

    public boolean addApplication( String email, String jobID, String institutionID, String locationID, String serviceTypeID, String supervisorName, String supervisorEmail, String supervisorPhone, String supervisorExtension ) {
        logger.debugMethod( "addApplication" );
        boolean returnedValue = false;
        setEmail( email );

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;
        
        String approvedByCPSO = "0";

        try {
            int insertCountAP = 0;

            if( institutionID.length() == ( COL_LENGTH_INSTITUTIONID + COL_SORT_LENGTH ) ) {
                institutionID = institutionID.substring( COL_SORT_LENGTH );
            }

            if( serviceTypeID.length() == ( COL_LENGTH_SERVICETYPEID + COL_SORT_LENGTH ) ) {
                serviceTypeID = serviceTypeID.substring( COL_SORT_LENGTH );
            }

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( ADD_APPLICATION_SQL );

            generateApplicationID( getEmail() );

            String newAppID = getNewApplicationID();

            if( !( newAppID == null || newAppID.trim().equals(EMPTY_STRING)) ) {
                preparedStatement.setString( 1, newAppID );
                preparedStatement.setString( 2, getEmail() );
                preparedStatement.setString( 3, jobID );
                preparedStatement.setString( 4, institutionID );
                preparedStatement.setString( 5, locationID );
                preparedStatement.setString( 6, serviceTypeID );
                preparedStatement.setString( 7, supervisorName );
                preparedStatement.setString( 8, supervisorEmail );
                preparedStatement.setString( 9, supervisorPhone );
                preparedStatement.setString( 10, supervisorExtension );
                preparedStatement.setString( 11, PHASE_NEW );
                preparedStatement.setString( 12, EMPTY_STRING );
                preparedStatement.setString( 13, approvedByCPSO );

                insertCountAP = preparedStatement.executeUpdate();
            }
            else {
                setNewAppIdErrorMessage( "Verify that University and Program are valid" );
            }

            if( insertCountAP != 1 ) {
                logger.error( "Add failed" );
                connection.rollback( savePoint );
            }
            else {
                connection.commit();

                returnedValue = true;
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );

            try {
                connection.rollback( savePoint );
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }
        finally {
            try {
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( Exception ex ) {
                logger.exception( ex );
            }
        }

        return returnedValue;
    }

    public boolean editApplication( String applicationID, String institutionID, String locationID, String serviceTypeID, String supervisorName, String supervisorEmail, String supervisorPhone, String supervisorExtension ) {
        logger.debugMethod( "editApplication" );
        boolean returnedValue = false;
        setSupervisorEmail( supervisorEmail );

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int updateCountAP = 0;

            if( serviceTypeID.length() == ( COL_LENGTH_SERVICETYPEID + COL_SORT_LENGTH ) ) {
                serviceTypeID = serviceTypeID.substring( COL_SORT_LENGTH );
            }

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            if( institutionID.length() == ( COL_LENGTH_INSTITUTIONID + COL_SORT_LENGTH ) ) {
                institutionID = institutionID.substring( COL_SORT_LENGTH );
            }

            if( serviceTypeID.length() == ( COL_LENGTH_SERVICETYPEID + COL_SORT_LENGTH ) ) {
                serviceTypeID = serviceTypeID.substring( COL_SORT_LENGTH );
            }

            preparedStatement = connection.prepareStatement( UPDATE_APPLICATION_SQL );

            preparedStatement.setString( 1, institutionID );
            preparedStatement.setString( 2, locationID );
            preparedStatement.setString( 3, serviceTypeID );
            preparedStatement.setString( 4, supervisorName );
            preparedStatement.setString( 5, getSupervisorEmail() );
            preparedStatement.setString( 6, supervisorPhone );
            preparedStatement.setString( 7, supervisorExtension );
            preparedStatement.setString( 8, applicationID );

            updateCountAP = preparedStatement.executeUpdate();

            if( updateCountAP != 1 ) {
                logger.error( "Update failed" );
                connection.rollback( savePoint );
            }
            else {
                connection.commit();
                returnedValue = true;
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );

            try {
                connection.rollback( savePoint );
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }
        finally {
            try {
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( Exception ex ) {
                logger.exception( ex );
            }
        }

        return returnedValue;
    }

    public Map<String, String> getAgreements() {
        logger.debugMethod( "getAgreements" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> agreements = new HashMap<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_AGREEMENTS_SQL );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String agreementID = resultSet.getString( "AgreementID" );
                String description_EN = resultSet.getString( "Description_EN" );

                agreements.put( agreementID, description_EN );
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );
        }
        finally {
            try {
                if( resultSet != null ) {
                    resultSet.close();
                }
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }

        return agreements;
    }

    public boolean addAgreeTo( String applicationID, String agreementList ) {
        logger.debugMethod( "addAgreeTo" );
        boolean returnedValue = false;

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int insertCountAT = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( ADD_AGREETO_SQL );

            preparedStatement.setString( 1, applicationID );
            preparedStatement.setString( 2, agreementList );

            insertCountAT = preparedStatement.executeUpdate();

            if( insertCountAT != 1 ) {
                logger.error( "Add failed" );
                connection.rollback( savePoint );
            }
            else {
                connection.commit();

                returnedValue = true;
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );

            try {
                connection.rollback( savePoint );
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }
        finally {
            try {
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( Exception ex ) {
                logger.exception( ex );
            }
        }

        return returnedValue;
    }

    public Map<String, String> getEmployerInfo( String applicationID ) {
        logger.debugMethod( "getEmployerInfo" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> employerInfo = new HashMap<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_EMPLOYERINFO_SQL );
            preparedStatement.setString( 1, applicationID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String lhinName = resultSet.getString( "LhinName" );
                String institutionName = resultSet.getString( "InstitutionName" );
                String serviceTypeName = resultSet.getString( "ServiceTypeName" );
                String supervisorName = resultSet.getString( "SupervisorName" );
                setSupervisorEmail( resultSet.getString( "SupervisorEmail" ) );
                String supervisorPhone = resultSet.getString( "SupervisorPhone" );
                String supervisorExtension = resultSet.getString( "SupervisorExtension" );

                employerInfo.put( "LhinName", lhinName );
                employerInfo.put( "InstitutionName", institutionName );
                employerInfo.put( "ServiceTypeName", serviceTypeName );
                employerInfo.put( "SupervisorName", supervisorName );
                employerInfo.put( "SupervisorEmail", getSupervisorEmail() );
                employerInfo.put( "SupervisorPhone", supervisorPhone );
                employerInfo.put( "SupervisorExtension", supervisorExtension );
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );
        }
        finally {
            try {
                if( resultSet != null ) {
                    resultSet.close();
                }
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }

        return employerInfo;
    }

    public String getAgreeTo( String applicationID ) {
        logger.debugMethod( "getAgreeTo" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String agreementList = EMPTY_STRING;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_AGREETO_SQL );
            preparedStatement.setString( 1, applicationID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                agreementList = resultSet.getString( "AgreementList" );
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );
        }
        finally {
            try {
                if( resultSet != null ) {
                    resultSet.close();
                }
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }

        return agreementList;
    }

    public boolean editAppComments( String comments, String applicationID ) {
        logger.debugMethod( "editAppComments" );
        boolean returnedValue = false;

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int updateCountAP = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( ADD_APP_COMMENTS_SQL );

            preparedStatement.setString( 1, comments );
            preparedStatement.setString( 2, applicationID );

            updateCountAP = preparedStatement.executeUpdate();

            if( updateCountAP != 1 ) {
                logger.error( "Update failed" );
                connection.rollback( savePoint );
            }
            else {
                connection.commit();
                returnedValue = true;
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );

            try {
                connection.rollback( savePoint );
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }
        finally {
            try {
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( Exception ex ) {
                logger.exception( ex );
            }
        }

        return returnedValue;
    }

    public String getAppComments( String applicationID ) {
        logger.debugMethod( "getAppComments" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String comments = EMPTY_STRING;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_APP_COMMENTS_SQL );
            preparedStatement.setString( 1, applicationID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                comments = resultSet.getString( "Comments" );
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );
        }
        finally {
            try {
                if( resultSet != null ) {
                    resultSet.close();
                }
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }

        return comments;
    }

    public String getNewApplicationID() {
        return newApplicationID;
    }

    public void setNewApplicationID( String newApplicationID ) {
        this.newApplicationID = newApplicationID;
    }

    public String getNewAppIdErrorMessage() {
        return newAppIdErrorMessage;
    }

    public void setNewAppIdErrorMessage( String newAppIdErrorMessage ) {
        this.newAppIdErrorMessage = newAppIdErrorMessage;
    }
    
    private String getEmail() {
        if ( email == null || email.trim().equals( EMPTY_STRING ) ) {
            return EMPTY_STRING;
        }
        else {
            return email.trim().toLowerCase();
        }
    }
    
    private void setEmail( String email ) {
        if ( email == null || email.trim().equals( EMPTY_STRING ) ) {
            this.email = EMPTY_STRING;
        }
        else {
            this.email = email.trim().toLowerCase();
        }
    }
    
    private String getSupervisorEmail() {
        if ( supervisorEmail == null || supervisorEmail.trim().equals( EMPTY_STRING ) ) {
            return EMPTY_STRING;
        }
        else {
            return supervisorEmail.trim().toLowerCase();
        }
    }
    
    private void setSupervisorEmail( String supervisorEmail ) {
        if ( supervisorEmail == null || supervisorEmail.trim().equals( EMPTY_STRING ) ) {
            this.supervisorEmail = EMPTY_STRING;
        }
        else {
            this.supervisorEmail = supervisorEmail.trim().toLowerCase();
        }
    }
}
