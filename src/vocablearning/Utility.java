/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vocablearning;

import java.util.Scanner;

/**
 *
 * @author Minh
 */
public class Utility {
    
    /**
     * This method will return user input and perform error checking which will
     * make sure user enter number within range, This is used to reduced repeated code
     * @param kb
     * @param lowerLimit
     * @param upperLimit
     * @param repeatedString will print out each time when user input is not correct, to remind user 
     *                       about available option
     * @return The correct number within the range.
     */
    public static int getUserInputOfNumberOnly(Scanner kb, int lowerLimit, int upperLimit, String repeatedString) {
        boolean userInputCorrect = false;
        String userEnterInput;
        int userChoice = 0; //When the loop finish, it guarantee to have valid input

        do {
            System.out.println("\n" + repeatedString);
            System.out.print("Enter your choice here: ");
            userEnterInput = kb.nextLine();

            try {

                userChoice = Integer.parseInt(userEnterInput);

                if (userChoice < lowerLimit || userChoice > upperLimit) {
                    throw new Exception("Out Of range, please enter number within range provided\n");
                }
                userInputCorrect = true;

            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number instead\n");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        } while (!userInputCorrect);

        return userChoice;
    }
    
}
