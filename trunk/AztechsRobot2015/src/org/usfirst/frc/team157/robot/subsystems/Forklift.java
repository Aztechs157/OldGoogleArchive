
package org.usfirst.frc.team157.robot.subsystems;

import org.usfirst.frc.team157.robot.DigitalLimitSwitch;
import org.usfirst.frc.team157.robot.Robot;
import org.usfirst.frc.team157.robot.RotaryEncoder;
import org.usfirst.frc.team157.robot.ScaledCANJaguar;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * DO NOT INITALIZE
 * 
 * @author Teju Nareddy
 *
 */
public abstract class Forklift extends Subsystem
{
	public enum ForkliftPart
	{
		FORKS, ELEVATOR
	}
	
	// Jaguar
	protected ScaledCANJaguar jag;
	
	// Limit Switches
	protected DigitalLimitSwitch highLimitSwitch;
	protected DigitalLimitSwitch lowLimitSwitch;
	
	// Rotary Enocder
	protected RotaryEncoder encoder;
	
	public double getRotaryEncoderPosition()
	{
		if (jag != null)
		{
			return jag.getPosition();
		}
		System.out.println("Jag is null");
		return .5;
	}
	
	public double getHighEndEncoderLimit()
	{
		return encoder.highEndVoltage;
	}
	
	public double getLowEndEncoderLimit()
	{
		return encoder.lowEndVoltage;
	}
	
	public void setJagPosition(double positionToSet)
	{
		if (jag != null)
		{
			Robot.setupJagForPositionControl(jag, CANJaguar.NeutralMode.Brake);
			jag.set(positionToSet);
		}
		
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
	
	public double getJagVoltage()
	{
		if (jag != null)
		{
			return jag.getOutputVoltage();
		}
		return 0;
	}
	
	public void setJagI(double value)
	{
		jag.setI(value);
	}
	
	public double getJagCurrent()
	{
		if (jag != null)
			return jag.getOutputCurrent();
		return 0;
	}
	
	/*
	 * public void setJagVoltage(double voltage)
	 * {
	 * if (jag != null)
	 * {
	 * Robot.setupJagForVoltageControl(jag, CANJaguar.NeutralMode.Brake);
	 * System.out.println("A + Mode: " + jag.getControlMode());
	 * jag.set(voltage);
	 * System.out.println("B");
	 * }
	 * else
	 * {
	 * System.out.println("Jag used in forklift is NULL! Don't know which part...");
	 * }
	 * }
	 */
}
