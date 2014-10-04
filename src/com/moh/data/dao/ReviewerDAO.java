package com.moh.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import com.moh.common._ParentDAO;
import com.moh.utils.Logger;

public class ReviewerDAO extends _ParentDAO {

    // SUPERVISORS
    // check if Supervisor already in table
    private String CHECK_INSTREVIEWER_SQL =
            "SELECT InstitutionID "
            + "FROM InstReviewer "
            + "WHERE Email = ?";
    // add Supervisor to table
    private String ADD_INSTREVIEWER_SQL =
            "INSERT INTO InstReviewer "
            + "( InstitutionID, Email ) "
            + "VALUES ( ?, ? )";
    // PROGRAM DIRECTORS
    // check if email already in table
    private String CHECK_UNIVPROG1_SQL =
            "SELECT UnivProgID "
            + "FROM UnivProg "
            + "WHERE Email = ?";
    // check if university and program already in table
    //TODO: Add Active column
    private String CHECK_UNIVPROG2_SQL =
            "SELECT UnivProgID "
            + "FROM UnivProg "
            + "WHERE UniversityID = ? "
            + "AND ProgramID = ?";
    // add Program Director to table
    //TODO: Add Active column
    private String ADD_UNIVPROG_SQL =
            "INSERT INTO UnivProg "
            + "( UnivProgID, UniversityID, ProgramID, Email, Active ) "
            + "VALUES ( ?, ?, ?, ?, ? )";
    private String GENERATE_UNIVPROGID_SQL =
            "SELECT MAX( UnivProgID ) AS MaxUnivProgID "
            + "FROM UnivProg";
    // PGME DEANS and COMMITTEE MEMBERS
    // check if Dean already in table
    private String CHECK_UNIVREVIEWER_SQL =
            "SELECT UniversityID "
            + "FROM UnivReviewer "
            + "WHERE Email = ?";
    // add Dean to table
    private String ADD_UNIVREVIEWER_SQL =
            "INSERT INTO UnivReviewer "
            + "( UniversityID, Email, Active ) "
            + "VALUES ( ?, ?, ? )";
    private String CHECK_UNIV_ASSIGNED_SQL =
            "SELECT urw.Email "
            + "FROM UnivReviewer urw, UserRole url "
            + "WHERE urw.Email = url.Email "
            + "AND urw.Active = 1 "
            + "AND urw.UniversityID = ? "
            + "AND url.RoleID = ?";
    private String DEACTIVATE_UNIV_REVIEWER_SQL =
            "UPDATE UnivReviewer "
            + "SET Active = 0 "
            + "WHERE UniversityID = ? "
            + "AND Email = ?";
    private String DEACTIVATE_UNIV_PROG_SQL =
            "UPDATE UnivProg "
            + "SET Active = 0 "
            + "WHERE UnivProgID = ?";
    private String previousReviewerEmail = EMPTY_STRING;
    private String previousUnivProgID = EMPTY_STRING;
    private String email;

    public ReviewerDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ReviewerDAO" );
    }

    public boolean institutionReviewerExists( String email ) {
        logger.debugMethod( "institutionReviewerExists" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        boolean result = false;
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( CHECK_INSTREVIEWER_SQL );
            preparedStatement.setString( 1, getEmail() );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String institutionID = resultSet.getString( "InstitutionID" );

                if( !( institutionID == null || institutionID.trim().equals( EMPTY_STRING ) ) ) {
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

    public boolean universityReviewerExists( String email ) {
        logger.debugMethod( "universityReviewerExists" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        boolean result = false;
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( CHECK_UNIVREVIEWER_SQL );
            preparedStatement.setString( 1, getEmail() );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String universityID = resultSet.getString( "UniversityID" );

                if( !( universityID == null || universityID.trim().equals( EMPTY_STRING ) ) ) {
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

    public boolean universityProgramExists( String param1, String param2 ) {
        logger.debugMethod( "universityProgramExists" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        boolean result = false;

        try {
            connection = getConnection();

            if( param2 == null || param2.trim().equals(EMPTY_STRING) ) {
                preparedStatement = connection.prepareStatement( CHECK_UNIVPROG1_SQL );
                preparedStatement.setString( 1, param1 ); // WARNING: This is the Program Director email
            }
            else {
                preparedStatement = connection.prepareStatement( CHECK_UNIVPROG2_SQL );
                preparedStatement.setString( 1, param1 ); // UniversityID
                preparedStatement.setString( 2, param2 ); // ProgramID
            }


            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String univProgID = resultSet.getString( "UnivProgID" );

                if( !( univProgID == null || univProgID.trim().equals( EMPTY_STRING ) ) ) {
                    setPreviousUnivProgID( univProgID );
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

    public boolean universityAlreadyAssigned( String universityID, String roleID ) {
        logger.debugMethod( "universityAlreadyAssigned" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        boolean result = false;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( CHECK_UNIV_ASSIGNED_SQL );
            preparedStatement.setString( 1, universityID );
            preparedStatement.setString( 2, roleID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                setEmail( resultSet.getString( "Email" ) );

                if( !( email == null || email.trim().equals( EMPTY_STRING ) ) ) {
                    setPreviousReviewerEmail( getEmail() );
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

    public boolean addInsttutionReviewerInfo( String institutionID, String email ) {
        logger.debugMethod( "addInsttutionReviewerInfo" );
        boolean returnedValue = false;
        setEmail( email );

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int insertCountDA = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( ADD_INSTREVIEWER_SQL );

            preparedStatement.setString( 1, institutionID );
            preparedStatement.setString( 2, getEmail() );

            insertCountDA = preparedStatement.executeUpdate();

            if( insertCountDA != 1 ) {
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

    public boolean addUniversityReviewerInfo( String universityID, String email ) {
        logger.debugMethod( "addUniversityReviewerInfo" );
        boolean returnedValue = false;
        setEmail( email );

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int insertCountDA = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( ADD_UNIVREVIEWER_SQL );

            preparedStatement.setString( 1, universityID );
            preparedStatement.setString( 2, getEmail() );
            preparedStatement.setBoolean( 3, true );

            insertCountDA = preparedStatement.executeUpdate();

            if( insertCountDA != 1 ) {
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

    public boolean addUniversityProgramInfo( String universityID, String programID, String email ) {
        logger.debugMethod( "addUniversityProgramInfo" );
        boolean returnedValue = false;
        setEmail( email );

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int insertCountDA = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( ADD_UNIVPROG_SQL );

            preparedStatement.setString( 1, generateUniversityProgramID() );
            preparedStatement.setString( 2, universityID );
            preparedStatement.setString( 3, programID );
            preparedStatement.setString( 4, getEmail() );
            preparedStatement.setBoolean( 5, true );

            insertCountDA = preparedStatement.executeUpdate();

            if( insertCountDA != 1 ) {
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

    public boolean deActivateUnivProgInfo( String univProgID ) {
        logger.debugMethod( "deActivateUnivProgInfo" );
        boolean returnedValue = false;

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int updateCount = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( DEACTIVATE_UNIV_PROG_SQL );
            preparedStatement.setString( 1, univProgID );

            updateCount = preparedStatement.executeUpdate();

            if( updateCount != 1 ) {
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

    public boolean deActivateUnivReviewer( String universityID, String email ) {
        logger.debugMethod( "deActivateUnivReviewer" );
        boolean returnedValue = false;
        setEmail( email );

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int updateCount = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( DEACTIVATE_UNIV_REVIEWER_SQL );
            preparedStatement.setString( 1, universityID );
            preparedStatement.setString( 2, getEmail() );

            updateCount = preparedStatement.executeUpdate();

            if( updateCount != 1 ) {
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

    public String generateUniversityProgramID() {
        logger.debugMethod( "generateUniversityProgramID" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        int intResult = 0;
        String result = "0";

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GENERATE_UNIVPROGID_SQL );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String maxUnivProg = resultSet.getString( "MaxUnivProgID" );

                if( maxUnivProg == null || maxUnivProg.trim().equals( EMPTY_STRING ) ) {
                    intResult = 0;
                }
                else {
                    intResult = Integer.parseInt( maxUnivProg );
                }

                result = EMPTY_STRING + ( intResult + 1 );
                
                while ( result.length() < COL_LENGTH_UNIVPROGID ) {
        			result = "0" + result;
        		}
            }

            if( result.equals( "0" ) ) {
                result = "1";

                while ( result.length() < COL_LENGTH_UNIVPROGID ) {
        			result = "0" + result;
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

    public String getPreviousReviewerEmail() {
        return previousReviewerEmail;
    }

    public void setPreviousReviewerEmail( String previousReviewerEmail ) {
        this.previousReviewerEmail = previousReviewerEmail;
    }

    public String getPreviousUnivProgID() {
        return previousUnivProgID;
    }

    public void setPreviousUnivProgID( String previousUnivProgID ) {
        this.previousUnivProgID = previousUnivProgID;
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
