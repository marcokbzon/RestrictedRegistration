package com.moh.security;

import javax.mail.MessagingException;

import com.moh.common.AbstractBean;
import com.moh.data.dao.SecurityDAO;
import com.moh.mail.Notification;
import com.moh.utils.Logger;

public class ForgotPasswordRQHelper extends AbstractBean {

    private String email = EMPTY_STRING;
    // private String yearOfBirth = EMPTY_STRING;  *** R-140521 ***
    // private String postalCode = EMPTY_STRING;  *** R-140521 ***

    public ForgotPasswordRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ForgotPasswordRQHelper" );
    }

    public String execute() {
        logger.debugMethod( "execute" );
        SecurityDAO securityDAO = new SecurityDAO();
        // short iYoB = 0;  *** R-140521 ***

        /*   *** R-140521 ***
        try {
            iYoB = Short.parseShort( yearOfBirth );
        }
        catch( NumberFormatException nfex ) {
            addErrorMessage( "error_yob_invalid" );
            
            logger.debugPage( "/jsp/forgotPassword.jsp" );
            return "forgotPassword";
        }
        */

        // if( !securityDAO.checkInputValues( getEmail(), iYoB, postalCode ) ) {  *** R-140521 ***
        if( !securityDAO.checkInputValues( getEmail() ) ) {
            addErrorMessage( "error_nomatching_recs" );
            // addExtraLoggingInfo("Email=" + getEmail() + ", YoB=" + iYoB + ", PostalCode=" + postalCode);  *** R-140521 ***
            addExtraLoggingInfo( "Email=" + getEmail() );
            
            logger.debugPage( "/jsp/forgotPassword.jsp" );
            return "forgotPassword";
        }

        try {
            Notification notification = new Notification();

            notification.sendEmailForgotPassword( getEmail(), securityDAO.getFirstName(), securityDAO.getLastName(), securityDAO.getPassword() );

            addInfoMessage( "info_email_with_password_sent" );
            
            /* *** R-140526 ***
            logger.debugPage( "/jsp/login.jsp" );
            return "login";
            */
            
            logger.debugPage( "/jsp/forgotPassword.jsp" );
            return "forgotPassword";
        }
        catch( MessagingException mex ) {
            addErrorMessage( "error_send_email_failed" );
            //logger.forceComment( "INVALID CREDENTIALS FOR ACCOUNT: " + email );
            
            logger.debugPage( "/jsp/forgotPassword.jsp" );
            return "forgotPassword";
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

    /*   *** R-140521 ***
    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth( String yearOfBirth ) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode( String postalCode ) {
        this.postalCode = postalCode;
    }
    */
}
