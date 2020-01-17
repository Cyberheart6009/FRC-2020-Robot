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
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class ClimberSubsystem extends SubsystemBase {
  /**
   * Creates a new subsystem.
   */

  private final SpeedController elevatorX;
  private final SpeedController elevatorY;
  private final SpeedController elevator;
  private final Encoder leftElevatorEncoder;
  private final Encoder rightElevatorEncoder;

  public ClimberSubsystem() {
      elevatorX = new Spark(Constants.ClimberConstants.kElevatorXMotorPort);
      elevatorY = new Spark(Constants.ClimberConstants.kElevatorYMotorPort);
      elevator = new SpeedControllerGroup(elevatorX, elevatorY);
      leftElevatorEncoder = new Encoder(Constants.EncoderConstants.kElevatorEncoderA, Constants.EncoderConstants.kElevatorEncoderB, true);
      rightElevatorEncoder = new Encoder(Constants.EncoderConstants.kElevatorEncoderA, Constants.EncoderConstants.kElevatorEncoderB, false);

      instance = NetworkTableInstance.getDefault();
      table = instance.getTable("SmartDashboard");

      class ElevatorEncoder;
      private Shuffleboard.getTab("Values");




  

  }

  public void Climb(double speed ) {
    // This method will be called once per scheduler run
    climber.set(speed);
  }

  public double GetRawEncoder() {
    return climberEncoder.getDistance();
  }
}
 