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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Staatsangehörigkeit");//testing printing German word
        TestVocab newTest = new TestVocab();
        AvailableWord wordList = new AvailableWord();
        
        
        
        //Start Menu asking for which modw the user wants to use
        int userInput = 0; // User input for the menu, seet to 0 as that is a valid condition for the following loop 
        
        while(userInput != 3) // This is the menu which is a while loop so the user can return to the main menu if they wish to switch modes
        {
            Scanner scan = new Scanner(System.in);
            System.out.println("Menu");
            System.out.println("1) Testing Mode");
            System.out.println("2) Multichoice");
            System.out.println("3) Exit");
            
            String tempInput = scan.nextLine();
            
             //Checking if the user enter characters(which are invalid input) or an integer
            if(!tempInput.isEmpty()) // Check if the string is not empty in order 
            {
                char tempChar = tempInput.charAt(0); //Creating a char variable for checking purposes
                if(!Character.isDigit(tempChar))
                {
                    userInput = 0; //Since the input is not a number set userInput to an invalid number so they will have to renter
                }
                else
                {
                    tempInput = ""+tempChar; //Make the user input the first number that they entered
                    userInput = Integer.parseInt(tempInput);
                }
            }
            else
            {
                userInput = 0;
            }
            
            switch (userInput) {
                case 1:
                    //Code for the testing mode will be implemented here
                    System.out.println("Testing Mode(Placeholer)\n");
                    break;
                case 2:
                    //This is where the multichoice queestion will go
                    System.out.println("Multi choice Mode(Placeholer)\n");
                    break;
                case 3:
                    System.out.println("Exiting Program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid Input! Please enter a valid input!\n");
                    break;
            }
          
        }
    }
    
}
