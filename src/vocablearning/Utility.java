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
    
    public static final char CHARACTER_Ä_EQUIVALENT = '[';
    public static final char CHARACTER_Ö_EQUIVALENT = ']';
    public static final char CHARACTER_Ü_EQUIVALENT = ';';
    
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
    
    /**
     * Utility method to convert symbol to special German character
     * SO user don't have to manually reconfigure keyboard language.
     * @param input
     * @return converted string
     */
    public static String convertSpecialChar(String input) {
        String convertedString;

        convertedString = input.replace(CHARACTER_Ä_EQUIVALENT, 'Ä');
        convertedString = convertedString.replace(CHARACTER_Ö_EQUIVALENT, 'Ö');
        convertedString = convertedString.replace(CHARACTER_Ü_EQUIVALENT, 'Ü');

        return convertedString;
    }
    
    /**
     * Check if the string input have special char to convert to special German letter
     * @param userInput
     * @return 
     */
    public static boolean containSpecialCharToConvert(String userInput) {

        boolean contain_Ä_symbol = userInput.contains(Character.toString(CHARACTER_Ä_EQUIVALENT));
        boolean contain_Ö_symbol = userInput.contains(Character.toString(CHARACTER_Ö_EQUIVALENT));
        boolean cotain_Ü_symbol = userInput.contains(Character.toString(CHARACTER_Ü_EQUIVALENT));

        return contain_Ä_symbol || contain_Ö_symbol || cotain_Ü_symbol;
    }
}
