public abstract class BinarySearch {

    // returns the index of the required value or -1 if not find
    public static int find(int[] arr, int value){
        if (arr == null || arr.length == 0)
            throw new IllegalArgumentException();
        int lo = 0, hi = arr.length-1;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == value)       return mid;
            else if (arr[mid] < value)   lo = mid + 1;
            else                         hi = mid - 1;;
        }
        return -1;
    }
}
