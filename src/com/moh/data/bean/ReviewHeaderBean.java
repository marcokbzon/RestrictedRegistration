package com.moh.data.bean;

import java.util.Map;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ReviewInfoDAO;
import com.moh.utils.Logger;

public class ReviewHeaderBean extends AbstractBean {

    private String applicantName;
    private String applicationDate;
    private String university;  // institution (in the web page)
    private String institution; // employer (in the web page)
    private String serviceType; // program (in the web page)
    private String supervisorName;
    private String applicationRejectedFlag;

    public ReviewHeaderBean() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ReviewHeaderBean" );

        ReviewInfoDAO reviewInfoDAO = new ReviewInfoDAO();
        Map<String, String> headerInfo = reviewInfoDAO.getHeaderInfo( ( String ) getFromSession( SESSION_APPLICATIONID ) );

        setApplicantName( headerInfo.get( "ApplicantName" ) );
        setApplicationDate( headerInfo.get( "ApplicationDate" ) );
        setUniversity( headerInfo.get( "UniversityName" ) );
        setInstitution( headerInfo.get( "InstitutionName" ) );
        setServiceType( headerInfo.get( "ServiceTypeName" ) );
        setSupervisorName( headerInfo.get( "SupervisorName" ) );

        boolean isRejected = reviewInfoDAO.hasApplicationBeenRejected( ( String ) getFromSession( SESSION_APPLICATIONID ) );

        if( isRejected ) {
            setApplicationRejectedFlag( "block" );
        }
        else {
            setApplicationRejectedFlag( "none" );
        }
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName( String applicantName ) {
        this.applicantName = applicantName;
    }

    public String getApplicationDate() {
        applicationDate = applicationDate.substring( 0, 10 );
        return applicationDate;
    }

    public void setApplicationDate( String applicationDate ) {
        this.applicationDate = applicationDate;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity( String university ) {
        this.university = university;
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

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName( String supervisorName ) {
        this.supervisorName = supervisorName;
    }

    public String getApplicationRejectedFlag() {
        return applicationRejectedFlag;
    }

    public void setApplicationRejectedFlag( String applicationRejectedFlag ) {
        this.applicationRejectedFlag = applicationRejectedFlag;
    }
}
