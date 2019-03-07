package vocablearning;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
        generateQuestion();                         
    }
    
    public void startTest(){
        
        
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

    private void generateQuestion() {
        
        for(int questionGenerate = 0; questionGenerate < NUMBER_QUESTION; questionGenerate++){
            int questionType = randomQuestionTypePicker.nextInt(2);
            
            switch(questionType){
                case FILL_IN_THE_BLANK:
                    break;
                case MULTICHOICE:
                    break;
            }
        }
    }
}
