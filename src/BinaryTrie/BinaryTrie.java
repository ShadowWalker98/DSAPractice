package BinaryTrie;

public class BinaryTrie {
    BinaryTrieNode root;

    public BinaryTrie() {
        root = new BinaryTrieNode();
    }

    public void insert(String n) {
        BinaryTrieNode track = root;
        char[] chars = n.toCharArray();
        for(int i = 0; i < chars.length; i++) {
            if(chars[i] == '0'){
                if(track.links[0] == null) {
                    track.links[0] = new BinaryTrieNode();
                }
                track = track.links[0];
            } else {
                if(track.links[1] == null) {
                    track.links[1] = new BinaryTrieNode();
                }
                track = track.links[1];
            }
            if(i == chars.length - 1) track.exists = true;
        }
    }

    public int getMax(String binRep) {
        StringBuilder sb = new StringBuilder();
        BinaryTrieNode track = root;
        char[] chars = binRep.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '0') {
                // look for 1 in the trie
                if(track.links[1] != null) {
                    sb.append("1");
                    track = track.links[1];
                } else {
                    sb.append("0");
                    track = track.links[0];
                }
            } else {
                if(track.links[0] != null) {
                    sb.append("1");
                    track = track.links[0];
                } else {
                    sb.append("0");
                    track = track.links[1];
                }
            }
        }
        return Integer.parseInt(sb.toString(), 2);
    }
}

class BinaryTrieNode {
    BinaryTrieNode[] links;
    boolean exists;

    public BinaryTrieNode() {
        links = new BinaryTrieNode[2];
        exists = false;
    }
}

class Query {
    int query;
    int m;
    int idx;

    public Query(int query, int m, int idx) {
        this.query = query;
        this.m = m;
        this.idx = idx;
    }
}