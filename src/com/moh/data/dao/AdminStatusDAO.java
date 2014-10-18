package com.moh.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.moh.common._ParentDAO;
import com.moh.utils.Logger;

public class AdminStatusDAO extends _ParentDAO {

    private String GET_APPLICATIONS_ALL_COUNT =
            "SELECT COUNT(*) AS ApplicationCount "
            + "FROM Application "
            + "WHERE ApprovedByCPSO = 1";
    private String GET_RESIDENTS_APPLIED =
            "SELECT Email AS GroupList "
            + "FROM Application "
            + "GROUP BY Email";
    private String GET_RESIDENTS_ALL_COUNT =
            "SELECT Email "
            + "FROM Application "
            + "WHERE RoleID = ? "
            + "AND App";
    private String GET_RESIDENTS_APPROVED =
    		"SELECT ap.Email "
    		+ "FROM Application ap, UserRole ur "
    		+ "WHERE ap.ApprovedByCPSO = 1 "
    		+ "AND ap.Email = ur.Email "
    		+ "AND ur.RoleID = 'UMR' "
    		+ "GROUP BY ap.Email";
    private String GET_UNIVERSITIES_ALL_COUNT =
            "SELECT COUNT(*) AS EntityCount "
            + "FROM University "
            + "WHERE Enabled = 1";
    private String GET_UNIVERSITIES_APPLIED =
            "SELECT UniversityID AS GroupList "
            + "FROM UnivProg "
            + "WHERE UnivProgID IN "
            + "( "
            + "SELECT UnivProgID "
            + "FROM Application app, UserInfo usr "
            + "WHERE app.Email = usr.Email "
            + "GROUP BY UnivProgID "
            + ") "
            + "GROUP BY UniversityID";
    private String GET_PROGRAMS_ALL_COUNT =
            "SELECT COUNT(*) AS EntityCount "
            + "FROM Program "
            + "WHERE Enabled = 1";
    private String GET_PROGRAMS_APPLIED =
            "SELECT ProgramID AS GroupList "
            + "FROM UnivProg "
            + "WHERE UnivProgID IN "
            + "( "
            + "SELECT UnivProgID "
            + "FROM Application app, UserInfo usr "
            + "WHERE app.Email = usr.Email "
            + "GROUP BY UnivProgID "
            + ") "
            + "GROUP BY ProgramID";
    private String GET_INSTITUTIONS_ALL_COUNT =
            "SELECT COUNT(*) AS EntityCount "
            + "FROM Institution "
            + "WHERE Enabled = 1";
    private String GET_INSTITUTIONS_APPLIED =
            "SELECT InstitutionID AS GroupList "
            + "FROM Application "
            + "GROUP BY InstitutionID";
    private String GET_SERVICETYPES_ALL_COUNT =
            "SELECT COUNT(*) AS EntityCount "
            + "FROM ServiceType";
    private String GET_SERVICETYPES_APPLIED =
            "SELECT ServiceTypeID AS GroupList "
            + "FROM Application "
            + "GROUP BY ServiceTypeID";

    public AdminStatusDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "AdminStatusDAO" );
    }

    public String applicationCount( String appFilter ) {
        logger.debugMethod( "applicationCount" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        int intResult = 0;

        try {
            connection = getConnection();

            if( appFilter.equals( DEFAULT_ALL ) ) {
                preparedStatement = connection.prepareStatement( GET_APPLICATIONS_ALL_COUNT );
            }
            else {
                if( appFilter.equals( PHASE_NEW ) ) {
                    preparedStatement = connection.prepareStatement( EMPTY_STRING );
                    //
                }
                else {
                    if( appFilter.equals( PHASE_INPROGRESS ) ) {
                        preparedStatement = connection.prepareStatement( EMPTY_STRING );
                        //
                    }
                    else {
                        if( appFilter.equals( PHASE_COMPLETED ) ) {
                            preparedStatement = connection.prepareStatement( EMPTY_STRING );
                            //
                        }
                    }
                }
            }

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                intResult = resultSet.getInt( "ApplicationCount" );
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

        return EMPTY_STRING + intResult;
    }
    
    public String countResidentsApprovedByCPSO() {
        logger.debugMethod( "countResidentsApprovedByCPSO" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        int intResult = 0;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_RESIDENTS_APPROVED );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
            	intResult++;
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

        return EMPTY_STRING + intResult;
    }

    public String userCount( String roleFilter ) {
        logger.debugMethod( "userCount" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        int intResult = 0;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_RESIDENTS_ALL_COUNT );
            preparedStatement.setString( 1, roleFilter );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                intResult = resultSet.getInt( "UserCount" );
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

        return EMPTY_STRING + intResult;
    }

    public String institutionsAppliedCount() {
        return appliedCount( "INSTITUTION" );
    }

    public String residentsAppliedCount() {
        return appliedCount( "RESIDENT" );
    }

    public String serviceTypesAppliedCount() {
        return appliedCount( "SERVICETYPE" );
    }

    public String universitiesAppliedCount() {
        return appliedCount( "UNIVERSITY" );
    }

    public String programsAppliedCount() {
        return appliedCount( "PROGRAM" );
    }

    private String appliedCount( String entityType ) {
        logger.debugMethod( "appliedCount" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        @SuppressWarnings( "unused" )
        String dummy = EMPTY_STRING;
        int counter = 0;

        try {
            connection = getConnection();

            if( entityType.equals( "RESIDENT" ) ) {
                preparedStatement = connection.prepareStatement( GET_RESIDENTS_APPLIED );
            }
            else {
                if( entityType.equals( "PROGRAM" ) ) {
                    preparedStatement = connection.prepareStatement( GET_PROGRAMS_APPLIED );
                }
                else {
                    if( entityType.equals( "INSTITUTION" ) ) {
                        preparedStatement = connection.prepareStatement( GET_INSTITUTIONS_APPLIED );
                    }
                    else {
                        if( entityType.equals( "SERVICETYPE" ) ) {
                            preparedStatement = connection.prepareStatement( GET_SERVICETYPES_APPLIED );
                        }
                        else {
                            if( entityType.equals( "UNIVERSITY" ) ) {
                                preparedStatement = connection.prepareStatement( GET_UNIVERSITIES_APPLIED );
                            }
                        }
                    }
                }
            }

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                dummy = resultSet.getString( "GroupList" );
                counter++;
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

        return EMPTY_STRING + counter;
    }

    public String universityCount() {
        return entityCount( "UNIVERSITY" );
    }

    public String programCount() {
        return entityCount( "PROGRAM" );
    }

    public String institutionCount() {
        return entityCount( "INSTITUTION" );
    }

    public String serviceTypeCount() {
        return entityCount( "SERVICETYPE" );
    }

    private String entityCount( String entityType ) {
        logger.debugMethod( "entityCount" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        int intResult = 0;

        try {
            connection = getConnection();

            if( entityType.equals( "UNIVERSITY" ) ) {
                preparedStatement = connection.prepareStatement( GET_UNIVERSITIES_ALL_COUNT );
            }
            else {
                if( entityType.equals( "PROGRAM" ) ) {
                    preparedStatement = connection.prepareStatement( GET_PROGRAMS_ALL_COUNT );
                }
                else {
                    if( entityType.equals( "INSTITUTION" ) ) {
                        preparedStatement = connection.prepareStatement( GET_INSTITUTIONS_ALL_COUNT );
                    }
                    else {
                        if( entityType.equals( "SERVICETYPE" ) ) {
                            preparedStatement = connection.prepareStatement( GET_SERVICETYPES_ALL_COUNT );
                        }
                    }
                }
            }

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                intResult = resultSet.getInt( "EntityCount" );
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

        return EMPTY_STRING + intResult;
    }
}
