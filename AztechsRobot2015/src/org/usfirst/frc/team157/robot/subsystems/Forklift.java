
package org.usfirst.frc.team157.robot.subsystems;

import org.usfirst.frc.team157.robot.AbsoluteEncoder;
import org.usfirst.frc.team157.robot.HomeSensor;
import org.usfirst.frc.team157.robot.RobotMap;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/*
 * TODO
 * Flow of this class should be:
 * HomeSensor is constructed
 * HomeAPart Command is run for both the Elevator and Forks. This determines a boundary the parts lie in.
 * Absolute Potentiometer is constructed with the current boundary. Absolute location is determined with this.
 * Operator commands are then given as normal.
 */

/**
 * Forklift subsystem of the robot.
 *
 * @author Teju Nareddy
 */

public class Forklift extends Subsystem
{
	// Used to differentiate between the Elevator and Forks in the homing and encoderController commands
	public enum ForkliftPart
	{
		ELEVATOR, FORKS
	}
	
	// FIXME: The values of the constants
	public static final double kFORKS_BOUNDARY_AB_TICKS = 100;
	public static final double kFORKS_BOUNDARY_BC_TICKS = 200;
	public static final double kFORKS_BOUNDARY_CD_TICKS = 300;
	
	public static final double kELEVATOR_BOUNDARY_AB_TICKS = 200;
	public static final double kELEVATOR_BOUNDARY_BC_TICKS = 400;
	public static final double kELEVATOR_BOUNDARY_CD_TICKS = 600;
	
	private boolean isPositionSet = false; // Commands (other than HomeAPart) should not run if this is false
	
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
	
	public double getElevatorPosition()
	{
		return elevatorJag.getPosition();
	}
	
	public double getForksPosition()
	{
		return forksJag.getPosition();
	}
	
	public boolean isPositionSet()
	{
		return isPositionSet;
	}
	
	public void setAppropriateBoundary(HomeSensor.Boundary boundary, Forklift.ForkliftPart part)
	{
		if (part.equals(Forklift.ForkliftPart.ELEVATOR))
		{
			setElevatorBoundary(boundary);
		}
		else if (part.equals(Forklift.ForkliftPart.FORKS))
		{
			setForksBoundary(boundary);
		}
		else
		{
			System.out.println("Something has gone wrong! setAppropriateBoundary() in Forklift did not set a voltage...");
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
	
	public void setPositionIsSet()
	{
		isPositionSet = true;
	}
	
	private void constructElevatorAbsoluteEncoder()
	{
		elevatorEncoder = new AbsoluteEncoder(elevatorHome.getCurrentBoundary(), Forklift.ForkliftPart.ELEVATOR);
	}
	
	private void constructForksAbsoluteEncoder()
	{
		forksEncoder = new AbsoluteEncoder(forksHome.getCurrentBoundary(), Forklift.ForkliftPart.FORKS);
	}
	
	private HomeSensor.Zone getElevatorZone()
	{
		return elevatorHome.getZone();
	}
	
	private HomeSensor.Zone getForksZone()
	{
		return forksHome.getZone();
	}
	
	private void setElevatorBoundary(HomeSensor.Boundary boundary)
	{
		elevatorHome.setCurrentBoundary(boundary);
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
	
	private void setForksBoundary(HomeSensor.Boundary boundary)
	{
		forksHome.setCurrentBoundary(boundary);
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
	
	public static double getAppropriateOffset(HomeSensor.Boundary boundary, Forklift.ForkliftPart part)
	{
		double toReturn = -1;
		
		if (part.equals(Forklift.ForkliftPart.ELEVATOR))
		{
			if (boundary.equals(HomeSensor.Boundary.BOUNDARY_AB))
			{
				toReturn = kELEVATOR_BOUNDARY_AB_TICKS;
			}
			else if (boundary.equals(HomeSensor.Boundary.BOUNDARY_BC))
			{
				toReturn = kELEVATOR_BOUNDARY_BC_TICKS;
			}
			else if (boundary.equals(HomeSensor.Boundary.BOUNDARY_CD))
			{
				toReturn = kELEVATOR_BOUNDARY_CD_TICKS;
			}
			else
			{
				System.out.println("getAppropriateOffset failed! Could not determine boundary in elevator...");
			}
		}
		else if (part.equals(Forklift.ForkliftPart.FORKS))
		{
			if (boundary.equals(HomeSensor.Boundary.BOUNDARY_AB))
			{
				toReturn = kFORKS_BOUNDARY_AB_TICKS;
			}
			else if (boundary.equals(HomeSensor.Boundary.BOUNDARY_BC))
			{
				toReturn = kFORKS_BOUNDARY_BC_TICKS;
			}
			else if (boundary.equals(HomeSensor.Boundary.BOUNDARY_CD))
			{
				toReturn = kFORKS_BOUNDARY_CD_TICKS;
			}
			else
			{
				System.out.println("getAppropriateOffset failed! Could not determine boundary in forks...");
			}
		}
		else
		{
			System.out.println("getAppropriateOffset failed! Could not determine forklift part...");
		}
		
		return toReturn;
	}
}
