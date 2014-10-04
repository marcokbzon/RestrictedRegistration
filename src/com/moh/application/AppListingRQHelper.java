package com.moh.application;

import javax.faces.context.FacesContext;

import com.moh.common.AbstractBean;
import com.moh.utils.Logger;

public class AppListingRQHelper extends AbstractBean {

    public AppListingRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "AppListingRQHelper" );
    }

    public String view() {
        logger.debugMethod( "view" );

        String visaResident = ( String ) getFromSession( SESSION_VISA_RESIDENT );

        if( visaResident != null && ! visaResident.trim().equals(EMPTY_STRING) ) {
            if( visaResident.equals( "1" ) ) {
                addErrorMessage( "error_visa_resident_not_allowed" );

                return "mainPage";
            }
        }

        addToSession( SESSION_APPLICATIONID, EMPTY_STRING );

        logger.debugPage( "/jsp/appFormList.jsp" );
        return "appFormList";
    }

    public String open() {
        logger.debugMethod( "open" );

        FacesContext context = FacesContext.getCurrentInstance();
        String applicationID = context.getExternalContext().getRequestParameterMap().get( "appID" );

        addToSession( SESSION_APPLICATIONID, applicationID );

        logger.debugPage( "/jsp/appFormStatus.jsp" );
        return "appFormStatus";
    }
}
