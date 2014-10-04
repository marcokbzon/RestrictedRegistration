package com.moh.data.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ListAdDAO;

public class AdminInstitutionBean extends AbstractBean {

    @SuppressWarnings( "rawtypes" )
	DataModel institutionDM = new ListDataModel();
    private List<SelectItem> institutionIdList;

    public AdminInstitutionBean() {
        ListAdDAO listDAO = new ListAdDAO();

        List<AdminInstitutionData> institutionList = listDAO.getAdminInstitutionListing();

        Iterator<AdminInstitutionData> iter = institutionList.iterator();

        institutionIdList = new ArrayList<>();

        while( iter.hasNext() ) {
            String instituionID = iter.next().getInstitutionID();
            institutionIdList.add( new SelectItem( instituionID, EMPTY_STRING ) );
        }

        institutionDM.setWrappedData( institutionList );
    }

    @SuppressWarnings( "rawtypes" )
	public DataModel getInstitutionDM() {
        return institutionDM;
    }

    public void setInstitutionDM( Object data ) {
        institutionDM.setWrappedData( data );
    }

    public List<SelectItem> getInstitutionIdList() {
        return institutionIdList;
    }

    public void setInstitutionIdList( List<SelectItem> institutionIdList ) {
        this.institutionIdList = institutionIdList;
    }
}
