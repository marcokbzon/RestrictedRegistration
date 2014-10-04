package com.moh.data.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.model.SelectItem;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ListAaDAO;

public class CountryBean extends AbstractBean {

    private List<SelectItem> countryList;
    private TreeMap<String, String> countries;

    public CountryBean() {
    }

    public List<SelectItem> getCountryList() {
        if( countryList == null ) {
            countryList = new ArrayList<>();

            Iterator<Map.Entry<String, String>> countryIterator = ( Iterator<Map.Entry<String, String>> ) getCountries().entrySet().iterator();

            while( countryIterator.hasNext() ) {
                Map.Entry<String, String> country = countryIterator.next();
                countryList.add( new SelectItem( country.getKey(), country.getValue() ) );
            }
        }

        return countryList;
    }

    public void setCountryList( List<SelectItem> countryList ) {
        this.countryList = countryList;
    }

    public Map<String, String> getCountries() {
        if( countries == null ) {
            countries = new TreeMap<>();

            ListAaDAO listsDAO = new ListAaDAO();
            countries.putAll( listsDAO.getCountries() );
        }

        return countries;
    }

    public void setCountries( TreeMap<String, String> countries ) {
        this.countries = countries;
    }
}
