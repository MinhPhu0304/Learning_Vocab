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
    private final int NUMBER_WORDS_TO_LEARN; // This variable stores how much question that the user wants to learn

    private LinkedList<Word> wordsToLearn;
    private ArrayList<FillInTheBlankQuestion> questionList; //This is the question list which stores the questions that are randomly selected from the wordlist
    
    public LearningMode(int numberWordsToLearn){
        kb = new Scanner(System.in);
        NUMBER_WORDS_TO_LEARN = numberWordsToLearn;
        questionList = new ArrayList<>();
        wordsToLearn = new LinkedList<>();
        this.generateQuestions();
    }
    
    
    public void startLearning(){
        
        Iterator iterator = wordsToLearn.iterator();
        
        boolean userFinishedLearning = false;
        
        do{
            //I will finish this
            //Dont make it messy 
            //Please finish the start Small Test method
            
            
        }while(!userFinishedLearning);
        
    }
    
    /**
     * This will be used to see how many words user learn, After user learn all the words.
     */
    private void startSmallTest(){
        
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
    
    //Main is for testing prurposes will use later
    public static void main(String[] args){
        
        //Each time user choose learning mode we will have to contruct new 
        //Learning mode from random words
        LearningMode learn = new LearningMode(5);
        
        learn.startLearning();
    }
}
