package com.moh.security;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.moh.common.AbstractBean;
import com.moh.common.GeneralSettings;
import com.moh.common.MainRQHelper;
import com.moh.data.dao.SecurityDAO;
import com.moh.data.dao.UserDAO;
import com.moh.utils.Logger;

public class LoginRQHelper extends AbstractBean {

    private String email = EMPTY_STRING;
    private String password = EMPTY_STRING;

    public LoginRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "LoginRQHelper" );
    }

    public String execute() {
        logger.debugMethod( "execute" );

        SecurityDAO securityDAO = new SecurityDAO();

        AESEncrypter aesEncrypter = new AESEncrypter();
        aesEncrypter.init();

        if( securityDAO.authenticate( getEmail(), aesEncrypter.convertToHexDec( password ) ) ) {

            //PAIRO
            GeneralSettings genSettings = new GeneralSettings();
            genSettings.initialize();
            
            addToSession( SESSION_EMAIL, getEmail() );
            addToSession( SESSION_FIRSTNAME, securityDAO.getFirstName() );
            addToSession( SESSION_LASTNAME, securityDAO.getLastName() );

            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMdd" );
            String dateString = sdf.format( new Date() );

            addToSession( SESSION_TODAYSDATE, dateString );

            Map<String, String> roles = securityDAO.authorize( getEmail() );

            if( securityDAO.isOneRoleOnly() ) {
                if( roles.containsKey( ROLE_ADMIN ) ) {
                    addToSession( SESSION_ROLEID, ROLE_ADMIN );
                    addToSession( SESSION_ROLEDESC, ( String ) roles.get( ROLE_ADMIN ) );
                }
                else {
                    if( roles.containsKey( ROLE_RESIDENT ) ) {
                        addToSession( SESSION_ROLEID, ROLE_RESIDENT );
                        addToSession( SESSION_ROLEDESC, ( String ) roles.get( ROLE_RESIDENT ) );

                        UserDAO userDAO = new UserDAO();
                        addToSession( SESSION_VISA_RESIDENT, userDAO.isVisaResident( getEmail() ) );
                    }
                    else {
                        if( roles.containsKey( ROLE_DIRECTOR ) ) {
                            addToSession( SESSION_ROLEID, ROLE_DIRECTOR );
                            addToSession( SESSION_ROLEDESC, ( String ) roles.get( ROLE_DIRECTOR ) );
                            addToSession( SESSION_UNIVERSITYID, securityDAO.getUniversityID( getEmail() ) );
                        }
                        else {
                            if( roles.containsKey( ROLE_DEAN ) ) {
                                addToSession( SESSION_ROLEID, ROLE_DEAN );
                                addToSession( SESSION_ROLEDESC, ( String ) roles.get( ROLE_DEAN ) );
                                addToSession( SESSION_UNIVERSITYID, securityDAO.getUniversityID( getEmail() ) );
                            }
                            else {
                                if( roles.containsKey( ROLE_SUPERVISOR ) ) {
                                    addToSession( SESSION_ROLEID, ROLE_SUPERVISOR );
                                    addToSession( SESSION_ROLEDESC, ( String ) roles.get( ROLE_SUPERVISOR ) );
                                    addToSession( SESSION_UNIVERSITYID, securityDAO.getInstitutionID( getEmail() ) );
                                }
                                else {
                                    if( roles.containsKey( ROLE_COMMITTEE ) ) {
                                        addToSession( SESSION_ROLEID, ROLE_COMMITTEE );
                                        addToSession( SESSION_ROLEDESC, ( String ) roles.get( ROLE_COMMITTEE ) );
                                        addToSession( SESSION_UNIVERSITYID, securityDAO.getUniversityID( getEmail() ) );
                                    }
                                    else {
                                        if( roles.containsKey( ROLE_ADMIN_COMMITTEE ) ) {
                                            addToSession( SESSION_ROLEID, ROLE_ADMIN_COMMITTEE );
                                            addToSession( SESSION_ROLEDESC, ( String ) roles.get( ROLE_COMMITTEE ) );
                                            //addToSession( SESSION_UNIVERSITYID, securityDAO.getUniversityID( getEmail() ) );
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                MainRQHelper mainRQHelper = new MainRQHelper();
                return mainRQHelper.view();
            }

            addToSession( SESSION_APPLICATIONID, EMPTY_STRING );
            addToSession( SESSION_ROLES, roles );

            logger.debugPage( "/jsp/multiRole.jsp" );
            return "multiRole";
        }
        else {
            addErrorMessage( "error_invalid_credentials");
            addExtraLoggingInfo( "Email=" + getEmail() );
            
            logger.debugPage( "/jsp/login.jsp" );
            return "login";
        }
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
        return password.trim();
    }

    public void setPassword( String password ) {
        this.password = password;
    }
}
