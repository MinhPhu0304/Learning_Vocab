/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vocablearning;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Minh
 */
public class LearningMode {
    
    private final Scanner kb;
    private final int NUMBER_WORDS_TO_LEARN; // This variable stores how much question that the user wants to learn

    private LinkedList<Word> wordsToLearn;
    private ArrayList<FillInTheBlankQuestion> questionList; //This is the question list which stores the questions that are randomly selected from the wordlist
    private ArrayList<User> userList;
    private User currentUser;
    
    
    public LearningMode(){
        kb = new Scanner(System.in);
        questionList = new ArrayList<>();
        wordsToLearn = new LinkedList<>();
        userList = new ArrayList<>();
        this.generateQuestions();
        askUserName();
        NUMBER_WORDS_TO_LEARN = getUserNumberWordsLearn();
    }
    
    public LearningMode(int numberWordsToLearn){
        kb = new Scanner(System.in);
        NUMBER_WORDS_TO_LEARN = numberWordsToLearn;
        questionList = new ArrayList<>();
        wordsToLearn = new LinkedList<>();
        userList = new ArrayList<>();
        this.generateQuestions();
        askUserName();
    }
    
    /**
     * This method takes in the user's input and then takes checks it with the existing users
     * If the username already exist then it loads that user's index
     */
    private void askUserName() {
        System.out.println("Please enter your username: ");
        String userName = kb.nextLine();

        currentUser = new User(userName, 0);
        
        //Creating a UserListGenerator which handle in the file input
        UserListGenerator userListGenerator = new UserListGenerator(userList);
        
        //Saving the user list from file in to the program user list file
        userList = userListGenerator.getUserList();
        
        boolean isNameAlreadyExist = checkNameAlreadyExist();
        
        if (isNameAlreadyExist) {
            System.out.println("Loaded your previous results...");
        }
        else {
            System.out.println("Hi there, " + currentUser.getUserName());
            userList.add(currentUser);
        }

        userListGenerator.saveUserList();
    }
    
    private boolean checkNameAlreadyExist(){
        
        for(User i: userList){
            
            String currentUserName = currentUser.getUserName();
            String currentElementUserName = i.getUserName();
            if(currentUserName.equalsIgnoreCase(currentElementUserName)){
                currentUser = i;
                return true;
            }
        }
        
        return false;
    }
    
    public void startLearning(){
        
        Iterator iterator = wordsToLearn.iterator();
       
        boolean userFinishedLearning = false;
        
        do{
            //I will finish this
            //Dont make it messy 
            //Please finish the start Small Test method
            
            
        }while(!userFinishedLearning);
        
        startSmallTest();
    }
    
    /**
     * This will be used to see how many words user learn, After user learn all the words.
     */
    private void startSmallTest(){
        
        printWords();
        String userInput;
        
        for (int currentQuestionNumber = 0; currentQuestionNumber < NUMBER_WORDS_TO_LEARN; currentQuestionNumber++) {
            //Saving the question the current question into a temporary variable
            FillInTheBlankQuestion currentQuestion = questionList.get(currentQuestionNumber); 
            
            //Adding 1 to print out more user friendly question number
            System.out.println("\nQuestion " + (currentQuestionNumber + 1));
            currentQuestion.printQuestion();
            userInput = kb.nextLine();

            UserAnswerResult checkAnswer = currentQuestion.checkUserAnswer(userInput);
            if (checkAnswer == UserAnswerResult.UserCorrect) {
                System.out.println("That is correct");
            } else {
                System.out.println("That is incorrect the answer was " + currentQuestion.answer);
            }
        }
    }

    private void printWords() {
        System.out.println("Try to remember the following words, you will be tested on them:\n");
        
        for (int i = 0; i < NUMBER_WORDS_TO_LEARN; i++) {
            System.out.println(wordsToLearn.get(i).word + " : " + wordsToLearn.get(i).meaning + " in english");
        }

        System.out.println("Press Enter to continue...");
        kb.nextLine();

        //Clearing the console
        //Could'nt find a way to clear the console properly so just ran for loop printing out new lines
        for (int i = 0; i < 5; i++) {
            System.out.println("\n");
        }

    }
    
    private void generateQuestions(){ 
        
        //Static variable to optimize performance from not reading file many times
        AvailableWord availableWords = VocabLearning.WORD_LIST; 
        
        //Getting the wordList from the AvailableWord object
        ArrayList<Word> wordList = availableWords.getAvailableWord();
        Collections.shuffle(wordList);
        
        for(int i =0; i < NUMBER_WORDS_TO_LEARN; i++)
        {
            
            //Shuffled word list which will determine the word we select from the word list
            wordsToLearn.add(wordList.get(i));
            
            //Adding the question we selected from the word list
            questionList.add(new FillInTheBlankQuestion(wordList.get(i)));
        }
    }
    
    private int promptUserNumberWordToLearn(){
        
        System.out.println("Hello " + currentUser.userName);
        System.out.println("How many words to you want to learn today: ");
        
        int numberWordsUserWillLearn = getUserNumberWordsLearn();
        
        return 0;
    }
    
    private int getUserNumberWordsLearn() {        
        
        //Bad chaining method here
        int numberWordsAvailable = VocabLearning.WORD_LIST.getAvailableWord().size();
        String promptMessage = "Hi, " + currentUser.getUserName() + ".\nHow many words to you want to learn today";
        promptMessage += "( maximum number is " + numberWordsAvailable+ " )" ;

        return Utility.getUserInputOfNumberOnly(kb, 0, numberWordsAvailable, promptMessage);
    }
    
    //Main is for testing prurposes will use later
    public static void main(String[] args){
        
        //Each time user choose learning mode we will have to contruct new 
        //Learning mode from random words
        LearningMode learn = new LearningMode();
        
        learn.startSmallTest();
    }

   
}
