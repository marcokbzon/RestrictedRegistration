package com.moh.utils;

import java.util.Calendar;

import com.moh.common.AbstractBean;

public class KeyGenerator extends AbstractBean {

    public String getApplicationID() {

        //M = getFromSession( SESSION_FIRSTNAME );
        //S = getFromSession( SESSION_LASTNAME );

        return "MS" + getKey();
    }

    private String getKey() {
        Calendar cal = Calendar.getInstance();

        String year = ( EMPTY_STRING + cal.get( Calendar.YEAR ) ).substring( 3 );

        int month = cal.get( Calendar.MONTH );
        String sMonth = EMPTY_STRING + month;

        if( month > 9 ) {
            sMonth = getAlphaChar( month );
        }

        int day = cal.get( Calendar.DAY_OF_MONTH );
        String sDay = EMPTY_STRING + day;

        if( day > 9 ) {
            sDay = getAlphaChar( day );
        }

        int hour = cal.get( Calendar.HOUR_OF_DAY );
        String sHour = EMPTY_STRING + hour;

        if( hour > 9 ) {
            sHour = getAlphaChar( hour );
        }

        int minute = cal.get( Calendar.MINUTE );
        String sMinute = EMPTY_STRING + minute;

        if( sMinute.length() == 1 ) {
            sMinute = "0" + sMinute;
        }

        int second = cal.get( Calendar.SECOND );
        String sSecond = EMPTY_STRING + second;

        if( sSecond.length() == 1 ) {
            sSecond = "0" + sSecond;
        }

        return "-" + year + sMonth + sDay + "-" + sHour + sMinute + sSecond;
    }

    private String getAlphaChar( int NumValue ) {
        String returnedValue = EMPTY_STRING;

        switch( NumValue ) {
            case 10:
                returnedValue = "0";
                break;
            case 11:
                returnedValue = "A";
                break;
            case 12:
                returnedValue = "B";
                break;
            case 13:
                returnedValue = "C";
                break;
            case 14:
                returnedValue = "D";
                break;
            case 15:
                returnedValue = "E";
                break;
            case 16:
                returnedValue = "F";
                break;
            case 17:
                returnedValue = "G";
                break;
            case 18:
                returnedValue = "H";
                break;
            case 19:
                returnedValue = "I";
                break;
            case 20:
                returnedValue = "J";
                break;
            case 21:
                returnedValue = "K";
                break;
            case 22:
                returnedValue = "L";
                break;
            case 23:
                returnedValue = "M";
                break;
            case 24:
                returnedValue = "N";
                break;
            case 25:
                returnedValue = "O";
                break;
            case 26:
                returnedValue = "P";
                break;
            case 27:
                returnedValue = "Q";
                break;
            case 28:
                returnedValue = "R";
                break;
            case 29:
                returnedValue = "S";
                break;
            case 30:
                returnedValue = "T";
                break;
            case 31:
                returnedValue = "U";
                break;
            default:
                break;
        }

        return returnedValue;
    }
}
