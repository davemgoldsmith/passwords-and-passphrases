/**
 * Guard.java
 */
package edu.cnm.deepdive.security;

import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * This is a program to generate random passwords and passphrases.
 * Generation uses a cryptographically secure random number generator
 * to select words from a list, or characters from a pool.
 * 
 * 
 * @author dave goldsmith
 */
public class Guard {

	/**
	 * Pass command line arguments to a parser from the Apache Commons CLI library,
	 * then instantiate and invoke the appropriate classes and methods to 
	 * generate the requested artifact.
	 * 
	 * @param args    Command line arguments specifying generation options.
	 */
	public static void main(String[] args) {
	/** get options
	 *  generate artifact
	 *  emit artifact
	 */ 
	Option[] options = getOptions(args);
	String artifact = generateArtifact(options);
	emitArtifact(artifact);
		
	}
	
	static Option[] getOptions(String[] args) {
			
		try {
			Option lengthOption = Option.builder("L").argName("length")
													 .hasArg()
													 .longOpt("length")
													 .numberOfArgs(1)
													 .build();
			Option delimiterOption = Option.builder("d").argName("delimiter")
														.hasArg()
														.longOpt("delimeter")
														.numberOfArgs(1)
														.optionalArg(true)
														.build();
			Option wordListOption = Option.builder("w").argName("path-to-list-file")
														.hasArg()
														.longOpt("word-list")
														.numberOfArgs(1)
														.build();
			Options opts = new Options().addOption(lengthOption)
										.addOption(delimiterOption)
										.addOption(wordListOption);

			DefaultParser parser = new DefaultParser();
			return parser.parse(opts, args).getOptions();
			
		} catch (ParseException ex) {
			//TODO handle this exception with a usage display.
			return null;
		}
	}
	
	static String generateArtifact(Option[] options) {
		return null;  // FIXME
	}

	static void emitArtifact (String Artifact) {
		
	}
}
