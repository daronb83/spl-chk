package spell;
import java.util.*;

/**
 * Your trie class should implement the ITrie interface
 */
public class Trie implements ITrie {

  Node root;

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

    temp.incrementValue();
  }

	/**
	 * Searches the trie for the specified word
	 *
	 * @param word The word being searched for
	 *
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
    return (root.getNodeCount() * 13) + (root.getWordCount()) * 17);
  }

	@Override
	public boolean equals(Object o){
    if (this.toString().equals(o.toString()){
      return true;
    }
    return false;
  }
}
// ------------------------------------------------------------------------------------- //


/**
 * Node: Represents a letter of the alphabet in a Trie
 */
public class Node implements ITrie.INode {

  int value;
  TreeMap<String, Node> children;

  public Node(){
    value = 0;
    children = new TreeMap<String, Node>();
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
   * Checks if the node contains a particular letter
   *
   * @param key the letter you are checking for
   * @return Whether the letter already exists
   */
  public boolean containsKey(String key) {
    return children.containsKey(key);
  }

  /**
   * Returns a node representing the key letter. If the node does not
   * exist, one is created and added to the trie.
   *
   * @param key the key of the node you want to add
   * @return the node that was added
   */
  public Node getNode(String key) {
    if (children.containsKey(key)) {
      return children.get(key);
    }
    else {
      Node node = New Node();
      children.put(key, node);
      return node;
    }
  }

  /**
   * Returns the number of unique words in the sub-trie (recurrsive)
   *
   * @return The number of unique words in the sub-trie
   */
  public int getWordCount() {
    int count;
    (value > 0) ? count = 1 : count = 0;

    for (Map.Entry<String,Node> entry : treeMap.entrySet()) {
      count += entry.getValue().getWordCount();
    }

    return count;
  }

  /**
   * Returns a count of this node's children (recurrsive)
   *
   * @return a count of this node's children
   */
  public int getNodeCount() {
    int count = 1; // count yourself

    for (Map.Entry<String,Node> entry : treeMap.entrySet()) {
      count += entry.getValue().getNodeCount();
    }

    return count;
  }

  /**
   * Adds all words in this sub-trie to the "words" ArrayList (recurrsive)
   *
   * @param words the ArrayList to which all words will be added
   * @param sb a StringBuilder used to create words from keys
   * @return the given ArrayList after adding all words in this sub-trie
   */
  public ArrayList<String> getWords(ArrayList<String> words, StringBuilder sb) {

    if (value > 0){
      words.add(sb.toString());
    }

    for (Map.Entry<String,Node> entry : treeMap.entrySet()) {
      sb.append(entry.getKey());
      entry.getValue().getWords(words, sb);
      sb.setLength(sb.length() - 1); // pop this node's key so sb can be reused by the parent
    }

    return words;
  }
}


    /* Iterating TreeMap example
    for (Map.Entry<String,Node> entry : treeMap.entrySet()) {
      Node node = entry.getValue();
      String key = entry.getKey();
    }
    /*
