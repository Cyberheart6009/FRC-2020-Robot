/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SingleMotorSubsystem extends SubsystemBase {
  /**
   * Creates a new subsystem.
   */

  private final SpeedController motor;

  public SingleMotorSubsystem(int port) {
    motor = new Spark(port);
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
