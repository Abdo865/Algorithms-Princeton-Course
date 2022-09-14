public class WeightedQuickUnion {
    private int[] id;
    private int[] sz;
    private final int size;

    public WeightedQuickUnion(int size) {
        this.size = size;
        id = new int[size];
        sz = new int[size];
        for (int i = 0; i < size; i++){
            id[i] = i;
            sz[i] = 1;
        }
    }

    //finds the root of a given node
    public int root(int p) {
        while (id[p] != p) {
            p = id[p];
        }
        return p;
    }

    //finds whether 2 components are connected or not
    public boolean find(int p, int q){
        return root(p) == root(q);
    }

    public void union(int p, int q){
        int i = root(p), j = root(q);
        if (i == j)
            return;
        if (sz[i] < sz[j])  {  id[i] = j;  sz[j] += sz[i];  }
        else                {  id[j] = i;  sz[i] += sz[j];  }
    }

    public void print(){
        for (int i = 0; i < size; i++)
            System.out.print("id " + i + " = " +id[i]+ ", ");
        System.out.println();
        for (int i = 0; i < size; i++)
            System.out.print("id " + i + " = " +sz[i]+ ", ");
        System.out.println();

    }
}
