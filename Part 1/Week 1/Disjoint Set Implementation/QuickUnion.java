public class QuickUnion {
    private int[]  id;
    private int size;

    public QuickUnion(int size){
        this.size = size;
        id = new int[size];
        for (int i = 0; i < size; i++)
            id[i] = -1;
    }

    //finds the root of a given node
    public int root(int p) {
        while (id[p] >= 0) {
            p = id[p];
        }
        return p;
    }

    //finds whether 2 components are connected or not
    public boolean find(int p, int q){
        return root(p) == root(q);
    }

    public void union(int p, int q){
        if (find(p, q))
            return;
        id[root(p)] = root(q);
        id[root(q)] = -1;
    }

    public void print(){
        for (int i = 0; i < size; i++)
            System.out.print("id " + i + " = " +id[i]+ ", ");
        System.out.println();
    }
}
