package com.moh.mail;

import javax.mail.MessagingException;

import com.moh.common.Constants;
import com.moh.data.dao.UserDAO;
import com.moh.utils.Logger;

public class Notification implements Constants {

    private Logger logger;
    private String email;

    public Notification() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "Notification" );
    }

    public void sendEmailToAdminCommittee( String email, String applicationID ) throws MessagingException {
        logger.debugMethod( "sendEmailToAdminCommittee" );
        setEmail( email );

        EmailService emailService = new EmailService();
        emailService.setMessageRecepient( getEmail() );
        emailService.setMessageSubject( "Restricted Registration Notification" );
        emailService.setMessageText(
                "This message is from the Restricted Registration Office\n\n"
                + "There is a Restricted Registration application that requires your attention, please log-in to the\n"
                + "Restricted Registration Application website ( http://www.rrapp.ca ) to view the application.\n\n"
                + "Submitted Application ID is: " + applicationID + "\n\n"
                + "Kindly handle this request within the next 5 days.\n\n"
                + "Sincerely,\n"
                + "Restricted Registration Program Office\n"
                + "416-597-3650\n\n" );
        emailService.sendMessage();
    }

    public void sendEmailToReviewers( String email, String applicationID ) throws MessagingException {
        logger.debugMethod( "sendEmailToReviewers" );
        setEmail( email );

        UserDAO userDAO = new UserDAO();

        EmailService emailService = new EmailService();
        emailService.setMessageRecepient( getEmail() );
        emailService.setMessageSubject( "Restricted Registration Notification" );
        emailService.setMessageText(
                "This message is from the Restricted Registration Office\n\n"
                + "There is a Restricted Registration application from " + userDAO.getResidentName( applicationID ) + " that requires your attention, \n"
                + "please log-in to the Restricted Registration Application website ( http://www.rrapp.ca ) to view the application.\n\n"
                + "If you do not have a username and password, please contact the Program\n"
                + "Manager, Susan Adair ( mailto:info@restrictedregistrationontario.ca ) to request access.\n\n"
                + "We would appreciate your assistance by handling this request within the next 5 days.\n\n"
                + "Sincerely,\n"
                + "Restricted Registration Program Office\n"
                + "416-597-3650\n\n" );
        emailService.sendMessage();
    }

    public void sendEmailToAdminMissingReviewer( String applicationID, String roleID ) throws MessagingException {
        logger.debugMethod( "sendEmailToAdminMissingReviewer" );

        String roleName = EMPTY_STRING;

        if( roleID.equals( ROLE_SUPERVISOR ) ) {
            roleName = "Institution Supervisor";
        }
        else {
            if( roleID.equals( ROLE_DIRECTOR ) ) {
                roleName = "Program Director";
            }
            else {
                if( roleID.equals( ROLE_DEAN ) ) {
                    roleName = "PGME Dean";
                }
                else {
                    if( roleID.equals( ROLE_COMMITTEE ) ) {
                        roleName = "Committee Member";
                    }
                    else {
                        if( roleID.equals( ROLE_ADMIN_COMMITTEE ) ) {
                            roleName = "Admin Committee Member";
                        }
                    }
                }
            }
        }

        EmailService emailService = new EmailService();
        emailService.setMessageRecepient( WEBMASTER_EMAIL );
        emailService.setMessageSubject( "Missing Reviewer Information" );
        emailService.setMessageText(
                "Missing Reviewer Information:\n\n"
                + "An email notification was not sent to the " + roleName + "\n"
                + "related to the application  " + applicationID + "\n\n"
                + "Please make sure the information is entered promptly, and kindly\n"
                + "send an email to the missing reviewer.\n\n"
                + "Regards,\n"
                + "Restricted Registration Program Office\n"
                + "416-597-3650\n\n" );
        emailService.sendMessage();
    }

    public void sendEmailForgotPassword( String email, String firstName, String lastName, String password ) throws MessagingException {
        logger.debugMethod( "sendEmailForgotPassword" );
        setEmail( email );

        String userName = firstName + " " + lastName;

        EmailService emailService = new EmailService();
        emailService.setMessageRecepient( getEmail() );
        emailService.setMessageSubject( "Restricted Registration Inquiry" );
        emailService.setMessageText(
                "Hi " + userName + ",\n\n"
                + "Thank you for using the Restricted Registration website.\n"
                + "This is the information you requested:\n\n"
                + "Your password is: " + password + "\n\n"
                + "If you have any questions, please contact us at mailto:" + WEBMASTER_EMAIL );
        emailService.sendMessage();
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
