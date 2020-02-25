package frc.robot;

import java.util.function.DoubleSupplier;

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
  public ChassisSubsystem m_subsystem;

  public Auto(ChassisSubsystem subsystem) {
      m_subsystem = subsystem;
    DoubleSupplier offset;
	addCommands(
        // Drive forward the specified distance
        new DriveDistanceCommand(m_subsystem, 5, 5),
        // Turns the robot a specified angle
        new TurnInPlaceCommand(m_subsystem, 1, 100),
        // Aligns with the rocket ship
        new FollowTarget(m_subsystem, offset),
        // Shoot the ball
        new ShooterCommand(m_subsystem)
        
        //... more commands for auto
        
        
        );
  }

}