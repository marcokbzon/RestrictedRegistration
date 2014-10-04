package com.moh.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.moh.common._ParentDAO;
import com.moh.utils.Logger;

public class EducationDAO extends _ParentDAO {

    private String GET_EDUCATION_SQL =
            "SELECT ed.PostGraduateYear, ed.CountryCode, ed.ProvStateCode, ed.MedicalSchool, ed.NorthernStream, "
            + "ed.ATLSyear, ed.ACLSyear, ed.PALSyear, ed.NALSyear, ed.MCCQE1, ed.MCCQE2, ed.CPSOnumber, ed.CMPAnumber "
            + "FROM Education ed, UserInfo ui "
            + "WHERE ui.Email = ? "
            + "AND ui.Email = ed.Email";
    private String GET_UNIVPROG_SQL =
            "SELECT UniversityID, ProgramID, Email "
            + "FROM UnivProg "
            + "WHERE UnivProgID = ? ";
    private String GET_UNIVPROGNAMES_SQL =
            "SELECT u.Name_EN, p.Description_EN, ui.FirstName, ui.LastName "
            + "FROM UnivProg up, University u, Program p, UserInfo ui "
            + "WHERE up.UnivProgID = ? "
            + "AND up.UniversityID = u.UniversityID "
            + "AND up.ProgramID = p.ProgramID "
            + "AND up.Email = ui.Email";
    //R-140529: Add Active=1 to select criteria
    private String GET_UNIVPROGID_SQL =
            "SELECT UnivProgID "
            + "FROM UnivProg "
            + "WHERE UniversityID = ? "
            + "AND ProgramID = ? "
            + "AND Active = 1";
    private String GET_USERINFO_SQL =
            "SELECT FirstName, LastName, Gender, YearOfBirth, UnivProgID "
            + "FROM UserInfo "
            + "WHERE Email = ?";
    private String ADD_EDUCATION_SQL =
            "INSERT INTO Education "
            + "( Email, PostGraduateYear, CountryCode, ProvStateCode, MedicalSchool, NorthernStream, MCCQE1, MCCQE2, CPSOnumber, CMPAnumber, ATLSyear, ACLSyear, PALSyear, NALSyear ) "
            + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
    private String UPDATE_USERINFO_SQL =
            "UPDATE UserInfo "
            + "SET UnivProgID = ? "
            + "WHERE Email = ? ";
    private String UPDATE_EDUCATION_SQL =
            "UPDATE Education "
            + "SET PostGraduateYear = ?, CountryCode = ?, ProvStateCode = ?, MedicalSchool = ?, NorthernStream = ?, MCCQE1 = ?, MCCQE2 = ?, "
            + "CPSOnumber = ?, CMPAnumber = ?, ATLSyear = ?, ACLSyear = ?, PALSyear = ?, NALSyear = ? "
            + "WHERE Email = ? ";
    
    private String email;

    public EducationDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "EducationDAO" );
    }

    public String getUnivProgID( String univID, String progID ) {
        logger.debugMethod( "getUnivProgID" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String sqlResult = EMPTY_STRING;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_UNIVPROGID_SQL );
            preparedStatement.setString( 1, univID );
            preparedStatement.setString( 2, progID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                sqlResult = resultSet.getString( "UnivProgID" );
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

        return sqlResult;
    }

    public ArrayList<String> getUnivProgNames( String univProgId ) {
        logger.debugMethod( "getUnivProgNames" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        ArrayList<String> sqlResult = null;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_UNIVPROGNAMES_SQL );
            preparedStatement.setString( 1, univProgId );

            resultSet = preparedStatement.executeQuery();

            sqlResult = new ArrayList<>();

            while( resultSet.next() ) {
                sqlResult.add( 0, resultSet.getString( "Name_EN" ) );
                sqlResult.add( 1, resultSet.getString( "Description_EN" ) );
                sqlResult.add( 2, resultSet.getString( "FirstName" ) );
                sqlResult.add( 3, resultSet.getString( "LastName" ) );
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

        return sqlResult;
    }

    public Map<String, Object> getEducation( String email ) {
        logger.debugMethod( "getEducation" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, Object> sqlResult = null;
        String univProgID = EMPTY_STRING;
        String progDirEmail = EMPTY_STRING;
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_EDUCATION_SQL );
            preparedStatement.setString( 1, getEmail() );

            resultSet = preparedStatement.executeQuery();
            
            sqlResult = new HashMap<>();

            while( resultSet.next() ) {

                String postGraduateYear = EMPTY_STRING + resultSet.getInt( "PostGraduateYear" );
                String countryCode = resultSet.getString( "CountryCode" );
                String provStateCode = resultSet.getString( "ProvStateCode" );
                String medicalSchool = resultSet.getString( "MedicalSchool" );
                boolean northernStream = resultSet.getBoolean( "NorthernStream" );
                String atlsYear = EMPTY_STRING + resultSet.getInt( "ATLSyear" );
                String aclsYear = EMPTY_STRING + resultSet.getInt( "ACLSyear" );
                String palsYear = EMPTY_STRING + resultSet.getInt( "PALSyear" );
                String nalsYear = EMPTY_STRING + resultSet.getInt( "NALSyear" );

                String mccqe1Year = EMPTY_STRING;
                String mccqe1Month = EMPTY_STRING;
                String mccqe1 = resultSet.getString( "MCCQE1" );
                if( mccqe1 != null && ! mccqe1.trim().equals(EMPTY_STRING) ) {
                    mccqe1Year = mccqe1.substring( 0, 4 );
                    mccqe1Month = mccqe1.substring( 4 );
                }
                String mccqe2Year = EMPTY_STRING;
                String mccqe2Month = EMPTY_STRING;
                String mccqe2 = resultSet.getString( "MCCQE2" );
                if( mccqe2 != null && ! mccqe2.trim().equals(EMPTY_STRING) ) {
                    mccqe2Year = mccqe2.substring( 0, 4 );
                    mccqe2Month = mccqe2.substring( 4 );
                }

                String cpsoNumber = resultSet.getString( "CPSOnumber" );
                String cmpaNumber = resultSet.getString( "CMPAnumber" );

                sqlResult.put( "PostGraduateYear", postGraduateYear );
                sqlResult.put( "CountryCode", countryCode );
                sqlResult.put( "ProvStateCode", provStateCode );
                sqlResult.put( "MedicalSchool", medicalSchool );
                sqlResult.put( "NorthernStream", northernStream );
                sqlResult.put( "ATLSyear", atlsYear );
                sqlResult.put( "ACLSyear", aclsYear );
                sqlResult.put( "PALSyear", palsYear );
                sqlResult.put( "NALSyear", nalsYear );
                sqlResult.put( "MCCQE1year", mccqe1Year );
                sqlResult.put( "MCCQE1month", mccqe1Month );
                sqlResult.put( "MCCQE2year", mccqe2Year );
                sqlResult.put( "MCCQE2month", mccqe2Month );
                sqlResult.put( "CPSOnumber", cpsoNumber );
                sqlResult.put( "CMPAnumber", cmpaNumber );
            }
            
            preparedStatement.close();
            resultSet.close();

            preparedStatement = connection.prepareStatement( GET_USERINFO_SQL );
            preparedStatement.setString( 1, getEmail() );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                univProgID = resultSet.getString( "UnivProgID" );
            }

            preparedStatement.close();
            resultSet.close();
            
            if( univProgID == null || univProgID.trim().equals(EMPTY_STRING) ) {
                sqlResult.put( "UniversityID", EMPTY_STRING );
                sqlResult.put( "ProgramID", EMPTY_STRING );
                sqlResult.put( "ProgramDirector", EMPTY_STRING );
            }
            else {
                preparedStatement = connection.prepareStatement( GET_UNIVPROG_SQL );
                preparedStatement.setString( 1, univProgID );

                resultSet = preparedStatement.executeQuery();

                while( resultSet.next() ) {
                    String universityID = resultSet.getString( "UniversityID" );
                    String programID = resultSet.getString( "ProgramID" );
                    progDirEmail = resultSet.getString( "Email" );

                    sqlResult.put( "UniversityID", universityID );
                    sqlResult.put( "ProgramID", programID );
                }

                preparedStatement.close();
                resultSet.close();
                
                preparedStatement = connection.prepareStatement( GET_USERINFO_SQL );
                preparedStatement.setString( 1, progDirEmail );

                resultSet = preparedStatement.executeQuery();

                while( resultSet.next() ) {
                    String pgDirFirstName = resultSet.getString( "FirstName" );
                    String pgDirLastName = resultSet.getString( "LastName" );

                    sqlResult.put( "ProgramDirector", pgDirFirstName + " " + pgDirLastName );
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

        return sqlResult;
    }

    public boolean addEducation( String email, int postGraduateYear, String universityID, String programID, boolean northernStream,
            String countryCode, String provStateCode, String medicalSchool, int atlsYear, int aclsYear, int palsYear, int nalsYear,
            String mccqe1, String mccqe2, String cpsoNumber, String cmpaNumber ) {
        logger.debugMethod( "addEducation" );
        boolean returnedValue = false;
        setEmail( email );

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;
        ResultSet resultSet = null;
        String univProgID = EMPTY_STRING;

        try {
            int insertCountED = 0;
            int updateCountUI = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( ADD_EDUCATION_SQL );

            preparedStatement.setString( 1, getEmail() );
            preparedStatement.setInt( 2, postGraduateYear );
            preparedStatement.setString( 3, countryCode );
            preparedStatement.setString( 4, provStateCode );
            preparedStatement.setString( 5, medicalSchool );
            preparedStatement.setBoolean( 6, northernStream );
            preparedStatement.setString( 7, mccqe1 );
            preparedStatement.setString( 8, mccqe2 );
            preparedStatement.setString( 9, cpsoNumber );
            preparedStatement.setString( 10, cmpaNumber );
            preparedStatement.setInt( 11, atlsYear );
            preparedStatement.setInt( 12, aclsYear );
            preparedStatement.setInt( 13, palsYear );
            preparedStatement.setInt( 14, nalsYear );

            insertCountED = preparedStatement.executeUpdate();

            preparedStatement.close();
            
            if( ( universityID != null && ! universityID.trim().equals(EMPTY_STRING) ) & ( programID != null && ! programID.trim().equals(EMPTY_STRING) ) ) {
                preparedStatement = connection.prepareStatement( GET_UNIVPROGID_SQL );
                preparedStatement.setString( 1, universityID );
                preparedStatement.setString( 2, programID );

                resultSet = preparedStatement.executeQuery();

                while( resultSet.next() ) {
                    univProgID = resultSet.getString( "UnivProgID" );
                }

                preparedStatement.close();
                resultSet.close();
                
                preparedStatement = connection.prepareStatement( UPDATE_USERINFO_SQL );

                preparedStatement.setString( 1, univProgID );
                preparedStatement.setString( 2, getEmail() );

                updateCountUI = preparedStatement.executeUpdate();
            }
            else {
                preparedStatement = connection.prepareStatement( UPDATE_USERINFO_SQL );

                preparedStatement.setString( 1, EMPTY_STRING );
                preparedStatement.setString( 2, getEmail() );

                preparedStatement.executeUpdate();
                
                updateCountUI = 1;
            }

            if( ( insertCountED != 1 ) || ( updateCountUI != 1 ) ) {
                logger.error( "Add failed" );
                connection.rollback( savePoint );
            }
            else {
                connection.commit();
                returnedValue = true;
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );

            try {
                connection.rollback( savePoint );
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }
        finally {
            try {
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( Exception ex ) {
                logger.exception( ex );
            }
        }

        return returnedValue;
    }

    public boolean updateEducation( String email, int postGraduateYear, String universityID, String programID, boolean northernStream,
            String countryCode, String provStateCode, String medicalSchool, int atlsYear, int aclsYear, int palsYear, int nalsYear,
            String mccqe1, String mccqe2, String cpsoNumber, String cmpaNumber ) {
        logger.debugMethod( "updateEducation" );
        boolean returnedValue = false;
        setEmail( email );

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;
        ResultSet resultSet = null;
        String univProgID = EMPTY_STRING;

        try {
            int updateCountED = 0;
            int updateCountUI = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( UPDATE_EDUCATION_SQL );

            if( countryCode != null && ! countryCode.trim().equals(EMPTY_STRING) ) {
                if( countryCode.length() == ( COL_LENGTH_COUNTRYCODE + COL_SORT_LENGTH ) ) {
                    countryCode = countryCode.substring( COL_SORT_LENGTH );
                }
            }
            if( provStateCode != null && ! provStateCode.trim().equals(EMPTY_STRING) ) {
                if( provStateCode.length() == ( COL_LENGTH_PROVSTATECODE + COL_SORT_LENGTH ) ) {
                    provStateCode = provStateCode.substring( COL_SORT_LENGTH );
                }
            }

            preparedStatement.setInt( 1, postGraduateYear );
            preparedStatement.setString( 2, countryCode );
            preparedStatement.setString( 3, provStateCode );
            preparedStatement.setString( 4, medicalSchool );
            preparedStatement.setBoolean( 5, northernStream );
            preparedStatement.setString( 6, mccqe1 );
            preparedStatement.setString( 7, mccqe2 );
            preparedStatement.setString( 8, cpsoNumber );
            preparedStatement.setString( 9, cmpaNumber );
            preparedStatement.setInt( 10, atlsYear );
            preparedStatement.setInt( 11, aclsYear );
            preparedStatement.setInt( 12, palsYear );
            preparedStatement.setInt( 13, nalsYear );
            preparedStatement.setString( 14, getEmail() );

            updateCountED = preparedStatement.executeUpdate();
            
            preparedStatement.close();

            if( ( universityID != null && ! universityID.trim().equals(EMPTY_STRING) ) & ( programID != null && ! programID.trim().equals(EMPTY_STRING) ) ) {
                if( universityID.length() == ( COL_LENGTH_UNIVERSITYID + COL_SORT_LENGTH ) ) {
                    universityID = universityID.substring( COL_SORT_LENGTH );
                }
                if( programID.length() == ( COL_LENGTH_PROGRAMID + COL_SORT_LENGTH ) ) {
                    programID = programID.substring( COL_SORT_LENGTH );
                }

                preparedStatement = connection.prepareStatement( GET_UNIVPROGID_SQL );
                preparedStatement.setString( 1, universityID );
                preparedStatement.setString( 2, programID );

                resultSet = preparedStatement.executeQuery();

                while( resultSet.next() ) {
                    univProgID = resultSet.getString( "UnivProgID" );
                }

                preparedStatement.close();
                resultSet.close();
                
                preparedStatement = connection.prepareStatement( UPDATE_USERINFO_SQL );

                preparedStatement.setString( 1, univProgID );
                preparedStatement.setString( 2, getEmail() );

                updateCountUI = preparedStatement.executeUpdate();
            }
            else {
                preparedStatement = connection.prepareStatement( UPDATE_USERINFO_SQL );

                preparedStatement.setString( 1, EMPTY_STRING );
                preparedStatement.setString( 2, getEmail() );

                preparedStatement.executeUpdate();
                
                updateCountUI = 1;
            }

            if( ( updateCountED != 1 ) || ( updateCountUI != 1 ) ) {
                logger.error( "Update failed" );
                connection.rollback( savePoint );
            }
            else {
                connection.commit();
                returnedValue = true;
            }
        }
        catch( SQLException sex ) {
            logger.exception( sex );

            try {
                connection.rollback( savePoint );
            }
            catch( SQLException sex2 ) {
                logger.exception( sex2 );
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );

            try {
                connection.rollback( savePoint );
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }
        finally {
            try {
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if ( resultSet != null ) {
                	resultSet.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( Exception ex ) {
                logger.exception( ex );
            }
        }

        return returnedValue;
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
}
