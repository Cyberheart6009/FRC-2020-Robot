/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class CameraMount extends SubsystemBase {
  private final Servo cameraX;
  private final Servo cameraY;

  public CameraMount() {
      cameraX = new Servo(Constants.PWMConstants.kCameraXServoRange);
      cameraY = new Servo(Constants.PWMConstants.kCameraYServoRange);
  }

  public void SetServos(double x, double y) {
    cameraX.setAngle(x);
    cameraY.setAngle(y);
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
