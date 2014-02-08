/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc157.AztechRobot2014.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author aztechs
 */
public class SleepCommand extends Command{
    
    double sleepIntervalSec;
    double wakeTime;
    
    public SleepCommand(double inSleepIntervalSec) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        
        sleepIntervalSec = inSleepIntervalSec;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        wakeTime = Timer.getFPGATimestamp() + sleepIntervalSec;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Timer.getFPGATimestamp() > wakeTime;
    }
    
    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}