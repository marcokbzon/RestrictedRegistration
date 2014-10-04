package com.moh.common;

import com.moh.data.dao.AuditDAO;
import java.sql.Connection;
import java.sql.SQLException;

import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.moh.utils.Logger;

public class _ParentDAO implements Constants {

    protected Context context = null;
    protected Connection connection = null;
    protected Logger logger;
    private static String JNDI_DATASOURCE = EMPTY_STRING;

    static {
        JNDI_DATASOURCE = JDBC_JNDI_DATASOURCE;
    }

    public _ParentDAO() {
        connection = null;
    }

    public _ParentDAO( Connection connection ) {
        if( connection != null ) {
            this.connection = connection;
        }
    }

    public void closeConnection( Connection connection ) {
        try {
            connection.close();
            this.connection = null;
        }
        catch( SQLException sex ) {
            System.out.println( "_ParentDAO.closeConnection: " + sex );
        }
    }

    public Connection getConnection() {
        context = null;
        connection = null;

        try {
            context = new InitialContext();
            DataSource dataSource = ( DataSource ) context.lookup( JNDI_DATASOURCE );

            connection = dataSource.getConnection();
        }
        catch( SQLException sex ) {
            System.out.println( "_ParentDAO.getConnection: " + sex );
        }
        catch( Exception ex ) {
            System.out.println( "_ParentDAO.getConnection: " + ex );
        }

        return connection;
    }

    public void auditTransaction(String preparedStatement) {
        AuditDAO auditDAO;
        auditDAO = new AuditDAO();
      
        auditDAO.recordInformation( preparedStatement );
    }

    protected String intToString( int counter ) {
        String returnedValue = EMPTY_STRING;

        if( counter >= 0 & counter < 10 ) {
            returnedValue = "00" + counter;
        }
        else {
            if( counter >= 10 & counter < 100 ) {
                returnedValue = "0" + counter;
            }
            else {
                returnedValue = EMPTY_STRING + counter;
            }
        }

        return returnedValue;
    }

    /**
     * <p>
     * Return the <code>FacesContext</code> instance for the current request.
     */
    protected FacesContext context() {
        return ( FacesContext.getCurrentInstance() );
    }

    protected void addToSession( String key, Object value ) {
        context().getExternalContext().getSessionMap().put( key, value );
    }

    protected Object getFromSession( String key ) {
        Object sValue = context().getExternalContext().getSessionMap().get( key );

        return sValue;
    }

    protected void removeFromSession( String key ) {
        context().getExternalContext().getSessionMap().remove( key );
    }

    protected boolean existsInSession( String key ) {
        boolean exists = context().getExternalContext().getSessionMap().containsKey( key );

        return exists;
    }

    protected void clearSession() {
        context().getExternalContext().getSessionMap().clear();
    }

    protected String numberToMonth( String month ) {
        String returnedValue = EMPTY_STRING;

        int iMonth = Integer.parseInt( month );

        switch( iMonth ) {
            case 1:
                returnedValue = "January";
                break;
            case 2:
                returnedValue = "February";
                break;
            case 3:
                returnedValue = "March";
                break;
            case 4:
                returnedValue = "April";
                break;
            case 5:
                returnedValue = "May";
                break;
            case 6:
                returnedValue = "June";
                break;
            case 7:
                returnedValue = "July";
                break;
            case 8:
                returnedValue = "August";
                break;
            case 9:
                returnedValue = "September";
                break;
            case 10:
                returnedValue = "October";
                break;
            case 11:
                returnedValue = "November";
                break;
            case 12:
                returnedValue = "December";
                break;
            default:
                break;
        }

        return returnedValue;
    }

    public String formatNulls( String fieldValue ) {
        String returnedValue = fieldValue;

        if( fieldValue.trim().toUpperCase().equals( "NULL" ) ) {
            returnedValue = EMPTY_STRING;
        }

        return returnedValue;
    }
}
