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

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Lab4 {

    /**
     * The main driver method for the program.
     * @param args      One runtime argument containing a list of all the file names to be used.
     */
    public static void main(String[] args) {

        // Output filenames
        String inputFilenames = null;
        String sortedOutputFilename = null;
        String summaryOutputFilename;

        // Set up output file names; use default names if output file names are not specified
        if (args.length == 3) {
            inputFilenames = args[0];
            sortedOutputFilename = args[1];
            summaryOutputFilename = args[2];

            // Delete old output files
            deletePreviousFile(sortedOutputFilename);
            deletePreviousFile(summaryOutputFilename);

        }
        else if (args.length == 2) {
            System.out.println("Error: specify either one or three runtime parameters. Refer to README.md for more information and try again.");
            System.exit(1);
        }
        else if (args.length == 1) { // Case where we default to default output filenames
            inputFilenames = args[0];
            sortedOutputFilename = "sortedOutput.txt";
            summaryOutputFilename = "summaryOutput.txt";

            // Delete output files
            deletePreviousFile(sortedOutputFilename);
            deletePreviousFile(summaryOutputFilename);
        }
        else if (args.length == 0) {
            System.out.println("Error: runtime parameters must be specified. At minimum, specify name of file containing input filenames.");
            System.exit(1);
        }
        else { // Unspecified error with runtime parameters
            System.out.println("Unspecified error with runtime parameters. Refer to README.md and try again.");
            System.exit(1);
        }

        // Import files
        String[] fileNameArray = importFileNames(inputFilenames);

        // TODO remove test print imported file array
        System.out.println("Imported files: " + Arrays.toString(fileNameArray));

        // Create array of SortPerformance objects to store the results
        SortPerformance[] results = new SortPerformance[fileNameArray.length];

        // Create sorted output file name
        File sortedOutputFile = new File(sortedOutputFilename);


        // Loop through input files and sort them, storing the results
        for (int i = 0; i < fileNameArray.length; i++) {

            // Import a file
            int[] unsortedArray = importDataFile(fileNameArray[i]);
            //System.out.println(Arrays.toString(unsortedArray));

            // Send the array to a method that calls the sorting algorithms
            results[i] = sortWithAllMethods(unsortedArray, fileNameArray[i], sortedOutputFile);

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
    private static SortPerformance sortWithAllMethods(int[] unsortedArray, String filename, File sortedOutputFile) {

        // Declare method variables
        int[] heapSorted;
        int[] quickSorted1;
        int[] quickSorted2;
        int[] quickSorted3;
        int[] quickSorted4;
        int[] insertionSorted;
        long runTime;

        // Declare objects to be used in this method
        SortPerformance performance = new SortPerformance(filename);
        int[] sortedArray = new int[unsortedArray.length];

        // ***** Heapsort *****
        // Make a deep copy of the unsorted array so we can keep track of it for later
        heapSorted = Arrays.copyOf(unsortedArray, unsortedArray.length);

        // Call the HeapSort class to sort the array and record the runtime in nanoseconds
        runTime = HeapSort.sort(heapSorted);
        performance.setHeapSort(runTime);

        // Output results to file
        printStringToFile("\nUnsorted: \t\t\t" + Arrays.toString(unsortedArray), sortedOutputFile);
        printStringToFile("\nHeapsorted: \t\t" + Arrays.toString(heapSorted), sortedOutputFile);
        //System.out.println("Unsorted: \t\t\t" + Arrays.toString(unsortedArray));
        //System.out.println("Heapsorted: \t\t" + Arrays.toString(heapSorted));


        // ***** Quicksort1 *****
        // Make a deep copy of the unsorted array so we can keep track of it for later
        quickSorted1 = Arrays.copyOf(unsortedArray, unsortedArray.length);

        // Call the QuickSort1 class to sort the array and record the runtime in nanoseconds
        runTime = QuickSort1.sort(quickSorted1);
        performance.setQuickSort1(runTime);

        // Output sorted values to file
        printStringToFile("\nQuicksorted1: \t\t" + Arrays.toString(quickSorted1), sortedOutputFile);
        //System.out.println("Quicksorted1: \t\t" + Arrays.toString(quickSorted1));



        // ***** Quicksort2 *****
        // Make a deep copy of the unsorted array so we can keep track of it for later
        quickSorted2 = Arrays.copyOf(unsortedArray, unsortedArray.length);

        // Call the QuickSort2 class to sort the array and record the runtime in nanoseconds
        runTime = QuickSort2.sort(quickSorted2);
        performance.setQuickSort2(runTime);

        // Output sorted values to file
        //System.out.println("Quicksorted2: \t\t" + Arrays.toString(quickSorted2));
        printStringToFile("\nQuicksorted2: \t\t" + Arrays.toString(quickSorted2), sortedOutputFile);



        // ***** Quicksort3 *****
        // Make a deep copy of the unsorted array so we can keep track of it for later
        quickSorted3 = Arrays.copyOf(unsortedArray, unsortedArray.length);

        // Call the QuickSort3 class to sort the array and record the runtime in nanoseconds
        runTime = QuickSort3.sort(quickSorted3);
        performance.setQuickSort3(runTime);

        // Output sorted values to file
        //System.out.println("Quicksorted3: \t\t" + Arrays.toString(quickSorted3));
        printStringToFile("\nQuicksorted3: \t\t" + Arrays.toString(quickSorted3), sortedOutputFile);



        // ***** Quicksort4 *****
        // Make a deep copy of the unsorted array so we can keep track of it for later
        quickSorted4 = Arrays.copyOf(unsortedArray, unsortedArray.length);

        // Call the QuickSort4 class to sort the array and record the runtime in nanoseconds
        runTime = QuickSort4.sort(quickSorted4);
        performance.setQuickSort4(runTime);

        // Output sorted values to file
        //System.out.println("Quicksorted4: \t\t" + Arrays.toString(quickSorted4));
        printStringToFile("\nQuicksorted4: \t\t" + Arrays.toString(quickSorted4), sortedOutputFile);


        // ***** InsertionSort *****

        // Make a deep copy of the unsorted array so we can keep track of it for later
        insertionSorted = Arrays.copyOf(unsortedArray, unsortedArray.length);

        // Call the HeapSort class to sort the array and record the runtime in nanoseconds
        runTime = InsertionSort.sort(insertionSorted);
        performance.setInsertionSort(runTime);

        // Output sorted values to file
        //System.out.println("InsertionSorted: \t" + Arrays.toString(insertionSorted));
        printStringToFile("\nInsertionSorted: \t" + Arrays.toString(insertionSorted), sortedOutputFile);

        // TODO remove
        //System.out.println(performance.toString());

        // Print timing data to file
        printStringToFile(performance.toString(), sortedOutputFile);

        return performance;
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
            //System.out.println("\nImporting " + filename + "...");


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
     * @param filename      The name of the file containing all input files.
     * @return                    Array of all file names to be used throughout hte program.
     */
    private static String[] importFileNames(String filename) {

        // TODO there is some weird behavior when there are extra blank lines in this file; truncates the last element

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

    /**
     * Simple method to print a string to a file.
     *
     * @param str           String to be printed
     * @param outputFile    File object that is the output file
     */
    private static void printStringToFile(String str, File outputFile) {
        try {
            // Create FileWriter object in append mode
            FileWriter outWriter = new FileWriter(outputFile, true);

            // Write the string to file
            outWriter.write(str);
            outWriter.close();

        } catch (IOException ioExc) {
            System.out.println("Error writing to file " + ioExc.getMessage() +
                    ". Program exiting.");
        }
    }

    /**
     * This method deletes the previous file, if one exists of the same name.
     *
     * @param filename      The name of the output file, specified in runtime
     * parameters.
     */
    private static void deletePreviousFile(String filename) {
        File oldFile = new File(filename);
        oldFile.delete();
    }
}


