/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class BallTrackerCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ChassisSubsystem m_chassisSubsystem;
  private final IntakeSubsystem m_intakeSubsystem;
  private int m_numBallsLeft;
  private boolean m_ballCollected;
  private double m_ballAngle;
  private double m_ballDistance;
  private boolean m_facingBall;
  private double currentYaw;
  private double newYaw;
  private double startYaw;


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public BallTrackerCommand(ChassisSubsystem subsystem, IntakeSubsystem intakeSubsystem, int numBallsLeft) {
    m_chassisSubsystem = subsystem;
    m_intakeSubsystem = intakeSubsystem;
    m_numBallsLeft = numBallsLeft;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }



  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_ballCollected = true;
    m_facingBall = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // get the ball's angle and distance relative to bot's current position
    if (m_ballCollected == true) {
      m_ballAngle = SmartDashboard.getNumber("BallAngle", 0.0);
      m_ballDistance = SmartDashboard.getNumber("BallDistance", 0.0);
      m_ballCollected = false;
    }

    // turn to ball
    if (m_facingBall == false) {
      if (m_chassisSubsystem.gyroAngle() > m_chassisSubsystem.gyroAngle() + m_ballAngle) {
        m_chassisSubsystem.drive(0, -0.8);
    } else if (m_chassisSubsystem.gyroAngle() < m_chassisSubsystem.gyroAngle() + m_ballAngle) {
        m_chassisSubsystem.drive(0, 0.8);
    } else {
        m_facingBall = true;
        startYaw = m_chassisSubsystem.gyroYaw();
      }
    }

    // Drive to ball
    if (m_facingBall == true) {
      if (m_chassisSubsystem.getDistance() < m_ballDistance + 20) {
        currentYaw = m_chassisSubsystem.gyroYaw();
        newYaw = -(startYaw - currentYaw) / 12;
        m_chassisSubsystem.drive(1, 0);
        m_intakeSubsystem.setIntake(1);
      } else {
        m_numBallsLeft --;
        m_ballCollected = true;
        m_facingBall = false;
      }

    }        
  }
    


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("ended");
    m_intakeSubsystem.stopIntake();

  }


  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (m_numBallsLeft == 0) {
      return true;
    } else {
      return false;
    }
  }
}
