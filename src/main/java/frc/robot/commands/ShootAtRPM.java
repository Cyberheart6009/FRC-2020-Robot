/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootAtRPM extends CommandBase {
  private final ShooterSubsystem m_shooterSubsystem;

  private final double m_targetRPM;

  public ShootAtRPM(ShooterSubsystem subsystem, double targetRPM) {
    m_shooterSubsystem = subsystem;
    m_targetRPM = targetRPM;

    addRequirements(subsystem);
  }

  @Override
  public void execute() {
    double difference = m_targetRPM - m_shooterSubsystem.getRotationsPerMinute();
    if (difference > 0) {
      m_shooterSubsystem.set(m_shooterSubsystem.getSpeed() + 0.01);
    } else if (difference < 0) {
      m_shooterSubsystem.set(m_shooterSubsystem.getSpeed() - 0.01);
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  public double inputOutputCurve(double input) {
    return Math.sqrt(input);
  }
}