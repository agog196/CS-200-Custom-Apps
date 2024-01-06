///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Test cases for H10CustomApp
// Course:          CS200, Fall 2023
//
// Author:          Anish Gogineni
// Email:           agogineni2@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// No help given or received.
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This contains testing methods for the H10CustomApp class.
 *
 * @author Anish Gogineni
 */
public class TestH10CustomApp {

    /**
     * This calls the testH10CustomApp method and prints out the result.
     * @param args command-line arguments (unused)
     */
    public static void main(String []args) {
        System.out.println("Success: " + testH10CustomApp());
    }

    /**
     * This has various test cases that call the app methods to verify they work
     * according to the descriptions in the app method header comments.  If all the
     * test cases pass, then true is returned, otherwise false.
     *
     * @return true when all test cases pass, false otherwise.
     */
    public static boolean testH10CustomApp() {
        boolean error = false;

        //These test cases verify the exampleMethod works as described in its
        //method header comments.
        // Test cases are in separate blocks { } so the variables can be reused in other test cases
        // without conflicts.

        {  // Test case 1, this checks whether the input values "4 5 4 7 hello world 6"
            // in Scanner creates an array of length 4 with the values 5, 4, 7, and 6, as
            // the method createArray converts the input values into an array of size of
            // the first integer input.
            Scanner scnr = new Scanner("4 5 4 7 hello world 6");
            int[] expected = {5, 4, 7, 6};
            int[] actual = H10CustomApp.createArray(scnr);
            for (int i = 0; i < expected.length; ++i) {
                if (actual != null) {
                    if (expected[i] != actual[i]) {
                        error = true;
                        break;
                    }
                }
                else {
                    error = true;
                }
            }
            if (error) {
                System.out.println(
                        "createArray 1) Expected: " + Arrays.toString(expected)
                                + " actual: " + Arrays.toString(actual));
            }
        }

        {  // Test case 2, this checks whether the input values "4 1 hello world 6 7 hi bye java"
            // in Scanner, when run as a parameter for the method createArray would throw an
            // InputMismatchException, as the method throws an InputMismatchException when 5
            // invalid inputs are entered.
            Scanner scnr = new Scanner("4 1 hello world 6 7 hi bye java");
            try {
                H10CustomApp.createArray(scnr);
                error = true;
                System.out.println(
                        "createdArray 2) Expected: InputMismatchException, "
                                + "actual: no InputMismatchException");
            }
            catch (InputMismatchException e) {
                error = false;
            }
        }

        {  // Test case 3, this checks whether putting in the arrays {5, 1, 7, 4} and
            // {17, 3, 13} as parameters for the findMedianOfTwoArrays method will return
            // 5.0, as the method finds the median of the two unsorted arrays and returns
            // the value as a double.
            int[] array1 = {5, 1, 7, 4};
            int[] array2 = {17, 3, 13};
            double expected = 5.0;
            double actual = H10CustomApp.findMedianOfTwoArrays(array1, array2);
            if (expected != actual) {
                error = true;
                System.out.println(
                        "findMedianOfTwoArrays 1) Expected: " + expected + " actual: " + actual);
            }
        }

        {  // Test case 4, this checks whether putting in the arrays {5, 1, 7, 4} and
            // {17, 3, 13, 6} as parameters for the findMedianOfTwoArrays method will return
            // 5.5, as the method finds the median of the two unsorted arrays and returns
            // the value as a double.
            int[] array1 = {5, 1, 7, 4};
            int[] array2 = {17, 3, 13, 6};
            double expected = 5.5;
            double actual = H10CustomApp.findMedianOfTwoArrays(array1, array2);
            if (expected != actual) {
                error = true;
                System.out.println(
                        "findMedianOfTwoArrays 2) Expected: " + expected + " actual: " + actual);
            }
        }

        if (error) {
            System.out.println("Error(s) in test cases.");
        } else {
            System.out.println("All test cases passed.");
        }
        return !error;
    }
}
