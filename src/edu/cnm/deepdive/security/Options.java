/**
 * 
 */
package edu.cnm.deepdive.security;

import java.util.HashMap;
import java.util.ResourceBundle;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

/**
 * @author davem
 *
 */

//TODO add constants regarding warnings and errors.
// add exception handling.
// add constants in conflict warnings from message properties
// add constants in options properties 

public class Options {
	
	 private static final String OPTIONS_DESCRIPTION_BUNDLE = "resources/options";
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
	
	static HashMap<String, Object> getOptions(String[] args) {
	//TODO add options 
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
			
			
			org.apache.commons.cli.Options opts 
				= new org.apache.commons.cli.Options().addOption(lengthOption)
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
			if (cmdLine.hasOption('?')) {										
				new HelpFormatter().printHelp("These are the options", opts);  //FIXME
			}
			for (Option option : cmdLine.getOptions()) {
				String opt = option.getOpt();
				map.put(opt, cmdLine.getParsedOptionValue(opt));
			}
			return map;
			
			
		} catch (ParseException ex) {
			//TODO handle this exception with a usage display.
			// add errors and warnings regarding usage warnings 
			// as well as error messages that state what will not work 
			//properly
			return null;
		}
	}
	

}
