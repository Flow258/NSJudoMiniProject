package training;

import java.io.Serializable;

public class IntermediatePlan extends TrainingPlan implements Serializable {
    private static final long serialVersionUID = 1;
    private static final float FEE = 30.00f;

    @Override
    public String getPlanName() {
        return "Intermediate";
    }

    @Override
    public float calculateFee() {
        return FEE * WEEKLY_FEE_MULTIPLIER;
    }
}
