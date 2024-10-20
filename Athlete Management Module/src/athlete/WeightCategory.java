package athlete;

public enum WeightCategory {

    FLYWEIGHT(66), LIGHTWEIGHT(73),
    LIGHT_MIDDLEWEIGHT(81), MIDDLEWEIGHT(90),
    LIGHT_HEAVYWEIGHT(100), HEAVYWEIGHT(199),
    OVERWEIGHT(700);

    private final float upperLimit;

    WeightCategory(float upperLimit) {
        this.upperLimit = upperLimit;
    }

    public float getUpperLimit() {
        return upperLimit;
    }

    public static WeightCategory getCategory(float weight) {
        for (WeightCategory category : WeightCategory.values()) {
            if (weight <= category.upperLimit) {
                return category;
            }
        }
        return HEAVYWEIGHT; // if no category matches to the input
    }

    @Override
    public String toString() {
        return this.name() + " (up to " + this.upperLimit + " kg)";
    }
}
    /*
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
     */


