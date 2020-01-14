/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.commands.DriveDistanceCommand;
import frc.robot.commands.DriveTrain;
import frc.robot.commands.CameraMover;
import frc.robot.subsystems.CameraSubsystem;
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
  // The robot's subsystems and commands are defined here...
  private final ChassisSubsystem m_ChassisSubsystem = new ChassisSubsystem();

  //private final CameraSubsystem m_CameraSubsystem = new CameraSubsystem();

  private final SingleMotorSubsystem m_intake = new SingleMotorSubsystem(4);
  private final SingleMotorSubsystem m_launcher = new SingleMotorSubsystem(5);

  private final XboxController driver = new XboxController(0);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    /*
    // This sets the default command for the cammera subsystem
    m_CameraSubsystem.setDefaultCommand(
      new CameraMover(
        () -> {
          return driver.getY(Hand.kRight) * 90 + 90;
        },
        () -> {
          return driver.getX(Hand.kLeft) * 90 + 90; 
        },
        m_CameraSubsystem
        )
        );
        */

    m_launcher.setDefaultCommand(
      new RunCommand(() -> {
        System.out.println(driver.getY(Hand.kRight));
        m_launcher.variableOn(driver.getY(Hand.kRight));
      },
      m_launcher)
    );

    // This sets the default command for the cammera subsystem
    m_ChassisSubsystem.setDefaultCommand(
      new DriveTrain(
        () -> {
          return driver.getY(Hand.kLeft);
        },
        () -> {
          return driver.getX(Hand.kLeft); 
        },
        m_ChassisSubsystem
        )
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
    new JoystickButton(driver, 1)
      .whenPressed(() -> m_intake.fullBackward())
      .whenReleased(() -> m_intake.fullStop());
    new JoystickButton(driver, 2)
      .whenPressed(() -> m_intake.fullForward())
      .whenReleased(() -> m_intake.fullStop());
  }



  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new DriveDistanceCommand(m_ChassisSubsystem, 10, 1, 69420);
  }
}
