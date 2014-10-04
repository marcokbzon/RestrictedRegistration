package com.moh.admin;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ReviewerDAO;
import com.moh.data.dao.SecurityDAO;
import com.moh.data.dao.UserDAO;
import com.moh.security.AESEncrypter;
import com.moh.utils.Logger;

public class PersonalInfoRQHelper extends AbstractBean {

    private String firstName;
    private String lastName;
    private String email;
    private String roleID;
    private String password;
    private String passwordConfirm;
    private String gender;
    private String genderDesc;
    private String officePhone;
    private String pagerNumber;
    private String universityID;
    private String programID;
    private String institutionID;
    private boolean enableInstitution;
    private boolean enableUniversity;
    private boolean enableProgram;
    private boolean overrideIndicator;

    public PersonalInfoRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "PersonalInfoRQHelper" );
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

    public String open() {
        logger.debugMethod( "open" );

        setFirstName( EMPTY_STRING );
        setLastName( EMPTY_STRING );
        setEmail( EMPTY_STRING );
        setPassword( EMPTY_STRING );
        setPasswordConfirm( EMPTY_STRING );
        setGender( EMPTY_STRING );
        setOfficePhone( EMPTY_STRING );
        setPagerNumber( EMPTY_STRING );
        setInstitutionID( EMPTY_STRING );
        setUniversityID( EMPTY_STRING );
        setProgramID( EMPTY_STRING );
        setRoleID( EMPTY_STRING );
        setOverrideIndicator( false );

        setEnableInstitution( true );
        setEnableUniversity( true );
        setEnableProgram( true );

        logger.debugPage( "/jsp/adminCreateProfile.jsp" );
        return "adminCreateProfile";
    }

    public String create() {
        logger.debugMethod( "create" );
        boolean result = false;

        if( !getPassword().equals( getPasswordConfirm() ) ) {
            addErrorMessage( "error_nomatching_passwords" );
            
            logger.debugPage( "/jsp/adminCreateProfile.jsp" );
            return "adminCreateProfile";
        }

        SecurityDAO securityDAO = new SecurityDAO();

        if( securityDAO.emailExists( getEmail() ) ) {
            addErrorMessage( "error_email_already_exists" );
            
            logger.debugPage( "/jsp/adminCreateProfile.jsp" );
            return "adminCreateProfile";
        }
        
        if ( getRoleID().equals( "000" ) ) {
            addErrorMessage( "error_role_required" );
            
            logger.debugPage( "/jsp/adminCreateProfile.jsp" );
            return "adminCreateProfile";
        }

        AESEncrypter aesEncrypter = new AESEncrypter();
        aesEncrypter.init();

        UserDAO userDAO = new UserDAO();
        result = userDAO.addProfile( getEmail(), aesEncrypter.convertToHexDec( password ), firstName, lastName, gender, ( ( short ) 0 ), EMPTY_STRING, getRoleID(), EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, "CAN", "ON", EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, officePhone, EMPTY_STRING, pagerNumber );

        if( result ) {
            //addInfoMessage( "info_profile_created_ok" );
        }
        else {
            addErrorMessage( "error_db_insert_failed" );
            
            logger.debugPage( "/jsp/adminCreateProfile.jsp" );
            return "adminCreateProfile";
        }

        ReviewerDAO reviewerDAO = new ReviewerDAO();

        if( !( institutionID == null || institutionID.trim().equals(EMPTY_STRING) ) ) {
            if( institutionID.length() == ( COL_LENGTH_INSTITUTIONID + COL_SORT_LENGTH ) ) {
                institutionID = institutionID.substring( COL_SORT_LENGTH );
            }
        }
        if( !( universityID == null || universityID.trim().equals(EMPTY_STRING) ) ) {
            if( universityID.length() == ( COL_LENGTH_UNIVERSITYID + COL_SORT_LENGTH ) ) {
                universityID = universityID.substring( COL_SORT_LENGTH );
            }
        }
        if( !( programID == null || programID.trim().equals(EMPTY_STRING) ) ) {
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
                    // Should never execute as error_email_already_exists was previously returned
                    errorMsg = "error_reviewer_univ_already_assigned";
                }
                else {
                    if( reviewerDAO.universityProgramExists( universityID, programID ) ) {
                        if( overrideIndicator ) {
                            /* Scenario:
                             * Another reviewer has already been assigned the same University-Program
                             * If override is set to true, then set existing info to Active=0, and
                             * assign to the new Reviewer this University-Program with a new UnivProgID
                             *        	        		 *
                             * Table: UnivProg
                             *      UniversityID    ProgramID    Email     UnivProgID    Active
                             *      00003           00005        a@u.com   0000000001    0         <--- OLD
                             *      00003           00005        b@u.com   0000000002    1         <--- NEW
                             */

                            // First insert new Reviewer, then update previous one
                            boolean resultINS = reviewerDAO.addUniversityProgramInfo( universityID, programID, getEmail() );
                            boolean resultUPD = reviewerDAO.deActivateUnivProgInfo( reviewerDAO.getPreviousUnivProgID() );

                            if( resultINS & resultUPD ) {
                                result = true;
                            }
                            infoMsg = "info_reviewer_assigned_ok";
                        }
                        else {
                            errorMsg = "error_univprog_already_assigned";
                        }
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
                        // Should never execute as error_email_already_exists was previously returned
                        errorMsg = "error_reviewer_univ_already_assigned";
                    }
                    else {
                        if( reviewerDAO.universityAlreadyAssigned( universityID, getRoleID() ) ) {
                            if( overrideIndicator ) {
                                /* Scenario:
                                 * Another reviewer has already been assigned the same University
                                 * If override is set to true, then set existing info to Active=0, and
                                 * assign to the new Reviewer this University
                                 *        	        		 *
                                 * Table: UnivReviewer
                                 *      UniversityID    Email     Active
                                 *      00003           a@u.com   0         <--- OLD
                                 *      00003           b@u.com   1         <--- NEW
                                 */

                                // First insert new Reviewer, then update previous one
                                boolean resultINS = reviewerDAO.addUniversityReviewerInfo( universityID, getEmail() );
                                boolean resultUPD = reviewerDAO.deActivateUnivReviewer( universityID, reviewerDAO.getPreviousReviewerEmail() );

                                if( resultINS & resultUPD ) {
                                    result = true;
                                }
                                infoMsg = "info_reviewer_assigned_ok";
                            }
                            else {
                                errorMsg = "error_univ_already_assigned";
                            }
                        }
                        else {
                            result = reviewerDAO.addUniversityReviewerInfo( universityID, getEmail() );
                            infoMsg = "info_reviewer_assigned_ok";
                        }
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
        }
        else {
            userDAO.removeProfile( getEmail() );

            if( errorMsg.equals( EMPTY_STRING ) ) {
                addErrorMessage( "error_db_insert_failed" );
            }
            else {
                addErrorMessage( errorMsg );
            }
        }

        logger.debugPage( "/jsp/adminCreateProfile.jsp" );
        return "adminCreateProfile";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm( String passwordConfirm ) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getGender() {
        return gender;
    }

    public void setGender( String gender ) {
        this.gender = gender;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone( String officePhone ) {
        this.officePhone = officePhone;
    }

    public String getPagerNumber() {
        return pagerNumber;
    }

    public void setPagerNumber( String pagerNumber ) {
        this.pagerNumber = pagerNumber;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID( String roleID ) {
        this.roleID = roleID;
    }

    public String getGenderDesc() {
        return genderDesc;
    }

    public void setGenderDesc( String genderDesc ) {
        this.genderDesc = genderDesc;
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

    public boolean getOverrideIndicator() {
        return overrideIndicator;
    }

    public void setOverrideIndicator( boolean overrideIndicator ) {
        this.overrideIndicator = overrideIndicator;
    }
}
