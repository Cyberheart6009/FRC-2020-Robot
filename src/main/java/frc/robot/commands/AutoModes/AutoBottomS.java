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
public class AutoBottomS extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   *
   * @param drive The drive subsystem this command will run on
   * @param hatch The hatch subsystem this command will run on
   */
  public ChassisSubsystem c_subsystem;
  public ShooterSubsystem s_subsystem;
  public IntakeSubsystem i_subsystem;

  public AutoBottomS(ChassisSubsystem chassis_subsystem, ShooterSubsystem shooter_subsystem, IntakeSubsystem intake_subsystem) {
      c_subsystem = chassis_subsystem;
      s_subsystem = shooter_subsystem;
      i_subsystem = intake_subsystem;
    //placeholder because we do not have the value.
    DoubleSupplier offset;
  addCommands(
        /**this auto mode:
         * drives from starting position to the holes
         * shoots the three balls that the robot is loaded with 
         */

        //moving 175 inches to be in prime positioning for shooting
        new DriveDistanceCommand(150, 1, c_subsystem),
        //wait for 0.5 seconds
        new WaitCommand(0.5),
        //turning around 270 degrees CW (90 degrees CCW)
        new TurnInPlaceCommand(c_subsystem, 1, 270),
        //wait for 0.5 seconds
        new WaitCommand(0.5),
        //drives towards the hole
        new DriveDistanceCommand(132, 1, c_subsystem),
        //temp wait command
        new WaitCommand(0.5),
        //turns 270 degrees
        new TurnInPlaceCommand(c_subsystem, 1, 270),
        //temp wait command
        new WaitCommand(0.5),
        //aligning with the hole
        new AutoAlignCommand(c_subsystem),
        //shoots all three balls
        new Auto3BallShoot(s_subsystem)
        );
  }

}

