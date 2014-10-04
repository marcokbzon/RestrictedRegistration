package com.moh.admin;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ListBbDAO;
import com.moh.review.AppReviewPrintRQHelper;
import com.moh.utils.Logger;

public class AdminAppListByUniversitySSHelper2 extends AbstractBean {

    private String universityID;
    private String selectedRole;
    private String listCount;

    public AdminAppListByUniversitySSHelper2() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "AdminAppListByUniversitySSHelper2" );
    }

    public String applicationList() {
        setUniversityID( EMPTY_STRING );
        setSelectedRole( ROLE_SUPERVISOR );

        populate();

        logger.debugPage( "/jsp/adminAppListByUniversity2.jsp" );
        return "adminAppListByUniversity2";
    }

    public void populate() {
        logger.debugMethod( "populate" );

        if( universityID == null || universityID.trim().equals( EMPTY_STRING ) ) {
            addToSession( SESSION_UNIVERSITYID, EMPTY_STRING );
        }
        else {
            addToSession( SESSION_UNIVERSITYID, universityID );
        }
        if( selectedRole == null || selectedRole.trim().equals( EMPTY_STRING ) ) {
            addToSession( SESSION_SELECTED_ROLEID, EMPTY_STRING );
        }
        else {
            addToSession( SESSION_SELECTED_ROLEID, selectedRole );
        }

        ListBbDAO listDAO = new ListBbDAO();
        String appCounter = listDAO.getInitialApplicationCounter();

        setListCount( appCounter );
    }

    public String go() {
        logger.debugMethod( "go" );
        //populate();
        //FacesContext.getCurrentInstance().renderResponse();

        int approvedApps = Integer.parseInt( ( String ) getFromSession( SESSION_LIST_COUNT_APPROVED ) );
        int rejectedApps = Integer.parseInt( ( String ) getFromSession( SESSION_LIST_COUNT_REJECTED ) );

        setListCount( EMPTY_STRING + ( approvedApps + rejectedApps ) );

        logger.debugPage( "/jsp/adminAppListByUniversity2.jsp" );
        return "adminAppListByUniversity2";
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
        String newRole = EMPTY_STRING;
		
        if ( event != null ) {
            try {
                Object eventNewValue = event.getNewValue();

                if ( eventNewValue != null ) {
                    newRole = event.getNewValue().toString();
                }
            }
            catch ( NullPointerException npex ) {
                logger.exception( npex );
            }
        }

        addToSession( SESSION_SELECTED_ROLEID, newRole );

        setSelectedRole( newRole );

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

    public String getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole( String selectedRole ) {
        this.selectedRole = selectedRole;
    }

    public String getListCount() {
        return listCount;
    }

    public void setListCount( String listCount ) {
        this.listCount = listCount;
    }
}
