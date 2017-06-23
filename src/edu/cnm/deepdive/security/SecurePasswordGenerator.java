package edu.cnm.deepdive.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Password generator using cryptographically secure random number generator.
 * this utilizes PasswordGenerator, and simply substitutes the more robust
 * rng.
 * 
 * @author Dave
 *
 */
public class SecurePasswordGenerator extends PasswordGenerator {

	public SecurePasswordGenerator() {
		super ();
		// TODO Auto-generated constructor stub
	}
	@Override
protected void setupRng() {
	try {
		setRng(SecureRandom.getInstanceStrong());
	} catch (NoSuchAlgorithmException ex) {
		throw new RuntimeException(ex);
	}
	
}
}
