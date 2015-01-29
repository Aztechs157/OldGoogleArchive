
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.HomeSensor;
import org.usfirst.frc.team157.robot.Robot;
import org.usfirst.frc.team157.robot.subsystems.Forklift;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author Teju Nareddy
 *
 */

public class HomeAPart extends Command
{
	private Forklift.ForkliftPart homingPart;
	private HomeSensor.Zone initialZone;
	private HomeSensor.Zone currentZone;
	private HomeSensor.Zone targetZone;
	
	// Default voltage of the jaguar
	private double jagVoltage = 6;
	private boolean allDone;
	
	public HomeAPart(HomeSensor.Zone target, Forklift.ForkliftPart part)
	{
		this(part);
		targetZone = target;
	}
	
	public HomeAPart(HomeSensor.Zone target, Forklift.ForkliftPart part, double voltage)
	{
		this(target, part);
		jagVoltage = voltage;
	}
	
	public HomeAPart(Forklift.ForkliftPart part, double voltage)
	{
		this(part);
		jagVoltage = voltage;
	}
	
	public HomeAPart(Forklift.ForkliftPart part)
	{
		homingPart = part;
		initialZone = Robot.forklift.getAppropriateZone(homingPart);
		targetZone = HomeSensor.findNearestZone(initialZone);
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end()
	{
		// TODO finish all cases
		if (initialZone.equals(HomeSensor.Zone.ZONE_A) && initialZone.equals(HomeSensor.Zone.ZONE_B)
				|| initialZone.equals(HomeSensor.Zone.ZONE_B) && initialZone.equals(HomeSensor.Zone.ZONE_A))
		{
			Robot.forklift.setAppropriateBoundary(HomeSensor.Boundary.BOUNDARY_AB, homingPart);
		}
		else if (initialZone.equals(HomeSensor.Zone.ZONE_B) && initialZone.equals(HomeSensor.Zone.ZONE_C)
				|| initialZone.equals(HomeSensor.Zone.ZONE_C) && initialZone.equals(HomeSensor.Zone.ZONE_B))
		{
			Robot.forklift.setAppropriateBoundary(HomeSensor.Boundary.BOUNDARY_BC, homingPart);
		}
		else if (initialZone.equals(HomeSensor.Zone.ZONE_C) && initialZone.equals(HomeSensor.Zone.ZONE_D)
				|| initialZone.equals(HomeSensor.Zone.ZONE_D) && initialZone.equals(HomeSensor.Zone.ZONE_C))
		{
			Robot.forklift.setAppropriateBoundary(HomeSensor.Boundary.BOUNDARY_CD, homingPart);
		}
		else
		{
			System.out.println("HomeAPart Command did not finish successfully! Could not determine which boundary the "
					+ homingPart.toString() + " was in...");
		}
		
		Robot.forklift.constructAppropriateAbsoluteEncoder(homingPart);
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
