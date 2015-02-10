
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.subsystems.ForkliftPart;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GetLowEndPosition extends Command
{
	
	private ForkliftPart part;
	private boolean allDone;
	
	public GetLowEndPosition(ForkliftPart part)
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(part);
		this.part = part;
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		part.setJagScale(0.25);
		allDone = false;
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		part.setJagPosition(part.getLowEndEncoderLimit());
		if (part.isLowLimitSwitchClosed())
		{
			allDone = true;
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return allDone;
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end()
	{
		double position = part.getJagPosition();
		part.setJagPosition(position); // Stops it
		part.setLowEndEncoderLimit(position);
		System.out.println("Low end position for " + part.getName() + " is: " + position);
		part.setJagScale(1);
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
		part.setJagScale(1);
	}
}
