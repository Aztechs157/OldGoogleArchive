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
    
    public Turn(double inDegrees) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        
        degrees = inDegrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        AztechRobot.drive.setCoastMode(false);
        AztechRobot.drive.mecanumDrive_Cartesian(0, 0, degrees);

        duration = .75;
        startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (startTime >= (Timer.getFPGATimestamp() + duration)) {
            AztechRobot.drive.mecanumDrive_Cartesian(0, 0, 0);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (startTime >= (Timer.getFPGATimestamp() + duration + 0.2)) {
            return true;
        } else {
            return false;
        }
    }
    // Called once after isFinished returns true
    protected void end() {
        AztechRobot.drive.setCoastMode(true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
