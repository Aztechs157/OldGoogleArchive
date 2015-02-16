
package org.usfirst.frc.team157.robot.subsystems;

import org.usfirst.frc.team157.robot.RobotMap;
import org.usfirst.frc.team157.robot.ScaledCANJaguar;
import org.usfirst.frc.team157.robot.commands.JoystickVoltageControlElevator;
import edu.wpi.first.wpilibj.CANJaguar;

public class ForkliftElevator extends ForkliftPart
{
	public ForkliftElevator()
	{
		super.jag = RobotMap.elevatorJag;
		
		super.highLimitSwitch = RobotMap.elevatorHighLimitSwitch;
		super.lowLimitSwitch = RobotMap.elevatorLowLimitSwitch;
		
		// FIXME
		super.highEndVoltage = 0.29;
		super.lowEndVoltage = 0.88;
		
		setJagForVoltageControl();
	}
	
	@Deprecated
	@Override
	public void setJagForPositionControl()
	{
		// FIXME
		ScaledCANJaguar.setupJagForPositionControl(jag, CANJaguar.NeutralMode.Brake, -800, -0.05, 0);
	}
	
	@Override
	public void setJagForVoltageControl()
	{
		ScaledCANJaguar.setupJagForVoltageControl(jag, CANJaguar.NeutralMode.Brake);
	}
	
	@Override
	protected void initDefaultCommand()
	{
		setDefaultCommand(new JoystickVoltageControlElevator());
	}
	
	/*
	 * @Override
	 * public boolean setJagVoltage(double voltage)
	 * {
	 * if (voltage < 0 && this.isLowLimitSwitchClosed())
	 * {
	 * return false;
	 * }
	 * else if (voltage > 0 && this.isHighLimitSwitchClosed())
	 * {
	 * return false;
	 * }
	 * return super.setJagVoltage(voltage);
	 * }
	 */
}
