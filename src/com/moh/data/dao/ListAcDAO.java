package com.moh.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.moh.common._ParentDAO;
import com.moh.data.bean.AppFormListData;
import com.moh.data.bean.AppReviewListData;
import com.moh.utils.Logger;

public class ListAcDAO extends _ParentDAO {

    private String GET_AGREEMENTS_SQL =
            "SELECT agr.AgreementID, agr.Description_EN "
            + "FROM Agreement agr, CurrUndertaking cun "
            + "WHERE agr.AgreementID = cun.AgreementID "
            + "ORDER BY cun.Position";
    private String GET_APP_LISTING_SQL =
            "SELECT app.ApplicationID, ins.Name_EN AS Institution, cod.Description_EN AS Phase, cod.IconName "
            + "FROM UserInfo usr, Application app, Institution ins, Status sta, Code cod "
            + "WHERE usr.Email = ? "
            + "AND usr.Email = app.Email "
            + "AND app.InstitutionID = ins.InstitutionID "
            + "AND app.ApplicationID = sta.ApplicationID "
            + "AND sta.RoleID = 'UMR' "
            + "AND sta.CodeID = 'SAV' "
            + "AND app.CodeID = cod.CodeID "
            + "ORDER BY sta.UpdatedOn DESC";
    //Supervisor
    private String GET_REVIEW_LIST_INST_SQL =
            "SELECT usr.FirstName, usr.LastName, app.ApplicationID, srv.Name_EN AS ServiceTypeName, sta.UpdatedOn, "
            + "cod.Description_EN AS Phase, cod.IconName "
            + "FROM UserInfo usr, Application app, Status sta, Code cod, ServiceType srv "
            + "WHERE app.SupervisorEmail = ? "
            + "AND usr.Email = app.Email "
            + "AND app.ApplicationID = sta.ApplicationID "
            + "AND app.ApplicationID NOT IN ("
            + "SELECT insta.ApplicationID "
            + "FROM Status AS insta "
            + "WHERE insta.Email = app.SupervisorEmail "
            + "AND insta.CodeID IN ('APR','RJT') "
            + "AND insta.RoleID = 'ISV') "
            + "AND app.ServiceTypeID = srv.ServiceTypeID "
            + "AND sta.CodeID = 'SBM' "
            + "AND app.CodeID = cod.CodeID "
            + "AND app.CodeID IN ('NEW','PRG') "
            + "ORDER BY sta.UpdatedOn DESC";
    //Program Director
    private String GET_REVIEW_PGLIST_UNIV_SQL =
            "SELECT usr.FirstName, usr.LastName, app.ApplicationID, srv.Name_EN AS ServiceTypeName, sta.UpdatedOn, "
            + "cod.Description_EN AS Phase, cod.IconName "
            + "FROM UserInfo usr, Application app, Status sta, Code cod, ServiceType srv, UnivProg uniprg "
            + "WHERE usr.UnivProgID = uniprg.UnivProgID "
            + "AND uniprg.UniversityID = ? "
            + "AND uniprg.Email = ? "
            + "AND usr.Email = app.Email "
            + "AND app.ApplicationID = sta.ApplicationID "
            + "AND app.ApplicationID NOT IN ("
            + "SELECT insta.ApplicationID "
            + "FROM Status AS insta "
            + "WHERE insta.Email = uniprg.Email "
            + "AND insta.CodeID IN ('APR','RJT') "
            + "AND insta.RoleID = 'UPD') "
            + "AND app.ServiceTypeID = srv.ServiceTypeID "
            + "AND sta.RoleID = 'ISV' "
            + "AND app.CodeID = cod.CodeID "
            + "AND app.CodeID IN ('PRG') "
            + "ORDER BY sta.UpdatedOn DESC";
    //PGME Dean
    //TODO: Remove insta.Email to avoid showing applications that
    //      have been already approved by other Deans of the same University
    private String GET_REVIEW_LIST_UNIV_SQL =
            "SELECT usr.FirstName, usr.LastName, app.ApplicationID, srv.Name_EN AS ServiceTypeName, sta.UpdatedOn, "
            + "cod.Description_EN AS Phase, cod.IconName "
            + "FROM UserInfo usr, Application app, Status sta, Code cod, ServiceType srv, UnivProg uniprg "
            + "WHERE usr.UnivProgID = uniprg.UnivProgID "
            + "AND uniprg.UniversityID = ? "
            + "AND usr.Email = app.Email "
            + "AND app.ApplicationID = sta.ApplicationID "
            + "AND app.ApplicationID NOT IN ("
            + "SELECT insta.ApplicationID "
            + "FROM Status AS insta "
            + "WHERE insta.Email = ? "
            + "AND insta.CodeID IN ('APR','RJT') "
            + "AND insta.RoleID = 'UDN') "
            + "AND app.ServiceTypeID = srv.ServiceTypeID "
            + "AND sta.RoleID = 'UPD' "
            + "AND app.CodeID = cod.CodeID "
            + "AND app.CodeID IN ('PRG') "
            + "ORDER BY sta.UpdatedOn DESC";
    //Committee Member
    private String GET_REVIEW_LIST_COMM_SQL =
            "SELECT usr.FirstName, usr.LastName, app.ApplicationID, srv.Name_EN AS ServiceTypeName, sta.UpdatedOn, "
            + "cod.Description_EN AS Phase, cod.IconName "
            + "FROM UserInfo usr, Application app, Status sta, Code cod, ServiceType srv, UnivProg uniprg "
            + "WHERE usr.UnivProgID = uniprg.UnivProgID "
            + "AND uniprg.UniversityID = ? "
            + "AND usr.Email = app.Email "
            + "AND app.ApplicationID = sta.ApplicationID "
            + "AND app.ServiceTypeID = srv.ServiceTypeID "
            + "AND sta.RoleID = 'UDN' "
            + "AND app.CodeID = cod.CodeID "
            + "AND app.CodeID IN ('PRG') "
            + "ORDER BY sta.UpdatedOn DESC";
    //Administrator Committee Member
    private String GET_REVIEW_LIST_ADMIN_COMM_SQL =
            "SELECT usr.FirstName, usr.LastName, app.ApplicationID, srv.Name_EN AS ServiceTypeName, sta.UpdatedOn, "
            + "cod.Description_EN AS Phase, cod.IconName "
            + "FROM UserInfo usr, Application app, Status sta, Code cod, ServiceType srv, UnivProg uniprg "
            + "WHERE usr.UnivProgID = uniprg.UnivProgID "
            + "AND usr.Email = app.Email "
            + "AND app.ApplicationID = sta.ApplicationID "
            + "AND app.ServiceTypeID = srv.ServiceTypeID "
            + "AND sta.RoleID = 'UDN' "
            + "AND app.CodeID = cod.CodeID "
            + "AND app.CodeID IN ('PRG') "
            + "ORDER BY sta.UpdatedOn DESC";
    private String GET_LIST_OF_DOCUMENTS_SQL =
            "SELECT fu.FileID, fu.DelineativeName "
            + "FROM FileUpload fu, UserInfo ui "
            + "WHERE fu.FileDirectory = ui.FileDirectory "
            + "AND ui.Email = ?";
    private String numberedAgreementID = EMPTY_STRING;
    private int recCounter = 0;
    private String email;
    private String supervisorEmail;

    public ListAcDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ListAcDAO" );
    }

    public Map<String, String> getAgreements() {
        return getAgreements( EMPTY_STRING );
    }

    public Map<String, String> getAgreements( String agreementKey ) {
        logger.debugMethod( "getAgreements" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> agreements = new HashMap<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_AGREEMENTS_SQL );

            resultSet = preparedStatement.executeQuery();

            recCounter = 0;
            while( resultSet.next() ) {
                String agreementID = resultSet.getString( "AgreementID" );
                String description_EN = resultSet.getString( "Description_EN" );

                if( agreementKey != null && ! agreementKey.trim().equals(EMPTY_STRING) ) {
                    if( agreementID.equals( agreementKey ) ) {
                        setNumberedAgreementID( intToString( recCounter ) + agreementID );
                    }
                }

                agreements.put( intToString( recCounter ) + agreementID, description_EN );
                recCounter++;
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

    public List<AppFormListData> getAppListing( String email ) {
        logger.debugMethod( "getAppListing" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<AppFormListData> applicationList = new ArrayList<>();
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_APP_LISTING_SQL );
            preparedStatement.setString( 1, getEmail() );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String applicationID = resultSet.getString( "ApplicationID" );
                String institution = resultSet.getString( "Institution" );
                String phase = resultSet.getString( "Phase" );
                String iconName = resultSet.getString( "IconName" );

                applicationList.add( new AppFormListData( applicationID, institution, EMPTY_STRING, phase, iconName ) );
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

        return applicationList;
    }

    public List<AppReviewListData> getReviewListingInst( String supervisorEmail ) {
        logger.debugMethod( "getReviewListingInst" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<AppReviewListData> applicationList = new ArrayList<>();
        int counter = 1;
        setSupervisorEmail( supervisorEmail );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_REVIEW_LIST_INST_SQL );
            preparedStatement.setString( 1, getSupervisorEmail() );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String firstName = resultSet.getString( "FirstName" );
                String lastName = resultSet.getString( "LastName" );
                String applicationID = resultSet.getString( "ApplicationID" );
                String serviceTypeName = resultSet.getString( "ServiceTypeName" );
                String updatedOn = resultSet.getString( "UpdatedOn" );
                String phase = resultSet.getString( "Phase" );
                String iconName = resultSet.getString( "IconName" );
                String sCounter = EMPTY_STRING + counter++;

                applicationList.add( new AppReviewListData( applicationID, firstName + " " + lastName, serviceTypeName, updatedOn, phase, iconName, sCounter ) );
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

        return applicationList;
    }

    public List<AppReviewListData> getReviewListingUniv( String universityID ) {
        logger.debugMethod( "getReviewListingUniv" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<AppReviewListData> applicationList = new ArrayList<>();
        int counter = 1;
        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );

        try {
            connection = getConnection();

            if( ( ( String ) getFromSession( SESSION_ROLEID ) ).equals( ROLE_DIRECTOR ) ) {
                preparedStatement = connection.prepareStatement( GET_REVIEW_PGLIST_UNIV_SQL );
                preparedStatement.setString( 1, universityID );
                preparedStatement.setString( 2, getEmail() );
            }
            else {
                preparedStatement = connection.prepareStatement( GET_REVIEW_LIST_UNIV_SQL );
                preparedStatement.setString( 1, universityID );
                preparedStatement.setString( 2, getEmail() );
            }

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String firstName = resultSet.getString( "FirstName" );
                String lastName = resultSet.getString( "LastName" );
                String applicationID = resultSet.getString( "ApplicationID" );
                String serviceTypeName = resultSet.getString( "ServiceTypeName" );
                String updatedOn = resultSet.getString( "UpdatedOn" );
                String phase = resultSet.getString( "Phase" );
                String iconName = resultSet.getString( "IconName" );
                String sCounter = EMPTY_STRING + counter++;

                applicationList.add( new AppReviewListData( applicationID, firstName + " " + lastName, serviceTypeName, updatedOn, phase, iconName, sCounter ) );
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

        return applicationList;
    }

    public List<AppReviewListData> getReviewListingComm( String universityID ) {
        logger.debugMethod( "getReviewListingComm" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<AppReviewListData> applicationList = new ArrayList<>();
        int counter = 1;

        try {
            connection = getConnection();

            if( universityID == null || universityID.trim().equals( EMPTY_STRING ) ) {
                preparedStatement = connection.prepareStatement( GET_REVIEW_LIST_ADMIN_COMM_SQL );
            }
            else {
                preparedStatement = connection.prepareStatement( GET_REVIEW_LIST_COMM_SQL );
                preparedStatement.setString( 1, universityID );
            }

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String firstName = resultSet.getString( "FirstName" );
                String lastName = resultSet.getString( "LastName" );
                String applicationID = resultSet.getString( "ApplicationID" );
                String serviceTypeName = resultSet.getString( "ServiceTypeName" );
                String updatedOn = resultSet.getString( "UpdatedOn" );
                String phase = resultSet.getString( "Phase" );
                String iconName = resultSet.getString( "IconName" );
                String sCounter = EMPTY_STRING + counter++;

                applicationList.add( new AppReviewListData( applicationID, firstName + " " + lastName, serviceTypeName, updatedOn, phase, iconName, sCounter ) );
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

        return applicationList;
    }

    public String getNumberedAgreementID() {
        return numberedAgreementID;
    }

    public void setNumberedAgreementID( String numberedAgreementID ) {
        this.numberedAgreementID = numberedAgreementID;
    }

    public Map<String, String> getDocuments( String email ) {
        logger.debugMethod( "getDocuments" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> documents = new HashMap<>();
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_LIST_OF_DOCUMENTS_SQL );
            preparedStatement.setString( 1, getEmail() );

            resultSet = preparedStatement.executeQuery();

            recCounter = 0;
            while( resultSet.next() ) {
                String fileID = resultSet.getString( "FileID" );
                String delineativeName = resultSet.getString( "DelineativeName" );

                documents.put( fileID, delineativeName );
                recCounter++;
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

        return documents;
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
    
    private String getSupervisorEmail() {
        if ( supervisorEmail == null || supervisorEmail.trim().equals( EMPTY_STRING ) ) {
            return EMPTY_STRING;
        }
        else {
            return supervisorEmail.trim().toLowerCase();
        }
    }

    private void setSupervisorEmail(String supervisorEmail) {
        if ( supervisorEmail == null || supervisorEmail.trim().equals( EMPTY_STRING ) ) {
            this.supervisorEmail = EMPTY_STRING;
        }
        else {
            this.supervisorEmail = supervisorEmail.trim().toLowerCase();
        }
    }
}
