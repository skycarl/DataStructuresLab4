/**
 * This class defines the first QuickSort functionality. The first item of the partition is selected as the pivot.
 * Quicksort is used down to a partition size of k=50, then an insertion sort is used to finish.
 *
 * Note: this class was adapted from code from https://www.geeksforgeeks.org/iterative-quick-sort/
 *
 * @author Skyler Carlson
 * @since 2019-05-04
 */

public class QuickSort3 {

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
    private static int partition(int[] arr, int first, int last)
    {

        int pivot = arr[first]; // Choose the first element as the pivot
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
        while (top >= 0 && ((h - l) > 50))
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

        // Send to insertion sort to finish
        InsertionSort.sort(arr);
    }
}
