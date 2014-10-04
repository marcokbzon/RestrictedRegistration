package com.moh.data.bean;

public class AdminAppListByCriteriaData2 {

    private String applicationID;
    private String firstName;
    private String lastName;
    private String institutionName;
    private String serviceTypeName;
    private String universityName;
    private String position;

    public AdminAppListByCriteriaData2( String applicationID, String firstName, String lastName,
            String institutionName, String serviceTypeName, String universityName, String position ) {
        this.applicationID = applicationID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.institutionName = institutionName;
        this.serviceTypeName = serviceTypeName;
        this.universityName = universityName;
        this.position = position;
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
}
