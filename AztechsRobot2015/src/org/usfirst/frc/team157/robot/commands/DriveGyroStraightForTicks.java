
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveGyroStraightForTicks extends Command
{
	// FIXME Check constant
	private double P = -0.0002;
	
	private int ticks;
	private boolean allDone;
	
	private int currentTicksLeft;
	private int currentTicksRight;
	
	private double currentGyroAngle;
	
	private double leftSpeed;
	private double rightSpeed;
	
	public DriveGyroStraightForTicks(int ticksToDrive)
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
		currentTicksLeft = Math.abs(Robot.drive.getLeftEncoderTicks());
		currentTicksRight = Math.abs(Robot.drive.getRightEncoderTicks());
		currentGyroAngle = Robot.drive.getAngle();
		
		double changeInSpeed = currentGyroAngle * P;
		rightSpeed += changeInSpeed;
		leftSpeed -= changeInSpeed;
		
		Robot.drive.tankDrive(leftSpeed, rightSpeed);
		
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
		leftSpeed = 0.75;
		rightSpeed = 0.95;
		Robot.drive.resetEncoders();
		Robot.drive.resetGyro();
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
