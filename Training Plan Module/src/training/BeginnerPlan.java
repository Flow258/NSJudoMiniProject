package training;

import java.io.Serializable;

public class BeginnerPlan extends TrainingPlan implements Serializable {
    private static final long serialVersionUID = 1;
    private static final float FEE = 25.00f;

    @Override
    public String getPlanName() {
        return "Beginner"; // need to create a switch of if (1) option or just "Beginner"
    }

    @Override
    public float calculateFee() {
        return FEE * WEEKLY_FEE_MULTIPLIER;
    }
}
