
package org.usfirst.frc.team157.robot;

/*
 * TODO
 * 
 * Add AbsoluteEncoder
 * Update Test_MODE based on the hardware on the robot
 * "Robots Don't Quit" --> Allow robot to operate even if hardware components disconnect during match
 * Add Javadoc and Comments for next year's programmers
 * Check and Fix Motors ???
 * Drive Forward for time -- Can also use this to check if the robot is driving straight
 * For enumerations: Choose between imports or specific references
 */

import org.usfirst.frc.team157.robot.commands.AutonomousCommand;
import org.usfirst.frc.team157.robot.subsystems.Drive;
import org.usfirst.frc.team157.robot.subsystems.ForkliftElevator;
import org.usfirst.frc.team157.robot.subsystems.ForkliftForks;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 *
 * @author Teju Nareddy
 */
public class Robot extends IterativeRobot
{
	public CameraServer camera;
	
	// -----------------------------------------//
	// -----------------------------------------//
	
	// Create variables
	public static Command autonomousCommand;
	public static OI oi;
	public static Drive drive;
	
	public static ForkliftElevator elevator;
	public static ForkliftForks forks;
	
	@Override
	public void autonomousInit()
	{
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
		{
			autonomousCommand.start();
		}
	}
	
	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
	}
	
	@Override
	public void disabledInit()
	{
		// Do Nothing
	}
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit()
	{
		// Instantiate all hardware components
		RobotMap.init();
		
		try
		{
			camera = CameraServer.getInstance();
			camera.setQuality(50);
			camera.startAutomaticCapture("cam0");
		}
		catch (Exception E)
		{
			System.out.println("Problem with camera!");
		}
		
		// -----------------------------------------//
		// -----------------------------------------//
		
		// Instantiate subsystems
		drive = new Drive();
		elevator = new ForkliftElevator();
		forks = new ForkliftForks();
		
		// OI must be at the end of the list!!!
		oi = new OI();
		
		// -----------------------------------------//
		// -----------------------------------------//
		
		// Instantiate the command used for the autonomous period
		autonomousCommand = new AutonomousCommand();
	}
	
	@Override
	public void teleopInit()
	{
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
		{
			autonomousCommand.cancel();
		}
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();
	}
	
	/**
	 * This function called periodically during test mode
	 */
	@Override
	public void testPeriodic()
	{
		LiveWindow.run();
	}
}
