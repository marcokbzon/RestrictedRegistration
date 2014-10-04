package com.moh.admin;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ReviewerDAO;
import com.moh.data.dao.UserDAO;
import com.moh.utils.Logger;

public class PersonalInfoMultiRoleRQHelper extends AbstractBean {

    private String fullname;
    private String email;
    private String roleDescription;
    private String currentRoleID;
    private String roleID;
    private String universityID;
    private String programID;
    private String institutionID;
    private boolean enableInstitution;
    private boolean enableUniversity;
    private boolean enableProgram;

    public PersonalInfoMultiRoleRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "PersonalInfoMultiRoleRQHelper" );

        if( email == null || email.equals( EMPTY_STRING ) ) {
            open();
        }
    }

    public void changeRole( ValueChangeEvent event ) {
        String newRoleID = EMPTY_STRING;
		
        if ( event != null ) {
            try {
                Object eventNewValue = event.getNewValue();

                if ( eventNewValue != null ) {
                    newRoleID = event.getNewValue().toString();
                }
            }
            catch ( NullPointerException npex ) {
                logger.exception( npex );
            }
        }

        setEnableInstitution( true );
        setEnableUniversity( true );
        setEnableProgram( true );

        if( newRoleID.equals( ROLE_SUPERVISOR ) ) {
            setEnableInstitution( false );
        }
        else {
            if( newRoleID.equals( ROLE_DIRECTOR ) ) {
                setEnableUniversity( false );
                setEnableProgram( false );
            }
            else {
                if( newRoleID.equals( ROLE_DEAN ) ) {
                    setEnableUniversity( false );
                }
                else {
                    if( newRoleID.equals( ROLE_COMMITTEE ) ) {
                        setEnableUniversity( false );
                    }
                }
            }
        }

        FacesContext.getCurrentInstance().renderResponse();
    }

    @SuppressWarnings( "unchecked" )
    public String open() {
        logger.debugMethod( "open" );

        Map<String, String> reviewerInfo = ( Map<String, String> ) getFromSession( SESSION_DATAMAP_REVIEWER );

        setFullname( reviewerInfo.get( "FullName" ) );
        setEmail( reviewerInfo.get( "Email" ) );
        setRoleDescription( reviewerInfo.get( "RoleDescription" ) );
        currentRoleID = reviewerInfo.get( "RoleID" );

        setInstitutionID( EMPTY_STRING );
        setUniversityID( EMPTY_STRING );
        setProgramID( EMPTY_STRING );
        setRoleID( EMPTY_STRING );

        setEnableInstitution( true );
        setEnableUniversity( true );
        setEnableProgram( true );

        logger.debugPage( "/jsp/adminCreateMultiRole.jsp" );
        return "adminCreateMultiRole";
    }

    public String create() {
        logger.debugMethod( "create" );
        boolean result = false;

        if( roleID.equals( currentRoleID ) ) {
            addErrorMessage( "error_role_already_assigned" );

            logger.debugPage( "/jsp/adminCreateMultiRole.jsp" );
            return "adminCreateMultiRole";
        }

        ReviewerDAO reviewerDAO = new ReviewerDAO();

        if( !( institutionID == null || institutionID.trim().equals(EMPTY_STRING) ) ) {
            if( institutionID.length() == ( COL_LENGTH_INSTITUTIONID + COL_SORT_LENGTH ) ) {
                institutionID = institutionID.substring( COL_SORT_LENGTH );
            }
        }
        if( !( universityID == null  || universityID.trim().equals(EMPTY_STRING) ) ) {
            if( universityID.length() == ( COL_LENGTH_UNIVERSITYID + COL_SORT_LENGTH ) ) {
                universityID = universityID.substring( COL_SORT_LENGTH );
            }
        }
        if( !( programID == null  || programID.trim().equals(EMPTY_STRING) ) ) {
            if( programID.length() == ( COL_LENGTH_PROGRAMID + COL_SORT_LENGTH ) ) {
                programID = programID.substring( COL_SORT_LENGTH );
            }
        }

        String infoMsg = EMPTY_STRING;
        String errorMsg = EMPTY_STRING;
        String warnMsg = EMPTY_STRING;
        result = false;

        if( getRoleID().equals( ROLE_SUPERVISOR ) ) {
            if( reviewerDAO.institutionReviewerExists( getEmail() ) ) {
                warnMsg = "error_reviewer_inst_already_assigned";
            }
            else {
                result = reviewerDAO.addInsttutionReviewerInfo( institutionID, getEmail() );
                infoMsg = "info_reviewer_assigned_ok";
            }
        }
        else {
            if( getRoleID().equals( ROLE_DIRECTOR ) ) {
                if( reviewerDAO.universityProgramExists( getEmail(), EMPTY_STRING ) ) {
                    errorMsg = "error_reviewer_univ_already_assigned";
                }
                else {
                    if( reviewerDAO.universityProgramExists( universityID, programID ) ) {
                        errorMsg = "error_univprog_already_assigned";
                    }
                    else {
                        result = reviewerDAO.addUniversityProgramInfo( universityID, programID, getEmail() );
                        infoMsg = "info_reviewer_assigned_ok";
                    }
                }
            }
            else {
                if( getRoleID().equals( ROLE_DEAN ) || getRoleID().equals( ROLE_COMMITTEE ) ) {
                    if( reviewerDAO.universityReviewerExists( getEmail() ) ) {
                        errorMsg = "error_reviewer_univ_already_assigned";
                    }
                    else {
                        result = reviewerDAO.addUniversityReviewerInfo( universityID, getEmail() );
                        infoMsg = "info_reviewer_assigned_ok";
                    }
                }
                else {
                    if( getRoleID().equals( ROLE_ADMIN ) ) {
                        result = true;
                        infoMsg = "info_reviewer_assigned_ok";
                    }
                }
            }
        }

        if( result ) {
            if( !infoMsg.equals( EMPTY_STRING ) ) {
                addInfoMessage( infoMsg );
            }
            if( !warnMsg.equals( EMPTY_STRING ) ) {
                addWarningMessage( warnMsg );
            }

            UserDAO userDAO = new UserDAO();
            userDAO.addRole( getEmail(), roleID );
        }
        else {
            if( errorMsg.equals( EMPTY_STRING ) ) {
                addErrorMessage( "error_db_insert_failed" );
            }
            else {
                addErrorMessage( errorMsg );
            }
        }

        logger.debugPage( "/jsp/adminCreateMultiRole.jsp" );
        return "adminCreateMultiRole";
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

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID( String roleID ) {
        this.roleID = roleID;
    }

    public boolean getEnableInstitution() {
        return enableInstitution;
    }

    public void setEnableInstitution( boolean enableInstitution ) {
        this.enableInstitution = enableInstitution;
    }

    public boolean getEnableUniversity() {
        return enableUniversity;
    }

    public void setEnableUniversity( boolean enableUniversity ) {
        this.enableUniversity = enableUniversity;
    }

    public boolean getEnableProgram() {
        return enableProgram;
    }

    public void setEnableProgram( boolean enableProgram ) {
        this.enableProgram = enableProgram;
    }

    public String getUniversityID() {
        return universityID;
    }

    public void setUniversityID( String universityID ) {
        this.universityID = universityID;
    }

    public String getProgramID() {
        return programID;
    }

    public void setProgramID( String programID ) {
        this.programID = programID;
    }

    public String getInstitutionID() {
        return institutionID;
    }

    public void setInstitutionID( String institutionID ) {
        this.institutionID = institutionID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname( String fullname ) {
        this.fullname = fullname;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription( String roleDescription ) {
        this.roleDescription = roleDescription;
    }
}
