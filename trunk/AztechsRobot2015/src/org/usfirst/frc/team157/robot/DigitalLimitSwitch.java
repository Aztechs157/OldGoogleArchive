
package org.usfirst.frc.team157.robot;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Implements a Digital Limit Switch by extending DigitalInput. Made this class because the wiring of most of the Limit Switches
 * the team has are wired incorrectly. This class allows the Limit Switches to be instantiated with a "reversed" variable, so that
 * the get() method returns the proper boolean value.
 *
 * @author Teju Nareddy
 *
 */

public class DigitalLimitSwitch extends DigitalInput
{
	private boolean isReversed;
	
	public DigitalLimitSwitch(int channel, boolean reversed)
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
