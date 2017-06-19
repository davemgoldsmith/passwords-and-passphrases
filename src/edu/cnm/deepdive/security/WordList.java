package edu.cnm.deepdive.security;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.io.BufferedReader;
import java.io.IOException;

public class WordList {

	public static final int MIN_WORDS = 5;
	public static final String WORD_LIST_FILE = "resources/eff_large_wordlist.txt";
	private static final String PROPERTIES_FILE = "resources/text.properties";

	private static String usageMessage;
	private static String errorMessage;
	private static String warningMessage;

	public static void main(String[] args) {

		try {
			loadResources();
			int numWords = (args.length > 0) ? Integer.parseInt(args[0]) : MIN_WORDS;
			if (numWords <= 0) {
				throw new IllegalArgumentException(errorMessage);
			} // end if
			else if (numWords < MIN_WORDS) {
				System.out.println(warningMessage);
			} // end else if

			String[] wordList = getWordList(WORD_LIST_FILE);
			System.out.println(Arrays.toString(wordList)); // FIXME - get rid of this debugging

		} // end try
			// generate and emit passphrase

		catch (NumberFormatException ex) {
			ex.printStackTrace();
			System.out.println(errorMessage);
			System.out.printf(usageMessage, WordList.class.getName());
			System.exit(1);
		} catch (IllegalArgumentException ex) {
			System.out.println(errorMessage);
			System.out.printf(usageMessage, WordList.class.getName());
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(1);
		} // end catch
	} // end main method

	private static void loadResources()
			// load resources into usageMessage, errorMessage,
			// warningMessage
			throws IOException {
		Properties properties = new Properties();
		try (InputStream input = WordList.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
			properties.load(input);
			usageMessage = properties.getProperty("usage.message");
			errorMessage = properties.getProperty("error.message");
			warningMessage = properties.getProperty("warning.message");
		} // end try
	} // end loadResources method

	public static String[] getWordList(String listPath) 
	throws IOException {
		try (BufferedReader reader 
				= new BufferedReader (
						new InputStreamReader (
								WordList.class.getClassLoader().getResourceAsStream(listPath)))) {
			ArrayList<String> words = new ArrayList <>();
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				words.add(line.split("\\s+")[1]);
			} // end for
			return words.toArray(new String[]{});
		} //end try
		} // end getWordList method

}// end class
