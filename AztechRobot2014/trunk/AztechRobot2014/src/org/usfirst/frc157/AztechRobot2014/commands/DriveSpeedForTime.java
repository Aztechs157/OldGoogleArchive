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
public class DriveSpeedForTime extends Command {

    private double leftSpeed;
    private double rightSpeed;
    private double driveTime;
    private double stopTime;

    public DriveSpeedForTime(double _leftSpeed, double _rightSpeed, double timeSeconds) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
        // eg. requires(chassis);
        leftSpeed = _leftSpeed;
        rightSpeed = _rightSpeed;
        driveTime = timeSeconds;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        stopTime = Timer.getFPGATimestamp() + driveTime;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.drive.tankDrive(leftSpeed, rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Timer.getFPGATimestamp() > stopTime;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drive.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
