/**
 * 
 */
package edu.cnm.deepdive.security;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * @author davem
 *
 */
public class PassphraseGenerator {
	
	/**Constant to define that the default word list will be the resources/wordlist "wordlist.properties" */
	public static final String DEFAULT_WORD_LIST = "resources/wordlist";
	
	/**Constant to define the default delimiter between words in the passphrase will be a single space, 
	 * if not specified in the command line */
	public static final String DEFAULT_DELIMITER = " ";
	
	/**Constant that defines that the default length of a passphrase is 6 words. */
	public static final int DEFAULT_lENGTH = 6;
	
	private String wordList = DEFAULT_WORD_LIST;
	private String delimiter = DEFAULT_DELIMITER;
	private int length = DEFAULT_lENGTH;
	private Random rng = null;
	private ArrayList<String> pool = null;
	
	/**
	 * Passphrase generator that invokes the superclass guard.
	 */
	public PassphraseGenerator() {
		super();
		
	}
	/**
	 * setupPool constructor that creates the pool of words to be used in the
	 * passphrase generation, by getting the wordList from the resource bundle 
	 * called wordlist.properties. Note: the resource bundle can get different wordlists.
	 */
	protected void setupPool(){
		ResourceBundle bundle = ResourceBundle.getBundle(wordList);
		pool = new ArrayList<>();
		Enumeration<String> keyEnum = bundle.getKeys();
		while (keyEnum.hasMoreElements()) {
			String key = keyEnum.nextElement();
			String word = bundle.getString(key);
			pool.add(word);			
		}
	}
	
	/**
	 * setupRng method that invokes a new SecureRandom random number generator.
	 */
	protected void setupRng(){
		rng = new SecureRandom();
	}

	public String generate() {
		if (pool == null) {
			setupPool();
		}
		if (rng == null)  {
			setupRng();
		}
				StringBuilder builder = new StringBuilder();
				String word = pool.get(rng.nextInt(pool.size()));
				builder.append(word);
		for (int i = 0; i < length - 1; i++) {
			word = pool.get(rng.nextInt(pool.size()));
			builder.append(delimiter + word);
			}
		return builder.toString().trim();
	}
	
	/**
	 * @return the wordList
	 */
	public String getWordList() {
		return wordList;
	}
	
	/**
	 * @param wordList the wordList to set
	 */
	public void setWordList(String wordList) {
		this.wordList = wordList;
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
	 * @return the length
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
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
	
} //Passphrase generator class
