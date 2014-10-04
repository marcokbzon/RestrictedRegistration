package com.moh.common;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.moh.utils.Logger;
import com.moh.utils.ObjectManipulation;

public class AbstractBean implements Constants {

    protected Logger logger;

    /**
     * <p>
     * Return the <code>FacesContext</code> instance for the current request.
     */
    protected FacesContext context() {
        return ( FacesContext.getCurrentInstance() );
    }

    //PAIRO
    protected Object getPageName () {
        Object obj = context().getExternalContext().getRequest();
        
        return obj;
    }
    
    protected void addToSession( String key, Object value ) {
        removeFromSession( key );  //--- PAIRO

        context().getExternalContext().getSessionMap().put( key, value );
    }

    protected Object getFromSession( String key ) {
        Object sValue = context().getExternalContext().getSessionMap().get( key );
        
        if (sValue == null) {
            sValue = EMPTY_STRING;  //--- PAIRO
        }

        return sValue;
    }

    protected String nullsToString( String value ) {
        ObjectManipulation objMan = new ObjectManipulation();
        return objMan.nullsToString( value );
    }
    
    protected void removeFromSession( String key ) {
        if ( existsInSession( key ) ) {
            context().getExternalContext().getSessionMap().remove( key );
        }
    }

    protected boolean existsInSession( String key ) {
        boolean exists = context().getExternalContext().getSessionMap().containsKey( key );

        return exists;
    }

    protected void clearSession() {
        context().getExternalContext().getSessionMap().clear();
    }

    /**
     * <p>
     * Add a localized message to the <code>FacesContext</code> for the current request.
     * </p>
     */
    protected void addErrorMessage( String key ) {
        // Look up the requested message text
        String text = EMPTY_STRING;

        try {
            ResourceBundle bundle = ResourceBundle.getBundle( "ApplicationMessages", context().getViewRoot().getLocale() );
            text = bundle.getString( key );
        }
        catch( Exception e ) {
            text = "???" + key + "???";
        }

        logger.error( text );

        // Construct and add a FacesMessage containing it
        context().addMessage( EMPTY_STRING, new FacesMessage( FacesMessage.SEVERITY_ERROR, text, EMPTY_STRING ) );
    }
    
    protected void addExtraLoggingInfo( String freeFormatText ) {
        logger.trace(freeFormatText);
    }

    /**
     * <p>
     * Add a localized message to the <code>FacesContext</code> for the current request.
     * </p>
     */
    protected void addInfoMessage( String key ) {
        // Look up the requested message text
        String text = EMPTY_STRING;

        try {
            ResourceBundle bundle = ResourceBundle.getBundle( "ApplicationMessages", context().getViewRoot().getLocale() );
            text = bundle.getString( key );
        }
        catch( Exception e ) {
            text = "???" + key + "???";
        }

        logger.info( text );

        // Construct and add a FacesMessage containing it
        context().addMessage( EMPTY_STRING, new FacesMessage( FacesMessage.SEVERITY_INFO, text, EMPTY_STRING ) );
    }

    protected void addWarningMessage( String key ) {
        // Look up the requested message text
        String text = EMPTY_STRING;

        try {
            ResourceBundle bundle = ResourceBundle.getBundle( "ApplicationMessages", context().getViewRoot().getLocale() );
            text = bundle.getString( key );
        }
        catch( Exception e ) {
            text = "???" + key + "???";
        }

        // Construct and add a FacesMessage containing it
        context().addMessage( EMPTY_STRING, new FacesMessage( FacesMessage.SEVERITY_WARN, text, EMPTY_STRING ) );
    }

    /**
     * <p>
     * Add a localized message to the <code>FacesContext</code> for the current request.
     * </p>
     *
     * @param clientId
     *            Client identifier of the component this message relates to, or
     *            <code>EMPTY_STRING</code> for global messages
     * @param key
     *            Message key of the message to include
     */
    protected void message( String clientId, String key ) {
        // Look up the requested message text
        String text = EMPTY_STRING;

        try {
            ResourceBundle bundle = ResourceBundle.getBundle( "ApplicationMessages", context().getViewRoot().getLocale() );
            text = bundle.getString( key );
        }
        catch( Exception e ) {
            text = "???" + key + "???";
        }

        // Construct and add a FacesMessage containing it
        context().addMessage( clientId, new FacesMessage( FacesMessage.SEVERITY_ERROR, text, EMPTY_STRING ) );
    }

    /**
     * <p>
     * Add a localized message to the <code>FacesContext</code> for the current request.
     * </p>
     *
     * @param clientId
     *            Client identifier of the component this message relates to, or
     *            <code>EMPTY_STRING</code> for global messages
     * @param key
     *            Message key of the message to include
     * @param params
     *            Substitution parameters for using the localized text as a message format
     */
    protected void message( String clientId, String key, Object[] params ) {
        // Look up the requested message text
        String text = EMPTY_STRING;

        try {
            ResourceBundle bundle = ResourceBundle.getBundle( "ApplicationMessages", context().getViewRoot().getLocale() );
            text = bundle.getString( key );
        }
        catch( Exception e ) {
            text = "???" + key + "???";
        }

        // Perform the requested substitutions
        if( ( params != null ) && ( params.length > 0 ) ) {
            text = MessageFormat.format( text, params );
        }

        // Construct and add a FacesMessage containing it
        context().addMessage( clientId, new FacesMessage( text ) );
    }

    protected String numberToMonth( String month ) {
        String returnedValue = EMPTY_STRING;

        if ( ! month.trim().equals(EMPTY_STRING) ) {
            int iMonth = Integer.parseInt( month );

            switch( iMonth ) {
                case 1:
                    returnedValue = "Jan";
                    break;
                case 2:
                    returnedValue = "Feb";
                    break;
                case 3:
                    returnedValue = "Mar";
                    break;
                case 4:
                    returnedValue = "Apr";
                    break;
                case 5:
                    returnedValue = "May";
                    break;
                case 6:
                    returnedValue = "Jun";
                    break;
                case 7:
                    returnedValue = "Jul";
                    break;
                case 8:
                    returnedValue = "Aug";
                    break;
                case 9:
                    returnedValue = "Sep";
                    break;
                case 10:
                    returnedValue = "Oct";
                    break;
                case 11:
                    returnedValue = "Nov";
                    break;
                case 12:
                    returnedValue = "Dec";
                    break;
                default:
                    break;
            }
        }

        return returnedValue;
    }
}
