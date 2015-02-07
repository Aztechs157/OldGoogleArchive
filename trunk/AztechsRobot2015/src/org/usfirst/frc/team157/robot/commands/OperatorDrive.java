
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.OI;
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
		double rightSpeed = 0;
		double leftSpeed = 0;
		
		// Determine the main controller for driving -- See OI.java
		if (Robot.oi.getDriverType().equals(OI.DriverType.LogitechController))
		{
			leftSpeed = Robot.oi.logitechDriver.getLeftY();
			rightSpeed = Robot.oi.logitechDriver.getRightY();
			
			System.out.println("Logitech L:" + leftSpeed + " R:" + rightSpeed);
		}
		else if (Robot.oi.getDriverType().equals(OI.DriverType.Joysticks))
		{
			rightSpeed = Robot.oi.driverRight.getY();
			leftSpeed = Robot.oi.driverLeft.getY();
			// System.out.println("Joysticks L:" + leftSpeed + " R:" + rightSpeed);
		}
		else
		{
			System.out.println("OperatorType in RobotMap is not defined properly! Don't know "
					+ "what to use in OperatorDrive to determine the wheel speeds..." + Robot.oi.getDriverType().toString());
		}
		
		// If speeds are close enough, then both speeds are set equal. Allows the robot to drive straight
		if (Math.abs(rightSpeed - leftSpeed) <= 0.05)
		{
			leftSpeed = rightSpeed; // RightSpeed is dominant
		}
		
		rightSpeed *= OI.RIGHT_DRIVER_Y_SCALE;
		leftSpeed *= OI.LEFT_DRIVER_Y_SCALE;
		
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
