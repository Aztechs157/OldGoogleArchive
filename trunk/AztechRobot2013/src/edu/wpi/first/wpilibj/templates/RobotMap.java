package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

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
    private static double SpeedScale = 130;
       
    public static int FrontLeftMotorID = 7;   // CAN
    public static int FrontRightMotorID = 6;  // CAN
    public static int RearLeftMotorID = 4;    // CAN
    public static int RearRightMotorID = 5;   // CAN

    public static ScaledCANJaguar driveDriveFL;
    public static ScaledCANJaguar driveDriveFR;
    public static ScaledCANJaguar driveDriveRL;
    public static ScaledCANJaguar driveDriveRR;
    
    public static RobotDrive driveMechanumDrive;


    //=====================================================================
    // Shooter System Constants
    //=====================================================================
    public static int ShooterElevationMotorID = 3;         // CAN
    public static int ShooterFirstStageDrivePWMPort = 3;   // PWM
    public static int ShooterSecondStageDrivePWMPort = 4;  // PWM
    // Compressor Info
    public static final int PressureSwitchGPIOPort = 9;  // GPIO 
    public static final int CompressorRelayChannel = 4;  // Analog 
    
    // Loader Piston
    public static final int LoaderRetractPort = 2;      // Relay
    public static final int LoaderExtendPort = 3;       // Relay

    // Shoot Piston
    public static final int ShooterRetractPort = 4;     // Relay
    public static final int ShooterExtendPort = 5;      // Relay
    
         
    //=====================================================================
    // Lift System Constants
    //=====================================================================
    public static int LiftLPwmID = 1;
    public static int LiftRPwmID = 2;
    
    public static int LiftLimitHighLGPIOPort = 1;
    public static int LiftLimitHighRGPIOPort = 2;
    
    
    //=====================================================================
    //=====================================================================
        
    public static void init() {
        
        try {
            driveDriveFL = new ScaledCANJaguar(FrontLeftMotorID);
            driveDriveFL.setScalingFactor(SpeedScale);
        } catch (CANTimeoutException ex) {
            System.out.println("FAIL - Instantiating FL JAG " + FrontLeftMotorID);
            ex.printStackTrace();
        }

        try {
            driveDriveFR = new ScaledCANJaguar(FrontRightMotorID);
            driveDriveFR.setScalingFactor(SpeedScale);
        } catch (CANTimeoutException ex) {
            System.out.println("FAIL - Instantiating FR JAG " + FrontRightMotorID);
            ex.printStackTrace();
        }

        try {
            driveDriveRL = new ScaledCANJaguar(RearLeftMotorID);
            driveDriveRL.setScalingFactor(SpeedScale);
        } catch (CANTimeoutException ex) {
            System.out.println("FAIL - Instantiating RL JAG " + RearLeftMotorID);
            ex.printStackTrace();
        }

        try {
            driveDriveRR = new ScaledCANJaguar(RearRightMotorID);
            driveDriveRR.setScalingFactor(SpeedScale);
        } catch (CANTimeoutException ex) {
            System.out.println("FAIL - Instantiating RR JAG " + RearRightMotorID);
            ex.printStackTrace();
        }
        
        setupJagForSpeedControl(driveDriveFL);
        setupJagForSpeedControl(driveDriveRL);
        setupJagForSpeedControl(driveDriveFR);
        setupJagForSpeedControl(driveDriveRR);
        
        try {
            driveMechanumDrive = new RobotDrive(driveDriveFL, driveDriveRL, driveDriveFR, driveDriveRR);
        } catch (Exception e) {
            System.out.println("Can't get mech drive going...  FAIL");
        }

        if (driveMechanumDrive == null) {
            System.out.println("MD = NULL");
        }

        try {
            driveMechanumDrive.setSafetyEnabled(false);
            driveMechanumDrive.setExpiration(4.0);
            driveMechanumDrive.setSensitivity(0.5);
            driveMechanumDrive.setMaxOutput(1.0);
        } catch (Exception e) {
            System.out.println("Can't configure mech drive ...  FAIL");
        }
        
    }
    
        private static void setupJagForSpeedControl(CANJaguar jag) {
        try {
            jag.changeControlMode(CANJaguar.ControlMode.kSpeed);
            jag.setSpeedReference(CANJaguar.SpeedReference.kQuadEncoder);
            jag.configEncoderCodesPerRev(360 * 3);
            jag.setPID(.75, 0.05, 0);
            jag.enableControl();
        } catch (CANTimeoutException ex) {
            System.out.println("Exception while configuring speed");
        }

    }

}
