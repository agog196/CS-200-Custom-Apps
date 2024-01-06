///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Adds or multiplies the binary numbers input by the user
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
//    and returns the end result.
// 2. What are the biggest challenges you encountered:
//    The biggest challenge I encountered is to prevent the code from crashing
//    when given the wrong type of input, since my code includes a lot of loops.
// 3. What did you learn from this assignment:
//    I learnt how to code using good practice, and I learnt how to implement
//    an attempts checker.
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////


import java.util.Scanner;

/**
 * This class is the entire program that prompts the user whether they want
 * the binary numbers to be added or multiplied. Then, it prompts the user for the number
 * of binary numbers they want to enter and then prompts the user for the binary
 * numbers, and then returns the sum of the binary numbers input by the user.
 * If the user enters invalid inputs five times, the program terminates. This limit
 * resets every input.
 *
 * @author Anish Gogineni
 */
public class H8CustomApp {

    /**
     * This method converts the provided decimal number into a binary number.
     * <p>
     * For example: Passing a decimal number 13 should result in the binary
     * number 1101
     *
     * @param decimalNumber decimal Number.
     * @return The corresponding binary number.
     */
    public static String convertDecimalToBinary(int decimalNumber) {
        String binaryNumber = "";
        int quotient = 1;
        int remainder;

        // The following while loop converts the decimalNumber into binary.
        // Every iteration, the remainder when decimalNumber is divided by 2. The
        // result is then added to the start of the string binaryNumber. This method
        // of calculating the binary value of a decimal number is called the
        // successive division method.
        while (quotient > 0) {
            quotient = decimalNumber / 2;
            remainder = decimalNumber % 2;
            decimalNumber /= 2;
            binaryNumber = String.valueOf(remainder) + binaryNumber;
            // In the above line of code, the string value of remainder is concatenated
            // to the start of the string 'binaryNumber'.
        }
        return binaryNumber;
    }

    /**
     * This method converts the provided binary number into a decimal number.
     * <p>
     * For example: Passing a binary number 1101 should result in the decimal
     * number 13
     *
     * @param binaryNumber binary Number.
     * @return The corresponding decimal number.
     */
    public static int convertBinaryToDecimal(String binaryNumber) {
        int i = binaryNumber.length() - 1;
        int exponentOfTwo = 0;
        int decimalNumber = 0;

        // The following while loop converts string binaryNumber into
        // a decimal integer. The loop iterates through the string from
        // the end to the start to calculate the decimal value. Every
        // iteration, the exponentOfTwo is increased by 1 and 2^(exponentOfTwo)
        // is added to decimalNumber.
        while (i >= 0) {
            if (binaryNumber.charAt(i) == '1') {
                decimalNumber += Math.pow(2, exponentOfTwo);
            }
            i--;
            exponentOfTwo++;
        }
        return decimalNumber;
    }

    /**
     * This method checks whether provided binary number is valid or not.
     * <p>
     * For example: Passing a binary number 1101 should return true and passing
     * a number 1234 should return false.
     *
     * @param binaryNumber binary number.
     * @return true or false depending on whether Binary number is valid.
     */
    public static Boolean isValidBinaryNumber(String binaryNumber) {

        // The following loop checks every character of the string binaryNumber
        // to verify whether if any of those characters are something other than
        // 1 and 0.
        for (int i = 0; i < binaryNumber.length(); i++) {
            if (binaryNumber.charAt(i) != '0' && binaryNumber.charAt(i) != '1') {
                return false;
            }
        }
        return true;
    }

    /**
     * This is the main method which contains all the code to output the sum
     * of all binary numbers entered.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        final int LIMIT_OF_WRONG_INPUTS = 5;
        boolean exitProgram = false;
        int amountOfBinaryNumbers = 0;
        int numberOfWrongInputs = 0;
        String operationOfBinaryNumbers = "";
        String input = null;

        System.out.println("Enter '+' for addition and '*' for multiplication: ");

        // The following loop is a while(true) loop, which keeps running until it
        // encounters a break statement. This loop is to check whether the user
        // input for the operation to perform on the binary numbers is valid or not.
        while (true) {
            if (scnr.hasNext()) {
                input = scnr.next();
                // The following if and else statements check whether the input entered is
                // valid or not.
                if (!(input.equals("+") || input.equals("*"))) {
                    numberOfWrongInputs++;
                    // The following if and else statements check whether invalid input
                    // has been entered five times so far or not, as the limit for invalid
                    // inputs is five. The limit resets every input.
                    if (numberOfWrongInputs >= LIMIT_OF_WRONG_INPUTS) {
                        System.out.println("Too many invalid inputs, program is ending.");
                        exitProgram = true;
                        scnr.close();
                        break;
                    } else {
                        System.out.println("Invalid input, try again: ");
                    }
                } else {
                    operationOfBinaryNumbers = input;
                    break;
                }
            } else {
                System.out.println("No input, program is ending.");
                exitProgram = true;
                break;
            }
        }

        if(!exitProgram) {
            numberOfWrongInputs = 0;
            if (operationOfBinaryNumbers.equals("+")) {
                System.out.println("Enter the number of binary numbers you want to add: ");
            }
            else {
                System.out.println("Enter the number of binary numbers you want to multiply: ");
            }
            // The following loop is a while(true) loop, which keeps running until it
            // encounters a break statement.
            while (true) {
                // The following if and else statements check whether the input entered is
                // valid or not.
                if (!scnr.hasNextInt()) {
                    numberOfWrongInputs++;
                    // The following if and else statements check whether invalid input
                    // has been entered five times so far or not, as the limit for invalid
                    // inputs is five. The limit resets every input.
                    if (numberOfWrongInputs >= LIMIT_OF_WRONG_INPUTS) {
                        System.out.println("Too many invalid inputs, program is ending.");
                        exitProgram = true;
                        scnr.close();
                        break;
                    } else {
                        System.out.println("Invalid input, try again: ");
                        scnr.next();
                    }
                } else {
                    amountOfBinaryNumbers = scnr.nextInt();
                    if (amountOfBinaryNumbers < 0) {
                        numberOfWrongInputs++;
                        if (numberOfWrongInputs >= LIMIT_OF_WRONG_INPUTS) {
                            System.out.println("Too many invalid inputs, program is ending.");
                            exitProgram = true;
                            scnr.close();
                            break;
                        } else {
                            System.out.println("Invalid input, try again: ");
                        }
                    } else {
                        break;
                    }
                }
            }

        }
        int finalDecimalNumber = 0;
        if (operationOfBinaryNumbers.equals("*")) {
            finalDecimalNumber = 1;
        }

        // The following for loop is to take input of Binary numbers the number of times
        // mentioned by the user. After taking input, the binary number is converted to
        // decimal number and added to the finalDecimalNumber.
        for (int i = 0; i < amountOfBinaryNumbers; i++) {
            // The following if statement checks whether exitProgram is true or not.
            // If exitProgram is true, it means the number of invalid inputs exceeded
            // the maximum invalid inputs allowed.
            if (!exitProgram) {
                numberOfWrongInputs = 0;
                System.out.println("Enter Binary number " + (i + 1));
                String binaryNumber = "";
                while (binaryNumber.equals("")) {
                    binaryNumber = scnr.nextLine();
                    if (!isValidBinaryNumber(binaryNumber)) {
                        binaryNumber = "";
                        numberOfWrongInputs++;
                        if (numberOfWrongInputs >= LIMIT_OF_WRONG_INPUTS) {
                            System.out.println("Too many invalid inputs, program is ending.");
                            exitProgram = true;
                            scnr.close();
                            break;
                        } else {
                            System.out.println("Invalid input, try again ");
                        }
                    }
                }
                if (operationOfBinaryNumbers.equals("+")) {
                    finalDecimalNumber += convertBinaryToDecimal(binaryNumber);
                } else {
                    finalDecimalNumber *= convertBinaryToDecimal(binaryNumber);
                }
            }
        }
        // The following if statement checks whether exitProgram is true or not.
        // If exitProgram is true, it means the number of invalid inputs exceeded
        // the maximum invalid inputs allowed.
        if (!exitProgram) {
            if (amountOfBinaryNumbers == 0) {
                System.out.println("Final Result: " + 0);
            } else {
                String finalBinaryNumber = convertDecimalToBinary(finalDecimalNumber);
                System.out.println("Final Result: " + finalBinaryNumber);
            }
        }
    }

}
