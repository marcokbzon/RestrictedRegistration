package com.moh.review;

import java.util.Iterator;
import java.util.Map;

import javax.mail.MessagingException;

import com.moh.common.AbstractBean;
import com.moh.data.dao.FileDAO;
import com.moh.data.dao.ReviewDeanDAO;
import com.moh.data.dao.ReviewDirectorDAO;
import com.moh.data.dao.StatusDAO;
import com.moh.mail.Notification;
import com.moh.utils.Logger;

public class AppProgDirectorRQHelper extends AbstractBean {

    private boolean correctInformation;
    private boolean goodAcademic;
    private boolean attestEligibility;
    private boolean notifyCpso;
    private boolean provideInformation;
    private String comments;
    private String[] documents;
    private String approverName;
    private boolean nextButton;
    private boolean booleanApplicationConfirmation;
    private String applicationConfirmation;
    private String email;
    private String deanEmail;

    public AppProgDirectorRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "AppProgDirectorRQHelper" );

        String roleID = ( String ) getFromSession( SESSION_ROLEID );

        if( roleID.equals( ROLE_DEAN ) || roleID.equals( ROLE_COMMITTEE ) || roleID.equals( ROLE_ADMIN_COMMITTEE ) ) {
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

        if( roleID.equals( ROLE_DIRECTOR ) ) {
            StatusDAO statusDAO = new StatusDAO();
            isTabEditable = statusDAO.isInformationEditable( applicationID, roleID );
        }

        if( isTabEditable ) {
            logger.debugPage( "/jsp/appReview03e.jsp" );
            return "appReview03e";
        }
        else {
            ReviewDirectorDAO reviewDirectorDAO = new ReviewDirectorDAO();
            Map<String, Object> directorResponse = reviewDirectorDAO.getDirectorApproval( ( String ) getFromSession( SESSION_APPLICATIONID ) );

            setCorrectInformation( ( Boolean ) directorResponse.get( "CorrectInformation" ) );
            setGoodAcademic( ( Boolean ) directorResponse.get( "GoodAcademic" ) );
            setAttestEligibility( ( Boolean ) directorResponse.get( "AttestEligibility" ) );
            setNotifyCpso( ( Boolean ) directorResponse.get( "NotifyCpso" ) );
            setProvideInformation( ( Boolean ) directorResponse.get( "ProvideInformation" ) );
            setApproverName( ( String ) directorResponse.get( "ApproverName" ) );

            String svComments = ( String ) directorResponse.get( "Comments" );
            if( svComments == null || svComments.trim().equals( EMPTY_STRING ) ) {
                svComments = "No comments have been entered";
            }
            setComments( svComments );

            setBooleanApplicationConfirmation( ( Boolean ) directorResponse.get( "ApplicationConfirmation" ) );

            // Documents
            FileDAO fileDAO = new FileDAO();
            Iterator<Map.Entry<String, String>> documentIterator = ( Iterator<Map.Entry<String, String>> ) fileDAO.getDocumentLinks( applicationID, ROLE_DIRECTOR ).entrySet().iterator();
            String linkString = EMPTY_STRING;
            addToSession( "DocsAvailable", "N" );

            while( documentIterator.hasNext() ) {
                Map.Entry<String, String> document = documentIterator.next();
                linkString = linkString + "<a href=\"/" + document.getKey() + "\">" + document.getValue() + "</a><br>";
                addToSession( "DocsAvailable", "Y" );
            }

            addToSession( "DocLinks", linkString );

            logger.debugPage( "/jsp/appReview03.jsp" );
            return "appReview03";
        }
    }

    public String submit() {
        logger.debugMethod( "submit" );

        boolean result = false;

        boolean bApplicationConfirmation = false;
        String status = STATUS_REJECTED;

        if( applicationConfirmation == null || applicationConfirmation.trim().equals( EMPTY_STRING ) ) {
            addErrorMessage( "error_application_confirmation_required" );
            
            logger.debugPage( "/jsp/appReview03e.jsp" );
            return "appReview03e";
        }
        else {
            if( applicationConfirmation.equals( "Y" ) ) {
                bApplicationConfirmation = true;
                status = STATUS_APPROVED;

                if( correctInformation == false || goodAcademic == false || attestEligibility == false || notifyCpso == false || provideInformation == false ) {
                    addErrorMessage( "error_reviewer_attestation_is_incomplete" );
                    
                    logger.debugPage( "/jsp/appReview03e.jsp" );
                    return "appReview03e";
                }
            }
        }

        String applicationID = ( String ) getFromSession( SESSION_APPLICATIONID );

        ReviewDirectorDAO reviewDirectorDAO = new ReviewDirectorDAO();
        result = reviewDirectorDAO.addDirectorApproval( applicationID, correctInformation, goodAcademic, attestEligibility, notifyCpso, provideInformation, comments, bApplicationConfirmation );

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
                ReviewDeanDAO reviewDeanDAO = new ReviewDeanDAO();

                setDeanEmail( reviewDeanDAO.getDeanEmail( applicationID ) );

                Notification notification = new Notification();

                if( deanEmail == null || deanEmail.trim().equals( EMPTY_STRING ) ) {
                    notification.sendEmailToAdminMissingReviewer( applicationID, ROLE_DEAN );
                }
                else {
                    notification.sendEmailToReviewers( getDeanEmail(), applicationID );
                }
            }
            catch( MessagingException mex ) {
                addErrorMessage( "error_send_email_failed" );
            }
        }
        else {
            addErrorMessage( "error_db_update_failed" );
            
            logger.debugPage( "/jsp/appReview03e.jsp" );
            return "appReview03e";
        }

        logger.debugPage( "/jsp/appReviewList.jsp" );
        return "appReviewList";
    }

    public boolean getCorrectInformation() {
        return correctInformation;
    }

    public void setCorrectInformation( boolean correctInformation ) {
        this.correctInformation = correctInformation;
    }

    public boolean getGoodAcademic() {
        return goodAcademic;
    }

    public void setGoodAcademic( boolean goodAcademic ) {
        this.goodAcademic = goodAcademic;
    }

    public boolean getAttestEligibility() {
        return attestEligibility;
    }

    public void setAttestEligibility( boolean attestEligibility ) {
        this.attestEligibility = attestEligibility;
    }

    public boolean getNotifyCpso() {
        return notifyCpso;
    }

    public void setNotifyCpso( boolean notifyCpso ) {
        this.notifyCpso = notifyCpso;
    }

    public boolean getProvideInformation() {
        return provideInformation;
    }

    public void setProvideInformation( boolean provideInformation ) {
        this.provideInformation = provideInformation;
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

    private String getDeanEmail() {
        if ( deanEmail == null || deanEmail.trim().equals( EMPTY_STRING ) ) {
            return EMPTY_STRING;
        }
        else {
            return deanEmail.trim().toLowerCase();
        }
    }

    private void setDeanEmail(String deanEmail) {
        if ( deanEmail == null || deanEmail.trim().equals( EMPTY_STRING ) ) {
            this.deanEmail = EMPTY_STRING;
        }
        else {
            this.deanEmail = deanEmail.trim().toLowerCase();
        }
    }
}
