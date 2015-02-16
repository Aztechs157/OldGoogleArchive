
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.subsystems.ForkliftPart;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Teju Nareddy
 */

@Deprecated
public class PositionSet extends Command
{
	private double positionToSet;
	private ForkliftPart part;
	
	public PositionSet(double positionToSet, ForkliftPart part)
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(part);
		this.positionToSet = positionToSet;
		this.part = part;
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
		
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		part.setJagPosition(positionToSet);
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
