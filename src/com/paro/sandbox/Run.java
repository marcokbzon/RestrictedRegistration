package com.paro.sandbox;

import com.moh.security.AESEncrypter;

public class Run {

	public static void main( String[] args ) {
		// 
		decryptPassword( "225eec9396d63db5ad507f16fae08a69" );
	}

	public static void decryptPassword( String passwordString ) {
        AESEncrypter aesEncrypter = new AESEncrypter();
        aesEncrypter.init();

        String password = aesEncrypter.convertFromHexDec( passwordString );

        System.out.println( "Password is: [" + password + "]" );
    }
}
