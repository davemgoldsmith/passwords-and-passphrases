/**
 * Guard.java
 */
package edu.cnm.deepdive.security;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a program to generate random passwords and passphrases. Generation
 * uses a cryptographically secure random number generator to select words from
 * a list, or characters from a pool.
 * 
 * 
 * @author dave goldsmith
 */
public class Guard {

	/**
	 * Pass command line arguments to a parser from the Apache Commons CLI
	 * library, then instantiate and invoke the appropriate classes and methods
	 * to generate the requested artifact.
	 * 
	 * @param args
	 *            Command line arguments specifying generation options.
	 */
	public static void main(String[] args) {
		/**
		 * get options generate artifact emit artifact
		 */

		HashMap<String, Object> map = Options.getOptions(args);
		if (map != null){
			String artifact = generateArtifact(map);
			emitArtifact(artifact);
		} //if
	}  // main

	static String generateArtifact(HashMap<String, Object> map) {
		if (map.containsKey("m")) {
			PasswordGenerator gen = new SecurePasswordGenerator();
			for (Map.Entry<String, Object> entry : map.entrySet()){
				switch (entry.getKey()) {
				case "L" :
				int length = ((Number) entry.getValue()).intValue();
				gen.setMinLength(length);
				gen.setMaxLength(length);
				break;
				case "a":
					gen.setAmbiguousIncluded(false);
					break;
				case "b" :
					gen.setUpperCaseIncluded(false);
					break;
				case "s" :
					gen.setLowerCaseIncluded(false);
					break;
				case "n" :
					gen.setNumbersIncluded(false);
					break;
				case "p" :
					gen.setPunctuationIncluded(false);
					break;
					default:
						break;
					
				}
			}
			return gen.generate();
			
			// TODO set fields for all relevant and specified options.
			// 
		}   //if
		else {
			PassphraseGenerator gen = new PassphraseGenerator();
			for (Map.Entry<String, Object> entry : map.entrySet()){
				switch (entry.getKey()) {
				case "L" :
				int length = ((Number) entry.getValue()).intValue();
				gen.setLength(length);
				break;
				case "d":
					String delimiter = (String) entry.getValue();
					gen.setDelimiter(delimiter);
					break;
				case "w" :
					String wordListFile = (String) entry.getValue();
					gen.setWordList(wordListFile);
					break;
						
					default:
						break;
				}
				}
			return gen.generate();
		} //else
			
	}  //generateArtifact

	static void emitArtifact(String artifact) {
		 
		System.out.println(artifact);
	} //emit artifact

}  // Guard Class
