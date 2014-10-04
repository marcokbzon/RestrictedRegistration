package com.moh.data.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.model.SelectItem;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ListAbDAO;

public class LHINBean extends AbstractBean {

    private List<SelectItem> lhinList;
    private TreeMap<String, String> lhins;

    public LHINBean() {
    }

    public List<SelectItem> getLhinList() {
        if( lhinList == null ) {
            lhinList = new ArrayList<>();

            Iterator<Map.Entry<String, String>> lhinIterator = ( Iterator<Map.Entry<String, String>> ) getLhins().entrySet().iterator();

            while( lhinIterator.hasNext() ) {
                Map.Entry<String, String> lhin = lhinIterator.next();
                lhinList.add( new SelectItem( lhin.getKey(), lhin.getValue() ) );
            }
        }

        return lhinList;
    }

    public void setLhinList( List<SelectItem> locationList ) {
        this.lhinList = locationList;
    }

    public Map<String, String> getLhins() {
        if( lhins == null ) {
            lhins = new TreeMap<>();

            ListAbDAO listsDAO = new ListAbDAO();
            lhins.putAll( listsDAO.getLHINs() );
        }

        return lhins;
    }

    public void setLhins( TreeMap<String, String> lhins ) {
        this.lhins = lhins;
    }
}
