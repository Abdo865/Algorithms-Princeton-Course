public class MergeSort {
    private static void merge(Comparable[] c, Comparable[] aux, int lo, int mid, int hi){
        if (!isSorted(c, lo, mid))             throw new IllegalArgumentException();
        if (!isSorted(c, mid + 1, hi))      throw new IllegalArgumentException();

        for (int k = lo; k <= hi; k++)
            aux[k] = c[k];

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)                      c[k] = aux[j++];
            else if (j > hi)                  c[k] = aux[i++];
            else if (less(aux[i], aux[j]))    c[k] = aux[i++];
            else                              c[k] = aux[j++];
        }
    }

    //checks whether a portion of the array is sorted or not
    private static boolean isSorted(Comparable[] c, int lo, int hi){
        for (int i = lo; i < hi; i++) {
            if (less(c[i+1], c[i]))
                return false;
        }
        return true;
    }

    //Sorts the
    public static void sort(Comparable[] c, Comparable[] aux, int lo, int hi){
        if (hi <= lo) return;
        int mid = (lo + hi) / 2;
        sort(c, aux, lo, mid);
        sort(c, aux, mid + 1, hi);
        merge(c, aux, lo, mid, hi);
    }

    //the function that will be called by users
    public static void sort(Comparable[] c){
        Comparable[] aux = new Comparable[c.length];
        sort(c, aux, 0, c.length - 1);
    }

    //helper method to find if an element is less than another element
    private static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    //helper method to swap 2 elements in the array
    private static void swap(Comparable[] c, int i, int j){
        Comparable temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }
}
