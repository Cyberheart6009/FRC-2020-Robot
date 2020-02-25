/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.ChassisSubsystem;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class TurnInPlaceCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ChassisSubsystem m_chassisSubsystem;
  private Spark rightMotor;
  private Spark leftMotor;

  private double m_speed; 
  private double m_angle;

  private double targetAngle;



  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TurnInPlaceCommand(ChassisSubsystem subsystem, double speed, double angle) {
    m_chassisSubsystem = subsystem;
    m_angle = angle;
    targetAngle = m_chassisSubsystem.GetGyroAngle() + m_angle;
    m_speed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }



  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    while (m_chassisSubsystem.GetGyroAngle() != targetAngle) {

        if (m_angle > 0) {
            m_chassisSubsystem.getRightMotor().set(-m_speed);
            m_chassisSubsystem.getLeftMotor().set(m_speed);
        } else if (m_angle < 0) {
            m_chassisSubsystem.getRightMotor().set(m_speed);
            m_chassisSubsystem.getLeftMotor().set(-m_speed);
        }
    }
        
    }
    

  


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("ended");
  }



  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
