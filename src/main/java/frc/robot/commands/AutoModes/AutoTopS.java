package frc.robot.commands.AutoModes;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * A complex auto command that drives forward, releases a hatch, and then drives
 * backward.
 */
public class AutoTopS extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   *
   * @param drive The drive subsystem this command will run on
   * @param hatch The hatch subsystem this command will run on
   */
  public ChassisSubsystem c_subsystem;
  public ShooterSubsystem s_subsystem;
  public IntakeSubsystem i_subsystem;

  public AutoTopS(ChassisSubsystem chassis_subsystem, ShooterSubsystem shooter_subsystem, IntakeSubsystem intake_subsystem) {
      c_subsystem = chassis_subsystem;
      s_subsystem = shooter_subsystem;
      i_subsystem = intake_subsystem;
    //placeholder because we do not have the value.
    DoubleSupplier offset;
  addCommands(
        /**this auto mode:
         * shoots three balls in capacity 
         */
        //moving 150 inches to be in prime positioning for shooting
        new DriveDistanceCommand(150, 1, c_subsystem),
        //wait for 0.5 seconds
        new WaitCommand(0.5),
        //turning around 180 degrees
        new TurnInPlaceCommand(c_subsystem, 1, 180),
        //wait for 0.5 seconds
        new WaitCommand(0.5),

        //aligning with the hole
        new AutoAlignCommand(c_subsystem),
        //shoots all three balls
        new Auto3BallShoot(s_subsystem)
        );
  }

}

