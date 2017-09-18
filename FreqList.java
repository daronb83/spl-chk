package spell;

import java.util.ArrayList;
import java.util.Collections;

public class FreqList {

  ArrayList<String> words;
  int frequency;

  /**
   * A data structure that only stores strings of the highest frequency
   */
  public FreqList() {
    words = new ArrayList<String>();
    frequency = 1;
  }

  /**f
   * Stores a word, but only if its frequency matches or exceeds the current frequency.
   * If the new word's frequency exceeds the current frequency, the current frequency is
   *  set to the new frequency and all previous words are dropped.
   *
   * @param word the word to (potentially) store
   * @param frequency the word's frequency count
   */
  public void add(String word, int frequency) {
    if (this.frequency < frequency) {
      this.frequency = frequency;
      words.clear();
      words.add(word);
    }
    else if (this.frequency == frequency) {
      words.add(word);
    }
  }

  /**
   * @return the first word (alphabetically) at the current frequency
   */
  public String getBestWord() {

    if (words.size() > 0) {
      Collections.sort(words);
      return words.get(0);
    }

    return null;
  }

  /**
   * @return the number of words stored at the current frequency
   */
  public int size() {
    return words.size();
  }

  @Override
  public String toString() {
    return words.toString() + "F:" + frequency;
  }

}
