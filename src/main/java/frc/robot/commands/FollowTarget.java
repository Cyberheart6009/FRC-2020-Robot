/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class FollowTarget extends CommandBase {
  private final ChassisSubsystem m_ChassisSubsystem;
  private DoubleSupplier m_offset;
  private DoubleSupplier m_distance;

  private double m_endDistance;

  public FollowTarget(ChassisSubsystem subsystem, DoubleSupplier offset, DoubleSupplier distance, double endDistance) {
    this.m_ChassisSubsystem = subsystem;
    this.m_offset = offset;
    this.m_distance = distance;

    this.m_endDistance = endDistance;

    addRequirements(subsystem);
  }

  @Override
  public void execute() {
    if (m_distance.getAsDouble() > m_endDistance) {
      m_ChassisSubsystem.drive(0.7, m_offset.getAsDouble());
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}