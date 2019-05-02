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
        String[] fileNameArray = importFileNames(args);

        // Test print imported file array
        System.out.println(Arrays.toString(fileNameArray));

    }

    /**
     * This method imports the file that contains all the input file names.
     * @param args      The program runtime arguments, passed from main().
     * @return          Array of all file names to be used throughout hte program.
     */
    private static String[] importFileNames(String[] args) {

        String filename = args[0];
        String tempLine;
        String[] fileNameArray = new String[1];
        int i = 1;

        try {
            // Create the file and scanner objects
            File freqTableFile = new File(filename);
            Scanner freqTableScanner = new Scanner(new BufferedReader(new FileReader(freqTableFile)));

            // Read the file line by line
            while (freqTableScanner.hasNextLine()) {
                tempLine = freqTableScanner.nextLine();

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
            freqTableScanner.close();
        } catch (FileNotFoundException fileExc) {
            System.out.println("File not found: " + fileExc.getMessage() + ". Program exiting.");
            System.exit(1);
        }


        return fileNameArray;
    }
}


