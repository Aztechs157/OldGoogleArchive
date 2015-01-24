
package org.usfirst.frc.team157.robot;

import edu.wpi.first.wpilibj.CANJaguar;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{
	public static CANJaguar jag;
	
	public static void init()
	{
		jag = new CANJaguar(6);
		Robot.setupJagForVoltageControl(jag);
	}
}
