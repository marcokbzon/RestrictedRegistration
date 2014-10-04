package com.moh.data.bean;

public class AppFormListData {

    private String applicationID;
    private String institution;
    private String location;
    private String phase;
    private String iconName;

    public AppFormListData( String applicationID, String institution, String location, String phase, String iconName ) {
        this.applicationID = applicationID;
        this.institution = institution;
        this.location = location;
        this.phase = phase;
        this.iconName = iconName;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID( String applicationID ) {
        this.applicationID = applicationID;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution( String institution ) {
        this.institution = institution;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation( String location ) {
        this.location = location;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase( String phase ) {
        this.phase = phase;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName( String iconName ) {
        this.iconName = iconName;
    }
}
