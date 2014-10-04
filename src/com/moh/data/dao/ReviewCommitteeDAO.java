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

public class ReviewCommitteeDAO extends _ParentDAO {

    private String GET_COMMITTEE_APPROVAL_SQL =
            "SELECT AttestRequirements, Comments, ApplicationConfirmation "
            + "FROM ApprovalCommittee "
            + "WHERE ApplicationID = ? ";
    private String ADD_COMMITTEE_APPROVAL_SQL =
            "INSERT INTO ApprovalCommittee "
            + "( ApplicationID, AttestRequirements, Comments, ApplicationConfirmation ) "
            + "VALUES ( ?, ?, ?, ? )";
    private String GET_COMMITTEE_EMAIL_SQL =
            "SELECT usrrol.Email "
            + "FROM UserRole usrrol "
            + "WHERE usrrol.RoleID = 'UMC' "
            + "AND usrrol.Email IN ( "
            + "SELECT urv.Email "
            + "FROM Application app, UserInfo usr, UnivProg upg, UnivReviewer urv "
            + "WHERE app.ApplicationID = ? "
            + "AND app.Email = usr.Email "
            + "AND usr.UnivProgID = upg.UnivProgID "
            + "AND upg.UniversityID = urv.UniversityID )";
    private String GET_ADMIN_COMMITTEE_EMAIL_SQL =
            "SELECT Email "
            + "FROM UserRole "
            + "WHERE RoleID = 'AMC'";
    private String GET_STATUS_APPROVERS_INFO_SQL =
            "SELECT FirstName, LastName, UpdatedOn "
            + "FROM Status "
            + "WHERE ApplicationID = ? "
            + "AND RoleID = 'UMC'";

    private String committeeEmail;
    
    public ReviewCommitteeDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ReviewCommitteeDAO" );
    }

    public Map<String, Object> getCommitteeApproval( String applicationID ) {
        logger.debugMethod( "getCommitteeApproval" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, Object> committeeApproval = new HashMap<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_COMMITTEE_APPROVAL_SQL );
            preparedStatement.setString( 1, applicationID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                boolean attestRequirements = resultSet.getBoolean( "AttestRequirements" );
                String comments = resultSet.getString( "Comments" );
                boolean applicationConfirmation = resultSet.getBoolean( "ApplicationConfirmation" );

                committeeApproval.put( "AttestRequirements", attestRequirements );
                committeeApproval.put( "Comments", comments );
                committeeApproval.put( "ApproverName", getApproverName( applicationID ) );
                committeeApproval.put( "ApplicationConfirmation", applicationConfirmation );
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

        return committeeApproval;
    }

    public boolean addCommitteeApproval( String applicationID, boolean attestRequirements, String comments, boolean applicationConfirmation ) {

        logger.debugMethod( "addCommitteeApproval" );
        boolean returnedValue = false;

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int insertCountCA = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( ADD_COMMITTEE_APPROVAL_SQL );

            preparedStatement.setString( 1, applicationID );
            preparedStatement.setBoolean( 2, attestRequirements );
            preparedStatement.setString( 3, comments );
            preparedStatement.setBoolean( 4, applicationConfirmation );

            insertCountCA = preparedStatement.executeUpdate();

            if( insertCountCA != 1 ) {
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

    public String getCommitteeEmail( String applicationID ) {
        logger.debugMethod( "getCommitteeEmail" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String committeeEmail = EMPTY_STRING;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_COMMITTEE_EMAIL_SQL );
            preparedStatement.setString( 1, applicationID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                committeeEmail = resultSet.getString( "Email" );
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

        return committeeEmail;
    }

    public String getAdminCommitteeEmail() {
        logger.debugMethod( "getAdminCommitteeEmail" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        setCommitteeEmail( EMPTY_STRING );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_ADMIN_COMMITTEE_EMAIL_SQL );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                setCommitteeEmail( resultSet.getString( "Email" ) );
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

        return getCommitteeEmail();
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

    private String getCommitteeEmail() {
        if ( committeeEmail == null || committeeEmail.trim().equals( EMPTY_STRING ) ) {
            return EMPTY_STRING;
        }
        else {
            return committeeEmail.trim().toLowerCase();
        }
    }

    private void setCommitteeEmail(String committeeEmail) {
        if ( committeeEmail == null || committeeEmail.trim().equals( EMPTY_STRING ) ) {
            this.committeeEmail = EMPTY_STRING;
        }
        else {
            this.committeeEmail = committeeEmail.trim().toLowerCase();
        }
    }
}
