/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import java.util.ArrayList;

public class ChassisSubsystem extends SubsystemBase {

  private final SpeedController rightBackMotor, leftFrontMotor,leftBackMotor, rightFrontMotor;
  private final SpeedControllerGroup leftMotors, rightMotors;
  
  private final Encoder rightEncoder;
  private final Encoder leftEncoder;

  private final NetworkTableInstance instance;
  private final NetworkTable table;

  AHRS gyro = new AHRS(SerialPort.Port.kMXP);

  private final DifferentialDrive driveBase;

  //public double k_speed;
  //public double k_angle;

  /**
   * Creates a new ExampleSubsystem.
   */ 

  public ChassisSubsystem() {
    leftFrontMotor = new Spark(0);
    rightFrontMotor = new Spark(2);
    leftBackMotor = new Spark(1);
    rightBackMotor = new Spark(3);

    leftMotors = new SpeedControllerGroup(leftFrontMotor, leftBackMotor);
    rightMotors = new SpeedControllerGroup(rightFrontMotor, rightBackMotor);

    instance = NetworkTableInstance.getDefault();
    table = instance.getTable("SmartDashboard");

    
    if (Constants.PWMConstants.kLeftBackMotorPort != null || Constants.PWMConstants.kRightBackMotorPort != null) {
      rightMotors.setInverted(true);
      driveBase = new DifferentialDrive(leftMotors, rightMotors);
      driveBase.setRightSideInverted(false);
    } else {
      rightFrontMotor.setInverted(true);
      driveBase = new DifferentialDrive(leftFrontMotor, rightFrontMotor);
      driveBase.setRightSideInverted(false);
    }
    


    
    leftEncoder = new Encoder(Constants.EncoderConstants.kLeftEncoderA, Constants.EncoderConstants.kLeftEncoderB, true);
    rightEncoder = new Encoder(Constants.EncoderConstants.kRightEncoderA, Constants.EncoderConstants.kRightEncoderB, false);
    
    gyro = new AHRS(SerialPort.Port.kMXP);
  }

  public void drive(double speed, double angle) {
    //k_speed = speed;
    //k_angle = angle;
    driveBase.arcadeDrive(speed, angle);
  }

  public double GetGyroAngle(){
    return gyro.getYaw();
  }
  
  
  public void GyroReset(){
    gyro.reset();
  }

  @Override
  public void periodic() {
   // This method will be called once per scheduler run
  }

  public double getDistance(){
    return ((double) (leftEncoder.get() + rightEncoder.get()) / (Constants.EncoderConstants.ENCODER_COUNTS_PER_INCH * 2));
  }

  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public ArrayList<Integer> getValue() {
    ArrayList<Integer> encoderDistances = new ArrayList<Integer>();
    encoderDistances.add(leftEncoder.get());
    encoderDistances.add(rightEncoder.get());
    return encoderDistances;
  }
}
