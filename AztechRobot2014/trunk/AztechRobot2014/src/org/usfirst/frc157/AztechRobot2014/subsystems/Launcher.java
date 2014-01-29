// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc157.AztechRobot2014.subsystems;

import org.usfirst.frc157.AztechRobot2014.RobotMap;
import org.usfirst.frc157.AztechRobot2014.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class Launcher extends Subsystem {

    public static CANJaguar launchMotor;
    public static DigitalInput releaseSwitch;

    public Launcher() {
        releaseSwitch = new DigitalInput(RobotMap.DIGITAL_PORT_ReleaseSwitch);
        LiveWindow.addSensor("Launcher", "Limit Switch 1", releaseSwitch);

        int tries = 0;
        boolean failed = false;
        do {
            try {
                launchMotor = new CANJaguar(RobotMap.JAGID_Launcher);
                failed = false;
            } catch (CANTimeoutException ex) {
                failed = true;
                System.out.println("FAIL " + tries + " - Instantiating Launcher JAG " + RobotMap.JAGID_Launcher);
//                ex.printStackTrace();
            }
        } while (failed && (tries++ < RobotMap.m_kMaxCANRetries));

        if (launchMotor != null) {
            failed = false;
            do {
                try {
                    launchMotor.setVoltageRampRate(0.001);
                    launchMotor.configNeutralMode(CANJaguar.NeutralMode.kBrake);
                    launchMotor.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
                    launchMotor.setPID(2, 0, 0);
                    launchMotor.enableControl();
                    failed = false;
                } catch (CANTimeoutException ex) {
                    failed = true;
                    System.out.println("Exception " + tries + " while configuring voltage mode");
                }
            } while (failed && (tries++ < RobotMap.m_kMaxCANRetries));
        }
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void launch()
    {
        
       
    }
    
    public void reset()
    {
        try {
            // drive launch motor back until limit switch hit
            launchMotor.setX(-1);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}