public class Sort {
    //this will use the selection way to sort your array
    public static void SelectionSort(Comparable[] c) {
        for (int i = 0; i < c.length; i++) {
            int min = i;
            for (int j = i + 1; j < c.length; j++) {
                if (less(c[j], c[i]))
                    min = j;
            }
            swap(c, i, min);
        }
    }

    //this will use the Insertion way to sort your array
    public static void InsertionSort(Comparable[] c) {
        for (int i = 0; i < c.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(c[j], c[j - 1]))
                    swap(c, j, j - 1);
                else
                    break;
            }
        }
    }

    //this will use the Shell way to sort your array using the 3x+1 increment sequence
    public static void ShellSort(Comparable[] c) {
        int h = 1;
        while(h < c.length / 3)
            h = 3 * h + 1;
        while (h >= 1){
            for (int i = h; i < c.length; i++) {
                for (int j = i; j >= h && less(c[j], c[j-h]); j -= h)
                    swap(c, j, j-h);
            }
            h /= 3;
        }
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
