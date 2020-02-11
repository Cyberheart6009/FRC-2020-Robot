/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public class Constants {
    public static final double turnInversion = 1;

    public static final class PWMPorts{
        public static final int[] kLeftMotors = {0, 1};
        public static final int[] kRightMotors = {2, 3};

        public static final int kCameraXServoRange = 4;
        public static final int kCameraYServoRange = 5;
        public static final int kclimberXMotorPort = 6;
        public static final int kclimberYMotorPort = 7;
        public static final int kLauncherMotorPort = 8;
    }

    public static final class EncoderPorts {
        public static final int kRightEncoderA = 0;
        public static final int kRightEncoderB = 1;
        public static final int kLeftEncoderA = 2;
        public static final int kLeftEncoderB = 3;
        public static final int kElevatorEncoderA = 4;
        public static final int kElevatorEncoderB = 5;
        public static final int kclimberEncoderA = 4;
        public static final int kclimberEncoderB = 5;
        //placeholder value
        public static final double ENCODER_COUNTS_PER_INCH = 13.49;
        
        //placeholder values for PIDTurn
        public static final boolean kGyroReversed = false;
    }

    public static class PIDTurn {
        //placeholder values
        public static final double kTurnToleranceDeg = 2;
        public static final double kTurnRateToleranceDegPerS = 10; // degrees per second

        private static final double kU = 0.5;
        private static final double tU = 7/6.5;
        public static double kTurnP = 0.01; //0.45*kU; //0.6*kU;
        public static final double kTurnI = 0; //0.54*kU/tU; //1.2*kU/tU;
        public static final double kTurnD = 0; //3*kU*tU/40;
    }

    public static final class Control {
        public static final int kAButton = 1;
        public static final int kBButton = 2;
        public static final int kXButton = 3;
        public static final int kYButton = 4;
        public static final int kLBumper = 5;
        public static final int kRBumper = 6;
        public static final int kSelect = 7;
        public static final int kStart = 8;
        public static final int kLeftThumbPush = 9;
        public static final int kRightThumbPush = 10;
    }
}
