package com.moh.data.bean;

public class AppReviewAppHistoryData {

    private String applicationID;
    private String firstName;
    private String lastName;
    private String institutionName;
    private String serviceTypeName;
    private String universityName;
    private String position;
    private String statusName;
    private String statusIcon;

    public AppReviewAppHistoryData( String applicationID, String firstName, String lastName,
            String institutionName, String serviceTypeName, String universityName, String position, String statusName, String statusIcon ) {
        this.applicationID = applicationID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.institutionName = institutionName;
        this.serviceTypeName = serviceTypeName;
        this.universityName = universityName;
        this.position = position;
        this.statusName = statusName;
        this.statusIcon = statusIcon;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID( String applicationID ) {
        this.applicationID = applicationID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName( String institutionName ) {
        this.institutionName = institutionName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName( String universityName ) {
        this.universityName = universityName;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName( String serviceTypeName ) {
        this.serviceTypeName = serviceTypeName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition( String position ) {
        this.position = position;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName( String statusName ) {
        this.statusName = statusName;
    }

    public String getStatusIcon() {
        return statusIcon;
    }

    public void setStatusIcon( String statusIcon ) {
        this.statusIcon = statusIcon;
    }
}
