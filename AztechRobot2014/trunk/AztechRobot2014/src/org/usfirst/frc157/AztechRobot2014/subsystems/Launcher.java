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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc157.AztechRobot2014.Robot;

/**
 *
 */
public class Launcher extends Subsystem {

    public static Talon launchMotor;
    public static DigitalInput releaseSwitch;

    // Pneumatics
    public static Compressor compressor;
    Solenoid clutchRelease;
    Solenoid clutchEngage;

    public Talon getCockingMotor()
    {
        return launchMotor;
    }
    
    public Launcher() {

        System.out.println("== Initializing Shooter ==");
        if (compressor == null) {
            compressor = new Compressor(RobotMap.DIGITAL_PORT_PressureSwitch, RobotMap.RELAY_PORT_Compressor);
        }
        compressor.start();

        if (clutchRelease == null) {
            clutchRelease = new Solenoid(RobotMap.SOLENOID_PORT_ReleaseExtend);
        }
        if (clutchEngage == null) {
            clutchEngage = new Solenoid(RobotMap.SOLENOID_PORT_ReleaseRetract);
        }
        clutchRelease.set(true);
        clutchEngage.set(false);

        releaseSwitch = new DigitalInput(RobotMap.DIGITAL_PORT_ReleaseSwitch);
        LiveWindow.addSensor("Launcher", "Limit Switch 1", releaseSwitch);

        launchMotor = new Talon(RobotMap.PWN_Launcher);
        launchMotor.stopMotor();
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void engageClutch() {
        setClutchEngaged(true);
    }

    public void disengageClutch() {
        setClutchEngaged(false);
    }

    private void setClutchEngaged(boolean extend) {
        if (extend) {
            if ((clutchEngage != null) && (clutchRelease != null)) {
                clutchRelease.set(true);
                clutchEngage.set(false);
            }
        } else {
            if ((clutchEngage != null) && (clutchRelease != null)) {
                clutchEngage.set(true);
                clutchRelease.set(false);
            }
        }
    }

    public void cock(boolean rewind) {

        if (rewind) {
            launchMotor.set(-0.5);
        } else {
            launchMotor.stopMotor();
        }

    }

    public boolean isCocked() {
        // test
//        isCocked2();
        // end test
        boolean cocked = !releaseSwitch.get();
        SmartDashboard.putBoolean("Launcher Ready", cocked);
        return cocked;
    }

    public boolean isCocked2() {
        double shortRange = Robot.sensor.getShortRangeIRDistance();
        boolean cocked = !(shortRange > 6);
        SmartDashboard.putBoolean("Launcher Ready IR", cocked);
        return cocked;
    }

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
