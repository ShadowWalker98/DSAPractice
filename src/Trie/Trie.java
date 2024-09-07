package Trie;

import java.util.Stack;

class Trie {

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode track = root;
        char[] chars = word.toCharArray();

        for(int i = 0; i < word.length(); i++) {
            int idx = chars[i] - 'a';
            if(track.links[idx] == null) {
                track.links[idx] = new TrieNode();
            }

            track = track.links[idx];

            if(i == word.length() - 1) {
                if(track.wordExists) {
                    track.num++;
                } else {
                    track.wordExists = true;
                    track.num = 1;
                }
            }
        }
    }

    public boolean search(String word) {
        TrieNode track = root;
        char[] chars = word.toCharArray();
        for(int i = 0; i < word.length(); i++) {
            int idx  = chars[i] - 'a';

            if(track.links[idx] != null) {
                track = track.links[idx];
            } else {
                return false;
            }
        }

        return track.wordExists;
    }

    public boolean startsWith(String prefix) {
        TrieNode track = root;
        char[] chars = prefix.toCharArray();
        for(int i = 0; i < prefix.length(); i++) {
            int idx  = chars[i] - 'a';

            if(track.links[idx] != null) {
                track = track.links[idx];
            } else {
                return false;
            }
        }

        return track.hasChildren();
    }

    public int countWordsEqualTo(String word) {
        TrieNode track = root;
        char[] chars = word.toCharArray();
        for(int i = 0; i < word.length(); i++) {
            int idx = chars[i] - 'a';
            if(track.links[idx] != null) {
                track = track.links[idx];
            } else {
                return 0;
            }
        }

        return track.num;
    }

    public int countWordsStartingWith(String prefix) {
        TrieNode track = root;
        char[] chars = prefix.toCharArray();
        for(int i = 0; i < chars.length; i++) {
            int idx = chars[i] - 'a';
            if(track.links[idx] != null) {
                track = track.links[idx];
            } else {
                return 0;
            }
        }

        return helper(track);
    }

    private int helper(TrieNode t) {
        int total = 0;
        for(int i = 0; i < t.links.length; i++) {
            if(t.links[i] != null) {
                total += helper(t.links[i]);
            }
        }

        if(t.wordExists) {
            total += t.num;
        }

        return total;
    }

    public void erase(String word) {
        TrieNode track = root;
        Stack<TrieNode> stack = new Stack<>();
        char[] chars = word.toCharArray();
        for(int i = 0; i < chars.length; i++) {
            int idx = chars[i] - 'a';
            if(track.links[idx] != null) {
                stack.push(track);
                track = track.links[idx];
            }
        }

        int i = word.length() - 1;



        while(!stack.isEmpty()) {
            TrieNode parent = stack.pop();
            if(i == word.length() - 1) {
                track.num--;
                if(!track.hasChildren()) {
                    int idx = chars[i--] - 'a';
                    parent.links[idx] = null;
                } else {
                    return;
                }
            } else {
                int idx = chars[i--] - 'a';
                if(track.hasChildren()) {
                    return;
                } else {
                    parent.links[idx] = null;
                }
            }

            track = parent;
        }
    }
}

class TrieNode {
    TrieNode[] links;
    boolean wordExists;
    int num;

    public TrieNode() {
        links = new TrieNode[26];
        wordExists = false;
        num = 0;
    }

    public boolean hasChildren() {
        for (TrieNode link : links) {
            if (link != null) return true;
        }
        return wordExists;
    }

}
