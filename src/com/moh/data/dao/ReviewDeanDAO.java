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

public class ReviewDeanDAO extends _ParentDAO {

    private String GET_DEAN_APPROVAL_SQL =
            "SELECT Comments, ApplicationConfirmation "
            + "FROM ApprovalPgmeDean "
            + "WHERE ApplicationID = ? ";
    private String ADD_DEAN_APPROVAL_SQL =
            "INSERT INTO ApprovalPgmeDean "
            + "( ApplicationID, Comments, ApplicationConfirmation ) "
            + "VALUES ( ?, ?, ? )";
    private String GET_DEAN_EMAIL_SQL =
            "SELECT usrrol.Email "
            + "FROM UserRole usrrol "
            + "WHERE usrrol.RoleID = 'UDN' "
            + "AND usrrol.Email IN ( "
            + "SELECT urv.Email "
            + "FROM Application app, UserInfo usr, UnivProg upg, UnivReviewer urv "
            + "WHERE app.ApplicationID = ? "
            + "AND urv.Active = 1 "
            + "AND app.Email = usr.Email "
            + "AND usr.UnivProgID = upg.UnivProgID "
            + "AND upg.UniversityID = urv.UniversityID )";
    private String GET_STATUS_APPROVERS_INFO_SQL =
            "SELECT FirstName, LastName, UpdatedOn "
            + "FROM Status "
            + "WHERE ApplicationID = ? "
            + "AND RoleID = 'UDN'";
    
    private String deanEmail;

    public ReviewDeanDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ReviewDeanDAO" );
    }

    public Map<String, Object> getDeanApproval( String applicationID ) {
        logger.debugMethod( "getDeanApproval" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, Object> deanApproval = new HashMap<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_DEAN_APPROVAL_SQL );
            preparedStatement.setString( 1, applicationID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String comments = resultSet.getString( "Comments" );
                boolean applicationConfirmation = resultSet.getBoolean( "ApplicationConfirmation" );

                deanApproval.put( "Comments", comments );
                deanApproval.put( "ApproverName", getApproverName( applicationID ) );
                deanApproval.put( "ApplicationConfirmation", applicationConfirmation );
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

        return deanApproval;
    }

    public boolean addDeanApproval( String applicationID, String comments, boolean applicationConfirmation ) {
        logger.debugMethod( "addDeanApproval" );
        boolean returnedValue = false;

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int insertCountDA = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( ADD_DEAN_APPROVAL_SQL );

            preparedStatement.setString( 1, applicationID );
            preparedStatement.setString( 2, comments );
            preparedStatement.setBoolean( 3, applicationConfirmation );

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

    public String getDeanEmail( String applicationID ) {
        logger.debugMethod( "getDeanEmail" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        setDeanEmail( EMPTY_STRING );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_DEAN_EMAIL_SQL );
            preparedStatement.setString( 1, applicationID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                setDeanEmail( resultSet.getString( "Email" ) );
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

        return getDeanEmail();
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

    private String getDeanEmail() {
        if ( deanEmail == null || deanEmail.trim().equals( EMPTY_STRING ) ) {
            return EMPTY_STRING;
        }
        else {
            return deanEmail.trim().toLowerCase();
        }
    }

    private void setDeanEmail(String deanEmail) {
        if ( deanEmail == null || deanEmail.trim().equals( EMPTY_STRING ) ) {
            this.deanEmail = EMPTY_STRING;
        }
        else {
            this.deanEmail = deanEmail.trim().toLowerCase();
        }
    }
}
