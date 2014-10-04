package com.moh.review;

import java.util.ArrayList;
import java.util.Map;

import javax.faces.context.FacesContext;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ApplicationDAO;
import com.moh.data.dao.EducationDAO;
import com.moh.data.dao.ListAaDAO;
import com.moh.data.dao.ReviewCommitteeDAO;
import com.moh.data.dao.ReviewDeanDAO;
import com.moh.data.dao.ReviewDirectorDAO;
import com.moh.data.dao.ReviewSupervisorDAO;
import com.moh.data.dao.StatusDAO;
import com.moh.data.dao.UserDAO;
import com.moh.utils.DataFormatter;
import com.moh.utils.Logger;

public class AppReviewPrintRQHelper extends AbstractBean {

    private String applicationID;
    private String applicationSubmittedOn;
    private String rotationUpdatedOn;
    //Personal Info
    private String firstName;
    private String lastName;
    private String email;
    private String streetNumber;
    private String streetName;
    private String aptNumber;
    private String countryCode;
    private String provStateCode;
    private String city;
    private String postalCode;
    private String homePhone;
    private String officePhone;
    private String cellPhone;
    private String pagerNumber;
    private boolean aptSeparator;
    //Educational Info
    private String postGradYear;
    private String schoolResidency;
    private String northernStream;
    private String pgProgram;
    private String programDirector;
    private String edCountryCode;
    private String edCountryName;
    private String edProvStateCode;
    private String medSchool;
    private String atls;
    private String acls;
    private String pals;
    private String nals;
    private String mccqe1;
    private String mccqe2;
    private String cpsoNumber;
    private String cmpaNumber;
    //Rotation History will be retrieved from ClinicalDAO.getRotations
    //Employer Info
    private String lhinName;
    private String institutionName;
    private String serviceTypeName;
    private String supervisorName;
    private String supervisorEmail;
    private String supervisorPhone;
    private String supervisorExtension;
    //Resident Agreement
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
    private String umrComments;
    //Supervisor Approval
    private boolean isvEntryExists;
    private boolean isvConfirmApplied;
    private boolean isvAttestCredentials;
    private boolean isvAttestActivities;
    private boolean isvAbideToPairo;
    private boolean isvAttestSupervision;
    private boolean isvInformCpso;
    private boolean isvProvideInformation;
    private boolean isvApplicationConfirmation;
    private boolean isvInformActivities;
    private boolean isvIssueCertificate;
    private boolean isvConfirmCertificate;
    private boolean isvNotBeMRP;
    private String dutiesDescription;
    private String supervisedByName;
    private String supervisedByPhone;
    private String supervisedByEmail;
    private String supervisionDescription;
    //Program Director Approval
    private boolean updEntryExists;
    private boolean updCorrectInformation;
    private boolean updGoodAcademic;
    private boolean updAttestEligibility;
    private boolean updNotifyCpso;
    private boolean updProvideInformation;
    private String updComments;
    private boolean updApplicationConfirmation;
    //PGME Dean Approval
    private boolean udnEntryExists;
    private String udnComments;
    private boolean udnApplicationConfirmation;
    //Committee Approval
    private boolean umcEntryExists;
    private String umcComments;
    private boolean umcAttestRequirements;
    private boolean umcApplicationConfirmation;
    //Approvers information
    private String supervisorFullname;
    private String directorFullname;
    private String deanFullname;
    private String committeeFullname;
    private String supervisorApprovedOn;
    private String directorApprovedOn;
    private String deanApprovedOn;
    private String committeeApprovedOn;

    public AppReviewPrintRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "AppReviewPrintRQHelper" );
    }

    public String open() {
        FacesContext context = FacesContext.getCurrentInstance();
        String applicationID = context.getExternalContext().getRequestParameterMap().get( "appID" );

        addToSession( SESSION_APPLICATIONID, applicationID );

        return print();
    }

    public String print() {
        logger.debugMethod( "print" );

        applicationID = ( String ) getFromSession( SESSION_APPLICATIONID );

        StatusDAO statusDAO = new StatusDAO();
        setApplicationSubmittedOn( statusDAO.getApplicationSubmittedOn( applicationID ) );

        /*
         * PERSONAL INFORMATION
         */
        UserDAO userDAO = new UserDAO();
        setEmail( userDAO.getEmail( applicationID ) );
        Map<String, Object> sql_resultP = userDAO.getProfile( getEmail() );

        setRotationUpdatedOn( statusDAO.getRotationSubmittedOn( getEmail() ) );

        DataFormatter df = new DataFormatter();

        setFirstName( ( String ) sql_resultP.get( "FirstName" ) );
        setLastName( ( String ) sql_resultP.get( "LastName" ) );
        setStreetNumber( ( String ) sql_resultP.get( "StreetNumber" ) );
        setStreetName( ( String ) sql_resultP.get( "StreetName" ) );

        String vAptNumber = ( String ) sql_resultP.get( "AptNumber" );

        if( vAptNumber == null || vAptNumber.trim().equals( EMPTY_STRING ) ) {
            setAptSeparator( false );
        }
        else {
            setAptNumber( vAptNumber );
            setAptSeparator( true );
        }
        setCity( ( String ) sql_resultP.get( "City" ) );
        setPostalCode( df.postalCodeFormatter( ( String ) sql_resultP.get( "PostalCode" ) ) );
        setHomePhone( df.phoneFormatter( ( String ) sql_resultP.get( "HomePhone" ) ) );
        setOfficePhone( df.phoneFormatter( ( String ) sql_resultP.get( "OfficePhone" ) ) );
        setCellPhone( df.phoneFormatter( ( String ) sql_resultP.get( "CellPhone" ) ) );
        setPagerNumber( df.phoneFormatter( ( String ) sql_resultP.get( "PagerNumber" ) ) );
        setProvStateCode( "ON" );
        setCountryCode( "Canada" );

        /*
         * EDUCATIONAL INFORMATION
         */
        ArrayList<String> univProgInfo = null;
        EducationDAO educationDAO = new EducationDAO();
        Map<String, Object> sql_resultE = educationDAO.getEducation( getEmail() );

        if( sql_resultE.get( "PostGraduateYear" ) != null ) {
            setPostGradYear( "PGY" + sql_resultE.get( "PostGraduateYear" ) );
        }

        univProgInfo = educationDAO.getUnivProgNames( educationDAO.getUnivProgID( ( String ) sql_resultE.get( "UniversityID" ), ( String ) sql_resultE.get( "ProgramID" ) ) );

        if( univProgInfo != null & univProgInfo.size() > 0 ) {
            setSchoolResidency( univProgInfo.get( 0 ) );
            setPgProgram( univProgInfo.get( 1 ) );
            setProgramDirector( univProgInfo.get( 2 ) + " " + univProgInfo.get( 3 ) );
        }

        setEdCountryCode( ( String ) sql_resultE.get( "CountryCode" ) );

        if( edCountryCode != null && ! edCountryCode.trim().equals(EMPTY_STRING) ) {
            ListAaDAO listDAO = new ListAaDAO();

            String country = listDAO.getCountryName( edCountryCode );

            if( country == null || country.trim().equals( EMPTY_STRING ) ) {
                setEdCountryName( EMPTY_STRING );
            }
            else {
                setEdCountryName( "( " + country + " )" );
            }
        }

        setEdProvStateCode( ( String ) sql_resultE.get( "ProvStateCode" ) );
        setMedSchool( ( String ) sql_resultE.get( "MedicalSchool" ) );
        if( sql_resultE.get( "NorthernStream" ) != null ) {
            boolean northernStreamBoolean = ( Boolean ) sql_resultE.get( "NorthernStream" );

            if( northernStreamBoolean ) {
                setNorthernStream( " ( Northern Stream )" );
            }
            else {
                setNorthernStream( EMPTY_STRING );
            }
        }
        else {
            setNorthernStream( EMPTY_STRING );
        }

        if( !sql_resultE.get( "ATLSyear" ).equals( "0" ) ) {
            setAtls( "Completed in " + sql_resultE.get( "ATLSyear" ) );
        }
        else {
            setAtls( "No" );
        }
        if( !sql_resultE.get( "ACLSyear" ).equals( "0" ) ) {
            setAcls( "Completed in " + sql_resultE.get( "ACLSyear" ) );
        }
        else {
            setAcls( "No" );
        }
        if( !sql_resultE.get( "PALSyear" ).equals( "0" ) ) {
            setPals( "Completed in " + sql_resultE.get( "PALSyear" ) );
        }
        else {
            setPals( "No" );
        }
        if( !sql_resultE.get( "NALSyear" ).equals( "0" ) ) {
            setNals( "Completed in " + sql_resultE.get( "NALSyear" ) );
        }
        else {
            setNals( "No" );
        }

        if( sql_resultE.get( "MCCQE1year" ) != null ) {
            setMccqe1( "Completed in " + numberToMonth( ( String ) sql_resultE.get( "MCCQE1month" ) ) + " " + sql_resultE.get( "MCCQE1year" ) );
        }
        else {
            setMccqe1( EMPTY_STRING );
        }
        if( sql_resultE.get( "MCCQE2year" ) != null ) {
            setMccqe2( "Completed in " + numberToMonth( ( String ) sql_resultE.get( "MCCQE2month" ) ) + " " + sql_resultE.get( "MCCQE2year" ) );
        }
        else {
            setMccqe2( EMPTY_STRING );
        }

        setCpsoNumber( ( String ) sql_resultE.get( "CPSOnumber" ) );
        setCmpaNumber( ( String ) sql_resultE.get( "CMPAnumber" ) );

        /*
         * EMPLOYER INFORMATION
         */
        ApplicationDAO applicationDAO = new ApplicationDAO();
        Map<String, String> sql_resultA = applicationDAO.getEmployerInfo( applicationID );

        setLhinName( sql_resultA.get( "LhinName" ) );
        setInstitutionName( sql_resultA.get( "InstitutionName" ) );
        setServiceTypeName( sql_resultA.get( "ServiceTypeName" ) );
        setSupervisorName( sql_resultA.get( "SupervisorName" ) );
        setSupervisorEmail( sql_resultA.get( "SupervisorEmail" ) );
        setSupervisorPhone( sql_resultA.get( "SupervisorPhone" ) );
        setSupervisorExtension( sql_resultA.get( "SupervisorExtension" ) );
       
        /*
         * RESIDENT AGREEMENT
         */
        Map<String, String> agreements = applicationDAO.getAgreements();
        String agreeTo = applicationDAO.getAgreeTo( applicationID );

        ptcDescription = agreements.get( AGREEMENT_PTC );
        if( agreeTo.contains( AGREEMENT_PTC ) ) {
            ptcValue = true;
        }
        else {
            ptcValue = false;
        }
        nccDescription = agreements.get( AGREEMENT_NCC );
        if( agreeTo.contains( AGREEMENT_NCC ) ) {
            nccValue = true;
        }
        else {
            nccValue = false;
        }
        rasDescription = agreements.get( AGREEMENT_RAS );
        if( agreeTo.contains( AGREEMENT_RAS ) ) {
            rasValue = true;
        }
        else {
            rasValue = false;
        }
        rriDescription = agreements.get( AGREEMENT_RRI );
        if( agreeTo.contains( AGREEMENT_RRI ) ) {
            rriValue = true;
        }
        else {
            rriValue = false;
        }
        pwsDescription = agreements.get( AGREEMENT_PWS );
        if( agreeTo.contains( AGREEMENT_PWS ) ) {
            pwsValue = true;
        }
        else {
            pwsValue = false;
        }
        ftcDescription = agreements.get( AGREEMENT_FTC );
        if( agreeTo.contains( AGREEMENT_FTC ) ) {
            ftcValue = true;
        }
        else {
            ftcValue = false;
        }
        atiDescription = agreements.get( AGREEMENT_ATI );
        if( agreeTo.contains( AGREEMENT_ATI ) ) {
            atiValue = true;
        }
        else {
            atiValue = false;
        }
        rtwDescription = agreements.get( AGREEMENT_RTW );
        if( agreeTo.contains( AGREEMENT_RTW ) ) {
            rtwValue = true;
        }
        else {
            rtwValue = false;
        }
        pwtDescription = agreements.get( AGREEMENT_PWT );
        if( agreeTo.contains( AGREEMENT_PWT ) ) {
            pwtValue = true;
        }
        else {
            pwtValue = false;
        }
        rucDescription = agreements.get( AGREEMENT_RUC );
        if( agreeTo.contains( AGREEMENT_RUC ) ) {
            rucValue = true;
        }
        else {
            rucValue = false;
        }
        terDescription = agreements.get( AGREEMENT_TER );
        if( agreeTo.contains( AGREEMENT_TER ) ) {
            terValue = true;
        }
        else {
            terValue = false;
        }
        nlaDescription = agreements.get( AGREEMENT_NLA );
        if( agreeTo.contains( AGREEMENT_NLA ) ) {
            nlaValue = true;
        }
        else {
            nlaValue = false;
        }
        pidDescription = agreements.get( AGREEMENT_PID );
        if( agreeTo.contains( AGREEMENT_PID ) ) {
            pidValue = true;
        }
        else {
            pidValue = false;
        }
        iapDescription = agreements.get( AGREEMENT_IAP );
        if( agreeTo.contains( AGREEMENT_IAP ) ) {
            iapValue = true;
        }
        else {
            iapValue = false;
        }
        String suComments = applicationDAO.getAppComments( applicationID );

        if( suComments == null || suComments.trim().equals( EMPTY_STRING ) ) {
            suComments = "No information has been provided";
        }

        setUmrComments( suComments );

        /*
         * SUPERVISOR APPROVAL
         */
        ReviewSupervisorDAO reviewSupervisorDAO = new ReviewSupervisorDAO();
        Map<String, Object> supervisorResponse = reviewSupervisorDAO.getSupervisorApproval( applicationID );

        if( !supervisorResponse.isEmpty() ) {
            setIsvEntryExists( true );
            setIsvConfirmApplied( ( Boolean ) supervisorResponse.get( "ConfirmApplied" ) );
            setIsvAttestCredentials( ( Boolean ) supervisorResponse.get( "AttestCredentials" ) );
            setIsvAttestActivities( ( Boolean ) supervisorResponse.get( "AttestActivities" ) );
            setIsvAbideToPairo( ( Boolean ) supervisorResponse.get( "AbideToPairo" ) );
            setIsvAttestSupervision( ( Boolean ) supervisorResponse.get( "AttestSupervision" ) );
            setIsvInformCpso( ( Boolean ) supervisorResponse.get( "InformCpso" ) );
            setIsvProvideInformation( ( Boolean ) supervisorResponse.get( "ProvideInformation" ) );
            setIsvInformActivities( ( Boolean ) supervisorResponse.get( "InformActivities" ) );
            setIsvIssueCertificate( ( Boolean ) supervisorResponse.get( "IssueCertificate" ) );
            setIsvConfirmCertificate( ( Boolean ) supervisorResponse.get( "ConfirmCertificate" ) );
            setIsvNotBeMRP( ( Boolean ) supervisorResponse.get( "NotBeMRP" ) );

            setIsvApplicationConfirmation( ( Boolean ) supervisorResponse.get( "ApplicationConfirmation" ) );

            String svDutiesDescription = ( String ) supervisorResponse.get( "DutiesDescription" );

            if( svDutiesDescription == null || svDutiesDescription.trim().equals( EMPTY_STRING ) ) {
                svDutiesDescription = "No information has been provided";
            }

            String svSupervisionDescription = ( String ) supervisorResponse.get( "SupervisionDescription" );

            if( svSupervisionDescription == null || svSupervisionDescription.trim().equals( EMPTY_STRING ) ) {
                svSupervisionDescription = "No information has been provided";
            }

            setDutiesDescription( svDutiesDescription );
            setSupervisedByName( ( String ) supervisorResponse.get( "SupervisorName" ) );
            setSupervisedByPhone( ( String ) supervisorResponse.get( "SupervisorPhone" ) );
            setSupervisedByEmail( ( String ) supervisorResponse.get( "SupervisorEmail" ) );
            setSupervisionDescription( svSupervisionDescription );

            Map<String, String> supervisorStatusInfo = statusDAO.getStatusApproversInfo( applicationID, ROLE_SUPERVISOR );
            setSupervisorFullname( supervisorStatusInfo.get( "FullName" ) );
            setSupervisorApprovedOn( supervisorStatusInfo.get( "ApprovedOn" ) );
        }
        else {
            setIsvEntryExists( false );
        }

        /*
         * PROGRAM DIRECTOR APPROVAL
         */
        ReviewDirectorDAO reviewDirectorDAO = new ReviewDirectorDAO();
        Map<String, Object> directorResponse = reviewDirectorDAO.getDirectorApproval( applicationID );

        if( !directorResponse.isEmpty() ) {
            setUpdEntryExists( true );
            setUpdCorrectInformation( ( Boolean ) directorResponse.get( "CorrectInformation" ) );
            setUpdGoodAcademic( ( Boolean ) directorResponse.get( "GoodAcademic" ) );
            setUpdAttestEligibility( ( Boolean ) directorResponse.get( "AttestEligibility" ) );
            setUpdNotifyCpso( ( Boolean ) directorResponse.get( "NotifyCpso" ) );
            setUpdProvideInformation( ( Boolean ) directorResponse.get( "ProvideInformation" ) );

            String svComments = ( String ) directorResponse.get( "Comments" );
            if( svComments == null || svComments.trim().equals( EMPTY_STRING ) ) {
                svComments = "No comments have been entered";
            }
            setUpdComments( svComments );

            setUpdApplicationConfirmation( ( Boolean ) directorResponse.get( "ApplicationConfirmation" ) );

            Map<String, String> directorStatusInfo = statusDAO.getStatusApproversInfo( applicationID, ROLE_DIRECTOR );
            setDirectorFullname( directorStatusInfo.get( "FullName" ) );
            setDirectorApprovedOn( directorStatusInfo.get( "ApprovedOn" ) );
        }
        else {
            setUpdEntryExists( false );
        }

        /*
         * PGME DEAN APPROVAL
         */
        ReviewDeanDAO reviewDeanDAO = new ReviewDeanDAO();
        Map<String, Object> deanResponse = reviewDeanDAO.getDeanApproval( applicationID );

        if( !deanResponse.isEmpty() ) {
            setUdnEntryExists( true );

            String svComments = ( String ) deanResponse.get( "Comments" );
            if( svComments == null || svComments.trim().equals( EMPTY_STRING ) ) {
                svComments = "No comments have been entered";
            }
            setUdnComments( svComments );

            setUdnApplicationConfirmation( ( Boolean ) deanResponse.get( "ApplicationConfirmation" ) );

            Map<String, String> deanStatusInfo = statusDAO.getStatusApproversInfo( applicationID, ROLE_DEAN );
            setDeanFullname( deanStatusInfo.get( "FullName" ) );
            setDeanApprovedOn( deanStatusInfo.get( "ApprovedOn" ) );
        }
        else {
            setUdnEntryExists( false );
        }

        /*
         * COMMITTEE APPROVAL
         */
        ReviewCommitteeDAO reviewCommitteeDAO = new ReviewCommitteeDAO();
        Map<String, Object> committeeResponse = reviewCommitteeDAO.getCommitteeApproval( applicationID );

        if( !committeeResponse.isEmpty() ) {
            setUmcEntryExists( true );
            setUmcAttestRequirements( ( Boolean ) committeeResponse.get( "AttestRequirements" ) );

            String svComments = ( String ) committeeResponse.get( "Comments" );
            if( svComments == null || svComments.trim().equals( EMPTY_STRING ) ) {
                svComments = "No comments have been entered";
            }
            setUmcComments( svComments );

            setUmcApplicationConfirmation( ( Boolean ) committeeResponse.get( "ApplicationConfirmation" ) );

            Map<String, String> committeeStatusInfo = statusDAO.getStatusApproversInfo( applicationID, ROLE_COMMITTEE );
            setCommitteeFullname( committeeStatusInfo.get( "FullName" ) );
            setCommitteeApprovedOn( committeeStatusInfo.get( "ApprovedOn" ) );
        }
        else {
            setUmcEntryExists( false );
        }

        return "appReviewPrint";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
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

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber( String streetNumber ) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName( String streetName ) {
        this.streetName = streetName;
    }

    public String getAptNumber() {
        return aptNumber;
    }

    public void setAptNumber( String aptNumber ) {
        this.aptNumber = aptNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode( String countryCode ) {
        this.countryCode = countryCode;
    }

    public String getProvStateCode() {
        return provStateCode;
    }

    public void setProvStateCode( String provStateCode ) {
        this.provStateCode = provStateCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity( String city ) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode( String postalCode ) {
        this.postalCode = postalCode;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone( String homePhone ) {
        this.homePhone = homePhone;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone( String officePhone ) {
        this.officePhone = officePhone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone( String cellPhone ) {
        this.cellPhone = cellPhone;
    }

    public String getPagerNumber() {
        return pagerNumber;
    }

    public void setPagerNumber( String pagerNumber ) {
        this.pagerNumber = pagerNumber;
    }

    public boolean getAptSeparator() {
        return aptSeparator;
    }

    public void setAptSeparator( boolean aptSeparator ) {
        this.aptSeparator = aptSeparator;
    }

    public String getPostGradYear() {
        return postGradYear;
    }

    public void setPostGradYear( String postGradYear ) {
        this.postGradYear = postGradYear;
    }

    public String getSchoolResidency() {
        return schoolResidency;
    }

    public void setSchoolResidency( String schoolResidency ) {
        this.schoolResidency = schoolResidency;
    }

    public String getPgProgram() {
        return pgProgram;
    }

    public void setPgProgram( String pgProgram ) {
        this.pgProgram = pgProgram;
    }

    public String getProgramDirector() {
        return programDirector;
    }

    public void setProgramDirector( String programDirector ) {
        this.programDirector = programDirector;
    }

    public String getEdCountryCode() {
        return edCountryCode;
    }

    public void setEdCountryCode( String edCountryCode ) {
        this.edCountryCode = edCountryCode;
    }

    public String getEdProvStateCode() {
        return edProvStateCode;
    }

    public void setEdProvStateCode( String edProvStateCode ) {
        this.edProvStateCode = edProvStateCode;
    }

    public String getMedSchool() {
        return medSchool;
    }

    public void setMedSchool( String medSchool ) {
        this.medSchool = medSchool;
    }

    public String getNorthernStream() {
        return northernStream;
    }

    public void setNorthernStream( String northernStream ) {
        this.northernStream = northernStream;
    }

    public String getCpsoNumber() {
        return cpsoNumber;
    }

    public void setCpsoNumber( String cpsoNumber ) {
        this.cpsoNumber = cpsoNumber;
    }

    public String getCmpaNumber() {
        return cmpaNumber;
    }

    public void setCmpaNumber( String cmpaNumber ) {
        this.cmpaNumber = cmpaNumber;
    }

    public String getAtls() {
        return atls;
    }

    public void setAtls( String atls ) {
        this.atls = atls;
    }

    public String getAcls() {
        return acls;
    }

    public void setAcls( String acls ) {
        this.acls = acls;
    }

    public String getPals() {
        return pals;
    }

    public void setPals( String pals ) {
        this.pals = pals;
    }

    public String getNals() {
        return nals;
    }

    public void setNals( String nals ) {
        this.nals = nals;
    }

    public String getMccqe1() {
        return mccqe1;
    }

    public void setMccqe1( String mccqe1 ) {
        this.mccqe1 = mccqe1;
    }

    public String getMccqe2() {
        return mccqe2;
    }

    public void setMccqe2( String mccqe2 ) {
        this.mccqe2 = mccqe2;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID( String applicationID ) {
        this.applicationID = applicationID;
    }

    public String getLhinName() {
        return lhinName;
    }

    public void setLhinName( String lhinName ) {
        this.lhinName = lhinName;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName( String institutionName ) {
        this.institutionName = institutionName;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName( String serviceTypeName ) {
        this.serviceTypeName = serviceTypeName;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName( String supervisorName ) {
        this.supervisorName = supervisorName;
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

    public String getSupervisorPhone() {
        return supervisorPhone;
    }

    public void setSupervisorPhone( String supervisorPhone ) {
        this.supervisorPhone = supervisorPhone;
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

    public boolean getIsvConfirmApplied() {
        return isvConfirmApplied;
    }

    public void setIsvConfirmApplied( boolean isvConfirmApplied ) {
        this.isvConfirmApplied = isvConfirmApplied;
    }

    public boolean getIsvAttestCredentials() {
        return isvAttestCredentials;
    }

    public void setIsvAttestCredentials( boolean isvAttestCredentials ) {
        this.isvAttestCredentials = isvAttestCredentials;
    }

    public boolean getIsvAttestActivities() {
        return isvAttestActivities;
    }

    public void setIsvAttestActivities( boolean isvAttestActivities ) {
        this.isvAttestActivities = isvAttestActivities;
    }

    public boolean getIsvAbideToPairo() {
        return isvAbideToPairo;
    }

    public void setIsvAbideToPairo( boolean isvAbideToPairo ) {
        this.isvAbideToPairo = isvAbideToPairo;
    }

    public boolean getIsvAttestSupervision() {
        return isvAttestSupervision;
    }

    public void setIsvAttestSupervision( boolean isvAttestSupervision ) {
        this.isvAttestSupervision = isvAttestSupervision;
    }

    public boolean getIsvInformCpso() {
        return isvInformCpso;
    }

    public void setIsvInformCpso( boolean isvInformCpso ) {
        this.isvInformCpso = isvInformCpso;
    }

    public boolean getIsvProvideInformation() {
        return isvProvideInformation;
    }

    public void setIsvProvideInformation( boolean isvProvideInformation ) {
        this.isvProvideInformation = isvProvideInformation;
    }

    public boolean getIsvApplicationConfirmation() {
        return isvApplicationConfirmation;
    }

    public void setIsvApplicationConfirmation( boolean isvApplicationConfirmation ) {
        this.isvApplicationConfirmation = isvApplicationConfirmation;
    }

    public boolean getIsvEntryExists() {
        return isvEntryExists;
    }

    public void setIsvEntryExists( boolean isvEntryExists ) {
        this.isvEntryExists = isvEntryExists;
    }

    public boolean getUpdEntryExists() {
        return updEntryExists;
    }

    public void setUpdEntryExists( boolean updEntryExists ) {
        this.updEntryExists = updEntryExists;
    }

    public boolean getUpdCorrectInformation() {
        return updCorrectInformation;
    }

    public void setUpdCorrectInformation( boolean updCorrectInformation ) {
        this.updCorrectInformation = updCorrectInformation;
    }

    public boolean getUpdGoodAcademic() {
        return updGoodAcademic;
    }

    public void setUpdGoodAcademic( boolean updGoodAcademic ) {
        this.updGoodAcademic = updGoodAcademic;
    }

    public boolean getUpdAttestEligibility() {
        return updAttestEligibility;
    }

    public void setUpdAttestEligibility( boolean updAttestEligibility ) {
        this.updAttestEligibility = updAttestEligibility;
    }

    public boolean getUpdNotifyCpso() {
        return updNotifyCpso;
    }

    public void setUpdNotifyCpso( boolean updNotifyCpso ) {
        this.updNotifyCpso = updNotifyCpso;
    }

    public boolean getUpdProvideInformation() {
        return updProvideInformation;
    }

    public void setUpdProvideInformation( boolean updProvideInformation ) {
        this.updProvideInformation = updProvideInformation;
    }

    public boolean getUpdApplicationConfirmation() {
        return updApplicationConfirmation;
    }

    public void setUpdApplicationConfirmation( boolean updApplicationConfirmation ) {
        this.updApplicationConfirmation = updApplicationConfirmation;
    }

    public boolean getUdnApplicationConfirmation() {
        return udnApplicationConfirmation;
    }

    public void setUdnApplicationConfirmation( boolean udnApplicationConfirmation ) {
        this.udnApplicationConfirmation = udnApplicationConfirmation;
    }

    public void setUdnEntryExists( boolean udnEntryExists ) {
        this.udnEntryExists = udnEntryExists;
    }

    public boolean getUdnEntryExists() {
        return udnEntryExists;
    }

    public boolean getUmcEntryExists() {
        return umcEntryExists;
    }

    public void setUmcEntryExists( boolean umcEntryExists ) {
        this.umcEntryExists = umcEntryExists;
    }

    public boolean getUmcAttestRequirements() {
        return umcAttestRequirements;
    }

    public void setUmcAttestRequirements( boolean umcAttestRequirements ) {
        this.umcAttestRequirements = umcAttestRequirements;
    }

    public boolean getUmcApplicationConfirmation() {
        return umcApplicationConfirmation;
    }

    public void setUmcApplicationConfirmation( boolean umcApplicationConfirmation ) {
        this.umcApplicationConfirmation = umcApplicationConfirmation;
    }

    public String getEdCountryName() {
        return edCountryName;
    }

    public void setEdCountryName( String edCountryName ) {
        this.edCountryName = edCountryName;
    }

    public String getDutiesDescription() {
        return dutiesDescription;
    }

    public void setDutiesDescription( String dutiesDescription ) {
        this.dutiesDescription = dutiesDescription;
    }

    public String getSupervisedByName() {
        return supervisedByName;
    }

    public void setSupervisedByName( String supervisedByName ) {
        this.supervisedByName = supervisedByName;
    }

    public String getSupervisedByPhone() {
        return supervisedByPhone;
    }

    public void setSupervisedByPhone( String supervisedByPhone ) {
        this.supervisedByPhone = supervisedByPhone;
    }

    public String getSupervisedByEmail() {
        if ( supervisedByEmail == null || supervisedByEmail.trim().equals( EMPTY_STRING ) ) {
            return EMPTY_STRING;
        }
        else {
            return supervisedByEmail.trim().toLowerCase();
        }
    }

    public void setSupervisedByEmail( String supervisedByEmail ) {
        if ( supervisedByEmail == null || supervisedByEmail.trim().equals( EMPTY_STRING ) ) {
            this.supervisedByEmail = EMPTY_STRING;
        }
        else {
            this.supervisedByEmail = supervisedByEmail.trim().toLowerCase();
        }
    }

    public String getSupervisionDescription() {
        return supervisionDescription;
    }

    public void setSupervisionDescription( String supervisionDescription ) {
        this.supervisionDescription = supervisionDescription;
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

    public String getSupervisorExtension() {
        if( supervisorExtension == null || supervisorExtension.trim().equals( EMPTY_STRING ) ) {
            return supervisorExtension;
        }
        else {
            return "x:" + supervisorExtension;
        }
    }

    public void setSupervisorExtension( String supervisorExtension ) {
        this.supervisorExtension = supervisorExtension;
    }

    public String getSupervisorFullname() {
        return supervisorFullname;
    }

    public void setSupervisorFullname( String supervisorFullname ) {
        this.supervisorFullname = supervisorFullname;
    }

    public String getDirectorFullname() {
        return directorFullname;
    }

    public void setDirectorFullname( String directorFullname ) {
        this.directorFullname = directorFullname;
    }

    public String getDeanFullname() {
        return deanFullname;
    }

    public void setDeanFullname( String deanFullname ) {
        this.deanFullname = deanFullname;
    }

    public String getCommitteeFullname() {
        return committeeFullname;
    }

    public void setCommitteeFullname( String committeeFullname ) {
        this.committeeFullname = committeeFullname;
    }

    public String getSupervisorApprovedOn() {
        return supervisorApprovedOn;
    }

    public void setSupervisorApprovedOn( String supervisorApprovedOn ) {
        this.supervisorApprovedOn = supervisorApprovedOn;
    }

    public String getDirectorApprovedOn() {
        return directorApprovedOn;
    }

    public void setDirectorApprovedOn( String directorApprovedOn ) {
        this.directorApprovedOn = directorApprovedOn;
    }

    public String getDeanApprovedOn() {
        return deanApprovedOn;
    }

    public void setDeanApprovedOn( String deanApprovedOn ) {
        this.deanApprovedOn = deanApprovedOn;
    }

    public String getCommitteeApprovedOn() {
        return committeeApprovedOn;
    }

    public void setCommitteeApprovedOn( String committeeApprovedOn ) {
        this.committeeApprovedOn = committeeApprovedOn;
    }

    public boolean getIsvIssueCertificate() {
        return isvIssueCertificate;
    }

    public void setIsvIssueCertificate( boolean isvIssueCertificate ) {
        this.isvIssueCertificate = isvIssueCertificate;
    }

    public boolean getIsvConfirmCertificate() {
        return isvConfirmCertificate;
    }

    public void setIsvConfirmCertificate( boolean isvConfirmCertificate ) {
        this.isvConfirmCertificate = isvConfirmCertificate;
    }

    public boolean getIsvNotBeMRP() {
        return isvNotBeMRP;
    }

    public void setIsvNotBeMRP( boolean isvNotBeMRP ) {
        this.isvNotBeMRP = isvNotBeMRP;
    }

    public String getUpdComments() {
        return updComments;
    }

    public void setUpdComments( String updComments ) {
        this.updComments = updComments;
    }

    public String getUdnComments() {
        return udnComments;
    }

    public void setUdnComments( String udnComments ) {
        this.udnComments = udnComments;
    }

    public String getUmcComments() {
        return umcComments;
    }

    public void setUmcComments( String umcComments ) {
        this.umcComments = umcComments;
    }

    public String getApplicationSubmittedOn() {
        return applicationSubmittedOn;
    }

    public void setApplicationSubmittedOn( String applicationSubmittedOn ) {
        this.applicationSubmittedOn = applicationSubmittedOn;
    }

    public String getRotationUpdatedOn() {
        return rotationUpdatedOn;
    }

    public void setRotationUpdatedOn( String rotationUpdatedOn ) {
        this.rotationUpdatedOn = rotationUpdatedOn;
    }

    public String getUmrComments() {
        return umrComments;
    }

    public void setUmrComments( String umrComments ) {
        this.umrComments = umrComments;
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

    public boolean getIsvInformActivities() {
        return isvInformActivities;
    }

    public void setIsvInformActivities( boolean isvInformActivities ) {
        this.isvInformActivities = isvInformActivities;
    }
}
