package test.hallohallo;

public class YourLocation {
    public static String Command(){
        String YourLocation = "/Users/hilmir/Desktop/";
        String Command = "jdbc:sqlite:" + YourLocation + "Tours.db";
        return Command;
    }
}


