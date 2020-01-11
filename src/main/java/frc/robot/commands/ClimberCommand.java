/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberCommand extends CommandBase {
  private final ClimberSubsystem m_climberSubsystem;

  public ClimberCommand(ClimberSubsystem subsystem) {
    this.m_climberSubsystem = subsystem;

    addRequirements(subsystem);
  }

  @Override
  public void execute() {
    
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}