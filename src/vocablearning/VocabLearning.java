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
        Scanner kb = new Scanner(System.in);
        
        //Start Menu asking for which mode the user wants to use
        // User input for the menu, seet to 0 as that is a valid condition for the following loop 
        int userInput = 0; 
        
        while(userInput != EXIT) 
        {
                       
            userInput = getUserChoice(kb);
            
            //No need for default since getUserChoice already include error handling
            switch (userInput) {
                case TESTING_MODE:
                    
                    testVocab = new TestingMode(WORD_LIST.getAvailableWord());
                    testVocab.startTest();
                    System.out.println("\nYou have answered "+testVocab.getScore() + " out of 20 questions");
                    
                    break;
                case LEARNING_MODE:
                    
                    LearningMode learningMode = new LearningMode(5);//testing only
                    learningMode.startLearning();
                    break;
                case EXIT:
                    System.out.println("Exiting Program...");
                    System.exit(0);
            }
        }
        
    }
    
    public static int getUserChoice(Scanner kb){
        
        boolean userChoiceValid = false;//assumption is made here since user has not typed in anything yet
        String userEnter; //What user enter may not be valid
        int userChoice = 0;
        do {
            System.out.println("Menu");
            System.out.println("1) Testing Mode");
            System.out.println("2) Learning Mode");
            System.out.println("3) Exit");
            System.out.print("Enter your choice:");
            
            userEnter = kb.nextLine();
            boolean userChoiceOutOfRange;
            try {
                userChoice = Integer.parseInt(userEnter);
                userChoiceOutOfRange = userChoice <1 || userChoice > 3;
                
                if(userChoiceOutOfRange) throw new Exception("Error: The choice is out range");
                
                userChoiceValid = true;
                
            }catch(NumberFormatException e){
                System.out.println("\nError: Please enter a number instead\n\n");
            }catch(Exception e){
                //Number format exception message is more common
                if(!(e instanceof NumberFormatException)){
                    System.out.println("\n"+e.getMessage() + "\n\n");
                }
            }
            
            
        }while(!userChoiceValid);
        
        return userChoice;
    }
    
}
