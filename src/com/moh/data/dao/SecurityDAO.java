package com.moh.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.HashMap;
import java.util.Map;

import com.moh.common._ParentDAO;
import com.moh.security.AESEncrypter;
import com.moh.utils.Logger;

public class SecurityDAO extends _ParentDAO {

    private String AUTHENTICATE_SQL =
            "SELECT FirstName, LastName "
            + "FROM UserInfo "
            + "WHERE Email = ? "
            + "AND Password = ?";
    private String AUTHORIZE_SQL =
            "SELECT r.RoleID, r.Description_EN "
            + "FROM Role r, UserRole ur "
            + "WHERE r.RoleID = ur.RoleID "
            + "AND ur.Email = ?";
    /*  *** R-140521 ***
    private String GET_PASSWORD_SQL =
            "SELECT ui.Password, ui.FirstName, ui.LastName "
            + "FROM UserInfo ui, ContactInfo ci "
            + "WHERE ui.Email = ? "
            + "AND ui.YearOfBirth = ? "
            + "AND ci.PostalCode = ? "
            + "AND ui.Email = ci.Email";
    */
    private String GET_PASSWORD_SQL =
            "SELECT ui.Password, ui.FirstName, ui.LastName "
            + "FROM UserInfo ui, ContactInfo ci "
            + "WHERE ui.Email = ? "
            + "AND ui.Email = ci.Email";
    private String CHECK_EMAIL_SQL =
            "SELECT COUNT(*) AS email_count "
            + "FROM UserInfo "
            + "WHERE Email = ?";
    //Hospitals and other Health Institutions
    private String GET_INSTITUTIONID_SQL =
            "SELECT InstitutionID "
            + "FROM InstReviewer "
            + "WHERE Email = ?";
    private String GET_UNIVERSITYID_SQL =
            "SELECT UniversityID "
            + "FROM UnivReviewer "
            + "WHERE Email = ?";
    private String GET_PG_UNIVERSITYID_SQL =
            "SELECT UniversityID "
            + "FROM UnivProg "
            + "WHERE Email = ?";
    private String CHANGE_PASSWORD_SQL =
            "UPDATE UserInfo "
            + "SET Password = ? "
            + "WHERE Email = ?";
    private boolean oneRoleOnly = true;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

    public SecurityDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "SecurityDAO" );
    }

    public boolean authenticate( String email, String password ) {
        logger.debugMethod( "authenticate" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        boolean credentialsAreValid = false;
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( AUTHENTICATE_SQL );
            preparedStatement.setString( 1, getEmail() );
            preparedStatement.setString( 2, password );

            resultSet = preparedStatement.executeQuery();

            if( resultSet.next() ) {
                String userLN = resultSet.getString( "LastName" );

                if( userLN != null && ! userLN.trim().equals(EMPTY_STRING) ) {
                    credentialsAreValid = true;

                    setFirstName( resultSet.getString( "FirstName" ) );
                    setLastName( resultSet.getString( "LastName" ) );
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

        return credentialsAreValid;
    }

    public Map<String, String> authorize( String email ) {
        logger.debugMethod( "authorize" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> roles = new HashMap<>();
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( AUTHORIZE_SQL );
            preparedStatement.setString( 1, getEmail() );

            resultSet = preparedStatement.executeQuery();
            
            while( resultSet.next() ) {
                String roleID = resultSet.getString( "RoleID" );
                String roleDesc = resultSet.getString( "Description_EN" );
                
                roles.put( roleID, roleDesc );
            }

            if( roles.size() > 1 ) {
                setOneRoleOnly( false );
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

        return roles;
    }

    public boolean isOneRoleOnly() {
        return oneRoleOnly;
    }

    public void setOneRoleOnly( boolean oneRoleOnly ) {
        this.oneRoleOnly = oneRoleOnly;
    }

    //public boolean checkInputValues( String email, short yearOfBirth, String postalCode ) { *** R-140521 ***
    public boolean checkInputValues( String email ) {
        logger.debugMethod( "checkInputValues" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        boolean inputIsValid = false;
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_PASSWORD_SQL );
            preparedStatement.setString( 1, getEmail() );
            // preparedStatement.setShort( 2, yearOfBirth );  *** R-140521 ***
            // preparedStatement.setString( 3, postalCode );  *** R-140521 ***

            resultSet = preparedStatement.executeQuery();

            if( resultSet.next() ) {
                AESEncrypter aesEncrypter = new AESEncrypter();
                aesEncrypter.init();

                String password = aesEncrypter.convertFromHexDec( resultSet.getString( "Password" ) );

                if( !password.trim().equals( EMPTY_STRING ) ) {
                    inputIsValid = true;

                    setFirstName( resultSet.getString( "FirstName" ) );
                    setLastName( resultSet.getString( "LastName" ) );
                    setPassword( password );
                }
            }
        }
        catch( SQLException sex ) {
            logger.exception( sex );
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

        return inputIsValid;
    }

    public boolean emailExists( String email ) {
        logger.debugMethod( "emailExists" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        boolean accountAlreadyExists = true;
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( CHECK_EMAIL_SQL );
            preparedStatement.setString( 1, getEmail() );

            resultSet = preparedStatement.executeQuery();

            if( resultSet.next() ) {
                int emailCount = resultSet.getInt( "email_count" );

                if( emailCount == 0 ) {
                    accountAlreadyExists = false;
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

        return accountAlreadyExists;
    }

    public String getInstitutionID( String email ) {
        logger.debugMethod( "getInstitutionID" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String result = EMPTY_STRING;
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_INSTITUTIONID_SQL );
            preparedStatement.setString( 1, getEmail() );

            resultSet = preparedStatement.executeQuery();

            if( resultSet.next() ) {
                result = resultSet.getString( "InstitutionID" );
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

    public String getUniversityID( String email ) {
        logger.debugMethod( "getInstitutionID" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String result = EMPTY_STRING;
        setEmail( email );

        try {
            connection = getConnection();

            if( ( ( String ) getFromSession( SESSION_ROLEID ) ).equals( ROLE_DIRECTOR ) ) {
                preparedStatement = connection.prepareStatement( GET_PG_UNIVERSITYID_SQL );
            }
            else {
                preparedStatement = connection.prepareStatement( GET_UNIVERSITYID_SQL );
            }
            preparedStatement.setString( 1, getEmail() );

            resultSet = preparedStatement.executeQuery();

            if( resultSet.next() ) {
                result = resultSet.getString( "UniversityID" );
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

    public boolean changePassword( String email, String newPassword ) {
        logger.debugMethod( "changePassword" );
        boolean returnedValue = false;
        setEmail( email );

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int updateCountUI = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( CHANGE_PASSWORD_SQL );

            preparedStatement.setString( 1, newPassword );
            preparedStatement.setString( 2, getEmail() );

            updateCountUI = preparedStatement.executeUpdate();

            if( updateCountUI != 1 ) {
                logger.error( "Update failed" );
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

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
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
