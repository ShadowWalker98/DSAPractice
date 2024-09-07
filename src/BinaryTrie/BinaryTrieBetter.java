package BinaryTrie;

public class BinaryTrieBetter {

    Node root;

    public BinaryTrieBetter() {
        root = new Node();
    }

    public void insert(int n) {
        Node t = root;
        for(int i = 6; i >= 0; i--) {
            if(((1 << i) & n) > 0) {
                if (t.links[1] == null) {
                    t.links[1] = new Node();
                }
                t = t.links[1];
            } else {
                if (t.links[0] == null) {
                    t.links[0] = new Node();
                }

                t = t.links[0];
            }

            if (i == 0) {
                t.exists = true;
            }
        }
    }

    public int getMax(int x, int max) {
        int maxNum = 0;
        Node node = root;

        for (int i = 6; i >= 0; i--) {
            int r = (1 << i) & x;
            if (r > 0) {
                if(node.links[0] != null) {
                    if((maxNum | (1 << i)) <= max) {
                        maxNum = (maxNum | (1 << i));
                        node = node.links[0];
                    } else {
                        node = node.links[1];
                    }
                } else {
                    node = node.links[1];
                }
            } else {
                if(node.links[1] != null) {
                    if((maxNum | (1 << i)) <= max) {
                        maxNum = (maxNum | (1 << i));
                        node = node.links[1];
                    } else {
                        node = node.links[0];
                    }
                } else {
                    node = node.links[0];
                }
            }
        }

        return maxNum;
    }

}

class Node {
    Node[] links;
    boolean exists;

    public Node() {
        links = new Node[2];
        exists = false;
    }
}
