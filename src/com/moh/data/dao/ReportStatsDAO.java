package com.moh.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.moh.common._ParentDAO;
import com.moh.data.bean.ReportAppsByInstData;
import com.moh.data.bean.ReportAppsByProgData;
import com.moh.data.bean.ReportAppsByServData;
import com.moh.data.bean.ReportAppsByUnivData;
import com.moh.utils.Logger;

public class ReportStatsDAO extends _ParentDAO {

    // Applications By University
    private String GET_APPS_BY_UNIV_SQL =
            "SELECT uni.Name_EN, COUNT(*) AS Count "
            + "FROM Application app, UserInfo usr, UnivProg upg, University uni "
            + "WHERE app.Email = usr.Email "
            + "AND app.ApprovedByCPSO = 1 "
            + "AND usr.UnivProgID = upg.UnivProgID "
            + "AND upg.UniversityID = uni.UniversityID "
            + "GROUP BY Name_EN";
    // Applications By Program
    private String GET_APPS_BY_PROG_SQL =
            "SELECT prg.Description_EN, COUNT(*) AS Count "
            + "FROM Application app, UserInfo usr, UnivProg upg, Program prg "
            + "WHERE app.Email = usr.Email "
            + "AND app.ApprovedByCPSO = 1 "
            + "AND usr.UnivProgID = upg.UnivProgID "
            + "AND upg.ProgramID = prg.ProgramID "
            + "GROUP BY Description_EN";
    // Applications By Institution
    private String GET_APPS_BY_INST_SQL =
            "SELECT ins.Name_EN, COUNT(*) AS Count "
            + "FROM Application app, Institution ins "
            + "WHERE app.InstitutionID = ins.InstitutionID "
            + "AND app.ApprovedByCPSO = 1 "
            + "GROUP BY Name_EN";
    // Applications By Service Type
    private String GET_APPS_BY_SERV_SQL =
            "SELECT srv.Name_EN, COUNT(*) AS Count "
            + "FROM Application app, ServiceType srv "
            + "WHERE app.ServiceTypeID = srv.ServiceTypeID "
            + "AND app.ApprovedByCPSO = 1 "
            + "GROUP BY Name_EN";

    public ReportStatsDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ReportStatsDAO" );
    }

    public List<ReportAppsByUnivData> getAppsByUniversity() {
        logger.debugMethod( "getAppsByUniversity" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<ReportAppsByUnivData> reportList = new ArrayList<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_APPS_BY_UNIV_SQL );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String entityName = resultSet.getString( "Name_EN" );
                String entityCount = resultSet.getString( "Count" );

                reportList.add( new ReportAppsByUnivData( entityName, entityCount ) );
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

        return reportList;
    }

    public List<ReportAppsByProgData> getAppsByProgram() {
        logger.debugMethod( "getAppsByProgram" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<ReportAppsByProgData> reportList = new ArrayList<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_APPS_BY_PROG_SQL );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String entityName = resultSet.getString( "Description_EN" );
                String entityCount = resultSet.getString( "Count" );

                reportList.add( new ReportAppsByProgData( entityName, entityCount ) );
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

        return reportList;
    }

    public List<ReportAppsByInstData> getAppsByInstitution() {
        logger.debugMethod( "getAppsByInstitution" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<ReportAppsByInstData> reportList = new ArrayList<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_APPS_BY_INST_SQL );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String entityName = resultSet.getString( "Name_EN" );
                String entityCount = resultSet.getString( "Count" );

                reportList.add( new ReportAppsByInstData( entityName, entityCount ) );
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

        return reportList;
    }

    public List<ReportAppsByServData> getAppsByServType() {
        logger.debugMethod( "getAppsByServType" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<ReportAppsByServData> reportList = new ArrayList<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_APPS_BY_SERV_SQL );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String entityName = resultSet.getString( "Name_EN" );
                String entityCount = resultSet.getString( "Count" );

                reportList.add( new ReportAppsByServData( entityName, entityCount ) );
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

        return reportList;
    }
}
