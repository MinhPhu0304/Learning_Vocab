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
public class FillInTheBlankQuestion extends Question {

    public FillInTheBlankQuestion(Word wordToConstructQuestion) {
        super(wordToConstructQuestion);
    }
    
    /**
     * This is an empty method because fill in the blank only requires 
     *  the word and its meaning.
     */
    @Override
    protected void generateQuestion() {}

    /**
     * Warning: this class only print out the question in a nice way,
     *  all userInput handling will be taken by the parent class.
     */
    @Override
    public void printQuestion() {
        System.out.println("Enter the word that match with the meaning of it: ");
        printCensoredAnswer();
        System.out.print(": " + super.question + "\n");
        System.out.print("Please enter the word: ");        
    }
    
    /**
     * Every character in the word will be printed as underscore
     *  This will help user to know how many characters in the word
     *  Also this will make the question looks nicer.
     */
    private void printCensoredAnswer(){
        char []answerInCharArray = super.answer.toCharArray();
        
        for(char i:answerInCharArray){
            System.out.print("_ ");
        }
    }

    @Override
    public UserAnswerResult checkUserAnswer(String userInput) {
        return UserAnswerResult.UserCorrect;
        //NOT IMPLEMENTED YET
    }
}
