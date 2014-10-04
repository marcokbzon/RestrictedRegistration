package com.moh.data.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.faces.model.SelectItem;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ListAaDAO;

public class ProvStateBean extends AbstractBean {

    private List<SelectItem> provStateList;
    private SortedMap<String, String> provStates;
    private String countryCode;

    public ProvStateBean() {
    }

    public List<SelectItem> getProvStateList() {
        if( provStateList == null ) {
            provStateList = new ArrayList<>();

            Iterator<Map.Entry<String, String>> provStateIterator = ( Iterator<Map.Entry<String, String>> ) getProvStates().entrySet().iterator();

            while( provStateIterator.hasNext() ) {
                Map.Entry<String, String> provState = provStateIterator.next();
                provStateList.add( new SelectItem( provState.getKey(), provState.getValue() ) );
            }
        }

        return provStateList;
    }

    public void setProvStateList( List<SelectItem> provStateList ) {
        this.provStateList = provStateList;
    }

    public Map<String, String> getProvStates() {
        if( provStates == null ) {
            provStates = new TreeMap<>();

            ListAaDAO listsDAO = new ListAaDAO();
            provStates.putAll( listsDAO.getProvStates( "CAN" ) );
        }

        return provStates;
    }

    public void setProvStates( SortedMap<String, String> provStates ) {
        this.provStates = provStates;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode( String countryCode ) {
        this.countryCode = countryCode;
    }
}
