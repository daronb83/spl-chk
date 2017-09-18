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
   public void useDictionary(String dictionaryFileName) throws IOException {
     dictionary = new Trie();
     File file = new File(dictionaryFileName);
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
    else {
      // Distance 2
      getD2(deletions, validWords);
      getD2(transpositions, validWords);
      getD2(alterations, validWords);
      getD2(insertions, validWords);

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
    addValidWords(del, validWords);
    return del;
  }

  private ArrayList<String> getTran(String word, FreqList validWords) {
    ArrayList<String> tran = new ArrayList<String>();

    for (int i = 0; i < word.length() - 1; i++) {
      StringBuilder sb = new StringBuilder(word);
      sb.setCharAt(i, word.charAt(i + 1));
      sb.setCharAt(i + 1, word.charAt(i));
      tran.add(sb.toString());
    }
    addValidWords(tran, validWords);
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
    addValidWords(alt, validWords);
    return alt;
  }

  private ArrayList<String> getIns(String word, FreqList validWords) {
    ArrayList<String> ins = new ArrayList<String>();

    for (int i = 0; i < word.length() + 1; i++) {
      for (int j = 0; j < 26; j++) {
        StringBuilder sb = new StringBuilder(word);
        sb.insert(i, (char)('a' + j));
        ins.add(sb.toString());
      }
    }
    addValidWords(ins, validWords);
    return ins;
  }

  /**
   *
   *
   */
  private void getD2(ArrayList<String> list, FreqList validWords) {

    for (int i = 0; i < list.size(); i++) {
      getDel(list.get(i), validWords);
      getTran(list.get(i), validWords);
      getAlt(list.get(i), validWords);
      getIns(list.get(i), validWords);
    }
  }

  /**
   *
   *
   */
  private void addValidWords(ArrayList<String> strings, FreqList validWords) {

    for (int i = 0; i < strings.size(); i++) {
      ITrie.INode node = dictionary.find(strings.get(i));

      if (node != null) {
        validWords.add(strings.get(i), node.getValue());
      }
    }
  }


  @Override
  public String toString(){
    return "\n" + dictionary.toString();
  }
}
