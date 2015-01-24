
package org.usfirst.frc.team157.robot;

public class HomeSensor
{
	private DigitalSwitch mid;
	private DigitalSwitch end;
	
	public enum Zone
	{
		ZONE_A, ZONE_B, ZONE_C, ZONE_D
	}
	
	public HomeSensor(DigitalSwitch mid, DigitalSwitch end)
	{
		this.mid = mid;
		this.end = end;
	}
	
	public Zone getZone()
	{
		Zone toReturn;
		boolean midSwitch = mid.get();
		boolean endSwitch = end.get();
		
		if (endSwitch && !midSwitch)
		{
			toReturn = Zone.ZONE_A;
		}
		else if (endSwitch && midSwitch)
		{
			toReturn = Zone.ZONE_B;
		}
		else if (!endSwitch && midSwitch)
		{
			toReturn = Zone.ZONE_C;
		}
		else if (!endSwitch && !midSwitch)
		{
			toReturn = Zone.ZONE_D;
		}
		else
		{
			System.out.println("This is impossible");
			toReturn = Zone.ZONE_B;
		}
		
		return toReturn;
	}
}
