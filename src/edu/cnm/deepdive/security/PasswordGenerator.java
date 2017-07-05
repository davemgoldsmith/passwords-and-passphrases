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
	
	public static final int DEFAULT_PASSWORD_LENGTH = 12;
	
	private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String LOWERCASE = UPPERCASE.toLowerCase();
	private static final String NUMBERS = "0123456789";
	//*Punctuation characters that may optionally be included in the password. */
	private static final String PUNCTUATION = "!@#$%&*<>,.";
	private static final String AMBIGUOUS = "[Ol]";
	private Random rng = null;
	private char[] pool = null;
	private int minLength = DEFAULT_PASSWORD_LENGTH;
	private int maxLength = DEFAULT_PASSWORD_LENGTH;
	private boolean upperCaseIncluded = true;
	private boolean lowerCaseIncluded = true;
	private boolean numbersIncluded = true;
	private boolean punctuationIncluded = false;
	private boolean ambiguousIncluded = true;
	private String delimiter = "";
			
	/**
 * Constructor which generates random numbers.
 */
	public PasswordGenerator() {
		
		setRng(new Random());
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
			if (isLowerCaseIncluded()){
				builder.append(LOWERCASE);
				}
			if (isUpperCaseIncluded()){
				builder.append(UPPERCASE);
				}
			if (isNumbersIncluded()){
				builder.append(NUMBERS);
				}
			if (isPunctuationIncluded()){
				builder.append(PUNCTUATION);
				}
			String work = builder.toString();
			if (isAmbiguousIncluded()){
			work.replaceAll(AMBIGUOUS, "");
				} 
		pool = work.toCharArray();
		}
		
	}
	/**
	 * 
	 */
	protected void setupRng() {
		if (rng == null){
			rng = new Random();
		}
				
	}
	
	/**
	 * 	Method generate that puts the characters together in a string to create the completed password.
	 * @return Returns the finished password string.
	 */
	public String generate() {
		setupPool();
		setupRng();
		
		int passwordLength = minLength + getRng().nextInt(maxLength - minLength + 1);
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < passwordLength; i++) {
			char selection = pool[getRng().nextInt(pool.length)];
			builder.append(selection);
			
		}
		return builder.toString();
	}

	/**
	 * @return the upperCaseIncluded
	 */
	public boolean isUpperCaseIncluded() {
		return upperCaseIncluded;
	}

	/**
	 * @param upperCaseIncluded the upperCaseIncluded to set
	 */
	public void setUpperCaseIncluded(boolean upperCaseIncluded) {
		this.upperCaseIncluded = upperCaseIncluded;
	}

	/**
	 * @return the lowerCaseIncluded
	 */
	public boolean isLowerCaseIncluded() {
		return lowerCaseIncluded;
	}

	/**
	 * @param lowerCaseIncluded the lowerCaseIncluded to set
	 */
	public void setLowerCaseIncluded(boolean lowerCaseIncluded) {
		this.lowerCaseIncluded = lowerCaseIncluded;
	}

	/**
	 * @return the numbersIncluded
	 */
	public boolean isNumbersIncluded() {
		return numbersIncluded;
	}

	/**
	 * @param numbersIncluded the numbersIncluded to set
	 */
	public void setNumbersIncluded(boolean numbersIncluded) {
		this.numbersIncluded = numbersIncluded;
	}

	/**
	 * @return the punctuationIncluded
	 */
	public boolean isPunctuationIncluded() {
		return punctuationIncluded;
	}

	/**
	 * @param punctuationIncluded the punctuationIncluded to set
	 */
	public void setPunctuationIncluded(boolean punctuationIncluded) {
		this.punctuationIncluded = punctuationIncluded;
	}

	/**
	 * @return the ambiguousIncluded
	 */
	public boolean isAmbiguousIncluded() {
		return ambiguousIncluded;
	}

	/**
	 * @param ambiguousIncluded the ambiguousIncluded to set
	 */
	public void setAmbiguousIncluded(boolean ambiguousIncluded) {
		this.ambiguousIncluded = ambiguousIncluded;
	}

	/**
	 * @return the delimiter
	 */
	public String getDelimiter() {
		return delimiter;
	}

	/**
	 * @param delimiter the delimiter to set
	 */
	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	/**
	 * @return the rng
	 */
	protected Random getRng() {
		return rng;
	}

	/**
	 * @param rng the rng to set
	 */
	protected void setRng(Random rng) {
		this.rng = rng;
	}

}
