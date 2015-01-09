/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author Teju
 */
public class ChangeMotorControl extends Command
{
	
	public ChangeMotorControl()
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}
	
	// Called just before this Command runs the first time
	protected void initialize()
	{
		RobotMap.useCAN = !RobotMap.useCAN;
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return true;
	}
	
	// Called once after isFinished returns true
	protected void end()
	{
		System.out.println("UseCAN changed to: " + RobotMap.useCAN);
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
	}
}
