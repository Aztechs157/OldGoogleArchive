
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import org.usfirst.frc.team157.robot.subsystems.Forklift;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Teju Nareddy
 */
public class PrintDebugData extends Command
{
	
	public PrintDebugData()
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
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
		/*
		 * System.out.print("[X,Y,Z]: [" + RobotMap.accelerometer.getX());
		 * System.out.print(", " + RobotMap.accelerometer.getY());
		 * System.out.print(", " + RobotMap.accelerometer.getZ() + "]\n");
		 */
		System.out.println("Encoder: " + Robot.forklift.getAppropriatePotentiometerPosition(Forklift.ForkliftPart.FORKS));
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