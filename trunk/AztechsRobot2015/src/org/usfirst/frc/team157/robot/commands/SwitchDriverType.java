
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.OI.DriverType;
import org.usfirst.frc.team157.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Teju Nareddy
 */
public class SwitchDriverType extends Command
{
	
	public SwitchDriverType()
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
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
		DriverType current = Robot.oi.getDriverType();
		if (current.equals(DriverType.Joysticks))
		{
			Robot.oi.setLogitechDriveMode();
		}
		else if (current.equals(DriverType.LogitechController))
		{
			Robot.oi.setJoystickDriveMode();
		}
		else
		{
			System.out.println("SwitchDriverType failed to execute! driverType is not set properly...");
		}
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