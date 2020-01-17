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
public final class Constants {
    public static final class PWMConstants{
        
        //all 0 because i do not know the correct motor ports.
        public static final int[] kLeftMotors = {0}; //,1};
        public static final int[] kRightMotors = {2}; //,3};

        public static final int kCameraXServoRange = 8;
        public static final int kCameraYServoRange = 9;
        public static final int kclimberXMotorPort = 5;
        public static final int kclimberYMotorPort = 6;
        public static final int kShooterMotorPort = 7;
    }

    public static final class EncoderConstants{
        public static final int kRightEncoderA = 0;
        public static final int kRightEncoderB = 6;
        public static final int kLeftEncoderA = 2;
        public static final int kElevatorEncoderA = 4;
        public static final int kElevatorEncoderB = 5;
        
        public static final int kLeftEncoderB = 7;
        public static final int kclimberEncoderA = 4;
        public static final int kclimberEncoderB = 5;
        //placeholder value
        public static final double ENCODER_COUNTS_PER_INCH = 13.49;
        
        //placeholder values for PIDTurn
        public static final boolean kGyroReversed = false;
        public static final double kTurnToleranceDeg = 5;
        public static final double kTurnRateToleranceDegPerS = 10; // degrees per second
    }

    public static final class TurnConstants{
        //placeholder values
        public static final double kTurnP = 0;
        public static final double kTurnI = 0;
        public static final double kTurnD = 0;
    }

    public static final class XboxConstants{
        public static final int kAButton = 1;
        public static final int kBButton = 2;
        public static final int kXButton = 3;
        public static final int kYButton = 4;
        public static final int kLBumper = 5;
        public static final int kYBumper = 6;
        public static final int kSelect = 7;
        public static final int kStart = 8;
        public static final int kLeftThumbPush = 9;
        public static final int kRightThumbPush = 10;
    }
}
