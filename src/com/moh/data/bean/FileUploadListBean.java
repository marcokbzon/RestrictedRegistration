package com.moh.data.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import com.moh.common.AbstractBean;
import com.moh.data.dao.FileDAO;

public class FileUploadListBean extends AbstractBean {

	@SuppressWarnings( "rawtypes" )
    DataModel uploadFileDM = new ListDataModel();
    private List<SelectItem> fileIDList;
    private String email;

    public FileUploadListBean() {
        FileDAO fileDAO = new FileDAO();

        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );

        String fileDirectory = fileDAO.getFileDirectory( getEmail() );

        List<FileUploadListData> fileList = fileDAO.getFileUploadList( fileDirectory );

        Iterator<FileUploadListData> iter = fileList.iterator();

        fileIDList = new ArrayList<>();

        while( iter.hasNext() ) {
            String fileID = iter.next().getFileID();
            fileIDList.add( new SelectItem( fileID, EMPTY_STRING ) );
        }

        uploadFileDM.setWrappedData( fileList );
    }

    @SuppressWarnings( "rawtypes" )
    public DataModel getUploadFileDM() {
        return uploadFileDM;
    }

    public void setUploadFileDM( Object data ) {
        uploadFileDM.setWrappedData( data );
    }

    public List<SelectItem> getFileIDList() {
        return fileIDList;
    }

    public void setFileIDList( List<SelectItem> fileIDList ) {
        this.fileIDList = fileIDList;
    }
    
    private String getEmail() {
        if ( email == null || email.trim().equals( EMPTY_STRING ) ) {
            return EMPTY_STRING;
        }
        else {
            return email.trim().toLowerCase();
        }
    }
    
    private void setEmail( String email ) {
        if ( email == null || email.trim().equals( EMPTY_STRING ) ) {
            this.email = EMPTY_STRING;
        }
        else {
            this.email = email.trim().toLowerCase();
        }
    }
}
