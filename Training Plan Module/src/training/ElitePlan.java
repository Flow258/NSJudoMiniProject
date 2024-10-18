package training;

import java.io.Serial;
import java.io.Serializable;

public class ElitePlan extends TrainingPlan implements Serializable {
    private static final long serialVersionUID = 1;
    private static final float FEE = 35.00f;

    @Override
    public String getPlanName() {
        return "Elite";
    }

    @Override
    public float calculateFee() {
        return FEE * WEEKLY_FEE_MULTIPLIER;
    }
}
