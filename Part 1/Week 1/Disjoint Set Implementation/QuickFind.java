public class QuickFind {
    private int[]  id;
    private int size;

    public QuickFind(int size){
        this.size = size;
        id = new int[size];
        for (int i = 0; i < size; i++)
            id[i] = i;
    }

    //finds whether 2 components are connected or not
    public boolean find(int p, int q){
        return id[p] == id[q];
    }

    public void union(int p, int q){
        if (find(p, q))
            return;
        int value = id[q];
        for (int i = 0; i < size; i++) {
            if (id[i] == value)
                id[i] = id[p];
        }
    }

    public void print(){
        for (int i = 0; i < size; i++)
            System.out.print("id " + i + " = " +id[i]+ ", ");
        System.out.println();
    }


}
