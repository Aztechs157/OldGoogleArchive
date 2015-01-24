
package org.usfirst.frc.team157.robot.subsystems;

import org.usfirst.frc.team157.robot.RobotMap;
import org.usfirst.frc.team157.robot.commands.SetVoltage;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Motor extends Subsystem
{
	
	public CANJaguar jag = RobotMap.jag;
	
	public void initDefaultCommand()
	{
		setDefaultCommand(new SetVoltage());
	}
	
	public String motorStats()
	{
		String toReturn = "";
		toReturn += jag.getPosition() + "\t";
		toReturn += "c: " + jag.getOutputCurrent() + "\t";
		toReturn += jag.getDeviceID() + "\t";
		toReturn += jag.getControlMode() + "\t";
		toReturn += jag.getBusVoltage() + "\t";
		toReturn += jag.getTemperature() + "\t";
		toReturn += "v: " + jag.getOutputVoltage() + "\t";
		toReturn += jag.getFaults();
		return toReturn;
	}
	
	public void setVoltage(double voltage)
	{
		if (jag == null)
			System.out.println("Jag is null");
		jag.set(voltage);
	}
}
