/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveDistanceCommand;
import frc.robot.commands.FollowTarget;
import frc.robot.commands.PIDTurn;
import frc.robot.subsystems.CameraMount;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.SingleMotorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.RunCommand;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  private final ChassisSubsystem m_ChassisSubsystem = new ChassisSubsystem();
  private final CameraMount m_CameraSubsystem = new CameraMount();
  private final SingleMotorSubsystem m_Intake = new SingleMotorSubsystem(0);
  private final SingleMotorSubsystem m_Launcher = new SingleMotorSubsystem(Constants.PWMPorts.kLauncherMotorPort);

  // Controller Definitions
  private final XboxController driver = new XboxController(0);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // This sets the default command for the cammera subsystem
    m_CameraSubsystem.setDefaultCommand(
      new RunCommand(
        () -> {
          m_CameraSubsystem.SetServos(driver.getY(Hand.kRight) * 90 + 90, driver.getX(Hand.kLeft) * 90 + 90);
        },
        m_CameraSubsystem
        )
        );

    // Default Launcher Subsystem
    m_Launcher.setDefaultCommand(
      new RunCommand(() -> {
        //System.out.println(driver.getY(Hand.kLeft));
        m_Launcher.variableOn(driver.getY(Hand.kRight));
      },
      m_Launcher)
    );

    // This sets the default command for the chassis subsystem
    m_ChassisSubsystem.setDefaultCommand(
      new RunCommand(() -> m_ChassisSubsystem.drive(driver.getY(Hand.kLeft), (Constants.turnInversion) * driver.getX(Hand.kLeft)), m_ChassisSubsystem)
    );

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
    // Intake Controls
    new JoystickButton(driver, Constants.Control.kRBumper)
      .whenPressed(() -> m_ChassisSubsystem.changeGear());
    new JoystickButton(driver, Constants.Control.kLBumper)
      .whileHeld(() -> m_Intake.variableOn(-0.75))
      .whenReleased(()-> m_Intake.variableOn(0));
    new JoystickButton(driver, Constants.Control.kYButton)
      .whenPressed(() -> new PIDTurn(90.0, m_ChassisSubsystem).withTimeout(15).schedule()); 
    new JoystickButton(driver, Constants.Control.kXButton)
      .whenPressed(() -> m_ChassisSubsystem.GyroReset());

    new JoystickButton(driver, Constants.Control.kAButton)
      .whenPressed(() -> {
        Constants.PIDTurn.kTurnP += 0.005;
        SmartDashboard.putNumber("kP", Constants.PIDTurn.kTurnP);
      });
    new JoystickButton(driver, Constants.Control.kBButton)
      .whenPressed(() -> {
        Constants.PIDTurn.kTurnP -= 0.005;
        SmartDashboard.putNumber("kP", Constants.PIDTurn.kTurnP);
      });
    new JoystickButton(driver, Constants.Control.kStart)
      .whenPressed(new FollowTarget(m_ChassisSubsystem, () -> SmartDashboard.getNumber("CentroidOffset", 0)));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new Auto(m_ChassisSubsystem);
  }
}
