package frc.robot.commands.AutoModes;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * A complex auto command that drives forward, releases a hatch, and then drives
 * backward.
 */
public class AutoRouteTwo extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   *
   * @param drive The drive subsystem this command will run on
   * @param hatch The hatch subsystem this command will run on
   */
  public ChassisSubsystem c_subsystem;
  public ShooterSubsystem s_subsystem;
  public IntakeSubsystem i_subsystem;

  public AutoRouteTwo(ChassisSubsystem chassis_subsystem, ShooterSubsystem shooter_subsystem, IntakeSubsystem intake_subsystem) {
      c_subsystem = chassis_subsystem;
      s_subsystem = shooter_subsystem;
      i_subsystem = intake_subsystem;
      //double currentAngle = SmartDashboard.getNumber("BallAngle", 0.0);
      //double distance = SmartDashboard.getNumber("BallDistance", 0.0);
    //placeholder because we do not have the value.    
	addCommands(
        //Commands are in order based on the diagram 
        //Starts at top of arena, shoots balls, then drives directly backwards to pick up balls
        new ShooterCommand(s_subsystem),
        new TurnInPlaceCommand(c_subsystem, 1, -40),
        new BallTrackerCommand(c_subsystem, i_subsystem, 3),        
        new SequentialCommandGroup(           
          new TurnInPlaceCommand(c_subsystem, 0.7, -SmartDashboard.getNumber("BallAngle", 0.0)),
          new WaitCommand(1),
          new ParallelCommandGroup(new DriveDistanceCommand(SmartDashboard.getNumber("BallDistance", 0.0), 1, c_subsystem), 
          new SuccCommand(i_subsystem))
        ),
        new SequentialCommandGroup(           
          new TurnInPlaceCommand(c_subsystem, 0.7, -SmartDashboard.getNumber("BallAngle", 0.0)),
          new WaitCommand(1),
          new ParallelCommandGroup(new DriveDistanceCommand(SmartDashboard.getNumber("BallDistance", 0.0), 1, c_subsystem), 
          new SuccCommand(i_subsystem))
        ),
        new SequentialCommandGroup(           
          new TurnInPlaceCommand(c_subsystem, 0.7, -SmartDashboard.getNumber("BallAngle", 0.0)),
          new WaitCommand(1),
          new ParallelCommandGroup(new DriveDistanceCommand(SmartDashboard.getNumber("BallDistance", 0.0), 1, c_subsystem), 
          new SuccCommand(i_subsystem))
        )

    );
  }

}

