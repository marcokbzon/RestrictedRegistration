package com.moh.data.bean;

import com.moh.common.AbstractBean;
import com.moh.data.dao.DatabaseCountDAO;
import com.moh.utils.Logger;

public class DatabaseCountBean extends AbstractBean {

    private String countryCount;
    private String provinceStateCount;
    private String universityCount;
    private String programCount;
    private String roleCount;
    private String codeCount;
    private String institutionCount;
    private String serviceTypeCount;
    private String lhinCount;
    private String classificationCount;
    private String agreementCount;
    private String instLhinCount;
    private String instClassificationCount;
    private String instServiceTypeCount;
    private String userInfoCount;
    private String userRoleCount;
    DatabaseCountDAO databaseCountDAO;

    public DatabaseCountBean() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "DatabaseCountBean" );

        databaseCountDAO = new DatabaseCountDAO();
    }

    public String getCountryCount() {
        countryCount = databaseCountDAO.getNumberOfRecords( TABLE_COUNTRY );

        return countryCount;
    }

    public void setCountryCount( String countryCount ) {
        this.countryCount = countryCount;
    }

    public String getProvinceStateCount() {
        provinceStateCount = databaseCountDAO.getNumberOfRecords( TABLE_PROVINCESTATE );

        return provinceStateCount;
    }

    public void setProvinceStateCount( String provinceStateCount ) {
        this.provinceStateCount = provinceStateCount;
    }

    public String getUniversityCount() {
        universityCount = databaseCountDAO.getNumberOfRecords( TABLE_UNIVERSITY );

        return universityCount;
    }

    public void setUniversityCount( String universityCount ) {
        this.universityCount = universityCount;
    }

    public String getProgramCount() {
        programCount = databaseCountDAO.getNumberOfRecords( TABLE_PROGRAM );

        return programCount;
    }

    public void setProgramCount( String programCount ) {
        this.programCount = programCount;
    }

    public String getRoleCount() {
        roleCount = databaseCountDAO.getNumberOfRecords( TABLE_ROLE );

        return roleCount;
    }

    public void setRoleCount( String roleCount ) {
        this.roleCount = roleCount;
    }

    public String getCodeCount() {
        codeCount = databaseCountDAO.getNumberOfRecords( TABLE_CODE );

        return codeCount;
    }

    public void setCodeCount( String codeCount ) {
        this.codeCount = codeCount;
    }

    public String getInstitutionCount() {
        institutionCount = databaseCountDAO.getNumberOfRecords( TABLE_INSTITUTION );

        return institutionCount;
    }

    public void setInstitutionCount( String institutionCount ) {
        this.institutionCount = institutionCount;
    }

    public String getServiceTypeCount() {
        serviceTypeCount = databaseCountDAO.getNumberOfRecords( TABLE_SERVICETYPE );

        return serviceTypeCount;
    }

    public void setServiceTypeCount( String serviceTypeCount ) {
        this.serviceTypeCount = serviceTypeCount;
    }

    public String getLhinCount() {
        lhinCount = databaseCountDAO.getNumberOfRecords( TABLE_LHIN );

        return lhinCount;
    }

    public void setLhinCount( String lhinCount ) {
        this.lhinCount = lhinCount;
    }

    public String getClassificationCount() {
        classificationCount = databaseCountDAO.getNumberOfRecords( TABLE_CLASSIFICATION );

        return classificationCount;
    }

    public void setClassificationCount( String classificationCount ) {
        this.classificationCount = classificationCount;
    }

    public String getAgreementCount() {
        agreementCount = databaseCountDAO.getNumberOfRecords( TABLE_AGREEMENT );

        return agreementCount;
    }

    public void setAgreementCount( String agreementCount ) {
        this.agreementCount = agreementCount;
    }

    public String getInstLhinCount() {
        instLhinCount = databaseCountDAO.getNumberOfRecords( TABLE_INSTLHIN );

        return instLhinCount;
    }

    public void setInstLhinCount( String instLhinCount ) {
        this.instLhinCount = instLhinCount;
    }

    public String getInstClassificationCount() {
        instClassificationCount = databaseCountDAO.getNumberOfRecords( TABLE_INSTCLASSIFICATION );

        return instClassificationCount;
    }

    public void setInstClassificationCount( String instClassificationCount ) {
        this.instClassificationCount = instClassificationCount;
    }

    public String getInstServiceTypeCount() {
        instServiceTypeCount = databaseCountDAO.getNumberOfRecords( TABLE_INSTSERVICETYPE );

        return instServiceTypeCount;
    }

    public void setInstServiceTypeCount( String instServiceTypeCount ) {
        this.instServiceTypeCount = instServiceTypeCount;
    }

    public String getUserInfoCount() {
        userInfoCount = databaseCountDAO.getNumberOfRecords( TABLE_USERINFO );

        return userInfoCount;
    }

    public void setUserInfoCount( String userInfoCount ) {
        this.userInfoCount = userInfoCount;
    }

    public String getUserRoleCount() {
        userRoleCount = databaseCountDAO.getNumberOfRecords( TABLE_USERROLE );

        return userRoleCount;
    }

    public void setUserRoleCount( String userRoleCount ) {
        this.userRoleCount = userRoleCount;
    }
}
