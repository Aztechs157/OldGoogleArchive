package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;

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
    
    public static Jaguar tiltMotor;
    public static MotorControlAssembly tiltAssembly;
   
    public static Jaguar driveFLMotor, driveFRMotor, driveRLMotor, driveRRMotor;    
//    public static RobotDrive driveMechanumDrive;
    
    public static void init() {
        tiltMotor = new Jaguar(1);  
        
        tiltAssembly = new MotorControlAssembly(tiltMotor, 2);
        tiltAssembly.setRange(300, 900,0,45);
        tiltAssembly.setInvertedMotor(false);
        
        driveFLMotor = new Jaguar(3);
        driveFRMotor = new Jaguar(4);
        driveRLMotor = new Jaguar(5);
        driveRRMotor = new Jaguar(6);

//        driveMechanumDrive = new RobotDrive(driveFLMotor, driveRLMotor, driveFRMotor, driveRRMotor);        
//        driveMechanumDrive.setSafetyEnabled(false);
//        driveMechanumDrive.setExpiration(4.0);
//        driveMechanumDrive.setSensitivity(0.5);
//        driveMechanumDrive.setMaxOutput(1.0);
    }

    
}
