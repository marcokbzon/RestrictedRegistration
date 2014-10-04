package com.moh.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateFormatter {

    /**
     * Formats Calendar with Date and Time patterns
     * 
     *      Letter  Description              Example
     *      ------  -----------------------  ------------------------------------
     *      G       Era designator           AD  
     *      y       Year                     1996; 96
     *      M       Month in year            July; Jul; 07
     *      w       Week in year             27
     *      W       Week in month            2
     *      D       Day in year              189
     *      d       Day in month             10
     *      F       Day of week in month     2
     *      E       Day in week              Tuesday; Tue  
     *      a       Am/pm marker             PM  
     *      H       Hour in day (0-23)       0  
     *      k       Hour in day (1-24)       24  
     *      K       Hour in am/pm (0-11)     0  
     *      h       Hour in am/pm (1-12)     12  
     *      m       Minute in hour           30  
     *      s       Second in minute         55  
     *      S       Millisecond              978  
     *      z       Time zone                Pacific Standard Time; PST; GMT-08:00  
     * 
     * @param calendarToFormat
     * @param infoLevel
     */
    public String formatCalendar( Calendar calendarToFormat, int infoLevel ) {
        SimpleDateFormat formatter = null;

        switch( infoLevel ) {
            case 0:
                formatter = new SimpleDateFormat( "yyyy-MMM-dd hh:mm:ss.SSS" ); // 2008-Mar-23 11:41:03.436  ( used by Logger )
                break;
            case 1:
                formatter = new SimpleDateFormat( "E yyyy-MMM-dd 'at' hh:mm:ss a" ); // Sun 2008-Mar-23 at 11:41:37 AM
                break;
            case 2:
                formatter = new SimpleDateFormat( "E yyyy-MMM-dd hh:mm a" ); // Sun 2008-Mar-23 11:41 AM
                break;
            case 3:
                formatter = new SimpleDateFormat( "dd-MM-yyyy hh:mm a" ); // 23-03-2008 11:42 AM
                break;
            case 4:
                formatter = new SimpleDateFormat( "dd MMM yyyy" ); // 23 Mar 2008
                break;
            case 5:
                formatter = new SimpleDateFormat( "MMM yyyy" ); // Mar 2008
                break;
            case 6:
                formatter = new SimpleDateFormat( "yyyy-MM" ); // 2008-03
                break;
            case 7:
                formatter = new SimpleDateFormat( "yyyy-MM-dd" ); // 2008-03-23
                break;
            case 8:
                formatter = new SimpleDateFormat( "MMMM d, yyyy" ); // March 23, 2008
                break;
            default:
                formatter = new SimpleDateFormat( "yyyy/MMM/dd" ); // 2008/Mar/23
                break;
        }

        return formatter.format( calendarToFormat.getTime() );
    }
}
