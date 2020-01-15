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


public class ClimberSubsystem extends SubsystemBase {
  /**
   * Creates a new subsystem.
   */

<<<<<<< HEAD
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
  

=======
  private final SpeedController climberX;
  private final SpeedController climberY;
  private final SpeedController climber;
  private final Encoder climberEncoder;

  public ClimberSubsystem() {
      climberX = new Spark(Constants.PWMConstants.kclimberXMotorPort);
      climberY = new Spark(Constants.PWMConstants.kclimberYMotorPort);
      climber = new SpeedControllerGroup(climberX, climberY);
      climberEncoder = new Encoder(Constants.EncoderConstants.kclimberEncoderA, Constants.EncoderConstants.kclimberEncoderA, false);

      SmartDashboard.putNumber("leftElevatorEncoderValue", leftElevatorEncoder.);
>>>>>>> 94aecb64e2af4cadac00eb579730619f85093ccc
  }

  public void Climb(double speed ) {
    // This method will be called once per scheduler run
    climber.set(speed);
  }

  public double GetRawEncoder() {
    return climberEncoder.getDistance();
  }
}
 