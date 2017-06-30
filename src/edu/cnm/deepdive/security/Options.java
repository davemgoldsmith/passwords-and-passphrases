/**
 * 
 */
package edu.cnm.deepdive.security;

import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;

/**
 * @author davem
 *
 */

//TODO add constants regarding warnings and errors.
// add exception handling.
// add constants in conflict warnings from message properties
// add constants in options properties 

public class Options {
	
	public static final String JAR_FILE_NAME = "guard.jar";
	
	private static final String FATAL_MESSAGE = "not able to load messages bundle.";
	
	private static final String MISSING_ARGUMENT_KEY = "error.missingargument.message";
	
	 private static final String OPTIONS_DESCRIPTION_BUNDLE = "resources/options";
	 private static final String MESSAGES_BUNDLE = "resources/messages";
	 private static final String HELP_OPTION_KEY = "help.option";
	 private static final String LENGTH_OPTION_KEY = "length.option";
	 private static final String DELIMITER_OPTION_KEY= "delimiter.option";
	 private static final String LIST_OPTION_KEY	 ="word-list.option"; 
	 private static final String MODE_OPTION_KEY ="mode.option";
	 private static final String EXCLUDE_UPPER_OPTION_KEY="upper.option";
	 private static final String EXCLUDE_LOWER_OPTION_KEY="lower.option";
	 private static final String EXCLUDE_DIGITS_OPTION_KEY = "digits.option"; 
	 private static final String EXCLUDE_PUNCTUATION_OPTION_KEY = "punctuation.option";
	 private static final String EXCLUDE_AMBIGUOUS_OPTION_KEY= "ambiguous.option";
	 
	 private static String usageMessage = "java -jar %s [options]";
	
	static HashMap<String, Object> getOptions(String[] args) {
	ResourceBundle messageBundle = null;
	
	try {
		messageBundle = ResourceBundle.getBundle(MESSAGES_BUNDLE);
	}catch (MissingResourceException ex){
			System.out.println(FATAL_MESSAGE);
			return null;
		
	}
	
	org.apache.commons.cli.Options opts = null;
	
		try {
			
			ResourceBundle bundle = ResourceBundle.getBundle(OPTIONS_DESCRIPTION_BUNDLE);
			
			Option helpOption = Option.builder("?").longOpt("help")					
													.hasArg(false)
													.desc(bundle.getString(HELP_OPTION_KEY))
													.build();
			
			Option lengthOption = Option.builder("L").argName("length")
													 .hasArg()
													 .longOpt("length")
													 .numberOfArgs(1)
													 .optionalArg(true)
													 .type(Number.class)
													 .desc(bundle.getString(LENGTH_OPTION_KEY))
													 .build();
			Option delimiterOption = Option.builder("d").argName("delimiter")
														.hasArg()
														.longOpt("delimiter")
														.numberOfArgs(1)
														.optionalArg(true)
														.type(String.class)
														.desc(bundle.getString(DELIMITER_OPTION_KEY))
														.build();
			Option wordListOption = Option.builder("w").argName("path-to-list-file")
														.hasArg()
														.longOpt("word-list")
														.numberOfArgs(1)
														.type(String.class)
														.desc(bundle.getString(LIST_OPTION_KEY))
														.build();
			
			Option excludeUpperOption = Option.builder("b").longOpt("exclude-upper")
														.hasArg(false)
														.desc(bundle.getString(EXCLUDE_UPPER_OPTION_KEY))
														.build();
			Option excludeLowerOption = Option.builder("s").longOpt("exclude-lower")
														.hasArg(false)
														.desc(bundle.getString(EXCLUDE_LOWER_OPTION_KEY))
														.build();
			Option excludeDigitsOption = Option.builder("n").longOpt("exclude-digits")
														.hasArg(false)	
														.desc(bundle.getString(EXCLUDE_DIGITS_OPTION_KEY))
														.build();
			Option excludePunctuationOption = Option.builder("p").longOpt("exclude-punctuation")
														.hasArg(false)
														.desc(bundle.getString(EXCLUDE_PUNCTUATION_OPTION_KEY))
														.build();
			Option includeAmbiguousOption = Option.builder("a").longOpt("include-ambiguous")
														.hasArg(false)
														.desc(bundle.getString(EXCLUDE_AMBIGUOUS_OPTION_KEY))
														.build();
			Option modeOption = Option.builder("m").longOpt("password-mode")
														.hasArg(false)
														.desc(bundle.getString(MODE_OPTION_KEY))
														.build();
			
			
			opts = new org.apache.commons.cli.Options().addOption(lengthOption)
														.addOption(helpOption)  
														.addOption(delimiterOption)
														.addOption(wordListOption)
														.addOption(excludeUpperOption)
														.addOption(excludeLowerOption)
														.addOption(excludeDigitsOption)
														.addOption(excludePunctuationOption)
														.addOption(includeAmbiguousOption)
														.addOption(modeOption);

			DefaultParser parser = new DefaultParser();
			HashMap<String, Object> map = new HashMap<>();
			CommandLine cmdLine = parser.parse(opts,  args);
			
			for (Option option : cmdLine.getOptions()) {
				String opt = option.getOpt();
				map.put(opt, cmdLine.getParsedOptionValue(opt));
				//TODO perform additional validation on option values, including checking for extreme values.
				//TODO check for option conflicts.		
				
				
				
			}
			if (cmdLine.hasOption('?')) {										
				display (null, usageMessage, opts);
			}
			return map;
			
		
		} catch (MissingArgumentException ex)	{
			Option missing = ex.getOption();
			String optName = missing.getOpt();
			String message = messageBundle.getString(MISSING_ARGUMENT_KEY);
			message = String.format(message,  optName);
			display (message, usageMessage, opts);
			
			return null;
		} catch (UnrecognizedOptionException ex)	{
			// TODO Display error and usage.
			return null;
		}catch (ParseException ex) {
			//TODO display error and usage.
			return null;
		} catch (MissingResourceException ex) {
			//TODO Display error message like "incomplete installation"
			return null;
		}
		
	}
		private static void display(String message, String usage, org.apache.commons.cli.Options opts) {
			if (message != null) {
				System.out.println(message);
			}
			new HelpFormatter().printHelp(usage, opts);
	}
	

}
