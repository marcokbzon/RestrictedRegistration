package com.moh.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.moh.common._ParentDAO;
import com.moh.data.bean.AdminAppListByCriteriaData2;
import com.moh.data.bean.AppReviewAppHistoryData;
import com.moh.utils.Logger;

public class ListBbDAO extends _ParentDAO {

    /*
     *  RESIDENTS
     */
    private String GET_APPS_IN_INI_STAGE_ALL_SQL =
            "SELECT app.ApplicationID, usr.FirstName, usr.LastName, ins.Name_EN AS InstitutionName, "
            + "srv.Name_EN AS ServiceTypeName, uni.Name_EN AS UniversityName "
            + "FROM UserInfo usr, Application app, Institution ins, UnivProg upg, University uni, Code cod, ServiceType srv "
            + "WHERE usr.Email = app.Email "
            + "AND app.ApplicationID IN ("
            + "select x.ApplicationID from Status x "
            + "where x.RoleID = ? "
            + "and x.CodeID = 'SBM' "
            + "and x.ApplicationID NOT IN (select y.ApplicationID from Status y where y.RoleID = ?) "
            + "group by x.ApplicationID) "
            + "AND app.InstitutionID = ins.InstitutionID "
            + "AND usr.UnivProgID = upg.UnivProgID "
            + "AND upg.UniversityID =  uni.UniversityID "
            + "AND app.ServiceTypeID = srv.ServiceTypeID "
            + "AND app.CodeID = cod.CodeID "
            + "ORDER BY ApplicationID";
    private String GET_APPS_IN_INI_STAGE_BY_UNIVERSITY_SQL =
            "SELECT app.ApplicationID, usr.FirstName, usr.LastName, ins.Name_EN AS InstitutionName, "
            + "srv.Name_EN AS ServiceTypeName, uni.Name_EN AS UniversityName "
            + "FROM UserInfo usr, Application app, Institution ins, UnivProg upg, University uni, Code cod, ServiceType srv "
            + "WHERE usr.Email = app.Email "
            + "AND app.ApplicationID IN ("
            + "select x.ApplicationID from Status x "
            + "where x.RoleID = ? "
            + "and x.CodeID = 'SBM' "
            + "and x.ApplicationID NOT IN (select y.ApplicationID from Status y where y.RoleID = ?) "
            + "group by x.ApplicationID) "
            + "AND app.InstitutionID = ins.InstitutionID "
            + "AND usr.UnivProgID = upg.UnivProgID "
            + "AND upg.UniversityID =  uni.UniversityID "
            + "AND app.ServiceTypeID = srv.ServiceTypeID "
            + "AND app.CodeID = cod.CodeID "
            + "AND uni.UniversityID = ? "
            + "ORDER BY ApplicationID";
    private String GET_INIT_COUNT_SQL =
            "SELECT COUNT(*) AS InitCount "
            + "FROM UserInfo usr, Application app, Institution ins, UnivProg upg, University uni, Code cod, ServiceType srv "
            + "WHERE usr.Email = app.Email "
            + "AND app.ApplicationID IN ("
            + "select x.ApplicationID from Status x "
            + "where x.RoleID = 'ISV' "
            + "and x.ApplicationID NOT IN (select y.ApplicationID from Status y where y.RoleID = 'UPD') "
            + "group by x.ApplicationID) "
            + "AND app.InstitutionID = ins.InstitutionID "
            + "AND usr.UnivProgID = upg.UnivProgID "
            + "AND upg.UniversityID =  uni.UniversityID "
            + "AND app.ServiceTypeID = srv.ServiceTypeID "
            + "AND app.CodeID = cod.CodeID";
    /*
     *  SUPERVISORS, PROGRAM DIRECTORS and DEANS
     */
    private String GET_APPS_IN_MID_STAGE_ALL_SQL =
            "SELECT app.ApplicationID, usr.FirstName, usr.LastName, ins.Name_EN AS InstitutionName, "
            + "srv.Name_EN AS ServiceTypeName, uni.Name_EN AS UniversityName "
            + "FROM UserInfo usr, Application app, Institution ins, UnivProg upg, University uni, Code cod, ServiceType srv "
            + "WHERE usr.Email = app.Email "
            + "AND app.ApplicationID IN ("
            + "select x.ApplicationID from Status x "
            + "where x.RoleID = ? "
            + "and x.CodeID = ? "
            + "and x.ApplicationID NOT IN (select y.ApplicationID from Status y where y.RoleID = ?) "
            + "group by x.ApplicationID) "
            + "AND app.InstitutionID = ins.InstitutionID "
            + "AND usr.UnivProgID = upg.UnivProgID "
            + "AND upg.UniversityID =  uni.UniversityID "
            + "AND app.ServiceTypeID = srv.ServiceTypeID "
            + "AND app.CodeID = cod.CodeID "
            + "ORDER BY ApplicationID";
    private String GET_APPS_IN_MID_STAGE_BY_UNIVERSITY_SQL =
            "SELECT app.ApplicationID, usr.FirstName, usr.LastName, ins.Name_EN AS InstitutionName, "
            + "srv.Name_EN AS ServiceTypeName, uni.Name_EN AS UniversityName "
            + "FROM UserInfo usr, Application app, Institution ins, UnivProg upg, University uni, Code cod, ServiceType srv "
            + "WHERE usr.Email = app.Email "
            + "AND app.ApplicationID IN ("
            + "select x.ApplicationID from Status x "
            + "where x.RoleID = ? "
            + "and x.CodeID = ? "
            + "and x.ApplicationID NOT IN (select y.ApplicationID from Status y where y.RoleID = ?) "
            + "group by x.ApplicationID) "
            + "AND app.InstitutionID = ins.InstitutionID "
            + "AND usr.UnivProgID = upg.UnivProgID "
            + "AND upg.UniversityID =  uni.UniversityID "
            + "AND app.ServiceTypeID = srv.ServiceTypeID "
            + "AND app.CodeID = cod.CodeID "
            + "AND uni.UniversityID = ? "
            + "ORDER BY ApplicationID";

    /*
     *  COMMITTEE
     */
    private String GET_APPS_IN_END_STAGE_ALL_SQL =
            "SELECT app.ApplicationID, usr.FirstName, usr.LastName, ins.Name_EN AS InstitutionName, "
            + "srv.Name_EN AS ServiceTypeName, uni.Name_EN AS UniversityName "
            + "FROM UserInfo usr, Application app, Institution ins, UnivProg upg, University uni, Code cod, ServiceType srv "
            + "WHERE usr.Email = app.Email "
            + "AND app.ApplicationID IN ("
            + "select x.ApplicationID from Status x "
            + "where x.RoleID = ? "
            + "and x.CodeID = ? "
            + "group by x.ApplicationID) "
            + "AND app.InstitutionID = ins.InstitutionID "
            + "AND usr.UnivProgID = upg.UnivProgID "
            + "AND upg.UniversityID =  uni.UniversityID "
            + "AND app.ServiceTypeID = srv.ServiceTypeID "
            + "AND app.CodeID = cod.CodeID "
            + "ORDER BY ApplicationID";
    private String GET_APPS_IN_END_STAGE_BY_UNIVERSITY_SQL =
            "SELECT app.ApplicationID, usr.FirstName, usr.LastName, ins.Name_EN AS InstitutionName, "
            + "srv.Name_EN AS ServiceTypeName, uni.Name_EN AS UniversityName "
            + "FROM UserInfo usr, Application app, Institution ins, UnivProg upg, University uni, Code cod, ServiceType srv "
            + "WHERE usr.Email = app.Email "
            + "AND app.ApplicationID IN ("
            + "select x.ApplicationID from Status x "
            + "where x.RoleID = ? "
            + "and x.CodeID = ? "
            + "group by x.ApplicationID) "
            + "AND app.InstitutionID = ins.InstitutionID "
            + "AND usr.UnivProgID = upg.UnivProgID "
            + "AND upg.UniversityID =  uni.UniversityID "
            + "AND app.ServiceTypeID = srv.ServiceTypeID "
            + "AND app.CodeID = cod.CodeID "
            + "AND uni.UniversityID = ? "
            + "ORDER BY ApplicationID";

    /*
     *  ALL REVIEWERS
     */
    private String GET_APPS_HISTORY_BY_REVIEWER_EMAIL_SQL =
            "SELECT app.ApplicationID, usr.FirstName, usr.LastName, ins.Name_EN AS InstitutionName, "
            + "uni.Name_EN AS UniversityName, srv.Name_EN AS ServiceTypeName, cod.Description_EN AS StatusName, cod.IconName AS StatusIcon "
            + "FROM UserInfo usr, Application app, Institution ins, UnivProg upg, University uni, Code cod, Status sta, ServiceType srv "
            + "WHERE usr.Email = app.Email "
            + "AND app.InstitutionID = ins.InstitutionID "
            + "AND usr.UnivProgID = upg.UnivProgID "
            + "AND upg.UniversityID =  uni.UniversityID "
            + "AND uni.UniversityID = ? "
            + "AND app.CodeID = cod.CodeID "
            + "AND sta.ApplicationID = app.ApplicationID "
            + "AND app.ServiceTypeID = srv.ServiceTypeID "
            + "AND sta.Email = ? "
            + "ORDER BY sta.UpdatedOn";
    
    private String email;

    public ListBbDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ListBaDAO" );
    }

    public List<AdminAppListByCriteriaData2> getAppListByUniversity( String universityID, String codeID, String roleID ) {
        logger.debugMethod( "getAppListByUniversity" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<AdminAppListByCriteriaData2> applicationsList = new ArrayList<>();
        String subRoleID = EMPTY_STRING;
        int counter = 1;

        if( roleID.equals( ROLE_RESIDENT ) ) {
            subRoleID = ROLE_SUPERVISOR;
        }
        else {
            if( roleID.equals( ROLE_SUPERVISOR ) ) {
                subRoleID = ROLE_DIRECTOR;
            }
            else {
                if( roleID.equals( ROLE_DIRECTOR ) ) {
                    subRoleID = ROLE_DEAN;
                }
                else {
                    if( roleID.equals( ROLE_DEAN ) ) {
                        subRoleID = ROLE_COMMITTEE;
                    }
                }
            }
        }

        try {
            connection = getConnection();

            if( roleID.equals( ROLE_RESIDENT ) ) {
                if( universityID == null || universityID.trim().equals( EMPTY_STRING ) ) {
                    preparedStatement = connection.prepareStatement( GET_APPS_IN_INI_STAGE_ALL_SQL );
                    preparedStatement.setString( 1, roleID );
                    preparedStatement.setString( 2, subRoleID );
                }
                else {
                    if( universityID.length() != COL_LENGTH_UNIVERSITYID ) {
                        universityID = universityID.substring( COL_SORT_LENGTH );
                    }

                    preparedStatement = connection.prepareStatement( GET_APPS_IN_INI_STAGE_BY_UNIVERSITY_SQL );
                    preparedStatement.setString( 1, roleID );
                    preparedStatement.setString( 2, subRoleID );
                    preparedStatement.setString( 3, universityID );
                }
            }
            else {
                if( roleID.equals( ROLE_COMMITTEE ) ) {
                    if( universityID == null || universityID.trim().equals( EMPTY_STRING ) ) {
                        preparedStatement = connection.prepareStatement( GET_APPS_IN_END_STAGE_ALL_SQL );
                        preparedStatement.setString( 1, roleID );
                        preparedStatement.setString( 2, codeID ); // APR or RJT
                    }
                    else {
                        if( universityID.length() != COL_LENGTH_UNIVERSITYID ) {
                            universityID = universityID.substring( COL_SORT_LENGTH );
                        }

                        preparedStatement = connection.prepareStatement( GET_APPS_IN_END_STAGE_BY_UNIVERSITY_SQL );
                        preparedStatement.setString( 1, roleID );
                        preparedStatement.setString( 2, codeID ); // APR or RJT
                        preparedStatement.setString( 3, universityID );
                    }
                }
                else {
                    if( universityID == null || universityID.trim().equals( EMPTY_STRING ) ) {
                        preparedStatement = connection.prepareStatement( GET_APPS_IN_MID_STAGE_ALL_SQL );
                        preparedStatement.setString( 1, roleID );
                        preparedStatement.setString( 2, codeID ); // APR or RJT
                        preparedStatement.setString( 3, subRoleID );
                    }
                    else {
                        if( universityID.length() != COL_LENGTH_UNIVERSITYID ) {
                            universityID = universityID.substring( COL_SORT_LENGTH );
                        }

                        preparedStatement = connection.prepareStatement( GET_APPS_IN_MID_STAGE_BY_UNIVERSITY_SQL );
                        preparedStatement.setString( 1, roleID );
                        preparedStatement.setString( 2, codeID ); // APR or RJT
                        preparedStatement.setString( 3, subRoleID );
                        preparedStatement.setString( 4, universityID );
                    }
                }
            }

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String applicationID = resultSet.getString( "ApplicationID" );
                String firstName = resultSet.getString( "FirstName" );
                String lastName = resultSet.getString( "LastName" );
                String institutionName = resultSet.getString( "InstitutionName" );
                String universityName = resultSet.getString( "UniversityName" );
                String serviceTypeName = resultSet.getString( "ServiceTypeName" );
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

    public List<AppReviewAppHistoryData> getAppReviewAppHistoryList( String universityID, String email ) {
        logger.debugMethod( "getAppReviewAppHistoryList" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<AppReviewAppHistoryData> applicationsList = new ArrayList<>();
        int counter = 1;
        setEmail( email );

        try {
            connection = getConnection();

            if( universityID.length() != COL_LENGTH_UNIVERSITYID ) {
                universityID = universityID.substring( COL_SORT_LENGTH );
            }

            preparedStatement = connection.prepareStatement( GET_APPS_HISTORY_BY_REVIEWER_EMAIL_SQL );
            preparedStatement.setString( 1, universityID );
            preparedStatement.setString( 2, getEmail() );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String applicationID = resultSet.getString( "ApplicationID" );
                String firstName = resultSet.getString( "FirstName" );
                String lastName = resultSet.getString( "LastName" );
                String institutionName = resultSet.getString( "InstitutionName" );
                String universityName = resultSet.getString( "UniversityName" );
                String serviceTypeName = resultSet.getString( "ServiceTypeName" );
                String sCounter = EMPTY_STRING + counter++;
                String statusName = resultSet.getString( "StatusName" );
                String statusIcon = resultSet.getString( "StatusIcon" );

                applicationsList.add( new AppReviewAppHistoryData( applicationID, firstName, lastName, institutionName, serviceTypeName, universityName, sCounter, statusName, statusIcon ) );
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

    public String getInitialApplicationCounter() {
        logger.debugMethod( "getInitialApplicationCounter" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String applicationCounter = "0";

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_INIT_COUNT_SQL );

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
