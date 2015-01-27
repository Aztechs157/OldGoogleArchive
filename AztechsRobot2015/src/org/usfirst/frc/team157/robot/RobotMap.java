
package org.usfirst.frc.team157.robot;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANJaguar;

/**
 *
 *
 * @author Teju Nareddy
 */
public class RobotMap
{
	// Joystick and Logitech Controller IDS
	public static final int LEFT_JOYSTICK_ID = 0;
	public static final int RIGHT_JOYSTICK_ID = 1;
	public static final int LOGITECH_CONTROLLER_ID = 2;
	
	// Hardware IDs
	private static final int DRIVE_LEFT_JAG1_ID = 2;
	private static final int DRIVE_LEFT_JAG2_ID = 4;
	private static final int DRIVE_RIGHT_JAG1_ID = 7;
	private static final int DRIVE_RIGHT_JAG2_ID = 10;
	
	private static final int FORKS_JAG_ID = 6; // Test ID
	private static final int ELEVATOR_JAG_ID = 9; // Test ID
	
	private static final int FORKS_HOME_MID_LIMSWITCH_ID = 1;
	private static final int FORKS_HOME_END_LIMSWITCH_ID = 2;
	private static final int ELEVATOR_HOME_MID_LIMSWITCH_ID = 3;
	private static final int ELEVATOR_HOME_END_LIMSWITCH_ID = 4;
	
	// Jag Scaling Values
	private static final int DRIVE_LEFT_JAG1_SCALE = -1;
	private static final int DRIVE_LEFT_JAG2_SCALE = -1;
	private static final int DRIVE_RIGHT_JAG1_SCALE = 1;
	private static final int DRIVE_RIGHT_JAG2_SCALE = 1;
	private static final int FORKS_JAG_SCALE = 1;
	private static final int ELEVATOR_JAG_SCALE = 1;
	
	// Limit Switches IsReversed variables -- If the wiring of the limit switch is backward, set this to true for the robot to
	// correctly determine if the limit switch is opened or closed
	private static final boolean FORKS_HOME_MID_LIMSWITCH_REVERSED = true;
	private static final boolean FORKS_HOME_END_LIMSWITCH_REVERSED = true;
	private static final boolean ELEVATOR_HOME_MID_LIMSWITCH_REVERSED = true;
	private static final boolean ELEVATOR_HOME_END_LIMSWITCH_REVERSED = true;
	
	// -----------------------------------------//
	// -----------------------------------------//
	
	// Drive Subsystem Drive Wheels
	public static ScaledCANJaguar driveLeftJag1;
	public static ScaledCANJaguar driveLeftJag2;
	public static ScaledCANJaguar driveRightJag1;
	public static ScaledCANJaguar driveRightJag2;
	
	// Forklift Subsystem Jaguars
	public static ScaledCANJaguar forksJag;
	public static ScaledCANJaguar elevatorJag;
	
	// Forklift Subsystem Limit Switches
	public static DigitalLimitSwitch forkHomeMid;
	public static DigitalLimitSwitch forkHomeEnd;
	public static DigitalLimitSwitch elevatorHomeMid;
	public static DigitalLimitSwitch elevatorHomeEnd;
	
	// FIXME What subsystem?
	// Built-In Accelerometer: Most likely part of the Drive Subsystem
	public static BuiltInAccelerometer accelerometer;
	
	public static void init()
	{
		// Instantiate all of the jaguars with the scaling factors
		if (!Robot.TEST_MODE)
		{
			try
			{
				driveLeftJag1 = new ScaledCANJaguar(DRIVE_LEFT_JAG1_ID, DRIVE_LEFT_JAG1_SCALE);
			}
			catch (NullPointerException e)
			{
				System.out.println("Left Drive Jag 1 was not created! Missing CAN ID #" + DRIVE_LEFT_JAG1_ID);
			}
			
			try
			{
				driveLeftJag2 = new ScaledCANJaguar(DRIVE_LEFT_JAG2_ID, DRIVE_LEFT_JAG2_SCALE);
			}
			catch (NullPointerException e)
			{
				System.out.println("Left Drive Jag 2 was not created! Missing CAN ID #" + DRIVE_LEFT_JAG2_ID);
			}
			
			try
			{
				driveRightJag1 = new ScaledCANJaguar(DRIVE_RIGHT_JAG1_ID, DRIVE_RIGHT_JAG1_SCALE);
			}
			catch (NullPointerException e)
			{
				System.out.println("Right Drive Jag 1 was not created! Missing CAN ID #" + DRIVE_RIGHT_JAG1_ID);
			}
			
			try
			{
				driveRightJag2 = new ScaledCANJaguar(DRIVE_RIGHT_JAG2_ID, DRIVE_RIGHT_JAG2_SCALE);
			}
			catch (NullPointerException e)
			{
				System.out.println("Right Drive Jag 2 was not created! Missing CAN ID #" + DRIVE_RIGHT_JAG2_ID);
			}
		}
		else
		{
			try
			{
				forksJag = new ScaledCANJaguar(FORKS_JAG_ID, FORKS_JAG_SCALE);
			}
			catch (NullPointerException e)
			{
				System.out.println("Forks Jag was not created! Missing CAN ID #" + FORKS_JAG_ID);
			}
			
			try
			{
				elevatorJag = new ScaledCANJaguar(ELEVATOR_JAG_ID, ELEVATOR_JAG_SCALE);
			}
			catch (NullPointerException e)
			{
				System.out.println("Elevator Jag was not created! Missing CAN ID #" + ELEVATOR_JAG_ID);
			}
		}
		
		// Instantiate limit switches with the IsReversed variable
		forkHomeMid = new DigitalLimitSwitch(FORKS_HOME_MID_LIMSWITCH_ID, FORKS_HOME_MID_LIMSWITCH_REVERSED);
		forkHomeEnd = new DigitalLimitSwitch(FORKS_HOME_END_LIMSWITCH_ID, FORKS_HOME_END_LIMSWITCH_REVERSED);
		elevatorHomeMid = new DigitalLimitSwitch(ELEVATOR_HOME_MID_LIMSWITCH_ID, ELEVATOR_HOME_MID_LIMSWITCH_REVERSED);
		elevatorHomeMid = new DigitalLimitSwitch(ELEVATOR_HOME_END_LIMSWITCH_ID, ELEVATOR_HOME_END_LIMSWITCH_REVERSED);
		
		// Instantiate Built In Accelerometer
		accelerometer = new BuiltInAccelerometer();
		
		// -----------------------------------------//
		// -----------------------------------------//
		
		// Set proper jag control modes
		Robot.setupJagForPercentControl(driveLeftJag1, CANJaguar.NeutralMode.Coast);
		Robot.setupJagForPercentControl(driveLeftJag2, CANJaguar.NeutralMode.Coast);
		Robot.setupJagForPercentControl(driveRightJag1, CANJaguar.NeutralMode.Coast);
		Robot.setupJagForPercentControl(driveRightJag2, CANJaguar.NeutralMode.Coast);
		Robot.setupJagForVoltageControl(forksJag, CANJaguar.NeutralMode.Brake);
		Robot.setupJagForVoltageControl(elevatorJag, CANJaguar.NeutralMode.Brake);
	}
}
