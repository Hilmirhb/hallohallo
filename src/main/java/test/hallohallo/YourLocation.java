package test.hallohallo;

public class YourLocation {
    public static String Command(){
        String YourLocation = "/Users/owenwong/Desktop/HBV401G Software Development/HBV401G Final Project/hallohallo/";
        String Command = "jdbc:sqlite:" + YourLocation + "Tours.db";
        return Command;
    }
}


