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
   * Stores a word, but only if it beats or matches the current frequency.
   * If the new word's frequency is higher than the current frequency, the current frequency is
   *  increased and all previously stored words are removed.
   *
   * @param word the word to store
   * @param frequency the frequency count of 'word'
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
    Collections.sort(words);
    return words.get(0);
  }

  public int size() {
    return words.size();
  }

}
