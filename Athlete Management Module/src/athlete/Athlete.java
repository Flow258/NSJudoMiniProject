package athlete;

import coaching.PrivateCoaching;
import competition.Competition;
import training.TrainingPlan;

import java.io.Serializable;

public class Athlete implements Serializable {
    private static final long serialVersionUID = 1;

    // Declaring ANSI_RESET so that we can reset the color
    public static final String ANSI_RESET = "\u001B[0m";

    // Custom declaration and Declaring the color
    public static final String ANSI_YELLOW = "\u001B[33m"; //Yellow text color
    public static final String ANSI_CYAN = "\u001B[36m"; //Cyan text color

    // Declaring the background color
    public static final String ANSI_RED_BACKGROUND
            = "\u001B[41m";

    transient private String name; // Athlete name
    private float currentWeight; // Athlete given Weight
    private WeightCategory weightCategory; // Categorizing the athlete given weight
    private TrainingPlan trainingPlan; // Athlete plan
    private Competition competition; // Com need to allow only elite and Intermediate
    private PrivateCoaching privateCoaching; // add (0 to 5) only allowed

    // constructor for athlete
    public Athlete(String name, float currentWeight, TrainingPlan trainingPlan,
                   Competition competition, PrivateCoaching privateCoaching) {

        this.name = name;
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
        System.out.println(ANSI_YELLOW + "========================================" + ANSI_RESET);
    }
}
