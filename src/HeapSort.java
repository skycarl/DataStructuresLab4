/**
 * This class defines the HeapSort functionality.
 *
 * Note: this class was adapted from code from https://www.geeksforgeeks.org/iterative-heap-sort/
 *
 * @author Skyler Carlson
 * @since 2019-05-04
 */

// Java implementation of Iterative Heap Sort
public class HeapSort {

    private int[] unsorted;
    private int[] sorted;


    /**
     * Wrapper method that calls the heapSort() method with the size of the array, so that main() doesn't have to pass it
     * @param unsorted      The unsorted array to be sorted.
     * @return
     */
    public static void sort(int[] unsorted) {

        //int[] sorted = new int[unsorted.length];

        heapSort(unsorted, unsorted.length);

        //return sorted;
    }

    /**
     * Method that builds a max heap for the heapsort; each child is always smaller than the value of its parent.
     * @param arr       Array being input
     * @param n         Counter
     */
    private static void buildMaxHeap(int[] arr, int n)
    {
        for (int i = 1; i < n; i++)
        {
            // If child is bigger than parent
            if (arr[i] > arr[(i - 1) / 2])
            {
                int j = i;

                // Swap child and parent until parent is smaller
                while (arr[j] > arr[(j - 1) / 2])
                {
                    swap(arr, j, (j - 1) / 2);
                    j = (j - 1) / 2;
                }
            }
        }
    }

    /**
     * Method that performs the heap sort.
     * @param arr       Input array
     * @param n         Counter
     */
    private static void heapSort(int[] arr, int n)
    {
        buildMaxHeap(arr, n);

        for (int i = n - 1; i > 0; i--)
        {
            // Swap value of first indexed with last indexed
            swap(arr, 0, i);

            // Maintaining heap property after each swapping
            int j = 0, index;

            do
            {
                index = (2 * j + 1);

                // If left child is smaller than right child point index variable to right child
                if (index < (i - 1) && arr[index] < arr[index + 1])
                    index++;

                // If parent is smaller than child then swapping parent with child having higher value
                if (index < i && arr[j] < arr[index])
                    swap(arr, j, index);

                j = index;

            } while (index < i);
        }
    }

    /**
     * Simple swap method that swaps 2 values in an array.
     * @param array     Array in which values are being swapped.
     * @param i         Location of the 1st element being swapped.
     * @param j         Location from the 2nd element being swapped.
     */
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
