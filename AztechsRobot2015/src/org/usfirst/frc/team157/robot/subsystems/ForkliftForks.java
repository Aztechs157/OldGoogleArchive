
package org.usfirst.frc.team157.robot.subsystems;

import org.usfirst.frc.team157.robot.RobotMap;

public class ForkliftForks extends Forklift
{
	public ForkliftForks()
	{
		super.jag = RobotMap.forksJag;
		super.potentiometer = RobotMap.forksPotentiometer;
		super.highLimitSwitch = RobotMap.forksHighLimitSwitch;
		super.lowLimitSwitch = RobotMap.forksLowLimitSwitch;
		super.setDeadband(0.01);
	}
}
