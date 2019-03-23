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
public class VocabLearning {

    //Easier to get word from any class without passing value around
    public static final AvailableWord WORD_LIST = new AvailableWord();
    
    public static void main(String[] args) {
        
        final int TESTING_MODE = 1;
        final int LEARNING_MODE = 2;
        final int EXIT = 3;
        
        TestingMode testVocab;
        Scanner scan = new Scanner(System.in);
        
        //Start Menu asking for which mode the user wants to use
        // User input for the menu, seet to 0 as that is a valid condition for the following loop 
        int userInput = 0; 
        
        while(userInput != EXIT) 
        {
            System.out.println("Menu");
            System.out.println("1) Testing Mode");
            System.out.println("2) Learning Mode");
            System.out.println("3) Exit");
            
            String tempInput = scan.nextLine();
            
             //Checking if the user enter characters(which are invalid input) or an integer
            if (!tempInput.isEmpty()) {// Check if the string is not empty in order 
            
                boolean isUserInputValid = true; // This variable indicates whether the input is valid so tempInput can be converted in to integer safetly

                for (int characterIndex = 0; characterIndex < tempInput.length(); characterIndex++) {
                    if (!Character.isDigit(tempInput.charAt(characterIndex))) {
                        isUserInputValid = false;
                        userInput = 0; //Since the input is not a number set userInput to an invalid number so they will have to renter
                    }
                }

                if (isUserInputValid && tempInput.length() < 10) // checking if the number doesn't have any letter also check if the string length is less than 10(as there is no option tht require 10 numberic character) in order to avoid parse a number to big which will cause an exception 
                {
                    userInput = Integer.parseInt(tempInput);
                } else {
                    userInput = 0;
                }
            }
            
            switch (userInput) {
                case TESTING_MODE:
                    
                    testVocab = new TestingMode(WORD_LIST.getAvailableWord());
                    testVocab.startTest();
                    System.out.println("\nYou have answered "+testVocab.getScore() + " out of 20 questions");
                    break;
                case LEARNING_MODE:
                    
                    LearningMode learningMode = new LearningMode(5);//testing only
                    break;
                case EXIT:
                    System.out.println("Exiting Program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid Input! Please enter a valid input!\n");
                    break;
            }
          
        }
        
    }
    
}
