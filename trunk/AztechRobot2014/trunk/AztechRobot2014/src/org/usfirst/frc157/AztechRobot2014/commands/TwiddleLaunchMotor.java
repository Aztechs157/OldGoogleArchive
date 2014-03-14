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

    private int iterations = 0;
    private boolean finished = false;

    public TwiddleLaunchMotor() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.launcher);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        iterations = 0;
        Launch.terminateCommand();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

        iterations++;

        if ((iterations % 10) == 0) {
            if ((int) ((double) iterations / 10.0) % 2 == 0) {
                System.out.println("FW - @" + iterations);
                Robot.launcher.getCockingMotor().set(0.5);
            } else {
                System.out.println("Rv - @" + iterations);
                Robot.launcher.getCockingMotor().set(-0.5);
            }
        }
        if (iterations > 500) {
            finished = true;
            Robot.launcher.getCockingMotor().set(0.0);
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
