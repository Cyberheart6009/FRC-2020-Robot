package frc.robot.commands.AutoModes;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * A complex auto command that drives forward, releases a hatch, and then drives
 * backward.
 */
public class Auto5BallShoot extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   *
   * @param drive The drive subsystem this command will run on
   * @param hatch The hatch subsystem this command will run on
   */
  public ShooterSubsystem s_subsystem;

  public Auto5BallShoot(ShooterSubsystem shooter_subsystem) {
      s_subsystem = shooter_subsystem;
  addCommands(
    new ShooterCommand(s_subsystem),
    new WaitCommand(0.5),
    new ShooterCommand(s_subsystem),
    new WaitCommand(0.5),
    new ShooterCommand(s_subsystem),
    new WaitCommand(0.5),
    new ShooterCommand(s_subsystem),
    new WaitCommand(0.5),
    new ShooterCommand(s_subsystem),
    new WaitCommand(0.5)
        );
  }

}

