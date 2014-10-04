package com.moh.data.bean;

public class ReportShiftsByApplicationData {

    private String applicationID;
    private String numberOfShifts;
    private String numberOfHours;

    public ReportShiftsByApplicationData( String applicationID, String numberOfShifts, String numberOfHours ) {
        this.applicationID = applicationID;
        this.numberOfShifts = numberOfShifts;
        this.numberOfHours = numberOfHours;
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

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID( String applicationID ) {
        this.applicationID = applicationID;
    }
}
