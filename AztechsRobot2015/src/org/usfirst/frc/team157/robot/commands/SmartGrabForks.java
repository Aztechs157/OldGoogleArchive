
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SmartGrabForks extends Command
{
	private boolean allDone;
	
	private double[] currents;
	int count;
	
	public SmartGrabForks()
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.forks);
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end()
	{
		Robot.forks.setJagVoltage(0);
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		currents[count % 5] = Robot.forks.getJagCurrent();
		
		double averageCurrent = 0;
		for (int i = 0; i < 5; i++)
		{
			averageCurrent += currents[i];
		}
		averageCurrent /= 5;
		SmartDashboard.putNumber("Current", averageCurrent);
		if (Robot.forks.isLowLimitSwitchClosed())
		{
			allDone = true;
		}
		if (averageCurrent > 8 && !Robot.forks.hasBox)
		{
			Robot.forks.hasBox = true;
			SmartDashboard.putBoolean("Has Box", true);
			System.out.println("====== Forks have grabbed an object! ======");
			Robot.forks.setJagVoltage(2);
		}
		count++;
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		allDone = false;
		Robot.forks.hasBox = false;
		SmartDashboard.putBoolean("Has Box", false);
		Robot.forks.setJagVoltage(12);
		currents = new double[5];
		count = 0;
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
		Robot.forks.setJagVoltage(0);
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return allDone;
	}
}
