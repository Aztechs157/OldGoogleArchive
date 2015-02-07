
package org.usfirst.frc.team157.robot.subsystems;

import org.usfirst.frc.team157.robot.RobotMap;

public class ForkliftElevator extends Forklift
{
	public ForkliftElevator()
	{
		super.jag = RobotMap.elevatorJag;
		super.encoder = RobotMap.elevatorRotaryEncoder;
		super.highLimitSwitch = RobotMap.elevatorHighLimitSwitch;
		super.lowLimitSwitch = RobotMap.elevatorLowLimitSwitch;
	}
	
	@Override
	protected void initDefaultCommand()
	{
		// TODO Auto-generated method stub
		
	}
}
