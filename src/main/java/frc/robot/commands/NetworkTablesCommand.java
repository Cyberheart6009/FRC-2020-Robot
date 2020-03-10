package frc.robot.commands;

import frc.robot.subsystems.ChassisSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class NetworkTablesCommand extends CommandBase {
   NetworkTableEntry x1Entry;
   NetworkTableEntry x2Entry;
   private ChassisSubsystem m_chassisSubsystem;
   private NetworkTableInstance inst = NetworkTableInstance.getDefault();

   public NetworkTablesCommand(ChassisSubsystem subsystem) {
    m_chassisSubsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
   }

    @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    NetworkTable table = inst.getTable("SmartDashboard");
    x1Entry = table.getEntry("x1");
    x2Entry = table.getEntry("x2");
        
    System.out.println("X1 = " + x1Entry.toString());
    System.out.println("X2 = " + x2Entry.toString());

  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("ended");
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}