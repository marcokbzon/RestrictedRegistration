package com.moh.utils;

import com.moh.common.Constants;

public class DataFormatter implements Constants {

    public DataFormatter() {
    }

    public String phoneFormatter( String inputField ) {
        if( inputField == null || inputField.trim().equals(EMPTY_STRING) ) {
            inputField = EMPTY_STRING;
        }

        String formattedPhone = inputField;

        if( inputField.length() == 10 ) {
            if( hasOnlyDigits( inputField ) ) {
                String areaCode = ( ( String ) inputField ).substring( 0, 3 ).trim();
                String phoneThree = ( ( String ) inputField ).substring( 3, 6 ).trim();
                String phoneFour = ( ( String ) inputField ).substring( 6 ).trim();

                formattedPhone = areaCode + "-" + phoneThree + "-" + phoneFour;
            }
        }

        return formattedPhone;
    }

    public String postalCodeFormatter( String inputField ) {
        if( inputField == null || inputField.trim().equals(EMPTY_STRING) ) {
            inputField = EMPTY_STRING;
        }

        String formattedPostalCode = inputField;

        if( inputField.length() == 6 ) {
            String part1 = ( ( String ) inputField ).substring( 0, 3 ).trim();
            String part2 = ( ( String ) inputField ).substring( 3 ).trim();

            formattedPostalCode = part1 + " " + part2;
        }

        return formattedPostalCode;
    }

    private boolean hasOnlyDigits( String s ) {
        for( int i = 0 ; i < s.length() ; i++ ) {
            if( !Character.isDigit( s.charAt( i ) ) ) {
                return false;
            }
        }

        return true;
    }
}
