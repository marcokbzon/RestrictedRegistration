package com.moh.data.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.model.SelectItem;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ListAbDAO;

public class LocationBean extends AbstractBean {

    private List<SelectItem> locationList;
    private TreeMap<String, String> locations;
    private String institutionID;

    public LocationBean() {
    }

    public List<SelectItem> getLocationList() {
        if( locationList == null ) {
            locationList = new ArrayList<>();

            Iterator<Map.Entry<String, String>> locationIterator = ( Iterator<Map.Entry<String, String>> ) getLocations().entrySet().iterator();

            while( locationIterator.hasNext() ) {
                Map.Entry<String, String> location = locationIterator.next();
                locationList.add( new SelectItem( location.getKey(), location.getValue() ) );
            }
        }

        return locationList;
    }

    public void setLocationList( List<SelectItem> locationList ) {
        this.locationList = locationList;
    }

    public Map<String, String> getLocations() {
        if( locations == null ) {
            locations = new TreeMap<>();

            setInstitutionID( ( String ) getFromSession( SESSION_INSTITUTIONID ) );

            if( getInstitutionID() == null || getInstitutionID().trim().equals(EMPTY_STRING) ) {
                setInstitutionID( "0000" );
            }
            ListAbDAO listsDAO = new ListAbDAO();
            locations.putAll( listsDAO.getLocations( getInstitutionID() ) );
        }

        return locations;
    }

    public void setLocations( TreeMap<String, String> locations ) {
        this.locations = locations;
    }

    public String getInstitutionID() {
        return institutionID;
    }

    public void setInstitutionID( String institutionID ) {
        this.institutionID = institutionID;
    }
}
