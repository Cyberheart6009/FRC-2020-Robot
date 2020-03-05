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
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.Constants;
import frc.robot.util.RPMMonitor;

import java.util.ArrayList;

public class ChassisSubsystem extends SubsystemBase {

  private final SpeedControllerGroup leftMotors, rightMotors;

  private final Compressor compressor;
  private final DoubleSolenoid shifter;
  
  private final Encoder rightEncoder;
  private final Encoder leftEncoder;

  private final NetworkTableInstance instance;
  private final NetworkTable table;

  private final AHRS gyro;

  private final DifferentialDrive driveBase;

  private final RPMMonitor rpm = new RPMMonitor();

  /**
   * Creates a new ExampleSubsystem.
   */ 

  public ChassisSubsystem() {
    // Preparing Network Tables
    instance = NetworkTableInstance.getDefault();
    table = instance.getTable("SmartDashboard");

    // Sets sparks to Speed Controller
    if (Constants.PWMPorts.kLeftMotors.length == 2 || Constants.PWMPorts.kRightMotors.length == 2) {
      // Sets sparks if 2-per-side
      leftMotors = new SpeedControllerGroup(new Spark(Constants.PWMPorts.kLeftMotors[0]), new Spark(Constants.PWMPorts.kLeftMotors[1]));
      rightMotors = new SpeedControllerGroup(new Spark(Constants.PWMPorts.kRightMotors[0]), new Spark(Constants.PWMPorts.kRightMotors[1]));
    } else {
      // Sets Sparks if 1-per-Side
      leftMotors = new SpeedControllerGroup(new Spark(Constants.PWMPorts.kLeftMotors[0]));
      rightMotors = new SpeedControllerGroup(new Spark(Constants.PWMPorts.kRightMotors[0]));
    }

    driveBase = new DifferentialDrive(leftMotors, rightMotors);
    driveBase.setRightSideInverted(true);
    // Initializes compressor & shifter
    compressor = new Compressor(1);
    compressor.enabled();
    shifter = new DoubleSolenoid(1, 0, 1);

    // Assigns encoders to their ports
    leftEncoder = new Encoder(Constants.EncoderPorts.kLeftEncoderA, Constants.EncoderPorts.kLeftEncoderB, false);
    rightEncoder = new Encoder(Constants.EncoderPorts.kRightEncoderA, Constants.EncoderPorts.kRightEncoderB, true);

    // Sets the Gyro Port
    gyro = new AHRS(SPI.Port.kMXP);

    driveBase.setMaxOutput(1);
  }

  public void drive(double speed, double angle) {
    //k_speed = speed;
    //k_angle = angle;
    driveBase.arcadeDrive(speed, angle);
    System.out.println(angle);
  }

  public void sideDrive(double leftMotorSpeed, double rightMotorSpeed) {
    leftMotors.set(leftMotorSpeed);
    rightMotors.set(-rightMotorSpeed);
  }

  public double GetGyroAngle(){
    return gyro.getAngle();
  }

  public void GyroReset(){
    System.out.println("Resetting Gyro");
    gyro.reset();
  }

  public void changeGear() {
    if (shifter.get() == Value.kForward) {
      shifter.set(Value.kReverse);
    } else {
      shifter.set(Value.kForward);
    }
  }

  public void gearUp() {
    if (shifter.get() != Value.kForward) {
      shifter.set(Value.kForward);
    }
  }

  public void gearDown() {
    if (shifter.get() != Value.kReverse) {
      shifter.set(Value.kReverse);
    }
  }

  public double getGyroYaw() {
    return gyro.getYaw();
  }

  @Override
  public void periodic() {
    rpm.monitor(averageEncoders());

    /*
    if (getRotationsPerMinute() > 1000) {
      gearUp();
    } else if (getRotationsPerMinute() < 1000) {
      gearDown();
    }*/

    // Puts a Number of variables to SmartDashboard
    SmartDashboard.putNumber("Roll", gyro.getRoll());
    SmartDashboard.putNumber("Yaw", gyro.getYaw());
    SmartDashboard.putNumber("Pitch", gyro.getPitch());
    SmartDashboard.putNumber("Angle", gyro.getAngle());
    SmartDashboard.putNumber("X", gyro.getRawGyroX());
    SmartDashboard.putNumber("Distance 2", getDistance());
    SmartDashboard.putNumber("Left Encoder", leftEncoder.get());
    SmartDashboard.putNumber("Right Encoder", rightEncoder.get());
  }

  public double averageEncoders() {
    return (leftEncoder.get() + rightEncoder.get()) / 2;
  }

  public double getDistance(){
    double distance = (leftEncoder.get() + rightEncoder.get()) / (Constants.EncoderPorts.ENCODER_COUNTS_PER_INCH * 2);
    SmartDashboard.putNumber("Distance", distance);
    return (distance);
  }

  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public double getRotationsPerMinute() {
    return rpm.getRotationsPerMinute();
  }
}
