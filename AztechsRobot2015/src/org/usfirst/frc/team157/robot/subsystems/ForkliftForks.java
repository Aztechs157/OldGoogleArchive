
package org.usfirst.frc.team157.robot.subsystems;

import org.usfirst.frc.team157.robot.RobotMap;

public class ForkliftForks extends ForkliftPart
{
	public double[] positions =
	{ 0.4, 0.6, 0.65, 0.8 }; // Tote Sideways, Tote Regular, Container Upright, Container Sideways
	
	public ForkliftForks()
	{
		super.jag = RobotMap.forksJag;
		super.highLimitSwitch = RobotMap.forksHighLimitSwitch;
		super.lowLimitSwitch = RobotMap.forksLowLimitSwitch;
		
		super.highEndVoltage = 0.8;
		super.lowEndVoltage = 0.1;
		
		calculatePositions();
	}
	
	@Override
	protected void initDefaultCommand()
	{
		// TODO Auto-generated method stub
		
	}
	
	private void calculatePositions()
	{
		// TODO set the positions array based on low and high end positions... Need to calculate equation
	}
	
	public double getToteSideWaysPosition()
	{
		return positions[0];
	}
	
	public double getToteNormalPosition()
	{
		return positions[1];
	}
	
	public double getContainerUprightPosition()
	{
		return positions[2];
	}
	
	public double getContainerSideWaysPosition()
	{
		return positions[3];
	}
}
