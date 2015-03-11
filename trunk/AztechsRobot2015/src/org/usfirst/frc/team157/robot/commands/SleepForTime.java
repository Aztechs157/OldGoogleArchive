
package org.usfirst.frc.team157.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author Teju
 */
public class SleepForTime extends Command
{
	
	double sleepIntervalSec;
	double wakeTime;
	
	public SleepForTime(double timeToSleep)
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		
		sleepIntervalSec = timeToSleep;
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end()
	{
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
		wakeTime = Timer.getFPGATimestamp() + sleepIntervalSec;
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
		end();
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return Timer.getFPGATimestamp() > wakeTime;
	}
}
