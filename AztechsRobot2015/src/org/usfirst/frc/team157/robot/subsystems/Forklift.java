
package org.usfirst.frc.team157.robot.subsystems;

import org.usfirst.frc.team157.robot.HomeSensor;
import org.usfirst.frc.team157.robot.RobotMap;
import org.usfirst.frc.team157.robot.ScaledCANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Forklift subsystem of the robot.
 *
 * @author Teju Nareddy
 *
 */

public class Forklift extends Subsystem
{
	public ScaledCANJaguar elevtorJag = RobotMap.elevatorJag;
	public ScaledCANJaguar forksJag = RobotMap.forksJag;
	public HomeSensor forksHome = new HomeSensor(RobotMap.forkHomeMid, RobotMap.forkHomeEnd);
	public HomeSensor elevatorHome = new HomeSensor(RobotMap.elevatorHomeMid, RobotMap.elevatorHomeEnd);

	public void setForkVoltage(double voltage)
	{
		if (RobotMap.TEST_MODE)
		{
			if (forksJag != null)
			{
				forksJag.set(voltage);
			}
			else
			{
				System.out.println("Forklift Forks Jag is null!");
			}
		}
	}

	/*
	 * public String GetLimitSwitchStates()
	 * {
	 * String toReturn = "";
	 * toReturn += "Switch 1: " + forkHomeA.get() + ", ";
	 * toReturn += "Switch 2: " + forkHomeB.get();
	 * return toReturn;
	 * }
	 */

	@Override
	protected void initDefaultCommand()
	{
		// setDefaultCommand(new GetSwitchStates());
	}
}
