/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc.team157.robot;

import edu.wpi.first.wpilibj.CANJaguar;

/**
 *
 * @author MattKahn
 */
public class ScaledCANJaguar extends CANJaguar
{
	
	private double scalingFactor = 1;
	
	public ScaledCANJaguar(int deviceNumber)
	{
		super(deviceNumber);
	}
	
	public ScaledCANJaguar(int deviceNumber, double scale)
	{
		this(deviceNumber);
		scalingFactor = scale;
	}
	
	@Override
	public double get()
	{
		return super.get() / scalingFactor;
	}
	
	public double getScalingFactor()
	{
		return scalingFactor;
	}
	
	@Override
	public void set(double setPoint)
	{
		super.set(setPoint * scalingFactor);
	}
	
	@Override
	public void set(double setPoint, byte b)
	{
		super.set(setPoint * scalingFactor, b);
	}
	
	public void setScalingFactor(double scale)
	{
		scalingFactor = scale;
	}
	
	public static void setupJagForCurrentControl(CANJaguar jag, NeutralMode mode, double P, double I, double D)
	{
		if (jag != null)
		{
			jag.setCurrentMode(CANJaguar.kPotentiometer, P, I, D);
			// Forks -0.05, 0, 0
			jag.configNeutralMode(mode);
			jag.enableControl();
		}
	}
	
	public static void setupJagForPercentControl(CANJaguar jag, NeutralMode mode)
	{
		if (jag != null)
		{
			// jag.setPercentMode(CANJaguar.kQuadEncoder, 1);
			jag.setPercentMode();
			jag.configNeutralMode(mode);
			jag.enableControl();
			jag.setVoltageRampRate(0.02);
		}
	}
	
	public static void setupJagForPositionControl(CANJaguar jag, NeutralMode mode, double P, double I, double D)
	{
		if (jag != null)
		{
			jag.setPositionMode(CANJaguar.kPotentiometer, P, I, D);
			// Forks 1000, 0.05, 0
			// Elevator -500, 0, 0
			jag.configNeutralMode(mode);
			jag.enableControl();
		}
	}
	
	public static void setupJagForVoltageControl(CANJaguar jag, NeutralMode mode)
	{
		if (jag != null)
		{
			jag.setVoltageMode(CANJaguar.kPotentiometer);
			jag.configNeutralMode(mode);
			jag.setVoltageRampRate(0.02); // 0.02
			jag.enableControl();
		}
	}
}
