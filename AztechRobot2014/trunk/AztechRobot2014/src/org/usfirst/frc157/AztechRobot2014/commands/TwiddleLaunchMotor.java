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
public class TwiddleLaunchMotor extends Command {

    private double commandTime;
    private boolean finished = false;

    public TwiddleLaunchMotor() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.launcher);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        commandTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double now = Timer.getFPGATimestamp() - commandTime;

        int msNow = (int) (500.0 * now);

        if (msNow % 2 == 0) {
            System.out.println("FW - @" + now);
            Robot.launcher.getCockingMotor().set(0.5);
        } else {
            System.out.println("Rv - @" + now);
            Robot.launcher.getCockingMotor().set(-0.5);
        }

        if (now > 3) {
            finished = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.launcher.getCockingMotor().stopMotor();
        System.out.println("Twiddling Complete");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Robot.launcher.getCockingMotor().stopMotor();
    }
}
