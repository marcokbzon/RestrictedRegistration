package com.moh.data.bean;

public class ReportShiftsByServTypeData {

    private String serviceTypeID;
    private String serviceTypeDesc;
    private String numberOfShifts;
    private String numberOfHours;

    public ReportShiftsByServTypeData( String serviceTypeID, String serviceTypeDesc, String numberOfShifts, String numberOfHours ) {
        this.serviceTypeID = serviceTypeID;
        this.serviceTypeDesc = serviceTypeDesc;
        this.numberOfShifts = numberOfShifts;
        this.numberOfHours = numberOfHours;
    }

    public String getServiceTypeID() {
        return serviceTypeID;
    }

    public void setServiceTypeID( String serviceTypeID ) {
        this.serviceTypeID = serviceTypeID;
    }

    public String getServiceTypeDesc() {
        return serviceTypeDesc;
    }

    public void setServiceTypeDesc( String serviceTypeDesc ) {
        this.serviceTypeDesc = serviceTypeDesc;
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
