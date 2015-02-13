
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
@Deprecated
public class VoltageGrab extends Command
{
	
	private boolean allDone;
	
	public VoltageGrab()
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.forks);
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end()
	{
		System.out.println("================================= VoltageGrab end");
		Robot.forks.setJag(0);
		Robot.forks.setJagForPositionControl();
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		/*
		 * if (Robot.forks.getJagCurrent() > 8 && Robot.forks.getJagSetpoint() == 12)
		 * {
		 * Robot.forks.setJag(6);
		 * }
		 */
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		System.out.println("================================= VoltageGrab initialize");
		Robot.forks.setJagForVoltageControl();
		Robot.forks.setJag(12);
		allDone = false;
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
		System.out.println("================================= VoltageGrab interupted");
		Robot.forks.setJag(0);
		Robot.forks.setJagForPositionControl();
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return allDone;
	}
}
