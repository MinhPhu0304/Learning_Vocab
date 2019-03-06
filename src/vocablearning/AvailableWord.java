/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vocablearning;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *  This class will take care of the text file input and output
 *  It will create a list of available words from text file
 * @author Minh
 */
public class AvailableWord {
    
    private final String availableWordFileURL = "./AvailableWord.txt";
    private ArrayList<Word> availableWord;
    
    
    public AvailableWord(){
        
        availableWord = new ArrayList<>();
        File fileURLToRead = new File(availableWordFileURL);
        try{
            //The default of the file reader is UTF-8
            //But UTF-8 does not include all German alphabet.
            //From the souce on Stackoverflow the right encoding is ISO-8859-1
            //Links to explain more about the encoding:  https://stackoverflow.com/questions/7048745/what-is-the-difference-between-utf-8-and-iso-8859-1
            BufferedReader bufferedFile = new BufferedReader(
                                          new InputStreamReader(
                                          new FileInputStream(fileURLToRead),"ISO-8859-1"));
            readInAvailableWordList(bufferedFile);
        }
        catch(FileNotFoundException error){
            System.err.println("File not found,please check the file url");
            System.err.println(error);
        }
        catch (IOException exception){
            //Since FileNotFoundException has been catched so we do not need to 
            // reprint the exception again
            if( !(exception instanceof FileNotFoundException)){
                System.err.println(exception);
            }
        }
    }
    
    private void readInAvailableWordList(BufferedReader buffer) throws IOException{
        
        String stringRead = "";
        if(buffer.ready()){
            while(true){
                stringRead += buffer.readLine();
                if(buffer.read() == -1){
                    break;
                }
            }
            buffer.close();
        }
        constructWordList(stringRead);
    }
    
    private void constructWordList(String stringRead){
        char [] arrayOfWords = stringRead.toCharArray();
        
        for (char element : arrayOfWords){
            if(element == '[' || element == ']'){continue;}
            
            String word = "";
            String meaning = "";
            
            
            
        }
    }
    
    public ArrayList<Word> getAvailableWord(){
        return availableWord;
    }  
}
