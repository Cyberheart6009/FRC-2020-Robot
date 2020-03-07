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
    public static final double turnInversion = -1;

    public static final class PWMPorts{
        
        //all 0 because i do not know the correct motor ports.
        public static final int[] kLeftMotors = {1};
        public static final int kColourWheelMotor = 0;
        public static final int[] kRightMotors = {2, 3};

        public static final int kShootMotor1 = 6;
        public static final int kShootMotor2 = 7;
        public static final int kFeedMotor = 4;
        public static final int kAntiJamMotor = 8;
        public static final int kIntakeMotor = 5;
        public static final int kClimberMotor = 9;
    }

    public static final class EncoderPorts {
        public static final int kRightEncoderA = 0;
        public static final int kRightEncoderB = 1;
        public static final int kLeftEncoderA = 2;
        public static final int kLeftEncoderB = 3;
        public static final int kShooterEncoderA = 4;
        public static final int kShooterEncoderB = 5;

        public static final double ENCODER_COUNTS_PER_INCH = 3737/267.5;
        public static final double kRotationConverter = 1;
    }

    public static class PIDTurn {
        public static final double kTurnToleranceDeg = 2;
        public static final double kTurnRateToleranceDegPerS = 10; // degrees per second

        public static final double kTurnP = 0.1;
        public static final double kTurnI = 0; 
        public static final double kTurnD = 0.005; 
    }

    public static class PIDShoot {
        public static final double kTurnToleranceDeg = 1;
        public static final double kTurnRateToleranceDegPerS = 10; // degrees per second

        public static double kTurnP = 0.1;
        public static final double kTurnI = 0;
        public static final double kTurnD = 0.007;
    }

    public static class PIDStraight {
        public static final double kTurnToleranceDeg = 3;
        public static final double kTurnRateToleranceDegPerS = 10; // degrees per second

        public static final double kTurnP = 0.1;
        public static final double kTurnI = 7;
        public static final double kTurnD = 0.1;
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

        public static boolean kManualOverride = false;
    }

    public static final class SmartDashboardKeys {
        public static final String kShooterTargetAngle = "ShooterTargetAngle";
        public static final String kShooterDistance = "ShooterDistance";    
    }
}