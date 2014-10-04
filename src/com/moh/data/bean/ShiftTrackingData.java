package com.moh.data.bean;

public class ShiftTrackingData {

    private String applicationID;
    private String institution;
    private String serviceType;
    private String numberOfShifts;
    private String numberOfHours;

    public ShiftTrackingData( String applicationID, String institution, String serviceType, String numberOfShifts, String numberOfHours ) {
        this.applicationID = applicationID;
        this.institution = institution;
        this.serviceType = serviceType;
        this.numberOfShifts = numberOfShifts;
        this.numberOfHours = numberOfHours;
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

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType( String serviceType ) {
        this.serviceType = serviceType;
    }

    public String getNumberOfShifts() {
        return numberOfShifts;
    }

    public void setNumberOfShifts( String numberOfShifts ) {
        this.numberOfShifts = numberOfShifts;
    }

    public String getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours( String numberOfHours ) {
        this.numberOfHours = numberOfHours;
    }
}
