// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc157.ControlBoardTest2014;

import edu.wpi.first.wpilibj.CANJaguar;
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

    //Motor Subsystem
    public static boolean useCAN;
    public static int motorSubsystemCANJaguar1ID;
    public static int motorSubsystemPWMJaguar1ID;
    public static int DIGITAL_PORT_LimSwitch;
    public static int ANALOG_PORT_Pot;
    
    //Pnematic Subsystem
    public static int DIGITAL_PORT_PressureSwitch;
    public static int RELAY_PORT_Compressor;
    public static int SOLENOID_PORT_In;
    public static int SOLENOID_PORT_Out;
    
    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        useCAN = true;
        motorSubsystemCANJaguar1ID = 5;
        motorSubsystemPWMJaguar1ID = 5;
        DIGITAL_PORT_LimSwitch = 14;
        ANALOG_PORT_Pot = 1;
        
        DIGITAL_PORT_PressureSwitch = 1;
        RELAY_PORT_Compressor = 1;
        SOLENOID_PORT_In = 2;
        SOLENOID_PORT_Out = 1;
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
