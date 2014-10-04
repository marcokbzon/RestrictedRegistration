package com.moh.data.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ClinicalDAO;
import com.moh.data.dao.UserDAO;

public class RotationBean extends AbstractBean {

	@SuppressWarnings( "rawtypes" )
    DataModel rotationDM = new ListDataModel();
    private List<SelectItem> serviceTypePosList;
    private String residentEmail;

    public RotationBean() {
        ClinicalDAO clinicalDAO = new ClinicalDAO();
        setResidentEmail ( EMPTY_STRING );

        if( ( ( String ) getFromSession( SESSION_ROLEID ) ).equals( ROLE_RESIDENT ) ) {
            setResidentEmail( (String ) getFromSession( SESSION_EMAIL ) );
        }
        else {
            UserDAO userDAO = new UserDAO();
            setResidentEmail( userDAO.getEmail( ( String ) getFromSession( SESSION_APPLICATIONID ) ) );
        }

        List<RotationData> rotationList = clinicalDAO.getRotations( getResidentEmail() );

        Iterator<RotationData> iter = rotationList.iterator();

        serviceTypePosList = new ArrayList<>();

        while( iter.hasNext() ) {
            String position = iter.next().getPosition();
            serviceTypePosList.add( new SelectItem( position, EMPTY_STRING ) );
        }

        rotationDM.setWrappedData( rotationList );
    }

    @SuppressWarnings( "rawtypes" )
    public DataModel getRotationDM() {
        return rotationDM;
    }

    public void setRotationDM( Object data ) {
        rotationDM.setWrappedData( data );
    }

    public List<SelectItem> getServiceTypePosList() {
        return serviceTypePosList;
    }

    public void setServiceTypePosList( List<SelectItem> serviceTypePosList ) {
        this.serviceTypePosList = serviceTypePosList;
    }
    
    private String getResidentEmail() {
        if ( residentEmail == null || residentEmail.trim().equals( EMPTY_STRING ) ) {
            return EMPTY_STRING;
        }
        else {
            return residentEmail.trim().toLowerCase();
        }
    }
    
    private void setResidentEmail( String residentEmail ) {
        if ( residentEmail == null || residentEmail.trim().equals( EMPTY_STRING ) ) {
            this.residentEmail = EMPTY_STRING;
        }
        else {
            this.residentEmail = residentEmail.trim().toLowerCase();
        }
    }
}
