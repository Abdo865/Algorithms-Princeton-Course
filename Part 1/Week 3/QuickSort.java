import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {
    private static int partition(Comparable[] a, int lo, int hi)
    {
        int i = lo, j = hi+1;
        while (true)
        {
            while (less(a[++i], a[lo]))
                if (i == hi) break;
            while (less(a[lo], a[--j]))
                if (j == lo) break;

            if (i >= j) break;
            swap(a, i, j);
        }
        swap(a, lo, j);
        return j;
    }

    public void sort(Comparable[] arr){
        StdRandom.shuffle(arr);
        sort(arr,0, arr.length - 1);
    }

    private void sort(Comparable[] arr, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(arr, lo, hi);
        sort(arr, lo, j - 1);
        sort(arr, j + 1, hi);
    }
    private static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    private static void swap(Comparable[] c, int i, int j){
        Comparable temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }

}
