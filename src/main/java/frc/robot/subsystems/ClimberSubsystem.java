/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class ClimberSubsystem extends SubsystemBase {
  public static enum LockState {LOCKED, UNLOCKED};
  
  /**
   * Creates a new subsystem.
   */

  private final Spark climber;
  private final Servo lockServo;

  public ClimberSubsystem() {
      climber = new Spark(Constants.PWMPorts.kClimberMotor);
      lockServo = new Servo(Constants.PWMPorts.kClimberServo);
      lock();
  }

  public void climb(double speed ) {
    climber.set(speed);
  }

  @Override
  public void periodic() {
  }

  public void lock() {
    lockServo.setAngle(140*0.666666);
  }

  public void unlock() {
    lockServo.setAngle(0);
  }
}
 