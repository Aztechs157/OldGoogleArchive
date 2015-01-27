
package org.usfirst.frc.team157.robot;

import org.usfirst.frc.team157.robot.HomeSensor.Zone;
import org.usfirst.frc.team157.robot.commands.AutonomousCommand;
import org.usfirst.frc.team157.robot.commands.HomeRobotPart;
import org.usfirst.frc.team157.robot.commands.HomeRobotPart.RobotPart;
import org.usfirst.frc.team157.robot.commands.OperatorDrive;
import org.usfirst.frc.team157.robot.commands.TestCommand;
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
	// Joysticks
	public Joystick leftJoystick;
	public Joystick rightJoystick;
	
	// Buttons
	private JoystickButton driverLeftButtonTrigger; // Button 1 - Trigger
	private JoystickButton driverLeftButton5;
	private JoystickButton driverLeftButton3;
	
	public OI()
	{
		// Instantiate Joysticks
		rightJoystick = new Joystick(1);
		leftJoystick = new Joystick(0);
		
		// Instantiate Buttons
		
		driverLeftButtonTrigger = new JoystickButton(leftJoystick, 1); // Button 1 - Trigger
		driverLeftButton3 = new JoystickButton(leftJoystick, 3); // Button 1 - Trigger
		driverLeftButton5 = new JoystickButton(leftJoystick, 5);
		
		// Tie commands to each button
		if (RobotMap.TEST_MODE)
		{
			driverLeftButton5.whenPressed(new HomeRobotPart(Zone.ZONE_B, RobotPart.Forks));
			driverLeftButton3.whenPressed(new HomeRobotPart(Zone.ZONE_B, RobotPart.Elevator));
		}
		driverLeftButtonTrigger.whenPressed(new TestCommand());
		
		// SmartDashboard Buttons
		SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
		SmartDashboard.putData("Operator Drive", new OperatorDrive());
	}
}
