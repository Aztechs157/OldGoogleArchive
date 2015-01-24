
package org.usfirst.frc.team157.robot.subsystems;

import org.usfirst.frc.team157.robot.DigitalSwitch;
import org.usfirst.frc.team157.robot.RobotMap;
import org.usfirst.frc.team157.robot.ScaledCANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Forklift extends Subsystem
{
	public ScaledCANJaguar elevtorJag = RobotMap.elevatorJag;
	public ScaledCANJaguar forksJag = RobotMap.forksJag;
	public DigitalSwitch forkHomeA = RobotMap.forkHomeA;
	public DigitalSwitch forkHomeB = RobotMap.forkHomeB;
	public DigitalSwitch elevatorHomeA = RobotMap.elevatorHomeA;
	public DigitalSwitch elevatorHomeB = RobotMap.elevatorHomeB;
	
	@Override
	protected void initDefaultCommand()
	{
		// setDefaultCommand(new GetSwitchStates());
	}
	
	public String GetLimitSwitchStates()
	{
		String toReturn = "";
		toReturn += "Switch 1: " + forkHomeA.get() + ", ";
		toReturn += "Switch 2: " + forkHomeB.get();
		return toReturn;
	}
	
	public void setForkVoltage(double voltage)
	{
		if (RobotMap.TEST_MODE)
		{
			if (forksJag != null)
				forksJag.set(voltage);
			else
				System.out.println("Forklift Forks Jag is null!");
		}
	}
}
