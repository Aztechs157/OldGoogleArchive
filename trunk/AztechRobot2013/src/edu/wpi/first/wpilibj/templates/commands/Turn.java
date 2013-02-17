/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.templates.AztechRobot;

/**
 *
 * @author MattKahn
 */
public class Turn extends CommandBase {
    
    double degrees = 0;
    double startTime;
    double duration = 0;
    boolean isFinished = false;
    double direction = 1;  // -1 or 1
    double rate = 0;
    
    public Turn(double inDegrees) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        
        degrees = inDegrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if (degrees < 0)
        {
            direction = -1;
        }
        if (degrees >= 0) 
        {
            direction = 1;
        }
        rate = 50 * direction;
                
        duration = Math.abs(degrees) * 0.01;
        
        startTime = Timer.getFPGATimestamp();
        System.out.println("Turn @ " + startTime + " for " + duration);
        isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (Timer.getFPGATimestamp() > (startTime + duration)) {
            CommandBase.drive.mecanumDrive_Cartesian(0, 0, 0);
            CommandBase.drive.setCoastMode(false);
            isFinished = true;
        } else {
            CommandBase.drive.mecanumDrive_Cartesian(0, 0, rate);
        }
            
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }
    // Called once after isFinished returns true
    protected void end() {
        CommandBase.drive.setCoastMode(true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
