package com.moh.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.moh.common._ParentDAO;
import com.moh.data.bean.FileUploadListData;
import com.moh.utils.Logger;

public class FileDAO extends _ParentDAO {

    private String CHECK_USERINFO_SQL =
            "SELECT FileDirectory "
            + "FROM UserInfo "
            + "WHERE Email = ?";
    private String UPDATE_USERINFO_SQL =
            "UPDATE UserInfo "
            + "SET FileDirectory = ? "
            + "WHERE Email = ?";
    private String GET_USERINFO_SQL =
            "SELECT FirstName, LastName "
            + "FROM UserInfo "
            + "WHERE Email = ?";
    private String ADD_FILEUPLOAD_SQL =
            "INSERT INTO FileUpload "
            + "( FileID, FileDirectory, FileName, Category, DelineativeName, BriefDescription ) "
            + "VALUES ( ?, ?, ?, ?, ?, ? )";
    private String UPDATE_FILEUPLOAD_SQL =
            "UPDATE FileUpload "
            + "SET DelineativeName = ?, BriefDescription = ? "
            + "WHERE FileDirectory = ? "
            + "AND FileID = ?";
    private String GET_FILEID_SQL =
            "SELECT FileID "
            + "FROM FileUpload "
            + "WHERE FileDirectory = ? "
            + "AND FileName = ?";
    private String DELETE_FILEUPLOAD_SQL =
            "DELETE FROM FileUpload "
            + "WHERE FileDirectory = ? "
            + "AND FileID = ?";
    private String GET_FILEUPLOAD_LIST_SQL =
            "SELECT FileID, FileName, Category, DelineativeName, BriefDescription "
            + "FROM FileUpload "
            + "WHERE FileDirectory = ?";
    private String GET_FILEUPLOAD_SQL =
            "SELECT FileName, Category, DelineativeName, BriefDescription "
            + "FROM FileUpload "
            + "WHERE FileDirectory = ? "
            + "AND FileID = ?";
    private String GET_FILECATEGORY_LIST_SQL =
            "SELECT Category, Description_EN "
            + "FROM FileCategory";
    private String GET_FILECATEGORY_SQL =
            "SELECT Description_EN "
            + "FROM FileCategory "
            + "WHERE Category = ?";
    private String ADD_DOCUMENT_SQL =
            "INSERT INTO Document "
            + "( ApplicationID, RoleID, FileDirectory, FileID ) "
            + "VALUES ( ?, ?, ?, ? )";
    private String GET_DOCUMENTS_SQL =
            "SELECT dc.FileDirectory, fu.FileName, fu.DelineativeName "
            + "FROM Document dc, FileUpload fu "
            + "WHERE dc.ApplicationID = ? "
            + "AND fu.FileDirectory = dc.FileDirectory "
            + "AND fu.FileID = dc.FileID "
            + "AND dc.RoleID = ?";
    
    private String email;

    public FileDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "FileDAO" );
    }

    public String getFileDirectory( String email ) {
        logger.debugMethod( "getFileDirectory" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String fileDirectory = EMPTY_STRING;
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( CHECK_USERINFO_SQL );
            preparedStatement.setString( 1, getEmail() );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                fileDirectory = resultSet.getString( "FileDirectory" );
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

        return fileDirectory;
    }

    public boolean updateFileDirectory( String email ) {
        logger.debugMethod( "updateFileDirectory" );
        boolean returnedValue = false;
        String firstName = EMPTY_STRING;
        String lastName = EMPTY_STRING;
        String fileDirectory = EMPTY_STRING;
        setEmail( email );

        PreparedStatement sPreparedStatement = null;
        ResultSet sResultSet = null;
        Connection sConnection = null;

        try {
            sConnection = getConnection();

            sPreparedStatement = sConnection.prepareStatement( GET_USERINFO_SQL );
            sPreparedStatement.setString( 1, getEmail() );

            sResultSet = sPreparedStatement.executeQuery();

            while( sResultSet.next() ) {
                firstName = sResultSet.getString( "FirstName" );
                lastName = sResultSet.getString( "LastName" );
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );
        }
        finally {
            try {
                if( sResultSet != null ) {
                    sResultSet.close();
                }
                if( sPreparedStatement != null ) {
                    sPreparedStatement.close();
                }
                if( sConnection != null ) {
                    sConnection.close();
                }
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }

        // FILE DIRECTORY

        Calendar cal = Calendar.getInstance();

        firstName = firstName.substring( 0, 1 ).toUpperCase();
        lastName = lastName.substring( 0, 1 ).toUpperCase();

        fileDirectory = firstName + lastName + cal.getTimeInMillis();

        // UPDATE

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int updateCountUF = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( UPDATE_USERINFO_SQL );

            preparedStatement.setString( 1, fileDirectory );
            preparedStatement.setString( 2, getEmail() );

            updateCountUF = preparedStatement.executeUpdate();

            if( updateCountUF != 1 ) {
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

    public boolean addFileUpload( String fileDirectory, String fileName, String category, String delineativeName, String briefDescription ) {
        logger.debugMethod( "addFileUpload" );
        boolean returnedValue = false;

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        Calendar cal = Calendar.getInstance();
        String fileID = EMPTY_STRING + cal.getTimeInMillis();

        try {
            int insertCountUF = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( ADD_FILEUPLOAD_SQL );

            preparedStatement.setString( 1, fileID );
            preparedStatement.setString( 2, fileDirectory );
            preparedStatement.setString( 3, fileName );
            preparedStatement.setString( 4, category );
            preparedStatement.setString( 5, delineativeName );
            preparedStatement.setString( 6, briefDescription );

            insertCountUF = preparedStatement.executeUpdate();

            if( insertCountUF != 1 ) {
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

    public boolean updateFileUpload( String fileDirectory, String fileID, String delineativeName, String briefDescription ) {
        logger.debugMethod( "updateFileUpload" );
        boolean returnedValue = false;

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int updateCountUF = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( UPDATE_FILEUPLOAD_SQL );

            preparedStatement.setString( 1, delineativeName );
            preparedStatement.setString( 2, briefDescription );
            preparedStatement.setString( 3, fileDirectory );
            preparedStatement.setString( 4, fileID );

            updateCountUF = preparedStatement.executeUpdate();

            if( updateCountUF != 1 ) {
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

    public String getFileID( String fileDirectory, String fileName ) {
        logger.debugMethod( "getFileID" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String fileID = EMPTY_STRING;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_FILEID_SQL );
            preparedStatement.setString( 1, fileDirectory );
            preparedStatement.setString( 2, fileName );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                fileID = resultSet.getString( "FileID" );
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

        return fileID;
    }

    public boolean deleteFileUpload( String fileDirectory, String fileID ) {
        logger.debugMethod( "deleteFileUpload" );
        boolean returnedValue = false;

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int deleteCountUF = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( DELETE_FILEUPLOAD_SQL );

            preparedStatement.setString( 1, fileDirectory );
            preparedStatement.setString( 2, fileID );

            deleteCountUF = preparedStatement.executeUpdate();

            if( deleteCountUF != 1 ) {
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

    public List<FileUploadListData> getFileUploadList( String fileDirectory ) {
        logger.debugMethod( "getFileUploadList" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<FileUploadListData> listOfFiles = new ArrayList<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_FILEUPLOAD_LIST_SQL );
            preparedStatement.setString( 1, fileDirectory );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String fileID = resultSet.getString( "FileID" );
                String fileName = resultSet.getString( "FileName" );
                String category = resultSet.getString( "Category" );
                String delineativeName = resultSet.getString( "DelineativeName" );
                String briefDescription = resultSet.getString( "BriefDescription" );

                listOfFiles.add( new FileUploadListData( fileID, fileName, fileDirectory, category, delineativeName, briefDescription ) );
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

        return listOfFiles;
    }

    public Map<String, String> getFileUpload( String fileDirectory, String fileID ) {
        logger.debugMethod( "getFileUpload" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> fileUpload = new HashMap<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_FILEUPLOAD_SQL );

            preparedStatement.setString( 1, fileDirectory );
            preparedStatement.setString( 2, fileID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String fileName = resultSet.getString( "FileName" );
                String category = resultSet.getString( "Category" );
                String delineativeName = resultSet.getString( "DelineativeName" );
                String briefDescription = resultSet.getString( "BriefDescription" );

                fileUpload.put( "FileName", fileName );
                fileUpload.put( "Category", category );
                fileUpload.put( "DelineativeName", delineativeName );
                fileUpload.put( "BriefDescription", briefDescription );
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

        return fileUpload;
    }

    public Map<String, String> getFileCategoryList() {
        logger.debugMethod( "getFileCategoryList" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> listOfCategories = new HashMap<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_FILECATEGORY_LIST_SQL );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String category = resultSet.getString( "Category" );
                String description_EN = resultSet.getString( "Description_EN" );

                listOfCategories.put( "Category", category );
                listOfCategories.put( "Description_EN", description_EN );
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

        return listOfCategories;
    }

    public String getFileCategoryDesc( String category ) {
        logger.debugMethod( "getFileCategoryDesc" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String description = EMPTY_STRING;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_FILECATEGORY_SQL );

            preparedStatement.setString( 1, category );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                description = resultSet.getString( "Description_EN" );
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

        return description;
    }

    public boolean addDocument( String applicationID, String roleID, String fileDirectory, String fileID ) {
        logger.debugMethod( "addDocument" );
        boolean returnedValue = false;

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int insertCountAD = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( ADD_DOCUMENT_SQL );

            preparedStatement.setString( 1, applicationID );
            preparedStatement.setString( 2, roleID );
            preparedStatement.setString( 3, fileDirectory );
            preparedStatement.setString( 4, fileID );

            insertCountAD = preparedStatement.executeUpdate();

            if( insertCountAD != 1 ) {
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

    public Map<String, String> getDocumentLinks( String applicationID, String roleID ) {
        logger.debugMethod( "getDocumentLinks" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map<String, String> documents = new HashMap<>();

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_DOCUMENTS_SQL );
            preparedStatement.setString( 1, applicationID );
            preparedStatement.setString( 2, roleID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String fileHref = resultSet.getString( "FileDirectory" ) + "/" + resultSet.getString( "FileName" );
                String delineativeName = resultSet.getString( "DelineativeName" );

                documents.put( fileHref.trim(), delineativeName.trim() ); // key, value
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

        return documents;
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
