
package org.usfirst.frc.team157.robot.subsystems;

import org.usfirst.frc.team157.robot.DigitalLimitSwitch;
import org.usfirst.frc.team157.robot.Robot;
import org.usfirst.frc.team157.robot.ScaledCANJaguar;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * DO NOT INITALIZE
 * 
 * @author Teju Nareddy
 *
 */
public abstract class ForkliftPart extends Subsystem
{
	// Jaguar
	protected ScaledCANJaguar jag;
	
	// Limit Switches
	protected DigitalLimitSwitch highLimitSwitch;
	protected DigitalLimitSwitch lowLimitSwitch;
	
	// Rotary Encoder Positions
	protected double highEndVoltage;
	protected double lowEndVoltage;
	
	public double getJagPosition()
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
		return highEndVoltage;
	}
	
	public double getLowEndEncoderLimit()
	{
		return lowEndVoltage;
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
		if (highLimitSwitch != null)
		{
			return highLimitSwitch.get();
		}
		System.out.println("HighLimitSwitch is null!");
		return true;
	}
	
	public boolean isLowLimitSwitchClosed()
	{
		if (lowLimitSwitch != null)
		{
			return lowLimitSwitch.get();
		}
		System.out.println("LowLimitSwitch is null!");
		return true;
	}
	
	public double getJagVoltage()
	{
		if (jag != null)
		{
			return jag.getOutputVoltage();
		}
		return 0;
	}
	
	public double getJagCurrent()
	{
		if (jag != null)
		{
			return jag.getOutputCurrent();
		}
		System.out.println("Could not get jag current!");
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
