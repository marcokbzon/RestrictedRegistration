package com.moh.data.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import com.moh.common.AbstractBean;
import com.moh.data.dao.CPSOapprovalDAO;

public class CPSOapprovedAppsBean extends AbstractBean {

	@SuppressWarnings( "rawtypes" )
    DataModel applicationDM = new ListDataModel();
    private List<SelectItem> applicationIdList;

    public CPSOapprovedAppsBean() {
        CPSOapprovalDAO listDAO = new CPSOapprovalDAO();

        String universityID = ( String ) getFromSession( SESSION_UNIVERSITYID );

        List<AdminAppListByCriteriaData2> applicationList = listDAO.getAppListByUniversity( universityID );

        Iterator<AdminAppListByCriteriaData2> iter = applicationList.iterator();

        applicationIdList = new ArrayList<>();

        int counter = 0;

        while( iter.hasNext() ) {
            String applicationID = iter.next().getApplicationID();
            applicationIdList.add( new SelectItem( applicationID, EMPTY_STRING ) );

            counter++;
        }

        addToSession( SESSION_LIST_COUNT_APPROVED, EMPTY_STRING + counter );

        applicationDM.setWrappedData( applicationList );
    }

    @SuppressWarnings( "rawtypes" )
    public DataModel getApplicationDM() {
        return applicationDM;
    }

    public void setApplicationDM( Object data ) {
        applicationDM.setWrappedData( data );
    }

    public List<SelectItem> getApplicationIdList() {
        return applicationIdList;
    }

    public void setApplicationIdList( List<SelectItem> applicationIdList ) {
        this.applicationIdList = applicationIdList;
    }
}
