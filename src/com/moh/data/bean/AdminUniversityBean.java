package com.moh.data.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ListAdDAO;

public class AdminUniversityBean extends AbstractBean {

	@SuppressWarnings( "rawtypes" )
	DataModel universityDM = new ListDataModel();
    private List<SelectItem> universityIdList;

    public AdminUniversityBean() {
        ListAdDAO listDAO = new ListAdDAO();

        List<AdminUniversityData> universityList = listDAO.getAdminUniversityListing();

        Iterator<AdminUniversityData> iter = universityList.iterator();

        universityIdList = new ArrayList<>();

        while( iter.hasNext() ) {
            String universityID = iter.next().getUniversityID();
            universityIdList.add( new SelectItem( universityID, EMPTY_STRING ) );
        }

        universityDM.setWrappedData( universityList );
    }

    @SuppressWarnings( "rawtypes" )
    public DataModel getUniversityDM() {
        return universityDM;
    }

    public void setUniversityDM( Object data ) {
        universityDM.setWrappedData( data );
    }

    public List<SelectItem> getUniversityIdList() {
        return universityIdList;
    }

    public void setUniversityIdList( List<SelectItem> universityIdList ) {
        this.universityIdList = universityIdList;
    }
}
