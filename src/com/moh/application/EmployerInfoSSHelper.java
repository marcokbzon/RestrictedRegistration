package com.moh.application;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ApplicationDAO;
import com.moh.data.dao.StatusDAO;
import com.moh.data.dao.UserDAO;
import com.moh.utils.EmailValidator;
import com.moh.utils.Logger;

public class EmployerInfoSSHelper extends AbstractBean {

    private String lhinID;
    private String institutionID;
    private String institutionName;
    private String locationID;
    private String locationName;
    private String serviceTypeID;
    private String serviceTypeName;
    private String supervisorName;
    private String supervisorEmail;
    private String supervisorPhone;
    private String supervisorExtension;
    private String applicationID;
    private String email;

    public EmployerInfoSSHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "EmployerInfoSSHelper" );
    }

    public String view() {
        populate();

        logger.debugPage( "/jsp/appForm04.jsp" );
        return "applicationForm04";
    }

    public void populate() {
        logger.debugMethod( "populate" );

        if( getApplicationID() == null || getApplicationID().trim().equals(EMPTY_STRING) ) {
            setLhinID( "00" );
            setInstitutionID( "0000" );
            setLocationID( "000000" );
            setServiceTypeID( "000" );

            setSupervisorName( EMPTY_STRING );
            setSupervisorEmail( EMPTY_STRING );
            setSupervisorPhone( EMPTY_STRING );
            setSupervisorExtension( EMPTY_STRING );
        }
    }

    public String save() {
        logger.debugMethod( "save" );
        boolean result = false;

        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );

        ApplicationDAO applicationDAO = new ApplicationDAO();

        setApplicationID( ( String ) getFromSession( SESSION_APPLICATIONID ) );

        boolean errorOcurred = false;

        if( lhinID.equals( "00" ) ) {
            addErrorMessage( "error_lhin_required" );
            errorOcurred = true;
        }
        if( institutionID.equals( "0000" ) ) {
            addErrorMessage( "error_institution_required" );
            errorOcurred = true;
        }
        if( serviceTypeID.equals( "000" ) ) {
            addErrorMessage( "error_servicetype_required" );
            errorOcurred = true;
        }
        if( supervisorName == null || supervisorName.trim().equals( EMPTY_STRING ) ) {
            addErrorMessage( "error_supervisor_name_required" );
            errorOcurred = true;
        }
        if( supervisorEmail == null || supervisorEmail.trim().equals( EMPTY_STRING ) ) {
            addErrorMessage( "error_supervisor_email_required" );
            errorOcurred = true;
        }
        else {
            EmailValidator emailValidator = new EmailValidator();
            if ( ! emailValidator.validate( getSupervisorEmail() ) ) {
                addErrorMessage( "error_email_invalid" );
                errorOcurred = true;
            }
        }
        if( supervisorPhone == null || supervisorPhone.trim().equals( EMPTY_STRING ) ) {
            addErrorMessage( "error_supervisor_phone_required" );
            errorOcurred = true;
        }

        if( errorOcurred ) {
            logger.debugPage( "/jsp/appForm04.jsp" );
            return "applicationForm04";
        }

        UserDAO userDAO = new UserDAO();
        if( !userDAO.universityProgramIdExists( getEmail() ) ) {
            addErrorMessage( "error_university_program_required" );
            
            logger.debugPage( "/jsp/appForm04.jsp" );
            return "applicationForm04";
        }

        if( applicationID == null || applicationID.trim().equals(EMPTY_STRING) ) {
            result = applicationDAO.addApplication( getEmail(), EMPTY_STRING, institutionID, EMPTY_STRING, serviceTypeID, supervisorName, getSupervisorEmail(), supervisorPhone, supervisorExtension );

            if( result ) {
                String roleID = ( String ) getFromSession( SESSION_ROLEID );
                String firstName = ( String ) getFromSession( SESSION_FIRSTNAME );
                String lastName = ( String ) getFromSession( SESSION_LASTNAME );
                setApplicationID( ( String ) getFromSession( SESSION_APPLICATIONID ) );

                StatusDAO statusDAO = new StatusDAO();
                statusDAO.addStatus( applicationID, roleID, STATUS_SAVED, firstName, lastName, getEmail() );
            }
            else {
                addErrorMessage( "error_db_save_failed" );
                
                logger.debugPage( "/jsp/appForm04.jsp" );
                return "applicationForm04";
            }
        }
        else {
            result = applicationDAO.editApplication( applicationID, institutionID, locationID, serviceTypeID, supervisorName, getSupervisorEmail(), supervisorPhone, supervisorExtension );
        }

        logger.debugVariable( "applicationID", applicationID );

        if( result ) {
            addInfoMessage( "info_employer_saved_ok" );
        }
        else {
            addErrorMessage( "error_db_save_failed" );
        }

        logger.debugPage( "/jsp/appForm04.jsp" );
        return "applicationForm04";
    }

    public void changeLHIN( ValueChangeEvent event ) {
        String newLHIN_ID = EMPTY_STRING;
		
        if ( event != null ) {
            try {
                Object eventNewValue = event.getNewValue();

                if ( eventNewValue != null ) {
                    newLHIN_ID = event.getNewValue().toString();
                }
            }
            catch ( NullPointerException npex ) {
                logger.exception( npex );
            }
        }

        if( newLHIN_ID.trim().length() == ( COL_LENGTH_LHINID + COL_SORT_LENGTH ) ) {
            newLHIN_ID = newLHIN_ID.substring( COL_SORT_LENGTH );
        }

        setLhinID( newLHIN_ID );
        setInstitutionID( "0000" );
        setLocationID( "000000" );
        setServiceTypeID( "000" );
        
        addToSession( SESSION_LHINID, newLHIN_ID );
        addToSession( SESSION_INSTITUTIONID, EMPTY_STRING );
        
        FacesContext.getCurrentInstance().renderResponse();
    }
    
    public void changeInstitution( ValueChangeEvent event ) {
        String newInstitutionID = EMPTY_STRING;
		
        if ( event != null ) {
            try {
                Object eventNewValue = event.getNewValue();

                if ( eventNewValue != null ) {
                    newInstitutionID = event.getNewValue().toString();
                }
            }
            catch ( NullPointerException npex ) {
                logger.exception( npex );
            }
        }

        if( newInstitutionID.trim().length() == ( COL_LENGTH_INSTITUTIONID + COL_SORT_LENGTH ) ) {
            newInstitutionID = newInstitutionID.substring( COL_SORT_LENGTH );
        }

        //setLhinID( "00" );
        setInstitutionID( newInstitutionID );
        setLocationID( "000000" );
        setServiceTypeID( "000" );

        addToSession( SESSION_INSTITUTIONID, newInstitutionID );

        FacesContext.getCurrentInstance().renderResponse();
    }

    public String getInstitutionID() {
        return institutionID;
    }

    public void setInstitutionID( String institutionID ) {
        this.institutionID = institutionID;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName( String institutionName ) {
        this.institutionName = institutionName;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID( String locationID ) {
        this.locationID = locationID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName( String locationName ) {
        this.locationName = locationName;
    }

    public String getServiceTypeID() {
        return serviceTypeID;
    }

    public void setServiceTypeID( String serviceTypeID ) {
        this.serviceTypeID = serviceTypeID;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName( String serviceTypeName ) {
        this.serviceTypeName = serviceTypeName;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName( String supervisorName ) {
        this.supervisorName = supervisorName;
    }

    public String getSupervisorEmail() {
        if ( supervisorEmail == null || supervisorEmail.trim().equals( EMPTY_STRING ) ) {
            return EMPTY_STRING;
        }
        else {
            return supervisorEmail.trim().toLowerCase();
        }
    }

    public void setSupervisorEmail( String supervisorEmail ) {
        if ( supervisorEmail == null || supervisorEmail.trim().equals( EMPTY_STRING ) ) {
            this.supervisorEmail = EMPTY_STRING;
        }
        else {
            this.supervisorEmail = supervisorEmail.trim().toLowerCase();
        }
    }

    public String getSupervisorPhone() {
        return supervisorPhone;
    }

    public void setSupervisorPhone( String supervisorPhone ) {
        this.supervisorPhone = supervisorPhone;
    }

    public String getLhinID() {
        return lhinID;
    }

    public void setLhinID( String lhinID ) {
        this.lhinID = lhinID;
    }

    public String getApplicationID() {
        return ( String ) getFromSession( SESSION_APPLICATIONID );
    }

    public void setApplicationID( String applicationID ) {
        this.applicationID = applicationID;
    }

    public String getSupervisorExtension() {
        return supervisorExtension;
    }

    public void setSupervisorExtension( String supervisorExtension ) {
        this.supervisorExtension = supervisorExtension;
    }
    
    public String getEmail() {
        if ( email == null || email.trim().equals( EMPTY_STRING ) ) {
            return EMPTY_STRING;
        }
        else {
            return email.trim().toLowerCase();
        }
    }
    
    public void setEmail( String email ) {
        if ( email == null || email.trim().equals( EMPTY_STRING ) ) {
            this.email = EMPTY_STRING;
        }
        else {
            this.email = email.trim().toLowerCase();
        }
    }
}
