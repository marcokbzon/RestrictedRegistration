package com.moh.common;

import java.io.File;
import java.util.Map;

import com.moh.data.dao.FileDAO;
import com.moh.utils.Logger;

public class FileUploadListSSHelper extends AbstractBean {

    private String selectedFileID;
    private String email;

    public FileUploadListSSHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "FileUploadListSSHelper" );
    }

    public String uploadFileList() {
        setSelectedFileID( EMPTY_STRING );

        System.out.println( "selectedFileID: " + selectedFileID );
        
        logger.debugPage( "/jsp/fileUpload.jsp" );
        return "fileUploadList";
    }

    public String addUploadFile() {
        setSelectedFileID( EMPTY_STRING );

        logger.debugPage( "/jsp/fileUpload.jsp" );
        return "fileUpload";
    }

    public String deleteUploadFile() {
        if( selectedFileID == null || selectedFileID.trim().equals(EMPTY_STRING) ) {
            addErrorMessage( "error_file_required" );
            
            logger.debugPage( "/jsp/fileUpload.jsp" );
            return "fileUploadList";
        }

        return delete();
    }

    public String delete() {
        logger.debugMethod( "delete" );
        boolean result = false;

        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );

        FileDAO fileDAO = new FileDAO();

        String fileDirectory = fileDAO.getFileDirectory( getEmail() );

        Map<String, String> mapFU = fileDAO.getFileUpload( fileDirectory, selectedFileID );

        String fileName = mapFU.get( "FileName" );

        result = fileDAO.deleteFileUpload( fileDirectory, selectedFileID );

        if( result ) {
            addInfoMessage( "info_upload_file_deleted_ok" );
        }
        else {
            addErrorMessage( "error_db_delete_failed" );
        }

        boolean success = ( new File( SESSION_RRDOCS_PATH + SESSION_FILE_SEPARATOR + fileDirectory + SESSION_FILE_SEPARATOR + fileName ) ).delete();

        if( !success ) {
            addErrorMessage( "error_file_not_deleted" );
        }

        logger.debugPage( "/jsp/fileUploadDelConf.jsp" );
        return "fileUploadDelConf";
    }

    public String getSelectedFileID() {
        return selectedFileID;
    }

    public void setSelectedFileID( String selectedFileID ) {
        this.selectedFileID = selectedFileID;
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
