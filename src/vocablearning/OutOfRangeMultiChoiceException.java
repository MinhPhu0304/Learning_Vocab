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
public class OutOfRangeMultiChoiceException extends Exception {
    
    
    public OutOfRangeMultiChoiceException(){}
    
    @Override
    public String getMessage(){
    
        return "Multi choice question out of range";
    }
}
