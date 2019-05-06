/**
 * This class defines the first Insertion Sort functionality.
 * <p>
 * Note: this class was adapted from code from https://www.geeksforgeeks.org/insertion-sort/
 *
 * @author Skyler Carlson
 * @since 2019-05-04
 */

class InsertionSort {

   /**
    * Wrapper method for insertion sort. This is not needed here, but is done for the sake of fair comparison in regards
    * to the timing, because the other sorting methods utilize a wrapped method.
    *
    * @param arr Array to be sorted.
    * @return Execution time in nanoseconds.
    */
   public static long sort(int[] arr) {

      // Begin timer, using nanoseconds
      long startTime = System.nanoTime();

      insertionSort(arr);

      // End timer and return
      long endTime = System.nanoTime();
      return endTime - startTime;
   }

   /**
    * This method sorts an array via an insertion sort.
    *
    * @param arr The array to be sorted.
    */
   private static void insertionSort(int[] arr) {

      int n = arr.length;
      for (int i = 1; i < n; ++i) {
         int key = arr[i];
         int j = i - 1;

         // Move elements of arr[0..i-1], that are greater than key, to one position ahead of their current position
         while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j = j - 1;
         }
         arr[j + 1] = key;
      }
   }
}