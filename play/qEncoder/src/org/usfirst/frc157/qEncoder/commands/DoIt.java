// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.


package org.usfirst.frc157.qEncoder.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc157.qEncoder.Robot;
import org.usfirst.frc157.qEncoder.subsystems.EncJag;

/**
 *
 */
public class  DoIt extends Command {

    private static int theValue = 0;
    
    private int delta;
    public DoIt(int inDelta) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.encJag);
        // eg. requires(chassis);
	delta = inDelta;
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        System.out.println("Command \"DoIt(\" " + inDelta + ") constructed");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("DoIt command starting - delta is " + delta);
        theValue = theValue + delta;
        
        if(delta == 0){
            theValue = 0;
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.encJag.setSpeed(theValue);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        System.out.println("DoIt command complete - value is " + theValue);
        return true;        
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
