package com.moh.review;

import java.util.Map;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ReviewInfoDAO;
import com.moh.utils.Logger;

public class AppInfoRQHelper extends AbstractBean {

    private String address;
    private String city;
    private String province;
    private String postalCode;
    private String country;
    private String email;
    private String homePhone;
    private String pagerNumber;
    private String pgYear;
    private String pgProgram;
    private String medicalSchool;
    private String atlsIndicator;
    private String atlsYear;
    private String aclsIndicator;
    private String aclsYear;
    private String palsIndicator;
    private String palsYear;
    private String nalsIndicator;
    private String nalsYear;
    private String northernStream;
    private String mccqe1Date;
    private String mccqe2Date;
    private String cpsoNumber;
    private String cmpaNumber;
    private String employerName;
    private String employerProgram;
    private String supervisorName;
    private String progDirector;
    private String progDirEmail;

    public AppInfoRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ApplicationInfoRQHelper" );

        view();
    }

    public String view() {
        logger.debugMethod( "view" );

        ReviewInfoDAO reviewInfoDAO = new ReviewInfoDAO();
        Map<String, String> appInfo = reviewInfoDAO.getApplicantInfo( ( String ) getFromSession( SESSION_APPLICATIONID ) );

        setAddress( appInfo.get( "Address" ) );
        setCity( appInfo.get( "City" ) );
        setProvince( appInfo.get( "ProvinceName" ) );
        setPostalCode( appInfo.get( "PostalCode" ) );
        setCountry( appInfo.get( "CountryName" ) );
        setEmail( appInfo.get( "Email" ) );
        setHomePhone( appInfo.get( "HomePhone" ) );
        setPagerNumber( appInfo.get( "PagerNumber" ) );

        setPgYear( appInfo.get( "PostGraduateYear" ) );
        setPgProgram( appInfo.get( "ProgramName" ) );
        setMedicalSchool( appInfo.get( "MedicalSchool" ) );

        setNorthernStream( appInfo.get( "NorthernStream" ) );

        setAtlsIndicator( appInfo.get( "ATLSindicator" ) );
        setAtlsYear( appInfo.get( "ATLSyear" ) );
        setAclsIndicator( appInfo.get( "ACLSindicator" ) );
        setAclsYear( appInfo.get( "ACLSyear" ) );
        setPalsIndicator( appInfo.get( "PALSindicator" ) );
        setPalsYear( appInfo.get( "PALSyear" ) );
        setNalsIndicator( appInfo.get( "NALSindicator" ) );
        setNalsYear( appInfo.get( "NALSyear" ) );

        setMccqe1Date( appInfo.get( "MCCQE1date" ) );
        setMccqe2Date( appInfo.get( "MCCQE2date" ) );

        setCpsoNumber( appInfo.get( "CPSOnumber" ) );
        setCmpaNumber( appInfo.get( "CMPAnumber" ) );

        setProgDirector( appInfo.get( "ProgDirector" ) );
        setProgDirEmail( appInfo.get( "ProgDirEmail" ) );

        logger.debugPage( "/jsp/appReview01.jsp" );
        return "appReview01";
    }

    public String getAddress() {
        return address;
    }

    public void setAddress( String address ) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity( String city ) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince( String province ) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode( String postalCode ) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry( String country ) {
        this.country = country;
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

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone( String homePhone ) {
        this.homePhone = homePhone;
    }

    public String getPagerNumber() {
        return pagerNumber;
    }

    public void setPagerNumber( String pagerNumber ) {
        this.pagerNumber = pagerNumber;
    }

    public String getPgYear() {
        return pgYear;
    }

    public void setPgYear( String pgYear ) {
        this.pgYear = pgYear;
    }

    public String getPgProgram() {
        return pgProgram;
    }

    public void setPgProgram( String pgProgram ) {
        this.pgProgram = pgProgram;
    }

    public String getMedicalSchool() {
        return medicalSchool;
    }

    public void setMedicalSchool( String medicalSchool ) {
        this.medicalSchool = medicalSchool;
    }

    public String getAtlsIndicator() {
        return atlsIndicator;
    }

    public void setAtlsIndicator( String atlsIndicator ) {
        this.atlsIndicator = atlsIndicator;
    }

    public String getAtlsYear() {
        return atlsYear;
    }

    public void setAtlsYear( String atlsYear ) {
        this.atlsYear = atlsYear;
    }

    public String getAclsIndicator() {
        return aclsIndicator;
    }

    public void setAclsIndicator( String aclsIndicator ) {
        this.aclsIndicator = aclsIndicator;
    }

    public String getAclsYear() {
        return aclsYear;
    }

    public void setAclsYear( String aclsYear ) {
        this.aclsYear = aclsYear;
    }

    public String getPalsIndicator() {
        return palsIndicator;
    }

    public void setPalsIndicator( String palsIndicator ) {
        this.palsIndicator = palsIndicator;
    }

    public String getPalsYear() {
        return palsYear;
    }

    public void setPalsYear( String palsYear ) {
        this.palsYear = palsYear;
    }

    public String getNalsIndicator() {
        return nalsIndicator;
    }

    public void setNalsIndicator( String nalsIndicator ) {
        this.nalsIndicator = nalsIndicator;
    }

    public String getNalsYear() {
        return nalsYear;
    }

    public void setNalsYear( String nalsYear ) {
        this.nalsYear = nalsYear;
    }

    public String getMccqe1Date() {
        return mccqe1Date;
    }

    public void setMccqe1Date( String mccqe1Date ) {
        this.mccqe1Date = mccqe1Date;
    }

    public String getMccqe2Date() {
        return mccqe2Date;
    }

    public void setMccqe2Date( String mccqe2Date ) {
        this.mccqe2Date = mccqe2Date;
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

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName( String employerName ) {
        this.employerName = employerName;
    }

    public String getEmployerProgram() {
        return employerProgram;
    }

    public void setEmployerProgram( String employerProgram ) {
        this.employerProgram = employerProgram;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName( String supervisorName ) {
        this.supervisorName = supervisorName;
    }

    public String getNorthernStream() {
        return northernStream;
    }

    public void setNorthernStream( String northernStream ) {
        this.northernStream = northernStream;
    }

    public String getProgDirector() {
        return progDirector;
    }

    public void setProgDirector( String progDirector ) {
        this.progDirector = progDirector;
    }

    public String getProgDirEmail() {
        return progDirEmail;
    }

    public void setProgDirEmail( String progDirEmail ) {
        this.progDirEmail = progDirEmail;
    }
}
