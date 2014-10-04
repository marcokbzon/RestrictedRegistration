package com.moh.data.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ListAdDAO;

public class AdminProgramBean extends AbstractBean {

    @SuppressWarnings( "rawtypes" )
	DataModel programDM = new ListDataModel();
    private List<SelectItem> programIdList;

    public AdminProgramBean() {
        ListAdDAO listDAO = new ListAdDAO();

        List<AdminProgramData> programList = listDAO.getAdminProgramListing();

        Iterator<AdminProgramData> iter = programList.iterator();

        programIdList = new ArrayList<>();

        while( iter.hasNext() ) {
            String programID = iter.next().getProgramID();
            programIdList.add( new SelectItem( programID, EMPTY_STRING ) );
        }

        programDM.setWrappedData( programList );
    }

    @SuppressWarnings( "rawtypes" )
	public DataModel getProgramDM() {
        return programDM;
    }

    public void setProgramDM( Object data ) {
        programDM.setWrappedData( data );
    }

    public List<SelectItem> getProgramIdList() {
        return programIdList;
    }

    public void setProgramIdList( List<SelectItem> programIdList ) {
        this.programIdList = programIdList;
    }
}
