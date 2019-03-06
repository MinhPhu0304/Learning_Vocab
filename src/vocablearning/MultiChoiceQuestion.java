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
     * If-else statement which will make the code longer and less readable.
     */
    @Override
    public void printQuestion(){
        
    }
    
    @Override
    public String toString(){
        return "Multichoice question";
    }

    /**
     *  Checking user input but handling user input will be taken by the parent class
     *  since the scanner class will be initialized there
     * @param userInput
     * @return A enum type to say if user's answer correct or incorrect or the
     *  answer is out of range for the multi-choice
     */
    @Override
    public UserAnswerResult checkUserAnswer(String userInput){
        return UserAnswerResult.UserCorrect;
        //NOT IMPLEMENTED YET
    }
    
}
