package frc.robot.commands.AutoModes;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * A complex auto command that drives forward, releases a hatch, and then drives
 * backward.
 */
public class Auto extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   *
   * @param drive The drive subsystem this command will run on
   * @param hatch The hatch subsystem this command will run on
   */
  public ChassisSubsystem c_subsystem;
  public ShooterSubsystem s_subsystem;
  public IntakeSubsystem i_subsystem;

  public Auto(ChassisSubsystem chassis_subsystem, ShooterSubsystem shooter_subsystem, IntakeSubsystem intake_subsystem) {
      c_subsystem = chassis_subsystem;
      s_subsystem = shooter_subsystem;
      i_subsystem = intake_subsystem;
    //placeholder because we do not have the value.
    DoubleSupplier offset;
	addCommands(
        //Commands are in order based on the diagram 

        // Drive forward the specified distance (pink)
        new DriveDistanceCommand(c_subsystem, 245.9, 1),
        // Turns the robot a specified angle (preparing for orange)
        new TurnInPlaceCommand(c_subsystem, 1, 270),
        //Drives backwards into the remaining two balls while also sucking at the same time (orange)
        new ParallelCommandGroup(
          new DriveDistanceCommand(c_subsystem, 80.8, -1),
          new SuccCommand(i_subsystem)
        ),
        //Drives straight back (orange)
        new DriveDistanceCommand(c_subsystem, 80.8, 1),
        //Rotates 90 degrees (preparing for yellow)
        new TurnInPlaceCommand(c_subsystem, 1, 90),
        //Drives forward towards the rocket ship (yellow and lime for points)
        new DriveDistanceCommand(c_subsystem, (302.6 + 39.4), 1),
        //Waits for 0.5 seconds
        new WaitCommand(0.5),
        //turns backwards (to properly shoot) (out of lime now)
        new DriveDistanceCommand(c_subsystem, 39.4, -1),
        // Aligns with the rocket ship
        new FollowTarget(c_subsystem, offset),
        // Shoot the ball
        new ShooterCommand(s_subsystem),
        //Turn towards where the balls are (preparing for green)
        new TurnInPlaceCommand(c_subsystem,1,270),
        //moves backwards (green)
        new DriveDistanceCommand(c_subsystem, 80.8, -1),
        //turns to face the balls (preparing for blue)
        new TurnInPlaceCommand(c_subsystem, 1, 90),
        //Drive backwards to specified distance while continuously having the intake motors sucking to collect balls (blue)
        new ParallelCommandGroup(
          new DriveDistanceCommand(c_subsystem, 224.8, -1),
          new SuccCommand(i_subsystem)
        ),
        //drive forward (purple)
        new DriveDistanceCommand(c_subsystem, 224.8, 1),
        //turn left (preparing for magenta)
        new TurnInPlaceCommand(c_subsystem, 1, 270),
        //moves forward a bit (magenta)
        new DriveDistanceCommand(c_subsystem, 80.8, 1),
        //turns right (preparing to shoot)
        new TurnInPlaceCommand(c_subsystem, 1, 90),
        //aligns with the rocket ship
        new FollowTarget(c_subsystem, offset),
        //shoots the ball
        new ShooterCommand(s_subsystem)
        //... more commands for auto
        
        
        );
  }

}

