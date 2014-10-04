package com.moh.data.bean;

public class ReportShiftsByDateData {

    private String year;
    private String month;
    private String numberOfShifts;
    private String numberOfHours;

    public ReportShiftsByDateData( String year, String month, String numberOfShifts, String numberOfHours ) {
        this.year = year;
        this.month = month;
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

    public String getYear() {
        return year;
    }

    public void setYear( String year ) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth( String month ) {
        this.month = month;
    }
}
