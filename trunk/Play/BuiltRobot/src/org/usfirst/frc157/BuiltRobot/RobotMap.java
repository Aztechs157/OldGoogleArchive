// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.usfirst.frc157.BuiltRobot;
    
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.can.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import java.util.Vector;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    
    private static int FrontLeftMotorID = 7;
    private static int FrontRightMotorID = 6;
    private static int RearLeftMotorID = 4;
    private static int RearRightMotorID = 5;
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static CANJaguar simpleMotorCimJag;
    public static CANJaguar driveDriveFL;
    public static CANJaguar driveDriveFR;
    public static CANJaguar driveDriveRL;
    public static CANJaguar driveDriveRR;
    public static RobotDrive driveMechanumDrive;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static CANJaguar simpleMotorWinJag;

    public static void init() {
       try { 
            simpleMotorWinJag = new CANJaguar(2);
        } catch (CANTimeoutException ex) {
            System.out.println("FAIL - Instantiating JAG 6");
            ex.printStackTrace();
        }
	
        
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        try { 
            simpleMotorCimJag = new CANJaguar(3);
        } catch (CANTimeoutException ex) {
            System.out.println("FAIL - Instantiating JAG 5");
            ex.printStackTrace();
        }
	
        
        try { 
            driveDriveFL = new CANJaguar(FrontLeftMotorID);
        } catch (CANTimeoutException ex) {
            System.out.println("FAIL - Instantiating JAG 3");
            ex.printStackTrace();
        }
	
        
        try { 
            driveDriveFR = new CANJaguar(FrontRightMotorID);
        } catch (CANTimeoutException ex) {
            System.out.println("FAIL - Instantiating JAG 2");
            ex.printStackTrace();
        }
	
        
        try { 
            driveDriveRL = new CANJaguar(RearLeftMotorID);
        } catch (CANTimeoutException ex) {
            System.out.println("FAIL - Instantiating JAG 7");
            ex.printStackTrace();
        }
	
        
        try { 
            driveDriveRR = new CANJaguar(RearRightMotorID);
        } catch (CANTimeoutException ex) {
            System.out.println("FAIL - Instantiating JAG 8");
            ex.printStackTrace();
        }
	
        
        try { 
//public RobotDrive(SpeedController frontLeftMotor, SpeedController rearLeftMotor, SpeedController frontRightMotor, SpeedController rearRightMotor) {
//    FL, RL, FR, RR
    driveMechanumDrive = new RobotDrive(driveDriveFL, driveDriveRL, driveDriveFR, driveDriveRR);
//            driveMechanumDrive = new RobotDrive(driveDriveFL, driveDriveFL, driveDriveFL, driveDriveFL);
//            driveMechanumDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
//            driveMechanumDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        }
        catch (Exception e)
        {
            System.out.println("Can't get mech drive going...  FAIL");
        }

        if(driveMechanumDrive == null) System.out.println("MD = NULL");
        
        try {
        driveMechanumDrive.setSafetyEnabled(false);
        driveMechanumDrive.setExpiration(4.0);
        driveMechanumDrive.setSensitivity(0.5);
        driveMechanumDrive.setMaxOutput(1.0);
        }
        catch (Exception e)
        {
            System.out.println("Can't configure mech drive ...  FAIL");
        }
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
