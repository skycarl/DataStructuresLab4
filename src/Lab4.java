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
import java.util.*;

public class Lab4 {

    /**
     * The main driver method for the program.
     * @param args      One runtime argument containing a list of all the file names to be used.
     */
    public static void main(String[] args) {

        // Output filenames
        String inputFilenames = null;
        String summaryOutputFilename = null;

        // Check that output directory exists; create if not
        checkOutputDirectory();

        // Get the user-defined input filename and user-defined output filename
        if (args.length == 2) {
            inputFilenames = args[0];
            summaryOutputFilename = args[1];
        }
        else if (args.length == 1) { // Case where we default to default output filenames
            inputFilenames = args[0];
            summaryOutputFilename = "summaryOutput.txt";
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
        System.out.println("Imported " + fileNameArray.length + " files: " + Arrays.toString(fileNameArray));

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
        outputSummaryData(results, summaryOutputFilename);
    }

    /**
     * This method prints a summary of the program to file for easier analysis.
     * @param results                   Array of SortPerformance objects containing performance data of each sort.
     * @param summaryOutputFilename     The used-specified (or default, if not specified) filename for the summary output file.
     */
    private static void outputSummaryData(SortPerformance[] results, String summaryOutputFilename) {

        // Modify the filename to be appropriate to output
        String headerString;
        String performanceString;
        String horizontalLineString;

        try {
            // Create FileWriter object pointer towards the "output" directory
            FileWriter outWriter = new FileWriter(summaryOutputFilename);

            outWriter.write("----- Sorting summary -----\n");

            // Create table header
            headerString = String.format("\n%-10s%21s%21s%21s%21s%21s%21s", "Sort type", "Heap sort (ns)", "Quicksort1 (ns)", "Quicksort2 (ns)", "Quicksort3 (ns)", "Quicksort4 (ns)", "Insertion sort (ns)");
            outWriter.write(headerString);

            horizontalLineString = "\n---------------------------------------------------------------------------------------------------------------------------------------";
            outWriter.write(horizontalLineString);

            // Loop thru SortPerformance array and create a table of the results
            for (SortPerformance result : results) {

                // Get the sort type from the filename by stripping off the extension
                String sortType = result.getFilename().replace(".txt", "");

                // Create string for each line and write
                performanceString = String.format("\n%-10s%21s%21s%21s%21s%21s%21s", sortType, result.getHeapSort(),
                        result.getQuickSort1(),
                        result.getQuickSort2(), result.getQuickSort3(), result.getQuickSort4(),
                        result.getInsertionSort());
                outWriter.write(performanceString);
            }

            outWriter.close();

        } catch (IOException ioExc) {
            System.out.println("Error writing to file " + ioExc.getMessage() +
                    ". Program exiting.");
            System.exit(1);
        }
    }

    /**
     * This method checks that the output folder exists. If it does not, it creates it.
     * Note: this method was created with code adapted from: https://stackoverflow.com/questions/28947250/create-a-directory-if-it-does-not-exist-and-then-create-the-files-in-that-direct
     */
    private static void checkOutputDirectory() {

        // Specify output folder name
        String filePath = "./sortedOutputFiles/";

        // Create the directory if it does not exist
        File directory = new File(filePath);
        if (! directory.exists()) {
            directory.mkdir();
        }
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
        //printStringToFile("Unsorted: \t" + Arrays.toString(unsortedArray), sortedOutputFile);
        //printStringToFile("Heapsorted: \t" + Arrays.toString(heapSorted), sortedOutputFile);
        //System.out.println("Unsorted: \t\t\t" + Arrays.toString(unsortedArray));
        //System.out.println("Heapsorted: \t\t" + Arrays.toString(heapSorted));
        // Create output file for heapsort
        outputSortedArray(heapSorted, filename, "Heapsorted"); // TODO change all these to output all sort output


        // ***** Quicksort1 *****
        // Make a deep copy of the unsorted array so we can keep track of it for later
        quickSorted1 = Arrays.copyOf(unsortedArray, unsortedArray.length);

        // Call the QuickSort1 class to sort the array and record the runtime in nanoseconds
        runTime = QuickSort1.sort(quickSorted1);
        performance.setQuickSort1(runTime);

        // Output sorted values to file
        //printStringToFile("Quicksorted1: \t" + Arrays.toString(quickSorted1), sortedOutputFile);
        //System.out.println("Quicksorted1: \t\t" + Arrays.toString(quickSorted1));
        outputSortedArray(quickSorted1, filename, "Quicksorted1");



        // ***** Quicksort2 *****
        // Make a deep copy of the unsorted array so we can keep track of it for later
        quickSorted2 = Arrays.copyOf(unsortedArray, unsortedArray.length);

        // Call the QuickSort2 class to sort the array and record the runtime in nanoseconds
        runTime = QuickSort2.sort(quickSorted2);
        performance.setQuickSort2(runTime);

        // Output sorted values to file
        //System.out.println("Quicksorted2: \t\t" + Arrays.toString(quickSorted2));
        //printStringToFile("Quicksorted2: \t" + Arrays.toString(quickSorted2), sortedOutputFile);
        outputSortedArray(quickSorted2, filename, "Quicksorted2");



        // ***** Quicksort3 *****
        // Make a deep copy of the unsorted array so we can keep track of it for later
        quickSorted3 = Arrays.copyOf(unsortedArray, unsortedArray.length);

        // Call the QuickSort3 class to sort the array and record the runtime in nanoseconds
        runTime = QuickSort3.sort(quickSorted3);
        performance.setQuickSort3(runTime);

        // Output sorted values to file
        //System.out.println("Quicksorted3: \t\t" + Arrays.toString(quickSorted3));
        //printStringToFile("Quicksorted3: \t" + Arrays.toString(quickSorted3), sortedOutputFile);
        outputSortedArray(quickSorted3, filename, "Quicksorted3");


        // ***** Quicksort4 *****
        // Make a deep copy of the unsorted array so we can keep track of it for later
        quickSorted4 = Arrays.copyOf(unsortedArray, unsortedArray.length);

        // Call the QuickSort4 class to sort the array and record the runtime in nanoseconds
        runTime = QuickSort4.sort(quickSorted4);
        performance.setQuickSort4(runTime);

        // Output sorted values to file
        //System.out.println("Quicksorted4: \t\t" + Arrays.toString(quickSorted4));
        //printStringToFile("Quicksorted4: \t" + Arrays.toString(quickSorted4), sortedOutputFile);
        outputSortedArray(quickSorted4, filename, "Quicksorted4");


        // ***** InsertionSort *****

        // Make a deep copy of the unsorted array so we can keep track of it for later
        insertionSorted = Arrays.copyOf(unsortedArray, unsortedArray.length);

        // Call the HeapSort class to sort the array and record the runtime in nanoseconds
        runTime = InsertionSort.sort(insertionSorted);
        performance.setInsertionSort(runTime);

        // Output sorted values to file
        //System.out.println("InsertionSorted: \t" + Arrays.toString(insertionSorted));
        //printStringToFile("InsertionSorted:" + Arrays.toString(insertionSorted), sortedOutputFile);
        outputSortedArray(insertionSorted, filename, "InsertionSorted");

        // TODO remove
        //System.out.println(performance.toString());

        // Print timing data to file
        //printStringToFile(performance.toString(), sortedOutputFile);

        return performance;
    }

    /**
     * This method takes a sorted array and prints it to a file based on the filename of the input array.
     * @param sorted        The sorted array.
     * @param filename      The name of the input file.
     */
    private static void outputSortedArray(int[] sorted, String filename, String sortType) {

        // Modify the filename to be appropriate to output
        String outputFilename = filename.replace(".", sortType + ".");

        try {
            // Create FileWriter object pointer towards the "output" directory
            FileWriter outWriter = new FileWriter(new File("sortedOutputFiles", outputFilename));


            for (int value : sorted) {
                outWriter.write(value + "\n");
            }
            outWriter.close();

        } catch (IOException ioExc) {
            System.out.println("Error writing to file " + ioExc.getMessage() +
                    ". Program exiting.");
        }
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
            outWriter.write(str + "\n");
            outWriter.close();

        } catch (IOException ioExc) {
            System.out.println("Error writing to file " + ioExc.getMessage() +
                    ". Program exiting.");
        }
    }
}


