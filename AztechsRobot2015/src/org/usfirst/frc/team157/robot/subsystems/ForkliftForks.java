
package org.usfirst.frc.team157.robot.subsystems;

import org.usfirst.frc.team157.robot.RobotMap;

public class ForkliftForks extends Forklift
{
	public ForkliftForks()
	{
		super.jag = RobotMap.forksJag;
		super.encoder = RobotMap.forksRotaryEncoder;
		super.highLimitSwitch = RobotMap.forksHighLimitSwitch;
		super.lowLimitSwitch = RobotMap.forksLowLimitSwitch;
	}
	
	@Override
	protected void initDefaultCommand()
	{
		// TODO Auto-generated method stub
		
	}
}
