
package org.usfirst.frc.team157.robot.subsystems;

import org.usfirst.frc.team157.robot.DigitalLimitSwitch;
import org.usfirst.frc.team157.robot.ScaledCANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * CANNOT BE INITALIZED
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
	
	public double getHighEndEncoderLimit()
	{
		return highEndVoltage;
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
	
	public double getJagPosition()
	{
		if (jag != null)
		{
			return jag.getPosition();
		}
		System.out.println("Jag is null");
		return .5;
	}
	
	public double getJagSetpoint()
	{
		return jag.get();
	}
	
	public double getJagVoltage()
	{
		if (jag != null)
		{
			return jag.getOutputVoltage();
		}
		return 0;
	}
	
	public double getLowEndEncoderLimit()
	{
		return lowEndVoltage;
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
	
	public boolean isNearALimit()
	{
		return isNearHighLimit() || isNearLowLimit();
	}
	
	// FIXME enable limit switches
	public boolean isNearHighLimit()
	{
		if (Math.abs(highEndVoltage - getJagPosition()) < 0.01)
		{
			return true;
		}
		/*
		 * if (isHighLimitSwitchClosed() || highEndVoltage - getJagPosition() < 0.05)
		 * {
		 * return true;
		 * }
		 */
		return false;
	}
	
	public boolean isNearLowLimit()
	{
		if (Math.abs(getJagPosition() - lowEndVoltage) < 0.01)
		{
			return true;
		}
		/*
		 * if (isLowLimitSwitchClosed() || getJagPosition() - lowEndVoltage < 0.05)
		 * {
		 * return true;
		 * }
		 */
		return false;
	}
	
	public void setHighEndEncoderLimit(double position)
	{
		highEndVoltage = position;
	}
	
	public void setJag(double setPoint)
	{
		if (jag != null)
		{
			try
			{
				jag.set(setPoint);
			}
			catch (Exception e)
			{
				System.out.println("============= EXCEPTION ==============");
			}
		}
		else
		{
			System.out.println("Jag is null!");
		}
	}
	
	public void setJagScale(double scalingFactor)
	{
		jag.setScalingFactor(scalingFactor);
	}
	
	public void setLowEndEncoderLimit(double position)
	{
		lowEndVoltage = position;
	}
}
