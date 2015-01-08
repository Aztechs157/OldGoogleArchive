/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc157.ControlBoardTest2014.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc157.ControlBoardTest2014.RobotMap;

/**
 *
 * @author Teju
 */
public class PneumaticSubsystem extends Subsystem
{
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	public Compressor compressor;
	public Solenoid pistonOut;
	public Solenoid pistonIn;
	
	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		if (compressor == null)
		{
			compressor = new Compressor(RobotMap.DIGITAL_PORT_PressureSwitch, RobotMap.RELAY_PORT_Compressor);
		}
		if (pistonOut == null)
		{
			pistonOut = new Solenoid(RobotMap.SOLENOID_PORT_Out);
		}
		if (pistonIn == null)
		{
			pistonIn = new Solenoid(RobotMap.SOLENOID_PORT_In);
		}
		
		// Retracts the piston
		System.out.println("******************* Retract Piston");
		retractPiston();
		
		compressor.start();
		
	}
	
	public void extendPiston()
	{
		pistonOut.set(false);
		pistonIn.set(true);
	}
	
	public void retractPiston()
	{
		pistonOut.set(true);
		pistonIn.set(false);
	}
}
