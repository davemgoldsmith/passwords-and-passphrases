package edu.cnm.deepdive.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Password generator using cryptographically secure random number generator.
 * This utilizes PasswordGenerator, and simply substitutes the more robust
 * random number generator.
 * 
 * @author Dave
 *
 */
public class SecurePasswordGenerator extends PasswordGenerator {

	public SecurePasswordGenerator() {
		super ();
		
	}
	/**
	 * Uses an instance of Secure password generator, rather than password generator.
	 */
	@Override
protected void setupRng() {
	try {
		setRng(SecureRandom.getInstanceStrong());
	} catch (NoSuchAlgorithmException ex) {
		throw new RuntimeException(ex);
	}
	
}
}
