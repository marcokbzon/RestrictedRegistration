package com.moh.data.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ListAdDAO;

public class AdminServiceTypeBean extends AbstractBean {

    @SuppressWarnings( "rawtypes" )
	DataModel serviceTypeDM = new ListDataModel();
    private List<SelectItem> serviceTypeIdList;

    public AdminServiceTypeBean() {
        ListAdDAO listDAO = new ListAdDAO();

        List<AdminServiceTypeData> serviceTypeList = listDAO.getAdminServiceTypeListing();

        Iterator<AdminServiceTypeData> iter = serviceTypeList.iterator();

        serviceTypeIdList = new ArrayList<>();

        while( iter.hasNext() ) {
            String serviceTypeID = iter.next().getServiceTypeID();
            serviceTypeIdList.add( new SelectItem( serviceTypeID, EMPTY_STRING ) );
        }

        serviceTypeDM.setWrappedData( serviceTypeList );
    }

    @SuppressWarnings( "rawtypes" )
    public DataModel getServiceTypeDM() {
        return serviceTypeDM;
    }

    public void setServiceTypeDM( Object data ) {
        serviceTypeDM.setWrappedData( data );
    }

    public List<SelectItem> getServiceTypeIdList() {
        return serviceTypeIdList;
    }

    public void setServiceTypeIdList( List<SelectItem> serviceTypeIdList ) {
        this.serviceTypeIdList = serviceTypeIdList;
    }
}
