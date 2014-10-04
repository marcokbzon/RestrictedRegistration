package com.moh.data.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.model.SelectItem;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ListAaDAO;

public class SchoolResidencyBean extends AbstractBean {

    private List<SelectItem> schoolList;
    private TreeMap<String, String> schools;

    public SchoolResidencyBean() {
    }

    public List<SelectItem> getSchoolList() {
        if( schoolList == null ) {
            schoolList = new ArrayList<>();

            Iterator<Map.Entry<String, String>> schoolIterator = ( Iterator<Map.Entry<String, String>> ) getSchools().entrySet().iterator();

            while( schoolIterator.hasNext() ) {
                Map.Entry<String, String> school = schoolIterator.next();
                schoolList.add( new SelectItem( school.getKey(), school.getValue() ) );
            }
        }

        return schoolList;
    }

    public void setSchoolList( List<SelectItem> schoolList ) {
        this.schoolList = schoolList;
    }

    public Map<String, String> getSchools() {
        if( schools == null ) {
            schools = new TreeMap<>();

            ListAaDAO listsDAO = new ListAaDAO();
            schools.putAll( listsDAO.getUniversities() );
        }

        return schools;
    }

    public void setSchools( TreeMap<String, String> schools ) {
        this.schools = schools;
    }
}
