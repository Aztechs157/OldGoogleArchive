
package org.usfirst.frc.team157.robot.subsystems;

import org.usfirst.frc.team157.robot.DigitalLimitSwitch;
import org.usfirst.frc.team157.robot.ScaledCANJaguar;
import edu.wpi.first.wpilibj.AnalogInput;

public class Forklift
{
	public enum ForkliftPart
	{
		FORKS, ELEVATOR
	}
	
	private static double deadband;
	
	/*
	 * Use generic CANJaguar (not ScaledCANJaguar) as we don't actually care about the methods associated with the more-specific
	 * ScaledCANJaguar in this class. We only use the scaling factor when we instantiate the jaguar, which takes place in RobotMap
	 */
	protected ScaledCANJaguar jag;
	
	// Limit Switches
	protected DigitalLimitSwitch highLimitSwitch;
	protected DigitalLimitSwitch lowLimitSwitch;
	
	// Potentiometer
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
	
	public static double getDeadband()
	{
		return deadband;
	}
	
	public static void setDeadband(double deadband)
	{
		Forklift.deadband = deadband;
	}
}
