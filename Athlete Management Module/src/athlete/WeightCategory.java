package athlete;

import java.io.Serializable;

public enum WeightCategory {

    FLYWEIGHT(66), LIGHTWEIGHT(73),
    LIGHT_MIDDLEWEIGHT(81), MIDDLEWEIGHT(90),
    LIGHT_HEAVYWEIGHT(100), HEAVYWEIGHT(200),
    OVERWEIGHT(Float.MAX_VALUE);
    //OVERWEIGHT(Float.MAX_VALUE);

    private final float upperLimit;

    // Constructor
    WeightCategory(float upperLimit) {
        this.upperLimit = upperLimit;
    }

    //getter
    public float getUpperLimit() {
        return upperLimit;
    }

    // to know what the weight category of athlete
    public static WeightCategory getCategory(float weight) {
        for (WeightCategory category : WeightCategory.values()) {
            if (weight <= category.upperLimit) {
                return category;
            }
        }
        return HEAVYWEIGHT;
    }

    @Override
    public String toString() {
        return this.name() + " (up to " + this.upperLimit + " kg)";
    }
}

