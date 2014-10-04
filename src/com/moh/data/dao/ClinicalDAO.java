package com.moh.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import com.moh.common._ParentDAO;
import com.moh.data.bean.RotationData;
import com.moh.utils.Logger;

public class ClinicalDAO extends _ParentDAO {

    private String GET_ROTATIONS_SQL =
            "SELECT ServiceType, Position, Rotations, WeeksTotal "
            + "FROM RotationHistory "
            + "WHERE Email = ? "
            + "ORDER BY Position";
    private String GET_ROTATION_SQL =
            "SELECT ServiceType, Rotations, WeeksTotal "
            + "FROM RotationHistory "
            + "WHERE Email = ? "
            + "AND Position = ?";
    private String CHECK_ROTATION_SQL =
            "SELECT COUNT(*) AS RotationCount "
            + "FROM RotationHistory "
            + "WHERE Email = ? "
            + "AND ServiceTypeID = ?";
    private String ADD_ROTATION_SQL =
            "INSERT INTO RotationHistory "
            + "( Email, ServiceType, Position, Rotations, WeeksTotal, UpdatedOn ) "
            + "VALUES ( ?, ?, ?, ?, ?, ? )";
    private String GET_MAX_ROTATION_SQL =
            "SELECT MAX( Position ) AS MaxRotation "
            + "FROM RotationHistory "
            + "WHERE Email = ?";
    private String UPDATE_ROTATION_SQL =
            "UPDATE RotationHistory "
            + "SET ServiceType = ?, Rotations = ?, WeeksTotal = ?, UpdatedOn = ? "
            + "WHERE Email = ? "
            + "AND Position = ?";
    private String DELETE_ROTATION_SQL =
            "DELETE FROM RotationHistory "
            + "WHERE Email = ? "
            + "AND Position = ?";

    private String email;
    
    public ClinicalDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ClinicalDAO" );
    }

    public List<RotationData> getRotations( String email ) {
        logger.debugMethod( "getRotations" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<RotationData> rotationList = new ArrayList<>();
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_ROTATIONS_SQL );
            preparedStatement.setString( 1, getEmail() );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String serviceType = resultSet.getString( "ServiceType" );
                String rotations = EMPTY_STRING + resultSet.getShort( "Rotations" );
                String weeksTotal = EMPTY_STRING + resultSet.getShort( "WeeksTotal" );
                String position = EMPTY_STRING + resultSet.getShort( "Position" );

                rotationList.add( new RotationData( serviceType, rotations, weeksTotal, position ) );
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

        return rotationList;
    }

    public boolean addRotation( String email, String serviceType, int position, int rotations, int weeksTotal ) {
        logger.debugMethod( "addRotation" );
        boolean returnedValue = false;
        setEmail( email );

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int insertCountRT = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            Locale locale = Locale.CANADA;
            TimeZone timezone = TimeZone.getTimeZone( "EST" );
            Calendar cal = Calendar.getInstance( timezone, locale );
            Timestamp tsNow = new Timestamp( cal.getTimeInMillis() );

            preparedStatement = connection.prepareStatement( ADD_ROTATION_SQL );

            preparedStatement.setString( 1, getEmail() );
            preparedStatement.setString( 2, serviceType );
            preparedStatement.setInt( 3, position );
            preparedStatement.setInt( 4, rotations );
            preparedStatement.setInt( 5, weeksTotal );
            preparedStatement.setTimestamp( 6, tsNow );

            insertCountRT = preparedStatement.executeUpdate();

            if( insertCountRT != 1 ) {
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

    public boolean editRotation( String email, String serviceType, int position, int rotations, int weeksTotal ) {
        logger.debugMethod( "editRotation" );
        boolean returnedValue = false;
        setEmail( email );

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int updateCountRT = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            Locale locale = Locale.CANADA;
            TimeZone timezone = TimeZone.getTimeZone( "EST" );
            Calendar cal = Calendar.getInstance( timezone, locale );
            Timestamp tsNow = new Timestamp( cal.getTimeInMillis() );

            preparedStatement = connection.prepareStatement( UPDATE_ROTATION_SQL );

            preparedStatement.setString( 1, serviceType );
            preparedStatement.setInt( 2, rotations );
            preparedStatement.setInt( 3, weeksTotal );
            preparedStatement.setTimestamp( 4, tsNow );
            preparedStatement.setString( 5, getEmail() );
            preparedStatement.setInt( 6, position );

            updateCountRT = preparedStatement.executeUpdate();

            if( updateCountRT != 1 ) {
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

    public boolean deleteRotation( String email, int position ) {
        logger.debugMethod( "deleteRotation" );
        boolean returnedValue = false;
        setEmail( email );

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int deleteCountRT = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( DELETE_ROTATION_SQL );

            preparedStatement.setString( 1, getEmail() );
            preparedStatement.setInt( 2, position );

            deleteCountRT = preparedStatement.executeUpdate();

            if( deleteCountRT != 1 ) {
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

    public boolean rotationExists( String email, String serviceTypeID ) {
        logger.debugMethod( "rotationExists" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        int rotationCount = 0;
        setEmail( email );

        try {
            connection = getConnection();

            if( serviceTypeID.length() == ( COL_LENGTH_SERVICETYPEID + COL_SORT_LENGTH ) ) {
                serviceTypeID = serviceTypeID.substring( COL_SORT_LENGTH );
            }

            preparedStatement = connection.prepareStatement( CHECK_ROTATION_SQL );
            preparedStatement.setString( 1, getEmail() );
            preparedStatement.setString( 2, serviceTypeID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                rotationCount = resultSet.getInt( "RotationCount" );
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

        if( rotationCount == 0 ) {
            return false;
        }
        else {
            return true;
        }
    }

    public Map<String, String> getRotation( String email, int position ) {
        logger.debugMethod( "getRotation" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> rotation = new HashMap<>();
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_ROTATION_SQL );
            preparedStatement.setString( 1, getEmail() );
            preparedStatement.setInt( 2, position );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String serviceType = resultSet.getString( "ServiceType" );
                int rotations = resultSet.getInt( "Rotations" );
                int weeksTotal = resultSet.getInt( "WeeksTotal" );

                rotation.put( "ServiceType", serviceType );
                rotation.put( "Rotations", EMPTY_STRING + rotations );
                rotation.put( "WeeksTotal", EMPTY_STRING + weeksTotal );
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

        return rotation;
    }

    public int genNewRotationPosition( String email ) {
        logger.debugMethod( "genNewRotationPosition" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        int newPosition = 0;
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_MAX_ROTATION_SQL );
            preparedStatement.setString( 1, getEmail() );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                newPosition = resultSet.getInt( "MaxRotation" );
            }

            newPosition++;
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

        return newPosition;
    }
    
    private String getEmail() {
        if ( email == null || email.trim().equals( EMPTY_STRING ) ) {
            return EMPTY_STRING;
        }
        else {
            return email.trim().toLowerCase();
        }
    }
    
    private void setEmail( String email ) {
        if ( email == null || email.trim().equals( EMPTY_STRING ) ) {
            this.email = EMPTY_STRING;
        }
        else {
            this.email = email.trim().toLowerCase();
        }
    }
}
