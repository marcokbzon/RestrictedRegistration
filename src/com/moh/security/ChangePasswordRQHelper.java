package com.moh.security;

import com.moh.common.AbstractBean;
import com.moh.data.dao.SecurityDAO;
import com.moh.utils.Logger;

public class ChangePasswordRQHelper extends AbstractBean {

    private String oldPassword;
    private String newPassword;
    private String newPasswordConfirm;
    private String email;

    public ChangePasswordRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ChangePasswordRQHelper" );
    }

    public String execute() {
        boolean result = false;
        String mainPage = "mainReviewer";

        logger.debugMethod( "execute" );
        SecurityDAO securityDAO = new SecurityDAO();

        if( !newPassword.equals( newPasswordConfirm ) ) {
            addErrorMessage( "error_nomatching_passwords" );
            
            logger.debugPage( "/jsp/changePassword.jsp" );
            return "changePassword";
        }
        else {
            setEmail( ( String ) getFromSession( SESSION_EMAIL ) );

            AESEncrypter aesEncrypter = new AESEncrypter();
            aesEncrypter.init();

            result = securityDAO.authenticate( getEmail(), aesEncrypter.convertToHexDec( oldPassword ) );
            if( !result ) {
                addErrorMessage( "error_invalid_oldpassword" );
                
                logger.debugPage( "/jsp/changePassword.jsp" );
                return "changePassword";
            }

            result = securityDAO.changePassword( getEmail(), aesEncrypter.convertToHexDec( newPassword ) );

            if( result ) {
                addInfoMessage( "info_change_password_ok" );
            }
            else {
                addErrorMessage( "error_db_update_failed" );
            }
        }

        String roleID = ( String ) getFromSession( SESSION_ROLEID );

        if( roleID.equals( ROLE_ADMIN ) ) {
            mainPage = "mainAdmin";
        }
        else {
            if( roleID.equals( ROLE_RESIDENT ) ) {
                mainPage = "mainResident";
            }
        }

        return mainPage;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword( String oldPassword ) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword( String newPassword ) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

    public void setNewPasswordConfirm( String newPasswordConfirm ) {
        this.newPasswordConfirm = newPasswordConfirm;
    }
    
    private String getEmail() {
        if ( email == null || email.trim().equals( EMPTY_STRING ) ) {
            return EMPTY_STRING;
        }
        else {
            return email.trim().toLowerCase();
        }
    }

    private void setEmail(String email) {
        if ( email == null || email.trim().equals( EMPTY_STRING ) ) {
            this.email = EMPTY_STRING;
        }
        else {
            this.email = email.trim().toLowerCase();
        }
    }
}
