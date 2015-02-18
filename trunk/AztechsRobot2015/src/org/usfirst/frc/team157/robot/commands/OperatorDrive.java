
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.OI;
import org.usfirst.frc.team157.robot.Robot;
import org.usfirst.frc.team157.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		rightSpeed = Robot.oi.driverRight.getY();
		leftSpeed = Robot.oi.driverLeft.getY();
		// System.out.println("Joysticks L:" + leftSpeed + " R:" + rightSpeed);
		
		// If speeds are close enough, then both speeds are set equal. Allows the robot to drive straight
		if (Math.abs(rightSpeed - leftSpeed) <= 0.05)
		{
			leftSpeed = rightSpeed; // RightSpeed is dominant
		}
		
		rightSpeed *= OI.RIGHT_DRIVER_Y_SCALE;
		leftSpeed *= OI.LEFT_DRIVER_Y_SCALE;
		
		Robot.drive.tankDrive(leftSpeed, rightSpeed);
		
		SmartDashboard.putNumber("Accelerometer X", RobotMap.accelerometer.getX());
		SmartDashboard.putNumber("Accelerometer Y", RobotMap.accelerometer.getY());
		SmartDashboard.putNumber("Accelerometer Z", RobotMap.accelerometer.getZ());
		/*
		 * if (RobotMap.accelerometer.getZ() < 0.9)
		 * {
		 * System.out.println("======================= TIPPING =======================");
		 * }
		 */
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
		return false;
	}
}
