package frc.robot;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * A complex auto command that drives forward, releases a hatch, and then drives backward.
 */
public class Auto extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   *
   * @param drive The drive subsystem this command will run on
   * @param hatch The hatch subsystem this command will run on
   */
  public Auto(ChassisSubsystem drive) {
    addCommands(
        // Drive forward the specified distance
        new DriveDistance(AutoConstants.kAutoDriveDistanceInches, AutoConstants.kAutoDriveSpeed,
                          drive),

        // Release the hatch
        new ReleaseHatch(hatch),

        // Drive backward the specified distance
        new DriveDistance(AutoConstants.kAutoBackupDistanceInches, -AutoConstants.kAutoDriveSpeed,
                          drive));
  }

}