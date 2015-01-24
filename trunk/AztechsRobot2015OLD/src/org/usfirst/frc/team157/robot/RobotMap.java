
package org.usfirst.frc.team157.robot;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;

// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 *
 * @author Teju Nareddy
 */
public class RobotMap
{
	public static final boolean TEST_MODE = true;

	// Drive Subsystem
	public static ScaledCANJaguar driveLeftJag1;
	public static ScaledCANJaguar driveLeftJag2;
	public static ScaledCANJaguar driveRightJag1;
	public static ScaledCANJaguar driveRightJag2;

	// Forklift Subsystem
	public static ScaledCANJaguar forksJag;
	public static ScaledCANJaguar elevatorJag;
	public static DigitalLimitSwitch forkHomeMid;
	public static DigitalLimitSwitch forkHomeEnd;
	public static DigitalLimitSwitch elevatorHomeMid;
	public static DigitalLimitSwitch elevatorHomeEnd;

	public static BuiltInAccelerometer accelerometer;

	public static void init()
	{
		// Instantiate the jaguars
		if (!TEST_MODE)
		{
			driveLeftJag1 = new ScaledCANJaguar(2);
			driveLeftJag2 = new ScaledCANJaguar(4);
			driveRightJag1 = new ScaledCANJaguar(7);
			driveRightJag2 = new ScaledCANJaguar(10);
		}
		else
		{
			forksJag = new ScaledCANJaguar(6); // Test ID
			// elevatorJag = new ScaledCANJaguar(9); // Test ID
		}

		// Set the scaling factors
		if (!TEST_MODE)
		{
			driveLeftJag1.setScalingFactor(-1);
			driveLeftJag2.setScalingFactor(-1);
			driveRightJag1.setScalingFactor(1);
			driveRightJag2.setScalingFactor(1);
		}
		else
		{
			forksJag.setScalingFactor(1);
			// elevatorJag.setScalingFactor(1);
		}

		// Set proper jag control modes
		Robot.setupJagForPercentControl(driveLeftJag1);
		Robot.setupJagForPercentControl(driveLeftJag2);
		Robot.setupJagForPercentControl(driveRightJag1);
		Robot.setupJagForPercentControl(driveRightJag2);
		Robot.setupJagForVoltageControl(forksJag);
		Robot.setupJagForVoltageControl(elevatorJag);

		// Instantiate limit switches
		forkHomeMid = new DigitalLimitSwitch(1, true);
		forkHomeEnd = new DigitalLimitSwitch(2, true);
		elevatorHomeMid = new DigitalLimitSwitch(3, true);
		elevatorHomeMid = new DigitalLimitSwitch(4, true);

		// Instatiate Built In Accelerometer
		accelerometer = new BuiltInAccelerometer();
	}
}
