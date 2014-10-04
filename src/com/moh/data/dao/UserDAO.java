package com.moh.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.HashMap;
import java.util.Map;

import com.moh.common._ParentDAO;
import com.moh.utils.Logger;

public class UserDAO extends _ParentDAO {

    private String ADD_USERINFO_SQL =
            "INSERT INTO UserInfo "
            + "( Email, Password, FirstName, LastName, Gender, YearOfBirth, UnivProgID, VisaResident, FileDirectory ) "
            + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )";
    private String ADD_USERROLE_SQL =
            "INSERT INTO UserRole "
            + "( Email, RoleID ) "
            + "VALUES ( ?, ? )";
    private String ADD_CONTACTINFO_SQL =
            "INSERT INTO ContactInfo "
            + "( Email, StreetNumber, StreetName, AptNumber, CountryCode, ProvStateCode, City, PostalCode, HomePhone, OfficePhone, CellPhone, PagerNumber ) "
            + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
    private String GET_PROFILE_SQL =
            "SELECT FirstName, LastName, Gender, YearOfBirth, VisaResident, StreetNumber, StreetName, AptNumber, City, "
            + "PostalCode, HomePhone, OfficePhone, CellPhone, PagerNumber "
            + "FROM UserInfo ui, UserRole ur, ContactInfo ci "
            + "WHERE ui.Email = ? "
            + "AND ui.Email = ur.Email "
            + "AND ui.Email = ci.Email";
    private String UPDATE_USERINFO_SQL =
            "UPDATE UserInfo "
            + "SET FirstName = ?, LastName = ?, Gender = ?, YearOfBirth = ?, UnivProgID = ?, VisaResident = ? "
            + "WHERE Email = ? ";
    private String UPDATE_USERINFO2_SQL =
            "UPDATE UserInfo "
            + "SET FirstName = ?, LastName = ?, Gender = ?, YearOfBirth = ?, VisaResident = ? "
            + "WHERE Email = ? ";
    private String UPDATE_CONTACTINFO_SQL =
            "UPDATE ContactInfo "
            + "SET StreetNumber = ?, StreetName = ?, AptNumber = ?, City = ?, "
            + "PostalCode = ?, HomePhone = ?, OfficePhone = ?, CellPhone = ?, PagerNumber = ? "
            + "WHERE Email = ?";
    private String GET_EMAIL_SQL =
            "SELECT Email "
            + "FROM Application "
            + "WHERE ApplicationID = ?";
    private String CHECK_UNIVPROGID_SQL =
            "SELECT UnivProgID "
            + "FROM UserInfo "
            + "WHERE Email = ?";
    private String CHECK_ROLES_SQL =
            "SELECT COUNT(*) AS RoleCount "
            + "FROM UserRole "
            + "WHERE Email = ? "
            + "AND RoleID != 'UMR'";
    private String GET_REVIEWER_INFO =
            "SELECT usr.FirstName, usr.LastName, rol.RoleID, rol.Description_EN "
            + "FROM UserInfo usr, UserRole uro, Role rol "
            + "WHERE usr.Email = uro.Email "
            + "AND uro.RoleID = rol.RoleID "
            + "AND usr.Email = ?";
    public String GET_RESIDENCY_STATUS =
            "SELECT VisaResident "
            + "FROM UserInfo "
            + "WHERE Email = ?";
    public String DELETE_CONTACTINFO_SQL =
            "DELETE FROM ContactInfo "
            + "WHERE Email = ?";
    public String DELETE_USERROLE_SQL =
            "DELETE FROM UserRole "
            + "WHERE Email = ?";
    public String DELETE_USERINFO_SQL =
            "DELETE FROM UserInfo "
            + "WHERE Email = ?";
    private String GET_RESIDENT_NAMES_SQL =
            "SELECT usr.FirstName, usr.LastName "
            + "FROM Application app, UserInfo usr "
            + "WHERE ApplicationID = ? "
            + "AND app.Email = usr.Email";
    
    private String email;

    public UserDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "UserDAO" );
    }

    public boolean removeProfile( String email ) {
        logger.debugMethod( "removeProfile" );
        boolean returnedValue = false;
        setEmail( email );

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( DELETE_CONTACTINFO_SQL );
            preparedStatement.setString( 1, getEmail() );

            int deleteCountCI = preparedStatement.executeUpdate();
            auditTransaction( DELETE_CONTACTINFO_SQL );

            preparedStatement.close();
            
            preparedStatement = connection.prepareStatement( DELETE_USERROLE_SQL );
            preparedStatement.setString( 1, getEmail() );

            int deleteCountUR = preparedStatement.executeUpdate();
            auditTransaction( DELETE_USERROLE_SQL );

            preparedStatement.close();
            
            preparedStatement = connection.prepareStatement( DELETE_USERINFO_SQL );
            preparedStatement.setString( 1, getEmail() );

            int deleteCountUI = preparedStatement.executeUpdate();
            auditTransaction( DELETE_USERINFO_SQL );

            if( ( deleteCountCI != 1 ) || ( deleteCountUR != 1 ) || ( deleteCountUI != 1 ) ) {
                logger.error( "Remove failed" );
                connection.rollback( savePoint );
            }
            else {
                connection.commit();
            }

            returnedValue = true;
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

    public boolean addProfile( String email, String password, String firstName, String lastName, String gender, short yob, String visaResident, String roleID,
            String streetNumber, String streetName, String aptNumber, String countryCode, String provStateCode,
            String city, String postalCode, String homePhone, String officePhone, String cellPhone, String pagerNumber ) {
        logger.debugMethod( "addProfile" );
        boolean returnedValue = false;
        setEmail( email );

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( ADD_USERINFO_SQL );

            preparedStatement.setString( 1, getEmail() );
            preparedStatement.setString( 2, password );
            preparedStatement.setString( 3, firstName );
            preparedStatement.setString( 4, lastName );
            preparedStatement.setString( 5, gender );
            preparedStatement.setInt( 6, yob );
            preparedStatement.setString( 7, EMPTY_STRING );
            preparedStatement.setString( 8, visaResident );
            preparedStatement.setString( 9, EMPTY_STRING );

            int insertCountUI = preparedStatement.executeUpdate();
            auditTransaction( ADD_USERINFO_SQL );

            preparedStatement.close();

            preparedStatement = connection.prepareStatement( ADD_USERROLE_SQL );

            preparedStatement.setString( 1, getEmail() );
            preparedStatement.setString( 2, roleID );

            int insertCountUR = preparedStatement.executeUpdate();
            auditTransaction( ADD_USERROLE_SQL );

            preparedStatement.close();
            
            preparedStatement = connection.prepareStatement( ADD_CONTACTINFO_SQL );

            preparedStatement.setString( 1, getEmail() );
            preparedStatement.setString( 2, streetNumber );
            preparedStatement.setString( 3, streetName );
            preparedStatement.setString( 4, aptNumber );
            preparedStatement.setString( 5, countryCode );
            preparedStatement.setString( 6, provStateCode );
            preparedStatement.setString( 7, city );
            preparedStatement.setString( 8, postalCode );
            preparedStatement.setString( 9, homePhone );
            preparedStatement.setString( 10, officePhone );
            preparedStatement.setString( 11, cellPhone );
            preparedStatement.setString( 12, pagerNumber );

            int insertCountCI = preparedStatement.executeUpdate();
            auditTransaction( ADD_CONTACTINFO_SQL );

            if( ( insertCountUI != 1 ) || ( insertCountUR != 1 ) || ( insertCountCI != 1 ) ) {
                logger.error( "Add failed" );
                connection.rollback( savePoint );
            }
            else {
                connection.commit();
            }

            returnedValue = true;
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

    public boolean updateProfile( String email, String firstName, String lastName, String gender, short yob, String visaResident, String streetNumber,
            String streetName, String aptNumber, String city, String postalCode, String homePhone,
            String officePhone, String cellPhone, String pagerNumber ) {
        logger.debugMethod( "updateProfile" );
        boolean returnedValue = false;
        setEmail( email );

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            if( universityProgramIdExists( getEmail() ) ) {
                preparedStatement = connection.prepareStatement( UPDATE_USERINFO2_SQL );

                preparedStatement.setString( 1, firstName );
                preparedStatement.setString( 2, lastName );
                preparedStatement.setString( 3, gender );
                preparedStatement.setInt( 4, yob );
                preparedStatement.setString( 5, visaResident );
                preparedStatement.setString( 6, getEmail() );
                
                auditTransaction( UPDATE_USERINFO2_SQL );
            }
            else {
                preparedStatement = connection.prepareStatement( UPDATE_USERINFO_SQL );

                preparedStatement.setString( 1, firstName );
                preparedStatement.setString( 2, lastName );
                preparedStatement.setString( 3, gender );
                preparedStatement.setInt( 4, yob );
                preparedStatement.setString( 5, EMPTY_STRING );
                preparedStatement.setString( 6, visaResident );
                preparedStatement.setString( 7, getEmail() );
                
                auditTransaction( UPDATE_USERINFO_SQL );
            }

            int updateCountUI = preparedStatement.executeUpdate();

            preparedStatement.close();

            preparedStatement = connection.prepareStatement( UPDATE_CONTACTINFO_SQL );

            preparedStatement.setString( 1, streetNumber );
            preparedStatement.setString( 2, streetName );
            preparedStatement.setString( 3, aptNumber );
            preparedStatement.setString( 4, city );
            preparedStatement.setString( 5, postalCode );
            preparedStatement.setString( 6, homePhone );
            preparedStatement.setString( 7, officePhone );
            preparedStatement.setString( 8, cellPhone );
            preparedStatement.setString( 9, pagerNumber );
            preparedStatement.setString( 10, getEmail() );

            int updateCountCI = preparedStatement.executeUpdate();
            auditTransaction( UPDATE_CONTACTINFO_SQL );

            if( ( updateCountUI != 1 ) || ( updateCountCI != 1 ) ) {
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

    public Map<String, Object> getProfile( String email ) {
        logger.debugMethod( "getProfile" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, Object> sqlResult = null;
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_PROFILE_SQL );
            preparedStatement.setString( 1, getEmail() );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                sqlResult = new HashMap<>();

                String firstName = resultSet.getString( "FirstName" );
                String lastName = resultSet.getString( "LastName" );
                String gender = resultSet.getString( "Gender" );
                short yearOfBirth = resultSet.getShort( "YearOfBirth" );
                String visaResident = resultSet.getString( "VisaResident" );
                String streetNumber = resultSet.getString( "StreetNumber" );
                String streetName = resultSet.getString( "StreetName" );
                String aptNumber = resultSet.getString( "AptNumber" );
                String city = resultSet.getString( "City" );
                String postalCode = resultSet.getString( "PostalCode" );
                String homePhone = resultSet.getString( "HomePhone" );
                String officePhone = resultSet.getString( "OfficePhone" );
                String cellPhone = resultSet.getString( "CellPhone" );
                String pagerNumber = resultSet.getString( "PagerNumber" );

                sqlResult.put( "FirstName", firstName );
                sqlResult.put( "LastName", lastName );
                sqlResult.put( "Gender", gender );
                sqlResult.put( "YearOfBirth", yearOfBirth );
                sqlResult.put( "VisaResident", visaResident );
                sqlResult.put( "StreetNumber", streetNumber );
                sqlResult.put( "StreetName", streetName );
                sqlResult.put( "AptNumber", aptNumber );
                sqlResult.put( "City", city );
                sqlResult.put( "PostalCode", postalCode );
                sqlResult.put( "HomePhone", homePhone );
                sqlResult.put( "OfficePhone", officePhone );
                sqlResult.put( "CellPhone", cellPhone );
                sqlResult.put( "PagerNumber", pagerNumber );
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

    public String getEmail( String applicationID ) {
        logger.debugMethod( "getEmail" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        setEmail( EMPTY_STRING );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_EMAIL_SQL );
            preparedStatement.setString( 1, applicationID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                setEmail( resultSet.getString( "Email" ) );
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

        return getEmail();
    }

    public boolean universityProgramIdExists( String email ) {
        logger.debugMethod( "universityProgramIdExists" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        boolean result = false;
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( CHECK_UNIVPROGID_SQL );
            preparedStatement.setString( 1, getEmail() ); // WARNING: This is the Resident email

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String univProgID = resultSet.getString( "UnivProgID" );

                if( !( univProgID == null || univProgID.trim().equals( EMPTY_STRING ) ) ) {
                    result = true;
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

        return result;
    }

    public int checkRoles( String email ) {
        logger.debugMethod( "checkRoles" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        int sqlResult = 0;
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( CHECK_ROLES_SQL );
            preparedStatement.setString( 1, getEmail() );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                sqlResult = resultSet.getInt( "RoleCount" );
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

    public Map<String, String> getReviewerInfo( String email ) {
        logger.debugMethod( "getReviewerInfo" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> sqlResult = null;
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_REVIEWER_INFO );
            preparedStatement.setString( 1, getEmail() );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                sqlResult = new HashMap<String, String>();

                String fullName = resultSet.getString( "FirstName" ) + " " + resultSet.getString( "LastName" );
                String roleID = resultSet.getString( "RoleID" );
                String roleDescription = resultSet.getString( "Description_EN" );

                sqlResult.put( "FullName", fullName );
                sqlResult.put( "Email", getEmail() );
                sqlResult.put( "RoleID", roleID );
                sqlResult.put( "RoleDescription", roleDescription );
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

    public boolean addRole( String email, String roleID ) {
        logger.debugMethod( "addRole" );
        boolean returnedValue = false;
        setEmail( email );

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( ADD_USERROLE_SQL );
            //preparedStatement = StatementFactory.getStatement( connection, ADD_USERROLE_SQL );

            preparedStatement.setString( 1, getEmail() );
            preparedStatement.setString( 2, roleID );

            int insertCountUR = preparedStatement.executeUpdate();
            //auditTransaction( preparedStatement );

            if( insertCountUR != 1 ) {
                logger.error( "Add failed" );
                connection.rollback( savePoint );
            }
            else {
                connection.commit();
            }

            returnedValue = true;
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

    public String isVisaResident( String email ) {
        logger.debugMethod( "isVisaResident" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String sqlResult = null;
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_RESIDENCY_STATUS );
            preparedStatement.setString( 1, getEmail() );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                sqlResult = resultSet.getString( "VisaResident" );
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

    public String getResidentName( String applicationID ) {
        logger.debugMethod( "getResidentName" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String sqlResult = EMPTY_STRING;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_RESIDENT_NAMES_SQL );
            preparedStatement.setString( 1, applicationID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String firstName = resultSet.getString( "FirstName" );
                String lastName = resultSet.getString( "LastName" );

                sqlResult = firstName + " " + lastName;
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
