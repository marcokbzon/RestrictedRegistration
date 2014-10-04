package com.moh.data.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.model.SelectItem;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ListAbDAO;

public class InstitutionBean extends AbstractBean {

    private List<SelectItem> institutionList;
    private TreeMap<String, String> institutions;
    private String lhinID;

    public InstitutionBean() {
    }

    public List<SelectItem> getInstitutionList() {
        if( institutionList == null ) {
            institutionList = new ArrayList<>();

            Iterator<Map.Entry<String, String>> institutionIterator = ( Iterator<Map.Entry<String, String>> ) getInstitutions().entrySet().iterator();

            while( institutionIterator.hasNext() ) {
                Map.Entry<String, String> institution = institutionIterator.next();
                institutionList.add( new SelectItem( institution.getKey(), institution.getValue() ) );
            }
        }

        return institutionList;
    }

    public void setInstitutionList( List<SelectItem> institutionList ) {
        this.institutionList = institutionList;
    }

    public Map<String, String> getInstitutions() {
        if( institutions == null ) {
            institutions = new TreeMap<>();

            setLhinID( ( String ) getFromSession( SESSION_LHINID ) );

            if( getLhinID() == null || getLhinID().trim().equals(EMPTY_STRING) ) {
                setLhinID( "00" );
            }
            ListAbDAO listsDAO = new ListAbDAO();
            institutions.putAll( listsDAO.getInstitutions( getLhinID() ) );
        }

        return institutions;
    }

    public void setInstitutions( TreeMap<String, String> institutions ) {
        this.institutions = institutions;
    }

    public String getLhinID() {
        return lhinID;
    }

    public void setLhinID( String lhinID ) {
        this.lhinID = lhinID;
    }
}
