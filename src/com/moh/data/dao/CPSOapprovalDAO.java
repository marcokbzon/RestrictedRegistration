package com.moh.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

import com.moh.common._ParentDAO;
import com.moh.data.bean.AdminAppListByCriteriaData2;
import com.moh.data.bean.CPSOheaderData;
import com.moh.utils.Logger;

public class CPSOapprovalDAO extends _ParentDAO {

    private String CHECK_APPLICATION_ID_SQL =
            "SELECT COUNT(*) AS appCount "
            + "FROM ApprovalCommittee "
            + "WHERE ApplicationID = ? "
            + "AND ApplicationConfirmation = 1";
    private String GET_APPLICATION_HEADER_SQL =
            "SELECT usr.FirstName, usr.LastName, uni.Name_EN AS UnivName, prg.Description_EN AS ProgName, ins.Name_EN AS InstName, srv.Name_EN AS ServName "
            + "FROM UserInfo usr, University uni, Program prg, Institution ins, ServiceType srv, Application app, UnivProg uvp "
            + "WHERE app.ApplicationID = ? "
            + "AND app.Email = usr.Email "
            + "AND app.InstitutionID = ins.InstitutionID "
            + "AND app.ServiceTypeID = srv.ServiceTypeID "
            + "AND usr.UnivProgID = uvp.UnivProgID "
            + "AND uvp.UniversityID = uni.UniversityID "
            + "AND uvp.ProgramID = prg.ProgramID";
    private String UPDATE_CPSO_APPROVAL_SQL =
            "UPDATE Application "
            + "SET ApprovedByCPSO = 1 "
            + "WHERE ApplicationID = ?";
    private String GET_CPSOAPPROVED_BY_UNIVERSITY =
            "SELECT app.ApplicationID, app.Email AS ResidentEmail, usr.FirstName, usr.LastName, "
            + "uni.Name_EN AS UniversityName, uni.universityID, prg.Description_EN AS ProgramName, "
            + "ins.Name_EN AS InstitutionName, srv.Name_EN AS ServiceName "
            + "FROM Application app, Institution ins, ServiceType srv, UserInfo usr, University uni, Program prg, UnivProg u_p "
            + "WHERE ins.InstitutionID = app.InstitutionID "
            + "AND app.Email = usr.Email "
            + "AND srv.ServiceTypeID = app.ServiceTypeID "
            + "AND usr.UnivProgID = u_p.UnivProgID "
            + "AND u_p.UniversityID = uni.UniversityID "
            + "AND uni.universityID = ? "
            + "AND u_p.ProgramID = prg.ProgramID "
            + "AND app.ApprovedByCPSO = 1 "
            + "ORDER BY ApplicationID";
    private String GET_CPSOAPPROVED_INIT_COUNT =
            "SELECT COUNT(*) AS InitCount "
            + "FROM Application app, Institution ins, ServiceType srv, InstLHIN ilh, LHIN lhn, UserInfo usr, University uni, Program prg, UnivProg u_p "
            + "WHERE ins.InstitutionID = app.InstitutionID "
            + "AND ins.InstitutionID = ilh.InstitutionID "
            + "AND app.Email = usr.Email "
            + "AND ilh.LHIN_ID = lhn.LHIN_ID "
            + "AND srv.ServiceTypeID = app.ServiceTypeID "
            + "AND usr.UnivProgID = u_p.UnivProgID "
            + "AND u_p.UniversityID = uni.UniversityID "
            + "AND uni.universityID = ? "
            + "AND u_p.ProgramID = prg.ProgramID "
            + "AND app.ApprovedByCPSO = 1";

    public CPSOapprovalDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "CPSOapprovalDAO" );
    }

    public boolean applicationIDExists( String applicationID ) {
        logger.debugMethod( "applicationIDExists" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        boolean doesExists = false;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( CHECK_APPLICATION_ID_SQL );
            preparedStatement.setString( 1, applicationID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                int appCount = Integer.parseInt( resultSet.getString( "appCount" ) );

                if( appCount > 0 ) {
                    doesExists = true;
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

        return doesExists;
    }

    public boolean getApplicationHeader( String applicationID ) {
        logger.debugMethod( "getApplicationHeader" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        boolean processedOK = true;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_APPLICATION_HEADER_SQL );
            preparedStatement.setString( 1, applicationID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String firstName = resultSet.getString( "FirstName" );
                String lastName = resultSet.getString( "LastName" );
                String univName = resultSet.getString( "UnivName" );
                String progName = resultSet.getString( "ProgName" );
                String instName = resultSet.getString( "InstName" );
                String servName = resultSet.getString( "ServName" );

                CPSOheaderData cpsoData = new CPSOheaderData();

                cpsoData.setApplicationID( applicationID );
                cpsoData.setResidentName( firstName + " " + lastName );
                cpsoData.setUniversity( univName );
                cpsoData.setProgram( progName );
                cpsoData.setInstitution( instName );
                cpsoData.setServiceType( servName );

                addToSession( SESSION_DATAMAP_CPSO, cpsoData );
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );
            processedOK = false;
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

        return processedOK;
    }

    public boolean updateCPSOapproval( String applicationID ) {
        logger.debugMethod( "updateCPSOapproval" );
        boolean returnedValue = false;

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int updateCount = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( UPDATE_CPSO_APPROVAL_SQL );
            preparedStatement.setString( 1, applicationID );

            updateCount = preparedStatement.executeUpdate();

            if( updateCount != 1 ) {
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

    public List<AdminAppListByCriteriaData2> getAppListByUniversity( String universityID ) {
        logger.debugMethod( "getAppListByUniversity" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<AdminAppListByCriteriaData2> applicationsList = new ArrayList<>();
        int counter = 1;

        try {
            connection = getConnection();

            if( universityID.length() != COL_LENGTH_UNIVERSITYID ) {
                universityID = universityID.substring( COL_SORT_LENGTH );
            }

            preparedStatement = connection.prepareStatement( GET_CPSOAPPROVED_BY_UNIVERSITY );
            preparedStatement.setString( 1, universityID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String applicationID = resultSet.getString( "ApplicationID" );
                String firstName = resultSet.getString( "FirstName" );
                String lastName = resultSet.getString( "LastName" );
                String institutionName = resultSet.getString( "InstitutionName" );
                String universityName = resultSet.getString( "UniversityName" );
                String serviceTypeName = resultSet.getString( "ServiceName" );
                String sCounter = EMPTY_STRING + counter++;

                applicationsList.add( new AdminAppListByCriteriaData2( applicationID, firstName, lastName, institutionName, serviceTypeName, universityName, sCounter ) );
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

        return applicationsList;
    }

    public String getInitialApplicationCounter( String universityID ) {
        logger.debugMethod( "getInitialApplicationCounter" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String applicationCounter = "0";

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_CPSOAPPROVED_INIT_COUNT );
            preparedStatement.setString( 1, universityID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                applicationCounter = resultSet.getString( "InitCount" );
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

        return applicationCounter;
    }
}
