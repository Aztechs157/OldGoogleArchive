
package org.usfirst.frc.team157.robot.subsystems;

import org.usfirst.frc.team157.robot.RobotMap;

public class ForkliftElevator extends ForkliftPart
{
	public double[] PID =
	{ -500, 0, 0 };
	
	public ForkliftElevator()
	{
		super.jag = RobotMap.elevatorJag;
		super.setJagPID(PID[0], PID[1], PID[2]);
		
		super.highLimitSwitch = RobotMap.elevatorHighLimitSwitch;
		super.lowLimitSwitch = RobotMap.elevatorLowLimitSwitch;
		
		super.highEndVoltage = 0.8;
		super.lowEndVoltage = 0.1;
	}
	
	@Override
	protected void initDefaultCommand()
	{
		// TODO Auto-generated method stub
		
	}
}
