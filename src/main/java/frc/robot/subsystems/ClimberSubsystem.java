/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;


public class ClimberSubsystem extends SubsystemBase {
  /**
   * Creates a new subsystem.
   */

  private final SpeedController elevatorX;
  private final SpeedController elevatorY;
  private final SpeedController elevator;
  private final Encoder leftElevatorEncoder;
  private final Encoder rightElevatorEncoder;
  private final NetworkTableInstance instance;
  private final NetworkTable table;

  public ClimberSubsystem() {
      elevatorX = new Spark(Constants.PWMConstants.kclimberXMotorPort);
      elevatorY = new Spark(Constants.PWMConstants.kclimberYMotorPort);
      elevator = new SpeedControllerGroup(elevatorX, elevatorY);
      leftElevatorEncoder = new Encoder(Constants.EncoderConstants.kElevatorEncoderA, Constants.EncoderConstants.kElevatorEncoderB, true);
      rightElevatorEncoder = new Encoder(Constants.EncoderConstants.kElevatorEncoderA, Constants.EncoderConstants.kElevatorEncoderB, false);

      instance = NetworkTableInstance.getDefault();
      table = instance.getTable("SmartDashboard");
  

  }

  public void Climb(double speed ) {
    // This method will be called once per scheduler run
    climber.set(speed);
  }

  public double GetRawEncoder() {
    return climberEncoder.getDistance();
  }
}
 