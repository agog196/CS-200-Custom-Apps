///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Finding median of two unsorted Arrays.
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
///////////////////////////////// REFLECTION ///////////////////////////////////
//
// 1. Describe the problem you wrote the program to solve:
//    The program prompts the user to input the values into two Arrays (do
//    not have to be sorted) and checks if valid input is entered. The user gets
//    five attempts for each array to enter valid input. Then, the program
//    creates a bigger array and sorts the numbers in it to find the median.
// 2. Why did you choose arrays vs ArrayLists? In other words, what are the
//    differences and how did you take those into account?
//    For my program, the user is prompted to enter the number of elements in
//    each array, so I can use either arrays or arrayLists, and I chose arrays
//    because they are faster and simpler.
// 3. How did you decide which test cases to create?
//    I did the test cases of the most common types of cases possible in the
//    methods, and tried to include every possible type of scenario.
// 4. What did you learn from this assignment:
//    I learnt how to use try and catch block to check whether correct input has
//    been entered or not, and I learnt how to optimise my code by using methods
//    extensively. I also improved my skills in arrays and modifying them.
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is the entire program that prompts the user to input the values
 * into two Arrays (do not have to be sorted) and checks if valid input
 * is entered. The user gets five attempts for each array to enter valid
 * input. Then, the program creates a bigger array and sorts the numbers
 * in it to find the median and return it.
 *
 * @author Anish Gogineni
 */
public class H10CustomApp {

    /**
     * This method creates an array of numbers that are input by the user.
     * The size of the array is the first integer input by the user.
     * <p>
     * For example: Passing numbers 4 5 7 1 3 should result in the array
     * {5, 7, 1, 3}
     *
     * @param input The scanner object.
     * @return null if there is no input, array containing numbers that
     * were input by the user if there is input, throws InputMismatchException
     * if five invalid inputs are entered.
     */
    public static int[] createArray(Scanner input) {
        int arraySize;
        int numberOfWrongInputs = 0;
        // The following while loop checks for invalid user input for the
        // length of the array. The user has 5 attempts to enter the correct
        // input. Correct input is any integer greater than 0.
        while (true) {
            if (input.hasNext()) {
                if (input.hasNextInt()) {
                    int inputValue = input.nextInt();
                    if (inputValue > 0) {
                        arraySize = inputValue;
                        break;
                    } else {
                        if (numberOfWrongInputs < 4) {
                            System.out.println("Invalid input, please enter again: ");
                            numberOfWrongInputs++;
                        } else {
                            throw new InputMismatchException();
                            // Here, there is an exception thrown which is caught in the main
                            // method.
                        }
                    }
                } else {
                    if (numberOfWrongInputs < 4) {
                        System.out.println("Invalid input, please enter again: ");
                        numberOfWrongInputs++;
                        input.next();
                    } else {
                        throw new InputMismatchException();
                        // Here, there is an exception thrown which is caught in the main
                        // method.
                    }
                }
            } else {
                return null;
            }
        }

        int[] numList = new int[arraySize];


        // The following for loop iterates the number of times as the
        // parameter arraySize and inputs values from the user to
        // add elements into the array, while also checking for invalid
        // inputs and no input cases.
        for (int i = 0; i < arraySize; ++i) {
            System.out.println("Enter number " + (i + 1) + ": ");
            if (input.hasNext()) {
                if (input.hasNextInt()) {
                    numList[i] = input.nextInt();
                } else {
                    if (numberOfWrongInputs < 4) {
                        System.out.println("Invalid input, please enter again: ");
                        i--;
                        numberOfWrongInputs++;
                    } else {
                        throw new InputMismatchException();
                        // Here, there is an exception thrown which is caught in the main
                        // method.
                    }
                    input.next();
                }
            } else {
                return null;
            }

        }

        return numList;
    }

    /**
     * This method creates a new array which has all elements of array1 and array2
     * and then sorts it using the bubble sort algorithm and then finds the median
     * of all the numbers in the array.
     * <p>
     * For example: Passing {1, 7, 3} and {4, 9, 2} should result in 3.5 as the
     * median of all the numbers in the two arrays is equal to 3.5
     *
     * @param array1 The first array.
     * @param array2 The second array.
     * @return The median of the numbers in array1 and array2.
     */
    public static double findMedianOfTwoArrays(int[] array1, int[] array2) {
        int size1 = array1.length;
        int size2 = array2.length;
        int finalSize = size1 + size2;
        int temp;
        double median;
        int[] finalArray = new int[size1 + size2];

        // The following two for loops add the elements of the two parameter
        // arrays into the final array.
        for (int i = 0; i < array1.length; ++i) {
            finalArray[i] = array1[i];
        }
        for (int i = 0; i < array2.length; ++i) {
            finalArray[size1 + i] = array2[i];
        }

        // The following nested for loop is the bubble sort algorithm, which sorts all
        // the numbers in the array.
        for (int i = 1; i < finalSize; ++i){
            for (int j = 0; j < finalSize - 1; ++j) {
                if (finalArray[j] > finalArray[j + 1]) {
                    temp = finalArray[j];
                    finalArray[j] = finalArray[j + 1];
                    finalArray[j + 1] = temp;
                }
            }
        }
        // If the final array has an even number of elements, the median is the mean
        // of the middle two elements.
        if (finalSize % 2 == 0) {
            median = (finalArray[finalSize / 2] + finalArray[finalSize / 2 - 1]) / 2.0;
        }
        // If the final array has an odd number of elements, the median is the middle
        // element.
        else {
            median = finalArray[(finalSize - 1) / 2];
        }
        return median;
    }

    /**
     * This is the main method which calls the other two methods to carry out
     * the program and also checks for the exception or null array cases in
     * the createArray method.
     *
     * @param args unused
     */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean runProgram = true;

        // The following try block contains the code to execute the actual program
        // and if runs into an exception at any point, it would be the InputMismatchException
        // which was thrown in the method in the case of way too many invalid inputs.
        try {
            System.out.println("Enter the size of your first array: ");
            // The following if-else statements check whether the input is valid or not.
            // The user has 5 tries to enter the correct input
            int[] array1 = createArray(input);
            if (array1 == null) {
                runProgram = false;
            }

            System.out.println("Enter the size of your second array: ");
            // The following if-else statements check whether the input is valid or not.
            // The user has 5 tries to enter the correct input
            int[] array2 = createArray(input);
            if (array2 == null) {
                runProgram = false;
            }

            if (runProgram) {
                double median = findMedianOfTwoArrays(array1, array2);
                System.out.println("The median of the two arrays is " + median);
            } else {
                System.out.println("No input entered, program is ending.");
            }
        }
        // The following catch block catches the InputMismatchException and
        // tells the user that too many invalid inputs have been entered
        // causing the program to end.
        catch (InputMismatchException e) {
            System.out.println("Too many invalid inputs, program is ending.");
        }
    }
}
