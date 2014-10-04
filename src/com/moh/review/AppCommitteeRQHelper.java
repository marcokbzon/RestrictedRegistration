package com.moh.review;

import java.util.Iterator;
import java.util.Map;

import com.moh.common.AbstractBean;
import com.moh.data.dao.FileDAO;
import com.moh.data.dao.ReviewCommitteeDAO;
import com.moh.data.dao.StatusDAO;
import com.moh.utils.Logger;

public class AppCommitteeRQHelper extends AbstractBean {

    private boolean attestRequirements;
    private String comments;
    private String[] documents;
    private String approverName;
    private boolean booleanApplicationConfirmation;
    private String applicationConfirmation;
    private String email;

    public AppCommitteeRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "AppCommitteeRQHelper" );
    }

    public String open() {
        logger.debugMethod( "open" );

        // TAB control-----------------------
        String applicationID = ( String ) getFromSession( SESSION_APPLICATIONID );
        String roleID = ( String ) getFromSession( SESSION_ROLEID );

        boolean isTabEditable = false;

        if( roleID.equals( ROLE_COMMITTEE ) || roleID.equals( ROLE_ADMIN_COMMITTEE ) ) {
            roleID = ROLE_COMMITTEE;
            StatusDAO statusDAO = new StatusDAO();
            isTabEditable = statusDAO.isInformationEditable( applicationID, roleID );
        }

        if( isTabEditable ) {
            logger.debugPage( "/jsp/appReview05e.jsp" );
            return "appReview05e";
        }
        else {
            ReviewCommitteeDAO reviewCommitteeDAO = new ReviewCommitteeDAO();
            Map<String, Object> committeeResponse = reviewCommitteeDAO.getCommitteeApproval( ( String ) getFromSession( SESSION_APPLICATIONID ) );

            setAttestRequirements( ( Boolean ) committeeResponse.get( "AttestRequirements" ) );

            String svComments = ( String ) committeeResponse.get( "Comments" );
            if( svComments == null || svComments.trim().equals( EMPTY_STRING ) ) {
                svComments = "No comments have been entered";
            }
            setComments( svComments );

            setApproverName( ( String ) committeeResponse.get( "ApproverName" ) );

            setBooleanApplicationConfirmation( ( Boolean ) committeeResponse.get( "ApplicationConfirmation" ) );

            // Documents
            FileDAO fileDAO = new FileDAO();
            Iterator<Map.Entry<String, String>> documentIterator = ( Iterator<Map.Entry<String, String>> ) fileDAO.getDocumentLinks( applicationID, ROLE_COMMITTEE ).entrySet().iterator();
            String linkString = EMPTY_STRING;
            addToSession( "DocsAvailable", "N" );

            while( documentIterator.hasNext() ) {
                Map.Entry<String, String> document = documentIterator.next();
                linkString = linkString + "<a href=\"/" + document.getKey() + "\">" + document.getValue() + "</a><br>";
                addToSession( "DocsAvailable", "Y" );
            }

            addToSession( "DocLinks", linkString );

            logger.debugPage( "/jsp/appReview05.jsp" );
            return "appReview05";
        }
    }

    public String submit() {
        logger.debugMethod( "submit" );
        boolean result = false;

        boolean bApplicationConfirmation = false;
        String status = STATUS_REJECTED;

        if( applicationConfirmation == null || applicationConfirmation.trim().equals( EMPTY_STRING ) ) {
            addErrorMessage( "error_application_confirmation_required" );
            
            logger.debugPage( "/jsp/appReview05e.jsp" );
            return "appReview05e";
        }
        else {
            if( applicationConfirmation.equals( "Y" ) ) {
                bApplicationConfirmation = true;
                status = STATUS_APPROVED;
            }
        }

        String applicationID = ( String ) getFromSession( SESSION_APPLICATIONID );

        ReviewCommitteeDAO reviewCommitteeDAO = new ReviewCommitteeDAO();
        result = reviewCommitteeDAO.addCommitteeApproval( applicationID, attestRequirements, comments, bApplicationConfirmation );

        if( result ) {
            // Documents
            if( documents != null ) {
                FileDAO fileDAO = new FileDAO();

                setEmail( ( String ) getFromSession( SESSION_EMAIL ) );
                String fileDirectory = fileDAO.getFileDirectory( getEmail() );
                String roleID = ROLE_COMMITTEE;

                String fileID = EMPTY_STRING;
                for( int i = 0 ; i < documents.length ; i++ ) {
                    fileID = documents[i];
                    fileDAO.addDocument( applicationID, roleID, fileDirectory, fileID );
                }
            }

            addInfoMessage( "info_review_info_added_ok" );

            String roleID = ROLE_COMMITTEE;
            String firstName = ( String ) getFromSession( SESSION_FIRSTNAME );
            String lastName = ( String ) getFromSession( SESSION_LASTNAME );
            setEmail( ( String ) getFromSession( SESSION_EMAIL ) );

            StatusDAO statusDAO = new StatusDAO();
            statusDAO.addStatus( applicationID, roleID, status, firstName, lastName, getEmail() );
            statusDAO.updatePhase( applicationID, PHASE_COMPLETED );
        }
        else {
            addErrorMessage( "error_db_update_failed" );
            
            logger.debugPage( "/jsp/appReview05e.jsp" );
            return "appReview05e";
        }

        logger.debugPage( "/jsp/appReviewList.jsp" );
        return "appReviewList";
    }

    public boolean getAttestRequirements() {
        return attestRequirements;
    }

    public void setAttestRequirements( boolean attestRequirements ) {
        this.attestRequirements = attestRequirements;
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
}
