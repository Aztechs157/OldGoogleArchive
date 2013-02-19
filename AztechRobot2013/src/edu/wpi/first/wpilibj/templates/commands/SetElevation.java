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
    
    private double degrees;
    
    public SetElevation(double inDegrees) {
        degrees = inDegrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("setElevation(" + degrees + ")");
        CommandBase.shooter.setShooterElevation(degrees);
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
        end();
    }
}
