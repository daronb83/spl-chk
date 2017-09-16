package spell;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a dictionary of words
 * @author Daron Barnes
 */
public class Trie implements ITrie {

  private Node root;
  private int wordCount;
  private int nodeCount;

  /**
   * Represents a dictionary of words
   */
  public Trie() {
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
      int index = indexByLetter(word.charAt(i));

      if (temp.hasNode(index)) {
        temp = temp.getNode(index);
      }
      else {
        temp = temp.addNode(index);
        nodeCount++;
      }
    }

    temp.incrementValue();  // the remaining node represents the last letter of the word
    wordCount++;
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
      int index = indexByLetter(word.charAt(i));

      if (temp.hasNode(index)){
          temp = temp.getNode(index);
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
  public int getWordCount() {
    return wordCount;
  }

	/**
	 * Returns the number of children in the trie
	 *
	 * @return The number of children in the trie
	 */
	public int getNodeCount() {
    return nodeCount;
  }

  /**
   * returns the array index that represents a letter
   *
   * @param letter the letter for which an index is needed
   * @return the array index of 'children' that represents 'letter'
   */
  private int indexByLetter(char letter) {
    int output = letter - 'a';
    return output;
  }

  /**
   * returns the letter represented by an array index
   *
   * @param index the index for which a letter is needed
   * @return the letter represented by the array index
   */
  private char letterByIndex(int index) {
    char output = (char)('a' + index);
    return output;
  }

  /**
   * Returns the trie's root node
   *
   * @return the trie's root node
   */
  public Node getRoot() {
    return root;
  }

	/**
	 * The toString specification is as follows:
	 * For each word, in alphabetical order:
	 * <word>\n
	 */
	@Override
	public String toString() {
    return toStringHelper(root, new StringBuilder(), new StringBuilder());  // recursive
  }

  /**
   * Recursive helper for toString()
   */
  private String toStringHelper(Node node, StringBuilder current, StringBuilder output) {

    if (node.getValue() > 0) {
      output.append(current);
      output.append("\n");
    }

    for (int i = 0; i < 26; i++) {
      if (node.hasNode(i)) {
        current.append(letterByIndex(i));
        toStringHelper(node.getNode(i), current, output);
        current.setLength(current.length() - 1); // removes the last letter
      }
    }

    return output.toString();
  }


	@Override
	public int hashCode() {
    return wordCount * 3 + nodeCount * 5;
  }


	@Override
	public boolean equals(Object o) {

    if (this == o) {
      return true;
    }

    if (o == null) {
      return false;
    }

    if (this.getClass() != o.getClass()) {
      return false;
    }

    Trie other = (Trie)o;

    if (nodeCount != other.getNodeCount() || wordCount != other.getWordCount()) {
      return false;
    }

    return equalsHelper(root, other.getRoot());  // recursive
  }

  /**
   * Recursive helper for equals(Object o).
   */
  private boolean equalsHelper(Node mine, Node other) {

    // Check null parity
    if (mine == null) {
      return (other == null);
    }
    else if (other == null) {
      return false;
    }

    // Check value parity
    if (mine.getValue() != other.getValue()) {
      return false;
    }

    // Check children parity
    for (int i = 0; i < 26; i++) {
      if (!equalsHelper(mine.getNode(i), other.getNode(i))) {
        return false;
      }
    }

    return true;
  }
}
