package frc.robot.commands.AutoModes;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * A complex auto command that drives forward, releases a hatch, and then drives
 * backward.
 */
public class TestVisionSequence extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   *
   * @param drive The drive subsystem this command will run on
   * @param hatch The hatch subsystem this command will run on
   */
  public ChassisSubsystem m_subsystem;

  public TestVisionSequence(double angle, double distance, ChassisSubsystem chassisSubsystem) {
	  addCommands(
      new PIDTurn(SmartDashboard.getNumber("TargetAngle", 0), chassisSubsystem),
      new DriveDistanceCommand(SmartDashboard.getNumber("DistanceHeight", 0) - 100, 1, chassisSubsystem)
    );
  }
}