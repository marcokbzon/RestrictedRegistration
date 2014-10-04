package com.moh.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.moh.common._ParentDAO;
import com.moh.utils.Logger;

public class ListAaDAO extends _ParentDAO {

    private String GET_COUNTRIES_SQL =
            "SELECT CountryCode, Name_EN "
            + "FROM Country "
            + "ORDER BY Name_EN, CountryCode";
    private String GET_COUNTRY_SQL =
            "SELECT Name_EN "
            + "FROM Country "
            + "WHERE CountryCode = ?";
    private String GET_PROVSTATES_SQL =
            "SELECT ProvStateCode, Name_EN "
            + "FROM ProvinceState "
            + "WHERE CountryCode = ? "
            + "ORDER BY Name_EN, ProvStateCode";
    private String GET_ALL_PROVSTATES_SQL =
            "SELECT ProvStateCode, Name_EN "
            + "FROM ProvinceState "
            + "ORDER BY CountryCode, Name_EN, ProvStateCode";
    private String GET_UNIVERSITIES_SQL =
            "SELECT UniversityID, Name_EN "
            + "FROM University "
            + "WHERE Enabled = 1 "
            + "ORDER BY Name_EN, UniversityID";
    private String GET_PROGRAMS_SQL =
            "SELECT ProgramID, Description_EN "
            + "FROM Program "
            + "WHERE Enabled = 1 "
            + "ORDER BY Description_EN, ProgramID";
    private String GET_CUSTOM_PROGRAMS_SQL =
            "SELECT prg.ProgramID, prg.Description_EN "
            + "FROM Program prg, UnivProg uniprg "
            + "WHERE prg.Enabled = 1 "
            + "AND prg.ProgramID = uniprg.ProgramID "
            + "AND uniprg.UniversityID = ? "
            + "AND uniprg.Active = 1 "
            + "ORDER BY prg.Description_EN, prg.ProgramID";
    private String GET_SERVICETYPES_SQL =
            "SELECT ServiceTypeID, Name_EN "
            + "FROM ServiceType "
            + "ORDER BY Name_EN, ServiceTypeID";
    private String numberedCountryCode = EMPTY_STRING;
    private String numberedProvStateCode = EMPTY_STRING;
    private String numberedUniversityID = EMPTY_STRING;
    private String numberedProgramID = EMPTY_STRING;
    private String numberedServiceTypeID = EMPTY_STRING;
    private int recCounter = 0;

    public ListAaDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ListAaDAO" );
    }

    public Map<String, String> getCountries() {
        return getCountries( EMPTY_STRING );
    }

    public Map<String, String> getCountries( String countryKey ) {
        logger.debugMethod( "getCountries" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> countries = new HashMap<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_COUNTRIES_SQL );

            resultSet = preparedStatement.executeQuery();

            recCounter = 0;
            while( resultSet.next() ) {
                String countryCode = resultSet.getString( "CountryCode" );
                String name_EN = resultSet.getString( "Name_EN" );

                if( countryKey != null && ! countryKey.trim().equals(EMPTY_STRING) ) {
                    if( countryCode.equals( countryKey ) ) {
                        setNumberedCountryCode( intToString( recCounter ) + countryCode );
                    }
                }

                countries.put( intToString( recCounter ) + countryCode, name_EN );
                recCounter++;
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

        return countries;
    }

    public Map<String, String> getProvStates( String countryCode ) {
        return getProvStates( countryCode, EMPTY_STRING );
    }

    public Map<String, String> getProvStates( String countryCode, String provStateKey ) {
        logger.debugMethod( "getProvStates" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> provStates = new HashMap<>();

        try {
            connection = getConnection();

            if( countryCode == null || countryCode.trim().equals( EMPTY_STRING ) ) {
                preparedStatement = connection.prepareStatement( GET_ALL_PROVSTATES_SQL );
            }
            else {
                preparedStatement = connection.prepareStatement( GET_PROVSTATES_SQL );
                preparedStatement.setString( 1, countryCode );
            }

            resultSet = preparedStatement.executeQuery();

            recCounter = 0;
            while( resultSet.next() ) {
                String provStateCode = resultSet.getString( "ProvStateCode" );
                String name_EN = resultSet.getString( "Name_EN" );

                if( provStateKey != null && ! provStateKey.trim().equals(EMPTY_STRING) ) {
                    if( provStateCode.equals( provStateKey ) ) {
                        setNumberedProvStateCode( intToString( recCounter ) + provStateCode );
                    }
                }

                provStates.put( intToString( recCounter ) + provStateCode, name_EN );
                recCounter++;
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

        return provStates;
    }

    public String getCountryName( String countryCode ) {
        logger.debugMethod( "getCountryName" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String countryName = EMPTY_STRING;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_COUNTRY_SQL );
            preparedStatement.setString( 1, countryCode );

            resultSet = preparedStatement.executeQuery();

            recCounter = 0;
            while( resultSet.next() ) {
                countryName = resultSet.getString( "Name_EN" );
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

        return countryName;
    }

    public Map<String, String> getUniversities() {
        return getUniversities( EMPTY_STRING );
    }

    public Map<String, String> getUniversities( String universityKey ) {
        logger.debugMethod( "getUniversities" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> universities = new HashMap<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_UNIVERSITIES_SQL );

            resultSet = preparedStatement.executeQuery();

            recCounter = 0;
            while( resultSet.next() ) {
                String universityID = resultSet.getString( "UniversityID" );
                String name_EN = resultSet.getString( "Name_EN" );

                if( universityKey != null && ! universityKey.trim().equals(EMPTY_STRING) ) {
                    if( universityID.equals( universityKey ) ) {
                        setNumberedUniversityID( intToString( recCounter ) + universityID );
                    }
                }

                universities.put( intToString( recCounter ) + universityID, name_EN );
                recCounter++;
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

        return universities;
    }

    public Map<String, String> getPrograms() {
        return getPrograms( EMPTY_STRING );
    }

    public Map<String, String> getPrograms( String programKey ) {
        logger.debugMethod( "getPrograms" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> programs = new HashMap<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_PROGRAMS_SQL );

            resultSet = preparedStatement.executeQuery();

            recCounter = 0;
            while( resultSet.next() ) {
                String programID = resultSet.getString( "ProgramID" );
                String description_EN = resultSet.getString( "Description_EN" );

                if( programKey != null && ! programKey.trim().equals(EMPTY_STRING) ) {
                    if( programID.equals( programKey ) ) {
                        setNumberedProgramID( intToString( recCounter ) + programID );
                    }
                }

                programs.put( intToString( recCounter ) + programID, description_EN );
                recCounter++;
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

        return programs;
    }

    public Map<String, String> getCustomPrograms( String universityID ) {
        return getCustomPrograms( EMPTY_STRING, universityID );
    }

    public Map<String, String> getCustomPrograms( String programKey, String universityID ) {
        logger.debugMethod( "getCustomPrograms" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> programs = new HashMap<>();

        try {
            connection = getConnection();

            if( universityID == null || universityID.trim().equals(EMPTY_STRING) ) {
                preparedStatement = connection.prepareStatement( GET_PROGRAMS_SQL );
            }
            else {
                preparedStatement = connection.prepareStatement( GET_CUSTOM_PROGRAMS_SQL );
                preparedStatement.setString( 1, universityID );
            }

            resultSet = preparedStatement.executeQuery();

            recCounter = 0;
            while( resultSet.next() ) {
                String programID = resultSet.getString( "ProgramID" );
                String description_EN = resultSet.getString( "Description_EN" );

                if( programKey != null  && ! programKey.trim().equals(EMPTY_STRING) ) {
                    if( programID.equals( programKey ) ) {
                        setNumberedProgramID( intToString( recCounter ) + programID );
                    }
                }

                programs.put( intToString( recCounter ) + programID, description_EN );
                recCounter++;
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

        return programs;
    }

    public Map<String, String> getServiceTypes() {
        return getServiceTypes( EMPTY_STRING );
    }

    public Map<String, String> getServiceTypes( String serviceTypeKey ) {
        logger.debugMethod( "getServiceTypes" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> serviceTypes = new HashMap<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_SERVICETYPES_SQL );

            resultSet = preparedStatement.executeQuery();

            recCounter = 0;
            while( resultSet.next() ) {
                String serviceTypeID = resultSet.getString( "ServiceTypeID" );
                String name_EN = resultSet.getString( "Name_EN" );

                if( serviceTypeKey != null && ! serviceTypeKey.trim().equals(EMPTY_STRING) ) {
                    if( serviceTypeID.equals( serviceTypeKey ) ) {
                        setNumberedServiceTypeID( intToString( recCounter ) + serviceTypeID );
                    }
                }

                serviceTypes.put( intToString( recCounter ) + serviceTypeID, name_EN );
                recCounter++;
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

        return serviceTypes;
    }

    public String getNumberedCountryCode() {
        return numberedCountryCode;
    }

    public void setNumberedCountryCode( String numberedCountryCode ) {
        this.numberedCountryCode = numberedCountryCode;
    }

    public String getNumberedProvStateCode() {
        return numberedProvStateCode;
    }

    public void setNumberedProvStateCode( String numberedProvStateCode ) {
        this.numberedProvStateCode = numberedProvStateCode;
    }

    public String getNumberedUniversityID() {
        return numberedUniversityID;
    }

    public void setNumberedUniversityID( String numberedUniversityID ) {
        this.numberedUniversityID = numberedUniversityID;
    }

    public String getNumberedProgramID() {
        return numberedProgramID;
    }

    public void setNumberedProgramID( String numberedProgramID ) {
        this.numberedProgramID = numberedProgramID;
    }

    public String getNumberedServiceTypeID() {
        return numberedServiceTypeID;
    }

    public void setNumberedServiceTypeID( String numberedServiceTypeID ) {
        this.numberedServiceTypeID = numberedServiceTypeID;
    }
}
