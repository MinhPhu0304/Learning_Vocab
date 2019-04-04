/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vocablearning;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Minh
 */
public class LearningMode {
    private final Scanner kb;
    private final int NUMBER_WORDS_TO_LEARN; // This variable stores how much question that the user wants to learn
    private final int NUMBER_TYPING_REPEAT_TO_REMEBER = 5;
    private final int numberEmptyLineToPrint = 5;//making user think this is a new screen
    private final LinkedList<Word> wordsToLearn;
    private final ArrayList<FillInTheBlankQuestion> questionList; //This is the question list which stores the questions that are randomly selected from the wordlist
    private ArrayList<User> userList;
    private User currentUser;
    private UserListGenerator userDatabaseIO;
    public static final AvailableWord LEARNING_MODE_WORD_LIST = new AvailableWord();
    private int score = 0;
    
    public LearningMode() {
        kb = new Scanner(System.in);
        questionList = new ArrayList<>();
        wordsToLearn = new LinkedList<>();
        userList = new ArrayList<>();
        askUserName();
        //Checking if the user has already learned every word in the word list
        if (!(currentUser.getLastIndex() >= LEARNING_MODE_WORD_LIST.getAvailableWord().size())) {
            NUMBER_WORDS_TO_LEARN = getUserNumberWordsLearn();
            this.generateQuestions();
        }
        else {
            NUMBER_WORDS_TO_LEARN = currentUser.getLastIndex(); 
        }
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
        userDatabaseIO = new UserListGenerator(userList);
        //Saving the user list from file in to the program user list file
        userList = userDatabaseIO.getUserList();
        
        boolean isNameAlreadyExist = checkNameAlreadyExist();
        
        if (isNameAlreadyExist) {
            System.out.println("Loaded your previous results...");
        }
        else {
            System.out.println("Hi there, " + currentUser.getUserName());
            userList.add(currentUser);
        }
    }
    
    /**
     * Go through the entire user list and check the current user's username
     * with the list's username if there is already username then it returns true
     * @return a boolean which indicates whether the name already exist
     */
    private boolean checkNameAlreadyExist() {
        //Checking every user in the list and checking the name
        for (User i : userList) {
            String currentUserName = currentUser.getUserName();
            String currentElementUserName = i.getUserName();
            if (currentUserName.equalsIgnoreCase(currentElementUserName)) {
                //Assigning the previously saved user and saving inot a currentUser varaible
                currentUser = i;
                return true;
            }
        }
        //If the name does not exist then return false
        return false;
    }
    
    public void startLearning() {
        
        int currentWordToLearn = 1; //start from 1 to number repeat typing needed
        for(Word i: wordsToLearn){
            getUserTypeWordRepeatedly(i,currentWordToLearn++);
        }
        
        startSmallTest();
        currentUser.setLastIndex(currentUser.getLastIndex()+currentWordToLearn-1); //Minus 1 from this as we want to change it to an index(Index count from 0 onwards)
        userDatabaseIO.saveUserList(userList);
    }
    
    /**
     * This will be used to see how many words user learn, After user learn all the words.
     */
    private void startSmallTest() {
        
        printWords();
        String userInput;
        
        for (int currentQuestionNumber = 0; currentQuestionNumber < NUMBER_WORDS_TO_LEARN; currentQuestionNumber++) {
            //Saving the question the current question into a temporary variable
            FillInTheBlankQuestion currentQuestion = questionList.get(currentQuestionNumber);

            //Adding 1 to print out more user friendly question number instead of array index
            System.out.println("\nQuestion " + (currentQuestionNumber + 1));
            currentQuestion.printQuestion();
            userInput = kb.nextLine();
            
            if(Utility.containSpecialCharToConvert(userInput)) {
                userInput = Utility.convertSpecialChar(userInput);
            }
            
            compareUserInputWithAnswer(userInput,currentQuestion); 
        }
        System.out.println("You got "+score+" out of "+NUMBER_WORDS_TO_LEARN+" correct!");
    }
    
    /**
     * Check user input but it also print out if the user input is correct or not
     * @param userInput
     * @param currentQuestion 
     */
    private void compareUserInputWithAnswer(String userInput, Question currentQuestion) {
        
        UserAnswerResult checkAnswer = currentQuestion.checkUserAnswer(userInput);
        
        if (checkAnswer == UserAnswerResult.UserCorrect) {
            System.out.println("That is correct");
            score++;
        } else {
            System.out.println("That is incorrect the answer was " + currentQuestion.answer);
        }
    }
    
    /**
     * Repeating typing a word will make a person remember a word and it meanings
     * Therefore, it is also a good way to learn vocabulary
     * @param thisWord 
     * @param currentWordNumber
     */
    private void getUserTypeWordRepeatedly(Word thisWord, int currentWordNumber) {
        
        String userTyping;
        String currentWord =  thisWord.word;
        
        System.out.println("\nWord number " +  currentWordNumber + " :");
        System.out.println("The word is " + thisWord.word);
        System.out.println("The word means " + thisWord.meaning);
        
        Utility.printTypingGuide();
        System.out.println("\nType the word out "+NUMBER_TYPING_REPEAT_TO_REMEBER+" times:\n");
        for(int i =0; i < NUMBER_TYPING_REPEAT_TO_REMEBER; i++) {
            System.out.print("Enter the word:");
            
            userTyping = kb.nextLine();
            if(Utility.containSpecialCharToConvert(userTyping)) {
                userTyping = Utility.convertSpecialChar(userTyping);
            }
            
            if(!userTyping.equalsIgnoreCase(currentWord)){
                System.out.println("Wrong word typed");
                --i;//Decrease this enumeration since it is an invalid input
            }
        }
    }

    private void printWords() {
        System.out.println("Try to remember the following words, you will be tested on them:\n");

        for (int i = 0; i < NUMBER_WORDS_TO_LEARN; i++) {
            System.out.println(wordsToLearn.get(i).word + " : " + wordsToLearn.get(i).meaning + " in english");
        }

        //In Netbeans for window this short cut will work. Otherwise it will only print new line
        System.out.println("\nPress \"CTRL\" + \"L\" and then press \"Enter\" to continue...");
        kb.nextLine();

        //Clearing the console
        //Could'nt find a way to clear the console properly so just ran for loop printing out new lines
        for (int i = 0; i < numberEmptyLineToPrint; i++) {
            System.out.println("\n");
        }
    }
    
    /**
     * This method generates the question for the startSmallTest method
     */
    private void generateQuestions() {
        
        AvailableWord availableWords = LearningMode.LEARNING_MODE_WORD_LIST;

        //Getting the wordList from the AvailableWord object
        ArrayList<Word> wordList = availableWords.getAvailableWord();

        for (int i = 0; i < NUMBER_WORDS_TO_LEARN; i++) {
            //Adding the word from the list which the user has learned up to based on the lastIndex variable
            wordsToLearn.add(wordList.get(i+currentUser.getLastIndex()));

            //Adding the question we selected from the word list
            questionList.add(new FillInTheBlankQuestion(wordList.get(i+currentUser.getLastIndex())));
        }
    }
    
    private int getUserNumberWordsLearn() {
        //Bad chaining method here
        int numberWordsAvailable = LEARNING_MODE_WORD_LIST.getAvailableWord().size()-currentUser.getLastIndex(); //Subtracting the user's last index from the word list size
        String promptMessage = "Hi, " + currentUser.getUserName() + ".\nHow many words to you want to learn today";
        promptMessage += "( maximum number is " + numberWordsAvailable + " )";

        return Utility.getUserInputOfNumberOnly(kb, 0, numberWordsAvailable, promptMessage);
    }

    public int getCurrentUserLastIndex() {
        return currentUser.getLastIndex();
    }
    
}
