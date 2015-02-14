
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ForksClose extends Command
{
	private boolean allDone;
	private double gripTime;
	private double stopTime;
	
	public ForksClose()
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.forks);
		gripTime = 1;
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		stopTime = Timer.getFPGATimestamp() + gripTime;
		allDone = false;
		Robot.forks.setJag(12);
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		if (Robot.forks.isNearLowLimit() || stopTime <= Timer.getFPGATimestamp())
		{
			allDone = true;
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return allDone;
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end()
	{
		Robot.forks.setJag(0);
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
		System.out.println("ForksClose ================= Interrupted");
		Robot.forks.setJag(0);
	}
}
