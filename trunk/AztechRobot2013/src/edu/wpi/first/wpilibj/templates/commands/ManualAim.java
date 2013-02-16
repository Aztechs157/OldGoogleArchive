/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.templates.AztechRobot;

/**
 *
 * @author MattKahn
 */
public class ManualAim extends CommandBase {
    
    public ManualAim() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("MANUAL AIM ===================================================");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double rotation;
        double elevation;

        for(int idx = 0; idx < 10; idx++)
        {
            System.out.println("Axis [" + idx + "] = " + oi.getOperatorJoystick().getRawAxis(idx));
        }
        for(int idx = 0; idx < 25; idx++)
        {
            System.out.println("Button [" + idx + "] = " + oi.getOperatorJoystick().getRawButton(idx));
        }        
//        AztechRobot.drive.mecanumDrive_Cartesian(0, 0, rotation);
//        AztechRobot.shooter.adjustShooterElevation(elevation);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
