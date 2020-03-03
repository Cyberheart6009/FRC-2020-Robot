/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.hal.sim.DriverStationSim;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveDistanceCommand;
import frc.robot.commands.FollowTarget;
import frc.robot.commands.PIDTurn;
import frc.robot.subsystems.CameraMount;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterFeederSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SingleMotorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.AutoModes.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainerFinal {
  // The robot's subsystems
  private final ChassisSubsystem m_ChassisSubsystem = new ChassisSubsystem();
  private final IntakeSubsystem m_IntakeSubsystem = new IntakeSubsystem();
  private final ShooterSubsystem m_ShooterSubsystem = new ShooterSubsystem();
  private final ShooterFeederSubsystem m_ShooterFeederSubsystem = new ShooterFeederSubsystem();

  // Controller Definitions
  private final XboxController driver = new XboxController(0);
  private final XboxController operator = new XboxController(1);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainerFinal() {
    // The Drive Command
    m_ChassisSubsystem.setDefaultCommand(new RunCommand(()-> 
      m_ChassisSubsystem.drive(driver.getY(Hand.kLeft), -driver.getX(Hand.kLeft)), 
      m_ChassisSubsystem));

    // Intake Motor Command
    m_IntakeSubsystem.setDefaultCommand(new RunCommand(() -> {
      m_IntakeSubsystem.stopIntake();
    }, m_IntakeSubsystem));

    // ShooterSubsystemDefault
    m_ShooterSubsystem.setDefaultCommand(new RunCommand(() -> {
      m_ShooterSubsystem.setFlywheel(operator.getY(Hand.kRight));
    }, m_ShooterSubsystem));

    m_ShooterFeederSubsystem.setDefaultCommand(new RunCommand(() -> {
      m_ShooterFeederSubsystem.setFeed(operator.getY(Hand.kLeft));
      m_ShooterFeederSubsystem.setAntiJam(operator.getY(Hand.kLeft));
    }, m_ShooterFeederSubsystem));

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Controls for the chassis
    new JoystickButton(driver, Constants.Control.kRBumper)
      .whenPressed(new InstantCommand(() -> m_ChassisSubsystem.changeGear(), m_ChassisSubsystem));

    // Controls for the intake
    new JoystickButton(driver, Constants.Control.kYButton)
      .whenPressed(new InstantCommand(() -> m_IntakeSubsystem.changeLock(), m_IntakeSubsystem));

    new JoystickButton(driver, Constants.Control.kAButton)
      .whileHeld(new InstantCommand(() -> m_IntakeSubsystem.setIntake(1), m_IntakeSubsystem));
    new JoystickButton(driver, Constants.Control.kBButton)
      .whileHeld(new InstantCommand(() -> m_IntakeSubsystem.setIntake(-1), m_IntakeSubsystem));

    // Controls for the shooter
    new JoystickButton(operator, Constants.Control.kAButton)
      .whileHeld(new RunCommand(()-> {
        m_ShooterFeederSubsystem.setFeed(1);
        m_ShooterFeederSubsystem.setAntiJam(1);
      }, m_ShooterFeederSubsystem));

    new JoystickButton(operator, Constants.Control.kXButton)
      .whileHeld(new RunCommand(()-> {
        m_ShooterSubsystem.setFlywheel(1);
      }, m_ShooterSubsystem));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new Auto(m_ChassisSubsystem, m_ShooterSubsystem, m_IntakeSubsystem);
  }
}