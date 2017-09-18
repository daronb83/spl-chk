package spell;

import java.io.IOException;

/**
 * A simple main class for running the spelling corrector. This class is not
 * used by the passoff program.
 */
public class Main {

	/**
	 * Give the dictionary file name as the first argument and the word to correct
	 * as the second argument.
	 */
	public static void main(String[] args) throws IOException {
		String dictionaryFileName = null;
		String inputWord = null;

		if (args.length == 2) {
			dictionaryFileName = args[0];
			inputWord = args[1];
		}
		else {
			System.out.println("Usage: java spell.Main dictionaryFileName inputWord");
			System.exit(0);
		}

		SpellCorrector corrector = new SpellCorrector();

		try{
			corrector.useDictionary(dictionaryFileName);
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			System.exit(0);
		}

		String suggestion = corrector.suggestSimilarWord(inputWord);
		if (suggestion == null) {
		    suggestion = "No similar word found";
		}

		System.out.println("Suggestion is: " + suggestion);
	}

}
