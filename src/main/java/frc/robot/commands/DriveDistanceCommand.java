/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.ChassisSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class DriveDistanceCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ChassisSubsystem m_chassisSubsystem;
  private double m_distance; 

  private double m_speed; 
  private double m_angle;

  private double startDistance;
  private double startYaw;

  private boolean finished = false;

  private boolean hitEnd = false;

  private double currentYaw;
  private double newYaw;



  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveDistanceCommand(double distance, double speed, ChassisSubsystem subsystem) {
    m_chassisSubsystem = subsystem;
    m_distance = distance;
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
    SmartDashboard.putNumber("Yaw difference", startYaw - m_chassisSubsystem.getGyroYaw());
    /*
    double thresholdDistance = 0;
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
    */
    if (Math.abs(m_chassisSubsystem.getDistance() - startDistance) < Math.abs(m_distance)) {
      currentYaw = m_chassisSubsystem.getGyroYaw();
      
      newYaw = -(startYaw - currentYaw) / 12;
      SmartDashboard.putNumber("Difference", newYaw);
      m_chassisSubsystem.drive(m_speed, newYaw);
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
    if (m_chassisSubsystem.getDistance() - startDistance > m_distance) {
      return true;
    } else {
      return false;
    }
  }
}
