package com.moh.data.bean;

public class AdminInstitutionData {

    private String InstitutionID;
    private String InstitutionName;
    private String abbreviation;
    private String enabled;
    private String enabledDesc;

    public AdminInstitutionData( String InstitutionID, String InstitutionName, String abbreviation, String enabled ) {
        this.InstitutionID = InstitutionID;
        this.InstitutionName = InstitutionName;
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

    public String getInstitutionID() {
        return InstitutionID;
    }

    public void setInstitutionID( String institutionID ) {
        InstitutionID = institutionID;
    }

    public String getInstitutionName() {
        return InstitutionName;
    }

    public void setInstitutionName( String institutionName ) {
        InstitutionName = institutionName;
    }
}
