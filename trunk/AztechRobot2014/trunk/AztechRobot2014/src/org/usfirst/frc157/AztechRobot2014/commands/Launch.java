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

    private static boolean terminateCommand = false;

    boolean readyToLaunch = false;
    boolean finshed = false;
    private double launchTime = 0;
    private double commandStartTime = 0;

    public Launch() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.launcher);
    }

    public static void terminateCommand() {
        terminateCommand = true;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        terminateCommand = false;
        System.out.println("LAUNCH Init");
        if (Robot.launcher.isCocked() == false) {
            System.out.println("Recock");
            readyToLaunch = false;
            // need to cock so do so
            Robot.launcher.engageClutch();
            Robot.launcher.cock(true);
            finshed = false;
        } else {
            System.out.println("FIRE");
            readyToLaunch = true;
            // ready to launch, so do so
            Robot.launcher.disengageClutch();
            finshed = true;
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (readyToLaunch) {
            System.out.println("Launched");
        } else {
            System.out.println("LAUNCH Recocking");
            if (Robot.launcher.isCocked() == true) {
                Robot.launcher.cock(false);
                System.out.println(" - COCKED");
                finshed = true;
            }
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//        System.out.println("cst" + commandStartTime + " n" + Timer.getFPGATimestamp());
//        if((commandStartTime + 15) >  Timer.getFPGATimestamp())
//        {
//            System.out.println("Shot Timeout");
//            return true;
//        }
        return (finshed || terminateCommand);
    }

    // Called once after isFinished returns true
    protected void end() {
        System.out.println("Launch Complete");
        if (terminateCommand) {
            Robot.launcher.cock(false);
        }
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
