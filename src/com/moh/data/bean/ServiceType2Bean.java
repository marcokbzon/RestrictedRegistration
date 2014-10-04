package com.moh.data.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.model.SelectItem;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ListAbDAO;

public class ServiceType2Bean extends AbstractBean {

    private List<SelectItem> serviceTypeList;
    private TreeMap<String, String> serviceTypes;
    private String institutionID;

    public ServiceType2Bean() {
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

            setInstitutionID( ( String ) getFromSession( SESSION_INSTITUTIONID ) );

            if( getInstitutionID() == null || getInstitutionID().trim().equals(EMPTY_STRING) ) {
                setInstitutionID( "0000" );
            }

            ListAbDAO listsDAO = new ListAbDAO();
            serviceTypes.putAll( listsDAO.getServiceTypes( getInstitutionID() ) );
        }

        return serviceTypes;
    }

    public void setServiceTypes( TreeMap<String, String> serviceTypes ) {
        this.serviceTypes = serviceTypes;
    }

    public String getInstitutionID() {
        return institutionID;
    }

    public void setInstitutionID( String institutionID ) {
        this.institutionID = institutionID;
    }
}
