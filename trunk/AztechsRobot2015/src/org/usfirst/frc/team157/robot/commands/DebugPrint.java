
package org.usfirst.frc.team157.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Teju Nareddy
 *
 */
public class DebugPrint extends Command
{
	
	private String printString;
	
	public DebugPrint(String data)
	{
		// Use requires() here to declare subsystem dependencies
		printString = data;
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
		System.out.println(printString);
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
