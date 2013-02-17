package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.templates.subsystems.Drive;
import edu.wpi.first.wpilibj.templates.subsystems.LiftSubsystem;
import edu.wpi.first.wpilibj.templates.subsystems.Shooter;
import edu.wpi.first.wpilibj.templates.subsystems.VisionSubsystem;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;

    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
    
    //=====================================================================
    // Drive System Constants
    //=====================================================================
    public static final double SpeedScale = 130;
    public static final boolean FrontLeftMotorInverted = true;
    public static final boolean RearLeftMotorInverted = true;
    public static final boolean FrontRightMotorInverted = false;
    public static final boolean RearRightMotorInverted = false;
    
    public static final int FrontLeftMotorID = 7;   // CAN
    public static final int FrontRightMotorID = 6;  // CAN
    public static final int RearLeftMotorID = 4;    // CAN
    public static final int RearRightMotorID = 5;   // CAN

    //=====================================================================
    // Shooter System Constants
    //=====================================================================
    public static int ShooterElevationMotorID = 3;         // CAN
    public static int ShooterFirstStageDrivePWMPort = 3;   // PWM
    public static int ShooterSecondStageDrivePWMPort = 4;  // PWM
    // Compressor Info
    public static final int PressureSwitchGPIOPort = 12;  // GPIO 
    public static final int CompressorRelayChannel = 4;  // Analog 
    
    // Loader Piston
    public static final int LoaderRetractPort = 2;      // Relay
    public static final int LoaderExtendPort = 3;       // Relay

    // Shoot Piston
    public static final int ShooterRetractPort = 7;     // Relay
    public static final int ShooterExtendPort = 6;      // Relay
    
    // Request Light Panel
    public static final int RedLightPort = 6;            // Relay
    public static final int WhiteLightPort = 7;          // Relay
    public static final int BlueLightPort = 8;           // Relay
    
         
    //=====================================================================
    // Lift System Constants
    //=====================================================================
    public static final int LiftLeftPWMPort = 1;
    public static final int LiftRightPWMPort = 2;
    
    public static final int LiftLeftLimitRetractGPIOPort = 1;
    public static final int LiftRightLimitRetractGPIOPort = 2;
    public static final int LiftLeftLimitExtendGPIOPort = 3;
    public static final int LiftRightLimitExtendGPIOPort = 4;
    
    public static final double LiftLeftExtendSpeed = 0.6;
    public static final double LiftLeftRetractSpeed = 0.6;
    public static final boolean LiftLeftMotorInverted = false;
    
    public static final double LiftRightExtendSpeed = 0.6;
    public static final double LiftRightRetractSpeed = 0.6;
    public static final boolean LiftRightMotorInverted = false;

    //=====================================================================
    // Vision System Constants
    //=====================================================================
    public static final double VisionXMinDeg = -24.0;
    public static final double VisionXMaxDeg = +24.0;
    public static final double VisionYMinDeg = -24.0;
    public static final double VisionYMaxDeg = +24.0;
    
    public static final int VisionBorderTolerance = 5;
    
    public static final int VisionQualityRequired = 4;
    public static final int VisionQualityMax = 10;
    
    //=====================================================================
    // Vision Align Command Constants
    //=====================================================================
    public static final double AlignToleranceXDeg = 3.0;
    public static final double AlignToleranceYDeg = 3.0;
    

    //=====================================================================
    //=====================================================================
}
