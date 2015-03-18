
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
@Deprecated
public class DriveStraightForTicks extends Command
{
	private int ticks;
	private boolean allDone;
	
	private int currentTicksLeft;
	private int currentTicksRight;
	private int lastTicksLeft;
	private int lastTicksRight;
	
	private double leftSpeed;
	private double rightSpeed;
	
	public DriveStraightForTicks(int ticksToDrive)
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drive);
		ticks = ticksToDrive;
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
		
		lastTicksLeft = currentTicksLeft;
		lastTicksRight = currentTicksRight;
		
		currentTicksLeft = Robot.drive.getLeftEncoderTicks();
		currentTicksRight = Robot.drive.getRightEncoderTicks();
		
		int leftChange = currentTicksLeft - lastTicksLeft;
		int rightChange = currentTicksRight - lastTicksRight;
		
		if (leftChange > rightChange)
		{
			leftSpeed -= 0.005;
			rightSpeed += 0.005;
		}
		else if (leftChange < rightChange)
		{
			leftSpeed += 0.005;
			rightSpeed -= 0.005;
		}
		
		if (currentTicksLeft >= ticks || currentTicksRight > ticks)
		{
			allDone = true;
		}
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		allDone = false;
		currentTicksLeft = Robot.drive.getLeftEncoderTicks();
		currentTicksRight = Robot.drive.getRightEncoderTicks();
		leftSpeed = 0.8;
		rightSpeed = 0.9;
		Robot.drive.resetEncoders();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
		Robot.drive.tankDrive(0, 0);
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return allDone;
	}
}
