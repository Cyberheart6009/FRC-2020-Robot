/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AlignAndShoot;
import frc.robot.commands.TurnInPlaceCommand;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.ColourWheel;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterFeederSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.commands.AutoModes.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final PowerDistributionPanel pdp = new PowerDistributionPanel();

  // The robot's subsystems
  private final ChassisSubsystem m_ChassisSubsystem = new ChassisSubsystem();
  private final IntakeSubsystem m_IntakeSubsystem = new IntakeSubsystem();
  private final ShooterSubsystem m_ShooterSubsystem = new ShooterSubsystem();
  private final ShooterFeederSubsystem m_ShooterFeederSubsystem = new ShooterFeederSubsystem();
  private final ClimberSubsystem m_ClimberSubsystem = new ClimberSubsystem();

  // Controller Definitions
  private final XboxController driver = new XboxController(0);
  private final XboxController operator = new XboxController(1);

  // Auto Chooser
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private static final String autoOne = "autoOne";
  private static final String autoTwo = "autoTwo";
  private static final String autoThree = "autoThree";
  private static final String shootStraight = "shootStraight";

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_chooser.addOption("One", autoOne);
    m_chooser.addOption("Two", autoTwo);
    m_chooser.addOption("Three", autoThree);
    m_chooser.addObject("Shoot Straight", shootStraight);

    SmartDashboard.putData("Auto choices", m_chooser);

    // The Drive Command
    m_ChassisSubsystem.setDefaultCommand(new RunCommand(()-> 
      m_ChassisSubsystem.drive(driver.getY(Hand.kLeft), driver.getX(Hand.kLeft)), 
      m_ChassisSubsystem));

    // Intake Motor Command
    m_IntakeSubsystem.setDefaultCommand(new RunCommand(() -> {
      m_IntakeSubsystem.stopIntake();
    }, m_IntakeSubsystem));

    // Shooter Subsystem Default
    m_ShooterSubsystem.setDefaultCommand(new RunCommand(() -> {
      m_ShooterSubsystem.set(operator.getY(Hand.kRight));
    }, m_ShooterSubsystem));

    m_ShooterFeederSubsystem.setDefaultCommand(new RunCommand(() -> {
      if (operator.getTriggerAxis(Hand.kRight) > 0.05) {
        m_ShooterFeederSubsystem.setFeed(-operator.getTriggerAxis(Hand.kRight));
        m_ShooterFeederSubsystem.setAntiJam(-operator.getTriggerAxis(Hand.kRight));
      } else {
        m_ShooterFeederSubsystem.setFeed(operator.getTriggerAxis(Hand.kLeft));
        m_ShooterFeederSubsystem.setAntiJam(operator.getTriggerAxis(Hand.kLeft));
      }
    }, m_ShooterFeederSubsystem));

    // Climber Subsystem Default
    m_ClimberSubsystem.setDefaultCommand(new RunCommand(() -> {
      m_ClimberSubsystem.climb(operator.getY(Hand.kLeft));
    }, m_ClimberSubsystem));

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
    // Driver Manual Override
    new JoystickButton(driver, Constants.Control.kXButton)
      .whenPressed(new InstantCommand(
        ()-> Constants.Control.kManualOverride = !Constants.Control.kManualOverride)
        );

    // Controls for the chassis
    new JoystickButton(driver, Constants.Control.kRBumper)
      .whenPressed(new InstantCommand(() -> m_ChassisSubsystem.changeGear(), m_ChassisSubsystem));
    new JoystickButton(driver, Constants.Control.kLBumper)
      .whenPressed(() -> {
        new TurnInPlaceCommand(m_ChassisSubsystem, 0.75, SmartDashboard.getNumber(Constants.SmartDashboardKeys.kShooterTargetAngle, 0.0)).withTimeout(15).schedule();
      });


    // Controls for the intake
    new JoystickButton(driver, Constants.Control.kYButton)
      .whenPressed(new InstantCommand(() -> m_IntakeSubsystem.changeLock(), m_IntakeSubsystem));

    new JoystickButton(driver, Constants.Control.kAButton)
      .whileHeld(new InstantCommand(() -> m_IntakeSubsystem.setIntake(1), m_IntakeSubsystem));
    new JoystickButton(driver, Constants.Control.kBButton)
      .whileHeld(new InstantCommand(() -> m_IntakeSubsystem.setIntake(-1), m_IntakeSubsystem));

    // Controls for the shooter
    new JoystickButton(operator, Constants.Control.kXButton)
      .whileHeld(new RunCommand(()-> {
        m_ShooterSubsystem.set(1);
      }, m_ShooterSubsystem));

    new JoystickButton(operator, Constants.Control.kYButton)
      .whenPressed(() -> {
        m_ChassisSubsystem.gyroReset();
        m_ChassisSubsystem.encoderReset();
      });

    // Controls for the climber
    new JoystickButton(operator, Constants.Control.kRBumper)
      .whenPressed(new InstantCommand(() -> m_ClimberSubsystem.lock(), m_ClimberSubsystem));
    new JoystickButton(operator, Constants.Control.kLBumper)
      .whenPressed(new InstantCommand(() -> m_ClimberSubsystem.unlock(), m_ClimberSubsystem));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    String m_autoSelected = m_chooser.getSelected();
    Command selected;
    // An ExampleCommand will run in autonomous
    switch (m_autoSelected) {
      case autoOne:
        selected = (new AutoRouteOne(m_ChassisSubsystem, m_ShooterSubsystem, m_ShooterFeederSubsystem, m_IntakeSubsystem));
        break;
      case autoTwo:
        selected = (new AutoRouteTwo(m_ChassisSubsystem, m_ShooterSubsystem, m_ShooterFeederSubsystem, m_IntakeSubsystem));
        break;
      case autoThree:
        selected = (new AutoRouteThree(m_ChassisSubsystem, m_ShooterSubsystem, m_ShooterFeederSubsystem, m_IntakeSubsystem));
        break;
      case shootStraight:
        selected = (new ShootStraightMove(m_ChassisSubsystem, m_ShooterSubsystem, m_ShooterFeederSubsystem, (double)48));
      default:
        selected = (new AlignAndShoot(m_ChassisSubsystem, m_ShooterSubsystem, m_ShooterFeederSubsystem));
        break;
    }

    return selected;
    
  }

  public void teleopInit() {
    m_ChassisSubsystem.gearUp();
  }
}