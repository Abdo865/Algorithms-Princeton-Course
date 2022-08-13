public class WeightedQuickUnionWithPathCompression {
    private int[] id;
    private byte[] rank;
    private final int size;

    public WeightedQuickUnionWithPathCompression(int size) {
        if (size < 0)
            throw new IllegalArgumentException();
        this.size = size;
        id = new int[size];
        rank = new byte[size];
        for (int i = 0; i < size; i++){
            id[i] = i;
            rank[i] = 0;
        }
    }

    //finds the root of a given node
    public int root(int p) {
        if (p < 0 || p >= size)
            throw new IllegalArgumentException("index: " + p + "is out of bounds of your array");
        while (id[p] != p) {
            p = id[p];
            id[p] = id[id[p]];
        }
        return p;
    }

    public void remove(int i){
        id[i] = i;
    }

    //finds whether 2 components are connected or not
    public boolean find(int p, int q){
        return root(p) == root(q);
    }

    public void union(int p, int q){
        int i = root(p), j = root(q);
        if (i == j)
            return;
        if (rank[i] < rank[j])     id[i] = j;
        else if(rank[i] > rank[j]) id[j] = i;
        else {
            id[j] = i;
            rank[i]++;
        }
    }

    public void print(){
        for (int i = 0; i < size; i++)
            System.out.print("id " + i + " = " +id[i]+ ", ");
        System.out.println();
        for (int i = 0; i < size; i++)
            System.out.print("id " + i + " = " +rank[i]+ ", ");
        System.out.println();

    }
}

