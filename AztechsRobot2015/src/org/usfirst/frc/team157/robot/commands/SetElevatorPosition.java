
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import org.usfirst.frc.team157.robot.subsystems.ForkliftElevator;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Teju Nareddy
 */
public class SetElevatorPosition extends Command
{
	private double positionToSet;
	private boolean allDone;
	
	private double lastPosition;
	private double currentPosition;
	
	public SetElevatorPosition(double positionToSet)
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.positionToSet = positionToSet;
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end()
	{
		
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		lastPosition = currentPosition;
		currentPosition = Robot.elevator.getPotentiometerPosition();
		
		System.out.println("Last Position = " + lastPosition + " Current Position = " + currentPosition);
		
		if (Math.abs(positionToSet - currentPosition) < ForkliftElevator.getDeadband()
				&& Math.abs(positionToSet - lastPosition) < ForkliftElevator.getDeadband())
		{
			allDone = true;
			Robot.elevator.setJagVoltage(0);
		}
		else if (positionToSet > currentPosition)
		{
			Robot.elevator.setJagVoltage(-12);
		}
		else
		{
			Robot.elevator.setJagVoltage(12);
		}
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		currentPosition = Robot.elevator.getPotentiometerPosition();
		allDone = false;
		
		/*
		 * if (Math.abs(positionToSet - currentPosition) < Forklift.ELEVATOR_OVERSHOOT)
		 * {
		 * // already there so do nothing
		 * }
		 * else if (positionToSet > currentPosition)
		 * {
		 * positionToSet -= Forklift.ELEVATOR_OVERSHOOT;
		 * }
		 * else if (positionToSet < currentPosition)
		 * {
		 * positionToSet += Forklift.ELEVATOR_OVERSHOOT;
		 * }
		 */
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
		
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return allDone;
	}
}
