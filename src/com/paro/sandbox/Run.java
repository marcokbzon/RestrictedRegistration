package com.paro.sandbox;

import com.moh.security.AESEncrypter;

public class Run {

	public static void main( String[] args ) {
		// 
		decryptPassword( "310c4691e0e3391152aaf374aee9982d" );
	}

	public static void decryptPassword( String passwordString ) {
        AESEncrypter aesEncrypter = new AESEncrypter();
        aesEncrypter.init();

        String password = aesEncrypter.convertFromHexDec( passwordString );

        System.out.println( "Password is: [" + password + "]" );
    }
}
