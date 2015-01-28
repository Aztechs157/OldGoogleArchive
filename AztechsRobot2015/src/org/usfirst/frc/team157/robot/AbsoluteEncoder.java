
package org.usfirst.frc.team157.robot;

import org.usfirst.frc.team157.robot.subsystems.Forklift;

/**
 *
 * @author Teju Nareddy
 *
 */
public class AbsoluteEncoder
{
	private Forklift.ForkliftPart part;
	
	private double offset = 0;
	private double initialPosition;
	
	public AbsoluteEncoder(HomeSensor.Boundry boundry, Forklift.ForkliftPart part)
	{
		this.part = part;
		
		offset = Forklift.getAppropriateOffset(boundry, part);
		initialPosition = Robot.forklift.getAppropriatePosition(part) + offset;
	}
	
	// TODO Figure out what exact position we want... Work the math out as well
	public double getCurrentPosition()
	{
		return Robot.forklift.getAppropriatePosition(part) + offset;
	}
	
	public double getInitialPosition()
	{
		return initialPosition;
	}
}
