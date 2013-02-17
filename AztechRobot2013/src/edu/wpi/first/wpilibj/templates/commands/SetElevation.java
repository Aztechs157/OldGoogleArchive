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
public class SetElevation extends CommandBase {
    
    private static final double allowedMotorTime = 1.0; //seconds
    private double degrees;
    private static double startTime;
    private boolean isFinished;
    
    public SetElevation(double inDegrees) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        degrees = inDegrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        isFinished = false;
        CommandBase.shooter.enableElevation(true);
        CommandBase.shooter.setShooterElevation(degrees);
        startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(Timer.getFPGATimestamp() > (startTime + allowedMotorTime))
        {
            isFinished = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
        CommandBase.shooter.enableElevation(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
