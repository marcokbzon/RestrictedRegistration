package com.moh.application;

import java.util.Map;

import com.moh.common.AbstractBean;
import com.moh.data.dao.StatusDAO;
import com.moh.utils.Logger;

public class AppStatusRQHelper extends AbstractBean {

    private String applicationID;
    private String institution;
    private String serviceType;

    public AppStatusRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "AppStatusRQHelper" );

        view();
    }

    public String view() {
        logger.debugMethod( "view" );

        setApplicationID( ( String ) getFromSession( SESSION_APPLICATIONID ) );

        StatusDAO statusDAO = new StatusDAO();

        Map<String, String> statusHdr = statusDAO.getStatusHeader( getApplicationID() );

        setInstitution( statusHdr.get( "InstitutionName" ) );
        setServiceType( statusHdr.get( "ServiceTypeDesc" ) );

        logger.debugPage( "/jsp/appFormStatus.jsp" );
        return "appFormStatus";
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID( String applicationID ) {
        this.applicationID = applicationID;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution( String institution ) {
        this.institution = institution;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType( String serviceType ) {
        this.serviceType = serviceType;
    }
}
