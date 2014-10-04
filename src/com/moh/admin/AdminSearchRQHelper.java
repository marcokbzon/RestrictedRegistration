package com.moh.admin;

import java.util.Map;

import com.moh.common.AbstractBean;
import com.moh.data.dao.UserDAO;
import com.moh.utils.Logger;

public class AdminSearchRQHelper extends AbstractBean {

    private String email;

    public AdminSearchRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "AdminSearchRQHelper" );
    }

    public String open() {
        logger.debugMethod( "open" );

        setEmail( EMPTY_STRING );

        logger.debugPage( "/jsp/adminSearchEmail.jsp" );
        return "adminSearchEmail";
    }

    public String submit() {
        logger.debugMethod( "submit" );

        if( email == null || email.trim().equals( EMPTY_STRING ) ) {
            addErrorMessage( "error_email_required" );

            logger.debugPage( "/jsp/adminSearchEmail.jsp" );
            return "adminSearchEmail";
        }

        UserDAO userDAO = new UserDAO();
        int numberOfRoles = userDAO.checkRoles( getEmail() );

        if( numberOfRoles == 0 ) {
            addErrorMessage( "error_email_doesnt_exist" );

            logger.debugPage( "/jsp/adminSearchEmail.jsp" );
            return "adminSearchEmail";
        }
        else {
            if( numberOfRoles > 1 ) {
                addErrorMessage( "error_profile_already_multirole" );

                logger.debugPage( "/jsp/adminSearchEmail.jsp" );
                return "adminSearchEmail";
            }
        }

        Map<String, String> reviewerInfo = userDAO.getReviewerInfo( getEmail() );

        addToSession( SESSION_DATAMAP_REVIEWER, reviewerInfo );

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
}
