package edu.cnm.deepdive.security;

import java.security.SecureRandom;

/**
 * Password generator using cryptographically secure random number generator.
 * this utilizes PasswordGenerator, and simply subsitutes the more robust
 * Png.
 * 
 * @author Dave
 *
 */
public class SecurePasswordGenerator extends PasswordGenerator {

	public SecurePasswordGenerator() {
		super ();
		rng = new SecureRandom();
		// TODO Auto-generated constructor stub
	}
/**
 * Invokes {@link PasswordGenerator#PasswordGenerator(int, int) constructor}
 * 
 * @param minLength
 * @param maxLength
 */
	public SecurePasswordGenerator(int minLength, int maxLength) {
		super(minLength, maxLength);
		// TODO Auto-generated constructor stub
	}
	/**
	 *   Invokes {@link PasswordGenerator#PasswordGenerator(int, int, boolean, boolean, boolean, boolean, boolean) constructor}
	 *   
	 * @param minLength
	 * @param maxLength
	 * @param includeUpperCase
	 * @param includeLowerCase
	 * @param includeNumbers
	 * @param includePunctuation
	 * @param excludeAmbiguous
	 */
	
	public SecurePasswordGenerator(int minLength, int maxLength, boolean includeUpperCase, boolean includeLowerCase,
			boolean includeNumbers, boolean includePunctuation, boolean excludeAmbiguous) {
		super(minLength, maxLength, includeUpperCase, includeLowerCase, includeNumbers, includePunctuation,
				excludeAmbiguous);
		// TODO Auto-generated constructor stub
	}

}
