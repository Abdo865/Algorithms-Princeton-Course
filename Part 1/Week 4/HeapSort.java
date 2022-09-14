public class HeapSort {
    public static void sort(Comparable[] pq){
        int n = pq.length;

        for (int k = n/2; k >= 1; k--)
            sink(pq, k, n);

        int k = n;
        while (k > 1) {
            exch(pq, 1, k--);
            sink(pq, 1, k);
        }
    }

    private static void sink(Comparable[] pq, int k, int N){
        while (k * 2 <=  N){
            int j = 2 * k;
            if (j < N && less(pq, j, j + 1)) j++;
            if (less(pq, j, k)) break;
            exch(pq, j, k);
            k = j;
        }
    }

    private static boolean less(Comparable[] pq, int i, int j)
    { return pq[i - 1].compareTo(pq[j - 1]) < 0; }

    private static void exch(Comparable[] pq, int i, int j)
    { Comparable t = pq[i - 1]; pq[i - 1] = pq[j - 1]; pq[j - 1] = t; }

}
