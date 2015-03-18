
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
@Deprecated
public class TurnRightForTicks extends Command
{
	private int ticks;
	private boolean allDone;
	
	public TurnRightForTicks(int ticks)
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drive);
		this.ticks = ticks;
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
		// System.out.println("Current = " + Robot.drive.getRightEncoderTicks() + " Total = " + ticks);
		// System.out.println("Current = " + RobotMap.driveRightJag1.getOutputCurrent() + " 2 = "
		// + RobotMap.driveRightJag2.getOutputCurrent());
		if (Math.abs(Robot.drive.getLeftEncoderTicks()) >= ticks)
		{
			allDone = true;
		}
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		allDone = false;
		if (ticks > 0)
		{
			Robot.drive.tankDrive(0.8, -0.8);
		}
		else if (ticks < 0)
		{
			Robot.drive.tankDrive(-0.8, 0.8);
		}
		ticks = Math.abs(ticks);
		Robot.drive.resetEncoders();
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
