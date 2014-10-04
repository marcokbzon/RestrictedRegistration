package com.moh.data.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.model.SelectItem;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ListAaDAO;

public class ServiceTypeBean extends AbstractBean {

    private List<SelectItem> serviceTypeList;
    private TreeMap<String, String> serviceTypes;

    public ServiceTypeBean() {
    }

    public List<SelectItem> getServiceTypeList() {
        if( serviceTypeList == null ) {
            serviceTypeList = new ArrayList<>();

            Iterator<Map.Entry<String, String>> serviceTypeIterator = ( Iterator<Map.Entry<String, String>> ) getServiceTypes().entrySet().iterator();

            while( serviceTypeIterator.hasNext() ) {
                Map.Entry<String, String> serviceType = serviceTypeIterator.next();
                serviceTypeList.add( new SelectItem( serviceType.getKey(), serviceType.getValue() ) );
            }
        }

        return serviceTypeList;
    }

    public void setServiceTypeList( List<SelectItem> serviceTypeList ) {
        this.serviceTypeList = serviceTypeList;
    }

    public Map<String, String> getServiceTypes() {
        if( serviceTypes == null ) {
            serviceTypes = new TreeMap<>();

            ListAaDAO listsDAO = new ListAaDAO();
            serviceTypes.putAll( listsDAO.getServiceTypes() );
        }

        return serviceTypes;
    }

    public void setServiceTypes( TreeMap<String, String> serviceTypes ) {
        this.serviceTypes = serviceTypes;
    }
}
