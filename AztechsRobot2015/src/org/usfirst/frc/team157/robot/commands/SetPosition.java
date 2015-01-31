
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import org.usfirst.frc.team157.robot.subsystems.Forklift;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Teju Nareddy
 */
public class SetPosition extends Command
{
	private Forklift.ForkliftPart part;
	private double positionToSet;
	private boolean allDone;
	
	private double lastPosition;
	private double currentPosition;
	
	public SetPosition(Forklift.ForkliftPart part, double positionToSet)
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.part = part;
		this.positionToSet = positionToSet;
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
		lastPosition = currentPosition;
		currentPosition = Robot.forklift.getAppropriatePotentiometerPosition(part);
		
		System.out.println("Last Position = " + lastPosition + " Current Position = " + currentPosition);
		
		if (positionToSet == currentPosition || lastPosition < positionToSet && currentPosition > positionToSet
				|| lastPosition > positionToSet && currentPosition < positionToSet)
		{
			allDone = true;
			Robot.forklift.setAppropriateVoltage(0, part);
		}
		else if (positionToSet > currentPosition)
		{
			Robot.forklift.setAppropriateVoltage(-12, part);
		}
		else
		{
			Robot.forklift.setAppropriateVoltage(12, part);
		}
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		currentPosition = Robot.forklift.getAppropriatePotentiometerPosition(part);
		allDone = false;
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
		return allDone;
	}
}
