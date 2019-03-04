/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vocablearning;

import java.util.Random;

/**
 *
 * @author Minh
 */
abstract public class Question {
    private String question;//this will be based on the meaning of the word.
    private String answer;  
    
    public Question(String word,String meaning){
        this.answer = word;
        this.question = meaning;//Either the computer gave user the meaning then user 
                                //fill in the blank or user are given meaning then pick from the 
                                //available word choices;
    }
    
    protected abstract void generateQuestion();
    public abstract void printQuestion();
    
    public String toString(){
        return "This question has a the word: " + this.answer + " and it means "+ this.question;
    }
}
