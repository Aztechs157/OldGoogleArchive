
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnGyro extends Command
{
	public double degreesToTurn;
	public boolean allDone = false;
	
	public TurnGyro(double degreesToTurn)
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drive);
		this.degreesToTurn = degreesToTurn;
	}
	
	// Called just before this Command runs the first time
	protected void initialize()
	{
		Robot.drive.resetGyro();
		allDone = false;
		
		if (degreesToTurn > 0)
		{
			Robot.drive.tankDrive(0.8, -0.8);
		}
		else if (degreesToTurn < 0)
		{
			Robot.drive.tankDrive(-0.8, 0.8);
		}
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		if (Math.abs(Robot.drive.getAngle()) > Math.abs(degreesToTurn))
		{
			allDone = true;
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return allDone;
	}
	
	// Called once after isFinished returns true
	protected void end()
	{
		Robot.drive.tankDrive(0, 0);
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		Robot.drive.tankDrive(0, 0);
	}
}
