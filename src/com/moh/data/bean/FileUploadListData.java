package com.moh.data.bean;

public class FileUploadListData {

    private String fileID;
    private String fileName;
    private String fileDirectory;
    private String category;
    private String delineativeName;
    private String description;

    public FileUploadListData( String fileID, String fileName, String fileDirectory, String category, String delineativeName, String description ) {
        this.fileID = fileID;
        this.fileName = fileName;
        this.fileDirectory = fileDirectory;
        this.category = category;
        this.delineativeName = delineativeName;
        this.description = description;
    }

    public String getFileID() {
        return fileID;
    }

    public void setFileID( String fileID ) {
        this.fileID = fileID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName( String fileName ) {
        this.fileName = fileName;
    }

    public String getFileDirectory() {
        return fileDirectory;
    }

    public void setFileDirectory( String fileDirectory ) {
        this.fileDirectory = fileDirectory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory( String category ) {
        this.category = category;
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
}
