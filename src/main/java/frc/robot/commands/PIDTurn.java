/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.ChassisSubsystem;

/**
 * A command that will turn the robot to the specified angle.
 */
public class PIDTurn extends PIDCommand {

  public PIDTurn(double targetAngleDegrees, ChassisSubsystem chassis) {
    super(
      new PIDController(Constants.PIDTurn.kTurnP, Constants.PIDTurn.kTurnI, Constants.PIDTurn.kTurnD),
      // Close loop on heading
      chassis::GetGyroAngle,
      // Set reference to target
      targetAngleDegrees,
      // Pipe output to turn robot
      output -> {
        System.out.println("kTurnP: " + Constants.PIDTurn.kTurnP);
        System.out.println("PID Output: " + output);
        SmartDashboard.putNumber("PID Output", output);
        chassis.turnFeed(output);
      },
      // Require the drive
      chassis
    );
    System.out.println("New PIDTurn Created");

    // Set the controller to be continuous (because it is an angle controller)
    getController().enableContinuousInput(-180, 180);

    // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
    // setpoint before it is considered as having reached the reference
    getController()
        .setTolerance(Constants.PIDTurn.kTurnToleranceDeg, Constants.PIDTurn.kTurnRateToleranceDegPerS);
  }

  @Override
  public boolean isFinished() {
    // End when the controller is at the reference.
    return getController().atSetpoint();
  }
}