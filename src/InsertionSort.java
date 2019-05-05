/**
 * This class defines the first Insertion Sort functionality.
 *
 * Note: this class was adapted from code from https://www.geeksforgeeks.org/insertion-sort/
 *
 * @author Skyler Carlson
 * @since 2019-05-04
 */

class InsertionSort {

    /**
     * This method sorts an array via an insertion sort.
     * @param arr       The array to be sorted.
     */
    void sort(int[] arr)
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

			/* Move elements of arr[0..i-1], that are 
			greater than key, to one position ahead 
			of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
}