
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Grab extends Command
{
	private double[] currents;
	private int count;
	private double averageCurrent;
	private boolean allDone;
	
	public Grab()
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.forks);
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		Robot.forks.setJagPosition(Robot.forks.getLowEndEncoderLimit());
		currents = new double[5];
		count = 0;
		allDone = false;
		// clear the average
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		// TODO add constants
		double current = Robot.forks.getJagCurrent();
		
		// average the current over the last 5 samples
		count++;
		currents[count % 5] = current;
		
		averageCurrent = 0;
		for (int i = 0; i < 5; i++)
		{
			averageCurrent += currents[i];
		}
		averageCurrent /= 5;
		
		if (averageCurrent > 4) // Threshold
		{
			allDone = true;
			System.out.println("Grabbed something!");
			Robot.forks.setJagPosition(Robot.forks.getJagPosition()); // Stop them from crushing the object
		}
		
		if (Robot.forks.getJagPosition() - Robot.forks.getLowEndEncoderLimit() < 0.05)
		{
			allDone = true;
			System.out.println("Did not grab anything!");
			Robot.forks.setJagPosition(Robot.forks.getHighEndEncoderLimit()); // Open them again
		}
		// if the average current is greater than a threshold
		// then stop (allDone = true)
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
		
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
	}
}
