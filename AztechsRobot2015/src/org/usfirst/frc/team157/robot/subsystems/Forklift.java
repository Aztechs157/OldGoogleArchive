
package org.usfirst.frc.team157.robot.subsystems;

import org.usfirst.frc.team157.robot.HomeSensor;
import org.usfirst.frc.team157.robot.HomeSensor.Zone;
import org.usfirst.frc.team157.robot.RobotMap;
import org.usfirst.frc.team157.robot.commands.HomeRobotPart.RobotPart;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Forklift subsystem of the robot.
 *
 * @author Teju Nareddy
 *
 */

public class Forklift extends Subsystem
{
	/*
	 * Use generic CANJaguar (not ScaledCANJaguar) as we don't actually care about the methods associated with the more-specific
	 * ScaledCANJaguar in this class. We only use the scaling factor when we instantiate the jaguar, which takes place in RobotMap
	 */
	public CANJaguar elevatorJag = RobotMap.elevatorJag;
	public CANJaguar forksJag = RobotMap.forksJag;
	
	// Create and Instantiate HomeSensors for the forks and elevator
	public HomeSensor forksHome = new HomeSensor(RobotMap.forkHomeMid, RobotMap.forkHomeEnd);
	public HomeSensor elevatorHome = new HomeSensor(RobotMap.elevatorHomeMid, RobotMap.elevatorHomeEnd);
	
	public Zone getAppropriateZone(RobotPart part)
	{
		if (part.equals(RobotPart.Elevator))
		{
			return getElevatorZone();
		}
		else if (part.equals(RobotPart.Forks))
		{
			return getForksZone();
		}
		System.out.println("Something has gone wrong! getAppropriateZone() in Forklift is returning null...");
		return null;
	}
	
	public Zone getElevatorZone()
	{
		return elevatorHome.getZone();
	}
	
	public Zone getForksZone()
	{
		return forksHome.getZone();
	}
	
	public void setAppropriateVoltage(double voltage, RobotPart part)
	{
		if (part.equals(RobotPart.Elevator))
		{
			setElevatorVoltage(voltage);
		}
		else if (part.equals(RobotPart.Forks))
		{
			setForksVoltage(voltage);
		}
		System.out.println("Something has gone wrong! setAppropriateVoltage() in Forklift did not set a voltage...");
	}
	
	public void setElevatorVoltage(double voltage)
	{
		if (elevatorJag != null)
		{
			elevatorJag.set(voltage);
		}
		else
		{
			System.out.println("Forklift Elevator Jag is null!");
		}
	}
	
	public void setForksVoltage(double voltage)
	{
		if (forksJag != null)
		{
			forksJag.set(voltage);
		}
		else
		{
			System.out.println("Forklift Forks Jag is null!");
		}
	}
	
	@Override
	protected void initDefaultCommand()
	{
		// Set the subsystem's default command here
		// setDefaultCommand(new GetSwitchStates());
	}
}
