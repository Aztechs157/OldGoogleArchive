
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import org.usfirst.frc.team157.robot.subsystems.ForkliftPart;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VoltageSetPart extends Command
{
	private boolean allDone;
	private double voltage;
	private ForkliftPart part;
	
	public VoltageSetPart(double voltageToSet, ForkliftPart part)
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(part);
		voltage = voltageToSet;
		this.part = part;
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end()
	{
		part.setJagVoltage(0);
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		if (part.equals(Robot.forks))
		{
			if (voltage > 0 && part.isLowLimitSwitchClosed())
			{
				allDone = true;
			}
			else if (voltage < 0 && part.isHighLimitSwitchClosed())
			{
				allDone = true;
			}
			else if (voltage < 0 && Robot.forks.hasBox)
			{
				Robot.forks.hasBox = false;
				SmartDashboard.putBoolean("Has Box", false);
				System.out.println("====== Forks has dropped an object! ======");
			}
		}
		else if (part.equals(Robot.elevator))
		{
			if (voltage < 0 && part.isLowLimitSwitchClosed())
			{
				allDone = true;
			}
			else if (voltage > 0 && part.isHighLimitSwitchClosed())
			{
				allDone = true;
			}
		}
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		allDone = false;
		if (part.equals(Robot.forks))
		{
			if (voltage < 0 && part.isHighLimitSwitchClosed())
			{
				// Do nothing
			}
			else
			{
				part.setJagVoltage(voltage);
			}
		}
		else
		{
			part.setJagVoltage(voltage);
		}
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
		part.setJagVoltage(0);
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return allDone;
	}
}
