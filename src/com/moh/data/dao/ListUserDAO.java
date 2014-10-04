package com.moh.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.moh.common._ParentDAO;
import com.moh.data.bean.AdminUserListByRoleData;
import com.moh.utils.Logger;

public class ListUserDAO extends _ParentDAO {

    private String GET_RESIDENTS_INCOMPLETE_SQL =
            "SELECT usr.FirstName, usr.LastName, usr.Email, rol.Description_EN AS RoleName "
            + "FROM UserInfo usr, UserRole url, Role rol "
            + "WHERE usr.Email = url.Email "
            + "AND url.RoleID = rol.RoleID "
            + "AND url.RoleID = 'UMR' "
            + "AND usr.UnivProgID IS NULL "
            + "ORDER BY LastName, FirstName, RoleName";
    private String GET_RESIDENTS_SQL =
            "SELECT usr.FirstName, usr.LastName, usr.Email, uni.Name_EN AS UniversityName, prg.Description_EN AS ProgramName, rol.Description_EN AS RoleName "
            + "FROM UserInfo usr, UserRole url, Role rol, UnivProg upg, University uni, Program prg "
            + "WHERE usr.Email = url.Email "
            + "AND url.RoleID = rol.RoleID "
            + "AND url.RoleID = 'UMR' "
            + "AND usr.UnivProgID = upg.UnivProgID "
            + "AND upg.UniversityID = uni.UniversityID "
            + "AND upg.ProgramID = prg.ProgramID "
            + "ORDER BY LastName, FirstName, RoleName";
    private String GET_INIT_COUNTER =
            "SELECT COUNT(*) AS UserCount "
            + "FROM UserInfo usr, UserRole url, Role rol "
            + "WHERE usr.Email = url.Email "
            + "AND url.RoleID = rol.RoleID "
            + "AND url.RoleID = 'UMR'";
    private String GET_SUPERVISORS_SQL =
            "SELECT usr.FirstName, usr.LastName, usr.Email, ins.Name_EN AS InstitutionName, rol.Description_EN AS RoleName "
            + "FROM UserInfo usr, UserRole url, Role rol, InstReviewer irw, Institution ins "
            + "WHERE usr.Email = url.Email "
            + "AND url.RoleID = rol.RoleID "
            + "AND url.RoleID = 'ISV' "
            + "AND usr.Email = irw.Email "
            + "AND irw.InstitutionID = ins.InstitutionID "
            + "ORDER BY LastName, FirstName, RoleName";
    private String GET_DIRECTORS_SQL =
            "SELECT usr.FirstName, usr.LastName, usr.Email, uni.Name_EN AS UniversityName, prg.Description_EN AS ProgramName, rol.Description_EN AS RoleName "
            + "FROM UserInfo usr, UserRole url, Role rol, UnivProg upg, University uni, Program prg "
            + "WHERE usr.Email = url.Email "
            + "AND url.RoleID = rol.RoleID "
            + "AND url.RoleID = 'UPD' "
            + "AND usr.Email = upg.Email "
            + "AND upg.UniversityID = uni.UniversityID "
            + "AND upg.ProgramID = prg.ProgramID "
            + "ORDER BY LastName, FirstName, RoleName";
    private String GET_DEANS_SQL =
            "SELECT usr.FirstName, usr.LastName, usr.Email, uni.Name_EN AS UniversityName, rol.Description_EN AS RoleName "
            + "FROM UserInfo usr, UserRole url, Role rol, UnivReviewer urw, University uni "
            + "WHERE usr.Email = url.Email "
            + "AND url.RoleID = rol.RoleID "
            + "AND url.RoleID = 'UDN' "
            + "AND usr.Email = urw.Email "
            + "AND urw.UniversityID = uni.UniversityID "
            + "ORDER BY LastName, FirstName, RoleName";
    private String GET_COMMITTEES_SQL =
            "SELECT usr.FirstName, usr.LastName, usr.Email, uni.Name_EN AS UniversityName, rol.Description_EN AS RoleName "
            + "FROM UserInfo usr, UserRole url, Role rol, UnivReviewer urw, University uni "
            + "WHERE usr.Email = url.Email "
            + "AND url.RoleID = rol.RoleID "
            + "AND url.RoleID = 'UMC' "
            + "AND usr.Email = urw.Email "
            + "AND urw.UniversityID = uni.UniversityID "
            + "ORDER BY LastName, FirstName, RoleName";
    private String GET_OTHER_USERS_SQL =
            "SELECT usr.FirstName, usr.LastName, usr.Email, rol.Description_EN AS RoleName "
            + "FROM UserInfo usr, UserRole url, Role rol "
            + "WHERE usr.Email = url.Email "
            + "AND url.RoleID = rol.RoleID "
            + "AND url.RoleID IN ('ADM', 'AMC') "
            + "ORDER BY LastName, FirstName, RoleName";
    private String GET_ALL_USERS_SQL =
            "SELECT usr.FirstName, usr.LastName, usr.Email, rol.Description_EN AS RoleName "
            + "FROM UserInfo usr, UserRole url, Role rol "
            + "WHERE usr.Email = url.Email "
            + "AND url.RoleID = rol.RoleID "
            + "ORDER BY LastName, FirstName, RoleName";

    private String email;
    
    public ListUserDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ListUserDAO" );
    }

    public List<AdminUserListByRoleData> getUserListByRole( String roleID ) {
        logger.debugMethod( "getUserListByRole" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<AdminUserListByRoleData> userList = new ArrayList<>();
        int counter = 1;

        try {
            connection = getConnection();

            if( roleID.equals( DEFAULT_ALL ) ) {
                preparedStatement = connection.prepareStatement( GET_ALL_USERS_SQL );

                resultSet = preparedStatement.executeQuery();

                while( resultSet.next() ) {
                    String firstName = resultSet.getString( "FirstName" );
                    String lastName = resultSet.getString( "LastName" );
                    setEmail( resultSet.getString( "Email" ) );
                    String info1 = EMPTY_STRING;
                    String info2 = EMPTY_STRING;
                    String role = resultSet.getString( "RoleName" );
                    String sCounter = EMPTY_STRING + counter++;

                    userList.add( new AdminUserListByRoleData( sCounter, firstName, lastName, getEmail(), info1, info2, role ) );
                }
            }
            else {
                if( roleID.equals( ROLE_RESIDENT ) ) {
                    preparedStatement = connection.prepareStatement( GET_RESIDENTS_SQL );

                    resultSet = preparedStatement.executeQuery();

                    while( resultSet.next() ) {
                        String firstName = resultSet.getString( "FirstName" );
                        String lastName = resultSet.getString( "LastName" );
                        setEmail( resultSet.getString( "Email" ) );
                        String info1 = resultSet.getString( "UniversityName" );
                        String info2 = resultSet.getString( "ProgramName" );
                        String role = resultSet.getString( "RoleName" );
                        String sCounter = EMPTY_STRING + counter++;

                        userList.add( new AdminUserListByRoleData( sCounter, firstName, lastName, getEmail(), info1, info2, role ) );
                    }

                    preparedStatement.close();
                    resultSet.close();
                    
                    preparedStatement = connection.prepareStatement( GET_RESIDENTS_INCOMPLETE_SQL );

                    resultSet = preparedStatement.executeQuery();

                    while( resultSet.next() ) {
                        String firstName = resultSet.getString( "FirstName" );
                        String lastName = resultSet.getString( "LastName" );
                        setEmail( resultSet.getString( "Email" ) );
                        String info1 = EMPTY_STRING;
                        String info2 = EMPTY_STRING;
                        String role = resultSet.getString( "RoleName" );
                        String sCounter = EMPTY_STRING + counter++;

                        userList.add( new AdminUserListByRoleData( sCounter, firstName, lastName, getEmail(), info1, info2, role ) );
                    }
                }
                else {
                    if( roleID.equals( ROLE_SUPERVISOR ) ) {
                        preparedStatement = connection.prepareStatement( GET_SUPERVISORS_SQL );

                        resultSet = preparedStatement.executeQuery();

                        while( resultSet.next() ) {
                            String firstName = resultSet.getString( "FirstName" );
                            String lastName = resultSet.getString( "LastName" );
                            setEmail( resultSet.getString( "Email" ) );
                            String info1 = resultSet.getString( "InstitutionName" );
                            String info2 = EMPTY_STRING;
                            String role = resultSet.getString( "RoleName" );
                            String sCounter = EMPTY_STRING + counter++;

                            userList.add( new AdminUserListByRoleData( sCounter, firstName, lastName, getEmail(), info1, info2, role ) );
                        }

                    }
                    else {
                        if( roleID.equals( ROLE_DIRECTOR ) ) {
                            preparedStatement = connection.prepareStatement( GET_DIRECTORS_SQL );

                            resultSet = preparedStatement.executeQuery();

                            while( resultSet.next() ) {
                                String firstName = resultSet.getString( "FirstName" );
                                String lastName = resultSet.getString( "LastName" );
                                setEmail( resultSet.getString( "Email" ) );
                                String info1 = resultSet.getString( "UniversityName" );
                                String info2 = resultSet.getString( "ProgramName" );
                                String role = resultSet.getString( "RoleName" );
                                String sCounter = EMPTY_STRING + counter++;

                                userList.add( new AdminUserListByRoleData( sCounter, firstName, lastName, getEmail(), info1, info2, role ) );
                            }
                        }
                        else {
                            if( roleID.equals( ROLE_DEAN ) ) {
                                preparedStatement = connection.prepareStatement( GET_DEANS_SQL );

                                resultSet = preparedStatement.executeQuery();

                                while( resultSet.next() ) {
                                    String firstName = resultSet.getString( "FirstName" );
                                    String lastName = resultSet.getString( "LastName" );
                                    setEmail( resultSet.getString( "Email" ) );
                                    String info1 = resultSet.getString( "UniversityName" );
                                    String info2 = EMPTY_STRING;
                                    String role = resultSet.getString( "RoleName" );
                                    String sCounter = EMPTY_STRING + counter++;

                                    userList.add( new AdminUserListByRoleData( sCounter, firstName, lastName, getEmail(), info1, info2, role ) );
                                }
                            }
                            else {
                                if( roleID.equals( ROLE_COMMITTEE ) ) {
                                    preparedStatement = connection.prepareStatement( GET_COMMITTEES_SQL );

                                    resultSet = preparedStatement.executeQuery();

                                    while( resultSet.next() ) {
                                        String firstName = resultSet.getString( "FirstName" );
                                        String lastName = resultSet.getString( "LastName" );
                                        setEmail( resultSet.getString( "Email" ) );
                                        String info1 = resultSet.getString( "UniversityName" );
                                        String info2 = EMPTY_STRING;
                                        String role = resultSet.getString( "RoleName" );
                                        String sCounter = EMPTY_STRING + counter++;

                                        userList.add( new AdminUserListByRoleData( sCounter, firstName, lastName, getEmail(), info1, info2, role ) );
                                    }
                                }
                                else {
                                    if( roleID.equals( "OTH" ) ) {
                                        preparedStatement = connection.prepareStatement( GET_OTHER_USERS_SQL );

                                        resultSet = preparedStatement.executeQuery();

                                        while( resultSet.next() ) {
                                            String firstName = resultSet.getString( "FirstName" );
                                            String lastName = resultSet.getString( "LastName" );
                                            setEmail( resultSet.getString( "Email" ) );
                                            String info1 = EMPTY_STRING;
                                            String info2 = EMPTY_STRING;
                                            String role = resultSet.getString( "RoleName" );
                                            String sCounter = EMPTY_STRING + counter++;

                                            userList.add( new AdminUserListByRoleData( sCounter, firstName, lastName, getEmail(), info1, info2, role ) );
                                        }
                                    }
                                }
                            }
                        }
                    }
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

        return userList;
    }

    public String getInitialUserCounter() {
        logger.debugMethod( "getInitialUserCounter" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String userCounter = "0";

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_INIT_COUNTER );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                userCounter = resultSet.getString( "UserCount" );
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

        return userCounter;
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
