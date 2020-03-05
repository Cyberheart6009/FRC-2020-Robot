package frc.robot.util;

public class RPMMonitor {
    private double rpm = 0;
    private double previousEncoderCount = 0;
    private double previousEncoderTime = 0;

    public void monitor(double currentEncoderCount) {
        // RPM Calculations
        double currentEncoderTime = System.currentTimeMillis();
        double rps = (currentEncoderCount - previousEncoderCount) / (currentEncoderTime - previousEncoderTime);
        rpm = rps * 60;

        previousEncoderCount = currentEncoderCount;
        previousEncoderTime = currentEncoderTime;
    }

    public double getRotationsPerMinute() {
        return rpm;
    }
}