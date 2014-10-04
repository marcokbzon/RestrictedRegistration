package com.moh.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.moh.common._ParentDAO;
import com.moh.utils.Logger;

public class DatabaseCountDAO extends _ParentDAO {

    private String GET_COUNTRY_COUNT_SQL =
            "SELECT COUNT(*) AS RecordCount "
            + "FROM Country";
    private String GET_PROVINCESTATE_COUNT_SQL =
            "SELECT COUNT(*) AS RecordCount "
            + "FROM ProvinceState";
    private String GET_UNIVERSITY_COUNT_SQL =
            "SELECT COUNT(*) AS RecordCount "
            + "FROM University";
    private String GET_PROGRAM_COUNT_SQL =
            "SELECT COUNT(*) AS RecordCount "
            + "FROM Program";
    private String GET_ROLE_COUNT_SQL =
            "SELECT COUNT(*) AS RecordCount "
            + "FROM Role";
    private String GET_CODE_COUNT_SQL =
            "SELECT COUNT(*) AS RecordCount "
            + "FROM Code";
    private String GET_INSTITUTION_COUNT_SQL =
            "SELECT COUNT(*) AS RecordCount "
            + "FROM Institution";
    private String GET_SERVICETYPE_COUNT_SQL =
            "SELECT COUNT(*) AS RecordCount "
            + "FROM ServiceType";
    private String GET_LHIN_COUNT_SQL =
            "SELECT COUNT(*) AS RecordCount "
            + "FROM LHIN";
    private String GET_CLASSIFICATION_COUNT_SQL =
            "SELECT COUNT(*) AS RecordCount "
            + "FROM Classification";
    private String GET_AGREEMENT_COUNT_SQL =
            "SELECT COUNT(*) AS RecordCount "
            + "FROM Agreement";
    private String GET_INSTLHIN_COUNT_SQL =
            "SELECT COUNT(*) AS RecordCount "
            + "FROM InstLHIN";
    private String GET_INSTCLASSIFICATION_COUNT_SQL =
            "SELECT COUNT(*) AS RecordCount "
            + "FROM InstClassification";
    private String GET_INSTSERVICETYPE_COUNT_SQL =
            "SELECT COUNT(*) AS RecordCount "
            + "FROM InstServType";
    private String GET_USERINFO_COUNT_SQL =
            "SELECT COUNT(*) AS RecordCount "
            + "FROM UserInfo";
    private String GET_USERROLE_COUNT_SQL =
            "SELECT COUNT(*) AS RecordCount "
            + "FROM UserRole";

    public DatabaseCountDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ReviewInfoDAO" );
    }

    public String getNumberOfRecords( String tableName ) {
        logger.debugMethod( "getNumberOfRecords" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String recCount = "0";

        try {
            connection = getConnection();

            if( tableName.equals( TABLE_COUNTRY ) ) {
                preparedStatement = connection.prepareStatement( GET_COUNTRY_COUNT_SQL );
            }
            else {
                if( tableName.equals( TABLE_PROVINCESTATE ) ) {
                    preparedStatement = connection.prepareStatement( GET_PROVINCESTATE_COUNT_SQL );
                }
                else {
                    if( tableName.equals( TABLE_UNIVERSITY ) ) {
                        preparedStatement = connection.prepareStatement( GET_UNIVERSITY_COUNT_SQL );
                    }
                    else {
                        if( tableName.equals( TABLE_PROGRAM ) ) {
                            preparedStatement = connection.prepareStatement( GET_PROGRAM_COUNT_SQL );
                        }
                        else {
                            if( tableName.equals( TABLE_ROLE ) ) {
                                preparedStatement = connection.prepareStatement( GET_ROLE_COUNT_SQL );
                            }
                            else {
                                if( tableName.equals( TABLE_CODE ) ) {
                                    preparedStatement = connection.prepareStatement( GET_CODE_COUNT_SQL );
                                }
                                else {
                                    if( tableName.equals( TABLE_INSTITUTION ) ) {
                                        preparedStatement = connection.prepareStatement( GET_INSTITUTION_COUNT_SQL );
                                    }
                                    else {
                                        if( tableName.equals( TABLE_SERVICETYPE ) ) {
                                            preparedStatement = connection.prepareStatement( GET_SERVICETYPE_COUNT_SQL );
                                        }
                                        else {
                                            if( tableName.equals( TABLE_LHIN ) ) {
                                                preparedStatement = connection.prepareStatement( GET_LHIN_COUNT_SQL );
                                            }
                                            else {
                                                if( tableName.equals( TABLE_CLASSIFICATION ) ) {
                                                    preparedStatement = connection.prepareStatement( GET_CLASSIFICATION_COUNT_SQL );
                                                }
                                                else {
                                                    if( tableName.equals( TABLE_AGREEMENT ) ) {
                                                        preparedStatement = connection.prepareStatement( GET_AGREEMENT_COUNT_SQL );
                                                    }
                                                    else {
                                                        if( tableName.equals( TABLE_INSTLHIN ) ) {
                                                            preparedStatement = connection.prepareStatement( GET_INSTLHIN_COUNT_SQL );
                                                        }
                                                        else {
                                                            if( tableName.equals( TABLE_INSTCLASSIFICATION ) ) {
                                                                preparedStatement = connection.prepareStatement( GET_INSTCLASSIFICATION_COUNT_SQL );
                                                            }
                                                            else {
                                                                if( tableName.equals( TABLE_INSTSERVICETYPE ) ) {
                                                                    preparedStatement = connection.prepareStatement( GET_INSTSERVICETYPE_COUNT_SQL );
                                                                }
                                                                else {
                                                                    if( tableName.equals( TABLE_USERINFO ) ) {
                                                                        preparedStatement = connection.prepareStatement( GET_USERINFO_COUNT_SQL );
                                                                    }
                                                                    else {
                                                                        if( tableName.equals( TABLE_USERROLE ) ) {
                                                                            preparedStatement = connection.prepareStatement( GET_USERROLE_COUNT_SQL );
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                recCount = resultSet.getString( "RecordCount" );
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

        return recCount;
    }
}
