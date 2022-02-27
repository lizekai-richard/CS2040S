import java.util.ArrayList;

public class Trie {

    // Wildcards
    final char WILDCARD = '.';
    TrieNode root;

    private class TrieNode {
        // TODO: Create your TrieNode class here.
        TrieNode[] children = new TrieNode[62];
        char c;
        boolean terminate;
        /**
         *
         * @param c the c stored in this TrieNode, let '#' be the special start c
         *
         */
        public TrieNode(char c) {
            this.c = c;
            this.terminate = false;
            for(int i = 0; i < children.length; ++i) {
                children[i] = null;
            }
        }

    }

    public Trie() {
        // TODO: Initialise a trie class here.
        root = new TrieNode('#');
    }

    public int ASCIItoIndex(int c) {
        if(c >= 48 && c <= 57) {
            return c - 48;
        } else if(c >= 65 && c <= 90) {
            return c - 55;
        } else {
            return c - 61;
        }
    }

    /**
     * Inserts string s into the Trie.
     *
     * @param s string to insert into the Trie
     */
    void insert(String s) {
        // TODO
        TrieNode node = root;
        for(int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            int charToIndex = ASCIItoIndex(c);
            if(node.children[charToIndex] == null) {
                node.children[charToIndex] = new TrieNode(c);
            }
            node = node.children[charToIndex];
        }
        node.terminate = true;
    }

    /**
     * Checks whether string s exists inside the Trie or not.
     *
     * @param s string to check for
     * @return whether string s is inside the Trie
     */
    public boolean containsHelper(String s, TrieNode node) {
        if(s.isEmpty()) {
            return node.terminate;
        } else {
            char c = s.charAt(0);
            int charToIndex = ASCIItoIndex(c);
            if(node.children[charToIndex] != null) {
                return containsHelper(s.substring(1), node.children[charToIndex]);
            } else {
                return false;
            }
        }
    }
    boolean contains(String s) {
        // TODO
        if(root == null) {
            return false;
        } else {
            return containsHelper(s, root);
        }
    }

    /**
     * Searches for strings with prefix matching the specified pattern sorted by lexicographical order. This inserts the
     * results into the specified ArrayList. Only returns at most the first limit results.
     *
     * @param s       pattern to match prefixes with
     * @param results array to add the results into
     * @param limit   max number of strings to add into results
     */

    void prefixSearch(String s, ArrayList<String> results, int limit) {
        // TODO
        TrieNode node = root;
        StringBuilder str = new StringBuilder("");
        // String str = "";
        if (s.contains(".")) {
            prefixSearchHelper(s, node, str, results, limit);
        } else {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int idx = ASCIItoIndex(c);
                if (node.children[idx] != null) {
                    // str += c;
                    str.append(c);
                    node = node.children[idx];
                } else {
                    return;
                }
            }
            getRestCharacters(node, str, results, limit);
        }
    }

    void prefixSearchHelper(String s, TrieNode node, StringBuilder str, ArrayList<String> results, int limit) {
        if (s.equals("")) {
            getRestCharacters(node, str, results, limit);
        } else {
            char c = s.charAt(0);
            if (c == WILDCARD) {
                int len = str.length();
                for (int i=0; i < node.children.length; i++) {
                    if (node.children[i] != null) {
                        char letter = node.children[i].c;
                        // str += letter;
                        str.append(letter);
                        // System.out.println("1" + str);
                        prefixSearchHelper(s.substring(1), node.children[i], str, results, limit);
                        // str = str.substring(0, str.length() - 1);
                        str.delete(len, str.length());
                        // System.out.println("2" + str);
                    }
                }
            } else {
                int idx = ASCIItoIndex(c);
                if (node.children[idx] != null) {
                    // str += c;
                    str.append(c);
                    // System.out.println("3" + str);
                    prefixSearchHelper(s.substring(1), node.children[idx], str, results, limit);
                }
            }
        }
    }

    void getRestCharacters(TrieNode node, StringBuilder str, ArrayList<String> results, int limit) {
        if (node.terminate) {
            if(results.size() < limit) {
                results.add(str.toString());
            }
        }
        for (int i=0; i < node.children.length; i++) {
            if (node.children[i] != null) {
                // str += node.children[i].c;
                str.append(node.children[i].c);
                getRestCharacters(node.children[i], str, results, limit);
                // str = str.substring(0, str.length() - 1);
                str.deleteCharAt(str.length() - 1);
            }
        }
    }



    // Simplifies function call by initializing an empty array to store the results.
    // PLEASE DO NOT CHANGE the implementation for this function as it will be used
    // to run the test cases.
    String[] prefixSearch(String s, int limit) {
        ArrayList<String> results = new ArrayList<String>();
        prefixSearch(s, results, limit);
        return results.toArray(new String[0]);
    }


    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("peter");
        t.insert("piper");
        t.insert("picked");
        t.insert("a");
        t.insert("peck");
        t.insert("of");
        t.insert("pickled");
        t.insert("peppers");
        t.insert("pepppito");
        t.insert("pepi");
        t.insert("pik");
        t.insert("bbb");
        t.insert("A12b");

        System.out.println(t.contains("peter"));
        System.out.println(t.contains("bbb"));
        System.out.println(t.contains("pepper"));
        System.out.println(t.contains("c"));
        System.out.println(t.contains("A12b"));

        // String[] result1 = t.prefixSearch("p", 10);
        String[] result2 = t.prefixSearch("pe.p", 10);
        for(String s: result2) {
            System.out.println(s);
        }
        // result1 should be:
        // ["peck", "pepi", "peppers", "pepppito", "peter"]
        // result2 should contain the same elements with result1 but may be ordered arbitrarily
    }
}
