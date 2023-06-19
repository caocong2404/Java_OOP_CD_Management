package CDHouse;

import data.CDList;
import java.io.IOException;
import ui.menu;
import util.MyToys;

public class CDHouse {
    static final String FILE_NAME = "CD.dat";

    public static void main(String[] args) throws IOException {
        menu menu = new menu("CD House Program");
        menu.addOption("    1.     Add CD to the catalog");
        menu.addOption("    2.     Search CD by CD title");
        menu.addOption("    3.     Search CD by part of CD title(new)");
        menu.addOption("    4.     Display the catalog");
        menu.addOption("    5.     Sort the CD(new)");
        menu.addOption("    6.     Update CD");
        menu.addOption("    7.     Save account to file");
        menu.addOption("    8.     Load and print list CDs from file(new)");
        menu.addOption("    9.     Search CD Price in Range(new)");
        menu.addOption("    10.    Search CD Year in Range(new)");
        menu.addOption("    Other. Exit");

        CDList cdList = new CDList();
        int choice = 0;
        do {
            menu.printMenu();
            choice = MyToys.getAnInterger("    Input your choice: ", "Your choice must be a number!!!");

            switch (choice) {

                case 1:
                    cdList.inputCD();
                    break;
                case 2:
                    cdList.searchCDbyTitle();
                    break;
                case 3:
                    cdList.searchCDbyPartofTitle();
                    break;
                case 4:
                    cdList.printCDList();
                    break;
                case 5:
                    cdList.sortList();
                    break;
                case 6:
                    cdList.UpdateAndDelete();
                    break;
                case 7:
                    cdList.saveFromFile(FILE_NAME);
                    break;
                case 8:
                    cdList.LoadAndPrint();
                    break;
                case 9:
                    cdList.searchByRangePrice();
                    break;
                case 10:
                    cdList.searchByRangeYear();
                    break;
                default: 
                    System.out.println("Goodbye!!");

            }
        } while (choice >= 1 && choice <= 10);

    }
}

