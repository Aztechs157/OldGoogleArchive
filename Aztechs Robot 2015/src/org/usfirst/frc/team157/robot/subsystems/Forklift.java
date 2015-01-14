
package org.usfirst.frc.team157.robot.subsystems;

import org.usfirst.frc.team157.robot.RobotMap;
import org.usfirst.frc.team157.robot.ScaledCANJaguar;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Forklift extends Subsystem
{
	public ScaledCANJaguar elevtorJag = RobotMap.elevatorJag;
	public ScaledCANJaguar forksJag = RobotMap.forksJag;
	public DigitalInput forkHomeA = RobotMap.forkHomeA;
	public DigitalInput forkHomeB = RobotMap.forkHomeB;
	public DigitalInput elevatorHomeA = RobotMap.elevatorHomeA;
	public DigitalInput elevatorHomeB = RobotMap.elevatorHomeB;
	
	@Override
	protected void initDefaultCommand()
	{
		// setDefaultCommand(new GetSwitchStates());
	}
	
	public String GetLimitSwitchStates()
	{
		String toReturn = "";
		toReturn += "Switch 1: " + !forkHomeA.get() + ", ";
		toReturn += "Switch 2: " + !forkHomeB.get();
		return toReturn;
	}
	
	public void setForkVoltage(double voltage)
	{
		forksJag.set(voltage);
	}
}
