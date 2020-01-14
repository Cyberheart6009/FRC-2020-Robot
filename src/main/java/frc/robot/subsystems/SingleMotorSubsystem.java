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
import frc.robot.Constants;

public class SingleMotorSubsystem extends SubsystemBase {
  /**
   * Creates a new subsystem.
   */

  private final SpeedController motor;

  public SingleMotorSubsystem(int port) {
    motor = new Spark(port);
  }

  public void variableOn(double value) {
    motor.set(value);
  }

  public void fullForward() {
    motor.set(1);
  }

  public void fullBackward() {
    motor.set(-1);
  }

  public void fullStop() {
    motor.set(0);
  }
}
