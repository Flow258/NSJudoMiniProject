package athlete;

import coaching.PrivateCoaching;
import competition.Competition;
import training.BeginnerPlan;
import training.ElitePlan;
import training.IntermediatePlan;
import training.TrainingPlan;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Athlete implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;

    // Declaring ANSI_RESET so that we can reset the color
    public static final String ANSI_RESET = "\u001B[0m";

    // Custom declaration and Declaring the color
    public static final String ANSI_YELLOW = "\u001B[33m"; //Yellow text color
    public static final String ANSI_CYAN = "\u001B[36m"; //Cyan text color
    public static final String ANSI_GREEN = "\u001B[32m"; // Green
    public static final String ANSI_RED = "\u001B[31m"; // Red
    public static final String ANSI_BLUE = "\u001B[34m"; // Vlue text

    // Declaring the background color
    public static final String ANSI_RED_BACKGROUND
            = "\u001B[41m";

    transient final private String name; // Athlete name
    private final float currentWeight; // Athlete given Weight
    private final WeightCategory weightCategory; // Categorizing the athlete given weight
    //private String athleteCategory;
    private final TrainingPlan trainingPlan; // Athlete plan
    private final Competition competition; // Com need to allow only elite and Intermediate
    private final PrivateCoaching privateCoaching; // add (0 to 5) only allowed

    // constructor for athlete
    public Athlete(String name, /*String athleteCategory,*/ float currentWeight, TrainingPlan trainingPlan,
                   Competition competition, PrivateCoaching privateCoaching) {

        this.name = name;
        //this.athleteCategory = athleteCategory;
        this.currentWeight = currentWeight;
        this.trainingPlan = trainingPlan;
        this.competition = competition;
        this.privateCoaching = privateCoaching;
        this.weightCategory = WeightCategory.getCategory(currentWeight);
    }

    // Calculating the total amount the athlete need to pay
    public float calculateTotalCost() {
        return trainingPlan.calculateFee() + competition.calculateFee() + privateCoaching.calculateFee();
    }

    // Category getter and calculate if exact limit or under like 43 kg under limit.
    public String getWeightCategory() {
        float upperLimit = weightCategory.getUpperLimit();
        float weightDifference = upperLimit - currentWeight;
        float weight = currentWeight;

        if (weightDifference == 0) {
            return "At exact limit of " + weightCategory;
        } else if (weight > 650) {
            return String.format(ANSI_RED_BACKGROUND + "%.1f kg over %s You are the Heaviest human alive" + ANSI_RESET, weightDifference, weightCategory);
        } else {
            return String.format("%.1f kg under %s limit", weightDifference, weightCategory);
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
        System.out.print(ANSI_CYAN +"Training Plans " + "\n(1)Beginner" + "\n(2)Intermediate" + "\n(3)Elite" + ANSI_RESET +
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
    public static byte getNumCompetition(Scanner input, TrainingPlan trainingPlan) {
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

    //Displaying the input of the athlete
    public void displayAthleteInformation(long athleteNumber) {
        System.out.println(ANSI_YELLOW + "\n========================================" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "Athlete ID No: " + athleteNumber);
        System.out.println(ANSI_CYAN + "Athlete                         : " + name);
        System.out.println(ANSI_CYAN + "Current Weight                  : " + currentWeight + " kg");
        System.out.println(ANSI_CYAN + "Training Plan                   : " + trainingPlan.getPlanName());
        System.out.println(ANSI_CYAN + "Training Fee For Month          : $" + String.format("%.2f", trainingPlan.calculateFee()));
        System.out.println(ANSI_CYAN + "Private Coaching Fee For Month  : $" + String.format("%.2f", privateCoaching.calculateFee()));
        System.out.println(ANSI_CYAN + "Total Competition Fee           : $" + String.format("%.2f", competition.calculateFee()));
        System.out.println(ANSI_CYAN + "Total Cost for the Month        : $" + String.format("%.2f", calculateTotalCost()));
        System.out.println(ANSI_CYAN + "Weight Category                 : " + weightCategory);
        System.out.println(ANSI_CYAN + "Weight Status                   : " + getWeightCategory());
        //System.out.println(compareWeightToCategory());
        System.out.println(ANSI_YELLOW + "========================================" + ANSI_RESET);
    }

    public static void setAthlete(List<Athlete> athletes, Scanner input) {
        String athleteName = getName(input);
        // String athleteCategory = getAhtleteCategory(input);
        TrainingPlan trainingPlan = getTrainingPlan(input);
        float currentWeight = getWeight(input);
        Competition competition = new Competition(getNumCompetition(input, trainingPlan), trainingPlan.getPlanName());
        PrivateCoaching privateCoaching = new PrivateCoaching(getPrivateCoachingHours(input));

        Athlete athlete = new Athlete(athleteName,/* athleteCategory,*/ currentWeight, trainingPlan, competition, privateCoaching);
        athletes.add(athlete); // add athlete to the list
        System.out.println(ANSI_BLUE + "Athlete added successfully." + ANSI_RESET);
    }

    // save in serialization bytes
    public static void saveAthletes(List<Athlete> athletes) {
        try (ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream("athletes.dat"))) { // this is the file path
            fileOut.writeObject(athletes); // this will store the data on the file
            System.out.println(ANSI_BLUE + "Athletes saved successfully." + ANSI_RESET);
        } catch (IOException e) {
            // handle exception
            System.out.println("Error saving athletes: " + e.getMessage());
        }
    }

    // Method to loading the athletes from the file athletes.dat
    public static void loadAthletes(List<Athlete> athletes) {
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
}


  /*
    public String compareWeightToCategory() {
        float upperLimit = weightCategory.getUpperLimit();

        if (currentWeight < upperLimit) {
            return String.format("%s is %.1f under the %s limit.", name, upperLimit - currentWeight, weightCategory);
        } else if (currentWeight == upperLimit) {
            return String.format("%s is at exact limit of %s.", name, weightCategory);
        } else {
            return String.format("%s is kg over the %s limit.", name, currentWeight - upperLimit, weightCategory);
        }
    }

     */