
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
		if (jag != null)
		{
			return jag.get();
		}
		return 0;
	}
	
	public double getJagOutputVoltage()
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
	
	// FIXME enable limit switches
	public boolean isNearHighLimit()
	{
		if (Math.abs(highEndVoltage - getJagPosition()) < 0.01)
		{
			return true;
		}
		return false;
	}
	
	public boolean isNearLowLimit()
	{
		if (Math.abs(getJagPosition() - lowEndVoltage) < 0.01)
		{
			return true;
		}
		return false;
	}
	
	public void setHighEndEncoderLimit(double position)
	{
		highEndVoltage = position;
	}
	
	public boolean setJag(double setPoint)
	{
		if (jag != null)
		{
			/*
			 * if (setPoint > 0 && isHighLimitSwitchClosed())
			 * {
			 * jag.set(0);
			 * System.out.println("Hit high limit!");
			 * return false;
			 * }
			 * else if (setPoint < 0 && isLowLimitSwitchClosed())
			 * {
			 * jag.set(0);
			 * System.out.println("Hit low limit!");
			 * return false;
			 * }
			 * else
			 * {
			 */
			try
			{
				jag.set(setPoint);
				System.out.println(jag.get());
			}
			catch (Exception e)
			{
				System.out.println("============= EXCEPTION ==============");
			}
			return true;
			// }
		}
		System.out.println("Jag is null!");
		return false;
	}
	
	public void setJagScale(double scalingFactor)
	{
		if (jag != null)
		{
			jag.setScalingFactor(scalingFactor);
		}
		System.out.println("Jag is null!");
	}
	
	public void setLowEndEncoderLimit(double position)
	{
		lowEndVoltage = position;
	}
	
	public abstract void setJagForPositionControl();
	
	public abstract void setJagForVoltageControl();
}
