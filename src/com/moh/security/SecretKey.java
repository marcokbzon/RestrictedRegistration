package com.moh.security;

/**
 * @author Marco Sosa 
 * 
 * Overrides javax.crypto.SecretKey
 */
public class SecretKey {

    private byte[] encodedKey = null;

    public byte[] getEncoded() {
        return getEncodedKey();
    }

    private byte[] getEncodedKey() {
        return encodedKey;
    }

    public void setEncodedKey( byte[] encodedKey ) {
        this.encodedKey = encodedKey;
    }
}
