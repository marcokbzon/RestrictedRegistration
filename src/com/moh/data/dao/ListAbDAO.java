package com.moh.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.moh.common._ParentDAO;
import com.moh.utils.Logger;

public class ListAbDAO extends _ParentDAO {

    private String GET_LHINS_SQL =
            "SELECT LHIN_ID, Name_EN "
            + "FROM LHIN "
            + "ORDER BY Name_EN";
    private String GET_INSTITUTIONS_SQL =
            "SELECT ins.InstitutionID AS InstitutionID, ins.Name_EN AS Name_EN "
            + "FROM Institution ins, InstLHIN inslhi "
            + "WHERE inslhi.InstitutionID = ins.InstitutionID "
            + "AND inslhi.LHIN_ID = ? "
            + "ORDER BY Name_EN";
    private String GET_ALL_INSTITUTIONS_SQL =
            "SELECT ins.InstitutionID AS InstitutionID, ins.Name_EN AS Name_EN "
            + "FROM Institution ins, InstLHIN inslhi "
            + "WHERE inslhi.InstitutionID = ins.InstitutionID "
            + "ORDER BY Name_EN";
    private String GET_CLASSIFICATIONS_SQL =
            "SELECT cls.ClassificationID AS ClassificationID, cls.Description_EN AS Description_EN "
            + "FROM Classification cls, InstClassification inscls "
            + "WHERE inscls.ClassificationID = cls.ClassificationID "
            + "AND inscls.InstitutionID = ? "
            + "ORDER BY Description_EN";
    private String GET_LOCATIONS_SQL =
            "SELECT loc.LocationID AS LocationID, loc.Description_EN AS Description_EN "
            + "FROM Location loc, InstLocation insloc "
            + "WHERE insloc.LocationID = loc.LocationID "
            + "AND insloc.InstitutionID = ? "
            + "ORDER BY Description_EN";
    private String GET_SERVICETYPES_SQL =
            "SELECT srv.ServiceTypeID AS ServiceTypeID, srv.Name_EN AS Name_EN "
            + "FROM ServiceType srv, InstServType inssrv "
            + "WHERE inssrv.ServiceTypeID = srv.ServiceTypeID "
            + "AND inssrv.InstitutionID = ? "
            + "ORDER BY Name_EN";
    private String numberedLHINID = EMPTY_STRING;
    private String numberedInstitutionID = EMPTY_STRING;
    private String numberedClassificationID = EMPTY_STRING;
    private String numberedLocationID = EMPTY_STRING;
    private String numberedServiceTypeID = EMPTY_STRING;
    private int recCounter = 0;

    public ListAbDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ListAbDAO" );
    }

    public Map<String, String> getLHINs() {
        return getLHINs( EMPTY_STRING );
    }

    public Map<String, String> getLHINs( String lhinKey ) {
        logger.debugMethod( "getLHINs" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> lhins = new HashMap<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_LHINS_SQL );

            resultSet = preparedStatement.executeQuery();

            recCounter = 0;
            while( resultSet.next() ) {
                String lhin_ID = resultSet.getString( "LHIN_ID" );
                String name_EN = resultSet.getString( "Name_EN" );

                if( lhinKey != null && ! lhinKey.trim().equals(EMPTY_STRING) ) {
                    if( lhin_ID.equals( lhinKey ) ) {
                        setNumberedLHINID( intToString( recCounter ) + lhin_ID );
                    }
                }

                lhins.put( intToString( recCounter ) + lhin_ID, name_EN );
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

        return lhins;
    }

    public Map<String, String> getInstitutions( String lhinKey ) {
        return getInstitutions( EMPTY_STRING, lhinKey );
    }

    public Map<String, String> getInstitutions( String institutionKey, String lhinKey ) {
        logger.debugMethod( "getInstitutions" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> institutions = new HashMap<>();

        try {
            connection = getConnection();

            if( lhinKey.equals( "00" ) ) {
                preparedStatement = connection.prepareStatement( GET_ALL_INSTITUTIONS_SQL );
            }
            else {
                preparedStatement = connection.prepareStatement( GET_INSTITUTIONS_SQL );
                preparedStatement.setString( 1, lhinKey );
            }

            resultSet = preparedStatement.executeQuery();

            recCounter = 0;
            while( resultSet.next() ) {
                String institutionID = resultSet.getString( "InstitutionID" );
                String name_EN = resultSet.getString( "Name_EN" );

                if( institutionKey != null && ! institutionKey.trim().equals(EMPTY_STRING) ) {
                    if( institutionID.equals( institutionKey ) ) {
                        setNumberedInstitutionID( intToString( recCounter ) + institutionID );
                    }
                }

                institutions.put( intToString( recCounter ) + institutionID, name_EN );
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

        return institutions;
    }

    public Map<String, String> getClassifications( String institutionKey ) {
        return getClassifications( EMPTY_STRING, institutionKey );
    }

    public Map<String, String> getClassifications( String classificationKey, String institutionKey ) {
        logger.debugMethod( "getClassifications" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> classifications = new HashMap<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_CLASSIFICATIONS_SQL );
            preparedStatement.setString( 1, institutionKey );

            resultSet = preparedStatement.executeQuery();

            recCounter = 0;
            while( resultSet.next() ) {
                String classificationID = resultSet.getString( "ClassificationID" );
                String description_EN = resultSet.getString( "Description_EN" );

                if( classificationKey != null && ! classificationKey.trim().equals(EMPTY_STRING) ) {
                    if( classificationID.equals( classificationKey ) ) {
                        setNumberedClassificationID( intToString( recCounter ) + classificationID );
                    }
                }

                classifications.put( intToString( recCounter ) + classificationID, description_EN );
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

        return classifications;
    }

    public Map<String, String> getLocations( String institutionKey ) {
        return getLocations( EMPTY_STRING, institutionKey );
    }

    public Map<String, String> getLocations( String locationKey, String institutionKey ) {
        logger.debugMethod( "getLocations" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> locations = new HashMap<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_LOCATIONS_SQL );
            preparedStatement.setString( 1, institutionKey );

            resultSet = preparedStatement.executeQuery();

            recCounter = 0;
            while( resultSet.next() ) {
                String locationID = resultSet.getString( "LocationID" );
                String description_EN = resultSet.getString( "Description_EN" );

                if( locationKey != null && ! locationKey.trim().equals(EMPTY_STRING) ) {
                    if( locationID.equals( locationKey ) ) {
                        setNumberedLocationID( intToString( recCounter ) + locationID );
                    }
                }

                locations.put( intToString( recCounter ) + locationID, description_EN );
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

        return locations;
    }

    public Map<String, String> getServiceTypes( String institutionKey ) {
        return getServiceTypes( EMPTY_STRING, institutionKey );
    }

    public Map<String, String> getServiceTypes( String serviceTypeKey, String institutionKey ) {
        logger.debugMethod( "getServiceTypes" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> serviceTypes = new HashMap<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_SERVICETYPES_SQL );
            preparedStatement.setString( 1, institutionKey );

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

    public String getNumberedInstitutionID() {
        return numberedInstitutionID;
    }

    public void setNumberedInstitutionID( String numberedInstitutionID ) {
        this.numberedInstitutionID = numberedInstitutionID;
    }

    public String getNumberedClassificationID() {
        return numberedClassificationID;
    }

    public void setNumberedClassificationID( String numberedClassificationID ) {
        this.numberedClassificationID = numberedClassificationID;
    }

    public String getNumberedLocationID() {
        return numberedLocationID;
    }

    public void setNumberedLocationID( String numberedLocationID ) {
        this.numberedLocationID = numberedLocationID;
    }

    public String getNumberedServiceTypeID() {
        return numberedServiceTypeID;
    }

    public void setNumberedServiceTypeID( String numberedServiceTypeID ) {
        this.numberedServiceTypeID = numberedServiceTypeID;
    }

    public String getNumberedLHINID() {
        return numberedLHINID;
    }

    public void setNumberedLHINID( String numberedLHINID ) {
        this.numberedLHINID = numberedLHINID;
    }
}
