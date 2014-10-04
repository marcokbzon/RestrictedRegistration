package com.moh.review;

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

        addToSession( SESSION_APPLICATIONID, EMPTY_STRING );

        logger.debugPage( "/jsp/appReviewList.jsp" );
        return "appReviewList";
    }

    public String open() {
        logger.debugMethod( "open" );

        FacesContext context = FacesContext.getCurrentInstance();
        String applicationID = context.getExternalContext().getRequestParameterMap().get( "appID" );

        addToSession( SESSION_APPLICATIONID, applicationID );

        logger.debugPage( "/jsp/appReview01.jsp" );
        return "appReview01";
    }
}
