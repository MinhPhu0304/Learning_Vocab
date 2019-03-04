/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vocablearning;

import java.util.ArrayList;

/**
 *
 * @author Minh
 */
public class MultiChoiceQuestion extends Question {

    //REMEMBER TO ADD ARRAY_LIST OF AVAILABLE WORD
    public MultiChoiceQuestion(String word, String meaning) {
        super(word, meaning);
    }
    
    @Override
    protected void generateQuestion() {
        
    }
    
    /**
     * The reason we have this method is to print the question the suit the question type
     * rather than checking instance of the class every time we get question and answer
     * If we only get question and get answer in the parent class we have to use
     * If-else statement which will make the code longer and less readable
     */
    @Override
    public void printQuestion(){
        
    }
    
    /**
     * Comparing user Answer with the question answer
     *  It may throw exception for the higher class to deal with
     * @param userInput
     * @return true if the user is correct and vice versa
     * @exception User anwser is not within the range from A to D
     */
    public boolean checkUserAnswer(String userInput){
        return true; //Not implemented
    }
    
    @Override
    public String toString(){
        return "Multichoice question";
    }
    
}
