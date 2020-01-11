/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ChassisSubsystem extends SubsystemBase {

  private final SpeedController rightMotor;
  private final SpeedController leftMotor;
  private final SpeedControllerGroup motors;
  
  private final Encoder rightEncoder;
  private final Encoder leftEncoder;

  private final DifferentialDrive driveBase;

  /**
   * Creates a new ExampleSubsystem.
   */ 

  public ChassisSubsystem() {
    leftMotor = new Spark(Constants.MotorConstants.kLeftMotorPort);
    rightMotor = new Spark(Constants.MotorConstants.kRightMotorPort);  
    rightMotor.setInverted(true);
    motors = new SpeedControllerGroup(rightMotor, leftMotor);

    driveBase = new DifferentialDrive(leftMotor, rightMotor);
    driveBase.setRightSideInverted(false);

    
    leftEncoder = new Encoder(Constants.EncoderConstants.kLeftEncoderA, Constants.EncoderConstants.kLeftEncoderB, true);
    rightEncoder = new Encoder(Constants.EncoderConstants.kRightEncoderA, Constants.EncoderConstants.kRightEncoderB, false);
  }

  public void drive(double speed, double angle) {
    driveBase.arcadeDrive(speed, angle);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  
}
