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
public class TestVocab {
    private int score;
    private ArrayList<Question> questionList;
    
    private final int MULTICHOICE = 0;
    private final int FILL_IN_THE_BLANK = 1;
    private final int NUMBER_QUESTION = 20;
    private final Random randomQuestionTypePicker;//This will be used to randomly choose between 
                                                  //multichoice or fill in the blank

    public TestVocab(List<Word> wordList){
        score = 0;
        randomQuestionTypePicker = new Random();
        questionList = new ArrayList<>();
        testingGenerateQuestion();//TESTING PURPOSE ONLY. DELETE WHEN FINISH
        generateQuestion(wordList);                         
    }
    
    public void startTest(){
        Scanner scan = new Scanner(System.in);
        String userInput = "";
        for(int i = 0; i < questionList.size(); i++) { // This loops prints out the entire list of qestion and scans for input from the user
            questionList.get(i).printQuestion();
           // score += (int)questionList.get(i).checkUserAnswer(userInput);
           // Reminder: Implement code that checks the user's input and adds to the user's score
        }
        
    }
    
    public int getScore(){
        return score;
    }
    
    @Override
    public String toString(){
        return "Test class";
    }

    private void testingGenerateQuestion() {
        for (int i = 0; i < NUMBER_QUESTION; i++){
        }
        
        for(Question i: questionList){
            System.out.println(i.toString());
        }
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
        
        for(int questionGenerate = 0; questionGenerate < NUMBER_QUESTION; questionGenerate++){
            
            int questionType = randomQuestionTypePicker.nextInt(2);
            int wordPick = randomQuestionTypePicker.nextInt(wordList.size());
            
            switch(questionType){
                case FILL_IN_THE_BLANK:
                    questionList.add(new FillInTheBlankQuestion(wordList.get(wordPick)));
                    break;
                case MULTICHOICE:
                    questionList.add(new MultiChoiceQuestion(wordList.get(wordPick)));
                    break;
            }
        }
    }
}
