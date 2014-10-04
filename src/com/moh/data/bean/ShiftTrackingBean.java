package com.moh.data.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ShiftTrackingDAO;

public class ShiftTrackingBean extends AbstractBean {

	@SuppressWarnings( "rawtypes" )
    DataModel shiftTrackDM = new ListDataModel();
    private List<SelectItem> applicationIdList;
    private String email;

    public ShiftTrackingBean() {
        ShiftTrackingDAO listDAO = new ShiftTrackingDAO();

        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );
        String year = ( String ) getFromSession( SHIFT_TRACK_YEAR );
        String month = ( String ) getFromSession( SHIFT_TRACK_MONTH );

        List<ShiftTrackingData> shiftTrackList = listDAO.getShiftTrackingListing( getEmail(), year, month );

        Iterator<ShiftTrackingData> iter = shiftTrackList.iterator();

        applicationIdList = new ArrayList<>();

        while( iter.hasNext() ) {
            String applicationID = iter.next().getApplicationID();
            applicationIdList.add( new SelectItem( applicationID, EMPTY_STRING ) );
        }

        shiftTrackDM.setWrappedData( shiftTrackList );
    }

    @SuppressWarnings( "rawtypes" )
    public DataModel getShiftTrackDM() {
        return shiftTrackDM;
    }

    public void setShiftTrackDM( Object data ) {
        shiftTrackDM.setWrappedData( data );
    }

    public List<SelectItem> getApplicationIdList() {
        return applicationIdList;
    }

    public void setApplicationIdList( List<SelectItem> applicationIdList ) {
        this.applicationIdList = applicationIdList;
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
