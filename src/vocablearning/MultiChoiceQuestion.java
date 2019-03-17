/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vocablearning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Minh
 */
public class MultiChoiceQuestion extends Question {

    private final int NUMBER_CHOICES_PER_QUESTION = 4;
    private final String POSSIBLE_ANSWER = "ABCD";
    private final List<Word> wordList;
    
    private List<Word> questions;
    
    public MultiChoiceQuestion(Word wordToConstructQuestion, List<Word> wordList) {
        super(wordToConstructQuestion);
        this.wordList = wordList;
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
        
        questions = generateUniqueAnswers(wordList);
        
        System.out.println("What is the word for this definition: "+this.question);
        String letter;
        for(int i = 0; i < NUMBER_CHOICES_PER_QUESTION; i++) {
            letter = ""+(char)('A'+i);
            
            System.out.println(letter+") "+questions.get(i).word);
        }
    }
    
    private List<Word> generateUniqueAnswers(List<Word> wordList) {
        
        ArrayList<Word> questionsToPrint = new ArrayList<>(); // This ArrayList has the question which will be printed to to the console
        questionsToPrint.add(new Word(this.answer, this.question));
        ArrayList<Integer> availableIndices = new ArrayList<>(); // This ArrayList keeps track of the word list's indices that haven't been used yet
        
        for(int i =0; i < wordList.size();i++) { // Populating th arryList with all the indices of the the word list
            availableIndices.add(i);
        }
        Collections.shuffle(availableIndices); // Shuffling the indices so they are random
        

        // This loop populates the QuestionToPrint arrayList with unique word from the word list 
        //  so there will not be duplicates
        for(int i = 0; i < NUMBER_CHOICES_PER_QUESTION - 1; i++) 
        {
            int tempIndex = availableIndices.get(0); // Saved the index from the ArrayList
            
            if(!this.question.equals(wordList.get(tempIndex).meaning)) { //Checking if the random word is the answer if it not is add it, if it is -1 from i so it will doinf it again
                questionsToPrint.add(wordList.get(tempIndex));
            }
            else {
                i--; // minus 1 from i so the loop will run again and grab another word but we will still remove the index so we won't accidently grab it again
            }
            availableIndices.remove((Integer)tempIndex); // Remove the index we just used so we won't use it again by accident
            
        }
        Collections.shuffle(questionsToPrint); // Shuffling so that the right answer doesn't always appear first
        return questionsToPrint;
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
        
        boolean userAnswerLengthCorrect = userInput.length() == 1;
        boolean userAnswerValid = userAnswerLengthCorrect && POSSIBLE_ANSWER.contains(userInput.toUpperCase());
        
        if(userAnswerValid){
            int index = (int)userInput.toUpperCase().charAt(0)-65; //The letter the user entered is converted to an int i.e 'A' = 65 so minus 65 = 0 
            if(questions.get(index).getWord().equals(this.answer)) { //Comparing the number that the user selected and comparing it the question's answer if they are the same than return UserCorrect
                return UserAnswerResult.UserCorrect;         
            }
            else {
                return UserAnswerResult.UserIncorrect;
            }
        }
        
        return UserAnswerResult.MultiChoiceOutOfRange;
    }
}
