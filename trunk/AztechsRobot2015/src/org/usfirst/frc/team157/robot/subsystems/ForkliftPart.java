
package org.usfirst.frc.team157.robot.subsystems;

import org.usfirst.frc.team157.robot.DigitalLimitSwitch;
import org.usfirst.frc.team157.robot.ScaledCANJaguar;
import edu.wpi.first.wpilibj.CANJaguar;
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
	
	public double getJagOutputVoltage()
	{
		if (jag != null)
		{
			return jag.getOutputVoltage();
		}
		return 0;
	}
	
	@Deprecated
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
	
	public double getLowEndEncoderLimit()
	{
		return lowEndVoltage;
	}
	
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
	
	@Deprecated
	public boolean isNearHighLimit()
	{
		if (Math.abs(highEndVoltage - getJagPosition()) < 0.01)
		{
			return true;
		}
		return false;
	}
	
	@Deprecated
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
	
	public boolean setJagPosition(double position)
	{
		return setJag(position, CANJaguar.ControlMode.Position);
	}
	
	public void setJagScale(double scalingFactor)
	{
		if (jag != null)
		{
			jag.setScalingFactor(scalingFactor);
		}
		System.out.println("Jag is null!");
	}
	
	public boolean setJagVoltage(double voltage)
	{
		return setJag(voltage, CANJaguar.ControlMode.Voltage);
	}
	
	public void setLowEndEncoderLimit(double position)
	{
		lowEndVoltage = position;
	}
	
	protected void setAppropriateJagControlMode(CANJaguar.ControlMode mode)
	{
		if (mode.equals(CANJaguar.ControlMode.Position))
		{
			setJagForPositionControl();
		}
		else if (mode.equals(CANJaguar.ControlMode.Voltage))
		{
			setJagForVoltageControl();
		}
	}
	
	protected boolean setJag(double setPoint, CANJaguar.ControlMode mode)
	{
		if (jag != null)
		{
			if (!jag.getControlMode().equals(mode))
			{
				setAppropriateJagControlMode(mode);
			}
			try
			{
				jag.set(setPoint);
			}
			catch (Exception e)
			{
				System.out.println("============= EXCEPTION ==============");
			}
			return true;
			
		}
		System.out.println("Jag is null!");
		return false;
	}
	
	protected abstract void setJagForPositionControl();
	
	protected abstract void setJagForVoltageControl();
}
