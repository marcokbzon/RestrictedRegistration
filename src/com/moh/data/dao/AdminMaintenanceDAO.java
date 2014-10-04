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

public class AdminMaintenanceDAO extends _ParentDAO {

    public String BY_NAME = "BY_NAME";
    public String BY_DESCRIPTION = "BY_DESCRIPTION";
    public String BY_ABBREVIATION = "BY_ABBREVIATION";
    public String FROM_UNIVERSITY = "FROM_UNIVERSITY";
    public String FROM_PROGRAM = "FROM_PROGRAM";
    public String FROM_SERVICETYPE = "FROM_SERVICETYPE";
    public String FROM_INSTITUTION = "FROM_INSTITUTION";
    public String FROM_INSTLHIN = "FROM_INSTLHIN";

    /*
     * UNIVERSITY SQL STATEMENTS
     */
    private String GET_UNIVERSITY_SQL =
            "SELECT Name_EN, Abbreviation, Enabled "
            + "FROM University "
            + "WHERE UniversityID = ?";
    private String CHECK_UNIVERSITY_NAME_SQL =
            "SELECT COUNT(*) AS DataCount "
            + "FROM University "
            + "WHERE Name_EN = ?";
    private String CHECK_UNIVERSITY_ABBR_SQL =
            "SELECT COUNT(*) AS DataCount "
            + "FROM University "
            + "WHERE Abbreviation = ?";
    private String CREATE_UNIVERSITY_SQL =
            "INSERT INTO University "
            + "( UniversityID, Name_EN, Abbreviation, CountryCode, ProvStateCode, Enabled ) "
            + "VALUES ( ?, ?, ?, ?, ?, ? )";
    private String MAX_UNIVERSITY_ID_SQL =
            "SELECT MAX( UniversityID ) AS MaxID "
            + "FROM University";
    private String UPDATE_UNIVERSITY_SQL =
            "UPDATE University "
            + "SET Name_EN = ?, Abbreviation = ?, Enabled = ? "
            + "WHERE UniversityID = ?";
    private String DELETE_UNIVERSITY_SQL =
            "DELETE FROM University "
            + "WHERE UniversityID = ?";

    /*
     * PROGRAM SQL STATEMENTS
     */
    private String GET_PROGRAM_SQL =
            "SELECT Description_EN, Abbreviation, Enabled "
            + "FROM Program "
            + "WHERE ProgramID = ?";
    private String CHECK_PROGRAM_DESCRIPTION_SQL =
            "SELECT COUNT(*) AS DataCount "
            + "FROM Program "
            + "WHERE Description_EN = ?";
    private String CHECK_PROGRAM_ABBR_SQL =
            "SELECT COUNT(*) AS DataCount "
            + "FROM Program "
            + "WHERE Abbreviation = ?";
    private String CREATE_PROGRAM_SQL =
            "INSERT INTO Program "
            + "( ProgramID, Description_EN, Abbreviation, Enabled ) "
            + "VALUES ( ?, ?, ?, ? )";
    private String MAX_PROGRAM_ID_SQL =
            "SELECT MAX( ProgramID ) AS MaxID "
            + "FROM Program";
    private String UPDATE_PROGRAM_SQL =
            "UPDATE Program "
            + "SET Description_EN = ?, Abbreviation = ?, Enabled = ? "
            + "WHERE ProgramID = ?";
    private String DELETE_PROGRAM_SQL =
            "DELETE FROM Program "
            + "WHERE ProgramID = ?";
    /*
     * SERVICE TYPE SQL STATEMENTS
     */
    private String GET_SERVICETYPE_SQL =
            "SELECT Name_EN, Abbreviation "
            + "FROM ServiceType "
            + "WHERE ServiceTypeID = ?";
    private String CHECK_SERVICETYPE_NAME_SQL =
            "SELECT COUNT(*) AS DataCount "
            + "FROM ServiceType "
            + "WHERE Name_EN = ?";
    private String CHECK_SERVICETYPE_ABBR_SQL =
            "SELECT COUNT(*) AS DataCount "
            + "FROM ServiceType "
            + "WHERE Abbreviation = ?";
    private String CREATE_SERVICETYPE_SQL =
            "INSERT INTO ServiceType "
            + "( ServiceTypeID, Name_EN, Abbreviation ) "
            + "VALUES ( ?, ?, ? )";
    private String MAX_SERVICETYPE_ID_SQL =
            "SELECT MAX( ServiceTypeID ) AS MaxID "
            + "FROM ServiceType";
    private String UPDATE_SERVICETYPE_SQL =
            "UPDATE ServiceType "
            + "SET Name_EN = ?, Abbreviation = ? "
            + "WHERE ServiceTypeID = ?";
    private String DELETE_SERVICETYPE_SQL =
            "DELETE FROM ServiceType "
            + "WHERE ServiceTypeID = ?";
    /*
     * INSTITUTION SQL STATEMENTS
     */
    private String GET_INSTITUTION_SQL =
            "SELECT ins.Name_EN AS InstitutionName, ins.Abbreviation, ins.UAPdesignated, ins.Enabled, "
            + "lhn.LHIN_ID, lhn.Name_EN AS LHIN_Name "
            + "FROM Institution ins, LHIN lhn, InstLHIN inslhn "
            + "WHERE ins.InstitutionID = ? "
            + "AND ins.InstitutionID = inslhn.InstitutionID "
            + "AND lhn.LHIN_ID = inslhn.LHIN_ID";
    private String CHECK_INSTITUTION_NAME_SQL =
            "SELECT COUNT(*) AS DataCount "
            + "FROM Institution "
            + "WHERE Name_EN = ?";
    private String CHECK_INSTITUTION_ABBR_SQL =
            "SELECT COUNT(*) AS DataCount "
            + "FROM Institution "
            + "WHERE Abbreviation = ?";
    private String CREATE_INSTITUTION_SQL =
            "INSERT INTO Institution "
            + "( InstitutionID, Name_EN, Abbreviation, UAPdesignated, Enabled ) "
            + "VALUES ( ?, ?, ?, ?, ? )";
    private String CREATE_INSTLHIN_SQL =
            "INSERT INTO InstLHIN "
            + "( InstitutionID, LHIN_ID ) "
            + "VALUES ( ?, ? )";
    private String MAX_INSTITUTION_ID_SQL =
            "SELECT MAX( InstitutionID ) AS MaxID "
            + "FROM Institution";
    private String UPDATE_INSTITUTION_SQL =
            "UPDATE Institution "
            + "SET Name_EN = ?, Abbreviation = ?, UAPdesignated = ?, Enabled = ? "
            + "WHERE InstitutionID = ?";
    private String UPDATE_INSTLHIN_SQL =
            "UPDATE InstLHIN "
            + "SET LHIN_ID = ? "
            + "WHERE InstitutionID = ?";
    private String DELETE_INSTLHIN_SQL =
            "DELETE FROM InstLHIN "
            + "WHERE InstitutionID = ?";
    private String DELETE_INSTITUTION_SQL =
            "DELETE FROM Institution "
            + "WHERE InstitutionID = ?";

    public AdminMaintenanceDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "AdminMaintenanceDAO" );
    }

    public Map<String, Object> getDataInfo( String tableName, String dataKey ) {
        logger.debugMethod( "getDataInfo" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, Object> data = new HashMap<>();

        try {
            connection = getConnection();

            if( tableName.equals( FROM_UNIVERSITY ) ) {
                preparedStatement = connection.prepareStatement( GET_UNIVERSITY_SQL );
            }
            else {
                if( tableName.equals( FROM_PROGRAM ) ) {
                    preparedStatement = connection.prepareStatement( GET_PROGRAM_SQL );
                }
                else {
                    if( tableName.equals( FROM_SERVICETYPE ) ) {
                        preparedStatement = connection.prepareStatement( GET_SERVICETYPE_SQL );
                    }
                    else {
                        if( tableName.equals( FROM_INSTITUTION ) ) {
                            preparedStatement = connection.prepareStatement( GET_INSTITUTION_SQL );
                        }
                    }
                }
            }
            preparedStatement.setString( 1, dataKey );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                if( tableName.equals( FROM_UNIVERSITY ) ) {
                    String name_EN = resultSet.getString( "Name_EN" );
                    String abbreviation = resultSet.getString( "Abbreviation" );

                    boolean enabled = resultSet.getBoolean( "Enabled" );

                    data.put( "Name_EN", name_EN );
                    data.put( "Abbreviation", abbreviation );

                    String sEnabled = "0";

                    if( enabled ) {
                        sEnabled = "1";
                    }

                    data.put( "Enabled", sEnabled );
                }
                else {
                    if( tableName.equals( FROM_PROGRAM ) ) {
                        String description_EN = resultSet.getString( "Description_EN" );
                        String abbreviation = resultSet.getString( "Abbreviation" );

                        boolean enabled = resultSet.getBoolean( "Enabled" );

                        data.put( "Description_EN", description_EN );
                        data.put( "Abbreviation", abbreviation );

                        String sEnabled = "0";

                        if( enabled ) {
                            sEnabled = "1";
                        }

                        data.put( "Enabled", sEnabled );
                    }
                    else {
                        if( tableName.equals( FROM_SERVICETYPE ) ) {
                            String name_EN = resultSet.getString( "Name_EN" );
                            String abbreviation = resultSet.getString( "Abbreviation" );

                            data.put( "Name_EN", name_EN );
                            data.put( "Abbreviation", abbreviation );
                        }
                        else {
                            if( tableName.equals( FROM_INSTITUTION ) ) {
                                String institutionName = resultSet.getString( "InstitutionName" );
                                String abbreviation = resultSet.getString( "Abbreviation" );

                                boolean uAPdesignated = resultSet.getBoolean( "UAPdesignated" );
                                boolean enabled = resultSet.getBoolean( "Enabled" );

                                String lhinID = resultSet.getString( "LHIN_ID" );
                                String lhinName = resultSet.getString( "LHIN_Name" );

                                data.put( "InstitutionName", institutionName );
                                data.put( "Abbreviation", abbreviation );

                                String sUAPdesignated = "0";

                                if( uAPdesignated ) {
                                    sUAPdesignated = "1";
                                }

                                String sEnabled = "0";

                                if( enabled ) {
                                    sEnabled = "1";
                                }

                                data.put( "UAPdesignated", sUAPdesignated );
                                data.put( "Enabled", sEnabled );

                                data.put( "LHIN_ID", lhinID );
                                data.put( "LHIN_Name", lhinName );
                            }
                        }
                    }
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

        return data;
    }

    public boolean addDataInfo( String tableName, String dataKey, String[] dataInfo ) {
        logger.debugMethod( "addDataInfo" );
        boolean returnedValue = false;

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int insertCount = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            if( tableName.equals( FROM_UNIVERSITY ) ) {
                preparedStatement = connection.prepareStatement( CREATE_UNIVERSITY_SQL );

                preparedStatement.setString( 1, dataKey );        // UniversityID
                preparedStatement.setString( 2, dataInfo[0] );    // Name_EN
                preparedStatement.setString( 3, dataInfo[1] );    // Abbreviation
                preparedStatement.setString( 4, DEFAULT_COUNTRY );     // CountryCode
                preparedStatement.setString( 5, DEFAULT_PROVINCE );    // ProvStateCode
                if( dataInfo[2].equals( "1" ) ) {                // Enabled
                    preparedStatement.setBoolean( 6, true );
                }
                else {
                    preparedStatement.setBoolean( 6, false );
                }
            }
            else {
                if( tableName.equals( FROM_PROGRAM ) ) {
                    preparedStatement = connection.prepareStatement( CREATE_PROGRAM_SQL );

                    preparedStatement.setString( 1, dataKey );        // ProgramID
                    preparedStatement.setString( 2, dataInfo[0] );    // Description_EN
                    preparedStatement.setString( 3, dataInfo[1] );    // Abbreviation
                    if( dataInfo[2].equals( "1" ) ) {                // Enabled
                        preparedStatement.setBoolean( 4, true );
                    }
                    else {
                        preparedStatement.setBoolean( 4, false );
                    }
                }
                else {
                    if( tableName.equals( FROM_SERVICETYPE ) ) {
                        preparedStatement = connection.prepareStatement( CREATE_SERVICETYPE_SQL );

                        preparedStatement.setString( 1, dataKey );        // ServiceTypeID
                        preparedStatement.setString( 2, dataInfo[0] );    // Name_EN
                        preparedStatement.setString( 3, dataInfo[1] );    // Abbreviation
                    }
                    else {
                        if( tableName.equals( FROM_INSTITUTION ) ) {
                            preparedStatement = connection.prepareStatement( CREATE_INSTITUTION_SQL );

                            preparedStatement.setString( 1, dataKey );        // InstitutionID
                            preparedStatement.setString( 2, dataInfo[0] );    // InstitutionName
                            preparedStatement.setString( 3, dataInfo[1] );    // Abbreviation
                            if( dataInfo[2].equals( "1" ) ) {                // UAPdesignated
                                preparedStatement.setBoolean( 4, true );
                            }
                            else {
                                preparedStatement.setBoolean( 4, false );
                            }
                            if( dataInfo[3].equals( "1" ) ) {                // Enabled
                                preparedStatement.setBoolean( 5, true );
                            }
                            else {
                                preparedStatement.setBoolean( 5, false );
                            }
                        }
                        else {
                            if( tableName.equals( FROM_INSTLHIN ) ) {
                                preparedStatement = connection.prepareStatement( CREATE_INSTLHIN_SQL );

                                preparedStatement.setString( 1, dataKey );        // InstitutionID
                                preparedStatement.setString( 2, dataInfo[0] );    // LHIN_ID
                            }
                        }
                    }
                }
            }

            preparedStatement.toString();

            insertCount = preparedStatement.executeUpdate();

            if( insertCount != 1 ) {
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

    public boolean editDataInfo( String tableName, String dataKey, String[] dataInfo ) {
        logger.debugMethod( "editDataInfo" );
        boolean returnedValue = false;

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int updateCount = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            if( tableName.equals( FROM_UNIVERSITY ) ) {
                preparedStatement = connection.prepareStatement( UPDATE_UNIVERSITY_SQL );

                preparedStatement.setString( 1, dataInfo[0] );   // Name_EN
                preparedStatement.setString( 2, dataInfo[1] );   // Abbreviation
                if( dataInfo[2].equals( "1" ) ) {               // Enabled
                    preparedStatement.setBoolean( 3, true );
                }
                else {
                    preparedStatement.setBoolean( 3, false );
                }
                preparedStatement.setString( 4, dataKey );       // UniversityID
            }
            else {
                if( tableName.equals( FROM_PROGRAM ) ) {
                    preparedStatement = connection.prepareStatement( UPDATE_PROGRAM_SQL );

                    preparedStatement.setString( 1, dataInfo[0] );   // Description_EN
                    preparedStatement.setString( 2, dataInfo[1] );   // Abbreviation
                    if( dataInfo[2].equals( "1" ) ) {               // Enabled
                        preparedStatement.setBoolean( 3, true );
                    }
                    else {
                        preparedStatement.setBoolean( 3, false );
                    }
                    preparedStatement.setString( 4, dataKey );       // ProgramID
                }
                else {
                    if( tableName.equals( FROM_SERVICETYPE ) ) {
                        preparedStatement = connection.prepareStatement( UPDATE_SERVICETYPE_SQL );

                        preparedStatement.setString( 1, dataInfo[0] );   // Name_EN
                        preparedStatement.setString( 2, dataInfo[1] );   // Abbreviation
                        preparedStatement.setString( 3, dataKey );       // ServiceTypeID
                    }
                    else {
                        if( tableName.equals( FROM_INSTITUTION ) ) {
                            preparedStatement = connection.prepareStatement( UPDATE_INSTITUTION_SQL );

                            preparedStatement.setString( 1, dataInfo[0] );   // InstitutionName
                            preparedStatement.setString( 2, dataInfo[1] );   // Abbreviation
                            if( dataInfo[2].equals( "1" ) ) {               // UAPdesignated
                                preparedStatement.setBoolean( 3, true );
                            }
                            else {
                                preparedStatement.setBoolean( 3, false );
                            }
                            if( dataInfo[3].equals( "1" ) ) {               // Enabled
                                preparedStatement.setBoolean( 4, true );
                            }
                            else {
                                preparedStatement.setBoolean( 4, false );
                            }

                            preparedStatement.setString( 5, dataKey );       // InstitutionID
                        }
                        else {
                            if( tableName.equals( FROM_INSTLHIN ) ) {
                                preparedStatement = connection.prepareStatement( UPDATE_INSTLHIN_SQL );

                                preparedStatement.setString( 1, dataInfo[0] );   // LHIN_ID
                                preparedStatement.setString( 2, dataKey );       // InstitutionID
                            }
                        }
                    }
                }
            }

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

    public boolean deleteDataInfo( String tableName, String dataKey ) {
        logger.debugMethod( "deleteDataInfo" );
        boolean returnedValue = false;

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int deleteCount = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            if( tableName.equals( FROM_UNIVERSITY ) ) {
                preparedStatement = connection.prepareStatement( DELETE_UNIVERSITY_SQL );
            }
            else {
                if( tableName.equals( FROM_PROGRAM ) ) {
                    preparedStatement = connection.prepareStatement( DELETE_PROGRAM_SQL );
                }
                else {
                    if( tableName.equals( FROM_SERVICETYPE ) ) {
                        preparedStatement = connection.prepareStatement( DELETE_SERVICETYPE_SQL );
                    }
                    else {
                        if( tableName.equals( FROM_INSTLHIN ) ) {
                            preparedStatement = connection.prepareStatement( DELETE_INSTLHIN_SQL );
                        }
                        else {
                            if( tableName.equals( FROM_INSTITUTION ) ) {
                                preparedStatement = connection.prepareStatement( DELETE_INSTITUTION_SQL );
                            }
                        }
                    }
                }
            }

            preparedStatement.setString( 1, dataKey );

            deleteCount = preparedStatement.executeUpdate();

            if( deleteCount != 1 ) {
                logger.error( "Delete failed" );
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

    public boolean dataInfoExists( String tableName, String criteria, String data ) {
        logger.debugMethod( "dataInfoExists" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        int dataCount = 0;

        try {
            connection = getConnection();

            if( tableName.equals( FROM_UNIVERSITY ) ) {
                if( criteria.equals( BY_NAME ) ) {
                    preparedStatement = connection.prepareStatement( CHECK_UNIVERSITY_NAME_SQL );
                }
                else {
                    if( criteria.equals( BY_ABBREVIATION ) ) {
                        preparedStatement = connection.prepareStatement( CHECK_UNIVERSITY_ABBR_SQL );
                    }
                    else {
                        return true;
                    }
                }
            }
            else {
                if( tableName.equals( FROM_PROGRAM ) ) {
                    if( criteria.equals( BY_DESCRIPTION ) ) {
                        preparedStatement = connection.prepareStatement( CHECK_PROGRAM_DESCRIPTION_SQL );
                    }
                    else {
                        if( criteria.equals( BY_ABBREVIATION ) ) {
                            preparedStatement = connection.prepareStatement( CHECK_PROGRAM_ABBR_SQL );
                        }
                        else {
                            return true;
                        }
                    }
                }
                else {
                    if( tableName.equals( FROM_SERVICETYPE ) ) {
                        if( criteria.equals( BY_NAME ) ) {
                            preparedStatement = connection.prepareStatement( CHECK_SERVICETYPE_NAME_SQL );
                        }
                        else {
                            if( criteria.equals( BY_ABBREVIATION ) ) {
                                preparedStatement = connection.prepareStatement( CHECK_SERVICETYPE_ABBR_SQL );
                            }
                            else {
                                return true;
                            }
                        }
                    }
                    else {
                        if( tableName.equals( FROM_INSTITUTION ) ) {
                            if( criteria.equals( BY_NAME ) ) {
                                preparedStatement = connection.prepareStatement( CHECK_INSTITUTION_NAME_SQL );
                            }
                            else {
                                if( criteria.equals( BY_ABBREVIATION ) ) {
                                    preparedStatement = connection.prepareStatement( CHECK_INSTITUTION_ABBR_SQL );
                                }
                                else {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }

            preparedStatement.setString( 1, data );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                dataCount = resultSet.getInt( "DataCount" );
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

        if( dataCount == 0 ) {
            return false;
        }
        else {
            return true;
        }
    }

    public String generateNewID( String tableName ) {
        logger.debugMethod( "generateNewID" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        int intResult = 0;
        String result = "0";

        try {
            connection = getConnection();

            if( tableName.equals( FROM_UNIVERSITY ) ) {
                preparedStatement = connection.prepareStatement( MAX_UNIVERSITY_ID_SQL );
            }
            else {
                if( tableName.equals( FROM_PROGRAM ) ) {
                    preparedStatement = connection.prepareStatement( MAX_PROGRAM_ID_SQL );
                }
                else {
                    if( tableName.equals( FROM_SERVICETYPE ) ) {
                        preparedStatement = connection.prepareStatement( MAX_SERVICETYPE_ID_SQL );
                    }
                    else {
                        if( tableName.equals( FROM_INSTITUTION ) ) {
                            preparedStatement = connection.prepareStatement( MAX_INSTITUTION_ID_SQL );
                        }
                    }
                }
            }

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String maxID = resultSet.getString( "MaxID" );

                if( maxID == null || maxID.trim().equals( EMPTY_STRING ) ) {
                    intResult = 0;
                }
                else {
                    intResult = Integer.parseInt( maxID );
                }

                result = EMPTY_STRING + ( intResult + 1 );

                int columnLength = 0;

                if( tableName.equals( FROM_UNIVERSITY ) ) {
                    columnLength = COL_LENGTH_UNIVERSITYID;
                }
                else {
                    if( tableName.equals( FROM_PROGRAM ) ) {
                        columnLength = COL_LENGTH_PROGRAMID;
                    }
                    else {
                        if( tableName.equals( FROM_SERVICETYPE ) ) {
                            columnLength = COL_LENGTH_SERVICETYPEID;
                        }
                        else {
                            if( tableName.equals( FROM_INSTITUTION ) ) {
                                columnLength = COL_LENGTH_INSTITUTIONID;
                            }
                        }
                    }
                }
                
                while ( result.length() < columnLength ) {
        			result = "0" + result;
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

        return result;
    }
}
