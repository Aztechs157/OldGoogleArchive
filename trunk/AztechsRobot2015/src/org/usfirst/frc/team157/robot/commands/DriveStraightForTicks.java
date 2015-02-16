
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightForTicks extends Command
{
	private int ticks;
	private boolean allDone;
	
	private int initTicksLeft;
	private int initTicksRight;
	private int currentTicksLeft;
	private int currentTicksRight;
	
	private double leftSpeed = 0.8;
	private double rightSpeed = 0.8;
	
	public DriveStraightForTicks(int ticksToDrive)
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drive);
		ticks = ticksToDrive;
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		allDone = false;
		initTicksLeft = Robot.drive.getLeftEncoderTicks();
		initTicksRight = Robot.drive.getRightEncoderTicks();
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		Robot.drive.tankDrive(leftSpeed, rightSpeed);
		
		currentTicksLeft = Robot.drive.getLeftEncoderTicks() - initTicksLeft;
		currentTicksRight = Robot.drive.getRightEncoderTicks() - initTicksRight;
		
		if (currentTicksLeft - currentTicksRight > 1)
		{
			leftSpeed -= 0.0025;
			rightSpeed += 0.0025;
		}
		else if (currentTicksLeft - currentTicksRight < 1)
		{
			leftSpeed += 0.0025;
			rightSpeed -= 0.0025;
		}
		
		if (currentTicksLeft > ticks && currentTicksRight > ticks)
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
