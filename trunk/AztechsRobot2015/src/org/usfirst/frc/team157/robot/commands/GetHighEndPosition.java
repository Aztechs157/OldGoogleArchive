
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.subsystems.ForkliftPart;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GetHighEndPosition extends Command
{
	
	private ForkliftPart part;
	private boolean allDone;
	
	public GetHighEndPosition(ForkliftPart part)
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
		part.setHighEndEncoderLimit(position);
		System.out.println("High end position for " + part.getName() + " is: " + position);
		part.setJagScale(1);
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		part.setJag(part.getHighEndEncoderLimit());
		if (part.isHighLimitSwitchClosed())
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