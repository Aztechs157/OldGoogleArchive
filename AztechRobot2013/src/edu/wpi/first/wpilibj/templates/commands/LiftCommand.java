/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.templates.AztechRobot;
import edu.wpi.first.wpilibj.templates.subsystems.LiftSubsystem;

/**
 *
 * @author aztechs
 */
public class LiftCommand extends CommandBase {
    
    LiftSubsystem lift = AztechRobot.lift;

    public LiftCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(lift);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if( oi.getFirstController().getButtonA() )
        {
            lift.retract();
        }
        else if( oi.getFirstController().getButtonB() )
        {
            lift.extend();
        }
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
