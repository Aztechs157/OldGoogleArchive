package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Jaguar;

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
    
    public static Jaguar tiltMotor_;
    public static MotorControlAssembly tiltAssembly_;
    
    public static void init() {
        tiltMotor_ = new Jaguar(1);  
        
        tiltAssembly_ = new MotorControlAssembly(tiltMotor_, 2);
        tiltAssembly_.setRange(300, 900,0,45);
        tiltAssembly_.setInvertedMotor(false);

    }

    
}
