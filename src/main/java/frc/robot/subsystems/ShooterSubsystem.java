/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.util.RPMMonitor;

public class ShooterSubsystem extends SubsystemBase {
  private final SpeedControllerGroup shooterGroup;
  private final Encoder shooterEncoder;
  private final RPMMonitor rpm = new RPMMonitor(256);

  public ShooterSubsystem() {
      Spark shooterOne = new Spark(Constants.PWMPorts.kShootMotor1);
      Spark shooterTwo = new Spark(Constants.PWMPorts.kShootMotor2);

      shooterGroup = new SpeedControllerGroup(shooterOne, shooterTwo);

      shooterEncoder = new Encoder(Constants.EncoderPorts.kShooterEncoderA, Constants.EncoderPorts.kShooterEncoderB);
  }

  public void set(double speed) {
    shooterGroup.set(speed);
  }

  public double getSpeed() {
    return shooterGroup.get();
  }

  @Override
  public void periodic() {
    rpm.monitor(shooterEncoder.get());
    SmartDashboard.putNumber("Shooter RPM", getRotationsPerMinute());
    SmartDashboard.putNumber("Shooter Get", shooterEncoder.getRate());

    if (Math.abs(SmartDashboard.getNumber("ShooterTargetAngle", 0.0)) < 2) {
      SmartDashboard.putBoolean("Should You Shoot?", true);
    } else {
      SmartDashboard.putBoolean("Should You Shoot?", false);
    }
  }

  public double getRotationsPerMinute() {
    return rpm.getRotationsPerMinute();
  }
}
