
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ForksSmartClose extends Command
{
	private boolean allDone;
	private boolean hasBox;
	
	private double[] currents;
	int count;
	
	public ForksSmartClose()
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.forks);
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		allDone = false;
		hasBox = false;
		Robot.forks.setJag(12);
		currents = new double[5];
		count = 0;
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		currents[count % 5] = Robot.forks.getJagCurrent();
		
		double averageCurrent = 0;
		for (int i = 0; i < 5; i++)
		{
			averageCurrent += currents[i];
		}
		averageCurrent /= 5;
		
		if (Robot.forks.isNearLowLimit())
		{
			allDone = true;
		}
		if (averageCurrent > 8 && !hasBox)
		{
			hasBox = true;
			Robot.forks.setJag(2);
		}
		count++;
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
		System.out.println("ForksSmartClose ================= Interrupted");
		Robot.forks.setJag(0);
	}
}