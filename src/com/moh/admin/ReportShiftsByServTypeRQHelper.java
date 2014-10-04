package com.moh.admin;

import java.util.Iterator;
import java.util.List;

import com.moh.common.AbstractBean;
import com.moh.data.bean.ReportShiftsByServTypeData;
import com.moh.data.dao.ShiftTrackingDAO;
import com.moh.utils.Logger;

public class ReportShiftsByServTypeRQHelper extends AbstractBean {

    public String totalHours;
    public String totalShifts;

    public ReportShiftsByServTypeRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ReportShiftsByServTypeRQHelper" );
    }

    public String shiftTrackingList() {
        ShiftTrackingDAO shiftTrackingDAO = new ShiftTrackingDAO();
        List<ReportShiftsByServTypeData> reportList = shiftTrackingDAO.getShiftsByServiceType();

        Iterator<ReportShiftsByServTypeData> iter = reportList.iterator();

        int hours = 0;
        int shifts = 0;

        while( iter.hasNext() ) {
            ReportShiftsByServTypeData dataBean = iter.next();
            hours = hours + Integer.parseInt( dataBean.getNumberOfHours() );
            shifts = shifts + Integer.parseInt( dataBean.getNumberOfShifts() );
        }

        setTotalHours( EMPTY_STRING + hours );
        setTotalShifts( EMPTY_STRING + shifts );

        logger.debugPage( "/jsp/reportShiftTrack01.jsp" );
        return "reportShiftTrack01";
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
