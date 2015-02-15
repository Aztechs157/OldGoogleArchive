
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import org.usfirst.frc.team157.robot.subsystems.ForkliftPart;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunAPartForTime extends Command
{
	private double speed;
	private double driveTime;
	private double stopTime;
	private ForkliftPart part;
	
	public RunAPartForTime(double leftSpeed, double timeSeconds, ForkliftPart part)
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drive);
		this.speed = leftSpeed;
		driveTime = timeSeconds;
		this.part = part;
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end()
	{
		part.setJag(0);
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		stopTime = Timer.getFPGATimestamp() + driveTime;
		part.setJag(speed);
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
