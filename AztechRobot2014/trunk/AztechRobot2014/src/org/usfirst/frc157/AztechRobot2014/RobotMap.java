// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc157.AztechRobot2014;

import edu.wpi.first.wpilibj.*;
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

    public static CANJaguar driveRL_Jag;
    public static CANJaguar driveFR_Jag;
    public static CANJaguar driveFL_Jag;
    public static CANJaguar driveRR_Jag;
    public static DigitalInput launcherLimitSwitch1;

    //=====================================================================
    // CAN System constants
    //=====================================================================
    public static int m_kMaxCANRetries = 3;

    //=====================================================================
    // Drive System Constants
    //=====================================================================
    public static final double SpeedScale = 1;

    // Jaguar IDs
    public static int JAGID_FrontLeft = 7;
    public static int JAGID_FrontRight = 6;
    public static int JAGID_RearLeft = 4;
    public static int JAGID_RearRight = 2;

    //=====================================================================
    // Launcher Constants
    //=====================================================================
    // Jaguar IDs
    public static int JAGID_Launcher = 5;
    public static int JAGID_Retriever = 8;
    
    // Limit Switches
    public static int DIGITAL_PORT_ReleaseSwitch = 1;
    
    // Compressor Info
    public static final int DIGITAL_PORT_PressureSwitch = 12;
    public static final int RELAY_PORT_Compressor = 4;
    
    // Loader Piston
    public static final int SOLENOID_PORT_ReleaseExtend = 1;
    public static final int SOLENOID_PORT_ReleaseRetract = 2;

    //PWM
    public static final int PWM_RetrieverTalon = 1;
    
    //=====================================================================
    // Sensor System Constants
    //=====================================================================
    // Analog Port IDs
    public static int ANALOG_PORT_ShortRangeIR = 1;
    public static int ANALOG_PORT_LongRangeIR_1 = 2;
    public static int ANALOG_PORT_LongRangeIR_2 = 3;
    public static int ANALOG_PORT_Ultrasonic_1 = 4;
    public static int ANALOG_PORT_BallRetrieverPot = 4;

    //=====================================================================
    // Operator Interface Constants
    //=====================================================================
    public static int CONTROLLERNUM_DriverLeft = 1;
    public static int CONTROLLERNUM_DriverRight = 2;
    public static int CONTROLLERNUM_Driver = 3;
    public static int CONTROLLERNUM_CoDriver = 4;

    
    public static void init() {

    }
}
