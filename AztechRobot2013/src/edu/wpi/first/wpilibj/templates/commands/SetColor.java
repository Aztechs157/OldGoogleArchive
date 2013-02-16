/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.templates.AztechRobot;
import edu.wpi.first.wpilibj.templates.subsystems.Shooter;

/**
 *
 * @author MattKahn
 */
public class SetColor extends CommandBase {
    
    Shooter.Color theColor;
    
    public SetColor(Shooter.Color color) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        theColor = color;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        CommandBase.shooter.setColor(theColor);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
