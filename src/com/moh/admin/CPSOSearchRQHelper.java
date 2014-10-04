package com.moh.admin;

import com.moh.common.AbstractBean;
import com.moh.data.dao.CPSOapprovalDAO;
import com.moh.utils.Logger;

public class CPSOSearchRQHelper extends AbstractBean {

    private String applicationID;

    public CPSOSearchRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "AdminSearchRQHelper" );
    }

    public String open() {
        logger.debugMethod( "open" );

        setApplicationID( EMPTY_STRING );

        logger.debugPage( "/jsp/adminCPSOsearch.jsp" );
        return "adminCPSOsearch";
    }

    public String search() {
        logger.debugMethod( "search" );

        if( applicationID == null || applicationID.trim().equals( EMPTY_STRING ) ) {
            addErrorMessage( "error_applicationId_required" );

            logger.debugPage( "/jsp/adminCPSOsearch.jsp" );
            return "adminCPSOsearch";
        }

        CPSOapprovalDAO cpsoDAO = new CPSOapprovalDAO();

        if( cpsoDAO.applicationIDExists( applicationID ) ) {
            cpsoDAO.getApplicationHeader( applicationID ); // dataMap is populated in session

            logger.debugPage( "/jsp/adminCPSOapprove.jsp" );
            return "adminCPSOapprove";
        }
        else {
            addErrorMessage( "error_applicationId_not_valid" );

            logger.debugPage( "/jsp/adminCPSOsearch.jsp" );
            return "adminCPSOsearch";
        }
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID( String applicationID ) {
        this.applicationID = applicationID;
    }
}
