package com.moh.admin;

import com.moh.common.AbstractBean;
import com.moh.data.bean.CPSOheaderData;
import com.moh.data.dao.CPSOapprovalDAO;
import com.moh.utils.Logger;

public class CPSOApprovalRQHelper extends AbstractBean {

    private String applicationID;
    private String residentName;
    private String university;
    private String program;
    private String institution;
    private String serviceType;
    private CPSOheaderData cpsoData;

    public CPSOApprovalRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "CPSOApprovalRQHelper" );

        open();
    }

    public String open() {
        logger.debugMethod( "open" );

        cpsoData = ( CPSOheaderData ) getFromSession( SESSION_DATAMAP_CPSO );

        setApplicationID( cpsoData.getApplicationID() );
        setResidentName( cpsoData.getResidentName() );
        setUniversity( cpsoData.getUniversity() );
        setProgram( cpsoData.getProgram() );
        setInstitution( cpsoData.getInstitution() );
        setServiceType( cpsoData.getServiceType() );

        logger.debugPage( "/jsp/adminCPSOapprove.jsp" );
        return "adminCPSOapprove";
    }

    public String update() {
        logger.debugMethod( "update" );

        CPSOapprovalDAO cpsoDAO = new CPSOapprovalDAO();

        if( cpsoDAO.updateCPSOapproval( applicationID ) ) {
            addInfoMessage( "info_application_approve_ok" );

            logger.debugPage( "/jsp/adminCPSOsearch.jsp" );
            return "adminCPSOsearch";
        }
        else {
            addErrorMessage( "error_application_not_approved" );

            logger.debugPage( "/jsp/adminCPSOapprove.jsp" );
            return "adminCPSOapprove";
        }
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID( String applicationID ) {
        this.applicationID = applicationID;
    }

    public String getResidentName() {
        return residentName;
    }

    public void setResidentName( String residentName ) {
        this.residentName = residentName;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity( String university ) {
        this.university = university;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram( String program ) {
        this.program = program;
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
