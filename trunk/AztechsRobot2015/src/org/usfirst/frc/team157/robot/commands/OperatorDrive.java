
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author Teju Nareddy
 *
 */
public class OperatorDrive extends Command
{
	
	public OperatorDrive()
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drive);
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
		double rightSpeed = Robot.oi.rightJoystick.getY();
		double leftSpeed = Robot.oi.leftJoystick.getY();
		
		if (Math.abs(rightSpeed - leftSpeed) <= 0.05)
		{
			leftSpeed = rightSpeed;
		}
		
		Robot.drive.tankDrive(leftSpeed, rightSpeed);
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		
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
		return true;
	}
}
