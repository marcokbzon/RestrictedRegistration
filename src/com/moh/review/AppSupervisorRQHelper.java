package com.moh.review;

import java.util.Iterator;
import java.util.Map;

import javax.mail.MessagingException;

import com.moh.common.AbstractBean;
import com.moh.data.dao.FileDAO;
import com.moh.data.dao.ReviewDirectorDAO;
import com.moh.data.dao.ReviewSupervisorDAO;
import com.moh.data.dao.StatusDAO;
import com.moh.mail.Notification;
import com.moh.utils.Logger;

public class AppSupervisorRQHelper extends AbstractBean {

    private boolean confirmApplied;
    private boolean attestCredentials;
    private boolean attestActivities;
    private boolean abideToPairo;
    private boolean attestSupervision;
    private boolean informCpso;
    private boolean provideInformation;
    private boolean issueCertificate;
    private boolean confirmCertificate;
    private boolean notBeMRP;
    private String dutiesDescription;
    private String supervisorName;
    private String supervisorPhone;
    private String supervisorEmail;
    private String supervisionDescription;
    private boolean informActivities;
    private String[] documents;
    private String approverName;
    private boolean nextButton;
    private boolean booleanApplicationConfirmation;
    private String applicationConfirmation;
    private String email;
    private String progDirEmail;

    public AppSupervisorRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "AppSupervisorRQHelper" );

        String roleID = ( String ) getFromSession( SESSION_ROLEID );

        if( roleID.equals( ROLE_DIRECTOR ) || roleID.equals( ROLE_DEAN ) || roleID.equals( ROLE_COMMITTEE ) || roleID.equals( ROLE_ADMIN_COMMITTEE ) ) {
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

        if( roleID.equals( ROLE_SUPERVISOR ) ) {
            StatusDAO statusDAO = new StatusDAO();
            isTabEditable = statusDAO.isInformationEditable( applicationID, roleID );
        }

        if( isTabEditable ) {
            String firstName = ( String ) getFromSession( SESSION_FIRSTNAME );
            String lastName = ( String ) getFromSession( SESSION_LASTNAME );
            setEmail( ( String ) getFromSession( SESSION_EMAIL ) );

            setSupervisorName( firstName + " " + lastName );
            setSupervisorEmail( getEmail() );

            logger.debugPage( "/jsp/appReview02e.jsp" );
            return "appReview02e";
        }
        else {
            ReviewSupervisorDAO reviewSupervisorDAO = new ReviewSupervisorDAO();
            Map<String, Object> supervisorResponse = reviewSupervisorDAO.getSupervisorApproval( ( String ) getFromSession( SESSION_APPLICATIONID ) );

            setConfirmApplied( ( Boolean ) supervisorResponse.get( "ConfirmApplied" ) );
            setAttestCredentials( ( Boolean ) supervisorResponse.get( "AttestCredentials" ) );
            setAttestActivities( ( Boolean ) supervisorResponse.get( "AttestActivities" ) );
            setAbideToPairo( ( Boolean ) supervisorResponse.get( "AbideToPairo" ) );
            setAttestSupervision( ( Boolean ) supervisorResponse.get( "AttestSupervision" ) );
            setInformCpso( ( Boolean ) supervisorResponse.get( "InformCpso" ) );
            setProvideInformation( ( Boolean ) supervisorResponse.get( "ProvideInformation" ) );
            setIssueCertificate( ( Boolean ) supervisorResponse.get( "IssueCertificate" ) );
            setConfirmCertificate( ( Boolean ) supervisorResponse.get( "ConfirmCertificate" ) );
            setNotBeMRP( ( Boolean ) supervisorResponse.get( "NotBeMRP" ) );
            setInformActivities( ( Boolean ) supervisorResponse.get( "InformActivities" ) );

            setBooleanApplicationConfirmation( ( Boolean ) supervisorResponse.get( "ApplicationConfirmation" ) );

            String svDutiesDescription = ( String ) supervisorResponse.get( "DutiesDescription" );
            if( svDutiesDescription == null || svDutiesDescription.trim().equals( EMPTY_STRING ) ) {
                svDutiesDescription = "No information has been provided";
            }
            setDutiesDescription( svDutiesDescription );

            setSupervisorName( ( String ) supervisorResponse.get( "SupervisorName" ) );
            setSupervisorPhone( ( String ) supervisorResponse.get( "SupervisorPhone" ) );
            setSupervisorEmail( ( String ) supervisorResponse.get( "SupervisorEmail" ) );

            String svSupervisionDescription = ( String ) supervisorResponse.get( "SupervisionDescription" );
            if( svSupervisionDescription == null || svSupervisionDescription.trim().equals( EMPTY_STRING ) ) {
                svSupervisionDescription = "No information has been provided";
            }
            setSupervisionDescription( svSupervisionDescription );

            setApproverName( ( String ) supervisorResponse.get( "ApproverName" ) );

            // Documents
            FileDAO fileDAO = new FileDAO();
            Iterator<Map.Entry<String, String>> documentIterator = ( Iterator<Map.Entry<String, String>> ) fileDAO.getDocumentLinks( applicationID, ROLE_SUPERVISOR ).entrySet().iterator();
            String linkString = EMPTY_STRING;
            addToSession( "DocsAvailable", "N" );

            while( documentIterator.hasNext() ) {
                Map.Entry<String, String> document = documentIterator.next();
                linkString = linkString + "<a href=\"/" + document.getKey() + "\">" + document.getValue() + "</a><br>";
                addToSession( "DocsAvailable", "Y" );
            }

            addToSession( "DocLinks", linkString );

            logger.debugPage( "/jsp/appReview02.jsp" );
            return "appReview02";
        }
    }

    public String submit() {
        logger.debugMethod( "submit" );

        boolean result = false;

        boolean bApplicationConfirmation = false;
        String status = STATUS_REJECTED;

        if( applicationConfirmation == null || applicationConfirmation.equals( EMPTY_STRING ) ) {
            addErrorMessage( "error_application_confirmation_required" );
            
            logger.debugPage( "/jsp/appReview02e.jsp" );
            return "appReview02e";
        }
        else {
            if( applicationConfirmation.equals( "Y" ) ) {
                bApplicationConfirmation = true;
                status = STATUS_APPROVED;

                if( confirmApplied == false || attestCredentials == false || attestActivities == false || abideToPairo == false || attestSupervision == false || informCpso == false || provideInformation == false || issueCertificate == false || confirmCertificate == false || notBeMRP == false || informActivities == false ) {
                    addErrorMessage( "error_reviewer_attestation_is_incomplete" );
                    
                    logger.debugPage( "/jsp/appReview02e.jsp" );
                    return "appReview02e";
                }
            }
        }

        String applicationID = ( String ) getFromSession( SESSION_APPLICATIONID );

        String firstName = ( String ) getFromSession( SESSION_FIRSTNAME );
        String lastName = ( String ) getFromSession( SESSION_LASTNAME );
        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );

        ReviewSupervisorDAO reviewSupervisorDAO = new ReviewSupervisorDAO();
        result = reviewSupervisorDAO.addSupervisorApproval( applicationID, confirmApplied, attestCredentials, attestActivities, abideToPairo, attestSupervision, informCpso, provideInformation, dutiesDescription, firstName + " " + lastName, supervisorPhone, email, supervisionDescription, bApplicationConfirmation, issueCertificate, confirmCertificate, notBeMRP, informActivities );

        if( result ) {
            // Documents
            if( documents != null ) {
                FileDAO fileDAO = new FileDAO();

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
            //String firstName = ( String ) getFromSession( SESSION_FIRSTNAME );
            //String lastName = ( String ) getFromSession( SESSION_LASTNAME );
            //String email = ( String ) getFromSession( SESSION_EMAIL );

            StatusDAO statusDAO = new StatusDAO();
            statusDAO.addStatus( applicationID, roleID, status, firstName, lastName, getEmail() );
            statusDAO.updatePhase( applicationID, PHASE_INPROGRESS );

            try {
                ReviewDirectorDAO reviewDirectorDAO = new ReviewDirectorDAO();

                setProgDirEmail( reviewDirectorDAO.getDirectorEmail( applicationID ) );

                Notification notification = new Notification();

                if( progDirEmail == null || progDirEmail.trim().equals( EMPTY_STRING ) ) {
                    notification.sendEmailToAdminMissingReviewer( applicationID, ROLE_DIRECTOR );
                }
                else {
                    notification.sendEmailToReviewers( getProgDirEmail(), applicationID );
                }
            }
            catch( MessagingException mex ) {
                addErrorMessage( "error_send_email_failed" );
            }
        }
        else {
            addErrorMessage( "error_db_update_failed" );
            
            logger.debugPage( "/jsp/appReview02e.jsp" );
            return "appReview02e";
        }

        logger.debugPage( "/jsp/appReviewList.jsp" );
        return "appReviewList";
    }

    public boolean getConfirmApplied() {
        return confirmApplied;
    }

    public void setConfirmApplied( boolean confirmApplied ) {
        this.confirmApplied = confirmApplied;
    }

    public boolean getAttestCredentials() {
        return attestCredentials;
    }

    public void setAttestCredentials( boolean attestCredentials ) {
        this.attestCredentials = attestCredentials;
    }

    public boolean getAttestActivities() {
        return attestActivities;
    }

    public void setAttestActivities( boolean attestActivities ) {
        this.attestActivities = attestActivities;
    }

    public boolean getAbideToPairo() {
        return abideToPairo;
    }

    public void setAbideToPairo( boolean abideToPairo ) {
        this.abideToPairo = abideToPairo;
    }

    public boolean getAttestSupervision() {
        return attestSupervision;
    }

    public void setAttestSupervision( boolean attestSupervision ) {
        this.attestSupervision = attestSupervision;
    }

    public boolean getInformCpso() {
        return informCpso;
    }

    public void setInformCpso( boolean informCpso ) {
        this.informCpso = informCpso;
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

    public String getDutiesDescription() {
        return dutiesDescription;
    }

    public void setDutiesDescription( String dutiesDescription ) {
        this.dutiesDescription = dutiesDescription;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName( String supervisorName ) {
        this.supervisorName = supervisorName;
    }

    public String getSupervisorPhone() {
        return supervisorPhone;
    }

    public void setSupervisorPhone( String supervisorPhone ) {
        this.supervisorPhone = supervisorPhone;
    }

    public String getSupervisorEmail() {
        if ( supervisorEmail == null || supervisorEmail.trim().equals( EMPTY_STRING ) ) {
            return EMPTY_STRING;
        }
        else {
            return supervisorEmail.trim().toLowerCase();
        }
    }

    public void setSupervisorEmail( String supervisorEmail ) {
        if ( supervisorEmail == null || supervisorEmail.trim().equals( EMPTY_STRING ) ) {
            this.supervisorEmail = EMPTY_STRING;
        }
        else {
            this.supervisorEmail = supervisorEmail.trim().toLowerCase();
        }
    }

    public String getSupervisionDescription() {
        return supervisionDescription;
    }

    public void setSupervisionDescription( String supervisionDescription ) {
        this.supervisionDescription = supervisionDescription;
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

    public boolean getIssueCertificate() {
        return issueCertificate;
    }

    public void setIssueCertificate( boolean issueCertificate ) {
        this.issueCertificate = issueCertificate;
    }

    public boolean getConfirmCertificate() {
        return confirmCertificate;
    }

    public void setConfirmCertificate( boolean confirmCertificate ) {
        this.confirmCertificate = confirmCertificate;
    }

    public boolean getNotBeMRP() {
        return notBeMRP;
    }

    public void setNotBeMRP( boolean notBeMRP ) {
        this.notBeMRP = notBeMRP;
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

    public boolean getInformActivities() {
        return informActivities;
    }

    public void setInformActivities( boolean informActivities ) {
        this.informActivities = informActivities;
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

    private String getProgDirEmail() {
        if ( progDirEmail == null || progDirEmail.trim().equals( EMPTY_STRING ) ) {
            return EMPTY_STRING;
        }
        else {
            return progDirEmail.trim().toLowerCase();
        }
    }

    private void setProgDirEmail(String progDirEmail) {
        if ( progDirEmail == null || progDirEmail.trim().equals( EMPTY_STRING ) ) {
            this.progDirEmail = EMPTY_STRING;
        }
        else {
            this.progDirEmail = progDirEmail.trim().toLowerCase();
        }
    }
}
