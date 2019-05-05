/**
 * This class defines the first QuickSort functionality. The first item of the partition is selected as the pivot.
 * Quicksort is used down to a partition size of k=100, then an insertion sort is used to finish.
 *
 * Note: this class was adapted from code from https://www.geeksforgeeks.org/iterative-quick-sort/
 *
 * @author Skyler Carlson
 * @since 2019-05-04
 */

public class QuickSort2 {

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
     * @param low       The low value of the partition.
     * @param high      The high value of the partition.
     * @return
     */
    private static int partition(int[] arr, int low, int high)
    {
        int pivot = arr[low];

        // Index of smaller element
        int i = (low-1);
        for (int j = low; j <= high-1; j++)
        {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
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

        // Push initial values of l and h to stack
        stack[++top] = l;
        stack[++top] = h;

        // Keep popping from stack while is not empty
        while (top >= 0)
        {
            // Pop h and l
            h = stack[top--];
            l = stack[top--];

            // Set pivot element at its correct position in sorted array
            int p = partition(arr, l, h);

            // If there are elements on left side of pivot, then push left side to stack
            if (p-1 > l)
            {
                stack[++top] = l;
                stack[++top] = p - 1;
            }

            // If there are elements on right side of pivot, then push right side to stack
            if (p+1 < h)
            {
                stack[++top] = p + 1;
                stack[++top] = h;
            }
        }
    }
}
