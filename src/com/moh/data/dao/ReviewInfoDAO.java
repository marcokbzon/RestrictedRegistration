package com.moh.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.moh.common._ParentDAO;
import com.moh.utils.Logger;

public class ReviewInfoDAO extends _ParentDAO {

    private String GET_APPLICATION_HEADER_SQL =
            "SELECT usr.FirstName, usr.LastName, sta.UpdatedOn, uni.Name_EN AS UniversityName, ins.Name_EN AS InstitutionName, "
            + "srv.Name_EN AS ServiceTypeName, app.SupervisorName "
            + "FROM Application app, UserInfo usr, Status sta, University uni, UnivProg uniprg, Institution ins, ServiceType srv "
            + "WHERE app.ApplicationID = ? "
            + "AND app.ApplicationID = sta.ApplicationID "
            + "AND sta.CodeID = 'SBM' "
            + "AND app.Email = usr.Email "
            + "AND usr.UnivProgID = uniprg.UnivProgID "
            + "AND uniprg.UniversityID = uni.UniversityID "
            + "AND app.InstitutionID = ins.InstitutionID "
            + "AND app.ServiceTypeID = srv.ServiceTypeID";
    private String GET_APPLICANT_INFO_SQL =
            "SELECT adr.Email, adr.StreetNumber, adr.StreetName, adr.AptNumber, adr.City, prv.Name_EN AS ProvinceName, adr.PostalCode, cnt.Name_EN AS CountryName, "
            + "adr.HomePhone, adr.PagerNumber, edu.PostGraduateYear, prg.Description_EN AS ProgramName, edu.MedicalSchool, edu.ATLSyear, edu.ACLSyear, "
            + "edu.NorthernStream, edu.PALSyear, edu.NALSyear, edu.MCCQE1, edu.MCCQE2, edu.CPSOnumber, edu.CMPAnumber, uniprg.Email AS ProgDirEmail  "
            + "FROM Application app, ContactInfo adr, Education edu, Country cnt, ProvinceState prv, UserInfo usr, UnivProg uniprg, Program prg "
            + "WHERE app.ApplicationID = ? "
            + "AND usr.Email = app.Email "
            + "AND usr.Email = adr.Email "
            + "AND usr.Email = edu.Email "
            + "AND adr.CountryCode = cnt.CountryCode "
            + "AND adr.ProvStateCode = prv.ProvStateCode "
            + "AND usr.UnivProgID = uniprg.UnivProgID "
            + "AND uniprg.ProgramID = prg.ProgramID";
    private String GET_PROG_DIRECTOR_INFO_SQL =
            "SELECT FirstName, LastName "
            + "FROM UserInfo "
            + "WHERE Email = ?";
    private String CHECK_APPLICATION_REJECT_SQL =
            "SELECT COUNT(*) AS RejectCount "
            + "FROM Status "
            + "WHERE CodeID = 'RJT' "
            + "AND ApplicationID = ?";
    
    private String email;
    private String progDirEmail;

    public ReviewInfoDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ReviewInfoDAO" );
    }

    public Map<String, String> getHeaderInfo( String applicationID ) {
        logger.debugMethod( "getHeaderInfo" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> headerInfo = new HashMap<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_APPLICATION_HEADER_SQL );
            preparedStatement.setString( 1, applicationID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String firstName = resultSet.getString( "FirstName" );
                String lastName = resultSet.getString( "LastName" );
                String updatedOn = resultSet.getString( "UpdatedOn" );
                String universityName = resultSet.getString( "UniversityName" );
                String institutionName = resultSet.getString( "InstitutionName" );
                String serviceTypeName = resultSet.getString( "ServiceTypeName" );
                String supervisorName = resultSet.getString( "SupervisorName" );

                headerInfo.put( "ApplicantName", firstName + " " + lastName );
                headerInfo.put( "ApplicationDate", updatedOn );
                headerInfo.put( "UniversityName", universityName );
                headerInfo.put( "InstitutionName", institutionName );
                headerInfo.put( "ServiceTypeName", serviceTypeName );
                headerInfo.put( "SupervisorName", supervisorName );
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );
        }
        finally {
            try {
                if( resultSet != null ) {
                    resultSet.close();
                }
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }

        return headerInfo;
    }

    public Map<String, String> getApplicantInfo( String applicationID ) {
        logger.debugMethod( "getApplicantInfo" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> appInfo = new HashMap<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_APPLICANT_INFO_SQL );
            preparedStatement.setString( 1, applicationID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                setEmail( resultSet.getString( "Email" ) );
                String streetNumber = resultSet.getString( "StreetNumber" );
                String streetName = resultSet.getString( "StreetName" );
                String aptNumber = resultSet.getString( "AptNumber" );
                String city = resultSet.getString( "City" );
                String provinceName = resultSet.getString( "ProvinceName" );
                String postalCode = resultSet.getString( "PostalCode" );
                String countryName = resultSet.getString( "CountryName" );
                String homePhone = resultSet.getString( "HomePhone" );
                String pagerNumber = resultSet.getString( "PagerNumber" );
                String postGraduateYear = EMPTY_STRING + resultSet.getInt( "PostGraduateYear" );
                String programName = resultSet.getString( "ProgramName" );
                String medicalSchool = resultSet.getString( "MedicalSchool" );
                boolean northernStreamBoolean = resultSet.getBoolean( "NorthernStream" );
                int atlsYear = resultSet.getInt( "ATLSyear" );
                int aclsYear = resultSet.getInt( "ACLSyear" );
                int palsYear = resultSet.getInt( "PALSyear" );
                int nalsYear = resultSet.getInt( "NALSyear" );
                String mccqe1 = resultSet.getString( "MCCQE1" );
                String mccqe2 = resultSet.getString( "MCCQE2" );
                String cpsoNumber = resultSet.getString( "CPSOnumber" );
                String cmpaNumber = resultSet.getString( "CMPAnumber" );

                String address = streetNumber + " " + streetName;
                String mccqe1Date = EMPTY_STRING;
                String mccqe2Date = EMPTY_STRING;

                if( aptNumber != null && ! aptNumber.trim().equals(EMPTY_STRING) ) {
                    address = aptNumber + " - " + streetNumber + " " + streetName;
                }

                appInfo.put( "Email", getEmail() );
                appInfo.put( "Address", address );
                appInfo.put( "City", city );
                appInfo.put( "ProvinceName", provinceName );
                appInfo.put( "PostalCode", postalCode );
                appInfo.put( "CountryName", countryName );

                appInfo.put( "HomePhone", homePhone );
                appInfo.put( "PagerNumber", pagerNumber );

                appInfo.put( "PostGraduateYear", postGraduateYear );
                appInfo.put( "ProgramName", programName );
                appInfo.put( "MedicalSchool", medicalSchool );

                if( northernStreamBoolean ) {
                    appInfo.put( "NorthernStream", "Yes" );
                }
                else {
                    appInfo.put( "NorthernStream", "No" );
                }

                if( atlsYear == 0 ) {
                    appInfo.put( "ATLSyear", EMPTY_STRING );
                    appInfo.put( "ATLSindicator", "No" );
                }
                else {
                    appInfo.put( "ATLSyear", EMPTY_STRING + atlsYear );
                    appInfo.put( "ATLSindicator", "Yes" );
                }
                if( aclsYear == 0 ) {
                    appInfo.put( "ACLSyear", EMPTY_STRING );
                    appInfo.put( "ACLSindicator", "No" );
                }
                else {
                    appInfo.put( "ACLSyear", EMPTY_STRING + aclsYear );
                    appInfo.put( "ACLSindicator", "Yes" );
                }
                if( palsYear == 0 ) {
                    appInfo.put( "PALSyear", EMPTY_STRING );
                    appInfo.put( "PALSindicator", "No" );
                }
                else {
                    appInfo.put( "PALSyear", EMPTY_STRING + palsYear );
                    appInfo.put( "PALSindicator", "Yes" );
                }
                if( nalsYear == 0 ) {
                    appInfo.put( "NALSyear", EMPTY_STRING );
                    appInfo.put( "NALSindicator", "No" );
                }
                else {
                    appInfo.put( "NALSyear", EMPTY_STRING + nalsYear );
                    appInfo.put( "NALSindicator", "Yes" );
                }

                if( mccqe1 != null && ! mccqe1.trim().equals(EMPTY_STRING) ) {
                    mccqe1Date = numberToMonth( mccqe1.substring( 4 ) ) + ", " + mccqe1.substring( 0, 4 );
                }
                if( mccqe2 != null && ! mccqe2.trim().equals(EMPTY_STRING) ) {
                    mccqe2Date = numberToMonth( mccqe2.substring( 4 ) ) + ", " + mccqe2.substring( 0, 4 );
                }

                appInfo.put( "MCCQE1date", mccqe1Date );
                appInfo.put( "MCCQE2date", mccqe2Date );

                appInfo.put( "CPSOnumber", cpsoNumber );
                appInfo.put( "CMPAnumber", cmpaNumber );

                //
                setProgDirEmail( resultSet.getString( "ProgDirEmail" ) );
                appInfo.put( "ProgDirector", getProgDirFullname( getProgDirEmail() ) );
                appInfo.put( "ProgDirEmail", getProgDirEmail() );
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );
        }
        finally {
            try {
                if( resultSet != null ) {
                    resultSet.close();
                }
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }

        return appInfo;
    }

    public boolean hasApplicationBeenRejected( String applicationID ) {
        logger.debugMethod( "hasApplicationBeenRejected" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        boolean rejectedApp = true;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( CHECK_APPLICATION_REJECT_SQL );
            preparedStatement.setString( 1, applicationID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                int rejectCount = resultSet.getInt( "RejectCount" );

                if( rejectCount == 0 ) {
                    rejectedApp = false;
                }
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );
        }
        finally {
            try {
                if( resultSet != null ) {
                    resultSet.close();
                }
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }

        return rejectedApp;
    }

    public String getProgDirFullname( String email ) {
        logger.debugMethod( "getProgDirFullname" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String result = EMPTY_STRING;
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_PROG_DIRECTOR_INFO_SQL );
            preparedStatement.setString( 1, getEmail() );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String firstName = resultSet.getString( "FirstName" );
                String lastName = resultSet.getString( "LastName" );

                result = firstName.trim() + " " + lastName.trim();
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );
        }
        finally {
            try {
                if( resultSet != null ) {
                    resultSet.close();
                }
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }

        return result;
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
