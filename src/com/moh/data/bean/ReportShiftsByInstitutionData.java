package com.moh.data.bean;

public class ReportShiftsByInstitutionData {

    private String institutionID;
    private String institutionName;
    private String numberOfShifts;
    private String numberOfHours;

    public ReportShiftsByInstitutionData( String institutionID, String institutionName, String numberOfShifts, String numberOfHours ) {
        this.institutionID = institutionID;
        this.institutionName = institutionName;
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

    public String getInstitutionID() {
        return institutionID;
    }

    public void setInstitutionID( String institutionID ) {
        this.institutionID = institutionID;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName( String institutionName ) {
        this.institutionName = institutionName;
    }
}
