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
public class AutoBottomPSPS extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   *
   * @param drive The drive subsystem this command will run on
   * @param hatch The hatch subsystem this command will run on
   */
  public ChassisSubsystem c_subsystem;
  public ShooterSubsystem s_subsystem;
  public IntakeSubsystem i_subsystem;

  public AutoBottomPSPS(ChassisSubsystem chassis_subsystem, ShooterSubsystem shooter_subsystem, IntakeSubsystem intake_subsystem) {
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
        new DriveDistanceCommand(175, 1, c_subsystem),
        //wait for 0.5 seconds
        new WaitCommand(0.5),

        //rotates towards the blue colour wheel 54.7 degrees CW
        new TurnInPlaceCommand(c_subsystem, 1, 54.7),
        //temp wait command
        new WaitCommand(0.5),
        //drives towards the colour wheel
        new DriveDistanceCommand(Math.sqrt(6341), 1, c_subsystem),
        //temp wait command
        new WaitCommand(0.5),

        //insert ball finding thing here

        //rotates 270 degrees CW (90 degrees CCW) 
        new TurnInPlaceCommand(c_subsystem, 1, 270),
        //temp wait command
        new WaitCommand(0.5),
        //drives x distance
        new DriveDistanceCommand(75, 1, c_subsystem),
        //temp wait command
        new WaitCommand(0.5),
        //rotates to drive the y distance + AutoBottomS distance 90 degrees CW
        new TurnInPlaceCommand(c_subsystem,1,90),
        //temo wait command
        new WaitCommand(0.5),
        //drives the y distance + autoBottomS distance
        new DriveDistanceCommand(217, 1, c_subsystem),
        //temp wait command
        new WaitCommand(0.5),
        //turns towards the hole by turning 270 degrees CW (90 degrees CCW)
        new TurnInPlaceCommand(c_subsystem, 1, 270),
        //temp wait command
        new WaitCommand(0.5),
        //driving forward 25 inches to shoot better
        new DriveDistanceCommand(25, 1, c_subsystem),
        //temp wait command
        new WaitCommand(0.5),
        //aligning with the hole
        new FollowTarget(c_subsystem, offset),
        //shoots all three balls
        new ShooterCommand(s_subsystem),
        new WaitCommand(0.5),
        new ShooterCommand(s_subsystem),
        new WaitCommand(0.5),
        new ShooterCommand(s_subsystem),
        new WaitCommand(0.5),

        //the rest is from AutoTopSPS
        
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
        new DriveDistanceCommand(Math.sqrt(19252), 1,c_subsystem),
        //offset target to go towards where to shoot the ball
        new FollowTarget(c_subsystem,offset),
        //shooting all 3 balls again
        new ShooterCommand(s_subsystem),
        new WaitCommand(0.25),
        new ShooterCommand(s_subsystem),
        new WaitCommand(0.25),
        new ShooterCommand(s_subsystem)
        );
  }

}

