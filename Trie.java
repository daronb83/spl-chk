package spell;

/**
 * Your trie class should implement the ITrie interface
 */
public class Trie implements ITrie {

  Node root = null;

	/**
	 * Adds the specified word to the trie (if necessary) and increments the word's frequency count
	 *
	 * @param word The word being added to the trie
	 */
	public void add(String word) {
    if (root == null){
      root = new Node("0");
    }
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
    return null;
  }

	/**
	 * Returns the number of unique words in the trie
	 *
	 * @return The number of unique words in the trie
	 */
	public int getWordCount(){
    return 0;
  }

	/**
	 * Returns the number of nodes in the trie
	 *
	 * @return The number of nodes in the trie
	 */
	public int getNodeCount(){
    return 0;
  }

	/**
	 * The toString specification is as follows:
	 * For each word, in alphabetical order:
	 * <word>\n
	 */
	@Override
	public String toString(){
    return "Need to implement toString()";
  }

	@Override
	public int hashCode(){
    return 0;
  }

	@Override
	public boolean equals(Object o){
    return false;
  }

	/**
	 * Your trie node class should implement the ITrie.INode interface
	 */
	public class Node implements ITrie.INode {

    char key;
    int value;
    ArrayList<Node> nodes;

    public Node(char key) {
      this.key = key;
      value = 0;
      nodes = new ArrayList<Node>();
    }

		/**
		 * Returns the frequency count for the word represented by the node
		 *
		 * @return The frequency count for the word represented by the node
		 */
		public int getValue(){
      return value;
    }

    /**
		 * Increments the frequency count for the word represented by the node
		 */
    public void incrementValue() {
      value++;
    }
	}

	/*
	 * EXAMPLE:
	 *
	 * public class Words implements ITrie {
	 *
	 * 		public void add(String word) { ... }
	 *
	 * 		public ITrie.INode find(String word) { ... }
	 *
	 * 		public int getWordCount() { ... }
	 *
	 * 		public int getNodeCount() { ... }
	 *
	 *		@Override
	 *		public String toString() { ... }
	 *
	 *		@Override
	 *		public int hashCode() { ... }
	 *
	 *		@Override
	 *		public boolean equals(Object o) { ... }
	 *
	 * }
	 *
	 * public class WordNode implements ITrie.INode {
	 *
	 * 		public int getValue() { ... }
	 * }
	 *
	 */
}
