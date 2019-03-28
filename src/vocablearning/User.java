/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vocablearning;

/**
 *
 * @author Millan
 */
public class User 
{
    public String userName;
    public int lastIndex;
    
    public User(String userName, int lastIndex)
    {
        this.userName = userName;
        this.lastIndex = lastIndex;
    }
    
    @Override
    public String toString()
    {
        return "User: "+userName;
    }
    
    public String getUserName() {
        return userName;
    }

    public int getLastIndex() {
        return lastIndex;
    }
    
    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }
    
}
