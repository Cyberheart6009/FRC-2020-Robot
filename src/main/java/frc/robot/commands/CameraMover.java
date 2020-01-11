/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


package frc.robot.commands;

import java.util.function.DoubleSupplier;

import frc.robot.subsystems.CameraSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class CameraMover extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final CameraSubsystem m_CameraSubsystem;
  private final DoubleSupplier m_xAxis;
  private final DoubleSupplier m_yAxis;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public CameraMover(DoubleSupplier xAxis, DoubleSupplier yAxis, CameraSubsystem camera) {
    m_CameraSubsystem = camera;
    m_xAxis = xAxis;
    m_yAxis = yAxis;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(camera);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_CameraSubsystem.SetServos(m_xAxis.getAsDouble(), m_yAxis.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
