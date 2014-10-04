package com.moh.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import com.moh.common._ParentDAO;
import com.moh.data.bean.AppFormStatusData;
import com.moh.utils.DateFormatter;
import com.moh.utils.Logger;

public class StatusDAO extends _ParentDAO {

    private String GET_STATUS_SQL =
            "SELECT sta.UpdatedOn AS LastUpdatedOn, rol.Description_EN AS RoleDesc, sta.FirstName, sta.LastName, cod.Description_EN AS CodeDesc, cod.IconName "
            + "FROM Application app, Status sta, Role rol, Code cod "
            + "WHERE sta.ApplicationID = ? "
            + "AND app.ApplicationID = sta.ApplicationID "
            + "AND rol.RoleID = sta.RoleID "
            + "AND cod.CodeID = sta.CodeID "
            + "AND sta.UpdatedOn = "
            + "( "
            + "SELECT MAX( stt.UpdatedOn ) "
            + "FROM Status stt "
            + "WHERE stt.RoleID = sta.RoleID "
            + "AND stt.ApplicationID = sta.ApplicationID "
            + ") "
            + "ORDER BY sta.UpdatedOn ASC";
    private String GET_STATUS_HEADER_SQL =
            "SELECT ins.Name_EN AS InstitutionName, srv.Name_EN AS ServiceTypeDesc "
            + "FROM Application app, Institution ins, ServiceType srv "
            + "WHERE app.ApplicationID = ? "
            + "AND app.InstitutionID = ins.InstitutionID "
            + "AND app.ServiceTypeID = srv.ServiceTypeID";
    private String ADD_STATUS_SQL =
            "INSERT INTO Status "
            + "( ApplicationID, UpdatedOn, RoleID, CodeID, FirstName, LastName, Email ) "
            + "VALUES ( ?, ?, ?, ?, ?, ?, ? )";
    private String UPDATE_PHASE_SQL =
            "UPDATE Application "
            + "SET CodeID = ? "
            + "WHERE ApplicationID = ?";
    private String CONFIRM_EDIT_STATUS =
            "SELECT COUNT(*) AS StatCount "
            + "FROM Status "
            + "WHERE ApplicationID = ? "
            + "AND RoleID = ? "
            + "AND CodeID IN ('APR','RJT','CAN')";
    private String GET_STATUS_APPROVERS_INFO_SQL =
            "SELECT FirstName, LastName, UpdatedOn "
            + "FROM Status "
            + "WHERE ApplicationID = ? "
            + "AND RoleID = ?";
    private String GET_APP_SUBMITTED_ON_SQL =
            "SELECT UpdatedOn "
            + "FROM Status "
            + "WHERE ApplicationID = ? "
            + "AND RoleID = 'UMR' "
            + "AND CodeID = 'SBM'";
    private String CHECK_APPLICATION_SUBMISSION =
            "SELECT COUNT(*) AS SubmitCount "
            + "FROM Status "
            + "WHERE ApplicationID = ? "
            + "AND RoleID = 'UMR' "
            + "AND CodeID = 'SBM'";
    private String GET_ROTATION_UPDATED_ON_SQL =
            "SELECT MAX(UpdatedOn) AS LastUpdatedOn "
            + "FROM RotationHistory "
            + "WHERE Email = ?";
    
    private String email;

    public StatusDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "StatusDAO" );
    }

    public boolean addStatus( String applicationID, String roleID, String statusCode, String firstName, String lastName, String email ) {
        logger.debugMethod( "addStatus" );
        boolean returnedValue = false;
        setEmail( email );

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int insertCountST = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( ADD_STATUS_SQL );

            Locale locale = Locale.CANADA;
            TimeZone timezone = TimeZone.getTimeZone( "EST" );
            Calendar cal = Calendar.getInstance( timezone, locale );
            Timestamp tsNow = new Timestamp( cal.getTimeInMillis() );

            preparedStatement.setString( 1, applicationID );
            preparedStatement.setTimestamp( 2, tsNow );
            preparedStatement.setString( 3, roleID );
            preparedStatement.setString( 4, statusCode );
            preparedStatement.setString( 5, firstName );
            preparedStatement.setString( 6, lastName );
            preparedStatement.setString( 7, getEmail() );

            insertCountST = preparedStatement.executeUpdate();

            if( insertCountST != 1 ) {
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

    public List<AppFormStatusData> getAppStatus( String applicationID ) {
        logger.debugMethod( "getAppStatus" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<AppFormStatusData> statusList = new ArrayList<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_STATUS_SQL );
            preparedStatement.setString( 1, applicationID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String updatedOn = resultSet.getString( "LastUpdatedOn" );
                String roleDesc = resultSet.getString( "RoleDesc" );
                String firstName = resultSet.getString( "FirstName" );
                String lastName = resultSet.getString( "LastName" );
                String codeDesc = resultSet.getString( "CodeDesc" );
                String iconName = resultSet.getString( "IconName" );

                statusList.add( new AppFormStatusData( updatedOn, roleDesc, firstName, lastName, codeDesc, iconName ) );
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

        return statusList;
    }

    public Map<String, String> getStatusHeader( String applicationID ) {
        logger.debugMethod( "getStatusHeader" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> statusHdr = new HashMap<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_STATUS_HEADER_SQL );
            preparedStatement.setString( 1, applicationID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String institutionName = resultSet.getString( "InstitutionName" );
                String serviceTypeDesc = resultSet.getString( "ServiceTypeDesc" );

                statusHdr.put( "InstitutionName", institutionName );
                statusHdr.put( "ServiceTypeDesc", serviceTypeDesc );
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

        return statusHdr;
    }

    public boolean updatePhase( String applicationID, String phase ) {
        logger.debugMethod( "updatePhase" );
        boolean returnedValue = false;

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int updateCountAP = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( UPDATE_PHASE_SQL );

            preparedStatement.setString( 1, phase );
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

    public boolean isInformationEditable( String applicationID, String roleID ) {
        logger.debugMethod( "isInformationEditable" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        boolean result = true;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( CONFIRM_EDIT_STATUS );
            preparedStatement.setString( 1, applicationID );
            preparedStatement.setString( 2, roleID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                int counter = resultSet.getInt( "StatCount" );

                if( counter > 0 ) {
                    result = false;
                }
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

        return result;
    }

    public Map<String, String> getStatusApproversInfo( String applicationID, String roleID ) {
        logger.debugMethod( "getStatusApproversInfo" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> statusInfo = new HashMap<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_STATUS_APPROVERS_INFO_SQL );
            preparedStatement.setString( 1, applicationID );
            preparedStatement.setString( 2, roleID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String firstName = resultSet.getString( "FirstName" );
                String lastName = resultSet.getString( "LastName" );
                String updatedOn = resultSet.getString( "UpdatedOn" );

                int uoYear = Integer.parseInt( updatedOn.substring( 0, 4 ) );
                int uoMonth = Integer.parseInt( updatedOn.substring( 5, 7 ) );
                int uoDay = Integer.parseInt( updatedOn.substring( 8, 10 ) );

                Calendar cal = Calendar.getInstance();
                cal.set( Calendar.YEAR, uoYear );
                cal.set( Calendar.MONTH, uoMonth - 1 );
                cal.set( Calendar.DAY_OF_MONTH, uoDay );

                DateFormatter df = new DateFormatter();

                statusInfo.put( "FullName", firstName + " " + lastName );
                statusInfo.put( "ApprovedOn", df.formatCalendar( cal, 8 ) );
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

        return statusInfo;
    }

    public String getApplicationSubmittedOn( String applicationID ) {
        logger.debugMethod( "getApplicationSubmittedOn" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String submittedOn = EMPTY_STRING;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_APP_SUBMITTED_ON_SQL );
            preparedStatement.setString( 1, applicationID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String updatedOn = resultSet.getString( "UpdatedOn" );

                int uoYear = Integer.parseInt( updatedOn.substring( 0, 4 ) );
                int uoMonth = Integer.parseInt( updatedOn.substring( 5, 7 ) );
                int uoDay = Integer.parseInt( updatedOn.substring( 8, 10 ) );

                Calendar cal = Calendar.getInstance();
                cal.set( Calendar.YEAR, uoYear );
                cal.set( Calendar.MONTH, uoMonth - 1 );
                cal.set( Calendar.DAY_OF_MONTH, uoDay );

                DateFormatter df = new DateFormatter();

                submittedOn = df.formatCalendar( cal, 8 );
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

        return submittedOn;
    }

    public String getRotationSubmittedOn( String email ) {
        logger.debugMethod( "getRotationSubmittedOn" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String submittedOn = EMPTY_STRING;
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_ROTATION_UPDATED_ON_SQL );
            preparedStatement.setString( 1, getEmail() );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String updatedOn = resultSet.getString( "LastUpdatedOn" );

                if( !( updatedOn == null || updatedOn.equals( EMPTY_STRING ) ) ) {
                    int uoYear = Integer.parseInt( updatedOn.substring( 0, 4 ) );
                    int uoMonth = Integer.parseInt( updatedOn.substring( 5, 7 ) );
                    int uoDay = Integer.parseInt( updatedOn.substring( 8, 10 ) );

                    Calendar cal = Calendar.getInstance();
                    cal.set( Calendar.YEAR, uoYear );
                    cal.set( Calendar.MONTH, uoMonth - 1 );
                    cal.set( Calendar.DAY_OF_MONTH, uoDay );

                    DateFormatter df = new DateFormatter();

                    submittedOn = df.formatCalendar( cal, 8 );
                }
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

        return submittedOn;
    }

    public boolean isAppAlreadySubmitted( String applicationID ) {
        logger.debugMethod( "isAppAlreadySubmitted" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        boolean alreadySubmitted = true;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( CHECK_APPLICATION_SUBMISSION );
            preparedStatement.setString( 1, applicationID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                int submitCount = resultSet.getInt( "SubmitCount" );

                if( submitCount == 0 ) {
                    alreadySubmitted = false;
                }
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

        return alreadySubmitted;
    }
    
    private String getEmail() {
        if ( email == null || email.trim().equals( EMPTY_STRING ) ) {
            return EMPTY_STRING;
        }
        else {
            return email.trim().toLowerCase();
        }
    }

    private void setEmail(String email) {
        if ( email == null || email.trim().equals( EMPTY_STRING ) ) {
            this.email = EMPTY_STRING;
        }
        else {
            this.email = email.trim().toLowerCase();
        }
    }
}
