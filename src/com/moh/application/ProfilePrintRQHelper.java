package com.moh.application;

import java.util.ArrayList;
import java.util.Map;

import com.moh.common.AbstractBean;
import com.moh.data.dao.EducationDAO;
import com.moh.data.dao.ListAaDAO;
import com.moh.data.dao.UserDAO;
import com.moh.utils.DataFormatter;
import com.moh.utils.Logger;

public class ProfilePrintRQHelper extends AbstractBean {

    //Personal Info
    private String firstName;
    private String lastName;
    private String email;
    private String visaStudentIndicator;
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
    public ProfilePrintRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ProfileInfoRQHelper" );
    }

    public String print() {
        logger.debugMethod( "print" );

        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );

        /*
         * PERSONAL INFORMATION
         */
        UserDAO userDAO = new UserDAO();
        Map<String, Object> sql_resultP = userDAO.getProfile( getEmail() );

        DataFormatter df = new DataFormatter();

        setFirstName( ( String ) sql_resultP.get( "FirstName" ) );
        setLastName( ( String ) sql_resultP.get( "LastName" ) );

        String visaResident = ( String ) sql_resultP.get( "VisaResident" );

        if( visaResident != null && ! visaResident.trim().equals(EMPTY_STRING) ) {
            if( visaResident.equals( "1" ) ) {
                setVisaStudentIndicator( "Yes" );
            }
            else {
                setVisaStudentIndicator( "No" );
            }
        }

        setStreetNumber( ( String ) sql_resultP.get( "StreetNumber" ) );
        setStreetName( ( String ) sql_resultP.get( "StreetName" ) );
        setAptNumber( ( String ) sql_resultP.get( "AptNumber" ) );
        if( aptNumber.trim().length() > 0 ) {
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
        ArrayList<String> univProgInfo;
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

        if( countryCode != null && ! countryCode.trim().equals(EMPTY_STRING) ) {
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
        boolean northernStreamBoolean = ( Boolean ) sql_resultE.get( "NorthernStream" );

        if( northernStreamBoolean ) {
            setNorthernStream( " ( Northern Stream )" );
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

        logger.debugPage( "/jsp/appFormPrint.jsp" );
        return "appFormPrint";
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

    public String getEdCountryName() {
        return edCountryName;
    }

    public void setEdCountryName( String edCountryName ) {
        this.edCountryName = edCountryName;
    }

    public String getVisaStudentIndicator() {
        return visaStudentIndicator;
    }

    public void setVisaStudentIndicator( String visaStudentIndicator ) {
        this.visaStudentIndicator = visaStudentIndicator;
    }
}
