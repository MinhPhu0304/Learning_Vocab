/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vocablearning;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Minh
 */
public class LearningMode {
    
    private final Scanner kb;
    private final char USER_QUIT = 'q';
    private final char USER_CHOOSE_NEXT = 'n';
    private final char USER_CHOOSE_BACK = 'b';
    private LinkedList<Word> words;
    private final int NUMBER_WORDS_TO_LEARN; // This variable stores how much question that the user wants to learn
    private LinkedList<FillInTheBlankQuestion> questionList; //This is the question list which stores the questions that are randomly selected from the wordlist
    public LearningMode(int numberWordsToLearn)
    {
        kb = new Scanner(System.in);
        NUMBER_WORDS_TO_LEARN = numberWordsToLearn;
        questionList = new LinkedList<>();
    }
    
    public void startLearning()
    {
        //This is the for Loop that prints out the questions and take in input
        //Still have to implement the going back and forwards in the lists of question
        for(int i =0; i < NUMBER_WORDS_TO_LEARN; i++)
        {
            FillInTheBlankQuestion currentQuestion = questionList.get(i); // Getting the current question from the LinkedList which is at index of i
            currentQuestion.printQuestion();
            
            //Taking in user input
            String userInput = kb.nextLine(); 
            
            //Saving the result of the checkUserAnswer method so we can to check to see if the user was right
            UserAnswerResult checkAnswer = currentQuestion.checkUserAnswer(userInput);
            
            if(checkAnswer == UserAnswerResult.UserCorrect)
            {
                System.out.println("That is correct!");
            }
            else if(checkAnswer == UserAnswerResult.UserIncorrect)
            {
                System.out.println("That is incorrect the correct answer was "+currentQuestion.getAnswer());
            }
        }
    } 
    public void generateQuestions()
    {
        Random rand = new Random();
        //Getting the Available Word object which is a static variable in VocabLearning so we don't have to parse or create a new AvailableWord
        AvailableWord availableWords = VocabLearning.WORD_LIST; 
        
        //Getting the wordList from the AvailableWord object
        ArrayList<Word> wordList = availableWords.getAvailableWord();
        
        for(int i =0; i < NUMBER_WORDS_TO_LEARN; i++)
        {
            //Random Index which will determine the word we select from the word list
            int index = rand.nextInt(wordList.size());
            
            //Adding the question we selected from the word list
            FillInTheBlankQuestion questionToAdd = new FillInTheBlankQuestion(wordList.get(index));
            questionList.add(questionToAdd);
        }
    }
    
    //Main is for testing prurposes will use later
    public static void main(String[] args)
    {
        LearningMode learn = new LearningMode(5);
        
        learn.generateQuestions();
        learn.startLearning();
    }
}
