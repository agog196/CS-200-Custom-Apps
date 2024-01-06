///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Test cases for H12CustomApp
// Course:          CS 200, Fall 2023
//
// Author:          Anish Gogineni
// Email:           agogineni2@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// N/A
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the test bench that contains testing methods for the H12CustomApp class.
 * The createTestDataFile and readTestDataFile are private testing methods intended to
 * be used within the test cases.
 * <p>
 * All the test cases within the testH12CustomApp method should be changed to test the
 * methods in your H12CustomApp class.
 *
 * @author Anish Gogineni
 */
public class TestH12CustomApp {

    /**
     * This method runs the selected tests.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        testH12CustomApp();
    }

    /**
     * This is a testing method to create a file with the specified name and fileContents
     * to be used by other testing methods. On a FileNotFoundException a stack trace is printed and
     * then returns.
     *
     * @param testDataFilename The filename of the testing file to create.
     * @param fileContents The data to put into the file.
     */
    private static void createTestDataFile(String testDataFilename, String fileContents) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(testDataFilename);
            writer.print(fileContents);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }

    /**
     * This method checks whether two CustomerDetail datatype variables have
     * the same attributes or not.
     *
     * @param customer1 First customer
     * @param customer2 Second customer
     * @return true if they have the same attributes, false otherwise
     */
    private static boolean compareCustomerDetails
    (H12CustomApp.CustomerDetails customer1, H12CustomApp.CustomerDetails customer2) {
        boolean result = true;
        if (customer1.customerID != customer2.customerID) {
            result = false;
        }
        if (!customer1.customerName.equals(customer2.customerName)) {
            result = false;
        }
        if (!customer1.customerEmail.equals(customer2.customerEmail)) {
            result = false;
        }
        if (!customer1.purchaseHistory.equals(customer2.purchaseHistory)) {
            result = false;
        }
        return result;
    }

    /**
     * This is a testing method to read and return the entire contents of the specified file to
     * be used soley by other testing methods.
     * On a FileNotFoundException a stack trace is printed and then "" returned.
     *
     * @param dataFilename The name of the file to read.
     * @return The contents of the file or "" on error.
     */
    private static String readTestDataFile(String dataFilename) {
        File file = new File(dataFilename);
        Scanner input = null;
        String contents = "";
        try {
            input = new Scanner( file);
            while (input.hasNextLine()) {
                contents += input.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (input != null) input.close();
        }
        return contents;
    }

    /**
     * Tests that the H12CustomApp read input and write output methods handle
     * the cases described in their method header comments.
     *
     * @return true for passing all testcases, false otherwise
     */
    public static boolean testH12CustomApp() {

        boolean error = false;

        {  // Test case 1: First case for readCustomerDetails method.

            // Creating a file with details of four customers in separate lines.
            String fileToRead = "testRead.txt";
            String fileContents = "1,John Doe,john.doe@email.com,Item1,Item2,Item3\n" +
                    "2,Jane Smith,jane.smith@email.com,Item2,Item4,Item5\n" +
                    "3,Bob Johnson,bob.johnson@email.com,Item1,Item3,Item5\n" +
                    "4,Alice Williams,alice.williams@email.com,Item2,Item4,Item6";
            createTestDataFile(fileToRead, fileContents);

            // In this test case, the second customer's details are going to be
            // checked.
            ArrayList<String> expectedPurchaseHistory = new ArrayList<>();
            expectedPurchaseHistory.add("Item2");
            expectedPurchaseHistory.add("Item4");
            expectedPurchaseHistory.add("Item5");
            H12CustomApp.CustomerDetails expected
                    = new H12CustomApp.CustomerDetails(2,
                    "Jane Smith", "jane.smith@email.com", expectedPurchaseHistory);

            // Reading the file with the H12CustomApp's read file method, which is
            // being tested in this test case.
            H12CustomApp.CustomerDetails actualContents = null;
            try {
                actualContents = H12CustomApp.readCustomerDetails("testRead.txt", 2);
            } catch(FileNotFoundException e) {
                System.out.println("File not found, test cases failed");
                return false;
            }

            // checking whether the actual contents are same as expected contents
            if (actualContents == null || !compareCustomerDetails(actualContents, expected)) {
                error = true;
                System.out.println("Actual contents differ " +
                        "from expected contents, test case 1 failed");
            } else {
                System.out.println("Test case 1 success");
                //since the test succeeded, removing the temporary file created with the testing
                // method
                File file = new File(fileToRead);
                file.delete();
            }
        }

        { // Test case 2: Second test case for readCustomerDetails method.
            // This test case checks whether an exception is thrown if a non-
            // existing file is passed in as a parameter to the readCustomerDetails
            // method.

            // Making sure the file doesn't exist by deleting it if it does.
            String fileToRead = "fileThatShouldNotExist.txt";
            File file = new File(fileToRead);
            if ( file.exists()) {
                file.delete();
            }

            // Will try using try catch block to see if an exception is thrown
            try {
                H12CustomApp.readCustomerDetails("fileThatShouldNotExist.txt", 1);
                error = true; // error is true if it gets to this sentence without
                              // throwing an exception
                System.out.println("Exception is not thrown, test case 2 failed");
                return false;
            } catch (FileNotFoundException e) {
                System.out.println("Test case 2 success");
            }

        }

        { // Test case 3: First test case for the writeCustomerDetails method.
            // This test case checks if true is returned by the method when
            // contents are successfully printed in a file. This test case
            // will also check whether the updated data is correct or not.

            boolean expected = true;
            String expectedContents = "2,Anish,agog@email.com,Item1,Item3,Item4\n";

            // For this test case, the file will be created by the writeCustomerDetails
            // method.

            // Creating the parameters to pass in to writeCustomerDetails method.
            String fileNameToWrite = "testWrite.txt";
            ArrayList<String> purchaseHistory = new ArrayList<>();
            purchaseHistory.add("Item1");
            purchaseHistory.add("Item3");
            purchaseHistory.add("Item4");
            H12CustomApp.CustomerDetails promptedCustomer
                    = new H12CustomApp.CustomerDetails(2,
                    "Anish", "agogineni2@wisc.edu", purchaseHistory);
            int userAction = 2; // User action 2 changes the email of the customer.
            String userInput = "agog@email.com"; // The new email.

            // Testing the writeCustomerDetails method.
            boolean actual = H12CustomApp.writeCustomerDetails
                    (fileNameToWrite, promptedCustomer, userAction, userInput);
            String actualContents = readTestDataFile("testWrite.txt");

            // Comparing expected and actual values
            if (actual == expected && actualContents.equals(expectedContents)) {
                System.out.println("Test case 3 success");
                //since the test succeeded, remove the temporary testing file.
                File file = new File(fileNameToWrite);
                file.delete();
            } else {
                error = true;
                System.out.println("Actual values are not the same " +
                        "as expected values, test case 3 failed");
            }

        }

        { // Test case 4: Second test case for the writeCustomerDetails method.
            // This test case checks if true is returned by the method when
            // contents are successfully printed in a file. This test case
            // will also check whether the updated data is correct or not.

            boolean expected = true;
            String expectedContents = "3,John,JohnIsCool@email.com,Item7,Item3,Item4,Item10\n";

            // For this test case, the file will be created by the writeCustomerDetails
            // method.

            // Creating the parameters to pass in to writeCustomerDetails method.
            String fileNameToWrite = "testWrite.txt";
            ArrayList<String> purchaseHistory = new ArrayList<>();
            purchaseHistory.add("Item7");
            purchaseHistory.add("Item3");
            purchaseHistory.add("Item4");
            H12CustomApp.CustomerDetails promptedCustomer
                    = new H12CustomApp.CustomerDetails(3,
                    "John", "JohnIsCool@email.com", purchaseHistory);
            int userAction = 3; // User action 3 adds to the purchase history.
            String userInput = "Item10"; // The new item added to purchase history.

            // Testing the writeCustomerDetails method.
            boolean actual = H12CustomApp.writeCustomerDetails(fileNameToWrite,
                    promptedCustomer, userAction, userInput);
            String actualContents = readTestDataFile("testWrite.txt");

            // Comparing expected and actual values
            if (actual == expected && actualContents.equals(expectedContents)) {
                System.out.println("Test case 4 success");
                //since the test succeeded, remove the temporary testing file.
                File file = new File(fileNameToWrite);
                file.delete();
            } else {
                error = true;
                System.out.println("Actual values are not the same as expected " +
                        "values, test case 4 failed");
                System.out.println(actualContents);
                System.out.println(expectedContents);
            }
        }


        if (error) {
            System.out.println("\nTestH12CustomApp failed");
            return false;
        } else {
            System.out.println("\nTestH12CustomApp passed");
            return true;
        }
    }

}
