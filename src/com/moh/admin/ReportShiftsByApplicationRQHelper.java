package com.moh.admin;

import java.util.Iterator;
import java.util.List;

import com.moh.common.AbstractBean;
import com.moh.data.bean.ReportShiftsByApplicationData;
import com.moh.data.dao.ShiftTrackingDAO;
import com.moh.utils.Logger;

public class ReportShiftsByApplicationRQHelper extends AbstractBean {

    public String totalHours;
    public String totalShifts;

    public ReportShiftsByApplicationRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ReportShiftsByApplicationRQHelper" );
    }

    public String shiftTrackingList() {
        ShiftTrackingDAO shiftTrackingDAO = new ShiftTrackingDAO();
        List<ReportShiftsByApplicationData> reportList = shiftTrackingDAO.getShiftsByApplication();

        Iterator<ReportShiftsByApplicationData> iter = reportList.iterator();

        int hours = 0;
        int shifts = 0;

        while( iter.hasNext() ) {
            ReportShiftsByApplicationData dataBean = iter.next();
            hours = hours + Integer.parseInt( dataBean.getNumberOfHours() );
            shifts = shifts + Integer.parseInt( dataBean.getNumberOfShifts() );
        }

        setTotalHours( EMPTY_STRING + hours );
        setTotalShifts( EMPTY_STRING + shifts );

        logger.debugPage( "/jsp/reportShiftTrack04.jsp" );
        return "reportShiftTrack04";
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
