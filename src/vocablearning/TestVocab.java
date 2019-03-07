package vocablearning;
import java.util.ArrayList;
/**
 * @author Minh
 * Class is used for testing mode in the program
 * The number of question should be around 20 questions per test
 */
public class TestVocab {
    private int score;
    private ArrayList<Question> questionList;
    private final int NUMBER_QUESTION = 20;
    
    //This constructor should only be for development
    // To use it for real program this class has to take in a word List
    public TestVocab(){
        score = 0;
        questionList = new ArrayList<>();
        testingGenerateQuestion();//TESTING PURPOSE ONLY. DELETE WHEN FINISH
                                      
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
}
