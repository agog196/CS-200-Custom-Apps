///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Test cases for H8CustomApp
// Course:          CS 200, Fall 2023
//
// Author:          Anish Gogineni
// Email:           agogineni2@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// No help given or received.
//
///////////////////////////////// REFLECTION ///////////////////////////////////
//
// 1. Describe the problem you wrote the program to solve:
//    The user decides if the binary numbers get added or multiplied, then
//    the user inputs the number of Binary numbers they want to enter and then
//    input the binary numbers. The program, then adds all the binary numbers
//    and returns the end result. The code is contained in the H8CustomApp file
//    and this file contains the test cases for the original H8CustomApp file.
// 2. What are the biggest challenges you encountered:
//    Coding the test cases were easy, but fixing a minor error that the test
//    cases helped in finding out about was a little challenging.
// 3. What did you learn from this assignment:
//    I learnt how to implement test cases to check whether the original code
//    is working good or not, and I also learnt how to use methods from other
//    classes when both files are in the same folder.
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////


/**
 * This class contains test cases for testing the app methods in
 * H8CustomApp.
 *
 * @author Anish Gogineni
 */
public class TestH8CustomApp {

    /**
     * This has various test cases that call the app methods to verify they work
     * according to the descriptions in the app method header comments.  If all the
     * test cases pass, then true is returned, otherwise false.
     *
     * @return true when all test cases pass, false otherwise.
     */
    public static boolean testH8CustomApp() {
        boolean error = false;

        // These test cases verify whether the app methods of H8CustomApp work as stated in
        // method header comments.
        // Test cases are in separate blocks { } so the variables can be reused in other test cases
        // without conflicts.

        {  // Test case 1, this checks whether "1001001" is returned when 73 is entered as
            // a parameter of the method covertDecimalToBinary, as 1001001 is the binary
            // form of the decimal number 73.
            int decimalNum = 73;
            String expected = "1001001";
            String result = H8CustomApp.convertDecimalToBinary(decimalNum);
            if (!result.equals(expected)) {
                error = true;
                System.out.println("convertDecimalToBinary 1) expected:"
                        + expected + " result:" + result);
            }
        }

        { // Test case 2, this checks whether "1101" is returned when 13 is entered as
            // a parameter of the method covertDecimalToBinary, as 1101 is the binary
            // form of the decimal number 13.
            int decimalNum = 13;
            String expected = "1101";
            String result = H8CustomApp.convertDecimalToBinary(decimalNum);
            if (!result.equals(expected)) {
                error = true;
                System.out.println("convertDecimalToBinary 2) expected:"
                        + expected + " result:" + result);
            }
        }

        { // Test case 3, this checks whether 56 is returned when "111000" is entered as
            // a parameter of the method covertBinaryToDecimal, as 56 is the decimal
            // form of the binary number "111000".
            String binaryNum = "111000";
            int expected = 56;
            int result = H8CustomApp.convertBinaryToDecimal(binaryNum);
            if (result != expected) {
                error = true;
                System.out.println("convertBinaryToDecimal 1) expected:"
                        + expected + " result:" + result);
            }
        }

        { // Test case 4, this checks whether 105 is returned when "1101001" is entered as
            // a parameter of the method covertBinaryToDecimal, as 105 is the decimal
            // form of the binary number "1101001".
            String binaryNum = "1101001";
            int expected = 105;
            int result = H8CustomApp.convertBinaryToDecimal(binaryNum);
            if (result != expected) {
                error = true;
                System.out.println("convertBinaryToDecimal 2) expected:"
                        + expected + " result:" + result);
            }
        }

        { // Test case 5, this checks whether true is returned when "11011" is entered as
            // a parameter of the method isValidBinaryNUmber, as 11011 is a valid binary number.
            String binaryNum = "1101001";
            boolean expected = true;
            boolean result = H8CustomApp.isValidBinaryNumber(binaryNum);
            if (result != expected) {
                error = true;
                System.out.println("convertBinaryToDecimal 2) expected:"
                        + expected + " result:" + result);
            }
        }

        { // Test case 6, this checks whether false is returned when "1234ab" is entered as
            // a parameter of the method isValidBinaryNUmber, as 1234ab is not
            // a valid binary number.
            String binaryNum = "1234ab";
            boolean expected = false;
            boolean result = H8CustomApp.isValidBinaryNumber(binaryNum);
            if (result != expected) {
                error   = true;
                System.out.println("convertBinaryToDecimal 2) expected:"
                        + expected + " result:" + result);
            }
        }

        if ( error) {
            System.out.println("Error(s) in test cases.");
        } else {
            System.out.println("All test cases passed.");
        }
        return !error;
    }

    /**
     * This calls the testH8CustomApp method and prints out the result.
     * @param args command-line arguments (unused)
     */
    public static void main(String []args) {
        System.out.println("Success: " + testH8CustomApp());
    }
}
