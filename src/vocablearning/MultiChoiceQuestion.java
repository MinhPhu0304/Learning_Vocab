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
    
    
    //REMEMBER TO ADD ARRAY_LIST OF AVAILABLE WORD
    public MultiChoiceQuestion(Word wordToConstructQuestion) {
        super(wordToConstructQuestion);
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
        
        AvailableWord availableWords = new AvailableWord();
        List<Word> questions = generateUniqueAnswers(availableWords.getAvailableWord());
        
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
        for(int i =0; i < NUMBER_CHOICES_PER_QUESTION - 1; i++) 
        {
            int tempIndex = availableIndices.get(i); // Saced the index from the Arra
            if(!wordList.get(tempIndex).word.equals(this.answer)) {
                questionsToPrint.add(wordList.get(tempIndex)); 
                availableIndices.remove(i); // Remove the index we just used so we won't use it again by accident
            }
            else { // This else will trigger when the word w got was the same as our question's word so we minnus 1 from i and shuffle againso it redo this iteration
                i--; 
                Collections.shuffle(availableIndices);
            }
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
        //NOT IMPLEMENTED YET
        return UserAnswerResult.UserCorrect;
    }
    
}
