package com.moh.data.bean;

public class AdminProgramData {

    private String programID;
    private String description_EN;
    private String abbreviation;
    private String enabled;
    private String enabledDesc;

    public AdminProgramData( String programID, String description_EN, String abbreviation, String enabled ) {
        this.programID = programID;
        this.description_EN = description_EN;
        this.abbreviation = abbreviation;
        this.enabled = enabled;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation( String abbreviation ) {
        this.abbreviation = abbreviation;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled( String enabled ) {
        this.enabled = enabled;
    }

    public String getProgramID() {
        return programID;
    }

    public void setProgramID( String programID ) {
        this.programID = programID;
    }

    public String getDescription_EN() {
        return description_EN;
    }

    public void setDescription_EN( String description_EN ) {
        this.description_EN = description_EN;
    }

    public String getEnabledDesc() {
        if( enabled.equals( "1" ) ) {
            enabledDesc = "Yes";
        }
        else {
            if( enabled.equals( "0" ) ) {
                enabledDesc = "No";
            }
        }
        return enabledDesc;
    }

    public void setEnabledDesc( String enabledDesc ) {
        this.enabledDesc = enabledDesc;
    }
}
