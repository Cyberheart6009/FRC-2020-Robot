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

public class ShooterSubsystem extends SubsystemBase {
  private final Spark shooterOne, shooterTwo;

  public ShooterSubsystem() {
      shooterOne = new Spark(Constants.PWMPorts.kShootMotor1);
      shooterTwo = new Spark(Constants.PWMPorts.kShootMotor2);
  }

  public void setFlywheel(double speed) {
    shooterOne.set(speed);
    shooterTwo.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
