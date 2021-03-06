/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * A command that will turn the robot to the specified angle.
 */
public class PIDShoot extends PIDCommand {

  public PIDShoot(double targetRPM, ShooterSubsystem shooter) {
    super(
      new PIDController(Constants.PIDShoot.kTurnP, Constants.PIDShoot.kTurnI, Constants.PIDShoot.kTurnD),
      // Close loop on heading
      shooter::getRotationsPerMinute,
      // Set reference to target
      targetRPM,
      // Pipe output to turn robot
      output -> {
        shooter.set(output);;
      },
      // Require the drive
      shooter
    );

    // Set the controller to be continuous (because it is an angle controller)
    getController().enableContinuousInput(-180, 180);

    // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
    // setpoint before it is considered as having reached the reference
    getController()
        .setTolerance(Constants.PIDShoot.kTurnToleranceDeg, Constants.PIDShoot.kTurnRateToleranceDegPerS);
  }

  @Override
  public boolean isFinished() {
    // End when the controller is at the reference.
    return getController().atSetpoint();
  }
}