
package org.usfirst.frc.team157.robot.subsystems;

import org.usfirst.frc.team157.robot.AbsoluteEncoder;
import org.usfirst.frc.team157.robot.HomeSensor;
import org.usfirst.frc.team157.robot.RobotMap;
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
	// Used to differentiate between the Elevator and Forks in the homing and encoderController commands
	public enum ForkliftPart
	{
		ELEVATOR, FORKS
	}
	
	// FIXME: The values of the constants
	public static final double kFORKS_BOUNDRY_AB_TICKS = 100;
	public static final double kFORKS_BOUNDRY_BC_TICKS = 200;
	public static final double kFORKS_BOUNDRY_CD_TICKS = 300;
	
	public static final double kELEVATOR_BOUNDRY_AB_TICKS = 200;
	public static final double kELEVATOR_BOUNDRY_BC_TICKS = 400;
	public static final double kELEVATOR_BOUNDRY_CD_TICKS = 600;
	
	/*
	 * Use generic CANJaguar (not ScaledCANJaguar) as we don't actually care about the methods associated with the more-specific
	 * ScaledCANJaguar in this class. We only use the scaling factor when we instantiate the jaguar, which takes place in RobotMap
	 */
	public CANJaguar elevatorJag = RobotMap.elevatorJag;
	public CANJaguar forksJag = RobotMap.forksJag;
	
	// Create and Instantiate HomeSensors for the forks and elevator
	public HomeSensor forksHome = new HomeSensor(RobotMap.forkHomeMid, RobotMap.forkHomeEnd);
	public HomeSensor elevatorHome = new HomeSensor(RobotMap.elevatorHomeMid, RobotMap.elevatorHomeEnd);
	
	public AbsoluteEncoder forksEncoder;
	public AbsoluteEncoder elevatorEncoder;
	
	public void constructAppropriateAbsoluteEncoder(Forklift.ForkliftPart part)
	{
		if (part.equals(Forklift.ForkliftPart.ELEVATOR))
		{
			constructElevatorAbsoluteEncoder();
		}
		else if (part.equals(Forklift.ForkliftPart.FORKS))
		{
			constructForksAbsoluteEncoder();
		}
		else
		{
			System.out.println("constructAppropriateAbsoluteEncoder failed! Forklift part does not match...");
		}
	}
	
	private void constructForksAbsoluteEncoder()
	{
		forksEncoder = new AbsoluteEncoder(forksHome.getCurrentBoundry(), Forklift.ForkliftPart.FORKS);
	}
	
	private void constructElevatorAbsoluteEncoder()
	{
		elevatorEncoder = new AbsoluteEncoder(elevatorHome.getCurrentBoundry(), Forklift.ForkliftPart.ELEVATOR);
	}
	
	public HomeSensor.Zone getAppropriateZone(Forklift.ForkliftPart part)
	{
		if (part.equals(Forklift.ForkliftPart.ELEVATOR))
		{
			return getElevatorZone();
		}
		else if (part.equals(Forklift.ForkliftPart.FORKS))
		{
			return getForksZone();
		}
		System.out.println("Something has gone wrong! getAppropriateZone() in Forklift is returning null...");
		return null;
	}
	
	public void setAppropriateBoundry(HomeSensor.Boundry boundry, Forklift.ForkliftPart part)
	{
		if (part.equals(Forklift.ForkliftPart.ELEVATOR))
		{
			setElevatorBoundry(boundry);
		}
		else if (part.equals(Forklift.ForkliftPart.FORKS))
		{
			setForksBoundry(boundry);
		}
		else
		{
			System.out.println("Something has gone wrong! setAppropriateBoundry() in Forklift did not set a voltage...");
		}
	}
	
	public void setAppropriateVoltage(double voltage, Forklift.ForkliftPart part)
	{
		if (part.equals(Forklift.ForkliftPart.ELEVATOR))
		{
			setElevatorVoltage(voltage);
		}
		else if (part.equals(Forklift.ForkliftPart.FORKS))
		{
			setForksVoltage(voltage);
		}
		else
		{
			System.out.println("Something has gone wrong! setAppropriateVoltage() in Forklift did not set a voltage...");
		}
	}
	
	private HomeSensor.Zone getElevatorZone()
	{
		return elevatorHome.getZone();
	}
	
	private HomeSensor.Zone getForksZone()
	{
		return forksHome.getZone();
	}
	
	private void setElevatorBoundry(HomeSensor.Boundry boundry)
	{
		elevatorHome.setCurrentBoundry(boundry);
	}
	
	private void setElevatorVoltage(double voltage)
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
	
	private void setForksBoundry(HomeSensor.Boundry boundry)
	{
		forksHome.setCurrentBoundry(boundry);
	}
	
	private void setForksVoltage(double voltage)
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
	
	public static double getAppropriateOffset(HomeSensor.Boundry boundry, Forklift.ForkliftPart part)
	{
		double toReturn = -1;
		
		if (part.equals(Forklift.ForkliftPart.ELEVATOR))
		{
			if (boundry.equals(HomeSensor.Boundry.BOUNDRY_AB))
			{
				toReturn = kELEVATOR_BOUNDRY_AB_TICKS;
			}
			else if (boundry.equals(HomeSensor.Boundry.BOUNDRY_BC))
			{
				toReturn = kELEVATOR_BOUNDRY_BC_TICKS;
			}
			else if (boundry.equals(HomeSensor.Boundry.BOUNDRY_CD))
			{
				toReturn = kELEVATOR_BOUNDRY_CD_TICKS;
			}
			else
			{
				System.out.println("getAppropriateOffset failed! Could not determine boundry in elevator...");
			}
		}
		else if (part.equals(Forklift.ForkliftPart.FORKS))
		{
			if (boundry.equals(HomeSensor.Boundry.BOUNDRY_AB))
			{
				toReturn = kFORKS_BOUNDRY_AB_TICKS;
			}
			else if (boundry.equals(HomeSensor.Boundry.BOUNDRY_BC))
			{
				toReturn = kFORKS_BOUNDRY_BC_TICKS;
			}
			else if (boundry.equals(HomeSensor.Boundry.BOUNDRY_CD))
			{
				toReturn = kFORKS_BOUNDRY_CD_TICKS;
			}
			else
			{
				System.out.println("getAppropriateOffset failed! Could not determine boundry in forks...");
			}
		}
		else
		{
			System.out.println("getAppropriateOffset failed! Could not determine forklift part...");
		}
		
		return toReturn;
	}
	
	public double getElevatorPosition()
	{
		return elevatorJag.getPosition();
	}
	
	public double getForksPosition()
	{
		return forksJag.getPosition();
	}
	
	public double getAppropriatePosition(Forklift.ForkliftPart part)
	{
		if (part.equals(Forklift.ForkliftPart.ELEVATOR))
		{
			return getElevatorPosition();
		}
		else if (part.equals(Forklift.ForkliftPart.FORKS))
		{
			return getForksPosition();
		}
		else
		{
			System.out.println("Something has gone wrong! getAppropriatePosition() in Forklift did not return a position...");
		}
		return -2;
	}
}
