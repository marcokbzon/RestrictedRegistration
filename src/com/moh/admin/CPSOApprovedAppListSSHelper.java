package com.moh.admin;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.moh.common.AbstractBean;
import com.moh.review.AppReviewPrintRQHelper;
import com.moh.utils.Logger;

public class CPSOApprovedAppListSSHelper extends AbstractBean {

    private String universityID;
    private String listCount;

    public CPSOApprovedAppListSSHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "CPSOApprovedAppListSSHelper" );
    }

    public String applicationList() {
        setUniversityID( "00000" );

        populate();

        logger.debugPage( "/jsp/adminCPSOapprovedList.jsp" );
        return "adminCPSOapprovedList";
    }

    public void populate() {
        logger.debugMethod( "populate" );

        if( universityID == null || universityID.trim().equals( EMPTY_STRING ) ) {
            addToSession( SESSION_UNIVERSITYID, EMPTY_STRING );
        }
        else {
            addToSession( SESSION_UNIVERSITYID, universityID );
        }

        //CPSOapprovalDAO listDAO = new CPSOapprovalDAO();
        //String appCounter = listDAO.getInitialApplicationCounter( universityID );
        String appCounter = "0";

        setListCount( appCounter );
    }

    public String go() {
        logger.debugMethod( "go" );

        int approvedApps = Integer.parseInt( ( String ) getFromSession( SESSION_LIST_COUNT_APPROVED ) );

        setListCount( EMPTY_STRING + approvedApps );

        logger.debugPage( "/jsp/adminCPSOapprovedList.jsp" );
        return "adminCPSOapprovedList";
    }

    public void changeUniversity( ValueChangeEvent event ) {
        String newUniversityID = EMPTY_STRING;
        
        // PAIRO
        if ( event != null ) {
            try {
                Object eventNewValue = event.getNewValue();

                if ( eventNewValue != null ) {
                    newUniversityID = event.getNewValue().toString();
                }
            }
            catch ( NullPointerException npex ) {
                logger.exception( npex );
            }
        }

        if( newUniversityID == null || newUniversityID.trim().equals( EMPTY_STRING ) ) {
            addToSession( SESSION_UNIVERSITYID, EMPTY_STRING );
        }
        else {
            addToSession( SESSION_UNIVERSITYID, newUniversityID );
        }

        setUniversityID( newUniversityID );

        FacesContext.getCurrentInstance().renderResponse();
    }

    public String open() {
        logger.debugMethod( "open" );

        FacesContext context = FacesContext.getCurrentInstance();
        String applicationID = context.getExternalContext().getRequestParameterMap().get( "appID" );

        addToSession( SESSION_APPLICATIONID, applicationID );

        AppReviewPrintRQHelper appPrint = new AppReviewPrintRQHelper();

        return appPrint.print();
    }

    public String getUniversityID() {
        return universityID;
    }

    public void setUniversityID( String universityID ) {
        this.universityID = universityID;
    }

    public String getListCount() {
        return listCount;
    }

    public void setListCount( String listCount ) {
        this.listCount = listCount;
    }
}
