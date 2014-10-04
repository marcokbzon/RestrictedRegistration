package com.moh.data.bean;

import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ListAcDAO;

public class AppReviewListBean extends AbstractBean {

	@SuppressWarnings( "rawtypes" )
    DataModel applicationsDM = new ListDataModel();
    private String email;

    public AppReviewListBean() {
        ListAcDAO listDAO = new ListAcDAO();

        String usrRole = ( String ) getFromSession( SESSION_ROLEID );

        List<AppReviewListData> applicationList = null;
        
        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );
        
        if( usrRole.equals( ROLE_SUPERVISOR ) ) {
            applicationList = listDAO.getReviewListingInst( getEmail() );
        }
        else {
            if( usrRole.equals( ROLE_DIRECTOR ) || usrRole.equals( ROLE_DEAN ) ) {
                applicationList = listDAO.getReviewListingUniv( ( String ) getFromSession( SESSION_UNIVERSITYID ) );
            }
            else {
                if( usrRole.equals( ROLE_COMMITTEE ) ) {
                    applicationList = listDAO.getReviewListingComm( ( String ) getFromSession( SESSION_UNIVERSITYID ) );
                }
                else {
                    if( usrRole.equals( ROLE_ADMIN_COMMITTEE ) ) {
                        applicationList = listDAO.getReviewListingComm( ( String ) getFromSession( SESSION_UNIVERSITYID ) );
                    }
                }
            }
        }

        applicationsDM.setWrappedData( applicationList );
    }

    @SuppressWarnings( "rawtypes" )
    public DataModel getApplicationsDM() {
        return applicationsDM;
    }

    public void setApplicationsDM( Object data ) {
        applicationsDM.setWrappedData( data );
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
