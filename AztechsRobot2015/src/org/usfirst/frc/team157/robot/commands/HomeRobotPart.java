
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.HomeSensor;
import org.usfirst.frc.team157.robot.HomeSensor.Zone;
import org.usfirst.frc.team157.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author Teju Nareddy
 *
 */

public class HomeRobotPart extends Command
{
	public enum RobotPart
	{
		Elevator, Forks
	}
	
	private RobotPart homingPart;
	private Zone currentZone;
	private Zone targetZone;
	
	// Default voltage of the jaguar
	private double jagVoltage = 6;
	private boolean allDone;
	
	public HomeRobotPart(Zone target, RobotPart part)
	{
		requires(Robot.forklift);
		targetZone = target;
		homingPart = part;
	}
	
	public HomeRobotPart(Zone target, RobotPart part, double voltage)
	{
		this(target, part);
		jagVoltage = voltage;
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
		currentZone = Robot.forklift.getAppropriateZone(homingPart);
		if (!currentZone.equals(targetZone))
		{
			if (HomeSensor.zoneToNumber(currentZone) > HomeSensor.zoneToNumber(targetZone))
			{
				Robot.forklift.setAppropriateVoltage(jagVoltage, homingPart);
			}
			else
			{
				Robot.forklift.setAppropriateVoltage(-jagVoltage, homingPart);
			}
		}
		else
		{
			Robot.forklift.setAppropriateVoltage(0, homingPart);
			allDone = true;
		}
		
		// System.out.println(currentZone.toString() + " going to " + targetZone.toString());
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
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
