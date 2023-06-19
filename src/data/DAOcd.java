
package data;

import java.io.IOException;


public interface DAOcd {
    public void inputCD();
    public void searchCDbyTitle();
    public void printCDList();
    public void updateCD();
    public void UpdateAndDelete();
    public void readFromFile(String fileName) throws IOException;
    public void saveFromFile(String fileName) throws IOException;
}
