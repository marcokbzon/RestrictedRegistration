package com.moh.review;

import java.util.Iterator;
import java.util.Map;

import javax.mail.MessagingException;

import com.moh.common.AbstractBean;
import com.moh.data.dao.FileDAO;
import com.moh.data.dao.ReviewCommitteeDAO;
import com.moh.data.dao.ReviewDeanDAO;
import com.moh.data.dao.StatusDAO;
import com.moh.mail.Notification;
import com.moh.utils.Logger;

public class AppPgmeDeanRQHelper extends AbstractBean {

    private String comments;
    private String[] documents;
    private String approverName;
    private boolean booleanApplicationConfirmation;
    private String applicationConfirmation;
    private boolean nextButton;
    private String email;
    private String committeeEmail;

    public AppPgmeDeanRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "AppPgmeDeanRQHelper" );

        String roleID = ( String ) getFromSession( SESSION_ROLEID );

        if( roleID.equals( ROLE_COMMITTEE ) || roleID.equals( ROLE_ADMIN_COMMITTEE ) ) {
            setNextButton( true );
        }
        else {
            setNextButton( false );
        }
    }

    public String open() {
        logger.debugMethod( "open" );

        // TAB control-----------------------
        String applicationID = ( String ) getFromSession( SESSION_APPLICATIONID );
        String roleID = ( String ) getFromSession( SESSION_ROLEID );

        boolean isTabEditable = false;

        if( roleID.equals( ROLE_DEAN ) ) {
            StatusDAO statusDAO = new StatusDAO();
            isTabEditable = statusDAO.isInformationEditable( applicationID, roleID );
        }

        if( isTabEditable ) {
            logger.debugPage( "/jsp/appReview04e.jsp" );
            return "appReview04e";
        }
        else {
            ReviewDeanDAO reviewDeanDAO = new ReviewDeanDAO();
            Map<String, Object> deanResponse = reviewDeanDAO.getDeanApproval( ( String ) getFromSession( SESSION_APPLICATIONID ) );

            String svComments = ( String ) deanResponse.get( "Comments" );
            if( svComments == null || svComments.trim().equals( EMPTY_STRING ) ) {
                svComments = "No comments have been entered";
            }
            setComments( svComments );

            setApproverName( ( String ) deanResponse.get( "ApproverName" ) );

            setBooleanApplicationConfirmation( ( Boolean ) deanResponse.get( "ApplicationConfirmation" ) );

            // Documents
            FileDAO fileDAO = new FileDAO();
            Iterator<Map.Entry<String, String>> documentIterator = ( Iterator<Map.Entry<String, String>> ) fileDAO.getDocumentLinks( applicationID, ROLE_DEAN ).entrySet().iterator();
            String linkString = EMPTY_STRING;
            addToSession( "DocsAvailable", "N" );

            while( documentIterator.hasNext() ) {
                Map.Entry<String, String> document = documentIterator.next();
                linkString = linkString + "<a href=\"/" + document.getKey() + "\">" + document.getValue() + "</a><br>";
                addToSession( "DocsAvailable", "Y" );
            }

            addToSession( "DocLinks", linkString );

            logger.debugPage( "/jsp/appReview04.jsp" );
            return "appReview04";
        }
    }

    public String submit() {
        logger.debugMethod( "submit" );
        boolean result = false;

        boolean bApplicationConfirmation = false;
        String status = STATUS_REJECTED;

        if( applicationConfirmation == null || applicationConfirmation.trim().equals( EMPTY_STRING ) ) {
            addErrorMessage( "error_application_confirmation_required" );
            
            logger.debugPage( "/jsp/appReview04e.jsp" );
            return "appReview04e";
        }
        else {
            if( applicationConfirmation.equals( "Y" ) ) {
                bApplicationConfirmation = true;
                status = STATUS_APPROVED;
            }
        }

        String applicationID = ( String ) getFromSession( SESSION_APPLICATIONID );

        ReviewDeanDAO reviewDeanDAO = new ReviewDeanDAO();
        result = reviewDeanDAO.addDeanApproval( applicationID, comments, bApplicationConfirmation );

        if( result ) {
            // Documents
            if( documents != null ) {
                FileDAO fileDAO = new FileDAO();

                setEmail( ( String ) getFromSession( SESSION_EMAIL ) );
                String fileDirectory = fileDAO.getFileDirectory( getEmail() );
                String roleID = ( String ) getFromSession( SESSION_ROLEID );

                String fileID = EMPTY_STRING;
                for( int i = 0 ; i < documents.length ; i++ ) {
                    fileID = documents[i];
                    fileDAO.addDocument( applicationID, roleID, fileDirectory, fileID );
                }
            }

            addInfoMessage( "info_review_info_added_ok" );

            String roleID = ( String ) getFromSession( SESSION_ROLEID );
            String firstName = ( String ) getFromSession( SESSION_FIRSTNAME );
            String lastName = ( String ) getFromSession( SESSION_LASTNAME );
            setEmail( ( String ) getFromSession( SESSION_EMAIL ) );

            StatusDAO statusDAO = new StatusDAO();
            statusDAO.addStatus( applicationID, roleID, status, firstName, lastName, getEmail() );
            statusDAO.updatePhase( applicationID, PHASE_INPROGRESS );

            try {
                ReviewCommitteeDAO reviewCommitteeDAO = new ReviewCommitteeDAO();

                //String committeEmail = reviewCommitteeDAO.getCommitteeEmail( applicationID );
                setCommitteeEmail( reviewCommitteeDAO.getAdminCommitteeEmail() );

                Notification notification = new Notification();

                if( committeeEmail == null || committeeEmail.trim().equals( EMPTY_STRING ) ) {
                    //notification.sendEmailToAdminMissingReviewer( applicationID, ROLE_COMMITTEE );
                    notification.sendEmailToAdminMissingReviewer( applicationID, ROLE_ADMIN_COMMITTEE );
                }
                else {
                    //notification.sendEmailToReviewers( committeeEmail );
                    notification.sendEmailToAdminCommittee( getCommitteeEmail(), applicationID );
                }
            }
            catch( MessagingException mex ) {
                addErrorMessage( "error_send_email_failed" );
            }
        }
        else {
            addErrorMessage( "error_db_update_failed" );
            
            logger.debugPage( "/jsp/appReview04e.jsp" );
            return "appReview04e";
        }

        logger.debugPage( "/jsp/appReviewList.jsp" );
        return "appReviewList";
    }

    public String getApplicationConfirmation() {
        return applicationConfirmation;
    }

    public void setApplicationConfirmation( String applicationConfirmation ) {
        this.applicationConfirmation = applicationConfirmation;
    }

    public boolean getBooleanApplicationConfirmation() {
        return booleanApplicationConfirmation;
    }

    public void setBooleanApplicationConfirmation( boolean booleanApplicationConfirmation ) {
        this.booleanApplicationConfirmation = booleanApplicationConfirmation;
    }

    public boolean getNextButton() {
        return nextButton;
    }

    public void setNextButton( boolean nextButton ) {
        this.nextButton = nextButton;
    }

    public String getComments() {
        return comments;
    }

    public void setComments( String comments ) {
        this.comments = comments;
    }

    public String[] getDocuments() {
        return documents;
    }

    public void setDocuments( String[] documents ) {
        this.documents = documents;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName( String approverName ) {
        this.approverName = approverName;
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

    private String getCommitteeEmail() {
        if ( committeeEmail == null || committeeEmail.trim().equals( EMPTY_STRING ) ) {
            return EMPTY_STRING;
        }
        else {
            return committeeEmail.trim().toLowerCase();
        }
    }

    private void setCommitteeEmail(String committeeEmail) {
        if ( committeeEmail == null || committeeEmail.trim().equals( EMPTY_STRING ) ) {
            this.committeeEmail = EMPTY_STRING;
        }
        else {
            this.committeeEmail = committeeEmail.trim().toLowerCase();
        }
    }
}
