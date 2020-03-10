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
import edu.wpi.first.wpilibj.SPI;
import frc.robot.Constants;
import frc.robot.util.RPMMonitor;

public class ChassisSubsystem extends SubsystemBase {
  public static enum GearState {GEAR_HIGH, GEAR_LOW}

  public GearState gearState = GearState.GEAR_LOW; 
  
  private final SpeedControllerGroup leftMotors, rightMotors;

  private final Compressor compressor;
  private final DoubleSolenoid shifter;
  
  private final Encoder rightEncoder;
  private final Encoder leftEncoder;

  private final NetworkTableInstance instance;
  private final NetworkTable table;

  private final AHRS gyro;

  private final DifferentialDrive driveBase;

  private final RPMMonitor rpm = new RPMMonitor(360);

  private final double maxLow = 0;
  private final double minHigh = 0;
  private double autoShiftTimer = 0;

  /**
   * Creates a new ExampleSubsystem.
   */ 
  public ChassisSubsystem() {
    // Preparing Network Tables
    instance = NetworkTableInstance.getDefault();
    table = instance.getTable("SmartDashboard");

    // Sets sparks to Speed Controller
    if (Constants.PWMPorts.kLeftMotors.length == 2) {
      // Sets sparks if 2-per-side
      leftMotors = new SpeedControllerGroup(new Spark(Constants.PWMPorts.kLeftMotors[0]), new Spark(Constants.PWMPorts.kLeftMotors[1]));
    } else {
      // Sets Sparks if 1-per-Side
      leftMotors = new SpeedControllerGroup(new Spark(Constants.PWMPorts.kLeftMotors[0]));
    }

    if (Constants.PWMPorts.kRightMotors.length == 2) {
      rightMotors = new SpeedControllerGroup(new Spark(Constants.PWMPorts.kRightMotors[0]), new Spark(Constants.PWMPorts.kRightMotors[1]));
    } else {
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

    gearDown();
  }

  public void drive(double speed, double angle) {
    //k_speed = speed;
    //k_angle = angle;
    driveBase.arcadeDrive(-speed, angle);
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
    if (shifter.get() != Value.kReverse) {
      gearDown();
    } else {
      gearUp();
    }
  }

  public void gearUp() {
    if (shifter.get() != Value.kForward) {
      gearState = GearState.GEAR_HIGH;
      shifter.set(Value.kForward);
      SmartDashboard.putBoolean("ShiftUp", true);
    }
  }

  public void gearDown() {
    if (shifter.get() != Value.kReverse) {
      gearState = GearState.GEAR_LOW;
      shifter.set(Value.kReverse);
      SmartDashboard.putBoolean("ShiftUp", false);
    }
  }

  public void setGear(GearState state) {
    if (state == GearState.GEAR_HIGH) {
      gearUp();
    } else {
      gearDown();
    }
  }

  public double getGyroYaw() {
    return gyro.getYaw();
  }

  @Override
  public void periodic() {
    rpm.monitor(averageEncoders());
    
    if (Constants.Control.kManualOverride) {
      if (gearState == GearState.GEAR_LOW){
        if (getRotationsPerMinute() > maxLow - 100) {
          if (System.currentTimeMillis() - autoShiftTimer > 5000 ) {
            gearUp();
            autoShiftTimer = System.currentTimeMillis();
          }
        } else {
          autoShiftTimer = System.currentTimeMillis();
        }
      } else {
        if (getRotationsPerMinute() < minHigh + 100) {
          if (System.currentTimeMillis() - autoShiftTimer > 5000 ) {
            gearUp();
            autoShiftTimer = System.currentTimeMillis();
          }
        } else {
          autoShiftTimer = System.currentTimeMillis();
        }
      }
    }

    // Puts a Number of variables to SmartDashboard
    SmartDashboard.putNumber("Gyro", gyro.getAngle());
    SmartDashboard.putNumber("Left Encoder", leftEncoder.get());
    SmartDashboard.putNumber("Right Encoder", rightEncoder.get());
    SmartDashboard.putNumber("Chassis RPM", getRotationsPerMinute());
    SmartDashboard.putNumber("Right Out", rightMotors.get());
    SmartDashboard.putNumber("Left Out", leftMotors.get());
    SmartDashboard.putNumberArray("ChassisDisplacement", new double[]{gyro.getDisplacementX() * 39.37, gyro.getDisplacementY() * 39.37});
  }

  public double averageEncoders() {
    return (leftEncoder.get() + rightEncoder.get()) / 2;
  }

  public double getDistance(){
    double distance = (leftEncoder.get() + rightEncoder.get()) / (Constants.EncoderPorts.ENCODER_COUNTS_PER_INCH * 2);
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