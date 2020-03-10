package frc.robot.commands.AutoModes;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.*;
import frc.robot.subsystems.*;


/**
 * A complex auto command that drives forward, releases a hatch, and then drives
 * backward.
 */
public class ShootStraightMove extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   *
   * @param drive The drive subsystem this command will run on
   * @param hatch The hatch subsystem this command will run on
   */
  public ChassisSubsystem c_subsystem;
  public ShooterSubsystem s_subsystem;
  public ShooterFeederSubsystem sf_subsystem;

  public ShootStraightMove(ChassisSubsystem chassis_subsystem, ShooterSubsystem shooter_subsystem, ShooterFeederSubsystem shooterFeederSubsystem, Double distance) {
      c_subsystem = chassis_subsystem;
      s_subsystem = shooter_subsystem;
      sf_subsystem = shooterFeederSubsystem;
      //double currentAngle = SmartDashboard.getNumber("BallAngle", 0.0);
      //double distance = SmartDashboard.getNumber("BallDistance", 0.0);
    //placeholder because we do not have the value.    
	addCommands(
        //Commands are in order based on the diagram 
        //Starts at top of arena, shoots balls, then drives directly backwards to pick up balls
        new AlignAndShoot(c_subsystem, s_subsystem, sf_subsystem).withTimeout(5),
        new DriveDistanceCommand(distance, -0.8, c_subsystem)

    );
  }

}

