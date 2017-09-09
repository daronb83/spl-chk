package spell;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class SpellCorrector implements ISpellCorrector{

  Trie dictionary = null;

  /**
   * Tells this <code>SpellCorrector</code> to use the given file as its dictionary
   * for generating suggestions.
   * @param dictionaryFileName File containing the words to be used
   * @throws IOException If the file cannot be read
   */
   public void useDictionary(String dictionaryFileName) throws IOException{
     File file = new File(dictionaryFileName);
     dictionary = new Trie();

     try {
       Scanner scanner = new Scanner(file);

       while (scanner.hasNext()) {
         String word = Scanner.next().toLowerCase();
         dictionary.add(scanner.next());
       }

       scanner.close();
     }
     catch (FileNotFoundException e){
       e.printStackTrace();
     }
   }

  /**
   * Suggest a word from the dictionary that most closely matches
   * <code>inputWord</code>
   * @param inputWord
   * @return The suggestion or null if there is no similar word in the dictionary
   */
  public String suggestSimilarWord(String inputWord){
    return dictionary.toString();
  }

}
