package interaction;

import athlete.Athlete;

import java.util.List;

public class Displayed {
    // Declaring ANSI_RESET so that we can reset the color
    public static final String ANSI_RESET = "\u001B[0m";

    // Custom declaration and Declaring the color
    public static final String ANSI_YELLOW = "\u001B[33m"; //Yellow text color
    public static final String ANSI_CYAN = "\u001B[36m"; //Cyan text color
    public static final String ANSI_GREEN = "\u001B[32m"; // Green
    public static final String ANSI_RED = "\u001B[31m"; // Red
    public static final String ANSI_BLUE = "\u001B[34m"; // Vlue text

    public static void ShowingMembers(List<Athlete> athletes) {
        System.out.println(ANSI_YELLOW +"\nRegistered Athletes:" + ANSI_RESET);
        System.out.println(ANSI_YELLOW +"========================================"+ ANSI_RESET);
        for (int i = 0; i < athletes.size(); i++) {
            athletes.get(i).displayAthleteInformation(i + 1); // Displaying each athlete information
        }
        System.out.println(ANSI_YELLOW +"\n========================================"+ ANSI_RESET);
    }

    public static void displayTitle() {
        System.out.println(ANSI_YELLOW +"\n                          ===================================================="+ ANSI_RESET);
        System.out.println(ANSI_YELLOW +"                          |          NSJudo Training Cost Calculator         |"+ ANSI_RESET);
        System.out.println(ANSI_YELLOW +"                          ===================================================="+ ANSI_RESET);
    }

    public static void displayInformation() {
        System.out.println(ANSI_YELLOW +"\n                                      -      Information       -              "+ ANSI_RESET);
        System.out.println(ANSI_YELLOW +"                                       ========================\n");
        System.out.println(ANSI_YELLOW +
                "====================================================\t==========================================================="+ ANSI_RESET);
        System.out.println(ANSI_YELLOW +
                "|              Training Plan - Prices              |\t| Weight Categories             | Upper Weight Limit (kg) |"+ ANSI_RESET);
        System.out.println(ANSI_YELLOW +
                "====================================================\t==========================================================="+ ANSI_RESET);
        System.out.println(ANSI_YELLOW +
                "| Beginner (2 sessions/week)              | $25.00 |\t| Heavyweight                   | Unlimited (Over 100)    |"+ ANSI_RESET);
        System.out.println(ANSI_YELLOW +
                "| Intermediate (3 sessions/week)          | $30.00 |\t| Light-Heavyweight             | 100                     |"+ ANSI_RESET);
        System.out.println(ANSI_YELLOW +
                "| Elite (5 sessions/week)                 | $35.00 |\t| Middleweight                  | 90                      |"+ ANSI_RESET);
        System.out.println(ANSI_YELLOW +
                "| Private tuition (per hour)              | $9.00  |\t| Light-Middleweight            | 81                      |"+ ANSI_RESET);
        System.out.println(ANSI_YELLOW +
                "| Competition entry fee (per competition) | $22.00 |\t| Lightweight                   | 73                      |"+ ANSI_RESET);
        System.out.println(ANSI_YELLOW +
                "====================================================\t| Flyweight                     | 66                      |"+ ANSI_RESET);
        System.out.println(ANSI_YELLOW +
                "                                                    \t==========================================================="+ ANSI_RESET);
    }
}
