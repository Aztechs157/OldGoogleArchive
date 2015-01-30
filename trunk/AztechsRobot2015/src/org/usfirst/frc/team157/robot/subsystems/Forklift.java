
package org.usfirst.frc.team157.robot.subsystems;

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
	
	/*
	 * Use generic CANJaguar (not ScaledCANJaguar) as we don't actually care about the methods associated with the more-specific
	 * ScaledCANJaguar in this class. We only use the scaling factor when we instantiate the jaguar, which takes place in RobotMap
	 */
	public CANJaguar elevatorJag = RobotMap.elevatorJag;
	public CANJaguar forksJag = RobotMap.forksJag;
	
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
	
	public double getElevatorPosition()
	{
		return elevatorJag.getPosition();
	}
	
	public double getForksPosition()
	{
		return forksJag.getPosition();
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
}
