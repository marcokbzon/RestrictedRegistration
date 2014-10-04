package com.moh.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.moh.common._ParentDAO;
import com.moh.data.bean.AdminAppListByCriteriaData;
import com.moh.utils.Logger;

public class ListBaDAO extends _ParentDAO {

    /*
    private String GET_ALL_APPS_BY_UNIVERSITY_SQL =
    "SELECT app.ApplicationID, usr.FirstName, usr.LastName, ins.Name_EN AS InstitutionName, " +
    "uni.Name_EN AS UniversityName, cod.Description_EN AS StatusName, cod.IconName AS StatusIcon " +
    "FROM UserInfo usr, Application app, Institution ins, UnivProg upg, University uni, Code cod " +
    "WHERE usr.Email = app.Email " +
    "AND app.InstitutionID = ins.InstitutionID " +
    "AND usr.UnivProgID = upg.UnivProgID " +
    "AND upg.UniversityID =  uni.UniversityID " +
    "AND app.CodeID = cod.CodeID " +
    "ORDER BY ApplicationID";
     */
    private String GET_ALL_APPS_BY_UNIVERSITY_SQL =
            "SELECT app.ApplicationID, usr.FirstName, usr.LastName, ins.Name_EN AS InstitutionName, "
            + "uni.Name_EN AS UniversityName, cod.Description_EN AS StatusName, cod.IconName AS StatusIcon "
            + "FROM UserInfo usr, Application app, Institution ins, UnivProg upg, University uni, Code cod, Status sta "
            + "WHERE usr.Email = app.Email "
            + "AND app.InstitutionID = ins.InstitutionID "
            + "AND usr.UnivProgID = upg.UnivProgID "
            + "AND upg.UniversityID =  uni.UniversityID "
            + "AND app.CodeID = cod.CodeID "
            + "AND sta.ApplicationID = app.ApplicationID "
            + "AND sta.RoleID = 'UMR' "
            + "AND sta.CodeID = 'SBM' "
            + "ORDER BY sta.UpdatedOn";
    /*
    private String GET_ALL_APPS_BY_UNIVERSITY_FILTERED_SQL =
    "SELECT app.ApplicationID, usr.FirstName, usr.LastName, ins.Name_EN AS InstitutionName, " +
    "uni.Name_EN AS UniversityName, cod.Description_EN AS StatusName, cod.IconName AS StatusIcon " +
    "FROM UserInfo usr, Application app, Institution ins, UnivProg upg, University uni, Code cod " +
    "WHERE usr.Email = app.Email " +
    "AND app.CodeID = ? " +
    "AND app.InstitutionID = ins.InstitutionID " +
    "AND usr.UnivProgID = upg.UnivProgID " +
    "AND upg.UniversityID =  uni.UniversityID " +
    "AND app.CodeID = cod.CodeID " +
    "ORDER BY ApplicationID";
     */
    private String GET_ALL_APPS_BY_UNIVERSITY_FILTERED_SQL =
            "SELECT app.ApplicationID, usr.FirstName, usr.LastName, ins.Name_EN AS InstitutionName, "
            + "uni.Name_EN AS UniversityName, cod.Description_EN AS StatusName, cod.IconName AS StatusIcon "
            + "FROM UserInfo usr, Application app, Institution ins, UnivProg upg, University uni, Code cod, Status sta "
            + "WHERE usr.Email = app.Email "
            + "AND app.CodeID = ? "
            + "AND app.InstitutionID = ins.InstitutionID "
            + "AND usr.UnivProgID = upg.UnivProgID "
            + "AND upg.UniversityID =  uni.UniversityID "
            + "AND app.CodeID = cod.CodeID "
            + "AND sta.ApplicationID = app.ApplicationID "
            + "AND sta.RoleID = 'UMR' "
            + "AND sta.CodeID = 'SBM' "
            + "ORDER BY sta.UpdatedOn";
    private String GET_COUNT_ALL_APPS_BY_UNIVERSITY_FILTERED_SQL =
            "SELECT COUNT( app.ApplicationID ) AS ApplicationCounter "
            + "FROM UserInfo usr, Application app, Institution ins, UnivProg upg, University uni, Code cod, Status sta "
            + "WHERE usr.Email = app.Email "
            + "AND app.CodeID = ? "
            + "AND app.InstitutionID = ins.InstitutionID "
            + "AND usr.UnivProgID = upg.UnivProgID "
            + "AND upg.UniversityID =  uni.UniversityID "
            + "AND app.CodeID = cod.CodeID "
            + "AND sta.ApplicationID = app.ApplicationID "
            + "AND sta.RoleID = 'UMR' "
            + "AND sta.CodeID = 'SBM'";
    /*
    private String GET_APPS_BY_UNIVERSITY_SQL =
    "SELECT app.ApplicationID, usr.FirstName, usr.LastName, ins.Name_EN AS InstitutionName, " +
    "uni.Name_EN AS UniversityName, cod.Description_EN AS StatusName, cod.IconName AS StatusIcon " +
    "FROM UserInfo usr, Application app, Institution ins, UnivProg upg, University uni, Code cod " +
    "WHERE usr.Email = app.Email " +
    "AND app.InstitutionID = ins.InstitutionID " +
    "AND usr.UnivProgID = upg.UnivProgID " +
    "AND upg.UniversityID =  uni.UniversityID " +
    "AND app.CodeID = cod.CodeID " +
    "AND uni.UniversityID = ? " +
    "ORDER BY ApplicationID";
     */
    private String GET_APPS_BY_UNIVERSITY_SQL =
            "SELECT app.ApplicationID, usr.FirstName, usr.LastName, ins.Name_EN AS InstitutionName, "
            + "uni.Name_EN AS UniversityName, cod.Description_EN AS StatusName, cod.IconName AS StatusIcon "
            + "FROM UserInfo usr, Application app, Institution ins, UnivProg upg, University uni, Code cod, Status sta "
            + "WHERE usr.Email = app.Email "
            + "AND app.InstitutionID = ins.InstitutionID "
            + "AND usr.UnivProgID = upg.UnivProgID "
            + "AND upg.UniversityID =  uni.UniversityID "
            + "AND app.CodeID = cod.CodeID "
            + "AND uni.UniversityID = ? "
            + "AND sta.ApplicationID = app.ApplicationID "
            + "AND sta.RoleID = 'UMR' "
            + "AND sta.CodeID = 'SBM' "
            + "ORDER BY sta.UpdatedOn";
    /*
    private String GET_APPS_BY_UNIVERSITY_FILTERED_SQL =
    "SELECT app.ApplicationID, usr.FirstName, usr.LastName, ins.Name_EN AS InstitutionName, " +
    "uni.Name_EN AS UniversityName, cod.Description_EN AS StatusName, cod.IconName AS StatusIcon " +
    "FROM UserInfo usr, Application app, Institution ins, UnivProg upg, University uni, Code cod " +
    "WHERE usr.Email = app.Email " +
    "AND app.InstitutionID = ins.InstitutionID " +
    "AND usr.UnivProgID = upg.UnivProgID " +
    "AND upg.UniversityID =  uni.UniversityID " +
    "AND app.CodeID = cod.CodeID " +
    "AND uni.UniversityID = ? " +
    "AND app.CodeID = ? " +
    "ORDER BY ApplicationID";
     */
    private String GET_APPS_BY_UNIVERSITY_FILTERED_SQL =
            "SELECT app.ApplicationID, usr.FirstName, usr.LastName, ins.Name_EN AS InstitutionName, "
            + "uni.Name_EN AS UniversityName, cod.Description_EN AS StatusName, cod.IconName AS StatusIcon "
            + "FROM UserInfo usr, Application app, Institution ins, UnivProg upg, University uni, Code cod, Status sta "
            + "WHERE usr.Email = app.Email "
            + "AND app.InstitutionID = ins.InstitutionID "
            + "AND usr.UnivProgID = upg.UnivProgID "
            + "AND upg.UniversityID =  uni.UniversityID "
            + "AND app.CodeID = cod.CodeID "
            + "AND uni.UniversityID = ? "
            + "AND app.CodeID = ? "
            + "AND sta.ApplicationID = app.ApplicationID "
            + "AND sta.RoleID = 'UMR' "
            + "AND sta.CodeID = 'SBM' "
            + "ORDER BY sta.UpdatedOn";

    public ListBaDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ListBaDAO" );
    }

    public List<AdminAppListByCriteriaData> getAppListByUniversity( String universityID, String codeID ) {
        logger.debugMethod( "getAppListByUniversity" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<AdminAppListByCriteriaData> applicationsList = new ArrayList<>();
        int counter = 1;

        try {
            connection = getConnection();

            if( codeID.equals( DEFAULT_ALL ) ) {
                if( universityID == null || universityID.trim().equals( EMPTY_STRING ) ) {
                    preparedStatement = connection.prepareStatement( GET_ALL_APPS_BY_UNIVERSITY_SQL );
                }
                else {
                    if( universityID.length() != COL_LENGTH_UNIVERSITYID ) {
                        universityID = universityID.substring( COL_SORT_LENGTH );
                    }

                    preparedStatement = connection.prepareStatement( GET_APPS_BY_UNIVERSITY_SQL );
                    preparedStatement.setString( 1, universityID );
                }
            }
            else {
                if( universityID == null || universityID.trim().equals( EMPTY_STRING ) ) {
                    preparedStatement = connection.prepareStatement( GET_ALL_APPS_BY_UNIVERSITY_FILTERED_SQL );
                    preparedStatement.setString( 1, codeID );
                }
                else {
                    if( universityID.length() != COL_LENGTH_UNIVERSITYID ) {
                        universityID = universityID.substring( COL_SORT_LENGTH );
                    }

                    preparedStatement = connection.prepareStatement( GET_APPS_BY_UNIVERSITY_FILTERED_SQL );
                    preparedStatement.setString( 1, universityID );
                    preparedStatement.setString( 2, codeID );
                }
            }

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String applicationID = resultSet.getString( "ApplicationID" );
                String firstName = resultSet.getString( "FirstName" );
                String lastName = resultSet.getString( "LastName" );
                String institutionName = resultSet.getString( "InstitutionName" );
                String universityName = resultSet.getString( "UniversityName" );
                String statusName = resultSet.getString( "StatusName" );
                String statusIcon = resultSet.getString( "StatusIcon" );
                String sCounter = EMPTY_STRING + counter++;

                applicationsList.add( new AdminAppListByCriteriaData( applicationID, firstName, lastName, institutionName, universityName, statusName, statusIcon, sCounter ) );
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

    public String getInitialApplicationCounter( String codeID ) {
        logger.debugMethod( "getInitialApplicationCounter" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String applicationCounter = "0";

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_COUNT_ALL_APPS_BY_UNIVERSITY_FILTERED_SQL );
            preparedStatement.setString( 1, codeID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                applicationCounter = resultSet.getString( "ApplicationCounter" );
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
