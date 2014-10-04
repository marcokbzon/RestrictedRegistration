package com.moh.common;

import com.moh.utils.Logger;

public class MainRQHelper extends AbstractBean {

    public MainRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "MainRQHelper" );
    }

    public String view() {
        //if ( (( String ) getFromSession( SESSION_EMAIL )).trim().equals( EMPTY_STRING ) ) {
        //	addErrorMessage( "error_invalid_credentials" );
        //
        //	return "login";
        //}

        logger.debugMethod( "view" );
        String forwardToPage = EMPTY_STRING;

        String roleId = ( String ) getFromSession( SESSION_ROLEID );
        
        if( roleId.equals( ROLE_ADMIN ) ) {
            forwardToPage = "mainAdmin";
        }
        else {
            if( roleId.equals( ROLE_RESIDENT ) ) {
                forwardToPage = "mainResident";
            }
            else {
                if ( roleId.equals( ROLE_SUPERVISOR ) || roleId.equals( ROLE_DIRECTOR ) || roleId.equals( ROLE_DEAN ) || roleId.equals( ROLE_COMMITTEE ) ) {
                    forwardToPage = "mainReviewer";
                }
                else {
                    addErrorMessage( "error_role_required" );
                    return "login";
                }
            }
        }

        addToSession( SESSION_APPLICATIONID, EMPTY_STRING );

        String jspPage = EMPTY_STRING;
        
        if ( forwardToPage.equals( "mainAdmin" ) ) {
            jspPage = "/jsp/mainAdmin.jsp";
        }
        else {
            if ( forwardToPage.equals( "mainResident" ) ) {
                jspPage = "/jsp/mainResident.jsp";
            }
            else {
                if ( forwardToPage.equals( "mainReviewer" ) ) {
                    jspPage = "/jsp/mainReviewer.jsp";
                }
            }
        }
        
        logger.debugPage( jspPage );
        return forwardToPage;
    }
}
