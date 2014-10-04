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

public class ReviewDirectorDAO extends _ParentDAO {

    private String GET_DIRECTOR_APPROVAL_SQL =
            "SELECT CorrectInformation, GoodAcademic, AttestEligibility, NotifyCpso, ProvideInformation, Comments, ApplicationConfirmation "
            + "FROM ApprovalProgDirector "
            + "WHERE ApplicationID = ? ";
    private String ADD_DIRECTOR_APPROVAL_SQL =
            "INSERT INTO ApprovalProgDirector "
            + "( ApplicationID, CorrectInformation, GoodAcademic, AttestEligibility, NotifyCpso, ProvideInformation, Comments, ApplicationConfirmation ) "
            + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )";
    private String GET_DIRECTOR_EMAIL_SQL =
            "SELECT upg.Email "
            + "FROM Application app, UserInfo usr, UnivProg upg "
            + "WHERE app.ApplicationID = ? "
            + "AND app.Email = usr.Email "
            + "AND usr.UnivProgID = upg.UnivProgID";
    private String GET_STATUS_APPROVERS_INFO_SQL =
            "SELECT FirstName, LastName, UpdatedOn "
            + "FROM Status "
            + "WHERE ApplicationID = ? "
            + "AND RoleID = 'UPD'";

    private String directorEmail;
    
    public ReviewDirectorDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ReviewDirectorDAO" );
    }

    public Map<String, Object> getDirectorApproval( String applicationID ) {
        logger.debugMethod( "getDirectorApproval" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, Object> directorApproval = new HashMap<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_DIRECTOR_APPROVAL_SQL );
            preparedStatement.setString( 1, applicationID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                boolean correctInformation = resultSet.getBoolean( "CorrectInformation" );
                boolean goodAcademic = resultSet.getBoolean( "GoodAcademic" );
                boolean attestEligibility = resultSet.getBoolean( "AttestEligibility" );
                boolean notifyCpso = resultSet.getBoolean( "NotifyCpso" );
                boolean provideInformation = resultSet.getBoolean( "ProvideInformation" );
                String comments = resultSet.getString( "Comments" );

                boolean applicationConfirmation = resultSet.getBoolean( "ApplicationConfirmation" );

                directorApproval.put( "CorrectInformation", correctInformation );
                directorApproval.put( "GoodAcademic", goodAcademic );
                directorApproval.put( "AttestEligibility", attestEligibility );
                directorApproval.put( "NotifyCpso", notifyCpso );
                directorApproval.put( "ProvideInformation", provideInformation );
                directorApproval.put( "Comments", comments );
                directorApproval.put( "ApproverName", getApproverName( applicationID ) );
                directorApproval.put( "ApplicationConfirmation", applicationConfirmation );
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

        return directorApproval;
    }

    public boolean addDirectorApproval( String applicationID, boolean correctInformation, boolean goodAcademic, boolean attestEligibility,
            boolean notifyCpso, boolean provideInformation, String comments, boolean applicationConfirmation ) {

        logger.debugMethod( "addDirectorApproval" );
        boolean returnedValue = false;

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int insertCountDA = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( ADD_DIRECTOR_APPROVAL_SQL );

            preparedStatement.setString( 1, applicationID );
            preparedStatement.setBoolean( 2, correctInformation );
            preparedStatement.setBoolean( 3, goodAcademic );
            preparedStatement.setBoolean( 4, attestEligibility );
            preparedStatement.setBoolean( 5, notifyCpso );
            preparedStatement.setBoolean( 6, provideInformation );
            preparedStatement.setString( 7, comments );
            preparedStatement.setBoolean( 8, applicationConfirmation );

            insertCountDA = preparedStatement.executeUpdate();

            if( insertCountDA != 1 ) {
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

    public String getDirectorEmail( String applicationID ) {
        logger.debugMethod( "getDirectorEmail" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        setDirectorEmail( EMPTY_STRING );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_DIRECTOR_EMAIL_SQL );
            preparedStatement.setString( 1, applicationID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                setDirectorEmail( resultSet.getString( "Email" ) );
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

        return getDirectorEmail();
    }

    public String getApproverName( String applicationID ) {
        logger.debugMethod( "getApproverName" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String approverName = EMPTY_STRING;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_STATUS_APPROVERS_INFO_SQL );
            preparedStatement.setString( 1, applicationID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String firstName = resultSet.getString( "FirstName" );
                String lastName = resultSet.getString( "LastName" );

                approverName = firstName.trim() + " " + lastName.trim();
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

        return approverName;
    }

    private String getDirectorEmail() {
        if ( directorEmail == null || directorEmail.trim().equals( EMPTY_STRING ) ) {
            return EMPTY_STRING;
        }
        else {
            return directorEmail.trim().toLowerCase();
        }
    }

    private void setDirectorEmail(String directorEmail) {
        if ( directorEmail == null || directorEmail.trim().equals( EMPTY_STRING ) ) {
            this.directorEmail = EMPTY_STRING;
        }
        else {
            this.directorEmail = directorEmail.trim().toLowerCase();
        }
    }
}
