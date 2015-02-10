
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Grab extends Command
{
	private static final int SAMPLE_SIZE = 5;
	
	private int count;
	private double[] currents;
	private double averageCurrent;
	
	private boolean allDone;
	
	public Grab()
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.forks);
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
		double current = Robot.forks.getJagCurrent();
		
		// average the current over the last sampleSize samples
		count++;
		currents[count % SAMPLE_SIZE] = current;
		
		averageCurrent = 0;
		for (int i = 0; i < SAMPLE_SIZE; i++)
		{
			averageCurrent += currents[i];
		}
		averageCurrent /= SAMPLE_SIZE;
		
		// Logic based on the current
		if (averageCurrent > Robot.forks.getGrabCurrentThreshold()) // Threshold
		{
			allDone = true;
			System.out.println("Grabbed something!");
			Robot.forks.setJagPosition(Robot.forks.getJagPosition()); // Stop them from crushing the object
		}
		else if (Robot.forks.getJagPosition() - Robot.forks.getLowEndEncoderLimit() < 0.05) // Near end limit: Did not grab
																							// anything
		{
			allDone = true;
			System.out.println("Did not grab anything!");
			Robot.forks.setJagPosition(Robot.forks.getHighEndEncoderLimit()); // Open them again
		}
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		Robot.forks.setJagPosition(Robot.forks.getLowEndEncoderLimit());
		currents = new double[SAMPLE_SIZE];
		count = 0;
		allDone = false;
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
		return allDone;
	}
}
