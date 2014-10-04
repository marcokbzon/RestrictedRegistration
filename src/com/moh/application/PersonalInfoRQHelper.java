package com.moh.application;

import java.util.Map;

import com.moh.common.AbstractBean;
import com.moh.data.dao.EducationDAO;
import com.moh.data.dao.SecurityDAO;
import com.moh.data.dao.UserDAO;
import com.moh.security.AESEncrypter;
import com.moh.utils.DataFormatter;
import com.moh.utils.Logger;

public class PersonalInfoRQHelper extends AbstractBean {

    private String firstName;
    private String lastName;
    private String email;
    private String roleID;
    private String password;
    private String passwordConfirm;
    private String gender;
    private String genderDesc;
    private short yearOfBirth;
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
    private boolean isResident;
    private boolean isAdministrator;
    private boolean isReviewer;

    public PersonalInfoRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "PersonalInfoRQHelper" );
    }

    public String create() {
        logger.debugMethod( "create" );
        boolean result = false;

        if( !getPassword().equals( getPasswordConfirm() ) ) {
            addErrorMessage( "error_nomatching_passwords" );
            
            logger.debugPage( "/jsp/createProfile.jsp" );
            return "createProfile";
        }

        SecurityDAO securityDAO = new SecurityDAO();

        if( securityDAO.emailExists( getEmail() ) ) {
            addErrorMessage( "error_email_already_exists" );
            
            logger.debugPage( "/jsp/createProfile.jsp" );
            return "createProfile";
        }

        AESEncrypter aesEncrypter = new AESEncrypter();
        aesEncrypter.init();

        String sPostalCode = postalCode.replaceAll( " ", EMPTY_STRING );
        sPostalCode = sPostalCode.replaceAll( "-", EMPTY_STRING );
        
        setVisaStudentIndicator( EMPTY_STRING );

        UserDAO userDAO = new UserDAO();
        result = userDAO.addProfile( getEmail(), aesEncrypter.convertToHexDec( password ), firstName, lastName, gender, yearOfBirth, visaStudentIndicator, getRoleID(), streetNumber, streetName, aptNumber, countryCode, provStateCode, city, sPostalCode, homePhone, officePhone, cellPhone, pagerNumber );

        if( result ) {
            addInfoMessage( "info_profile_created_ok" );
            addToSession( SESSION_EMAIL, getEmail() );
        }
        else {
            addErrorMessage( "error_db_insert_failed" );
        }

        EducationDAO educationDAO = new EducationDAO();
        result = educationDAO.addEducation( getEmail(), 0, EMPTY_STRING, EMPTY_STRING, false, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, 0, 0, 0, 0, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING );

        if( result ) {
            addInfoMessage( "info_education_added_ok" );
        }
        else {
            addErrorMessage( "error_db_insert_failed" );
        }
        
        logger.debugPage( "/jsp/login.jsp" );
        return "login";
    }

    public String view() {
        String visaResident = ( String ) getFromSession( SESSION_VISA_RESIDENT );

        if( visaResident != null && ! visaResident.trim().equals(EMPTY_STRING) ) {
            if( visaResident.equals( "1" ) ) {
                addErrorMessage( "error_visa_resident_not_allowed" );

                return "mainPage";
            }
        }

        if( existsInSession( SESSION_APPLICATIONID ) ) {
            removeFromSession( SESSION_APPLICATIONID );
        }

        populate();

        logger.debugPage( "/jsp/appForm01.jsp" );
        return "applicationForm01";
    }

    public void populate() {
        logger.debugMethod( "populate" );
        setIsResident( false );
        setIsAdministrator( false );
        setIsReviewer( false );

        if( ( ( String ) getFromSession( SESSION_ROLEID ) ).equals( ROLE_RESIDENT ) ) {
            setIsResident( true );
        }
        else {
            if( ( ( String ) getFromSession( SESSION_ROLEID ) ).equals( ROLE_ADMIN ) ) {
                setIsAdministrator( true );
            }
            else {
                setIsReviewer( true );
            }
        }

        UserDAO userDAO = new UserDAO();
        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );
        Map<String, Object> sql_result = userDAO.getProfile( getEmail() );

        DataFormatter df = new DataFormatter();

        setFirstName( ( String ) sql_result.get( "FirstName" ) );
        setLastName( ( String ) sql_result.get( "LastName" ) );
        setGender( ( String ) sql_result.get( "Gender" ) );
        if( getGender().equals( "M" ) ) {
            setGenderDesc( "Male" );
        }
        else {
            setGenderDesc( "Female" );
        }
        setYearOfBirth( Short.parseShort( sql_result.get( "YearOfBirth" ).toString() ) );

        String visaResident = ( String ) sql_result.get( "VisaResident" );

        if( visaResident == null || visaResident.trim().equals(EMPTY_STRING) ) {
        }
        else {
            if( visaResident.equals( "1" ) ) {
                setVisaStudentIndicator( "Yes" );
            }
            else {
                setVisaStudentIndicator( "No" );
            }
        }

        setStreetNumber( ( String ) sql_result.get( "StreetNumber" ) );
        setStreetName( ( String ) sql_result.get( "StreetName" ) );
        setAptNumber( ( String ) sql_result.get( "AptNumber" ) );
        setCity( ( String ) sql_result.get( "City" ) );
        setPostalCode( ( String ) sql_result.get( "PostalCode" ) );
        setHomePhone( df.phoneFormatter( ( String ) sql_result.get( "HomePhone" ) ) );
        setOfficePhone( df.phoneFormatter( ( String ) sql_result.get( "OfficePhone" ) ) );
        setCellPhone( df.phoneFormatter( ( String ) sql_result.get( "CellPhone" ) ) );
        setPagerNumber( df.phoneFormatter( ( String ) sql_result.get( "PagerNumber" ) ) );
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
            return nullsToString( email ).trim().toLowerCase();
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

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm( String passwordConfirm ) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getGender() {
        return gender;
    }

    public void setGender( String gender ) {
        this.gender = gender;
    }

    public short getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth( short yearOfBirth ) {
        this.yearOfBirth = yearOfBirth;
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

    public String getRoleID() {
        if( getFromSession( SESSION_ROLEID ) == null || getFromSession( SESSION_ROLEID ).toString().trim().equals( EMPTY_STRING ) ) {
            roleID = "UMR";
        }
        else {
            roleID = ( String ) getFromSession( SESSION_ROLEID );
        }
        return roleID;
    }

    public void setRoleID( String roleID ) {
        this.roleID = roleID;
    }

    public boolean getIsResident() {
        return isResident;
    }

    public void setIsResident( boolean isResident ) {
        this.isResident = isResident;
    }

    public boolean getIsAdministrator() {
        return isAdministrator;
    }

    public void setIsAdministrator( boolean isAdministrator ) {
        this.isAdministrator = isAdministrator;
    }

    public boolean getIsReviewer() {
        return isReviewer;
    }

    public void setIsReviewer( boolean isReviewer ) {
        this.isReviewer = isReviewer;
    }

    public String getGenderDesc() {
        return genderDesc;
    }

    public void setGenderDesc( String genderDesc ) {
        this.genderDesc = genderDesc;
    }

    public String getVisaStudentIndicator() {
        return visaStudentIndicator;
    }

    public void setVisaStudentIndicator( String visaStudentIndicator ) {
        this.visaStudentIndicator = visaStudentIndicator;
    }
}
