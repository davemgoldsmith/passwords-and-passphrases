/**
 * Guard.java
 */
package edu.cnm.deepdive.security;

import java.util.HashMap;

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
		String artifact = generateArtifact(map);
		emitArtifact(artifact);
	}  // main

	static String generateArtifact(HashMap<String, Object> map) {
		if (map.containsKey("m")) {
			PasswordGenerator gen = new SecurePasswordGenerator();
			// TODO set fields for all specified options.
		}   //if
		return null; // FIXME
	}  //generateArtifact

	static void emitArtifact(String artifact) {
		// TODO make this smarter
		System.out.println(artifact);
	} //emit artifact

}  // Guard Class
