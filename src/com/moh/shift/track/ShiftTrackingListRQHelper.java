package com.moh.shift.track;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ShiftTrackingDAO;
import com.moh.utils.Logger;

public class ShiftTrackingListRQHelper extends AbstractBean {

    private String yearMonth;
    private String shifts;
    private String hours;
    private String shiftTrackId;
    private String email;

    public ShiftTrackingListRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ShiftTrackingListRQHelper" );

        removeFromSession( SESSION_APPLICATIONID );

        open();
    }

    public String open() {
        logger.debugMethod( "open" );

        String year = ( String ) getFromSession( SHIFT_TRACK_YEAR );
        String month = ( String ) getFromSession( SHIFT_TRACK_MONTH );
        String monthLong = EMPTY_STRING;

        if( month.equals( MONTH_JAN_NN ) ) {
            monthLong = MONTH_JAN_FULL;
        }
        else {
            if( month.equals( MONTH_FEB_NN ) ) {
                monthLong = MONTH_FEB_FULL;
            }
            else {
                if( month.equals( MONTH_MAR_NN ) ) {
                    monthLong = MONTH_MAR_FULL;
                }
                else {
                    if( month.equals( MONTH_APR_NN ) ) {
                        monthLong = MONTH_APR_FULL;
                    }
                    else {
                        if( month.equals( MONTH_MAY_NN ) ) {
                            monthLong = MONTH_MAY_FULL;
                        }
                        else {
                            if( month.equals( MONTH_JUN_NN ) ) {
                                monthLong = MONTH_JUN_FULL;
                            }
                            else {
                                if( month.equals( MONTH_JUL_NN ) ) {
                                    monthLong = MONTH_JUL_FULL;
                                }
                                else {
                                    if( month.equals( MONTH_AUG_NN ) ) {
                                        monthLong = MONTH_AUG_FULL;
                                    }
                                    else {
                                        if( month.equals( MONTH_SEP_NN ) ) {
                                            monthLong = MONTH_SEP_FULL;
                                        }
                                        else {
                                            if( month.equals( MONTH_OCT_NN ) ) {
                                                monthLong = MONTH_OCT_FULL;
                                            }
                                            else {
                                                if( month.equals( MONTH_NOV_NN ) ) {
                                                    monthLong = MONTH_NOV_FULL;
                                                }
                                                else {
                                                    if( month.equals( MONTH_DEC_NN ) ) {
                                                        monthLong = MONTH_DEC_FULL;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        setYearMonth( monthLong + " " + year );

        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );

        ShiftTrackingDAO shiftDAO = new ShiftTrackingDAO();

        String[] shiftInfo = shiftDAO.getListingHeader( getEmail(), year, month );

        setShifts( shiftInfo[0] );
        setHours( shiftInfo[1] );

        logger.debugPage( "/jsp/shiftTracking02.jsp" );
        return "shiftTracking02";
    }

    public String editInfo() {
        logger.debugMethod( "editInfo" );

        if( shiftTrackId == null || shiftTrackId.trim().equals(EMPTY_STRING) ) {
            addErrorMessage( "error_application_required" );
            
            logger.debugPage( "/jsp/shiftTracking02.jsp" );
            return "shiftTracking02";
        }
        else {
            addToSession( SESSION_APPLICATIONID, shiftTrackId );
            
            logger.debugPage( "/jsp/shiftTracking03.jsp" );
            return "shiftTracking03";
        }
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth( String yearMonth ) {
        this.yearMonth = yearMonth;
    }

    public String getShifts() {
        return shifts;
    }

    public void setShifts( String shifts ) {
        this.shifts = shifts;
    }

    public String getHours() {
        return hours;
    }

    public void setHours( String hours ) {
        this.hours = hours;
    }

    public String getShiftTrackId() {
        return shiftTrackId;
    }

    public void setShiftTrackId( String shiftTrackId ) {
        this.shiftTrackId = shiftTrackId;
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
