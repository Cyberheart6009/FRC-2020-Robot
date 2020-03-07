
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ColourWheel extends SubsystemBase {
  /**
   * Creates a new subsystem.
   */

  private final SpeedController motor;

  public ColourWheel() {
    motor = new Spark(Constants.PWMPorts.kColourWheelMotor);
  }

  public void set(double value) {
    motor.set(value);
  }

  public void forward() {
    motor.set(1);
  }

  public void reverse() {
    motor.set(-1);
  }

  public void stop() {
    if (motor.get() != 0) {
      motor.set(0);
    }
  }
}
