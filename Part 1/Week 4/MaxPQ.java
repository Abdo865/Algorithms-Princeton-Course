public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;


    public MaxPQ(int capacity)
    { pq = (Key[]) new Comparable[capacity+1]; }

    public boolean isEmpty()
    { return N == 0; }

    public void insert(Key key){
        if (N == pq.length)
            throw new ArrayIndexOutOfBoundsException("your Priority Queue has reached its maximum capacity");
        pq[++N] = key;
        swim(N);
    }

    public Key delMax() {
        if (N == 0)
            throw new IllegalArgumentException("The Priority Queue is Empty");
        Key toReturn = pq[1];
        exch(1, N);
        sink(1);
        pq[N--] = null;
        return toReturn;
    }

    public Key max()
    { return N == 0? null : pq[1]; }

    private void swim(int k){
        while (k > 1 && less(k/2, k)){
            exch(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (k * 2 <= N){
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (less(j, k)) break;
            exch(j, k);
            k = j;
        }
    }

    private boolean less(int i, int j)
    { return pq[i].compareTo(pq[j]) < 0; }

    private void exch(int i, int j)
    { Key t = pq[i]; pq[i] = pq[j]; pq[j] = t; }

}
