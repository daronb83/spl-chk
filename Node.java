package spell;

/**
 * Represents a letter of the alphabet in a Trie
 */
public class Node implements ITrie.INode {

  private int value;
  private Node[] letters;


  /**
   * Returns the frequency count for the word represented by the node
   *
   * @return The frequency count for the word represented by the node
   */
  public int getValue() {
    return value;
  }


  /**
   * Increments the frequency count for the word represented by the node
   */
  public void incrementValue() {
    value++;
  }

  /**
   * Determines whether the child node at 'index' has been initialized
   *
   * @param index the array index (0-25) reprenting the desired letter
   * @return the child node at 'index'
   */
  public boolean hasNode(int index) {

    if (letters == null) {
      return false;
    }

    if (index >= 0 && index < letters.length) {
      if (letters[index] != null) {
        return true;
      }
    }

    return false;
  }


  /**
   * Adds a new child node at 'index'
   *
   * @param index the array index (0-25) reprenting the desired letter
   * @return the new node at 'index'
   */
  public Node addNode(int index) {

    if (letters == null) {
      letters = new Node[26];
    }

    if (index >= 0 && index < letters.length) {
      Node node = new Node();
      letters[index] = node;
      return node;
    }

    return null;
  }


  /**
   * Returns the child node at 'index', which may be null
   *
   * @param index the array index (0-25) reprenting the desired letter
   * @return the child node at 'index'
   */
  public Node getNode(int index) {

    if (letters == null) {
      return null;
    }

    if (index >= 0 && index < letters.length) {
      return letters[index];
    }

    return null;
  }

}
