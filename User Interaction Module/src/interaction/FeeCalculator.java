package interaction;

// importing all the packages to connect all
import athlete.Athlete;
import coaching.PrivateCoaching;
import competition.Competition;
import training.BeginnerPlan;
import training.ElitePlan;
import training.IntermediatePlan;
import training.TrainingPlan;

// the needed util to make the old members
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Trying to make a saving data function
import java.io.Serializable;

public class FeeCalculator implements Serializable {
    private static final long serialVersionUID = 1;

    // Declaring ANSI_RESET so that we can reset the color
    public static final String ANSI_RESET = "\u001B[0m"; //Yellow

    // Declaring the color
    // Custom declaration
    public static final String ANSI_YELLOW = "\u001B[33m"; //Yellow text color
    public static final String ANSI_CYAN = "\u001B[36m"; //Cyan text color
    public static final String ANSI_GREEN = "\u001B[32m"; // Green
    public static final String ANSI_RED = "\u001B[31m"; // Red
    public static final String ANSI_BLUE = "\u001B[34m"; // Vlue text

    // first you need to display the info
    // did you show the title
    // show the Menu
    // make the code short ass possible and Looks clean
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        List<Athlete> athletes = new ArrayList<>(); // List to store athletes

        loadAthletes(athletes); // this load data from the file athletes.dat
        displayInformation();
        // not yet initialize the old members might need to create a premade list for them
        displayTitle();

        while (true) {
            mainMenu();
            if (input.hasNextByte()) { // Check if the input is a byte
            byte choice = input.nextByte(); // input
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addAthlete(athletes, input);
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

    // to get the user inputted data
    public static String getName(Scanner input) {
        while (true) {
            System.out.print(ANSI_GREEN + "Enter the athlete's Name: " + ANSI_RESET);
            String name = input.nextLine().trim();
            if (isValidName(name)) {
                return name; // Valid name return it
            } else {
                System.out.println(ANSI_RED + "Invalid input. (letters only)." + ANSI_RESET);
            }
        }
    }

    private static boolean isValidName(String name) {
        return name.matches("[a-zA-Z ]+"); // Regex to allow only letters just see this in google
    }


    // to get the user inputted data
    public static float getWeight(Scanner input) {
        while (true) {
            System.out.print(ANSI_GREEN + "Enter the current weight (kg): " + ANSI_RESET);

            // Check if the next input is a float
            if (input.hasNextFloat()) {
                float weight = input.nextFloat();

                // Check if the weight is valid
                if (weight >= 1) {
                    return weight; // Return valid weight
                } else {
                    System.out.println(ANSI_RED +"Invalid input. Weight must be at least 1 kg." + ANSI_RESET);
                }
            } else {
                System.out.println(ANSI_RED + "Invalid input. Please enter a valid number." + ANSI_RESET);
                input.next(); // Clear the invalid input
            }
        }
    }

    // hey dave dont forget to make it so athlete can put number or the plan name can be inputted!
    public static TrainingPlan getTrainingPlan(Scanner input) {
        System.out.print(ANSI_CYAN +"Training Plans " +
                "\n(1)Beginner" +
                "\n(2)Intermediate" +
                "\n(3)Elite" + ANSI_RESET +
                ANSI_GREEN + "\nEnter your plan: " + ANSI_RESET);
        while (true) {
            String userInput = input.next();
            switch (userInput) {
                case "1":
                case "Beginner":
                    return new BeginnerPlan();
                case "2":
                case "Intermediate":
                    return new IntermediatePlan();
                case "3":
                case "Elite":
                    return new ElitePlan();
                default:
                    System.out.print(ANSI_RED + "Invalid training plan. enter (Beginner(1)/Intermediate(2)/Elite(3)): " + ANSI_RESET);
            }
        }
    }
    // for getting the input of user competitions only (1 to 0)
    public static byte getNumCompetitions(Scanner input, TrainingPlan trainingPlan) {
        if (trainingPlan instanceof BeginnerPlan) {
            System.out.println(ANSI_BLUE + "You don't have competitions until you are Intermediate or Elite." + ANSI_RESET);
            return 0;
        } else {
            while (true) {
                System.out.print(ANSI_GREEN + "Enter number of competitions entered this month (0 or 1): " + ANSI_RESET);
                if (input.hasNextByte()) {
                    byte competitions = input.nextByte();
                    if (competitions >= 0 && competitions <= 1) { // Check if input is 0 or 1
                        return competitions; // Valid input, return it
                    } else {
                        System.out.println(ANSI_RED + "Invalid input. Please enter 0 or 1." + ANSI_RESET);
                    }
                } else {
                    System.out.println(ANSI_RED + "Invalid input. Please enter a valid number." + ANSI_RESET);
                    input.next(); // Clear invalid input
                }
            }
        }
    }

    public static byte getPrivateCoachingHours(Scanner input) {
        while (true) {
            System.out.print(ANSI_GREEN + "Enter number of hours of private coaching (0 to 5): " + ANSI_RESET);
            if (input.hasNextByte()) {
                byte hours = input.nextByte();
                if (hours >= 0 && hours <= 5) {
                    return hours;
                } else {
                    System.out.println(ANSI_RED + "You can only enter a number between 0 and 5." + ANSI_RESET);
                }
            } else {
                System.out.println(ANSI_RED + "Invalid input. Please enter a valid number." + ANSI_RESET);
                input.next(); // clean input
            }
        }
    }

    private static void addAthlete(List<Athlete> athletes, Scanner input) {
        String athleteName = getName(input);
        TrainingPlan trainingPlan = getTrainingPlan(input);
        float currentWeight = getWeight(input);
        Competition competition = new Competition(getNumCompetitions(input, trainingPlan), trainingPlan.getPlanName());
        PrivateCoaching privateCoaching = new PrivateCoaching(getPrivateCoachingHours(input));

        Athlete athlete = new Athlete(athleteName, currentWeight, trainingPlan, competition, privateCoaching);
        athletes.add(athlete); // add athlete to the list
        System.out.println(ANSI_BLUE + "Athlete added successfully." + ANSI_RESET);
    }

    // save in serialization bytes
    private static void saveAthletes(List<Athlete> athletes) {
        try (ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream("athletes.dat"))) { // this is the file path
            fileOut.writeObject(athletes); // this will store the data on the file
            System.out.println(ANSI_BLUE + "Athletes saved successfully." + ANSI_RESET);
        } catch (IOException e) {
            // handle exception
            System.out.println("Error saving athletes: " + e.getMessage());
        }
    }

    // Method to loading the athletes from the file athletes.dat
    private static void loadAthletes(List<Athlete> athletes) {
        File fileIn = new File("athletes.dat"); // this is the file path
        if (fileIn.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileIn))) {
                List<Athlete> loadedAthletes = (List<Athlete>) ois.readObject(); // Read the list of athletes
                athletes.addAll(loadedAthletes); // Adding the loaded athletes to the list
                System.out.println(ANSI_BLUE + "Athletes loaded successfully." + ANSI_RESET);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(ANSI_RED + "Error loading athletes: " + e.getMessage() + ANSI_RESET);
            }
        }
    }

    // to show the MENU and lessen the code
    public static void mainMenu() {
        System.out.println(ANSI_CYAN +"\nMenu Options:"+ ANSI_RESET);
        System.out.println(ANSI_CYAN +"1. Register a New Athlete"+ ANSI_RESET);
        System.out.println(ANSI_CYAN +"2. Display All Athletes"+ ANSI_RESET);
        System.out.println(ANSI_CYAN +"3. Exit");
        System.out.println(ANSI_GREEN +"Enter your option: "+ ANSI_RESET);
    }

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
        System.out.println(ANSI_YELLOW +"                          |  North Sussex Judo Training Cost Calculator      |"+ ANSI_RESET);
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
