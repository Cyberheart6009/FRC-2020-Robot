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
import edu.wpi.first.wpilibj.PowerDistributionPanel;
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

  private final PowerDistributionPanel pdp = new PowerDistributionPanel();

  public GearState gearState = GearState.GEAR_LOW; 
  
  private final SpeedControllerGroup leftMotors, rightMotors;

  private final Compressor compressor;
  private final DoubleSolenoid shifter;
  
  private final Encoder rightEncoder;
  private final Encoder leftEncoder;

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
    // Sets left sparks depending on conrroller presence
    if (Constants.PWMPorts.kLeftMotors.length == 2) {
      leftMotors = new SpeedControllerGroup(new Spark(Constants.PWMPorts.kLeftMotors[0]), new Spark(Constants.PWMPorts.kLeftMotors[1]));
    } else {
      leftMotors = new SpeedControllerGroup(new Spark(Constants.PWMPorts.kLeftMotors[0]));
    }

    // Sets right sparks depending on controller presence
    if (Constants.PWMPorts.kRightMotors.length == 2) {
      rightMotors = new SpeedControllerGroup(new Spark(Constants.PWMPorts.kRightMotors[0]), new Spark(Constants.PWMPorts.kRightMotors[1]));
    } else {
      rightMotors = new SpeedControllerGroup(new Spark(Constants.PWMPorts.kRightMotors[0]));
    }

    driveBase = new DifferentialDrive(leftMotors, rightMotors);

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
    driveBase.arcadeDrive(speed, angle);
  }

  public void sideDrive(double leftMotorSpeed, double rightMotorSpeed) {
    leftMotors.set(leftMotorSpeed);
    rightMotors.set(rightMotorSpeed);
  }

  public double gyroAngle(){
    return gyro.getAngle();
  }

  public void gyroReset(){
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

  public void gearSet(GearState state) {
    if (state == GearState.GEAR_HIGH) {
      gearUp();
    } else {
      gearDown();
    }
  }

  public double gyroYaw() {
    return gyro.getYaw();
  }

  @Override
  public void periodic() {
    rpm.monitor(encoderAverage());
    
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
    SmartDashboard.putNumberArray("ChassisDisplacement", new double[]{gyro.getDisplacementX() * 39.37, gyro.getDisplacementY() * 39.37});
  }

  public double encoderAverage() {
    return (leftEncoder.get() + rightEncoder.get()) / 2;
  }

  public double getDistance(){
    return (leftEncoder.get() + rightEncoder.get()) / (Constants.EncoderPorts.ENCODER_COUNTS_PER_INCH * 2);
  }

  public void encoderReset() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public double getRotationsPerMinute() {
    return rpm.getRotationsPerMinute();
  }
}