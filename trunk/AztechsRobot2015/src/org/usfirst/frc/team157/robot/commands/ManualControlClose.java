
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.subsystems.ForkliftPart;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualControlClose extends Command
{
	
	private ForkliftPart part;
	
	public ManualControlClose(ForkliftPart part)
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
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		// if (part.getJagPosition() - part.getLowEndEncoderLimit() > 0.05)
		
		// System.out.println("Setpoint = " + part.getJagSetpoint());
		
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		part.setJag(part.getLowEndEncoderLimit());
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