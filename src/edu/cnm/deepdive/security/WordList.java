package edu.cnm.deepdive.security;

import java.io.InputStream;
import java.util.Properties;

import java.io.IOException;

public class WordList {

	public static final int MIN_WORDS = 5;

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
		} // end try
			// TODO generate and emit passphrase

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

		}
	}

	private static void loadResources()
			// TODO load resources into usageMEssage, errorMessage,
			// warningMessage
			throws IOException {
		Properties properties = new Properties();
		try (InputStream input = WordList.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
			properties.load(input);
			usageMessage = properties.getProperty("usage.message");
			errorMessage = properties.getProperty("error.message");
			warningMessage = properties.getProperty("warning.message");

		} // end throws

	}

} // end class
