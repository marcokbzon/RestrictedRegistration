package com.moh.data.dao;

import com.moh.common._ParentDAO;
import com.moh.utils.Logger;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/*
 * 
 */
public class AuditDAO extends _ParentDAO {

    private String ADD_AUDIT_LOG_SQL =
            "INSERT INTO Audit "
            + "( Email, SqlStatement, ExecutedOn ) "
            + "VALUES ( ?, ?, ? )";

    private String email;
    
    public AuditDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "AuditDAO" );
    }

    public boolean recordInformation( String sqlStatement ) {
        logger.debugMethod( "recordInformation" );
        boolean returnedValue = false;

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int insertCountST = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( ADD_AUDIT_LOG_SQL );

            Locale locale = Locale.CANADA;
            TimeZone timezone = TimeZone.getTimeZone( "EST" );
            Calendar cal = Calendar.getInstance( timezone, locale );
            Timestamp tsNow = new Timestamp( cal.getTimeInMillis() );

            setEmail( (String) getFromSession( SESSION_EMAIL ) );
            preparedStatement.setString( 1, getEmail() );
            preparedStatement.setString( 2, sqlStatement );
            preparedStatement.setTimestamp( 3, tsNow );

            insertCountST = preparedStatement.executeUpdate();

            if( insertCountST != 1 ) {
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
