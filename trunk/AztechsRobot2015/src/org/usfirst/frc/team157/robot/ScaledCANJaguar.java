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
}
