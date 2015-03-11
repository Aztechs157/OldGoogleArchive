
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
	
	// Called once after isFinished returns true
	@Override
	protected void end()
	{
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		double voltage = Robot.oi.logitechDriver.getRightY();
		voltage *= OI.OPERATOR_Y_SCALE;
		// System.out.println("Speed = " + speed);
		
		if (voltage > 0 && Robot.elevator.isHighLimitSwitchClosed())
		{
			voltage = 0;
		}
		else if (voltage < 0 && Robot.elevator.isLowLimitSwitchClosed())
		{
			voltage = 0;
		}
		
		if (Robot.oi.getDriverType().equals(OI.DriverType.DUAL_CONTROL))
		{
			Robot.elevator.setJagVoltage(voltage * 12);
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
