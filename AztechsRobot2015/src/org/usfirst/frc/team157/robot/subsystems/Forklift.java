
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
	
	public final double ELEVATOR_LOWER_LIMIT = 1.313; // volts; at revolution 3
	public final double ELEVATOR_UPPER_LIMIT = 2.375; // volts; at revolution 6
	
	public final double FORKS_LOWER_LIMIT = 1.313; // volts; at revolution 3
	public final double FORKS_UPPER_LIMIT = 2.375; // volts; at revolution 6
	
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
	
	
	/**
	 * Gets the Potentiometer Position for a particular Forklift Part
	 * 
	 * @param part The Forklift Part to operate on, either FORKS or ELEVATOR
	 * @return The value of Potentiometer Position as a voltage
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
			System.out.println("BUGCHECK: Invalid Forklift Part");
		}
		return 2.5; // midpoint
	}
	
	public void moveTowardHigherPotentiometerVoltage(Forklift.ForkliftPart part)
	{
		if (part.equals(Forklift.ForkliftPart.ELEVATOR))
		{
			if ( getElevatorPotentiometerPosition() >= ELEVATOR_UPPER_LIMIT )
			{
				setElevatorVoltage(0.0);	//stop!
				System.out.println("Elevator above UPPER Limit");
			}
			else
			{
				setElevatorVoltage(-12.0); 
			}
			return;
		}
		else if (part.equals(Forklift.ForkliftPart.FORKS))
		{
			if ( getForksPotentiometerPosition() >= FORKS_UPPER_LIMIT )
			{
				setForksVoltage(0.0);	//stop!
				System.out.println("Forks above UPPER Limit");
			}
			else
			{
				setForksVoltage(-12.0); 
			}
			return;
		}
		else
		{
			System.out.println("BUGCHECK: Invalid Forklift Part");
		}
		return;
	}

	public void moveTowardLowerPotentiometerVoltage(Forklift.ForkliftPart part)
	{
		if (part.equals(Forklift.ForkliftPart.ELEVATOR))
		{
			if ( getElevatorPotentiometerPosition() <= ELEVATOR_LOWER_LIMIT )
			{
				setElevatorVoltage(0.0);	//stop!
				System.out.println("Elevator at LOWER Limit");
			}
			else
			{
				setElevatorVoltage(+12.0); 
			}
			return;
		}
		else if (part.equals(Forklift.ForkliftPart.FORKS))
		{
			if ( getForksPotentiometerPosition() <= FORKS_LOWER_LIMIT )
			{
				setForksVoltage(0.0);	//stop!
				System.out.println("Forks at LOWER Limit");
			}
			else
			{
				setForksVoltage(+12.0); 
			}
			return;
		}
		else
		{
			System.out.println("BUGCHECK: Invalid Forklift Part");
		}
		return;
	}
	
	public void stopMotor(Forklift.ForkliftPart part)
	{
		if (part.equals(Forklift.ForkliftPart.ELEVATOR))
		{
			setElevatorVoltage(0.0);
		}
		else if (part.equals(Forklift.ForkliftPart.FORKS))
		{
			setForksVoltage(0.0);
		}
		else
		{
			System.out.println("BUGCHECK: Invalid Forklift Part");
		}
		return;
	}

//	public void setAppropriateMotorVoltage(double voltage, Forklift.ForkliftPart part)
//	{
//		if (part.equals(Forklift.ForkliftPart.ELEVATOR))
//		{
//			setElevatorVoltage(voltage);
//		}
//		else if (part.equals(Forklift.ForkliftPart.FORKS))
//		{
//			setForksVoltage(voltage);
//		}
//		else
//		{
//			System.out.println("BUGCHECK: Invalid Forklift Part");
//		}
//		return;
//	}
	
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
