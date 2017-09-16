package spell;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Suggests correctly spelled similar words for mispelled words, based on a maxFrequency dictionary
 * @author Daron Barnes
 */
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
         String word = scanner.next();

         if (validateWord(word)) {
           word = word.toLowerCase();
           dictionary.add(word);
         }
       }

       scanner.close();
     }
     catch (FileNotFoundException e){
       e.printStackTrace();
     }
   }

   /**
    * Determines whether a String is a valid word
    * A word is defined as a sequence of one or more alphabetic characters
    *
    * @param word the String to validate
    * @return true if the String is a valid word, false otherwise
    */
   public boolean validateWord(String word) {
     for (int i = 0; i < word.length(); i++) {
       if (!Character.isLetter(word.charAt(i))) {
         return false;
       }
     }
     return true;
   }

  /**
   * Suggest a word from the dictionary that most closely matches
   * <code>inputWord</code>
   * @param inputWord
   * @return The suggestion or null if there is no similar word in the dictionary
   */
  public String suggestSimilarWord(String inputWord){
    inputWord = inputWord.toLowerCase();

    if (dictionary.find(inputWord) != null) {
      return inputWord;
    }

    // Distance 1
    FreqList validWords = new FreqList();
    ArrayList<String> deletions = getDel(inputWord, validWords);
    ArrayList<String> transpositions = getTran(inputWord, validWords);
    ArrayList<String> alterations = getAlt(inputWord, validWords);
    ArrayList<String> insertions = getIns(inputWord, validWords);

    if (validWords.size() > 0) {
      return validWords.getBestWord();
    }
    else {  // Distance 2
      validWords = new FreqList();
      getD2(deletions, 0, validWords);
      getD2(transpositions, 1, validWords);
      getD2(alterations, 2, validWords);
      getD2(insertions, 3, validWords);

      if (validWords.size() > 0) {
        return validWords.getBestWord();
      }
      else {
        return null;
      }
    }
  }

  /**
   *
   *
   */
  private ArrayList<String> getDel(String word, FreqList validWords) {
    ArrayList<String> del = new ArrayList<String>();

    for (int i = 0; i < word.length(); i++) {
      StringBuilder sb = new StringBuilder(word);
      sb.deleteCharAt(i);
      del.add(sb.toString());
    }
    addValidWords(validWords, del);
    return del;
  }

  private ArrayList<String> getTran(String word, FreqList validWords) {
    ArrayList<String> tran = new ArrayList<String>();

    for (int i = 0; i < word.length() - 1; i++) {
      StringBuilder sb = new StringBuilder(word);
      sb.setCharAt(i, word.charAt(i + 1));
      sb.setCharAt(i + 1, word.charAt(i));
      tran.add(word.toString());
    }
    addValidWords(validWords, tran);
    return tran;
  }

  private ArrayList<String> getAlt(String word, FreqList validWords) {
    ArrayList<String> alt = new ArrayList<String>();

    for (int i = 0; i < word.length(); i++) {
      for (int j = 0; j < 26; j++) {
        StringBuilder sb = new StringBuilder(word);
        sb.setCharAt(i, (char)('a' + j));
        alt.add(sb.toString());
      }
    }
    addValidWords(validWords, alt);
    return alt;
  }

  private ArrayList<String> getIns(String word, FreqList validWords) {
    ArrayList<String> ins = new ArrayList<String>();

    for (int i = 0; i < word.length() + 1; i++) {
      for (int j = 0; j < 26; j++) {
        StringBuilder sb = new StringBuilder(word);
        sb.insert(i, ('a' + j));
        ins.add(sb.toString());
      }
    }
    addValidWords(validWords, ins);
    return ins;
  }

  /**
   *
   *
   */
  private void getD2(ArrayList<String> list, int type, FreqList validWords) {

    for (int i = 0; i < list.size(); i++) {
      switch (type) {
        case 0 :
          getDel(list.get(i), validWords);
          break;
        case 1 :
          getTran(list.get(i), validWords);
          break;
        case 2 :
          getAlt(list.get(i), validWords);
          break;
        case 3 :
          getIns(list.get(i), validWords);
          break;
      }
    }
  }

  /**
   *
   *
   */
  private void addValidWords(FreqList validWords, ArrayList<String> strings) {

    for (int i = 0; i < strings.size(); i++) {
      ITrie.INode node = dictionary.find(strings.get(i));

      if (node != null) {
        validWords.add(strings.get(i), node.getValue());
      }
    }
  }


  @Override
  public String toString(){
    return dictionary.toString();
  }
}
