
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.subsystems.ForkliftPart;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualPositionHigherPart extends Command
{
	
	private ForkliftPart part;
	
	public ManualPositionHigherPart(ForkliftPart part)
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(part);
		this.part = part;
		System.out.println("============================= ManualControlUp.ManualControlUp()");
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end()
	{
		System.out.println("============================= ManualControlUp.end");
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		// if (part.getHighEndEncoderLimit() - part.getJagPosition() > 0.05)
		System.out.println("============================= ManualControlUp.execute");
		// System.out.println("Setpoint = " + part.getJagSetpoint());
		
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		System.out.println("============================= ManualControlUp.initialize");
		part.setJag(part.getHighEndEncoderLimit());
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
		System.out.println("============================= ManualControlUp.interrupted");
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return true;
	}
}
