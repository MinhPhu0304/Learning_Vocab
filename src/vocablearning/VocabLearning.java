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
        
        //Option user can choose
        final int TESTING_MODE = 1;
        final int LEARNING_MODE = 2;
        final int EXIT = 3;

        TestingMode testVocab;
        Scanner kb = new Scanner(System.in);
        
        //Start Menu asking for which mode the user wants to use
        // User input for the menu, seet to 0 as that is a valid condition for the following loop 
        int userInput = 0;

        while (userInput != EXIT) {

            userInput = getUserChoice(kb);

            //No need for default since getUserChoice already include error handling
            switch (userInput) {
                case TESTING_MODE:
                    
                    testVocab = new TestingMode(WORD_LIST.getAvailableWord());
                    testVocab.startTest();
                    System.out.println("\nYou have answered " + testVocab.getScore() + " out of 20 questions");
                    break;
                case LEARNING_MODE:
                    
                    LearningMode learningMode = new LearningMode();//testing only
                    learningMode.startLearning();
                    break;
                case EXIT:
                    
                    System.out.println("Exiting Program...");
                    System.exit(0);
            }
        }
    }
    
    public static int getUserChoice(Scanner kb) {
        String promptMessage = "Menu\n";
        promptMessage += "1) Testing Mode\n";
        promptMessage += "2) Learning Mode\n";
        promptMessage += "3) Exit\n";

        return Utility.getUserInputOfNumberOnly(kb, 1, 3, promptMessage);
    }
    
}
