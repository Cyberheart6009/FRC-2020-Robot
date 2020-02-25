/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.ChassisSubsystem;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class TurnInPlaceCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ChassisSubsystem m_chassisSubsystem;

  //private Spark rightMotor;
  //private Spark leftMotor;

  //private double m_speed; 
  //private double m_angle;

  rightMotor = new Spark(0);
  leftMotor = new Spark(1);

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TurnInPlaceCommand(ChassisSubsystem subsystem, double m_angle, double m_speed, Spark rightMotor, Spark leftMotor) {
    m_chassisSubsystem = subsystem;
    m_rightMotor = rightMotor;
    m_leftMotor = leftMotor;
    m_speed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startDistance = m_chassisSubsystem.getDistance();
    startYaw = m_chassisSubsystem.getGyroYaw();
    finished = false;
    hitEnd = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double thresholdDistance = 0;
    SmartDashboard.putNumber("Yaw difference", startYaw - m_chassisSubsystem.getGyroYaw());
    if (m_chassisSubsystem.getDistance() - startDistance < m_distance - thresholdDistance){
        if (hitEnd == false) {
          m_chassisSubsystem.drive(m_speed, startYaw - m_chassisSubsystem.getGyroYaw());
        } else if (hitEnd == true) {
          m_chassisSubsystem.drive(m_speed, 0);
        }
    } else if (m_chassisSubsystem.getDistance() - startDistance > m_distance + thresholdDistance) {
        hitEnd = true;
        m_chassisSubsystem.drive(-0.6, 0);
    } else {
      finished = true;
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
    return finished;
  }
}
