package com.moh.application;

import java.util.Map;

import com.moh.common.AbstractBean;
import com.moh.data.dao.UserDAO;
import com.moh.utils.DataFormatter;
import com.moh.utils.Logger;

public class ReviewerPersonalInfoSSHelper extends AbstractBean {

    private String firstName;
    private String lastName;
    private String gender;
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
    private String email;

    public ReviewerPersonalInfoSSHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ReviewerPersonalInfoSSHelper" );
    }

    public String updateProfile() {
        populate();

        logger.debugPage( "/jsp/updateProfile00.jsp" );
        return "updateProfile00";
    }

    public String update() {
        logger.debugMethod( "update" );
        boolean result = false;

        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );

        String sPostalCode = postalCode.replaceAll( " ", EMPTY_STRING );
        sPostalCode = sPostalCode.replaceAll( "-", EMPTY_STRING );

        UserDAO userDAO = new UserDAO();
        result = userDAO.updateProfile( getEmail(), firstName, lastName, gender, ( short ) 0, EMPTY_STRING, streetNumber, streetName, aptNumber, city, sPostalCode, homePhone, officePhone, cellPhone, pagerNumber );

        if( result ) {
            addInfoMessage( "info_profile_updated_ok" );
        }
        else {
            addErrorMessage( "error_db_update_failed" );
        }

        logger.debugPage( "/jsp/updateProfile00.jsp" );
        return "updateProfile00";
    }

    public void populate() {
        logger.debugMethod( "populate" );

        UserDAO userDAO = new UserDAO();
        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );
        Map<String, Object> sql_result = userDAO.getProfile( getEmail() );

        DataFormatter df = new DataFormatter();

        setFirstName( ( String ) sql_result.get( "FirstName" ) );
        setLastName( ( String ) sql_result.get( "LastName" ) );
        setGender( ( String ) sql_result.get( "Gender" ) );
        setStreetNumber( ( String ) sql_result.get( "StreetNumber" ) );
        setStreetName( ( String ) sql_result.get( "StreetName" ) );
        setAptNumber( ( String ) sql_result.get( "AptNumber" ) );
        setCity( ( String ) sql_result.get( "City" ) );
        setPostalCode( df.postalCodeFormatter( ( String ) sql_result.get( "PostalCode" ) ) );
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

    public String getGender() {
        return gender;
    }

    public void setGender( String gender ) {
        this.gender = gender;
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
