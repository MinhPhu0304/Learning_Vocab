/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vocablearning;

/**
 *
 * @author Minh
 */
abstract public class Question {
    protected String question;//this will be based on the meaning of the word.
    protected String answer;  
    
    public Question(Word wordToConstructQuestion){
        this.answer = wordToConstructQuestion.getWord();
        this.question = wordToConstructQuestion.getMeaning();//Either the computer gave user the meaning then user 
                                //fill in the blank or user are given meaning then pick from the 
                                //available word choices;
    }
    
    protected abstract void generateQuestion();
    public abstract void printQuestion();
    public abstract UserAnswerResult checkUserAnswer(String userInput);
    
    public String toString(){
        return "This question has a the word: " + this.answer + " and it means "+ this.question;
    }
}
