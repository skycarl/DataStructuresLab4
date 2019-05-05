/*
  This program implements two major sorting methods for analysis: Quicksort and heap sort.

  In Quicksort, four versions are explored:
  - [Quicksort1] First item of the partition is selected as the pivot. Sorting is complete when the partition is of size 1 and 2.
  - [Quicksort2] First item of the partition is selected as the pivot. Quicksort is used down to a partition size of k=100, then an insertion sort is used to finish.
  - [Quicksort3] First item of the partition is selected as the pivot. Quicksort is used down to a partition size of k=50, then an insertion sort is used to finish.
  - [Quicksort4] Median-of-three is used as the pivot. Quicksort is used down to a partition size of k=50, then an insertion sort is used to finish.

  @author Skyler Carlson
  @since 2019-05-01
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Lab4 {

    /**
     * The main driver method for the program.
     * @param args      One runtime argument containing a list of all the file names to be used.
     */
    public static void main(String[] args) {

        // Import files
        String[] fileNameArray = importFileNames(args);

        // TODO remove test print imported file array
        System.out.println("Imported files: " + Arrays.toString(fileNameArray));

        // Create array of SortPerformance objects to store the results
        SortPerformance[] results = new SortPerformance[fileNameArray.length];


        // Loop through input files and sort them, storing the results
        for (int i = 0; i < fileNameArray.length; i++) {

            // Import a file
            int[] unsortedArray = importDataFile(fileNameArray[i]);
            //System.out.println(Arrays.toString(unsortedArray));

            // Send the array to a method that calls the sorting algorithms
            results[i] = sortWithAllMethods(unsortedArray, fileNameArray[i]);

            //


        }


        // Call method to print create and print the summary output file
    }

    /**
     * Method that takes an unsorted array and sends them to the sorting methods. It then sends the sorted results to a file.
     * @param unsortedArray     An integer array of the unsorted values.
     * @param filename          String representation of the filename.
     * @return                  A SortPerformance object containing the performance of the sort.
     */
    private static SortPerformance sortWithAllMethods(int[] unsortedArray, String filename) {

        // Declare method variables
        int[] heapSorted;
        int[] quickSorted1;
        long runTime;

        // Declare objects to be used in this method
        SortPerformance performance = new SortPerformance(filename);
        int[] sortedArray = new int[unsortedArray.length];


        // ***** Heapsort *****
        // Make a deep copy of the unsorted array so we can keep track of it for later
        heapSorted = Arrays.copyOf(unsortedArray, unsortedArray.length);

        // Call the HeapSort class to sort the array and record the runtime in nanoseconds
        runTime = HeapSort.sort(heapSorted);

        // Write the sort time in nanoseconds to the performance object
        performance.setHeapSort(runTime);

        // TODO output results to file
        System.out.println("Unsorted: \t\t" + Arrays.toString(unsortedArray));
        System.out.println("Heapsorted: \t" + Arrays.toString(heapSorted));


        // ***** Quicksort1 *****
        // Make a deep copy of the unsorted array so we can keep track of it for later
        quickSorted1 = Arrays.copyOf(unsortedArray, unsortedArray.length);

        // Call the HeapSort class to sort the array and record the runtime in nanoseconds
        runTime = QuickSort1.sort(quickSorted1);

        // Write the sort time in nanoseconds to the performance object
        performance.setQuickSort1(runTime);

       // TODO Output sorted values to file
        System.out.println("Quicksorted1: \t" + Arrays.toString(heapSorted));



        // ***** Quicksort2 *****

        // Store performance results from heap sort

        // Output sorted values to file





        // ***** Quicksort3 *****

        // Store performance results from heap sort

        // Output sorted values to file




        // ***** Quicksort4 *****

        // Store performance results from heap sort

        // Output sorted values to file


        // TODO remove
        System.out.println(performance.toString());






        return performance;
    }

    /**
     * This method sorts using a heap sort.
     * @param unsortedArray     The unsorted array of integer values.
     * @return                  An integer array of sorted values.
     */
    private static int[] heapSort(int[] unsortedArray) {

        // Declare variables to be used in scope
        int[] sorted = new int[unsortedArray.length];

        // Sorting algorithm here

        return sorted;
    }

    /**
     * This method imports a data file and stores it in an array.
     * @param filename      The name of the data file.
     * @return              An integer array containing the values imported from the file.
     */
    private static int[] importDataFile(String filename) {

        // Declare variables used
        String tempLine;
        int[] unsorted = new int[1];
        int i = 1;


        try {
            // Add the folder to the file path in which the data files are held
            filename = "./input/" + filename;

            // TODO remove test print
            System.out.println("\nImporting " + filename + "...");


            // Create the file and scanner objects
            File dataFile = new File(filename);
            Scanner dataFileScanner = new Scanner(new BufferedReader(new FileReader(dataFile)));

            // Read the file line by line
            while (dataFileScanner.hasNextLine()) {
                tempLine = dataFileScanner.nextLine();

                // Test if the line is empty; if so, continue to next loop
                if (tempLine.isEmpty()) {

                    // If we've reached an empty line, delete the last entry because it is null
                    unsorted = Arrays.copyOf(unsorted, unsorted.length-1);

                    continue;
                }
                else {
                    unsorted[i-1] = Integer.parseInt(tempLine);
                }

                // Create new array to store the data
                unsorted = Arrays.copyOf(unsorted, i+1);
                i++;

            }

            // End of file has been reached; clean things up
            dataFileScanner.close();

            // Remove the null entry at the end of the array
            unsorted = Arrays.copyOf(unsorted, unsorted.length-1);

        } catch (FileNotFoundException fileExc) {
            System.out.println("File not found: " + fileExc.getMessage() + ". Program exiting.");
            System.exit(1);
        }

        return unsorted;
    }

    /**
     * This method imports the file that contains all the input file names.
     * @param args      The program runtime arguments, passed from main().
     * @return          Array of all file names to be used throughout hte program.
     */
    private static String[] importFileNames(String[] args) {

        // TODO there is some weird behavior when there are extra blank lines in this file; truncates the last element

        String filename = args[0];
        String tempLine;
        String[] fileNameArray = new String[1];
        int i = 1;

        try {
            // Create the file and scanner objects
            File filenameFile = new File(filename);
            Scanner fileScanner = new Scanner(new BufferedReader(new FileReader(filenameFile)));

            // Read the file line by line
            while (fileScanner.hasNextLine()) {
                tempLine = fileScanner.nextLine();

                // Test if the line is empty; if so, continue to next loop
                if (tempLine.isEmpty()) {
                    // If we've reached an empty line, delete the last entry because it is null
                    // TODO is this the source of the bug with the last element getting deleted?
                    fileNameArray = Arrays.copyOf(fileNameArray, fileNameArray.length-1);

                    continue;
                }
                else {
                    fileNameArray[i-1] = tempLine;
                }

                // Create new array to store the data
                fileNameArray = Arrays.copyOf(fileNameArray, i+1);

                i++;

            }

            // End of file has been reached; clean things up
            fileScanner.close();

            // Remove the null entry at the end of the array
            fileNameArray = Arrays.copyOf(fileNameArray, fileNameArray.length-1);

        } catch (FileNotFoundException fileExc) {
            System.out.println("File not found: " + fileExc.getMessage() + ". Program exiting.");
            System.exit(1);
        }

        return fileNameArray;
    }
}


