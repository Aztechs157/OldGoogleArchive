
package org.usfirst.frc.team157.robot.subsystems;

import org.usfirst.frc.team157.robot.DigitalLimitSwitch;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANJaguar;

public class Forklift
{
	public static final double ELEVATOR_DEADBAND = 0.01;
	
	public enum ForkliftPart
	{
		FORKS, ELEVATOR
	}
	
	/*
	 * Use generic CANJaguar (not ScaledCANJaguar) as we don't actually care about the methods associated with the more-specific
	 * ScaledCANJaguar in this class. We only use the scaling factor when we instantiate the jaguar, which takes place in RobotMap
	 */
	
	// Jaguars
	protected CANJaguar jag;
	
	// Limit Switches
	protected DigitalLimitSwitch highLimitSwitch;
	protected DigitalLimitSwitch lowLimitSwitch;
	
	// Potentiometers
	protected AnalogInput potentiometer;
	
	public double getPotentiometerPosition()
	{
		if (potentiometer != null)
		{
			return potentiometer.getVoltage();
		}
		System.out.print("Potentiometer is null! Don't know which part...");
		return 1;
	}
	
	// FIXME null check
	public boolean isHighLimitSwitchClosed()
	{
		return highLimitSwitch.get();
	}
	
	public boolean isLowLimitSwitchClosed()
	{
		return lowLimitSwitch.get();
	}
	
	public void setJagVoltage(double voltage)
	{
		if (jag != null)
		{
			jag.set(voltage);
		}
		else
		{
			System.out.println("Jag used in forklift is NULL! Don't know which part...");
		}
	}
}
