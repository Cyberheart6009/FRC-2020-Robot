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
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {
  /**
   * Creates a new subsystem.
   */

  private final SpeedController elevatorX;
  private final SpeedController elevatorY;
  private final SpeedController elevator;
  private final Encoder elevatorEncoder;

  public ClimberSubsystem() {
      elevatorX = new Spark(Constants.ClimberConstants.kElevatorXMotorPort);
      elevatorY = new Spark(Constants.ClimberConstants.kElevatorYMotorPort);
      elevator = new SpeedControllerGroup(elevatorX, elevatorY);
      elevatorEncoder = new Encoder(Constants.EncoderConstants.kElevatorEncoder);
  }

  public void climb(double speed ) {
    // This method will be called once per scheduler run
    elevator.set(speed);
  }
}
 