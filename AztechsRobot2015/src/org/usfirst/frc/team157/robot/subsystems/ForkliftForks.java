
package org.usfirst.frc.team157.robot.subsystems;

import org.usfirst.frc.team157.robot.RobotMap;
import org.usfirst.frc.team157.robot.ScaledCANJaguar;
import edu.wpi.first.wpilibj.CANJaguar;

public class ForkliftForks extends ForkliftPart
{
	public ForkliftForks()
	{
		super.jag = RobotMap.forksJag;
		
		super.highLimitSwitch = RobotMap.forksHighLimitSwitch;
		super.lowLimitSwitch = RobotMap.forksLowLimitSwitch;
		
		super.highEndVoltage = 0.195;
		super.lowEndVoltage = 0.46;
		
		setJagForVoltageControl();
	}
	
	@Override
	public void setJagForPositionControl()
	{
		ScaledCANJaguar.setupJagForPositionControl(jag, CANJaguar.NeutralMode.Brake, 2000, 0.15, 0);
	}
	
	@Override
	public void setJagForVoltageControl()
	{
		ScaledCANJaguar.setupJagForVoltageControl(jag, CANJaguar.NeutralMode.Brake);
	}
	
	@Override
	protected void initDefaultCommand()
	{
		// Nothing
	}
}
