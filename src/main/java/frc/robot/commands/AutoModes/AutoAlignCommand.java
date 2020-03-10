package frc.robot.commands.AutoModes;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * A complex auto command that drives forward, releases a hatch, and then drives
 * backward.
 */
public class AutoAlignCommand extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   *
   * @param drive The drive subsystem this command will run on
   * @param hatch The hatch subsystem this command will run on
   */
  public ChassisSubsystem c_subsystem;

  public AutoAlignCommand(ChassisSubsystem chassis_subsystem) {
      c_subsystem = chassis_subsystem;
  addCommands(
          new PIDTurn(SmartDashboard.getNumber(Constants.SmartDashboardKeys.kShooterTargetAngle, 0),c_subsystem),
          new DriveDistanceCommand(SmartDashboard.getNumber(Constants.SmartDashboardKeys.kShooterDistance,0), 1, c_subsystem)
        );
  }

}

