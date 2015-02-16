
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnForTicks extends Command
{
	private int ticks;
	private boolean allDone;
	private int initTick;
	
	public TurnForTicks(int ticks)
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drive);
		this.ticks = ticks;
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		allDone = false;
		initTick = Robot.drive.getRightEncoderTicks();
		Robot.drive.tankDrive(-1, 1);
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		System.out.println("Current = " + Robot.drive.getRightEncoderTicks() + " Init = " + initTick + " Total = " + ticks);
		if (Robot.drive.getRightEncoderTicks() - initTick > ticks)
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
		Robot.drive.tankDrive(0, 0);
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
		Robot.drive.tankDrive(0, 0);
	}
}
