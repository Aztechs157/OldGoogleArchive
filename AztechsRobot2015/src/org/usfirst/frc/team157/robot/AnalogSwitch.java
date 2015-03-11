
package org.usfirst.frc.team157.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class AnalogSwitch extends AnalogInput
{
	private final double[] voltages =
	{ 4.946, 3.914, 2.909, 1.934, 0.976, 0 };
	
	public AnalogSwitch(int channel)
	{
		super(channel);
	}
	
	public int getCase()
	{
		double current = getVoltage();
		if (Math.abs(current - voltages[0]) < 0.1)
		{
			return 1;
		}
		else if (Math.abs(current - voltages[1]) < 0.1)
		{
			return 2;
		}
		else if (Math.abs(current - voltages[2]) < 0.1)
		{
			return 3;
		}
		else if (Math.abs(current - voltages[3]) < 0.1)
		{
			return 4;
		}
		else if (Math.abs(current - voltages[4]) < 0.1)
		{
			return 5;
		}
		else if (Math.abs(current - voltages[5]) < 0.1)
		{
			return 6;
		}
		return 0;
	}
}
