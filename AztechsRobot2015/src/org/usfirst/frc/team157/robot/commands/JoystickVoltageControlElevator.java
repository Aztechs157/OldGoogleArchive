
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.OI;
import org.usfirst.frc.team157.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoystickVoltageControlElevator extends Command
{
	
	public JoystickVoltageControlElevator()
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.elevator);
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		double speed = Robot.oi.operator.getY();
		speed *= OI.OPERATOR_Y_SCALE;
		
		// System.out.println("Speed = " + speed + " Type = " + Robot.oi.getDriverType().toString());
		
		if (speed > 0 && Robot.elevator.isHighLimitSwitchClosed())
		{
			speed = 0;
		}
		else if (speed < 0 && Robot.elevator.isLowLimitSwitchClosed())
		{
			speed = 0;
		}
		
		if (Robot.oi.getDriverType().equals(OI.DriverType.OPERATOR))
		{
			Robot.elevator.setJag(speed * 12);
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return false;
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end()
	{
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
	}
}
