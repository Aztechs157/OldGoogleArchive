
package org.usfirst.frc.team157.robot.subsystems;

import org.usfirst.frc.team157.robot.RobotMap;
import org.usfirst.frc.team157.robot.ScaledCANJaguar;
import org.usfirst.frc.team157.robot.commands.JoystickVoltageControlElevator;
import edu.wpi.first.wpilibj.CANJaguar;

public class ForkliftElevator extends ForkliftPart
{
	public double[] PID =
	{ -500, 0, 0 };
	
	public ForkliftElevator()
	{
		super.jag = RobotMap.elevatorJag;
		// super.setJagPID(PID[0], PID[1], PID[2]);
		
		super.highLimitSwitch = RobotMap.elevatorHighLimitSwitch;
		super.lowLimitSwitch = RobotMap.elevatorLowLimitSwitch;
		
		super.highEndVoltage = 0.29;
		super.lowEndVoltage = 0.88;
		
		setJagForVoltageControl();
	}
	
	public void setJagForPositionControl()
	{
		ScaledCANJaguar.setupJagForPositionControl(jag, CANJaguar.NeutralMode.Brake, -800, -0.05, 0);
	}
	
	public void setJagForVoltageControl()
	{
		ScaledCANJaguar.setupJagForVoltageControl(jag, CANJaguar.NeutralMode.Brake);
	}
	
	@Override
	protected void initDefaultCommand()
	{
		setDefaultCommand(new JoystickVoltageControlElevator());
	}
}
