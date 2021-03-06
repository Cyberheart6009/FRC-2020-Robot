/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.ChassisSubsystem.GearState;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class TurnInPlaceCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final double threshold = 3;

  private final ChassisSubsystem m_chassisSubsystem;
  private GearState startGear;
  private double m_speed;
  private double m_angleTarget;

  private double targetAngle;



  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TurnInPlaceCommand(ChassisSubsystem subsystem, double speed, double angle) {
    m_chassisSubsystem = subsystem;
    m_speed = speed;
    m_angleTarget = angle;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }



  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    targetAngle = m_chassisSubsystem.gyroAngle() + m_angleTarget;
    startGear = m_chassisSubsystem.gearState;
    m_chassisSubsystem.gearDown();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_chassisSubsystem.gyroAngle() > targetAngle) {
      m_chassisSubsystem.drive(0, m_speed);
    } else {
      m_chassisSubsystem.drive(0, -m_speed);
    }
        
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("ended");
    m_chassisSubsystem.gearSet(startGear);
  }



  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (m_chassisSubsystem.gyroAngle() > targetAngle - threshold && m_chassisSubsystem.gyroAngle() < targetAngle + threshold) {
      return true;
    } else {
      return false;
    }
  }
}
