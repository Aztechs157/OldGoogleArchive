/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc157.AztechRobot2014.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc157.AztechRobot2014.Robot;

/**
 *
 * @author mattkahn
 */
public class Launch extends Command {

    boolean readyToLaunch = false;
    boolean finshed = false;
    private double launchTime = 0;

    public Launch() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.launcher);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if (Robot.launcher.isCocked()) {
            readyToLaunch = true;
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (readyToLaunch) {
            Robot.launcher.disengageClutch();
            launchTime = Timer.getFPGATimestamp();

            while ((Timer.getFPGATimestamp() - launchTime) < 0.5) //waits 0.5 seconds
            {
                //do nothing
            }

            if (!Robot.launcher.isCocked()) {
                Robot.launcher.engageClutch();
                Robot.launcher.cock(true);
            } else {
                Robot.launcher.cock(false);
                finshed = true;
            }
        } else {
            if (!Robot.launcher.isCocked()) {   //Check this
                Robot.launcher.engageClutch();
                Robot.launcher.cock(true);
            } else {
                Robot.launcher.cock(false);
                finshed = true;
            }
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return readyToLaunch;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
