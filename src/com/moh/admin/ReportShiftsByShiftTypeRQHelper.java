package com.moh.admin;

import com.moh.common.AbstractBean;
import com.moh.common.Constants;
import com.moh.data.dao.ShiftTrackingDAO;
import com.moh.utils.Logger;

public class ReportShiftsByShiftTypeRQHelper extends AbstractBean implements Constants {

    private String weekdayCount;
    private String weeknightCount;
    private String weekendDayCount;
    private String weekendNightCount;
    private String locumWeekCount;

    public ReportShiftsByShiftTypeRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ReportShiftsByShiftTypeRQHelper" );
    }

    public String shiftTrackingList() {
        ShiftTrackingDAO shiftTrackingDAO = new ShiftTrackingDAO();

        String weekdayInfo = EMPTY_STRING + shiftTrackingDAO.getWeekCount( SHIFT_TYPE_WEEK_DAY );
        String weeknightInfo = EMPTY_STRING + shiftTrackingDAO.getWeekCount( SHIFT_TYPE_WEEK_NIGHT );
        String weekendDayInfo = EMPTY_STRING + shiftTrackingDAO.getWeekCount( SHIFT_TYPE_WEEKEND_DAY );
        String weekendNightInfo = EMPTY_STRING + shiftTrackingDAO.getWeekCount( SHIFT_TYPE_WEEKEND_NIGHT );
        String locumWeekInfo = EMPTY_STRING + shiftTrackingDAO.getWeekCount( SHIFT_TYPE_LOCUM_WEEK );

        setWeekdayCount( weekdayInfo );
        setWeeknightCount( weeknightInfo );

        setWeekendDayCount( weekendDayInfo );
        setWeekendNightCount( weekendNightInfo );

        setLocumWeekCount( locumWeekInfo );

        logger.debugPage( "/jsp/reportShiftTrack03.jsp" );
        return "reportShiftTrack03";
    }

    public String getWeekdayCount() {
        return weekdayCount;
    }

    public void setWeekdayCount( String weekdayCount ) {
        this.weekdayCount = weekdayCount;
    }

    public String getWeeknightCount() {
        return weeknightCount;
    }

    public void setWeeknightCount( String weeknightCount ) {
        this.weeknightCount = weeknightCount;
    }

    public String getWeekendDayCount() {
        return weekendDayCount;
    }

    public void setWeekendDayCount( String weekendDayCount ) {
        this.weekendDayCount = weekendDayCount;
    }

    public String getWeekendNightCount() {
        return weekendNightCount;
    }

    public void setWeekendNightCount( String weekendNightCount ) {
        this.weekendNightCount = weekendNightCount;
    }

    public String getLocumWeekCount() {
        return locumWeekCount;
    }

    public void setLocumWeekCount( String locumWeekCount ) {
        this.locumWeekCount = locumWeekCount;
    }
}
