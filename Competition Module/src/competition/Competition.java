package competition;

import java.io.Serializable;

public class Competition implements Serializable {
    private final byte numCom; // Competition Number
    private final String trainingPlan;
    private static final float COMPETITION_FEE = 22.00f;

    public Competition(byte numCom, String trainingPlan) {

        this.numCom = numCom;
        this.trainingPlan = trainingPlan;
    }

    public float calculateFee() {

        if ("Intermediate".equals(trainingPlan) || "Elite".equals(trainingPlan)) {
            return numCom * COMPETITION_FEE; // to store the data for me to get this
        }
        return 0.0f;
    }
}
