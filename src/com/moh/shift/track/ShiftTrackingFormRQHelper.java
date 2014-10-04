package com.moh.shift.track;

import com.moh.common.AbstractBean;
import com.moh.data.bean.ShiftFormData;
import com.moh.data.dao.ShiftTrackingDAO;
import com.moh.utils.Logger;

public class ShiftTrackingFormRQHelper extends AbstractBean {

    private String yearMonth;
    private String applicationId;
    private String institution;
    private String serviceType;
    private String numberOfShifts;
    private String numberOfHours;
    private boolean weekday;
    private boolean weeknight;
    private boolean weekend_day;
    private boolean weekend_night;
    private boolean week_locum;
    private String email;

    public ShiftTrackingFormRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ShiftTrackingFormRQHelper" );

        open();
    }

    public String open() {
        logger.debugMethod( "open" );

        String appID = ( String ) getFromSession( SESSION_APPLICATIONID );
        String year = ( String ) getFromSession( SHIFT_TRACK_YEAR );
        String month = ( String ) getFromSession( SHIFT_TRACK_MONTH );

        ShiftTrackingDAO shiftDAO = new ShiftTrackingDAO();
        ShiftFormData formData = shiftDAO.getShiftFormData( appID, year, month );

        setYearMonth( formData.getYearMonth().toUpperCase() );
        setApplicationId( formData.getApplicationId() );
        setInstitution( formData.getInstitution() );
        setServiceType( formData.getServiceType() );
        setNumberOfShifts( formData.getNumberOfShifts() );
        setNumberOfHours( formData.getNumberOfHours() );
        setWeekday( formData.getWeekday() );
        setWeeknight( formData.getWeeknight() );
        setWeekend_day( formData.getWeekendDay() );
        setWeekend_night( formData.getWeekendNight() );
        setWeek_locum( formData.getWeekLocum() );

        logger.debugPage( "/jsp/shiftTracking03.jsp" );
        return "shiftTracking03";
    }

    public String update() {
        logger.debugMethod( "update" );

        try {
            @SuppressWarnings( "unused" )
            int numShifts = Integer.parseInt( numberOfShifts );
        }
        catch( NumberFormatException nfex ) {
            addErrorMessage( "error_shifts_format_invalid" );
            
            logger.debugPage( "/jsp/shiftTracking03.jsp" );
            return "shiftTracking03";
        }
        catch( NullPointerException npex ) {
            addErrorMessage( "error_shifts_format_invalid" );
            
            logger.debugPage( "/jsp/shiftTracking03.jsp" );
            return "shiftTracking03";
        }

        try {
            @SuppressWarnings( "unused" )
            int numHours = Integer.parseInt( numberOfHours );
        }
        catch( NumberFormatException nfex ) {
            addErrorMessage( "error_hours_format_invalid" );
            
            logger.debugPage( "/jsp/shiftTracking03.jsp" );
            return "shiftTracking03";
        }
        catch( NullPointerException npex ) {
            addErrorMessage( "error_hours_format_invalid" );
            
            logger.debugPage( "/jsp/shiftTracking03.jsp" );
            return "shiftTracking03";
        }

        boolean addTrackingOK = true;
        boolean updateTrackingOK = true;
        boolean addSummaryOK = true;
        boolean updateSummaryOK = true;

        String year = ( String ) getFromSession( SHIFT_TRACK_YEAR );
        String month = ( String ) getFromSession( SHIFT_TRACK_MONTH );

        ShiftTrackingDAO shiftDAO = new ShiftTrackingDAO();

        if( shiftDAO.formDataExists( applicationId, year, month ) ) {
            updateTrackingOK = shiftDAO.formDataUpdate( applicationId, year, month, numberOfShifts, weekday, weeknight, weekend_day, weekend_night, numberOfHours, week_locum );
        }
        else {
            addTrackingOK = shiftDAO.formDataAdd( applicationId, year, month, numberOfShifts, weekday, weeknight, weekend_day, weekend_night, numberOfHours, week_locum );
        }

        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );

        String[] shiftYearMonth = shiftDAO.getTotalByDate( getEmail(), year, month );

        if( shiftDAO.summaryDataExists( getEmail(), year, month ) ) {
            updateSummaryOK = shiftDAO.summaryDataUpdate( getEmail(), year, month, shiftYearMonth[0], shiftYearMonth[1] );
        }
        else {
            addSummaryOK = shiftDAO.summaryDataAdd( getEmail(), year, month, shiftYearMonth[0], shiftYearMonth[1] );
        }

        if( addTrackingOK & updateTrackingOK & addSummaryOK & updateSummaryOK ) {
            addInfoMessage( "info_shifttracking_updated_ok" );
        }
        else {
            addErrorMessage( "error_shifttracking_update_failed" );
        }

        logger.debugPage( "/jsp/shiftTracking03.jsp" );
        return "shiftTracking03";
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

    public boolean getWeekend_day() {
        return weekend_day;
    }

    public void setWeekend_day( boolean weekend_day ) {
        this.weekend_day = weekend_day;
    }

    public boolean getWeekend_night() {
        return weekend_night;
    }

    public void setWeekend_night( boolean weekend_night ) {
        this.weekend_night = weekend_night;
    }

    public boolean getWeek_locum() {
        return week_locum;
    }

    public void setWeek_locum( boolean week_locum ) {
        this.week_locum = week_locum;
    }
    
    private String getEmail() {
        if ( email == null || email.trim().equals( EMPTY_STRING ) ) {
            return EMPTY_STRING;
        }
        else {
            return email.trim().toLowerCase();
        }
    }

    private void setEmail(String email) {
        if ( email == null || email.trim().equals( EMPTY_STRING ) ) {
            this.email = EMPTY_STRING;
        }
        else {
            this.email = email.trim().toLowerCase();
        }
    }
}
