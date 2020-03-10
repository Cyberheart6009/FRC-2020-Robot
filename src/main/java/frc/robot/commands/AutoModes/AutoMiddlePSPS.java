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
public class AutoMiddlePSPS extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   *
   * @param drive The drive subsystem this command will run on
   * @param hatch The hatch subsystem this command will run on
   */
  public ChassisSubsystem c_subsystem;
  public ShooterSubsystem s_subsystem;
  public IntakeSubsystem i_subsystem;

  public AutoMiddlePSPS(ChassisSubsystem chassis_subsystem, ShooterSubsystem shooter_subsystem, IntakeSubsystem intake_subsystem) {
      c_subsystem = chassis_subsystem;
      s_subsystem = shooter_subsystem;
      i_subsystem = intake_subsystem;
    //placeholder because we do not have the value.
    DoubleSupplier offset;
  addCommands(
        /**this auto mode:
         * turns towards AutoTopPSPS starting position
         * follows the AutoTopPSPS route (gets 2 balls from rendezvous, shoots 5 balls, gets 3 balls from color wheel, shoots 3)
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
        new DriveDistanceCommand(71, 1, c_subsystem),
        //temp wait command
        new WaitCommand(0.5),

        //turns towards the rendezvous point by turning 90 degrees CW
        new TurnInPlaceCommand(c_subsystem, 1, 90),

        //this is where the ball tracking would go

        //driving the distance in order to safely rotate without running into a pole
        new DriveDistanceCommand(Math.sqrt(481), 1, c_subsystem),
        //temporary wait command
        new WaitCommand(0.5),
        //rotating towards the ship
        new TurnInPlaceCommand(c_subsystem, 1, 336),
        //temp wait command
        new WaitCommand(0.5),
        //driving towards where we need to shoot
        new DriveDistanceCommand(94, 1, c_subsystem),
        //aligning with the hole
        new AutoAlignCommand(c_subsystem),
        //shooting all five balls in capacity, and waiting 0.25 seconds in between each shot
        new Auto5BallShoot(s_subsystem),
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

        //offset target to go towards where to shoot the ball
        new AutoAlignCommand(c_subsystem),

        //shooting all 3 balls again
        new Auto3BallShoot(s_subsystem)
        );
  }

}

