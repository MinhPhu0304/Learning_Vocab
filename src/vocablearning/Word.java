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
public class Word {
    private final String word, meaning;
    
    
    public Word(String word,String meaning){
        this.word = word;
        this.meaning = meaning;
    }
    
    public String getWord(){
        return this.word;
    }
        
    public String getMeaning(){
        return this.meaning;
    }     
    
    @Override
    public String toString(){
        return "The word is " +  this.word;
    }
}
