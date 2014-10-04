package com.moh.application;

import java.util.Map;

import javax.mail.MessagingException;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ApplicationDAO;
import com.moh.data.dao.StatusDAO;
import com.moh.mail.Notification;
import com.moh.utils.Logger;

public class AgreementInfoSSHelper extends AbstractBean {

    private boolean ptcValue;
    private String ptcDescription;
    private boolean nccValue;
    private String nccDescription;
    private boolean rasValue;
    private String rasDescription;
    private boolean rriValue;
    private String rriDescription;
    private boolean pwsValue;
    private String pwsDescription;
    private boolean ftcValue;
    private String ftcDescription;
    private boolean atiValue;
    private String atiDescription;
    private boolean rtwValue;
    private String rtwDescription;
    private boolean pwtValue;
    private String pwtDescription;
    private boolean rucValue;
    private String rucDescription;
    private boolean terValue;
    private String terDescription;
    private boolean nlaValue;
    private String nlaDescription;
    private boolean pidValue;
    private String pidDescription;
    private boolean iapValue;
    private String iapDescription;
    private String comments;
    //private boolean sliValue;
    //private String sliDescription;
    private String applicationID;
    private String email;

    public AgreementInfoSSHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "AgreementInfoSSHelper" );
    }

    public String view() {
        logger.debugMethod( "view" );

        ApplicationDAO applicationDAO = new ApplicationDAO();

        Map<String, String> agreements = applicationDAO.getAgreements();

        ptcValue = false;
        ptcDescription = agreements.get( AGREEMENT_PTC );
        nccValue = false;
        nccDescription = agreements.get( AGREEMENT_NCC );
        rasValue = false;
        rasDescription = agreements.get( AGREEMENT_RAS );
        rriValue = false;
        rriDescription = agreements.get( AGREEMENT_RRI );
        pwsValue = false;
        pwsDescription = agreements.get( AGREEMENT_PWS );
        ftcValue = false;
        ftcDescription = agreements.get( AGREEMENT_FTC );
        atiValue = false;
        atiDescription = agreements.get( AGREEMENT_ATI );
        rtwValue = false;
        rtwDescription = agreements.get( AGREEMENT_RTW );
        pwtValue = false;
        pwtDescription = agreements.get( AGREEMENT_PWT );
        rucValue = false;
        rucDescription = agreements.get( AGREEMENT_RUC );
        terValue = false;
        terDescription = agreements.get( AGREEMENT_TER );
        nlaValue = false;
        nlaDescription = agreements.get( AGREEMENT_NLA );
        pidValue = false;
        pidDescription = agreements.get( AGREEMENT_PID );
        iapValue = false;
        iapDescription = agreements.get( AGREEMENT_IAP );

        logger.debugPage( "/jsp/appForm05.jsp" );
        return "applicationForm05";
    }

    public String submit() {
        logger.debugMethod( "submit" );
        boolean result = false;

        String roleID = ( String ) getFromSession( SESSION_ROLEID );
        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );
        String firstName = ( String ) getFromSession( SESSION_FIRSTNAME );
        String lastName = ( String ) getFromSession( SESSION_LASTNAME );

        setApplicationID( ( String ) getFromSession( SESSION_APPLICATIONID ) );

        ApplicationDAO applicationDAO = new ApplicationDAO();

        String listOfAgreements = EMPTY_STRING;

        if( applicationID == null || applicationID.trim().equals(EMPTY_STRING) ) {
            addErrorMessage( "error_employer_is_incomplete" );

            logger.debugPage( "/jsp/appForm05.jsp" );
            return "applicationForm05";
        }
        else {
            StatusDAO statusDAO = new StatusDAO();
            boolean isAppAlreadySubmitted = statusDAO.isAppAlreadySubmitted( applicationID );

            if( isAppAlreadySubmitted ) {
                addErrorMessage( "error_application_already_submitted" );

                logger.debugPage( "/jsp/appFormList.jsp" );
                return "appFormList";
            }

            if( ptcValue ) {
                listOfAgreements = listOfAgreements + AGREEMENT_PTC + "|";
            }
            if( nccValue ) {
                listOfAgreements = listOfAgreements + AGREEMENT_NCC + "|";
            }
            if( rasValue ) {
                listOfAgreements = listOfAgreements + AGREEMENT_RAS + "|";
            }
            if( rriValue ) {
                listOfAgreements = listOfAgreements + AGREEMENT_RRI + "|";
            }
            if( pwsValue ) {
                listOfAgreements = listOfAgreements + AGREEMENT_PWS + "|";
            }
            if( ftcValue ) {
                listOfAgreements = listOfAgreements + AGREEMENT_FTC + "|";
            }
            if( atiValue ) {
                listOfAgreements = listOfAgreements + AGREEMENT_ATI + "|";
            }
            if( rtwValue ) {
                listOfAgreements = listOfAgreements + AGREEMENT_RTW + "|";
            }
            if( pwtValue ) {
                listOfAgreements = listOfAgreements + AGREEMENT_PWT + "|";
            }
            if( rucValue ) {
                listOfAgreements = listOfAgreements + AGREEMENT_RUC + "|";
            }
            if( terValue ) {
                listOfAgreements = listOfAgreements + AGREEMENT_TER + "|";
            }
            if( nlaValue ) {
                listOfAgreements = listOfAgreements + AGREEMENT_NLA + "|";
            }
            if( pidValue ) {
                listOfAgreements = listOfAgreements + AGREEMENT_PID + "|";
            }
            if( iapValue ) {
                listOfAgreements = listOfAgreements + AGREEMENT_IAP + "|";
            }

            int listLen = listOfAgreements.length();

            if( listLen != 56 ) { // number of values times 4
                addErrorMessage( "error_agreement_is_incomplete" );

                logger.debugPage( "/jsp/appForm05.jsp" );
                return "applicationForm05";
            }
            else {
                listOfAgreements = listOfAgreements.substring( 0, listLen - 1 );
            }

            result = applicationDAO.addAgreeTo( applicationID, listOfAgreements );

            if( result ) {
                addInfoMessage( "info_application_submitted_ok" );

                applicationDAO.editAppComments( comments, applicationID );

                //StatusDAO statusDAO = new StatusDAO();
                statusDAO.addStatus( applicationID, roleID, STATUS_SUBMITTED, firstName, lastName, getEmail() );

                Map<String, String> employerInfo = applicationDAO.getEmployerInfo( applicationID );
                try {
                    Notification notification = new Notification();

                    notification.sendEmailToReviewers( employerInfo.get( "SupervisorEmail" ), applicationID );

                    //statusDAO.addStatus( applicationID, ROLE_SUPERVISOR, STATUS_NOTIFIED, EMPTY_STRING, EMPTY_STRING, employerInfo.get( "SupervisorEmail" ) );
                }
                catch( MessagingException mex ) {
                    addErrorMessage( "error_send_email_failed" );
                }
            }
            else {
                addErrorMessage( "error_db_save_failed" );
            }
        }

        logger.debugPage( "/jsp/appFormList.jsp" );
        return "appFormList";
    }

    public String getApplicationID() {
        return ( String ) getFromSession( SESSION_APPLICATIONID );
    }

    public void setApplicationID( String applicationID ) {
        this.applicationID = applicationID;
    }

    public boolean getPtcValue() {
        return ptcValue;
    }

    public void setPtcValue( boolean ptcValue ) {
        this.ptcValue = ptcValue;
    }

    public String getPtcDescription() {
        return ptcDescription;
    }

    public void setPtcDescription( String ptcDescription ) {
        this.ptcDescription = ptcDescription;
    }

    public boolean getNccValue() {
        return nccValue;
    }

    public void setNccValue( boolean nccValue ) {
        this.nccValue = nccValue;
    }

    public String getNccDescription() {
        return nccDescription;
    }

    public void setNccDescription( String nccDescription ) {
        this.nccDescription = nccDescription;
    }

    public boolean getRasValue() {
        return rasValue;
    }

    public void setRasValue( boolean rasValue ) {
        this.rasValue = rasValue;
    }

    public String getRasDescription() {
        return rasDescription;
    }

    public void setRasDescription( String rasDescription ) {
        this.rasDescription = rasDescription;
    }

    public boolean getPwsValue() {
        return pwsValue;
    }

    public void setPwsValue( boolean pwsValue ) {
        this.pwsValue = pwsValue;
    }

    public String getPwsDescription() {
        return pwsDescription;
    }

    public void setPwsDescription( String pwsDescription ) {
        this.pwsDescription = pwsDescription;
    }

    public boolean getFtcValue() {
        return ftcValue;
    }

    public void setFtcValue( boolean ftcValue ) {
        this.ftcValue = ftcValue;
    }

    public String getFtcDescription() {
        return ftcDescription;
    }

    public void setFtcDescription( String ftcDescription ) {
        this.ftcDescription = ftcDescription;
    }

    public boolean getAtiValue() {
        return atiValue;
    }

    public void setAtiValue( boolean atiValue ) {
        this.atiValue = atiValue;
    }

    public String getAtiDescription() {
        return atiDescription;
    }

    public void setAtiDescription( String atiDescription ) {
        this.atiDescription = atiDescription;
    }

    public boolean getRtwValue() {
        return rtwValue;
    }

    public void setRtwValue( boolean rtwValue ) {
        this.rtwValue = rtwValue;
    }

    public String getRtwDescription() {
        return rtwDescription;
    }

    public void setRtwDescription( String rtwDescription ) {
        this.rtwDescription = rtwDescription;
    }

    public boolean getPwtValue() {
        return pwtValue;
    }

    public void setPwtValue( boolean pwtValue ) {
        this.pwtValue = pwtValue;
    }

    public String getPwtDescription() {
        return pwtDescription;
    }

    public void setPwtDescription( String pwtDescription ) {
        this.pwtDescription = pwtDescription;
    }

    public boolean getPidValue() {
        return pidValue;
    }

    public void setPidValue( boolean pidValue ) {
        this.pidValue = pidValue;
    }

    public String getPidDescription() {
        return pidDescription;
    }

    public void setPidDescription( String pidDescription ) {
        this.pidDescription = pidDescription;
    }

    public boolean getRriValue() {
        return rriValue;
    }

    public void setRriValue( boolean rriValue ) {
        this.rriValue = rriValue;
    }

    public String getRriDescription() {
        return rriDescription;
    }

    public void setRriDescription( String rriDescription ) {
        this.rriDescription = rriDescription;
    }

    public boolean getRucValue() {
        return rucValue;
    }

    public void setRucValue( boolean rucValue ) {
        this.rucValue = rucValue;
    }

    public String getRucDescription() {
        return rucDescription;
    }

    public void setRucDescription( String rucDescription ) {
        this.rucDescription = rucDescription;
    }

    public boolean getTerValue() {
        return terValue;
    }

    public void setTerValue( boolean terValue ) {
        this.terValue = terValue;
    }

    public String getTerDescription() {
        return terDescription;
    }

    public void setTerDescription( String terDescription ) {
        this.terDescription = terDescription;
    }

    public boolean getNlaValue() {
        return nlaValue;
    }

    public void setNlaValue( boolean nlaValue ) {
        this.nlaValue = nlaValue;
    }

    public String getNlaDescription() {
        return nlaDescription;
    }

    public void setNlaDescription( String nlaDescription ) {
        this.nlaDescription = nlaDescription;
    }

    public String getComments() {
        return comments;
    }

    public void setComments( String comments ) {
        this.comments = comments;
    }

    public boolean getIapValue() {
        return iapValue;
    }

    public void setIapValue( boolean iapValue ) {
        this.iapValue = iapValue;
    }

    public String getIapDescription() {
        return iapDescription;
    }

    public void setIapDescription( String iapDescription ) {
        this.iapDescription = iapDescription;
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
