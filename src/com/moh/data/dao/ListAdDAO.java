package com.moh.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.moh.common._ParentDAO;
import com.moh.data.bean.AdminInstitutionData;
import com.moh.data.bean.AdminProgramData;
import com.moh.data.bean.AdminServiceTypeData;
import com.moh.data.bean.AdminUniversityData;
import com.moh.utils.Logger;

public class ListAdDAO extends _ParentDAO {

    private String GET_UNIVERSITY_SQL =
            "SELECT UniversityID, Name_EN, Abbreviation, Enabled "
            + "FROM University "
            + "ORDER BY Name_EN";
    private String GET_PROGRAM_SQL =
            "SELECT ProgramID, Description_EN, Abbreviation, Enabled "
            + "FROM Program "
            + "ORDER BY Description_EN";
    private String GET_INSTITUTION_SQL =
            "SELECT InstitutionID, Name_EN, Abbreviation, Enabled "
            + "FROM Institution "
            + "ORDER BY Name_EN";
    private String GET_SERVICETYPE_SQL =
            "SELECT ServiceTypeID, Name_EN, Abbreviation "
            + "FROM ServiceType "
            + "ORDER BY Name_EN";

    public ListAdDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ListAdDAO" );
    }

    public List<AdminUniversityData> getAdminUniversityListing() {
        logger.debugMethod( "getAdminUniversityListing" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<AdminUniversityData> universityList = new ArrayList<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_UNIVERSITY_SQL );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String universityID = resultSet.getString( "UniversityID" );
                String name_EN = resultSet.getString( "Name_EN" );
                String abbreviation = resultSet.getString( "Abbreviation" );
                String enabled = resultSet.getString( "Enabled" );

                universityList.add( new AdminUniversityData( universityID, name_EN, abbreviation, enabled ) );
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

        return universityList;
    }

    public List<AdminProgramData> getAdminProgramListing() {
        logger.debugMethod( "getAdminProgramListing" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<AdminProgramData> programList = new ArrayList<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_PROGRAM_SQL );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String programID = resultSet.getString( "ProgramID" );
                String description_EN = resultSet.getString( "Description_EN" );
                String abbreviation = resultSet.getString( "Abbreviation" );
                String enabled = resultSet.getString( "Enabled" );

                programList.add( new AdminProgramData( programID, description_EN, abbreviation, enabled ) );
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

        return programList;
    }

    public List<AdminInstitutionData> getAdminInstitutionListing() {
        logger.debugMethod( "getAdminInstitutionListing" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<AdminInstitutionData> institutionList = new ArrayList<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_INSTITUTION_SQL );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String institutionID = resultSet.getString( "InstitutionID" );
                String name_EN = resultSet.getString( "Name_EN" );
                String abbreviation = resultSet.getString( "Abbreviation" );
                String enabled = resultSet.getString( "Enabled" );

                institutionList.add( new AdminInstitutionData( institutionID, name_EN, abbreviation, enabled ) );
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

        return institutionList;
    }

    public List<AdminServiceTypeData> getAdminServiceTypeListing() {
        logger.debugMethod( "getAdminServiceTypeListing" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<AdminServiceTypeData> serviceTypeList = new ArrayList<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_SERVICETYPE_SQL );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String serviceTypeID = resultSet.getString( "ServiceTypeID" );
                String name_EN = resultSet.getString( "Name_EN" );
                String abbreviation = resultSet.getString( "Abbreviation" );

                serviceTypeList.add( new AdminServiceTypeData( serviceTypeID, name_EN, abbreviation ) );
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

        return serviceTypeList;
    }
}
