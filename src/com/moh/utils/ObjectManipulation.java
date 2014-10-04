package com.moh.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.moh.common.Constants;

public class ObjectManipulation implements Constants {

    /**
     * Clones any Java Object ( using deep-cloning technique )<BR>
     * 
     * @param ObjectToBeCloned
     * @return exact copy of the requested object
     */
    public Object cloneMe( Object ObjectToBeCloned ) {
        try {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            ObjectOutputStream oOut = new ObjectOutputStream( bOut );
            oOut.writeObject( ObjectToBeCloned );

            ByteArrayInputStream bIn = new ByteArrayInputStream( bOut.toByteArray() );
            ObjectInputStream oIn = new ObjectInputStream( bIn );

            return ( oIn.readObject() );
        }
        catch( Exception ex ) {
            return null;
        }
    }

    /**
     * If String is null then returns empty String, otherwise the value of the input String<BR>
     * 
     * @param varToCheck
     * @return the requested String trimmed, or an empty String if null
     */
    public String nullsToString( String varToCheck ) {
        String returnedValue = EMPTY_STRING;

        if( varToCheck != null && ! varToCheck.trim().equals(EMPTY_STRING) ) {
            returnedValue = varToCheck.trim();
        }

        return returnedValue;
    }

    /**
     * It will convert all carriage return characters within a String into single space characters<BR>
     * 
     * @param varToCheck
     * @return the requested String without carriage return characters
     */
    public String replaceCarriageReturn( String varToCheck, String replacement ) {
        String returnedValue = EMPTY_STRING;
        boolean continueLoop = true;

        if( varToCheck != null && ! varToCheck.trim().equals(EMPTY_STRING) ) {
            int x = varToCheck.indexOf( "\n" );

            if( x > 0 ) {
                while( continueLoop ) {
                    x = varToCheck.indexOf( "\n" );

                    if( x > 0 ) {
                        String varToCheck1 = varToCheck.substring( 0, x - 1 );
                        String varToCheck2 = varToCheck.substring( x + 1, varToCheck.length() );
                        varToCheck = varToCheck1 + replacement + varToCheck2;
                    }
                    else {
                        continueLoop = false;
                    }
                }
            }

            returnedValue = varToCheck;
        }

        return returnedValue;
    }
}
