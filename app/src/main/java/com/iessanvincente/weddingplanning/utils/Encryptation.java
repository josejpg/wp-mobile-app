package com.iessanvincente.weddingplanning.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Jose J. Pardines
 */
public class Encryptation {

	/**
	 * Encrypt a plain text into MD5
	 *
	 * @param text to encode
	 * @return a string encoded in MD5
	 */

	public static String encryptMD5( String text ) {
		final String encrypt = "MD5";
		String generatedPassword = null;
		try {
			// Create MD5 Hash
			MessageDigest md = MessageDigest.getInstance( encrypt );

			//Creating MD% string with Hash
			byte[] bytes = md.digest( text.getBytes() );
			BigInteger number = new BigInteger( 1, bytes );
			generatedPassword = number.toString( 16 );
			while ( generatedPassword.length() < 32 ) {
				generatedPassword = "0" + generatedPassword;
			}
		} catch (NoSuchAlgorithmException e) {
			// never happens
			e.printStackTrace();
		}
		return generatedPassword;
	}

	/**
	 * Encrypt a plain text into SHA512
	 *
	 * @param text to encode
	 * @return a string encoded in SHA512
	 */
	public static String encryptSHA512( String text ) {
		final String encrypt = "SHA-512";
		String generatedPassword = null;
		try {
			// Create SHA512 Hash
			MessageDigest md = MessageDigest.getInstance( encrypt );
			md.update( "WeddingPlanning".getBytes() );
			byte[] bytes = md.digest( text.getBytes() );
			StringBuilder sb = new StringBuilder();
			for ( int i = 0; i < bytes.length; i++ ) {
				sb.append( Integer.toString( ( bytes[ i ] & 0xff ) + 0x100, 16 ).substring( 1 ) );
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}
}
