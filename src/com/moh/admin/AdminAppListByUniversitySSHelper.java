package com.moh.admin;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ListBaDAO;
import com.moh.review.AppReviewPrintRQHelper;
import com.moh.utils.Logger;

public class AdminAppListByUniversitySSHelper extends AbstractBean {

    private String universityID;
    private String selectedStatus;
    private String listCount;

    public AdminAppListByUniversitySSHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "AdminAppListByUniversitySSHelper" );
    }

    public String applicationList() {
        setUniversityID( EMPTY_STRING );
        setSelectedStatus( PHASE_NEW );

        populate();

        logger.debugPage( "/jsp/adminAppListByUniversity.jsp" );
        return "adminAppListByUniversity";
    }

    public void populate() {
        logger.debugMethod( "populate" );

        if( universityID == null || universityID.trim().equals( EMPTY_STRING ) ) {
            addToSession( SESSION_UNIVERSITYID, EMPTY_STRING );
        }
        else {
            addToSession( SESSION_UNIVERSITYID, universityID );
        }
        if( selectedStatus == null || selectedStatus.trim().equals( EMPTY_STRING ) ) {
            addToSession( SESSION_CODEID, EMPTY_STRING );
        }
        else {
            addToSession( SESSION_CODEID, selectedStatus );
        }

        ListBaDAO listDAO = new ListBaDAO();
        String appCounter = listDAO.getInitialApplicationCounter( PHASE_NEW );

        setListCount( appCounter );
    }

    public String go() {
        logger.debugMethod( "go" );
        //populate();
        //FacesContext.getCurrentInstance().renderResponse();

        setListCount( ( String ) getFromSession( SESSION_LIST_COUNT ) );

        logger.debugPage( "/jsp/adminAppListByUniversity.jsp" );
        return "adminAppListByUniversity";
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

    public void changeStatus( ValueChangeEvent event ) {
        String newStatus = EMPTY_STRING;
		
        if ( event != null ) {
            try {
                Object eventNewValue = event.getNewValue();

                if ( eventNewValue != null ) {
                    newStatus = event.getNewValue().toString();
                }
            }
            catch ( NullPointerException npex ) {
                logger.exception( npex );
            }
        }

        addToSession( SESSION_CODEID, newStatus );

        setSelectedStatus( newStatus );

        //FacesContext.getCurrentInstance().renderResponse();
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

    public String getSelectedStatus() {
        return selectedStatus;
    }

    public void setSelectedStatus( String selectedStatus ) {
        this.selectedStatus = selectedStatus;
    }

    public String getListCount() {
        return listCount;
    }

    public void setListCount( String listCount ) {
        this.listCount = listCount;
    }
}
