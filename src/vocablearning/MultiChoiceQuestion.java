/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vocablearning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Minh
 */
public class MultiChoiceQuestion extends Question {

    Random randomIndexGenerator = new Random();
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
        List<Word> questions = generateUniqueAnswers(this, availableWords.getAvailableWord());
        
        System.out.println("What is the word for this definition: "+this.question);
        String letter = "A";
        for(int i = 0; i < 4; i++) {
            letter = ""+(char)('A'+i);
            System.out.println(letter+") "+questions.get(i).word);
        }
    }
    
    private List<Word> generateUniqueAnswers(MultiChoiceQuestion question, List<Word> wordList) {
        
        ArrayList<Word> questionsToPrint = new ArrayList<>(); // This ArrayList has the question which will be printed to to the console
        questionsToPrint.add(new Word(this.answer, this.question));
        for(int i =0; i < 3; i++) // There are going to be four potential answers 
        {
            int index = randomIndexGenerator.nextInt(wordList.size());
            
            if(!wordList.get(index).word.equals(question.answer)) // Checking if the random word is the same as test
            {
                // REMINDERL Add check that ensure that all words are unique in the list
                questionsToPrint.add(wordList.get(index));
            }
            else
                i--; // minus 1 from i so that it does another iteration to try get a unique answer
        }
        
        ArrayList<Integer> elementsToChange = new ArrayList<>(); // This ArrayList will contains all the indices for the element that are not unique and need to be changed
        for(int i = questionsToPrint.size()-1; i > 0; i--) {
            for(int j =0; j < 4; j++) {
                if(questionsToPrint.get(i).equals(questionsToPrint.get(j)) && !elementsToChange.contains(j) && !elementsToChange.contains(i) && i != j) { // Determine if it equals to any of the other indexes and if the index is not already in the ArrayList
                    elementsToChange.add(i);
                }
            }
        }
        // This next loop finds a new word and swaps it with the ones that are not unique and need to be removed
        for(int i =0; i < elementsToChange.size(); i++) {
            int index = randomIndexGenerator.nextInt(wordList.size()); // getting the index for a random word in the word list
            
            int tempIndex = elementsToChange.get(i); // Saving the index of the word that needs to switch out
            if(!questionsToPrint.get(tempIndex).equals(wordList.get(index)) && !wordList.get(index).word.equals(question.answer)) { // Checking if the random word is the same as the word we want to remove
                questionsToPrint.remove(questionsToPrint.get(tempIndex)); // Removing our duplicate word
                questionsToPrint.add(wordList.get(index)); //adding the unqiue new word
            }
            else
                i--; // If the new word is the same as our duplicate word we minus 1 from i so to the loop runs again and finds a new word
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
        return UserAnswerResult.UserCorrect;
        //NOT IMPLEMENTED YET
    }
    
}
