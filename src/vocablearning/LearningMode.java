/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vocablearning;

import java.util.Scanner;

/**
 *
 * @author Minh
 */
public class LearningMode {
    
    private final Scanner kb;
    private final char USER_QUIT = 'q';
    private final char USER_CHOOSE_NEXT = 'n';
    private final char USER_CHOOSE_BACK = 'b';
    
    private int number_word_to_learn;
    
    public LearningMode(int numberWordToLearn){
        kb = new Scanner(System.in);
        
    }
    
    
    public void startLearning(){
        
    }
}
