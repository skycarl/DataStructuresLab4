import java.util.Arrays;

/**
 * This class defines the first QuickSort functionality. The pivot selection methodology is median-of-three. QuickSort
 * is used to sort the entire array.
 *
 * Note: this class was adapted from code from https://www.geeksforgeeks.org/iterative-quick-sort/
 * and https://github.com/jsquared21/Intro-to-Java-Programming/blob/master/Exercise_23/Exercise_23_04/Exercise_23_04.java
 * and https://gist.github.com/epomp447/4c0d0676d9f013788647cbe6e60ae0df
 *
 * @author Skyler Carlson
 * @since 2019-05-05
 */

public class QuickSort4 {

    /**
     * Wrapper method to call the quickSortIterative method easier from the driver class. This also allows for timing to
     * happen closer to the actual sorting, for greater accuracy.
     * @param arr       Array to be sorted.
     */
    public static long sort(int[] arr) {

        // Begin timer, using nanoseconds
        long startTime = System.nanoTime();

        // Call the quicksort method
        quickSortIterative(arr, 0, arr.length-1);

        // End timer and return
        long endTime = System.nanoTime();
        return endTime - startTime;
    }



    /**
     * Method that defines the partition, using the first element as the pivot.
     * @param arr       The array to be sorted.
     * @param first       The low value of the partition.
     * @param last      The high value of the partition.
     * @return          Final sorted position of the pivot.
     */
    private static int partition(int[] arr, int first, int last) {
        //int pivot = arr[first]; // Choose the first element as the pivot

        //int mid = (last + first) / 2;
        int pivot = medianPivot(arr, first, last);

        int low = first + 1; // Index for forward search
        int high = last; // Index for backward search

        while (high > low) {
            // Search forward from left
            while (low <= high && arr[low] <= pivot)
                low++;

            // Search backward from right
            while (low <= high && arr[high] > pivot)
                high--;

            // Swap two elements in the list
            if (high > low) {
                int temp = arr[high];
                arr[high] = arr[low];
                arr[low] = temp;
            }
        }

        while (high > first && arr[high] >= pivot)
            high--;

        // Swap pivot with list[high]
        if (pivot > arr[high]) {
            arr[first] = arr[high];
            arr[high] = pivot;
            return high;
        }
        else {
            return first;
        }
    }

    /**
     * Selects the pivot based on a Median-of-Three strategy.
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public static int medianPivot(int[] arr, int low, int high) {
        /*
         * create subarray with low, high, and middle elements in the array sort the
         * subarray and use index 1 as the median of 3
         */

        int first = arr[low];
        int last = arr[arr.length - 1];
        int mid = (high) / 2;

        //System.out.println("\tMiddle of Arr at Index= " + mid + " : " + arr[mid]);
        int[] sortingArr = { arr[low], arr[mid], arr[high] };

        // TODO change to native insertionsort?
        Arrays.sort(sortingArr);

        int middleValue = sortingArr[1];
        //System.out.println("\t"+ Arrays.toString(sortingArr));
        //printArray(sortingArr);

        /*
        // swap with the last to serve as pivot
        int temp = arr[high];
        arr[high] = middleValue;
        if (middleValue == arr[low]) {
            arr[low] = temp;
        } else if (middleValue == arr[mid]) {
            arr[mid] = temp;
        }

         */

        return middleValue;

        //return partition(arr, low, high); //TODO whats up here?

    }

    /**
     * Method that sorts the input array iteratively using the QuickSort method.
     * @param arr       The array to be sorted.
     * @param l         The low value of the partition
     * @param h         The high value of the partition.
     */
    private static void quickSortIterative (int[] arr, int l, int h)
    {
        // Create an auxiliary stack
        int[] stack = new int[h-l+1];

        // initialize top of stack
        int top = -1;

        // push initial values of l and h to stack
        stack[++top] = l;
        stack[++top] = h;

        // Keep popping from stack while is not empty
        while (top >= 0)
        {
            // Pop h and l
            h = stack[top--];
            l = stack[top--];

            // Set pivot element at its correct position
            // in sorted array
            int p = partition(arr, l, h);

            // If there are elements on left side of pivot,
            // then push left side to stack
            if (p-1 > l)
            {
                stack[++top] = l;
                stack[++top] = p - 1;
            }

            // If there are elements on right side of pivot,
            // then push right side to stack
            if (p+1 < h)
            {
                stack[++top] = p + 1;
                stack[++top] = h;
            }
        }
    }
}
