
package org.usfirst.frc.team157.robot.subsystems;

import org.usfirst.frc.team157.robot.DigitalLimitSwitch;
import org.usfirst.frc.team157.robot.RobotMap;
import edu.wpi.first.wpilibj.AnalogInput;
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
	public static final double ELEVATOR_DEADBAND = 0.01;
	
	// Used to differentiate between the Elevator and Forks in the homing and encoderController commands
	public enum ForkliftPart
	{
		ELEVATOR, FORKS
	}
	
	// FIXME: The values of the constants
	public static double forksOpen = 0.25;
	public static double forksClosed = 4.5;
	
	public static double elevatorBottom = 0.25;
	public static double elevatorTop = 4.5;
	
	/*
	 * Use generic CANJaguar (not ScaledCANJaguar) as we don't actually care about the methods associated with the more-specific
	 * ScaledCANJaguar in this class. We only use the scaling factor when we instantiate the jaguar, which takes place in RobotMap
	 */
	
	// Jaguars
	private CANJaguar elevatorJag = RobotMap.elevatorJag;
	private CANJaguar forksJag = RobotMap.forksJag;
	
	// Limit Switches
	private DigitalLimitSwitch forksEndLimitSwitch = RobotMap.forksEndLimitSwitch;
	private DigitalLimitSwitch elevatorEndLimitSwitch = RobotMap.elevatorEndLimitSwitch;
	
	// Potentiometers
	private AnalogInput forksPotentiometer = RobotMap.forksPotentiometer;
	private AnalogInput elevatorPotentiometer = RobotMap.elevatorPotentiometer;
	
	/*
	 * public double getAppropriatePosition(Forklift.ForkliftPart part)
	 * {
	 * if (part.equals(Forklift.ForkliftPart.ELEVATOR))
	 * {
	 * return getElevatorPosition();
	 * }
	 * else if (part.equals(Forklift.ForkliftPart.FORKS))
	 * {
	 * return getForksPosition();
	 * }
	 * else
	 * {
	 * System.out.println("Something has gone wrong! getAppropriatePosition() in Forklift did not return a position...");
	 * }
	 * return -2;
	 * }
	 */
	
	public double getAppropriatePotentiometerPosition(Forklift.ForkliftPart part)
	{
		if (part.equals(Forklift.ForkliftPart.FORKS))
		{
			return getForksPotentiometerPosition();
		}
		else if (part.equals(Forklift.ForkliftPart.ELEVATOR))
		{
			return getElevatorPotentiometerPosition();
		}
		else
		{
			System.out.println("Something has gone wrong! "
					+ "getAppropriatePotentiometerPosition() in Forklift did return a position value...");
		}
		return 2.5;
	}
	
	public boolean isEndLimitSwitchClosed(Forklift.ForkliftPart part)
	{
		if (part.equals(Forklift.ForkliftPart.ELEVATOR))
		{
			return isElevatorEndLimitSwitchClosed();
		}
		else if (part.equals(Forklift.ForkliftPart.FORKS))
		{
			return isForksEndLimitSwitchClosed();
		}
		else
		{
			System.out.println("Something has gone wrong! "
					+ "isEndLimitSwitchClosed() in Forklift did return a limit switch state...");
		}
		return true;
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
	
	private double getElevatorPotentiometerPosition()
	{
		if (elevatorPotentiometer != null)
		{
			return elevatorPotentiometer.getVoltage();
		}
		System.out.println("elevatorPotentiometer is null!");
		return 2.5;
	}
	
	private double getForksPotentiometerPosition()
	{
		if (forksPotentiometer != null)
		{
			return forksPotentiometer.getVoltage();
		}
		System.out.println("forksPotentiometer is null!");
		return 2.5;
	}
	
	private boolean isElevatorEndLimitSwitchClosed()
	{
		return elevatorEndLimitSwitch.get();
	}
	
	/*
	 * private double getElevatorPosition()
	 * {
	 * return elevatorJag.getPosition();
	 * }
	 * 
	 * private double getForksPosition()
	 * {
	 * return forksJag.getPosition();
	 * }
	 */
	
	private boolean isForksEndLimitSwitchClosed()
	{
		return forksEndLimitSwitch.get();
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
