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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is similar to the AvailableWord in which that it handles
 * the file input and output of the user data
 * @author Millan
 */
public class UserListGenerator {
    private ArrayList<User> userList;
    private final String userFile = "./UserList.txt";
    private final File fileURLToRead;
    
    public UserListGenerator(ArrayList<User> userList) {
        this.userList = userList;
        fileURLToRead = new File(userFile);
        readFile();
    }
    
    /**
     * This reads in the file and calls the readInInputFromFIle
     */
    private void readFile() {
        try {
            BufferedReader bufferedFile = new BufferedReader(
                                          new InputStreamReader(new FileInputStream(fileURLToRead)));

            readInInputFromFIle(bufferedFile);
        } catch (FileNotFoundException error) {
            System.err.println("File not found");
        } catch (IOException exception) {
            if (!(exception instanceof FileNotFoundException)) {
                System.err.println(exception.getMessage());
            }
        }
    }
    
    /**
     * This method reads in the input from the file as a string
     * @param buffer is the buffered reader 
     * @throws IOException 
     */
    private void readInInputFromFIle(BufferedReader buffer) throws IOException {
        String stringRead = "";
        String line;
        
        if (buffer.ready()) {
            while ((line = buffer.readLine()) != null) {
                stringRead += line;
            }
            buffer.close();
        }

        sortUserList(stringRead);
    }
    
    /**
     * This method adds the user to the program's users list
     * @param stringRead the string that was read in the file
     */
    private void sortUserList(String stringRead) {
        ArrayList<String> arrayListOfData = new ArrayList<>();
        StringTokenizer extractingEachWordAndMeaning = new StringTokenizer(stringRead.trim(), ",");

        while (extractingEachWordAndMeaning.hasMoreTokens()) {
            arrayListOfData.add(extractingEachWordAndMeaning.nextToken());
        }
        
        for (String i : arrayListOfData) {
            String[] userAndIndex = i.split(":");
            userList.add(new User(userAndIndex[0], Integer.parseInt(userAndIndex[1])));
        }
    }
    
    /**
     * This method outputs the program's userList to the UserList.txt file
     */
    public void saveUserList() {
        File userListFile = new File("./UserList.txt");
        
        try {
            PrintWriter writer = new PrintWriter(userListFile);

            for (int i = 0; i < userList.size() - 1; i++) {
                User user = userList.get(i);
                String outputToFile = user.getUserName() + ":" + user.getLastIndex() + ",\n";
                writer.print(outputToFile);
                writer.flush();
            }
            String outputToFile = userList.get(userList.size() - 1).getUserName() + ":" + userList.get(userList.size() - 1).getLastIndex();

            writer.print(outputToFile);
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(LearningMode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<User> getUserList() {
        return userList;
    }
    
}
