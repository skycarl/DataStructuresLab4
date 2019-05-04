/*
  This program implements two major sorting methods for analysis: Quicksort and heap sort. In Quicksort, four versions
  are explored.

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
        int[] problemSizeArray = importInputSizes(args);
        // TODO remove: Test print imported file array
        System.out.println(Arrays.toString(problemSizeArray));

        // Loop through each input size and sort
        for (int i = 0; i < problemSizeArray.length; i++) {

            // Generate the 3 cases for the input arrays: random, reverse, and ascending order

        }



    }

    /**
     * This method imports the file that contains the sizes of the sorting exercises. Each line contains new exercise size.
     * @param args      The program runtime arguments, passed from main().
     * @return          Array of all file names to be used throughout hte program.
     */
    private static int[] importInputSizes(String[] args) {

        String filename = args[0];
        String tempLine;
        int[] problemSizeArray = new int[1];
        int i = 1;

        try {
            // Create the file and scanner objects
            File inputSizeFile = new File(filename);
            Scanner inputScanner = new Scanner(new BufferedReader(new FileReader(inputSizeFile)));

            // Read the file line by line
            while (inputScanner.hasNextLine()) {
                tempLine = inputScanner.nextLine();

                // Test if the line is empty; if so, continue to next loop
                if (tempLine.isEmpty()) {
                    continue;
                }
                else {
                    problemSizeArray[i-1] = Integer.parseInt(tempLine);
                }

                // Create new array to store the data
                problemSizeArray = Arrays.copyOf(problemSizeArray, i+1);

                i++;

            }
            inputScanner.close();
        } catch (FileNotFoundException fileExc) {
            System.out.println("File not found: " + fileExc.getMessage() + ". Program exiting.");
            System.exit(1);
        }


        return problemSizeArray;
    }
}


