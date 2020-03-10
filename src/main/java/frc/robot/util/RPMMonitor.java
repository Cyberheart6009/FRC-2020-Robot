package frc.robot.util;

public class RPMMonitor {
    private double rpms = 0;
    private double rpm = 0;
    private double previousEncoderCount = 0;
    private double previousEncoderTime = 0;
    private double encoderDivisor = 1;

    public RPMMonitor(double encoderDivisor) {
        this.encoderDivisor = encoderDivisor;
    }

    public void monitor(double currentEncoderCount) {
        // RPM Calculations
        double currentEncoderTime = System.currentTimeMillis();
        rpms = (currentEncoderCount - previousEncoderCount) / (currentEncoderTime - previousEncoderTime);

        previousEncoderCount = currentEncoderCount;
        previousEncoderTime = currentEncoderTime;
    }

    public double getRotationsPerMinute() {
        return rpms * 1000 * 60 / 256;
    }
}