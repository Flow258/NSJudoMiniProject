package coaching;

import java.io.Serializable;

public class PrivateCoaching implements Serializable {
    private static final long serialVersionUID = 1;
    private final byte numHours;
    private static float HOURLY_RATE = 9.00f;

    public PrivateCoaching(byte numHours) {

        this.numHours = numHours;
    }

    public float calculateFee() {

        return numHours * HOURLY_RATE * 4; // hour rate is multi. bay 4 weeks
    }
}
