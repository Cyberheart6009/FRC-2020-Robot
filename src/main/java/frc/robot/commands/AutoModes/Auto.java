package frc.robot.commands.AutoModes;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

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

    // Place holder while we don't know the value
    DoubleSupplier offset;
	addCommands(
        /*
        // Drive forward the specified distance
        new DriveDistanceCommand(c_subsystem, 1, 1),
        // Turns the robot a specified angle
        new TurnInPlaceCommand(c_subsystem, 1, 100),
        // Aligns with the rocket ship
        //new FollowTarget(c_subsystem, offset),
        // Shoot the ball
        new ShooterCommand(s_subsystem),
        
        // Turn to have the back of the bot face the trench run
        new TurnInPlaceCommand(c_subsystem, 1, -60),

        new ParallelCommandGroup(
          new DriveDistanceCommand(c_subsystem, 1, -1),
          new SuccCommand(i_subsystem)
        ),

        new DriveDistanceCommand(c_subsystem, 1, 1),
        new FollowTarget(c_subsystem, offset),
        new ShooterCommand(s_subsystem)     */   
        );
  }
}