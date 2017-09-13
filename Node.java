package spell;
import java.util.ArrayList;

/**
 * Represents a letter of the alphabet in a Trie
 * @author Daron Barnes
 */
public class Node implements ITrie.INode {

  private int value;
  private int size;
  private Node[] children;

  /**
   * Represents a letter of the alphabet in a Trie
   */
  public Node(){
    value = 0;
    size = 0;
    children = new Node[26];
  }

  /**
   * Returns the child node at children[index]
   *
   * @param index an integer from 0-25 where 0 = a, 1 = b ..., 25 = z
   * @return the child node at children[index]
   */
  public Node getChildByIndex(int index) {
    if (index >= 0 && index < children.length) {
      return children[index];
    }
    return null;
  }

  /**
   * Returns a node represented by 'letter.'
   * If the node exists in children[], that node is returned.
   * If the node does not exist, one is created and added to the trie.
   *
   * @param letter the letter representing the node you want to get/add
   * @return a node represented by 'letter'
   */
  public Node getNode(char letter) {
    if (children[getIndex(letter)] == null) {
      Node node = new Node();
      children[getIndex(letter)] = node;
      size++;
      return node;
    }
    else {
      return children[getIndex(letter)];
    }
  }

  /**
   * Determines if 'children' contains a node representing 'letter.'
   *
   * @param letter the letter representing the node you want to check for
   * @return true if the node exists, false otherwise
   */
  public boolean hasNode(char letter) {
    if (children[getIndex(letter)] == null) {
      return false;
    }
    else {
      return true;
    }
  }

  /**
   * returns the array index of 'children' that represents 'letter'
   *
   * @param letter the letter for which an index is needed
   * @return the array index of 'children' that represents 'letter'
   */
  private int getIndex(char letter) {
    switch (letter) {
      case 'a' : return 0;
      case 'b' : return 1;
      case 'c' : return 2;
      case 'd' : return 3;
      case 'e' : return 4;
      case 'f' : return 5;
      case 'g' : return 6;
      case 'h' : return 7;
      case 'i' : return 8;
      case 'j' : return 9;
      case 'k' : return 10;
      case 'l' : return 11;
      case 'm' : return 12;
      case 'n' : return 13;
      case 'o' : return 14;
      case 'p' : return 15;
      case 'q' : return 16;
      case 'r' : return 17;
      case 's' : return 18;
      case 't' : return 19;
      case 'u' : return 20;
      case 'v' : return 21;
      case 'w' : return 22;
      case 'x' : return 23;
      case 'y' : return 24;
      case 'z' : return 25;
      default : return -1;
    }
  }

  /**
   * returns the letter represented by an array index
   *
   * @param index the index for which a letter is needed
   * @return the letter represented by the array index
   */
  private char getLetter(int index) {
    switch (index) {
      case 0 : return 'a';
      case 1 : return 'b';
      case 2 : return 'c';
      case 3 : return 'd';
      case 4 : return 'e';
      case 5 : return 'f';
      case 6 : return 'g';
      case 7 : return 'h';
      case 8 : return 'i';
      case 9 : return 'j';
      case 10 : return 'k';
      case 11 : return 'l';
      case 12 : return 'm';
      case 13 : return 'n';
      case 14 : return 'o';
      case 15 : return 'p';
      case 16 : return 'q';
      case 17 : return 'r';
      case 18 : return 's';
      case 19 : return 't';
      case 20 : return 'u';
      case 21 : return 'v';
      case 22 : return 'w';
      case 23 : return 'x';
      case 24 : return 'y';
      case 25 : return 'z';
      default : return '0';
    }
  }

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
   * Returns the number of initialized children nodes within 1 level of this node
   *
   * @return the number of initialized children nodes within 1 level of this node
   */
  public int size() {
    return size;
  }

  /**
   * Returns the number of unique words in the sub-trie (recursive)
   *
   * @return The number of unique words in the sub-trie
   */
  public int getWordCount() {
    int count = (value > 0) ? 1 : 0; // count yourself

    for (int i = 0; i < children.length; i++) {
      if (children[i] != null) {
        count += children[i].getWordCount(); // add count of all children
      }
    }

    return count;
  }

  /**
   * Returns a count of this node's children (recursive)
   *
   * @return a count of this node's children
   */
  public int getNodeCount() {
    int count = 1; // count yourself

    for (int i = 0; i < children.length; i++) {
      if (children[i] != null) {
        count += children[i].getNodeCount(); // add count of all children
      }
    }

    return count;
  }

  /**
   * Appends all words in this sub-trie to 'wordList' (recursive)
   *
   * @param wordList the ArrayList to which all words will be added
   * @param sb a StringBuilder used to build words out of Node letters
   * @return 'wordList' with all words from this sub-trie appended
   */
  public ArrayList<String> getWords(ArrayList<String> wordList, StringBuilder sb) {

    if (value > 0){  // if any words ended on this letter
      wordList.add(sb.toString());
    }

    for (int i = 0; i < children.length; i++) {
      if (children[i] != null) {
        sb.append(getLetter(i));  // pre-appending the child's letter to sb
        children[i].getWords(wordList, sb);
        sb.setLength(sb.length() - 1); // pop this node's key so sb can be reused by the parent
      }
    }

    return wordList;
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

    Node node = (Node)o;

    if (size != node.size() || value != node.getValue()){
      return false;
    }

    for (int i = 0; i < children.length; i++) {
      if (children[i] == null) {
        if (node.getChildByIndex(i) != null) {
          return false;
        }
      }
      else if (!children[i].equals(node.getChildByIndex(i))) {
        return false;
      }
    }

    return true; // must be true if we got this far
  }

  @Override
  public int hashCode() {
    int hash = (size + value) * 3;

    for (int i = 0; i < children.length; i++) {
      if (children[i] != null) {
        hash += i * 5;
        hash += children[i].hashCode();
      }
    }

    return hash;
  }
}
