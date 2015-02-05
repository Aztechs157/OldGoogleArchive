
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import org.usfirst.frc.team157.robot.subsystems.Forklift;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualControlDown extends Command
{
	Forklift.ForkliftPart partToMove;
	
	public ManualControlDown(Forklift.ForkliftPart part)
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		partToMove = part;
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
		//Robot.forklift.setAppropriateVoltage(-12, partToMove);
		Robot.forklift.moveTowardLowerPotentiometerVoltage(partToMove);
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
