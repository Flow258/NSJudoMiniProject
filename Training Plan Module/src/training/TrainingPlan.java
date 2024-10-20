package training;

import java.io.Serial;
import java.io.Serializable;

public abstract class TrainingPlan implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;
    protected static final float WEEKLY_FEE_MULTIPLIER = 4;

    public abstract String getPlanName(); // Getter for athlete name plan
    public abstract float calculateFee(); // calculating the Fees of the athlete payments
}
