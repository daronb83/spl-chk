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
       dictionary.add(word);
       }

     scanner.close();
   }

  /**
   * Suggest a word from the dictionary that most closely matches
   * <code>inputWord</code>
   * @param inputWord
   * @return The suggestion or null if there is no similar word in the dictionary
   */
  public String suggestSimilarWord(String inputWord){

    if (dictionary.find(inputWord) != null) {
      return inputWord.toLowerCase();
    }

    ArrayList<String> possibleWords = getD1(inputWord);  // Distance 1
    FreqList validWords = new FreqList();
    addValidWords(possibleWords, validWords);

    if (validWords.size() > 0) {
      return validWords.getBestWord();
    }
    else {
      for (String word : possibleWords) {  // Distance 2
        addValidWords(getD1(word), validWords);
      }
      return validWords.getBestWord();
    }
  }

  /**
   *  Returns an array of all strings distance 1 away from the input string
   *
   *  @param word the base string used to generate all d1 words
   *  @return an array of all strings distance 1 away from the input string
   */
  private ArrayList<String> getD1(String word) {
    ArrayList<String> possibleWords = new ArrayList<String>();

    for (int i = 0; i < word.length(); i++) {
      StringBuilder sb = new StringBuilder(word);
      sb.deleteCharAt(i);
      possibleWords.add(sb.toString());
    }

    for (int i = 0; i < word.length() - 1; i++) {
      StringBuilder sb = new StringBuilder(word);
      sb.setCharAt(i, word.charAt(i + 1));
      sb.setCharAt(i + 1, word.charAt(i));
      possibleWords.add(sb.toString());
    }

    for (int i = 0; i < word.length(); i++) {
      for (int j = 0; j < 26; j++) {
        StringBuilder sb = new StringBuilder(word);
        sb.setCharAt(i, (char)('a' + j));
        possibleWords.add(sb.toString());
      }
    }

    for (int i = 0; i < word.length() + 1; i++) {
      for (int j = 0; j < 26; j++) {
        StringBuilder sb = new StringBuilder(word);
        sb.insert(i, (char)('a' + j));
        possibleWords.add(sb.toString());
      }
    }

    return possibleWords;
  }

  /**
   *  Finds all words in a list of Strings that have valid entries in the dictionary, and adds them
   *   to a FreqList
   *
   *  @param possibleWords an array of strings to test for validity
   *  @param validWords a Freqlist, to which all valid words will be added
   */
  private void addValidWords(ArrayList<String> possibleWords, FreqList validWords) {

    for (String word : possibleWords){
      ITrie.INode node = dictionary.find(word);

      if (node != null) {
        validWords.add(word, node.getValue());
      }
    }
  }


  @Override
  public String toString(){
    return "\n" + dictionary.toString();
  }
}
