/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author Matt Kahn, Teju Nareddy
 */
public class DriveSpeedForTime extends Command
{
	
	private double leftSpeed;
	private double rightSpeed;
	private double driveTime;
	private double stopTime;
	
	public DriveSpeedForTime(double leftSpeed, double rightSpeed, double timeSeconds)
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drive);
		this.leftSpeed = leftSpeed;
		this.rightSpeed = rightSpeed;
		driveTime = timeSeconds;
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end()
	{
		Robot.drive.tankDrive(0, 0);
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		Robot.drive.tankDrive(leftSpeed, rightSpeed);
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		stopTime = Timer.getFPGATimestamp() + driveTime;
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return Timer.getFPGATimestamp() > stopTime;
	}
}
