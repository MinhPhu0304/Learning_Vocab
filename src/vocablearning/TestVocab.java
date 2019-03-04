package vocablearning;
import java.util.ArrayList;
import java.util.Collections;
/**
 * @author Minh
 * Class is used for testing mode in the program
 * The number of question should be around 20 questions per test
 */
public class TestVocab {
    private int score;
    private ArrayList<Question> questionList;
    private final int NUMBER_QUESTION = 20;
    
    public TestVocab(){
        score = 0;
        questionList = new ArrayList<>();
        testingGenerateQuestion();//TESTING PURPOSE ONLY. DELETE WHEN FINISH
        __readingAvailableWordFromStorage();//this should only be called once
                                            //only used to set up a list of availableword
                                      
    }
    
    private void __readingAvailableWordFromStorage(){
        
        availableWordList = new ArrayList(40);//TESTING ONLY
    }
    
    private void generateQuestion(){
        
        Collections.shuffle(availableWordList);
        
    }
    
    public void startTest(){
    
        generateQuestion();
        
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
            questionList.add(new MultiChoiceQuestion("String","A String"));
        }
        
        for(Question i: questionList){
            System.out.println(i.toString());
        }
    }
}
