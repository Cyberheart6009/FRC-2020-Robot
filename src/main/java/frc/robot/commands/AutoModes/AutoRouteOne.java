package frc.robot.commands.AutoModes;

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
public class AutoRouteOne extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   *
   * @param drive The drive subsystem this command will run on
   * @param hatch The hatch subsystem this command will run on
   */
  public ChassisSubsystem c_subsystem;
  public ShooterSubsystem s_subsystem;
  public ShooterFeederSubsystem sf_subsystem;
  public IntakeSubsystem i_subsystem;

  public AutoRouteOne(ChassisSubsystem chassis_subsystem, ShooterSubsystem shooter_subsystem, ShooterFeederSubsystem shooterFeederSubsystem,IntakeSubsystem intake_subsystem) {
      c_subsystem = chassis_subsystem;
      s_subsystem = shooter_subsystem;
      i_subsystem = intake_subsystem;
      sf_subsystem = shooterFeederSubsystem;
      //double currentAngle = SmartDashboard.getNumber("BallAngle", 0.0);
      //double distance = SmartDashboard.getNumber("BallDistance", 0.0);
    //placeholder because we do not have the value.    
	addCommands(
        //Commands are in order based on the diagram 
        //Starts at top of arena, shoots balls, then drives directly backwards to pick up balls
        new AlignAndShoot(c_subsystem, s_subsystem, sf_subsystem).withTimeout(5),
        new TurnInPlaceCommand(c_subsystem, 0.85, 50),   
        new DriveDistanceCommand(100, 0.8, c_subsystem),        
     
        new SequentialCommandGroup(           
          new TurnInPlaceCommand(c_subsystem, 0.7, -SmartDashboard.getNumber("BallAngle", 0.0)),
          new WaitCommand(1),
          new ParallelCommandGroup(new DriveDistanceCommand(100, 0.8, c_subsystem), 
          new SuccCommand(i_subsystem))
        ),
        new SequentialCommandGroup(           
          new TurnInPlaceCommand(c_subsystem, 0.7, -SmartDashboard.getNumber("BallAngle", 0.0)),
          new WaitCommand(1),
          new ParallelCommandGroup(new DriveDistanceCommand(40, 0.8, c_subsystem), 
          new SuccCommand(i_subsystem))
        ),
        new SequentialCommandGroup(           
          new TurnInPlaceCommand(c_subsystem, 0.7, -SmartDashboard.getNumber("BallAngle", 0.0)),
          new WaitCommand(1),
          new ParallelCommandGroup(new DriveDistanceCommand(40, 0.8, c_subsystem), 
          new SuccCommand(i_subsystem))
        )        
    );
  }

}

