/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author MattKahn
 */
public class PrintMessage extends Command {

    private String m_message;

    public PrintMessage(String message) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        m_message = message;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("PrintMessage - \"" + m_message + "\"");
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
