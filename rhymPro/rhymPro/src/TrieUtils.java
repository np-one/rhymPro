import java.util.ArrayList;

public class TrieUtils {
    private TrieNode root;

    public TrieUtils() {
        root = new TrieNode();
    }

    /**
     * Insert String word into trie
     * @param word
     */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = word.length() - 1; i >= 0; i--) {
            char c = word.charAt(i);

            int index = c - 'a';
            if (Character.isDigit(c)) {
                index = c - 21;
            }

            if (node.arr[index] == null) {
                TrieNode temp = new TrieNode();
                node.arr[index] = temp;
                node = temp;
            } else {
                node = node.arr[index];
            }
        }
        node.isEnd = true;
    }


    /**
     * Function to tell if any word starts with a prefix
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix) {
        TrieNode node = searchNode(prefix);
        if (node == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * returns the end node of a word if present
     * @param word
     * @return
     */
    public TrieNode searchNode(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (Character.isDigit(c)) {
                index = c - 21;
            }
            if (node.arr[index] != null) {
                node = node.arr[index];
            } else {
                return null;
            }
        }

        if (node == root)
            return null;

        return node;
    }

    /**
     * returns list of words having a prefix
     * @param prefix
     * @return
     */
    public ArrayList<String> getAllWords(String prefix) {
        TrieNode p = searchNode(prefix);
        return printWords(p, new String(""), new ArrayList<String>(), prefix);
    }

    public ArrayList<String> printWords(TrieNode node, String last, ArrayList<String> res, String s) {
        if (node == null) {
            return null;
        }
        for (int i = 0; i < node.arr.length; i++) {
            TrieNode node1;
            if (node.arr[i] == null)
                continue;
            node1 = node;
            while (node1.arr[i] != null) {
                node1 = node1.arr[i];
                printWords(node1, last + String.valueOf((char) (i + 97)), res, s);
            }
            if (node1.isEnd) {
                String word = new StringBuilder(last + String.valueOf((char) (i + 97))).reverse().toString();
                String suffix = new StringBuilder(s).reverse().toString();
                res.add(word + suffix);
//                System.out.print(last+String.valueOf((char)(i + 97)));
//                System.out.println();
            }
        }
        return res;
    }
}