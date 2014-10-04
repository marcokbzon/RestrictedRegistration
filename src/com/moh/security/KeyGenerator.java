package com.moh.security;

import java.util.ResourceBundle;

import com.moh.common.Constants;

/**
 * @author Marco Sosa 
 * 
 * Overrides javax.crypto.KeyGenerator
 */
public class KeyGenerator implements Constants {

    private static int KEYSTRING_SIZE = 16;
    private static int HEX_255 = 0xFF;
    private byte[] rawBytes = new byte[KEYSTRING_SIZE];
    private String selectedKey = "raw.04";

    public KeyGenerator( String algorithm ) {
        super();
    }

    public void init( int encryptionValue ) {
        ResourceBundle rb = ResourceBundle.getBundle( "SecurityKeys" );
        String keyString = rb.getString( selectedKey );

        String keyValue = EMPTY_STRING;
        int keyStrLen = keyString.length();
        int iniVal = 0, endVal = 0;

        for( int i = 0 ; i < KEYSTRING_SIZE ; i++ ) {
            endVal = keyString.indexOf( ",", iniVal );

            if( endVal == -1 ) {
                endVal = keyStrLen;
            }

            keyValue = keyString.substring( iniVal, endVal );
            iniVal = endVal + 1;
            byte oneByte = ( byte ) ( Long.parseLong( keyValue ) & HEX_255 );

            rawBytes[i] = oneByte;
        }

        setRawBytes( rawBytes );
    }

    public SecretKey generateKey() {
        SecretKey sk = new SecretKey();
        sk.setEncodedKey( getRawBytes() );

        return sk;
    }

    private byte[] getRawBytes() {
        return rawBytes;
    }

    private void setRawBytes( byte[] rawBytes ) {
        this.rawBytes = rawBytes;
    }
}
