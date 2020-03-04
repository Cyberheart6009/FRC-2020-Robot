/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCommand extends CommandBase {
  private final ShooterSubsystem m_shooterSubsystem;

  public ShooterCommand(ShooterSubsystem subsystem) {
    m_shooterSubsystem = subsystem;

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