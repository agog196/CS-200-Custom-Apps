///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Update customer details (H12CustomApp)
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
///////////////////////////////// REFLECTION ///////////////////////////////////
//
// 1. Describe the problem you wrote the program to solve:
//    This program allows the user to select a customer from the input customer data
//    file, and lets the user update an attribute associated with the customer. Then,
//    the program prints the updated customer's attributes into another file.
// 2. Why did you choose the method header for the read file method (e.g., return type,
//    parameters, throws clause)?
//    The name of my read file method is readCustomerDetails. As the name suggests, the
//    method reads a line in the input file and creates a new CustomerDetails reference
//    with the attributes listed. The return type is CustomerDetails, because it returns
//    a reference of CustomerDetails with the attributes listed in the line in the original
//    input file. The method also throws FileNotFound exception, which lets me catch it
//    later in the main method.
// 3. Why did you choose the method header for the write file method (e.g., return type,
//    parameters, throws clause)?
//    The name of the write file method is writeCustomerDetails. As the name suggests, the
//    method writes the customer details into a file using PrintWriter. The return type is
//    boolean because the method returns true if the customer attributes are successfully
//    printed into a file, and returns false if the customer attributes are not successfully
//    printed into a file.
// 4. What are the biggest challenges you encountered:
//    One big challenge I had to deal with was to check for any invalid input in any stage of
//    the program, as there were many user interactions and inputs throughout the program.
// 3. What did you learn from this assignment:
//    I learnt how to work with files, in both reading the contents of the file and writing
//    content into a file. I also learned how to deal with exceptions properly and work with
//    classes. This project also helped me learn how to choose the correct parameters and
//    return types when working with methods.
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This program allows the user to select a customer from the input customer data
 * file, and lets the user update an attribute associated with the customer. Then,
 * the program prints the updated customer's attributes into another file. The methods
 * are all checked for exceptions and invalid inputs.
 *
 * @author Anish Gogineni
 */
public class H12CustomApp {

    /**
     * This class is for the customer details. Attributes for customers are created here
     * using a constructor, which are used throughout the entire rest of the program.
     *
     * @author Anish Gogineni
     */
    static class CustomerDetails {
        int customerID; // attribute for customer ID
        String customerName; // attribute for customer Name
        String customerEmail; // attribute for customer Email
        ArrayList<String> purchaseHistory; // attribute for purchase History

        public CustomerDetails(int customerID, String customerName,
                               String customerEmail, ArrayList<String> purchaseHistory) {
            this.customerID = customerID;
            this.customerName = customerName;
            this.customerEmail = customerEmail;
            this.purchaseHistory = purchaseHistory;
        }
    }

    /**
     * This method reads the details of the customer with the customer ID specified
     * as a parameter from the file specified as a parameter. It reads the line
     * that contains the required customer details, and assigns each attribute as
     * a different attribute of the CustomerDetails datatype and returns it.
     * <p>
     * Example: Let's consider a file "data.txt" has
     * 1,John Doe,john.doe@email.com,Item1,Item2,Item3
     * 2,Jane Smith,jane.smith@email.com,Item2,Item4,Item5
     * as the lines, and the customer ID passed in as a parameter is 2.
     * The method would then return a CustomerDetails datatype with customer ID 2,
     * customer Name "Jane", customer Email "jane.smith@email.com", customer purchase
     * history as an arraylist with elements Item2, Item4, Item5.
     *
     * @param filename   The name of the file to read from
     * @param customerID The ID of the customer whose data is to be read
     * @return Attributes of the customer in the form of CustomerDetails datatype.
     * @throws FileNotFoundException It throws a FileNotFoundException if there is no
     *                               file with the name filename. This exception is
     *                               later caught in the main method.
     */
    public static CustomerDetails readCustomerDetails
    (String filename, int customerID) throws FileNotFoundException {

        // Creating new file from file name in input as a string
        File file = new File(filename);
        Scanner input = null;
        int currentID = 0;
        String customerName = null;
        String customerEmail = null;
        ArrayList<String> purchaseHistory = new ArrayList<>();

        // The following try-catch block checks for RuntimeException, which
        // is thrown in the try block, and finally closes input.
        try {
            input = new Scanner(file);
            // The following while loop loops through each line until it
            // gets to the line which is to be read based on the customer
            // ID.
            while (currentID < customerID - 1) {
                if (input.hasNextLine()) {
                    currentID++;
                    input.nextLine();
                } else {
                    throw new RuntimeException();
                    // Here RuntimeException is thrown if the customerID
                    // is bigger than the total number of customers.
                }
            }
            String[] detailsOfCustomer = input.nextLine().split(",");
            customerName = detailsOfCustomer[1].trim(); // gets rid of whitespace
            customerEmail = detailsOfCustomer[2].trim();
            // The following for loop adds the purchase items into
            // the purchaseHistory arrayList.
            for (int i = 3; i < detailsOfCustomer.length; ++i) {
                purchaseHistory.add(detailsOfCustomer[i]);
            }
        } catch (RuntimeException e) {
            return null;
        } finally {
            if (input != null) input.close();
        }
        return new CustomerDetails(customerID, customerName, customerEmail, purchaseHistory);
    }

    /**
     * This method reads the data in filename and counts the number of lines, because
     * each line has one customer. Hence, this method counts the number of customers.
     * <p>
     * Example: Let's consider a file "data.txt" has
     * 1,John Doe,john.doe@email.com,Item1,Item2,Item3
     * 2,Jane Smith,jane.smith@email.com,Item2,Item4,Item5
     * This method would return 2.
     *
     * @param filename The name of the file to read from
     * @return The number of customers (the number of lines in the file)
     */
    public static int numberOfCustomers(String filename) {
        int result = 0;
        File file = new File(filename);
        Scanner input = null;
        // The following try-catch block checks for
        // FileNotFoundException, which could be thrown if
        // the file from which data is meant to be read from
        // does not exist.
        try {
            input = new Scanner(file);
            // The following while loop checks adds 1 to result
            // everytime it sees there is a new line, as each line
            // has data about different customers.
            while (input.hasNextLine()) {
                result++;
                input.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("readFile FileNotFoundException: " + e.getMessage());
        } finally {
            if (input != null) input.close();
        }
        return result;
    }

    /**
     * This method updates an attribute of the customer and then prints all
     * the customer's attributes into the file filename. It takes in
     * variable promptedCustomer, which is of the datatype CustomerDetails.
     * It also takes in userAction, which determines what attribute is to be
     * updated, and it takes in userInput, which determines what the attribute
     * should be changed to. Then, it returns true if the file is successfully
     * created, false otherwise.
     * <p>
     * Example: If filename is output.txt, userAction is 1, userInput is Johnny.
     * Let us assume that promptedCustomer has attributes 2, Jade, jade@gmail.com,
     * Item1, Item3. In this case, the name of the customer is changed from
     * Jade to Johnny and then "2,Johnny,jade@gmail.com,Item1,Item3" is printed
     * to the file 'output.txt'
     *
     * @param filename         The name of the file to write to.
     * @param promptedCustomer The details of the customer
     * @param userAction       specifies what attribute is to be changed
     * @param userInput        specified what the attribute is to be changed to
     * @return true if the file was created, false otherwise.
     */
    public static boolean writeCustomerDetails
    (String filename, CustomerDetails promptedCustomer, int userAction, String userInput) {
        PrintWriter writer = null;
        // The following try-catch block checks for
        // FileNotFoundException, which could be thrown if
        // the file to which the content must be printed
        // does not exist
        try {
            writer = new PrintWriter(filename);
            // if userAction is 1, it means the user wants to change
            // the name of the customer
            if (userAction == 1) {
                promptedCustomer.customerName = userInput;
            } else if (userAction == 2) {
                promptedCustomer.customerEmail = userInput;
                // If the userAction is 2, it means the user wants to change
                // the email of the customer
            } else if (userAction == 3) {
                promptedCustomer.purchaseHistory.add(userInput);
                // If the userAction is 3, it means the user wants to add an
                // item to the purchase history of customer
            }
            writer.print(promptedCustomer.customerID + ",");
            writer.print(promptedCustomer.customerName + ",");
            writer.print(promptedCustomer.customerEmail + ",");
            // The following for loop loops through purchaseHistory of the
            // user, and prints it into the file as a String.
            for (int i = 0; i < promptedCustomer.purchaseHistory.size(); ++i) {
                if (i == promptedCustomer.purchaseHistory.size() - 1) {
                    writer.print(promptedCustomer.purchaseHistory.get(i));
                } else {
                    writer.print(promptedCustomer.purchaseHistory.get(i) + ",");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("writeFile FileNotFoundException: " + e.getMessage());
            return false;
        } finally {
            if (writer != null) writer.close();
        }
        return true;
    }


    /**
     * This main method takes in user input for the names of both input and output
     * files, and it also takes in user input for userAction and userInput which are
     * used in the write file method. It also checks for invalid inputs in every step
     * where invalid inputs are possible, and deals with the invalid input.
     * <p>
     * Example: if user enters 4, test.txt, out.txt, and 2; customerID would be 2,
     * input file name would be test.txt, output file name would be out.txt, and
     * the attribute to be changed would be email.
     *
     * @param args unused
     */
    public static void main(String[] args) {


        Scanner scnr = new Scanner(System.in); // User input Scanner
        boolean endProgram = false;
        int customerID = 0;

        System.out.println("Enter a customerID: ");
        // The following if-else statements check whether
        // the entered value is an integer or not, if it is not,
        // the rest of the code does not run.
        if (scnr.hasNextInt()) {
            customerID = scnr.nextInt();
        } else {
            endProgram = true;
            System.out.println("Not an integer, program is ending.");
        }

        if (!endProgram) {
            System.out.println("Enter the name of input file: ");
            String inputFilename = scnr.next();
            // the following try-catch block checks for FileNotFoundException
            // as it may get thrown whenever the code tries to access
            // a file that does not exist.
            try {
                CustomerDetails promptedCustomer = readCustomerDetails(inputFilename, customerID);

                System.out.println("Enter the name of output file: ");
                String outputFilename = scnr.next();

                System.out.println("Choose an action to perform: ");
                System.out.println("1) Change name of the customer");
                System.out.println("2) Change email of the customer");
                System.out.println("3) Add an item in purchase history of customer");

                boolean successfullyWritten = false;

                // The following if statement checks whether the user
                // input is an integer or not.
                if (scnr.hasNextInt()) {
                    int userAction = scnr.nextInt();
                    // The following if statement checks whether the user
                    // input is between 1 (inclusive) and 3 (inclusive) or
                    // not.
                    if (userAction >= 1 && userAction <= 3) {
                        if (promptedCustomer == null) {
                            System.out.println("Customer ID " +
                                    "index out of bounds, program is ending.");
                        } else {
                            successfullyWritten = writeCustomerDetails
                                    (outputFilename, promptedCustomer, userAction, scnr.next());
                        }
                    } else {
                        System.out.println("Invalid input, program is ending.");
                    }
                } else {
                    System.out.println("Invalid input, program is ending.");
                }

                // The following if statement tells the user if the updated details
                // have been printed to the file or not.
                if (successfullyWritten) {
                    System.out.println("Updated Customer details have been printed to the file");
                } else {
                    System.out.println("Updated Customer details " +
                            "have not been printed to the file");
                }
            } catch (FileNotFoundException e) {
                System.out.println("readFile FileNotFoundException: " + e.getMessage());
                System.out.println("Updated customer details have not been printed to the file");
            }

        }

    }
}
