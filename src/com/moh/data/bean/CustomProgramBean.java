package com.moh.data.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.model.SelectItem;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ListAaDAO;

public class CustomProgramBean extends AbstractBean {

    private List<SelectItem> programList;
    private TreeMap<String, String> programs;

    public CustomProgramBean() {
    }

    public List<SelectItem> getProgramList() {
        if( programList == null ) {
            programList = new ArrayList<>();

            Iterator<Map.Entry<String, String>> programIterator = ( Iterator<Map.Entry<String, String>> ) getPrograms().entrySet().iterator();

            while( programIterator.hasNext() ) {
                Map.Entry<String, String> program = programIterator.next();
                programList.add( new SelectItem( program.getKey(), program.getValue() ) );
            }
        }

        return programList;
    }

    public void setProgramList( List<SelectItem> programList ) {
        this.programList = programList;
    }

    public Map<String, String> getPrograms() {
        if( programs == null ) {
            programs = new TreeMap<>();

            String univID = ( String ) getFromSession( SESSION_UNIVERSITYID );

            ListAaDAO listsDAO = new ListAaDAO();
            programs.putAll( listsDAO.getCustomPrograms( univID ) );
        }

        return programs;
    }

    public void setPrograms( TreeMap<String, String> programs ) {
        this.programs = programs;
    }
}
