package com.moh.data.bean;

import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.moh.common.AbstractBean;
import com.moh.data.dao.StatusDAO;

public class AppFormStatusBean extends AbstractBean {

	@SuppressWarnings( "rawtypes" )
    DataModel statusDM = new ListDataModel();

    public AppFormStatusBean() {
        StatusDAO statusDAO = new StatusDAO();

        List<AppFormStatusData> statusList = statusDAO.getAppStatus( ( String ) getFromSession( SESSION_APPLICATIONID ) );

        statusDM.setWrappedData( statusList );
    }

    @SuppressWarnings( "rawtypes" )
    public DataModel getStatusDM() {
        return statusDM;
    }

    public void setStatusDM( Object data ) {
        statusDM.setWrappedData( data );
    }
}
