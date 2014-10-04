package com.moh.admin;

import java.util.Iterator;
import java.util.List;

import com.moh.common.AbstractBean;
import com.moh.data.bean.ReportShiftsByDateData;
import com.moh.data.dao.ShiftTrackingDAO;
import com.moh.utils.Logger;

public class ReportShiftsByDateRQHelper extends AbstractBean {

    public String totalHours;
    public String totalShifts;

    public ReportShiftsByDateRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ReportShiftsByDateRQHelper" );
    }

    public String shiftTrackingList() {
        ShiftTrackingDAO shiftTrackingDAO = new ShiftTrackingDAO();
        List<ReportShiftsByDateData> reportList = shiftTrackingDAO.getShiftsByDate();

        Iterator<ReportShiftsByDateData> iter = reportList.iterator();

        int hours = 0;
        int shifts = 0;

        while( iter.hasNext() ) {
            ReportShiftsByDateData dataBean = iter.next();
            hours = hours + Integer.parseInt( dataBean.getNumberOfHours() );
            shifts = shifts + Integer.parseInt( dataBean.getNumberOfShifts() );
        }

        setTotalHours( EMPTY_STRING + hours );
        setTotalShifts( EMPTY_STRING + shifts );

        logger.debugPage( "/jsp/reportShiftTrack05.jsp" );
        return "reportShiftTrack05";
    }

    public String getTotalHours() {
        return totalHours;
    }

    public void setTotalHours( String totalHours ) {
        this.totalHours = totalHours;
    }

    public String getTotalShifts() {
        return totalShifts;
    }

    public void setTotalShifts( String totalShifts ) {
        this.totalShifts = totalShifts;
    }
}
