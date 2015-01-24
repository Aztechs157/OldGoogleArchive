
package org.usfirst.frc.team157.robot;

import edu.wpi.first.wpilibj.DigitalInput;

public class DigitalSwitch extends DigitalInput
{
	private boolean isReversed;

	public DigitalSwitch(int channel, boolean reversed)
	{
		super(channel);
		isReversed = reversed;
	}

	@Override
	public boolean get()
	{
		if (isReversed)
		{
			return !super.get();
		}
		return super.get();
	}

	public boolean getReversed()
	{
		return isReversed;
	}

	public void setReversed(boolean reversed)
	{
		isReversed = reversed;
	}
}
