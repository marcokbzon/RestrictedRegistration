package com.moh.security;

import java.security.*;

import javax.crypto.*;
import javax.crypto.spec.*;

import com.moh.common.Constants;

/**
 * @author Marco Sosa 
 * 
 * This program generates an AES key, retrieves it in raw byte format, then re-instantiates 
 * another AES key from the previously generated key bytes.
 *  
 * The re-instantiated key is used to initialize an AES cipher for encryption and de-cryption. 
 * The methods convertToHexDec and convertFromHexDec are meant to be used to read/write passwords 
 * in/from the database as hex values.
 */
public class AESEncrypter implements Constants {

    private static Cipher cipher = null;
    private static SecretKeySpec skeySpec = null;
    private static int HEX_255 = 0xFF;
    private static int MAX_NUM_BASE = 16;
    private static byte MAX_BYTE = 127;
    // private static byte MIN_BYTE = -128;
    private static int HEXDEC_LENGTH = 2;

    public String getReverse( String input ) {
        String returnedValue = EMPTY_STRING;

        char[] seqOfChars = input.toCharArray();

        for( int i = 0 ; i < seqOfChars.length ; i++ ) {
            returnedValue = seqOfChars[i] + returnedValue;
        }

        return returnedValue;
    }

    public String convertToHexDec( String userInput ) {
        String encodedValue = EMPTY_STRING;
        String partOfPsswd = EMPTY_STRING;

        try {
            byte[] encryptedValue = encrypt( userInput );

            for( int i = 0 ; i < encryptedValue.length ; i++ ) {
                partOfPsswd = Long.toString( ( int ) encryptedValue[i] & HEX_255, MAX_NUM_BASE );

                if( partOfPsswd.length() == 1 ) {
                    partOfPsswd = "0" + partOfPsswd;
                }
                encodedValue = encodedValue + partOfPsswd;
            }
        }
        catch( Exception ex ) {
            System.out.println( "AESEncrypter.convertForDB() Exception: " + ex );
        }

        return getReverse( encodedValue );
    }

    public String convertFromHexDec( String dbValue ) {
        String dbEntry = getReverse( dbValue );
        String decodedValue = EMPTY_STRING;
        int entryLen = dbEntry.length();
        byte[] encryptedValue = new byte[entryLen / HEXDEC_LENGTH];
        String pieceOfEntry = EMPTY_STRING;
        int iniPos = 0;
        int endPos = HEXDEC_LENGTH;

        try {
            for( int i = 0 ; i < entryLen / 2 ; i++ ) {
                pieceOfEntry = dbEntry.substring( iniPos, endPos );
                int outputVal = Integer.parseInt( pieceOfEntry, MAX_NUM_BASE );
                if( outputVal > MAX_BYTE ) {
                    outputVal = ( outputVal - ( HEX_255 + 1 ) );
                }
                encryptedValue[i] = ( byte ) outputVal;
                iniPos = iniPos + HEXDEC_LENGTH;
                endPos = endPos + HEXDEC_LENGTH;
            }

            decodedValue = decrypt( encryptedValue );
        }
        catch( Exception ex ) {
            System.out.println( "AESEncrypter.convertFromDB() Exception: " + ex );
        }

        return decodedValue;
    }

    public void init() {
        try {
            KeyGenerator kgen = new KeyGenerator( "AES" );
            kgen.init( 128 );

            SecretKey skey = kgen.generateKey();
            byte[] raw = skey.getEncoded();

            String rawList = EMPTY_STRING;
            for( int i = 0 ; i < raw.length ; i++ ) {
                rawList = rawList + raw[i] + ",";
            }

            skeySpec = new SecretKeySpec( raw, "AES" );

            cipher = Cipher.getInstance( "AES" );
        }
        catch( NoSuchAlgorithmException nsaex ) {
        }
        catch( NoSuchPaddingException nspex ) {
        }
    }

    synchronized private byte[] encrypt( String userInput ) throws InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {
        byte[] encryptedValue = null;

        cipher.init( Cipher.ENCRYPT_MODE, skeySpec );

        encryptedValue = cipher.doFinal( ( userInput ).getBytes() );

        return encryptedValue;
    }

    synchronized private String decrypt( byte[] encodedInput ) throws InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {
        String decryptedValue = EMPTY_STRING;

        cipher.init( Cipher.DECRYPT_MODE, skeySpec );

        byte[] decrypted = cipher.doFinal( encodedInput );
        decryptedValue = new String( decrypted );

        return decryptedValue;
    }
}
