
package org.usfirst.frc.team157.robot;

/**
 * Class to detect what zone the elevator/rack is in. Instantiated using two DigitalLimitSwitches.
 *
 * @author Teju Nareddy
 *
 */

public class HomeSensor
{
	public enum Boundary
	{
		BOUNDARY_AB, BOUNDARY_BC, BOUNDARY_CD, UNKNOWN
	}
	
	public enum Zone
	{
		ZONE_A, ZONE_B, ZONE_C, ZONE_D
	}
	
	private DigitalLimitSwitch mid;
	private DigitalLimitSwitch end;
	private HomeSensor.Boundary currentBoundary = HomeSensor.Boundary.UNKNOWN;
	
	public HomeSensor(DigitalLimitSwitch mid, DigitalLimitSwitch end)
	{
		this.mid = mid;
		this.end = end;
	}
	
	public HomeSensor.Boundary getCurrentBoundary()
	{
		return currentBoundary;
	}
	
	public DigitalLimitSwitch getEndLimitSwitch()
	{
		return end;
	}
	
	public DigitalLimitSwitch getMidLimitSwitch()
	{
		return mid;
	}
	
	public HomeSensor.Zone getZone()
	{
		HomeSensor.Zone toReturn;
		boolean midSwitch = mid.get();
		boolean endSwitch = end.get();
		
		if (endSwitch && !midSwitch)
		{
			toReturn = HomeSensor.Zone.ZONE_A;
		}
		else if (endSwitch && midSwitch)
		{
			toReturn = HomeSensor.Zone.ZONE_B;
		}
		else if (!endSwitch && midSwitch)
		{
			toReturn = HomeSensor.Zone.ZONE_C;
		}
		else if (!endSwitch && !midSwitch)
		{
			toReturn = HomeSensor.Zone.ZONE_D;
		}
		else
		{
			System.out.println("This is impossible");
			toReturn = HomeSensor.Zone.ZONE_B;
		}
		
		return toReturn;
	}
	
	public void setCurrentBoundary(HomeSensor.Boundary currentBoundary)
	{
		this.currentBoundary = currentBoundary;
	}
	
	public static int zoneToNumber(HomeSensor.Zone z)
	{
		int toReturn = -1;
		if (z.equals(HomeSensor.Zone.ZONE_A))
		{
			toReturn = 1;
		}
		else if (z.equals(HomeSensor.Zone.ZONE_B))
		{
			toReturn = 2;
		}
		else if (z.equals(HomeSensor.Zone.ZONE_C))
		{
			toReturn = 3;
		}
		else if (z.equals(HomeSensor.Zone.ZONE_D))
		{
			toReturn = 4;
		}
		return toReturn;
	}
	
	public static HomeSensor.Zone findNearestZone(HomeSensor.Zone initZone)
	{
		if (initZone.equals(HomeSensor.Zone.ZONE_A))
		{
			return HomeSensor.Zone.ZONE_B;
		}
		else if (initZone.equals(HomeSensor.Zone.ZONE_B))
		{
			return HomeSensor.Zone.ZONE_A;
		}
		else if (initZone.equals(HomeSensor.Zone.ZONE_C))
		{
			return HomeSensor.Zone.ZONE_B;
		}
		else if (initZone.equals(HomeSensor.Zone.ZONE_D))
		{
			return HomeSensor.Zone.ZONE_C;
		}
		
		System.out.println("findNearestZone did not work! Could not determine what the initZone was...");
		return initZone;
	}
}
