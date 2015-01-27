
package org.usfirst.frc.team157.robot;

import org.usfirst.frc.team157.robot.HomeSensor.Zone;
import org.usfirst.frc.team157.robot.commands.AutonomousCommand;
import org.usfirst.frc.team157.robot.commands.DebugPrint;
import org.usfirst.frc.team157.robot.commands.HomeRobotPart;
import org.usfirst.frc.team157.robot.commands.HomeRobotPart.RobotPart;
import org.usfirst.frc.team157.robot.commands.PrintData;
import org.usfirst.frc.team157.robot.commands.SwitchDriverType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 *
 * @author Teju Nareddy
 */
public class OI
{
	// Used to determine the main drive controller --> See OperatorDrive command
	public enum DriverType
	{
		Joysticks, LogitechController
	}
	
	// Default driver type is joysticks
	private DriverType driverType = DriverType.Joysticks;
	
	// -----------------------------------------//
	// -----------------------------------------//
	
	// Define all joysticks, logitech controllers, and buttons
	public Joystick driverLeft;
	private JoystickButton driverRightButtonTrigger; // Button 1 - Trigger
	private JoystickButton driverRightButton2; // Button 2 - Stick down
	private JoystickButton driverRightButton3; // Button 3 - Stick center
	private JoystickButton driverRightButton4; // Button 4 - Stick left
	private JoystickButton driverRightButton5; // Button 5 - Stick right
	private JoystickButton driverRightButton6; // Button 6 - Base Left Away
	private JoystickButton driverRightButton7; // Button 7 - Base Left Close
	private JoystickButton driverRightButton8; // Button 8 - Base Near Left
	private JoystickButton driverRightButton9; // Button 9 - Base Near Right
	private JoystickButton driverRightButton10; // Button 10 - Base Right Close
	private JoystickButton driverRightButton11; // Button 11 - Base RIght Away
	
	public Joystick driverRight;
	private JoystickButton driverLeftButtonTrigger; // Button 1 - Trigger
	private JoystickButton driverLeftButton2; // Button 2 - Stick down
	private JoystickButton driverLeftButton3; // Button 3 - Stick center
	private JoystickButton driverLeftButton4; // Button 4 - Stick left
	private JoystickButton driverLeftButton5; // Button 5 - Stick right
	private JoystickButton driverLeftButton6; // Button 6 - Base Left Away
	private JoystickButton driverLeftButton7; // Button 7 - Base Left Close
	private JoystickButton driverLeftButton8; // Button 8 - Base Near Left
	private JoystickButton driverLeftButton9; // Button 9 - Base Near Right
	private JoystickButton driverLeftButton10; // Button 10 - Base Right Close
	private JoystickButton driverLeftButton11; // Button 11 - Base RIght Away
	
	public LogitechController logitechDriver;
	private LogitechControllerButton logitechDriverButtonLeftB; // Left Button (Above Trigger)
	private LogitechControllerButton logitechDriverButtonRightB; // Right Button (Above Trigger)
	private LogitechControllerButton logitechDriverButtonLeftTop; // Left Stick Pressing
	private LogitechControllerButton logitechDriverButtonRightTop; // Right Stick Pressing
	private LogitechControllerButton logitechDriverButtonA; // A Button (Green)
	private LogitechControllerButton logitechDriverButtonB; // B Button (Red)
	private LogitechControllerButton logitechDriverButtonX; // X Button (Blue)
	private LogitechControllerButton logitechDriverButtonY; // Y Button (Yellow)
	private LogitechControllerButton logitechDriverButtonStart; // Start Button
	private LogitechControllerButton logitechDriverButtonBack; // Back Button
	private LogitechControllerButton logitechDriverButtonLeftTrigger; // Left Trigger (Used as button)
	private LogitechControllerButton logitechDriverButtonRightTrigger; // Right Trigger (Used as button)
	private LogitechControllerButton logitechDriverButtonGameUp; // Game Pad Up
	private LogitechControllerButton logitechDriverButtonGameDown; // Game Pad Down
	private LogitechControllerButton logitechDriverButtonGameLeft; // Game Pad Left
	private LogitechControllerButton logitechDriverButtonGameRight; // Game Pad Right
	
	public OI()
	{
		// Instantiate Joysticks and Logitech Controllers
		driverLeft = new Joystick(RobotMap.LEFT_JOYSTICK_ID);
		driverRight = new Joystick(RobotMap.RIGHT_JOYSTICK_ID);
		logitechDriver = new LogitechController(RobotMap.LOGITECH_CONTROLLER_ID);
		
		// -----------------------------------------//
		// -----------------------------------------//
		
		// Instantiate all of the buttons on the 2 joysticks and the logitech controller
		driverLeftButtonTrigger = new JoystickButton(driverLeft, 1);
		driverLeftButton2 = new JoystickButton(driverLeft, 2);
		driverLeftButton3 = new JoystickButton(driverLeft, 3);
		driverLeftButton4 = new JoystickButton(driverLeft, 4);
		driverLeftButton5 = new JoystickButton(driverLeft, 5);
		driverLeftButton6 = new JoystickButton(driverLeft, 6);
		driverLeftButton7 = new JoystickButton(driverLeft, 7);
		driverLeftButton8 = new JoystickButton(driverLeft, 8);
		driverLeftButton9 = new JoystickButton(driverLeft, 9);
		driverLeftButton10 = new JoystickButton(driverLeft, 10);
		driverLeftButton11 = new JoystickButton(driverLeft, 11);
		
		driverRightButtonTrigger = new JoystickButton(driverRight, 1);
		driverRightButton2 = new JoystickButton(driverRight, 2);
		driverRightButton3 = new JoystickButton(driverRight, 3);
		driverRightButton4 = new JoystickButton(driverRight, 4);
		driverRightButton5 = new JoystickButton(driverRight, 5);
		driverRightButton6 = new JoystickButton(driverRight, 6);
		driverRightButton7 = new JoystickButton(driverRight, 7);
		driverRightButton8 = new JoystickButton(driverRight, 8);
		driverRightButton9 = new JoystickButton(driverRight, 9);
		driverRightButton10 = new JoystickButton(driverRight, 10);
		driverRightButton11 = new JoystickButton(driverRight, 11);
		
		logitechDriverButtonLeftB = new LogitechControllerButton(logitechDriver, LogitechController.ButtonType.kButtonLeftB.value);
		logitechDriverButtonRightB = new LogitechControllerButton(logitechDriver,
				LogitechController.ButtonType.kButtonRightB.value);
		logitechDriverButtonLeftTop = new LogitechControllerButton(logitechDriver,
				LogitechController.ButtonType.kButtonLeftTop.value);
		logitechDriverButtonRightTop = new LogitechControllerButton(logitechDriver,
				LogitechController.ButtonType.kButtonRightTop.value);
		logitechDriverButtonA = new LogitechControllerButton(logitechDriver, LogitechController.ButtonType.kButtonA.value);
		logitechDriverButtonB = new LogitechControllerButton(logitechDriver, LogitechController.ButtonType.kButtonB.value);
		logitechDriverButtonX = new LogitechControllerButton(logitechDriver, LogitechController.ButtonType.kButtonX.value);
		logitechDriverButtonY = new LogitechControllerButton(logitechDriver, LogitechController.ButtonType.kButtonY.value);
		logitechDriverButtonStart = new LogitechControllerButton(logitechDriver, LogitechController.ButtonType.kButtonStart.value);
		logitechDriverButtonBack = new LogitechControllerButton(logitechDriver, LogitechController.ButtonType.kButtonBack.value);
		logitechDriverButtonLeftTrigger = new LogitechControllerButton(logitechDriver,
				LogitechController.ButtonType.kButtonLeftTrigger.value);
		logitechDriverButtonRightTrigger = new LogitechControllerButton(logitechDriver,
				LogitechController.ButtonType.kButtonRightTrigger.value);
		logitechDriverButtonGameUp = new LogitechControllerButton(logitechDriver,
				LogitechController.ButtonType.kButtonGameUp.value);
		logitechDriverButtonGameDown = new LogitechControllerButton(logitechDriver,
				LogitechController.ButtonType.kButtonGameDown.value);
		logitechDriverButtonGameLeft = new LogitechControllerButton(logitechDriver,
				LogitechController.ButtonType.kButtonGameLeft.value);
		logitechDriverButtonGameRight = new LogitechControllerButton(logitechDriver,
				LogitechController.ButtonType.kButtonGameRight.value);
		
		// -----------------------------------------//
		// -----------------------------------------//
		
		// Tie DebugPrint commands to each button: Every time a button is pressed, it will print out
		driverLeftButtonTrigger.whenPressed(new DebugPrint("driverLeftButtonTrigger Pressed"));
		driverLeftButton2.whenPressed(new DebugPrint("driverLeftButton2 Pressed"));
		driverLeftButton3.whenPressed(new DebugPrint("driverLeftButton3 Pressed"));
		driverLeftButton4.whenPressed(new DebugPrint("driverLeftButton4 Pressed"));
		driverLeftButton5.whenPressed(new DebugPrint("driverLeftButton5 Pressed"));
		driverLeftButton6.whenPressed(new DebugPrint("driverLeftButton6 Pressed"));
		driverLeftButton7.whenPressed(new DebugPrint("driverLeftButton7 Pressed"));
		driverLeftButton8.whenPressed(new DebugPrint("driverLeftButton8 Pressed"));
		driverLeftButton9.whenPressed(new DebugPrint("driverLeftButton9 Pressed"));
		driverLeftButton10.whenPressed(new DebugPrint("driverLeftButton10 Pressed"));
		driverLeftButton11.whenPressed(new DebugPrint("driverLeftButton11 Pressed"));
		
		driverRightButtonTrigger.whenPressed(new DebugPrint("driverRightButtonTrigger Pressed"));
		driverRightButton2.whenPressed(new DebugPrint("driverRightButton2 Pressed"));
		driverRightButton3.whenPressed(new DebugPrint("driverRightButton3 Pressed"));
		driverRightButton3.whenPressed(new DebugPrint("driverRightButton3 Pressed"));
		driverRightButton4.whenPressed(new DebugPrint("driverRightButton4 Pressed"));
		driverRightButton5.whenPressed(new DebugPrint("driverRightButton5 Pressed"));
		driverRightButton6.whenPressed(new DebugPrint("driverRightButton6 Pressed"));
		driverRightButton7.whenPressed(new DebugPrint("driverRightButton7 Pressed"));
		driverRightButton8.whenPressed(new DebugPrint("driverRightButton8 Pressed"));
		driverRightButton9.whenPressed(new DebugPrint("driverRightButton9 Pressed"));
		driverRightButton10.whenPressed(new DebugPrint("driverRightButton10 Pressed"));
		driverRightButton11.whenPressed(new DebugPrint("driverRightButton11 Pressed"));
		
		logitechDriverButtonLeftB.whenPressed(new DebugPrint("logitechDriverButtonLeftB Pressed"));
		logitechDriverButtonRightB.whenPressed(new DebugPrint("logitechDriverButtonRightB Pressed"));
		logitechDriverButtonLeftTop.whenPressed(new DebugPrint("logitechDriverButtonLeftTop Pressed"));
		logitechDriverButtonRightTop.whenPressed(new DebugPrint("logitechDriverButtonRightTop Pressed"));
		logitechDriverButtonA.whenPressed(new DebugPrint("logitechDriverButtonA Pressed"));
		logitechDriverButtonB.whenPressed(new DebugPrint("logitechDriverButtonB Pressed"));
		logitechDriverButtonX.whenPressed(new DebugPrint("logitechDriverButtonX Pressed"));
		logitechDriverButtonY.whenPressed(new DebugPrint("logitechDriverButtonY Pressed"));
		logitechDriverButtonStart.whenPressed(new DebugPrint("logitechDriverButtonStart Pressed"));
		logitechDriverButtonBack.whenPressed(new DebugPrint("logitechDriverButtonBack Pressed"));
		logitechDriverButtonLeftTrigger.whenPressed(new DebugPrint("logitechDriverButtonLeftTrigger Pressed"));
		logitechDriverButtonRightTrigger.whenPressed(new DebugPrint("logitechDriverButtonRightTrigger Pressed"));
		logitechDriverButtonGameUp.whenPressed(new DebugPrint("logitechDriverButtonGameUp Pressed"));
		logitechDriverButtonGameDown.whenPressed(new DebugPrint("logitechDriverButtonGameDown Pressed"));
		logitechDriverButtonGameLeft.whenPressed(new DebugPrint("logitechDriverButtonGameLeft Pressed"));
		logitechDriverButtonGameRight.whenPressed(new DebugPrint("logitechDriverButtonGameRight Pressed"));
		
		// -----------------------------------------//
		// -----------------------------------------//
		
		// Actual commands that we want each button to have
		// FIXME
		if (Robot.TEST_MODE)
		{
			driverLeftButton3.whenPressed(new HomeRobotPart(Zone.ZONE_B, RobotPart.Elevator));
			driverLeftButton4.whenPressed(new HomeRobotPart(Zone.ZONE_B, RobotPart.Forks));
		}
		driverLeftButtonTrigger.whenPressed(new PrintData());
		driverLeftButton2.whenPressed(new SwitchDriverType());
		
		// -----------------------------------------//
		// -----------------------------------------//
		
		// TODO
		// SmartDashboard Buttons
		SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
		SmartDashboard.putData("Test Command", new PrintData());
	}
	
	public DriverType getDriverType()
	{
		return driverType;
	}
	
	public void setJoystickDriveMode()
	{
		driverType = DriverType.Joysticks;
	}
	
	public void setLogitechDriveMode()
	{
		driverType = DriverType.LogitechController;
	}
}
