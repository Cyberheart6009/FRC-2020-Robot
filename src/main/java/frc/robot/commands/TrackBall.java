/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class TrackBall extends CommandBase {
  private final ChassisSubsystem m_ChassisSubsystem;
  private boolean finished;
  private double turnAngle;
  private double autoDistance;
  private double currentAngle;



  public TrackBall(ChassisSubsystem subsystem) {
    this.m_ChassisSubsystem = subsystem;
    addRequirements(subsystem);
  }


  @Override
  public void initialize() {
    finished = false;
    turnAngle = SmartDashboard.getNumber("TurnAngle", 0.0);
    autoDistance = SmartDashboard.getNumber("AutoDistance", 0.0);
    currentAngle = m_ChassisSubsystem.GetGyroAngle();
  }

  @Override
  public void execute() {

    if (turnAngle > 0) {
        while (currentAngle != currentAngle + turnAngle) {
            m_ChassisSubsystem.getRightMotor().set(1);
            m_ChassisSubsystem.getLeftMotor().set(-1);
        }
    } else if (turnAngle < 0) {
        while (currentAngle != currentAngle + turnAngle) {
            m_ChassisSubsystem.getRightMotor().set(-1);
            m_ChassisSubsystem.getLeftMotor().set(1);
        }
    }
    
    m_ChassisSubsystem.resetEncoders();

    // If i move backwards will that register as negative with the encoders
    while (m_ChassisSubsystem.getDistance() < autoDistance) {
        m_ChassisSubsystem.drive(-1, 0);
    }

    finished = true;


}

  @Override
  public boolean isFinished() {
    return finished;
  }
}