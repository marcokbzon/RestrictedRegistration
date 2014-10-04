package com.moh.data.bean;

import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ListAcDAO;

public class AppFormListBean extends AbstractBean {

	@SuppressWarnings( "rawtypes" )
    DataModel applicationsDM = new ListDataModel();
    private String email;

    public AppFormListBean() {
        ListAcDAO listDAO = new ListAcDAO();

        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );
        List<AppFormListData> applicationList = listDAO.getAppListing( getEmail() );

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
