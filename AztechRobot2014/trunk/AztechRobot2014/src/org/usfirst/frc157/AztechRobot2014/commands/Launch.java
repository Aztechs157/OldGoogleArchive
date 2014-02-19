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
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("LAUNCH Init");
        if (Robot.launcher.isCocked() == false) {
            readyToLaunch = false;
        } else {
            readyToLaunch = true;
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (readyToLaunch) {
            System.out.println("LAUNCH Ready To Launch");
            Robot.launcher.disengageClutch();
            launchTime = Timer.getFPGATimestamp();

            if ((Timer.getFPGATimestamp() - launchTime) > 0.5) {
                if (Robot.launcher.isCocked() == false) {
                    System.out.println(" - Cock");
                    Robot.launcher.engageClutch();
                    Robot.launcher.cock(true);
                } else {
                    Robot.launcher.cock(false);
                    System.out.println(" - LOCKED");
                    finshed = true;
                }
            }
        } else {
            System.out.println("LAUNCH Recocking");
            if (Robot.launcher.isCocked() == false) {
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
        return finshed;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
