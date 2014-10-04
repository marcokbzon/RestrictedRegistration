package com.moh.utils;

import com.moh.common.Constants;
import java.util.Calendar;

public class Test implements Constants {

    public static void main( String[] args ) {

        String updatedOn = "2008-03-21 20:08:00"; // March 21, 2008

        int uoYear = Integer.parseInt( updatedOn.substring( 0, 4 ) );
        int uoMonth = Integer.parseInt( updatedOn.substring( 5, 7 ) );
        int uoDay = Integer.parseInt( updatedOn.substring( 8, 10 ) );

        Calendar cal = Calendar.getInstance();

        System.out.println( "LS" + cal.getTimeInMillis() );

        cal.set( Calendar.YEAR, uoYear );
        cal.set( Calendar.MONTH, uoMonth - 1 );
        cal.set( Calendar.DAY_OF_MONTH, uoDay );

        DateFormatter df = new DateFormatter();

        System.out.println( EMPTY_STRING + df.formatCalendar( cal, 8 ) );
    }
}
