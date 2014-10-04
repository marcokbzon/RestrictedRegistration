package com.moh.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.myfaces.custom.fileupload.UploadedFile;

import com.moh.data.dao.FileDAO;
import com.moh.utils.Logger;

public class FileUploadRQHelper extends AbstractBean {

    private UploadedFile supportFile;
    private String fileName;
    private String fileType;
    private boolean overrideFile;
    private String delineativeName;
    private String description;
    private String personalDirectory;
    private String email;

    public FileUploadRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "FileUploadRQHelper" );
    }

    public String open() {
        logger.debugMethod( "open" );

        boolean userInfoWasUpdated = false;

        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );

        FileDAO fileDAO = new FileDAO();
        String fileDirectory = fileDAO.getFileDirectory( getEmail() );

        if( fileDirectory == null || fileDirectory.trim().equals( EMPTY_STRING ) ) {
            userInfoWasUpdated = fileDAO.updateFileDirectory( getEmail() );

            if( !userInfoWasUpdated ) {
                addErrorMessage( "error_db_update_failed" );
                
                logger.debugPage( "/jsp/mainReviewer.jsp" );
                return "mainReviewer";
            }
        }

        logger.debugPage( "/jsp/fileUpload.jsp" );
        return "fileUpload";
    }

    public String submit() {
        logger.debugMethod( "submit" );

        if( supportFile == null ) {
            addErrorMessage( "error_physical_file_required" );
            
            logger.debugPage( "/jsp/fileUpload.jsp" );
            return "fileUpload";
        }

        if( delineativeName == null || delineativeName.trim().equals( EMPTY_STRING ) ) {
            addErrorMessage( "error_delineative_name_required" );
            
            logger.debugPage( "/jsp/fileUpload.jsp" );
            return "fileUpload";
        }

        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;

        String fullName = supportFile.getName();
        int slashPos = fullName.lastIndexOf( SESSION_FILE_SEPARATOR );
        String fileName = fullName.substring( slashPos + 1 ).replaceAll( " ", "_" );

        long fileSize = supportFile.getSize();

        if( fileSize > 5000000 ) {
            addErrorMessage( "error_file_is_too_big" );
            
            logger.debugPage( "/jsp/fileUpload.jsp" );
            return "fileUpload";
        }

        FileDAO fileDAO = new FileDAO();
        
        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );
        personalDirectory = fileDAO.getFileDirectory( getEmail() );

        if( personalDirectory == null || personalDirectory.trim().equals( EMPTY_STRING ) ) {
            boolean userInfoWasUpdated = fileDAO.updateFileDirectory( getEmail() );

            if( !userInfoWasUpdated ) {
                addErrorMessage( "error_db_update_failed" );
                
                logger.debugPage( "/jsp/fileUpload.jsp" );
                return "fileUpload";
            }
            else {
                personalDirectory = fileDAO.getFileDirectory( getEmail() );
            }
        }

        boolean dirExists = ( new File( SESSION_RRDOCS_PATH + SESSION_FILE_SEPARATOR + personalDirectory + SESSION_FILE_SEPARATOR ) ).exists();

        if( !dirExists ) {
            boolean dirCreatedOK = ( new File( SESSION_RRDOCS_PATH + SESSION_FILE_SEPARATOR + personalDirectory + SESSION_FILE_SEPARATOR ) ).mkdir();

            if( !dirCreatedOK ) {
                addErrorMessage( "error_create_directory_failed" );
                
                logger.debugPage( "/jsp/fileUpload.jsp" );
                return "fileUpload";
            }
        }

        boolean fileExists = ( new File( SESSION_RRDOCS_PATH + SESSION_FILE_SEPARATOR + personalDirectory + SESSION_FILE_SEPARATOR + fileName ) ).exists();

        if( fileExists ) {
            if( !overrideFile ) {
                addErrorMessage( "error_file_already_exists" );
                
                logger.debugPage( "/jsp/fileUpload.jsp" );
                return "fileUpload";
            }
        }

        try {
            inputStream = new BufferedInputStream( supportFile.getInputStream() );

            fileOutputStream = new FileOutputStream( SESSION_RRDOCS_PATH + SESSION_FILE_SEPARATOR + personalDirectory + SESSION_FILE_SEPARATOR + fileName );

            byte[] buf = new byte[256];
            int read = 0;

            while( ( read = inputStream.read( buf ) ) > 0 ) {
                fileOutputStream.write( buf, 0, read );
            }
        }
        catch( IOException ioex ) {
            logger.exception( ioex );
            addErrorMessage( "error_upload_file_failed" );
            
            logger.debugPage( "/jsp/fileUpload.jsp" );
            return "fileUpload";
        }
        finally {
            try {
                inputStream.close();
                fileOutputStream.close();

                inputStream = null;
                fileOutputStream = null;
            }
            catch( IOException ioex2 ) {
                logger.exception( ioex2 );
            }
        }

        boolean uploadFileTableOK = false;

        if( fileExists ) {
            String fileID = fileDAO.getFileID( personalDirectory, fileName );
            uploadFileTableOK = fileDAO.updateFileUpload( personalDirectory, fileID, delineativeName, description );
        }
        else {
            uploadFileTableOK = fileDAO.addFileUpload( personalDirectory, fileName, EMPTY_STRING, delineativeName, description );
        }

        if( !uploadFileTableOK ) {
            addErrorMessage( "error_upload_file_failed" );
            
            logger.debugPage( "/jsp/fileUpload.jsp" );
            return "fileUpload";
        }
        else {
            addInfoMessage( "info_upload_file_ok" );
        }

        logger.debugPage( "/jsp/mainReviewer.jsp" );
        return "mainReviewer";
    }

    public UploadedFile getSupportFile() {
        return supportFile;
    }

    public void setSupportFile( UploadedFile supportFile ) {
        this.supportFile = supportFile;
    }

    public boolean getOverrideFile() {
        return overrideFile;
    }

    public void setOverrideFile( boolean overrideFile ) {
        this.overrideFile = overrideFile;
    }

    public String getDelineativeName() {
        return delineativeName;
    }

    public void setDelineativeName( String delineativeName ) {
        this.delineativeName = delineativeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName( String fileName ) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType( String fileType ) {
        this.fileType = fileType;
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
