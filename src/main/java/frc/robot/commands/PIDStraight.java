/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*
package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.ChassisSubsystem;

public class PIDStraight extends PIDCommand {

  public PIDStraight(double distance, ChassisSubsystem chassis) {
      new PIDController(Constants.PIDTurn.kTurnP, Constants.PIDTurn.kTurnI, Constants.PIDTurn.kTurnD),
      // Close loop on heading
      chassis::GetGyroAngle,
      // Set reference to target
      distance,
      // Pipe output to turn robot
      output -> {
        System.out.println("kTurnPStraight: " + Constants.PIDTurn.kTurnP);
        System.out.println("PIDStraight Output: " + output);
        SmartDashboard.putNumber("PIDStraight Output", output);
        chassis.drive(output, 0);
    System.out.println("New PIDTurn Created");

    // Set the controller to be continuous (because it is an angle controller)
    getController().disableContinuousInput();

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
*/