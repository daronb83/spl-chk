package spell;
import java.util.ArrayList;

/**
 * Represents a dictionary of words
 * @author Daron Barnes
 */
public class Trie implements ITrie {

  private Node root;

  /**
   * Represents a dictionary of words
   */
  public Trie(){
    root = new Node();
  }

	/**
	 * Adds the specified word to the trie (if necessary) and increments the word's frequency count
	 *
	 * @param word The word being added to the trie
	 */
	public void add(String word) {
    Node temp = root;

    for (int i = 0; i < word.length(); i++) {
      temp = temp.getNode(word.at(i));
    }

    temp.incrementValue();  // the remaining node represents the last letter of the word
  }

	/**
	 * Searches the trie for the specified word
	 *
	 * @param word The word being searched for
	 * @return A reference to the trie node that represents the word,
	 * 			or null if the word is not in the trie
	 */
	public INode find(String word) {
    Node temp = root;

    for (int i = 0; i < word.length(); i++) {
      if (temp.containsKey(word.at(i)){
          temp = temp.getNode(word.at(i));
      }
      else {
        return null;
      }
    }
    return temp;
  }

	/**
	 * Returns the number of unique words in the trie
	 *
	 * @return The number of unique words in the trie
	 */
	public int getWordCount(){
    return root.getWordCount();
  }

  /**
   * Returns the trie's root node
   *
   * @return the trie's root node
   */
	public Node getRoot(){
    return root;
  }

	/**
	 * Returns the number of children in the trie
	 *
	 * @return The number of children in the trie
	 */
	public int getNodeCount(){
    return root.getNodeCount();
  }

	/**
	 * The toString specification is as follows:
	 * For each word, in alphabetical order:
	 * <word>\n
	 */
	@Override
	public String toString(){
    ArrayList<String> words = root.getWords(New ArrayList<String>(), New StringBuilder());
    words.sort();

    StringBuilder sb = new StringBuilder();
    for (String word : words) {
      sb.append(word);
      sp.append("\n");
    }

    return sb.toString();
  }

	@Override
	public int hashCode(){
    return root.hashCode(); // recursive
  }

	@Override
	public boolean equals(Object o){

    if (this == o) {
      return true;
    }

    if (o == null) {
      return false;
    }

    if (this.getClass() != o.getClass()) {
      return false;
    }

    Trie trie = (Trie)o;
    return root.equals(trie.getRoot());  // recursive
  }
}
