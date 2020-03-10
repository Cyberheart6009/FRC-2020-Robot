package frc.robot.commands.AutoModes;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * A complex auto command that drives forward, releases a hatch, and then drives
 * backward.
 */
public class AutoTopSPS extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   *
   * @param drive The drive subsystem this command will run on
   * @param hatch The hatch subsystem this command will run on
   */
  public ChassisSubsystem c_subsystem;
  public ShooterSubsystem s_subsystem;
  public IntakeSubsystem i_subsystem;

  public AutoTopSPS(ChassisSubsystem chassis_subsystem, ShooterSubsystem shooter_subsystem, IntakeSubsystem intake_subsystem) {
      c_subsystem = chassis_subsystem;
      s_subsystem = shooter_subsystem;
      i_subsystem = intake_subsystem;
    //placeholder because we do not have the value.
    DoubleSupplier offset;
	addCommands(
    /**this auto command:
     * shoots 3 balls in capacity
     * drives towards and picks up the 3 balls by the colour wheel
     * shoots those three balls
     */
        //moving 175 pixels to be in prime positioning for shooting
        new DriveDistanceCommand(150, 1, c_subsystem),
        //wait for 0.5 seconds
        new WaitCommand(0.5),
        //turning around 180 degrees
        new TurnInPlaceCommand(c_subsystem, 1, 180),
        //wait for 0.5 seconds
        new WaitCommand(0.5),
        //aligning with the hole
        new AutoAlignCommand(c_subsystem),
        //shooting all three balls in capacity, and waiting 0.25 seconds in between each shot
        new Auto3BallShoot(s_subsystem),
        //move back 25 inches to make sure my code isn't broken 
        new DriveDistanceCommand(25, -1, c_subsystem),
        //temp wait command
        new WaitCommand(0.5),
        //angle required to rotate towards the line of balls (with the colour wheel)
        new TurnInPlaceCommand(c_subsystem, 1, 313),
        //waiting 0.5 seconds
        new WaitCommand(0.5),
        //driving forward the distance from the shooting point to the ball wheel
        new DriveDistanceCommand(Math.sqrt(6736), 1, c_subsystem),
        
        //This is where the ball find command would go

        //turning in place towards where to shoot (setting up for the follow target command)
        new TurnInPlaceCommand(c_subsystem, 1, 345),
        //driving the distance towards the shooting area
        new DriveDistanceCommand(Math.sqrt(19252), 1, c_subsystem),
        //offset target to go towards where to shoot the ball
        new AutoAlignCommand(c_subsystem),
        //shooting all 3 balls again
        new Auto3BallShoot(s_subsystem)
        );
  }

}

