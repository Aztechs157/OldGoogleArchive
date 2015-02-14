
package org.usfirst.frc.team157.robot.subsystems;

import org.usfirst.frc.team157.robot.RobotMap;
import org.usfirst.frc.team157.robot.ScaledCANJaguar;
import edu.wpi.first.wpilibj.CANJaguar;

public class ForkliftForks extends ForkliftPart
{
	public double[] positions =
	{ 0.4, 0.6, 0.65, 0.8 }; // Tote Sideways, Tote Regular, Container Upright, Container Sideways
	
	public final double grabCurrentThreshold = 6;
	
	public ForkliftForks()
	{
		// Setup Jag and PID
		super.jag = RobotMap.forksJag;
		// super.setJagPID(PID[0], PID[1], PID[2]);
		
		super.highLimitSwitch = RobotMap.forksHighLimitSwitch;
		super.lowLimitSwitch = RobotMap.forksLowLimitSwitch;
		
		super.highEndVoltage = 0.195;
		super.lowEndVoltage = 0.46;
		
		setJagForVoltageControl();
		
		// calculatePositions();
	}
	
	public double getContainerSideWaysPosition()
	{
		return positions[3];
	}
	
	public double getContainerUprightPosition()
	{
		return positions[2];
	}
	
	public double getGrabCurrentThreshold()
	{
		return grabCurrentThreshold;
	}
	
	public double getToteNormalPosition()
	{
		return positions[1];
	}
	
	public double getToteSideWaysPosition()
	{
		return positions[0];
	}
	
	public void setJagForCurrentControl()
	{
		ScaledCANJaguar.setupJagForPositionControl(jag, CANJaguar.NeutralMode.Brake, 75, 1, 0);
	}
	
	public void setJagForPositionControl()
	{
		ScaledCANJaguar.setupJagForPositionControl(jag, CANJaguar.NeutralMode.Brake, 2000, 0.15, 0);
	}
	
	public void setJagForVoltageControl()
	{
		ScaledCANJaguar.setupJagForVoltageControl(jag, CANJaguar.NeutralMode.Brake);
	}
	
	private void calculatePositions()
	{
		// TODO set the positions array based on low and high end positions... Need to calculate equation
	}
	
	@Override
	protected void initDefaultCommand()
	{
		// Nothing
	}
}
