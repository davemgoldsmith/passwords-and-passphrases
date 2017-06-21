/*
 * PasswordGenerator.java
 */

package edu.cnm.deepdive.security;

import java.util.Random;

/**
 * Implementation of a <em>simple</em> password generator. This includes support
 * for elementary character-wet based rules (optional inclusion of specified sets
 * and exclusion of a small number of ambiguous characters.. but not (yet)
 * regular expressions.
 * 
 * @author Dave Goldsmith
 * @version 1.0
 */
public class PasswordGenerator {

	private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String LOWERCASE = UPPERCASE.toLowerCase();
	private static final String NUMBERS = "0123456789";
	private static final String PUNCTUATION = "!@#$%&*<>,.";
	private static final String AMBIGUOUS = "[Ol]";
	private char[] pool = null;
	private int minLength = 6;
	private int maxLength = 12;
	protected Random rng = new Random();
	private boolean includeUpperCase = true;
	private boolean includeLowerCase = true;
	private boolean includeNumbers = true;
	private boolean includePunctuation = false;
	private boolean excludeAmbiguous = true;

	/**
	 * Test rig for generating passwords.
	 * 
	 * @param args
	 *            Command line parameters for generation options.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
/**
 * Constructor which generates random numbers.
 */
	public PasswordGenerator() {
		
		rng = new Random();
	}
/**
 *  Initialize generator for password of length in the range specified.
 * @param minLength
 * @param maxLength
 */
	public PasswordGenerator(int minLength, int maxLength) {
		this();
		System.out.println("In overloaded constructor");
		this.minLength = minLength;
		this.maxLength = maxLength;
	}
	/**
	 *  Constructor that creates new instance objects of the below options regarding 
	 *  the generation of passwords.
	 * @param minLength
	 * @param maxLength
	 * @param includeUpperCase
	 * @param includeLowerCase
	 * @param includeNumbers
	 * @param includePunctuation
	 * @param excludeAmbiguous
	 */
	public PasswordGenerator(int minLength, int maxLength, 
			boolean includeUpperCase, boolean includeLowerCase,
			boolean includeNumbers, boolean includePunctuation,
			boolean excludeAmbiguous) {
		this(minLength, maxLength);
		
		this.includeUpperCase = includeUpperCase;
		this.includeLowerCase = includeLowerCase;
		this.includeNumbers = includeNumbers;
		this.includePunctuation = includePunctuation;
		this.excludeAmbiguous = excludeAmbiguous;
	
			}

	/**
	 * @return the maxLength
	 */
	public int getMaxLength() {
		return maxLength;
	}

	/**
	 * @param maxLength
	 *            the maxLength to set
	 */
	protected void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	/**
	 * @return the minLength
	 */
	public int getMinLength() {
		return minLength;
	}

	/**
	 * @param minLength
	 *            the minLength to set
	 */
	protected void setMinLength(int minLength) {
		this.minLength = minLength;
	}
	
	private void setupPool(){
		if (pool == null){
			StringBuilder builder = new StringBuilder();
			if (includeLowerCase){
				builder.append(LOWERCASE);
				}
			if (includeUpperCase){
				builder.append(UPPERCASE);
				}
			if (includeNumbers){
				builder.append(NUMBERS);
				}
			if (includePunctuation){
				builder.append(PUNCTUATION);
				}
			String work = builder.toString();
			if (excludeAmbiguous){
			work.replaceAll(AMBIGUOUS, "");
				} 
		pool = work.toCharArray();
		}
	}
	/**
	 * 	Method generate that puts the characters together in a string to create the completed password.
	 * @return Returns the finished password string.
	 */
	public String generate() {
		setupPool();
		int passwordLength = minLength + rng.nextInt(maxLength - minLength);
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < passwordLength; i++) {
			char selection = pool[rng.nextInt(pool.length)];
			builder.append(selection);
			
		}
		return builder.toString();
	}

}
