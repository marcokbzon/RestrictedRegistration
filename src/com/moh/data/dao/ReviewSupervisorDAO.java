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

public class ReviewSupervisorDAO extends _ParentDAO {

    private String GET_SUPERVISOR_APPROVAL_SQL =
            "SELECT ConfirmApplied, AttestCredentials, AttestActivities, AbideToPairo, AttestSupervision, InformCpso, ProvideInformation, "
            + "DutiesDescription, SupervisorName, SupervisorPhone, SupervisorEmail, SupervisionDescription, ApplicationConfirmation, "
            + "IssueCertificate, ConfirmCertificate, NotBeMRP, InformActivities "
            + "FROM ApprovalSupervisor "
            + "WHERE ApplicationID = ? ";
    private String ADD_SUPERVISOR_APPROVAL_SQL =
            "INSERT INTO ApprovalSupervisor "
            + "( ApplicationID, ConfirmApplied, AttestCredentials, AttestActivities, AbideToPairo, AttestSupervision, InformCpso, ProvideInformation, "
            + "DutiesDescription, SupervisorName, SupervisorPhone, SupervisorEmail, SupervisionDescription, ApplicationConfirmation, "
            + "IssueCertificate, ConfirmCertificate, NotBeMRP, InformActivities ) "
            + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
    private String GET_STATUS_APPROVERS_INFO_SQL =
            "SELECT FirstName, LastName, UpdatedOn "
            + "FROM Status "
            + "WHERE ApplicationID = ? "
            + "AND RoleID = 'ISV'";
    
    private String supervisorEmail;

    public ReviewSupervisorDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ReviewSupervisorDAO" );
    }

    public Map<String, Object> getSupervisorApproval( String applicationID ) {
        logger.debugMethod( "getSupervisorApproval" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, Object> supervisorApproval = new HashMap<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_SUPERVISOR_APPROVAL_SQL );
            preparedStatement.setString( 1, applicationID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                boolean confirmApplied = resultSet.getBoolean( "ConfirmApplied" );
                boolean attestCredentials = resultSet.getBoolean( "AttestCredentials" );
                boolean attestActivities = resultSet.getBoolean( "AttestActivities" );
                boolean abideToPairo = resultSet.getBoolean( "AbideToPairo" );
                boolean attestSupervision = resultSet.getBoolean( "AttestSupervision" );
                boolean informCpso = resultSet.getBoolean( "InformCpso" );
                boolean provideInformation = resultSet.getBoolean( "ProvideInformation" );
                boolean informActivities = resultSet.getBoolean( "InformActivities" );
                boolean issueCertificate = resultSet.getBoolean( "IssueCertificate" );
                boolean confirmCertificate = resultSet.getBoolean( "ConfirmCertificate" );
                boolean notBeMRP = resultSet.getBoolean( "NotBeMRP" );

                String dutiesDescription = resultSet.getString( "DutiesDescription" );
                String supervisorName = resultSet.getString( "SupervisorName" );
                String supervisorPhone = resultSet.getString( "SupervisorPhone" );
                setSupervisorEmail( resultSet.getString( "SupervisorEmail" ) );
                String supervisionDescription = resultSet.getString( "SupervisionDescription" );

                boolean applicationConfirmation = resultSet.getBoolean( "ApplicationConfirmation" );

                supervisorApproval.put( "ConfirmApplied", confirmApplied );
                supervisorApproval.put( "AttestCredentials", attestCredentials );

                supervisorApproval.put( "AttestActivities", attestActivities );
                supervisorApproval.put( "AbideToPairo", abideToPairo );
                supervisorApproval.put( "AttestSupervision", attestSupervision );
                supervisorApproval.put( "InformCpso", informCpso );
                supervisorApproval.put( "ProvideInformation", provideInformation );
                supervisorApproval.put( "InformActivities", informActivities );

                supervisorApproval.put( "IssueCertificate", issueCertificate );
                supervisorApproval.put( "ConfirmCertificate", confirmCertificate );
                supervisorApproval.put( "NotBeMRP", notBeMRP );

                supervisorApproval.put( "DutiesDescription", dutiesDescription );
                supervisorApproval.put( "SupervisorName", supervisorName );
                supervisorApproval.put( "SupervisorPhone", supervisorPhone );
                supervisorApproval.put( "SupervisorEmail", getSupervisorEmail() );
                supervisorApproval.put( "SupervisionDescription", supervisionDescription );

                supervisorApproval.put( "ApproverName", getApproverName( applicationID ) );

                supervisorApproval.put( "ApplicationConfirmation", applicationConfirmation );
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

        return supervisorApproval;
    }

    public boolean addSupervisorApproval( String applicationID, boolean confirmApplied, boolean attestCredentials, boolean attestActivities,
            boolean abideToPairo, boolean attestSupervision, boolean informCpso, boolean provideInformation, String dutiesDescription,
            String supervisorName, String supervisorPhone, String supervisorEmail, String supervisionDescription, boolean applicationConfirmation,
            boolean issueCertificate, boolean confirmCertificate, boolean notBeMRP, boolean informActivities ) {
        logger.debugMethod( "addSupervisorApproval" );
        boolean returnedValue = false;
        setSupervisorEmail( supervisorEmail );

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int insertCountSA = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( ADD_SUPERVISOR_APPROVAL_SQL );

            preparedStatement.setString( 1, applicationID );
            preparedStatement.setBoolean( 2, confirmApplied );
            preparedStatement.setBoolean( 3, attestCredentials );
            preparedStatement.setBoolean( 4, attestActivities );
            preparedStatement.setBoolean( 5, abideToPairo );
            preparedStatement.setBoolean( 6, attestSupervision );
            preparedStatement.setBoolean( 7, informCpso );
            preparedStatement.setBoolean( 8, provideInformation );
            preparedStatement.setString( 9, dutiesDescription );
            preparedStatement.setString( 10, supervisorName );
            preparedStatement.setString( 11, supervisorPhone );
            preparedStatement.setString( 12, getSupervisorEmail() );
            preparedStatement.setString( 13, supervisionDescription );
            preparedStatement.setBoolean( 14, applicationConfirmation );
            preparedStatement.setBoolean( 15, issueCertificate );
            preparedStatement.setBoolean( 16, confirmCertificate );
            preparedStatement.setBoolean( 17, notBeMRP );
            preparedStatement.setBoolean( 18, informActivities );

            insertCountSA = preparedStatement.executeUpdate();

            if( insertCountSA != 1 ) {
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
