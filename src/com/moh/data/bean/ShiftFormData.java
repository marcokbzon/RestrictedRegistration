package com.moh.data.bean;

public class ShiftFormData {

    private String yearMonth;
    private String applicationId;
    private String institution;
    private String serviceType;
    private String numberOfShifts;
    private String numberOfHours;
    private boolean weekday;
    private boolean weeknight;
    private boolean weekendDay;
    private boolean weekendNight;
    private boolean weekLocum;

    public ShiftFormData( String yearMonth, String applicationId, String institution, String serviceType, String numberOfShifts, String numberOfHours, boolean weekday, boolean weeknight, boolean weekendDay, boolean weekendNight, boolean weekLocum ) {
        this.yearMonth = yearMonth;
        this.applicationId = applicationId;
        this.institution = institution;
        this.serviceType = serviceType;
        this.numberOfShifts = numberOfShifts;
        this.numberOfHours = numberOfHours;
        this.weekday = weekday;
        this.weeknight = weeknight;
        this.weekendDay = weekendDay;
        this.weekendNight = weekendNight;
        this.weekLocum = weekLocum;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth( String yearMonth ) {
        this.yearMonth = yearMonth;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId( String applicationId ) {
        this.applicationId = applicationId;
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

    public boolean getWeekday() {
        return weekday;
    }

    public void setWeekday( boolean weekday ) {
        this.weekday = weekday;
    }

    public boolean getWeeknight() {
        return weeknight;
    }

    public void setWeeknight( boolean weeknight ) {
        this.weeknight = weeknight;
    }

    public boolean getWeekendDay() {
        return weekendDay;
    }

    public void setWeekendDay( boolean weekendDay ) {
        this.weekendDay = weekendDay;
    }

    public boolean getWeekendNight() {
        return weekendNight;
    }

    public void setWeekendNight( boolean weekendNight ) {
        this.weekendNight = weekendNight;
    }

    public boolean getWeekLocum() {
        return weekLocum;
    }

    public void setWeekLocum( boolean weekLocum ) {
        this.weekLocum = weekLocum;
    }
}
