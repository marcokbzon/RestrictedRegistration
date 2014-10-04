package com.moh.application;

import java.util.ArrayList;
import java.util.Map;

import com.moh.common.AbstractBean;
import com.moh.data.dao.EducationDAO;
import com.moh.data.dao.ListAaDAO;
import com.moh.utils.Logger;

public class EducationalInfoRQHelper extends AbstractBean {

    private String postGradYear;
    private String schoolResidency;
    private String pgProgram;
    private String programDirector;
    private String countryCode;
    private String countryName;
    private String provStateCode;
    private String medSchool;
    private String atlsIndicator;
    private String atlsYear;
    private String aclsIndicator;
    private String aclsYear;
    private String palsIndicator;
    private String palsYear;
    private String nalsIndicator;
    private String nalsYear;
    private String northernStreamIndicator;
    private String mccqe1Month;
    private String mccqe1Year;
    private String mccqe2Month;
    private String mccqe2Year;
    private String cpsoNumber;
    private String cmpaNumber;
    private String email;

    public EducationalInfoRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "EducationalInfoRQHelper" );
    }

    public String view() {
        populate();

        logger.debugPage( "/jsp/appForm02.jsp" );
        return "applicationForm02";
    }

    public void populate() {
        logger.debugMethod( "populate" );
        ArrayList<String> univProgInfo;
        EducationDAO educationDAO = new EducationDAO();
        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );
        Map<String, Object> sql_result = educationDAO.getEducation( getEmail() );

        if( sql_result.get( "PostGraduateYear" ) != null ) {
            setPostGradYear( "PGY" + sql_result.get( "PostGraduateYear" ) );
        }

        univProgInfo = educationDAO.getUnivProgNames( educationDAO.getUnivProgID( ( String ) sql_result.get( "UniversityID" ), ( String ) sql_result.get( "ProgramID" ) ) );

        if( univProgInfo != null & univProgInfo.size() > 0 ) {
            setSchoolResidency( univProgInfo.get( 0 ) );
            setPgProgram( univProgInfo.get( 1 ) );
            setProgramDirector( univProgInfo.get( 2 ) + " " + univProgInfo.get( 3 ) );
        }

        setCountryCode( ( String ) sql_result.get( "CountryCode" ) );
        if( countryCode != null  && ! countryCode.trim().equals(EMPTY_STRING)) {
            ListAaDAO listDAO = new ListAaDAO();
            setCountryName( "( " + listDAO.getCountryName( countryCode ) + " )" );
        }
        //setProvStateCode( ( String ) sql_result.get( "ProvStateCode" ) );
        setMedSchool( ( String ) sql_result.get( "MedicalSchool" ) );
        boolean northernStreamBoolean = ( Boolean ) sql_result.get( "NorthernStream" );

        if( northernStreamBoolean ) {
            setNorthernStreamIndicator( "YES" );
        }
        else {
            setNorthernStreamIndicator( "NO" );
        }

        if( !sql_result.get( "ATLSyear" ).equals( "0" ) ) {
            setAtlsYear( " in " + sql_result.get( "ATLSyear" ) );
        }
        if( !sql_result.get( "ACLSyear" ).equals( "0" ) ) {
            setAclsYear( " in " + sql_result.get( "ACLSyear" ) );
        }
        if( !sql_result.get( "PALSyear" ).equals( "0" ) ) {
            setPalsYear( " in " + sql_result.get( "PALSyear" ) );
        }
        if( !sql_result.get( "NALSyear" ).equals( "0" ) ) {
            setNalsYear( " in " + sql_result.get( "NALSyear" ) );
        }

        if( atlsYear == null || atlsYear.trim().equals(EMPTY_STRING) || atlsYear.equals( "0" ) ) {
            setAtlsIndicator( "NO" );
        }
        else {
            setAtlsIndicator( "YES" );
        }
        if( aclsYear == null || aclsYear.trim().equals(EMPTY_STRING) || aclsYear.equals( "0" ) ) {
            setAclsIndicator( "NO" );
        }
        else {
            setAclsIndicator( "YES" );
        }
        if( palsYear == null || palsYear.trim().equals(EMPTY_STRING) || palsYear.equals( "0" ) ) {
            setPalsIndicator( "NO" );
        }
        else {
            setPalsIndicator( "YES" );
        }
        if( nalsYear == null || nalsYear.trim().equals(EMPTY_STRING) || nalsYear.equals( "0" ) ) {
            setNalsIndicator( "NO" );
        }
        else {
            setNalsIndicator( "YES" );
        }

        setMccqe1Year( ( String ) sql_result.get( "MCCQE1year" ) );

        if( sql_result.get( "MCCQE1month" ) != null ) {
            setMccqe1Month( numberToMonth( ( String ) sql_result.get( "MCCQE1month" ) ) );
        }

        setMccqe2Year( ( String ) sql_result.get( "MCCQE2year" ) );

        if( sql_result.get( "MCCQE2month" ) != null ) {
            setMccqe2Month( numberToMonth( ( String ) sql_result.get( "MCCQE2month" ) ) );
        }

        setCpsoNumber( ( String ) sql_result.get( "CPSOnumber" ) );
        setCmpaNumber( ( String ) sql_result.get( "CMPAnumber" ) );
    }

    public String getPostGradYear() {
        return postGradYear;
    }

    public void setPostGradYear( String postGradYear ) {
        this.postGradYear = postGradYear;
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

    public String getSchoolResidency() {
        return schoolResidency;
    }

    public void setSchoolResidency( String schoolResidency ) {
        this.schoolResidency = schoolResidency;
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

    public String getPgProgram() {
        return pgProgram;
    }

    public void setPgProgram( String pgProgram ) {
        this.pgProgram = pgProgram;
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

    public String getMedSchool() {
        return medSchool;
    }

    public void setMedSchool( String medSchool ) {
        this.medSchool = medSchool;
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

    public String getMccqe1Month() {
        return mccqe1Month;
    }

    public void setMccqe1Month( String mccqe1Month ) {
        this.mccqe1Month = mccqe1Month;
    }

    public String getMccqe1Year() {
        return mccqe1Year;
    }

    public void setMccqe1Year( String mccqe1Year ) {
        this.mccqe1Year = mccqe1Year;
    }

    public String getCpsoNumber() {
        return cpsoNumber;
    }

    public void setCpsoNumber( String cpsoNumber ) {
        this.cpsoNumber = cpsoNumber;
    }

    public String getMccqe2Month() {
        return mccqe2Month;
    }

    public void setMccqe2Month( String mccqe2Month ) {
        this.mccqe2Month = mccqe2Month;
    }

    public String getMccqe2Year() {
        return mccqe2Year;
    }

    public void setMccqe2Year( String mccqe2Year ) {
        this.mccqe2Year = mccqe2Year;
    }

    public String getCmpaNumber() {
        return cmpaNumber;
    }

    public void setCmpaNumber( String cmpaNumber ) {
        this.cmpaNumber = cmpaNumber;
    }

    public String getProgramDirector() {
        return programDirector;
    }

    public void setProgramDirector( String programDirector ) {
        this.programDirector = programDirector;
    }

    public String getNorthernStreamIndicator() {
        return northernStreamIndicator;
    }

    public void setNorthernStreamIndicator( String northernStreamIndicator ) {
        this.northernStreamIndicator = northernStreamIndicator;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName( String countryName ) {
        this.countryName = countryName;
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
