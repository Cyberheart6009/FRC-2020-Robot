/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ChassisSubsystem;;

public class DriveTrain extends CommandBase {
  private final ChassisSubsystem m_chassisSubsystem;
  private final DoubleSupplier m_speed;
  private final DoubleSupplier m_angle;

  public DriveTrain(DoubleSupplier speed, DoubleSupplier angle, ChassisSubsystem subsystem) {
    this.m_chassisSubsystem = subsystem;
    m_speed = speed;
    m_angle = angle;

    addRequirements(subsystem);
  }

  @Override
  public void execute() {
    m_chassisSubsystem.drive(m_speed.getAsDouble(), m_angle.getAsDouble());
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}