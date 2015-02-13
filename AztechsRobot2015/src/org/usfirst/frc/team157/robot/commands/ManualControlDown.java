
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualControlDown extends Command
{
	private boolean allDone;
	
	public ManualControlDown()
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.elevator);
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end()
	{
		Robot.elevator.setJag(0);
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		if (Robot.elevator.isLowLimitSwitchClosed())
		{
			allDone = true;
		}
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		allDone = false;
		Robot.elevator.setJag(-12);
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
		Robot.elevator.setJag(0);
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return allDone;
	}
}
