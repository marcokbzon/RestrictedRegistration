package com.moh.admin;

import com.moh.common.AbstractBean;
import com.moh.data.dao.AdminStatusDAO;

import com.moh.utils.Logger;

public class AdminStatusPrintRQHelper extends AbstractBean {

    // Applications and Profiles
    private String numberApplicationsAll;
    private String numberApplicationsNew;
    private String numberApplicationsInProgress;
    private String numberApplicationsCompleted;
    private String numberResidentsAll;
    private String numberResidentsApplied;
    private String numberSupervisorsAll;
    private String numberProgramDirectorsAll;
    private String numberPGMEDeansAll;
    private String numberCommitteeMembersAll;
    // Universities and Institutions
    private String numberUniversities;
    private String numberUniversitiesApplied;
    private String numberPrograms;
    private String numberProgramsApplied;
    private String numberInstitutions;
    private String numberInstitutionsApplied;
    private String numberServiceTypes;
    private String numberServiceTypesApplied;

    public AdminStatusPrintRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "AppReviewPrintRQHelper" );
    }

    public String open() {

        return print();
    }

    public String print() {
        logger.debugMethod( "print" );

        /*
         * Applications and Profiles
         */
        AdminStatusDAO statusDAO = new AdminStatusDAO();

        String applicationsAll = statusDAO.applicationCount( DEFAULT_ALL );
        setNumberApplicationsAll( applicationsAll );

        //String residentsAll = statusDAO.userCount( ROLE_RESIDENT );
        String residentsAll = statusDAO.countResidentsApprovedByCPSO();
        setNumberResidentsAll( residentsAll );

        String residentsApplied = statusDAO.residentsAppliedCount();
        setNumberResidentsApplied( residentsApplied );

        String supervisorsAll = statusDAO.userCount( ROLE_SUPERVISOR );
        setNumberSupervisorsAll( supervisorsAll );

        String programDirectorsAll = statusDAO.userCount( ROLE_DIRECTOR );
        setNumberProgramDirectorsAll( programDirectorsAll );

        String deansAll = statusDAO.userCount( ROLE_DEAN );
        setNumberPGMEDeansAll( deansAll );

        String committeeMembersAll = statusDAO.userCount( ROLE_COMMITTEE );
        setNumberCommitteeMembersAll( committeeMembersAll );

        /*
         * Universities and Institutions
         */
        String universities = statusDAO.universityCount();
        setNumberUniversities( universities );

        String universitiesApplied = statusDAO.universitiesAppliedCount();
        setNumberUniversitiesApplied( universitiesApplied );

        String programs = statusDAO.programCount();
        setNumberPrograms( programs );

        String programsApplied = statusDAO.programsAppliedCount();
        setNumberProgramsApplied( programsApplied );

        String institutions = statusDAO.institutionCount();
        setNumberInstitutions( institutions );

        String institutionsApplied = statusDAO.institutionsAppliedCount();
        setNumberInstitutionsApplied( institutionsApplied );

        String serviceTypes = statusDAO.serviceTypeCount();
        setNumberServiceTypes( serviceTypes );

        String serviceTypesApplied = statusDAO.serviceTypesAppliedCount();
        setNumberServiceTypesApplied( serviceTypesApplied );

        logger.debugPage( "/jsp/adminSiteStatusPrint.jsp" );
        return "adminSiteStatusPrint";
    }

    public String getNumberApplicationsAll() {
        return numberApplicationsAll;
    }

    public void setNumberApplicationsAll( String numberApplicationsAll ) {
        this.numberApplicationsAll = numberApplicationsAll;
    }

    public String getNumberApplicationsNew() {
        return numberApplicationsNew;
    }

    public void setNumberApplicationsNew( String numberApplicationsNew ) {
        this.numberApplicationsNew = numberApplicationsNew;
    }

    public String getNumberApplicationsInProgress() {
        return numberApplicationsInProgress;
    }

    public void setNumberApplicationsInProgress( String numberApplicationsInProgress ) {
        this.numberApplicationsInProgress = numberApplicationsInProgress;
    }

    public String getNumberApplicationsCompleted() {
        return numberApplicationsCompleted;
    }

    public void setNumberApplicationsCompleted( String numberApplicationsCompleted ) {
        this.numberApplicationsCompleted = numberApplicationsCompleted;
    }

    public String getNumberUniversities() {
        return numberUniversities;
    }

    public void setNumberUniversities( String numberUniversities ) {
        this.numberUniversities = numberUniversities;
    }

    public String getNumberPrograms() {
        return numberPrograms;
    }

    public void setNumberPrograms( String numberPrograms ) {
        this.numberPrograms = numberPrograms;
    }

    public String getNumberInstitutions() {
        return numberInstitutions;
    }

    public void setNumberInstitutions( String numberInstitutions ) {
        this.numberInstitutions = numberInstitutions;
    }

    public String getNumberServiceTypes() {
        return numberServiceTypes;
    }

    public void setNumberServiceTypes( String numberServiceTypes ) {
        this.numberServiceTypes = numberServiceTypes;
    }

    public String getNumberResidentsAll() {
        return numberResidentsAll;
    }

    public void setNumberResidentsAll( String numberResidentsAll ) {
        this.numberResidentsAll = numberResidentsAll;
    }

    public String getNumberResidentsApplied() {
        return numberResidentsApplied;
    }

    public void setNumberResidentsApplied( String numberResidentsApplied ) {
        this.numberResidentsApplied = numberResidentsApplied;
    }

    public String getNumberSupervisorsAll() {
        return numberSupervisorsAll;
    }

    public void setNumberSupervisorsAll( String numberSupervisorsAll ) {
        this.numberSupervisorsAll = numberSupervisorsAll;
    }

    public String getNumberProgramDirectorsAll() {
        return numberProgramDirectorsAll;
    }

    public void setNumberProgramDirectorsAll( String numberProgramDirectorsAll ) {
        this.numberProgramDirectorsAll = numberProgramDirectorsAll;
    }

    public String getNumberPGMEDeansAll() {
        return numberPGMEDeansAll;
    }

    public void setNumberPGMEDeansAll( String numberPGMEDeansAll ) {
        this.numberPGMEDeansAll = numberPGMEDeansAll;
    }

    public String getNumberCommitteeMembersAll() {
        return numberCommitteeMembersAll;
    }

    public void setNumberCommitteeMembersAll( String numberCommitteeMembersAll ) {
        this.numberCommitteeMembersAll = numberCommitteeMembersAll;
    }

    public String getNumberUniversitiesApplied() {
        return numberUniversitiesApplied;
    }

    public void setNumberUniversitiesApplied( String numberUniversitiesApplied ) {
        this.numberUniversitiesApplied = numberUniversitiesApplied;
    }

    public String getNumberProgramsApplied() {
        return numberProgramsApplied;
    }

    public void setNumberProgramsApplied( String numberProgramsApplied ) {
        this.numberProgramsApplied = numberProgramsApplied;
    }

    public String getNumberInstitutionsApplied() {
        return numberInstitutionsApplied;
    }

    public void setNumberInstitutionsApplied( String numberInstitutionsApplied ) {
        this.numberInstitutionsApplied = numberInstitutionsApplied;
    }

    public String getNumberServiceTypesApplied() {
        return numberServiceTypesApplied;
    }

    public void setNumberServiceTypesApplied( String numberServiceTypesApplied ) {
        this.numberServiceTypesApplied = numberServiceTypesApplied;
    }
}
