package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.*;

/**
 * A complex auto command that drives forward, releases a hatch, and then drives
 * backward.
 */
public class AlignAndShoot extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   *
   * @param drive The drive subsystem this command will run on
   * @param hatch The hatch subsystem this command will run on
   */
  public AlignAndShoot(ChassisSubsystem chassisSubsystem, ShooterSubsystem shooterSubsystem, ShooterFeederSubsystem shooterFeederSubsystem) {
  addCommands(
          //new TurnInPlaceCommand(chassisSubsystem, 1, SmartDashboard.getNumber(Constants.SmartDashboardKeys.kShooterTargetAngle, 0)).withTimeout(2),
          new RunCommand(() -> shooterSubsystem.set(1), shooterSubsystem).withTimeout(1),
          new ParallelCommandGroup (
              new RunCommand(() -> shooterSubsystem.set(1), shooterSubsystem),
              new RunCommand(() -> {
                shooterFeederSubsystem.setFeed(1);
                shooterFeederSubsystem.setAntiJam(1);
              }, shooterFeederSubsystem))
        );
  }

}