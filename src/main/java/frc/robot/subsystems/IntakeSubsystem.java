/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  /**
   * Creates a new subsystem.
   */

  private final Spark intake;
  private final DoubleSolenoid intakeLock;

  private double lockIntakeTimer;
  private boolean autoLockEnabled;

  public IntakeSubsystem() {
    intake = new Spark(Constants.PWMPorts.kIntakeMotor);
    intakeLock = new DoubleSolenoid(1, 2, 3);
    intakeLock.set(Value.kForward);

    autoLockEnabled = true;
  }

  public Spark getSuccMotor(){
    return intake;
  }

  public void setIntake(double speed) {
      intake.set(speed);
      lockIntakeTimer = System.currentTimeMillis();
  }

  public void stopIntake() {
    intake.set(0);
  }

  public void changeLock() {
    if (intakeLock.get() != Value.kForward) {
        intakeLock.set(Value.kForward);
    } else{
        intakeLock.set(Value.kReverse);
    }
    autoLockEnabled = false;
  }

  public void toggleAutoLock() {
    autoLockEnabled = !autoLockEnabled;
  }

  @Override
  public void periodic() {
    if (autoLockEnabled){
      // If open for greater than 5 seconds
      if ( System.currentTimeMillis() > lockIntakeTimer + 1000) {
        intakeLock.set(Value.kForward);
      } else if (intakeLock.get() != Value.kReverse) { // If open for less than 5 seconds and currently not open
        intakeLock.set(Value.kReverse);
      }
    }
  }
}
