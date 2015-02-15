
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.subsystems.ForkliftPart;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

@Deprecated
public class CalibrateLowPosition extends Command
{
	
	private ForkliftPart part;
	private boolean allDone;
	
	public CalibrateLowPosition(ForkliftPart part)
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(part);
		this.part = part;
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end()
	{
		double position = part.getJagPosition();
		part.setJag(position); // Stops it
		part.setLowEndEncoderLimit(position);
		System.out.println("Low end position for " + part.getName() + " is: " + position);
		part.setJagScale(1);
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		part.setJag(part.getLowEndEncoderLimit());
		if (part.isLowLimitSwitchClosed())
		{
			allDone = true;
		}
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		part.setJagScale(0.25);
		allDone = false;
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
		part.setJagScale(1);
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return allDone;
	}
}
