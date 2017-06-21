package edu.cnm.deepdive.security;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.io.BufferedReader;
import java.io.IOException;
/**
 * Passphrase program which allows the user to input, in the command line,
 * the number of random words that will be utilized to create the phrase.
 * @author davem
 *
 */

public class WordList {
	/** The default value used for the phrase length, if none is specified.*/	
	public static final int MIN_WORDS = 5;
	/** The word list that is imported and assigned to a string. */
	public static final String WORD_LIST_FILE = "resources/eff_large_wordlist.txt";
	
	private static final String PROPERTIES_FILE = "resources/text.properties";

	private static String usageMessage;
	private static String errorMessage;
	private static String warningMessage;

	/**
	 * Main method of program which loads resources, displays errors if incorrect parameter/argument 
	 * is given.
	 * @param args Parameter that is entered by the user to indicate the desired number of passphrase words.
	 */
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
			String[] selectedWords = getRandomWords(numWords, wordList);
			System.out.println(getJoinedString(selectedWords));

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

	/**
	 * Method that gets the word list and utilizes random number generator to select various words.
	 * @param listPath Classpath - relative to word list file.
	 * @return Returns words to the string of words to be used as the passphrase.
	 * @throws IOException Exception if there is not a wordlist to be imported.
	 */
	
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
	
	/**
	 * Method that gets random words from the word list and adds the number of words specified
	 * by the user, or the minimum number that was specified as a constant.
	 * @param numWords The number of words that the user has specified in the creation of the passphrase.
	 * @param wordList  The wordlist that is read.
	 * @return Returns each randomized word - to be combined in getJoinedString.
	 */
	
	public static String[] getRandomWords(int numWords, String[] wordList) {
		String[] selection = new String[numWords];
		Random rng = new Random();
		for (int i = 0; i < selection.length; i++) {
			int selectedPosition = rng.nextInt(wordList.length);
			selection[i] = wordList[selectedPosition];
		}
	return selection;
	} // end getRandomWords method
	
	private static String getJoinedString(String[] source) {
		StringBuilder builder = new StringBuilder();
		for (String item : source) {
			builder.append(item);
			builder.append(" ");
		}
		return builder.toString().trim();
	}

}// end class
