/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author MattKahn
 */
public class Turn extends CommandBase {

    double tolerance = 1.0;
    double degrees = 0;
    boolean timedOut;
    double startTime;
    
    public Turn(double inDegrees) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(CommandBase.drive);

        degrees = inDegrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("Turning " + degrees + " deg");
        drive.resetGyro();
        startTime = Timer.getFPGATimestamp();
        timedOut = false;
        
        // compute turn rate.  
        double rate = -1.0 * (drive.getAngle() - degrees) / 360.0;  // slow turn 
        CommandBase.drive.mecanumDrive_Cartesian(0, 0, rate);        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(Timer.getFPGATimestamp() > (startTime + 2))  // two seconds to get there
        {
            timedOut = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       return (timedOut || (Math.abs(degrees - drive.getAngle()) < tolerance));
    }
    // Called once after isFinished returns true
    protected void end() {
        CommandBase.drive.mecanumDrive_Cartesian(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
