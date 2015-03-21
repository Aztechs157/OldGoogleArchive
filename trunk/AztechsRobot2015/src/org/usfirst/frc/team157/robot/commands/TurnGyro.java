
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnGyro extends Command
{
	public double degreesToTurn;
	public boolean allDone;
	
	public enum ANGLES
	{
		NOTHING, TOTE, RC, BOTH, TOTE_180
	}
	
	public TurnGyro(ANGLES type)
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drive);
		
		// Negative angle = turn left, positive angle = turn right
		if (type.equals(ANGLES.NOTHING))
		{
			degreesToTurn = -35;
		}
		else if (type.equals(ANGLES.RC))
		{
			degreesToTurn = -40;
		}
		else if (type.equals(ANGLES.TOTE))
		{
			degreesToTurn = 45;
		}
		else if (type.equals(ANGLES.BOTH))
		{
			degreesToTurn = -50;
		}
		else if (type.equals(ANGLES.TOTE_180))
		{
			degreesToTurn = 100;
		}
		else
		{
			degreesToTurn = 1;
		}
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end()
	{
		Robot.drive.tankDrive(0, 0);
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		if (Math.abs(Robot.drive.getAngle()) > Math.abs(degreesToTurn))
		{
			allDone = true;
		}
	}
	
	// Called just before this Command runs the first time
	@Override
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
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
		Robot.drive.tankDrive(0, 0);
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return allDone;
	}
}
