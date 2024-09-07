package UnionFind;

public class UnionFInd {
    int[] parent;
    int[] rank;

    public UnionFInd(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
        for(int i = 0; i < size; i++) {
            rank[i] = 1;
        }
    }


    public int find(int x) {
        if(parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if(px == py) {
            return false;
        }
        if(px != py) {
            if(rank[px] < rank[py]) {
                parent[px] = py;
                rank[py] += rank[px];
            } else {
                parent[py] = px;
                rank[px] += rank[py];
            }
        }
        return true;
    }
}
