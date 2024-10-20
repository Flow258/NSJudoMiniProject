package interaction;

// importing all the packages to connect all
import athlete.Athlete;

// the needed util to make the old members
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Trying to make a saving data function
import java.io.Serializable;

import static athlete.Athlete.*;
import static interaction.Display.*;

public class FeeCalculator implements Serializable {
    private static final long serialVersionUID = 1;

    // Declaring ANSI_RESET so that we can reset the color
    public static final String ANSI_RESET = "\u001B[0m"; //Yellow

    // Custom declaration && Declaring the color
    public static final String ANSI_RED = "\u001B[31m"; // Red
    public static final String ANSI_BLUE = "\u001B[34m"; // Vlue text

    // make the code short ass possible and Looks clean and show the Menu
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Athlete> athletes = new ArrayList<>(); // List to store athletes
        loadAthletes(athletes); // this load data from the file athletes.dat
        displayInformation();
        displayTitle();

        while (true) {
            mainMenu();
            if (input.hasNextByte()) { // Check if the input is a byte
                byte choice = input.nextByte(); // input
                input.nextLine(); // Consume new1line

                switch (choice) {
                    case 1:
                        setAthlete(athletes, input);
                        break;
                    case 2:
                        ShowingMembers(athletes);
                        break;
                    case 3:
                        saveAthletes(athletes); // Save athletes before exiting
                        System.out.println(ANSI_BLUE + "Exiting saving data..." + ANSI_RESET);
                        input.close();
                        return;
                    default:
                        System.out.println(ANSI_RED + "Invalid option. Please try again." + ANSI_RESET);
                }
            } else {
                System.out.println(ANSI_RED + "Invalid input. Please enter a number (1-3)." + ANSI_RESET);
                input.next(); // Clear invalid input
            }
        }
    }
}



/*
    public static String getAhtleteCategory(Scanner input) {
        System.out.println("Select your Category: ");
        System.out.println("1. FlyWeight");
        System.out.println("2. LightWeight");
        System.out.println("3. Light MiddleWeight");
        System.out.println("4. MiddleWeight");
        System.out.println("5. Light HeavyWeight");
        System.out.println("6. HeavyWeight");

        while (true) {
            System.out.println("Enter the number of your category (1 to 6)");

            if (input.hasNextByte()) {
                byte choice = input.nextByte();
                input.nextLine();

                switch (choice) {
                    case 1:
                        return WeightCategory.FLYWEIGHT.toString();
                    case 2:
                        return WeightCategory.LIGHTWEIGHT.toString();
                    case 3:
                        return WeightCategory.LIGHT_MIDDLEWEIGHT.toString();
                    case 4:
                        return WeightCategory.MIDDLEWEIGHT.toString();
                    case 5:
                        return WeightCategory.LIGHT_HEAVYWEIGHT.toString();
                    case 6:
                        return WeightCategory.OVERWEIGHT.toString();
                }
            }else {
                System.out.println("Invalid enter the valid number. ");
                input.next();
            }

        }
    }
     */

