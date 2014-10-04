package com.moh.data.bean;

public class AdminUniversityData {

    private String universityID;
    private String name_EN;
    private String abbreviation;
    private String enabled;
    private String enabledDesc;

    public AdminUniversityData( String universityID, String name_EN, String abbreviation, String enabled ) {
        this.universityID = universityID;
        this.name_EN = name_EN;
        this.abbreviation = abbreviation;
        this.enabled = enabled;
    }

    public String getUniversityID() {
        return universityID;
    }

    public void setUniversityID( String universityID ) {
        this.universityID = universityID;
    }

    public String getName_EN() {
        return name_EN;
    }

    public void setName_EN( String name_EN ) {
        this.name_EN = name_EN;
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
