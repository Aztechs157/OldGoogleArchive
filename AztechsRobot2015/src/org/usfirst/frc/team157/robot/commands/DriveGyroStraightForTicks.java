
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveGyroStraightForTicks extends Command
{
	private double P = 0.01;
	
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
		Robot.drive.tankDrive(leftSpeed, rightSpeed);
		
		currentGyroAngle = Robot.drive.getAngle();
		
		double changeInSpeed = currentGyroAngle * P;
		rightSpeed += changeInSpeed;
		leftSpeed -= changeInSpeed;
		
		currentTicksLeft = Robot.drive.getLeftEncoderTicks();
		currentTicksRight = Robot.drive.getRightEncoderTicks();
		
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
		leftSpeed = 0.7;
		rightSpeed = 0.8;
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
