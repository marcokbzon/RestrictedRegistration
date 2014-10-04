package com.moh.data.bean;

public class AppReviewListData {

    private String applicationID;
    private String applicantName;
    private String serviceType;
    private String updatedOn;
    private String phase;
    private String iconName;
    private String position;

    public AppReviewListData( String applicationID, String applicantName, String serviceType, String updatedOn, String phase, String iconName, String position ) {
        this.position = position;
        this.applicationID = applicationID;
        this.applicantName = applicantName;
        this.serviceType = serviceType;
        this.updatedOn = updatedOn;
        this.phase = phase;
        this.iconName = iconName;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID( String applicationID ) {
        this.applicationID = applicationID;
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

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName( String applicantName ) {
        this.applicantName = applicantName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType( String serviceType ) {
        this.serviceType = serviceType;
    }

    public String getUpdatedOn() {
        updatedOn = updatedOn.substring( 0, 10 );
        return updatedOn;
    }

    public void setUpdatedOn( String updatedOn ) {
        this.updatedOn = updatedOn;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition( String position ) {
        this.position = position;
    }
}
