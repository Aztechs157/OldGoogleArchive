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
		// System.out.println("S");
		super.set(setPoint * scalingFactor);
	}

	@Override
	public void set(double setPoint, byte b)
	{
		// System.out.println("S..");
		super.set(setPoint * scalingFactor, b);
	}

	public void setScalingFactor(double scale)
	{
		scalingFactor = scale;
		System.out.println("Scaling factor: " + scale);
	}
}
