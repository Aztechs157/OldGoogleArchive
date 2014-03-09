/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc157.AztechRobot2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc157.AztechRobot2014.Robot;

/**
 *
 * @author mattkahn
 */
public class JogLoader extends Command {
    
    private double vOffset;
    
    public JogLoader(double _vOffset) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        vOffset = _vOffset;
        
        System.out.println("Created Command JogLoader(" + vOffset + ")");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.ballRetriever.jogSetpointVoltage(vOffset);
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
