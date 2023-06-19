package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import util.MyToyValid;
import util.MyToys;

public class CDList extends ArrayList<CD> implements DAOcd {

    public static final String FILE_NAME = "CD.dat";
    private static Scanner sc = new Scanner(System.in);

    private boolean checkDuplicated(String s) {
        for (CD x : this) {
            if (x.getCDid().equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void inputCD() {

        if (this.size() > 56) {
            System.out.println("Unable to add CD");
            System.err.println("The CD List is overload");
            return;
        }

        String CDid, CDname, CDtype, CDtitle;
        double CDUnitPrice;
        int CDpublish;

        //ID contain 5 character and no space
        do {
            CDid = MyToys.getStringbyFormat("Input CD ID: ", "The CD ID must be at five character and no spaces", "[\\S]{5}$");
            if (checkDuplicated(CDid)) {
                System.out.println(CDid + " is exist. Please enter ID again");
            }
        } while (checkDuplicated(CDid));

        //CDname = MyToys.getStringbyFormat("Input CD Name (Game/Movie/Music): ", "The CD Name must is (Game/Movie/Music)!!", "Game", "Movie", "Music");
        //CDname = MyToys.toUpperOneFirstLetter(CDname);
        System.out.println("Input CD Name:");
        System.out.println("    CD Name:");
        System.out.println("    1. Game");
        System.out.println("    2. Movie");
        System.out.println("    3. Music");

        int choice = MyToys.getAnInteger("Your choose: ", "Please choose 1 to 3\n", 1, 3);
        if (choice == 1) {
            CDname = "Game";
        } else if (choice == 2) {
            CDname = "Movie";
        } else {
            CDname = "Music";
        }

        System.out.print("CD Type: ");
        if (CDname.equalsIgnoreCase("Music") )
            CDtype = "Audio";
        else 
            CDtype = "Video";
        System.out.println(CDname + " - " + CDtype);
//        System.out.println("    CD Type:");
//        System.out.println("    1. Audio");
//        System.out.println("    2. Video");
//        choice = MyToys.getAnInteger("Your choose: ", "Please choose 1 to 2\n", 1, 2);
//        if (choice == 1) {
//            CDtype = "Audio";
//        } else {
//            CDtype = "Video";
//        }
        //CDtype = MyToys.getString("Input CD Type: ", "The CD Type must be not empty!!");

        CDtitle = MyToys.getString("Input CD Title: ", "The CD Title must be not empty!!");
        CDUnitPrice = MyToys.getDouble("Input UnitPrice: ", "UnitPrice must be less than 10 000 number!!", 0, 10000);

        do {
            CDpublish = MyToys.getAnInterger("Input CD Publish Year: ", "The CD Publish Year must be a number!!");
            if (!MyToyValid.checkYearValid(CDpublish)) {
                System.out.println("Year in range 1980-" + Calendar.getInstance().get(Calendar.YEAR));
            }
        } while (!MyToyValid.checkYearValid(CDpublish));

        this.add(new CD(CDid, CDname, CDtype, CDtitle, CDUnitPrice, CDpublish));
        System.out.println("Create successfully");

        if (goBackMenu()) {
            inputCD();
        } else {
            System.out.println("Exit");
        }
    }

    public boolean goBackMenu() {
        String choice = MyToys.getChoose("Do you want to continue(Y/N): ", "Y", "N");
        if (choice.equalsIgnoreCase("Y")) {
            return true;
        }
        return false;
    }

    @Override
    public void searchCDbyTitle() {
        if (this.isEmpty()) {
            System.err.println("Have no any Product");
            return;
        } else {
            System.out.println("Searching engine: ");
            System.out.println("There are " + this.size() + " CDs in the archive list.");
        }

        String searchTitle;
        searchTitle = MyToys.getString("Input CD Title to search: ", "Empty!!");
        int pos = searchCDbyTitle(searchTitle);
        if (pos >= 0) {
            System.out.println("FOUND!!");
            System.out.println("Here the list search by \"" + searchTitle + "\":");
            for (CD x : this) {
                if (x.getCDtitle().matches(searchTitle)) {
                    System.out.print(x);
                }
            }
        } else {
            System.out.println("NOT FOUND!!");
        }

        if (goBackMenu()) {
            searchCDbyTitle();
        } else {
            System.out.println("Exit");
        }
    }

    public int searchCDbyTitle(String searchTitle) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getCDtitle().equalsIgnoreCase(searchTitle)) {
                return i;
            }
        }
        return -1;
    }

    private int searchCDbyPartOfTitle(String searchTitle) {
        for (int i = 0; i < this.size(); i++) {
            if ((this.get(i).getCDtitle().toUpperCase()).contains(searchTitle.toUpperCase())) {
                return i;
            }
        }
        return -1;
    }

    public int searchCDbyID(String searchID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getCDid().equalsIgnoreCase(searchID)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void printCDList() {
        if (this.isEmpty()) {
            System.err.println("Have no any Product");
            return;
        } else {
            System.out.println("There are " + this.size() + " CDs in the archive list.");
        }

        int i = 1;
        System.out.println("|   |CD ID   |CD Name |CD Type |CD Title            |UnitPrice|Publishing Year");
        for (CD x : this) {
            System.out.printf("%-4d", i++);
            System.out.print(x);
        }
        System.out.println();

        if (goBackMenu()) {
            printCDList();
        } else {
            System.out.println("Exit");
        }
    }

    public void printCDListFromFile() throws IOException {
        ArrayList<CD> cdList = new ArrayList<>();
        cdList = readFromFileL(FILE_NAME);

        if (cdList.isEmpty()) {
            System.err.println("Have no any CD");
            LoadAndPrint();
            return;
        } else {
            System.out.println("There are " + cdList.size() + " CDs in the archive list.");
        }
        //sort by name
        Collections.sort(cdList, new Comparator<CD>() {
            @Override
            public int compare(CD o1, CD o2) {
                return o1.getCDname().compareToIgnoreCase(o2.getCDname());
            }
        });

        System.out.println("|   |CD ID   |CD Name |CD Type |CD Title            |UnitPrice|Publishing Year");
        int i = 1;
        for (CD x : cdList) {
            System.out.printf("%-4d", i++);
            System.out.print(x);
        }
        System.out.println();

        if (goBackMenu()) {
            LoadAndPrint();
        } else {
            System.out.println("Exit");
        }

    }

    @Override
    public void updateCD() {

        String updateID;

        String updateName, updateType, updateTitle;
        double updateUnitPrice;
        int updatePublish;

        updateID = MyToys.getStringbyFormat("Input CD ID you want to update: ", "The CD ID must be at five character and no spaces", "[\\S]{5}$");

        int pos = searchCDbyID(updateID);

        if (pos >= 0) {

            System.out.println("Update CD Name:");
            System.out.println("    CD Name:");
            System.out.println("    1. Game");
            System.out.println("    2. Movie");
            System.out.println("    3. Music");
            System.out.println("    4. No Update");

            int choice = MyToys.getAnInteger("Your choose: ", "Please choose 1 to 4\n", 1, 4);
            if (choice == 1) {
                updateName = "Game";
            } else if (choice == 2) {
                updateName = "Movie";
            } else if (choice == 3) {
                updateName = "Music";
            } else {
                updateName = this.get(pos).getCDname();
            }
            
//            if (choice == 3) {
//                System.out.print("CD Type: ");
//                if (updateName.equalsIgnoreCase("Music") )
//                    updateType = "Audio";
//                else 
//                updateType = "Video";
//                System.out.println(updateName + " - " + updateType);
//            }
            
            System.out.println("Update CD Type:");
            System.out.println("    CD Type:");
            System.out.println("    1. Audio");
            System.out.println("    2. Video");
            System.out.println("    3. No update");
            choice = MyToys.getAnInteger("Your choose: ", "Please choose 1 to 3\n", 1, 3);
            if (choice == 1) {
                updateType = "Audio";
            } else if (choice == 2) {
                updateType = "Video";
            } else {
                updateType = this.get(pos).getCDtype();
            }

            System.out.println("Old CD title: " + this.get(pos).getCDtitle() + " | New CD title: ");
            updateTitle = sc.nextLine().trim();
            if (updateTitle.length() > 0) {
                this.get(pos).setCDtitle(updateTitle);
            }

            updateUnitPrice = MyToys.getDouble("Old Unit Price: " + this.get(pos).getCDUnitPrice() + " | New Unit Price: ", "UnitPrice must be positive number!!");
            if (updateUnitPrice > 0) {
                this.get(pos).setCDUnitPrice(updateUnitPrice);
            }

            do {
                System.out.println("Old CD Publishing year: " + this.get(pos).getCDpublish());
                updatePublish = MyToys.getAnInterger("New CD Publishing year: ", "The CD Publish Year must be a number!!");
                if (!MyToyValid.checkYearValid(updatePublish)) {
                    System.out.println("Year in range 1980-" + Calendar.getInstance().get(Calendar.YEAR) + "\n");
                }
            } while (!MyToyValid.checkYearValid(updatePublish));

            this.get(pos).setCDname(updateName);
            this.get(pos).setCDtype(updateType);

            this.get(pos).setCDpublish(updatePublish);

            System.out.println("Update Successfully!!");

            if (goBackMenu()) {
                updateCD();
            } else {
                UpdateAndDelete();
            }
        } else {
            System.out.println("CD ID not found!!!");
            System.err.println("Update Fail");
            return;
        }
    }

    public void deleteCD() {
        String deleteID;

        deleteID = MyToys.getStringbyFormat("Input CD ID you want to delete: ", "The CD ID must be at least five character and no spaces", "[\\S]{5,}$");
        int pos = searchCDbyID(deleteID);

        if (pos >= 0) {
            this.remove(pos);
            System.out.println("Delete Success!!");

            if (goBackMenu()) {
                deleteCD();
            } else {
                UpdateAndDelete();
            }
        } else {
            System.err.println("Delete Fail!!");
            return;
        }
    }

    public void UpdateAndDelete() {
        if (this.isEmpty()) {
            System.err.println("Have no any CD");
            return;
        }

        System.out.println("=========================================");
        System.out.println("    Update CD:");
        System.out.println("    1. Update CD information");
        System.out.println("    2. Delete CD information ");
        System.out.println("    3. Quit");

        int choice = MyToys.getAnInteger("Your choose: ", "Please choose 1 to 3\n", 1, 3);
        if (choice == 1) {
            updateCD();
        } else if (choice == 2) {
            deleteCD();
        } else {
            System.out.println("Quit menu");
            return;
        }
    }

    public void LoadAndPrint() throws IOException {

        System.out.println("=========================================");
        System.out.println("    Load And Print:");
        System.out.println("    1. Load from file");
        System.out.println("    2. Print from file");
        System.out.println("    3. Quit");

        int choice = MyToys.getAnInteger("Your choose: ", "Please choose 1 to 3\n", 1, 3);
        if (choice == 1) {
            readFromFile(FILE_NAME);
        } else if (choice == 2) {
            printCDListFromFile();
        } else {
            System.err.println("Quit menu");
            return;
        }
    }

    private ArrayList<CD> readFromFileL(String fileName) throws IOException {
        ArrayList<CD> cdList = new ArrayList<>();

        CD p;
        File f = null;
        FileInputStream in = null;
        ObjectInputStream objInputStream = null;

        try {
            f = new File(fileName);
            if (!f.exists()) {
                System.out.println("File not exist");
                return null;
            }
            in = new FileInputStream(fileName);
            objInputStream = new ObjectInputStream(in);

            while ((p = (CD) (objInputStream.readObject())) != null) {
                cdList.add(p);
            }

        } catch (Exception e) {
        } finally {
            if (in != null) {
                in.close();
            }

            if (objInputStream != null) {
                objInputStream.close();
            }
        }
        return cdList;
    }

    @Override
    public void readFromFile(String fileName) throws IOException {

        CD p;
        File f = null;
        FileInputStream in = null;
        ObjectInputStream objInputStream = null;

        try {
            f = new File(fileName);
            if (!f.exists()) {
                System.out.println("File not exist");
                return;
            }
            in = new FileInputStream(fileName);
            objInputStream = new ObjectInputStream(in);

            while ((p = (CD) (objInputStream.readObject())) != null) {
                if (!checkDuplicated(p.getCDid())) {
                    this.add(p);
                }
            }

        } catch (Exception e) {
        } finally {
            if (in != null) {
                in.close();
            }

            if (objInputStream != null) {
                objInputStream.close();
            }
        }
        System.out.println("Read successfull!!");

        if (goBackMenu()) {
            LoadAndPrint();
        } else {
            System.out.println("Exit");
        }

    }

    @Override
    public void saveFromFile(String fileName) throws IOException {

        FileOutputStream out = null;
        ObjectOutputStream objOutputStream = null;
        try {
            out = new FileOutputStream(fileName);
            objOutputStream = new ObjectOutputStream(out);

            for (CD x : this) {
                objOutputStream.writeObject(x);
            }
        } catch (Exception e) {
        } finally {
            if (out != null) {
                out.close();
            }

            if (objOutputStream != null) {
                objOutputStream.close();
            }
        }

        System.out.println("Save successfull");

//        if (goBackMenu()) {
//            saveFromFile("CD.dat");
//        } else {
//            System.out.println("Exit");
//        }
    }

    public void sortList() {
        if (this.isEmpty()) {
            System.err.println("Have no any CD");
            return;
        }

        System.out.println("------======(Sort Type)======------");
        System.out.println("    1   -   Sort by CD ID.");
        System.out.println("    2   -   Sort by CD Name.");
        System.out.println("    3   -   Sort by CD Type.");
        System.out.println("    4   -   Sort by CD Title.");
        System.out.println("    5   -   Sort by CD Price.");
        System.out.println("    6   -   Sort by Publishing Year.");
        System.out.println("    7   -   Quit to menu.");

        int choice = MyToys.getAnInteger("Your choose: ", "Please choose 1...7\n", 1, 7);
        if (choice == 1) {
            Collections.sort(this, new Comparator<CD>() {
                @Override
                public int compare(CD o1, CD o2) {
                    return o1.getCDid().compareToIgnoreCase(o2.getCDid());
                }
            });
        } else if (choice == 2) {
            Collections.sort(this, new Comparator<CD>() {
                @Override
                public int compare(CD o1, CD o2) {
                    return o1.getCDname().compareToIgnoreCase(o2.getCDname());

                }
            });
        } else if (choice == 3) {
            Collections.sort(this, new Comparator<CD>() {
                @Override
                public int compare(CD o1, CD o2) {
                    return o1.getCDtype().compareToIgnoreCase(o2.getCDtype());

                }
            });
        } else if (choice == 4) {
            Collections.sort(this, new Comparator<CD>() {
                @Override
                public int compare(CD o1, CD o2) {
                    return o1.getCDtitle().compareToIgnoreCase(o2.getCDtitle());

                }
            });
        } else if (choice == 5) {
            Collections.sort(this, new Comparator<CD>() {
                @Override
                public int compare(CD o1, CD o2) {
                    return (int) (o1.getCDUnitPrice() - o2.getCDUnitPrice());
                }
            });
        } else if (choice == 6) {
            Collections.sort(this, new Comparator<CD>() {
                @Override
                public int compare(CD o1, CD o2) {
                    return (int)(o2.getCDpublish() - o1.getCDpublish());
                }
            });
        } else {
            System.err.println("Exit");
            return;
        }

        System.err.println("Sort complete!!");
    }

    public void searchCDbyPartofTitle() {
        if (this.isEmpty()) {
            System.err.println("Have no any CD");
            return;
        }

        String searchTitle;
        searchTitle = MyToys.getString("Input title to search: ", "Empty!!");
        int pos = searchCDbyPartOfTitle(searchTitle);
        if (pos >= 0) {
            System.out.println("FOUND!!");
            System.out.println("|   |CD ID   |CD Name |CD Type |CD Title            |UnitPrice|Publishing Year");
            for (CD x : this) {
                if ((x.getCDtitle().toUpperCase()).contains(searchTitle.toUpperCase())) {
                    System.out.print(x);
                }
            }
        } else {
            System.err.println("NOT FOUND!!");
        }

        if (goBackMenu()) {
            searchCDbyPartofTitle();
        } else {
            System.err.println("Exit");
        }
    }
    
    private int searchByRangePrice(double a, double b) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getCDUnitPrice()>= a && this.get(i).getCDUnitPrice() <= b)
                return i;
        }
        return -1;
    }
    
    public void searchByRangePrice(){
        System.out.println("SEARCH PRICE IN RANGE");
        double a = MyToys.getDouble("From: ", "Must be positive number", 0, Double.MAX_VALUE);
        double b = -1;
        do {
            b = MyToys.getDouble("To: ", "Must be >= " + a);
        } while (b <= a);
        
        int pos = searchByRangePrice(a, b);
        if (pos >= 0) {
            System.out.println("Here the list:");
            System.out.println("|CD ID   |CD Name |CD Type |CD Title            |UnitPrice|Publishing Year");
            for (CD x : this) {
                if (x.getCDUnitPrice()>= a && x.getCDUnitPrice()<= b)
                        System.out.println(x);
            }
        } else 
            System.out.println("Not found any CD Price in range " + a + "..." + b);
    }
    
    
    private int searchByRangeYear(int a, int b) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getCDpublish()>= a && this.get(i).getCDpublish() <= b)
                return i;
        }
        return -1;
    }
    
    public void searchByRangeYear(){
        System.out.println("SEARCH YEAR IN RANGE");
        int a = MyToys.getAnInteger("From: ", "Must be in year 1980..." + Calendar.getInstance().get(Calendar.YEAR), 1980, Calendar.getInstance().get(Calendar.YEAR));
        int b = MyToys.getAnInteger("To: ", "Must be int range " + a + "..." + Calendar.getInstance().get(Calendar.YEAR), a, Calendar.getInstance().get(Calendar.YEAR));
        
        
        int pos = searchByRangeYear(a, b);
        if (pos >= 0) {
            System.out.println("Here the list:");
            System.out.println("|CD ID   |CD Name |CD Type |CD Title            |UnitPrice|Publishing Year");
            for (CD x : this) {
                if (x.getCDpublish() >= a && x.getCDpublish() <= b)
                        System.out.println(x);
            }
        } else 
            System.out.println("Not found any CD Year in range " + a + "..." + b);
    }
}
