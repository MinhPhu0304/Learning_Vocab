package vocablearning;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
/**
 * @author Minh
 * Class is used for testing mode in the program
 * The number of question should be around 20 questions per test
 */
public class TestingMode {
    private int score;
    private final ArrayList<Question> questionList;//each test case should only have constant question
    private final int MULTICHOICE = 0;
    private final int FILL_IN_THE_BLANK = 1;
    private final int TOTAL_TYPE_OF_QUESTION = 2;
    private final int NUMBER_QUESTION = 20;
    private final Random randomQuestionTypePicker;//This will be used to randomly choose between 
                                                  //multichoice or fill in the blank
    public TestingMode(List<Word> wordList) {
        score = 0;
        randomQuestionTypePicker = new Random();
        questionList = new ArrayList<>();
        generateQuestion(wordList);
    }
    
    public void startTest() {
        Scanner keyboard = new Scanner(System.in);
        String userInput;
        int questionToPrint = 1; //we havent printed any yet

        for (Question currentQuestion : questionList) {

            System.out.println("\nQuestion " + questionToPrint++);
            currentQuestion.printQuestion();
            userInput = keyboard.nextLine();

            if (currentQuestion instanceof MultiChoiceQuestion) {
                //The reason we pass  keyboard object is to not waste memory and time to 
                // intialize another keyboard object
                checkUserAnswerMultiChoice(userInput, keyboard, (MultiChoiceQuestion) currentQuestion);
            } else {
                userInput = convertInputToSpecialChar(userInput);
                checkUserAnswerFillInTheBlank(userInput, currentQuestion);
            }
        }
    }
    
    /**
     * This method will perform change in the user Input if it detect user want
     *  to type Ä or Ö or Ü
     * User will type symbol '[' '[' or ';' to convert to those special character
     * If user does not type any special symbol like that it will just return the same
     * @param userInput
     * @return converted string or just the same string
     */
    private String convertInputToSpecialChar(String userInput) {

        if (Utility.containSpecialCharToConvert(userInput)) {
            String convertedInput = Utility.convertSpecialChar(userInput);
            System.out.println("We converted your word into: " + convertedInput);
            return convertedInput;
        }
        return userInput;
    }  
        
    private void checkUserAnswerFillInTheBlank(String userInput, Question currentQuestion) {

        switch (currentQuestion.checkUserAnswer(userInput)) {
            case UserCorrect:
                addPointForUser();
                break;
            case UserIncorrect:
                break;
        }
    }
    
    /**
     * This will check for user input in the multi choice and performing loop
     *  until user type the right question from the range
     * The reason to have 2 different methods for multi choice and fill in the blank
     *  is because the multi choice will have to perform a loop if necessary needed which
     *      could make the loop start test ugly and messy
     * @param userInput 
     */
    private void checkUserAnswerMultiChoice(String userInput, Scanner keyboard, MultiChoiceQuestion currentQuestion) {

        switch (currentQuestion.checkUserAnswer(userInput)) {
            case UserCorrect:
                addPointForUser();
                break;
            case UserIncorrect:
                break;
            case MultiChoiceOutOfRange:
                System.out.print("Your answer is invalid please enter again: ");
                String userNewAnswer = keyboard.nextLine();
                //recursively check for input again.
                checkUserAnswerMultiChoice(userNewAnswer, keyboard, currentQuestion);
                break;
        }
    }
    
    private void addPointForUser() {
        score++;
    }
    
    public int getScore() {
        return score;
    }
    
    @Override
    public String toString() {
        return "Test class";
    }

    /**
     * This class will only be called in the constructor of the class
     * The main reason is that user might want another test so we can not used the 
     *  same question before, the testVocab class can be initialized more than once depends
     *  on how many tests user wanna take, NOTE: we don't store the wordList as private field
     * The reason for not storing wordList for private field is because it will take more memory 
     *  and the wordList is needed only for constructing question, after that it will not be needed
     * @param wordList 
     */
    private void generateQuestion(List<Word> wordList) {

        Collections.shuffle(wordList);//calling more than once will affect performance. 
        //so it's best to call it before constructing testing question only

        for (int questionGenerate = 0; questionGenerate < NUMBER_QUESTION; questionGenerate++) {

            int questionType = randomQuestionTypePicker.nextInt(TOTAL_TYPE_OF_QUESTION);
            int wordPick = randomQuestionTypePicker.nextInt(wordList.size());

            ArrayList<Word> availableWord = VocabLearning.WORD_LIST.getAvailableWord();

            switch (questionType) {

                case FILL_IN_THE_BLANK:
                    questionList.add(new FillInTheBlankQuestion(availableWord.get(wordPick)));
                    break;

                case MULTICHOICE:
                    questionList.add(new MultiChoiceQuestion(availableWord.get(wordPick), availableWord));
                    break;
            }
        }
    }
}
