
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.subsystems.ForkliftPart;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Teju Nareddy
 */
public class SetPosition extends Command
{
	private double positionToSet;
	private boolean allDone;
	private ForkliftPart part;
	
	// private double lastPosition;
	// private double currentPosition;
	
	public SetPosition(double positionToSet, ForkliftPart part)
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(part);
		this.positionToSet = positionToSet;
		this.part = part;
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
		/*
		 * lastPosition = currentPosition;
		 * currentPosition = Robot.elevator.getRotaryEncoderPosition();
		 * 
		 * System.out.println("Last Position = " + lastPosition + " Current Position = " + currentPosition);
		 * 
		 * if (Math.abs(positionToSet - currentPosition) < ForkliftElevator.getDeadband()
		 * && Math.abs(positionToSet - lastPosition) < ForkliftElevator.getDeadband())
		 * {
		 * allDone = true;
		 * Robot.elevator.setJagVoltage(0);
		 * }
		 * else if (positionToSet > currentPosition)
		 * {
		 * Robot.elevator.setJagVoltage(-12);
		 * }
		 * else
		 * {
		 * Robot.elevator.setJagVoltage(12);
		 * }
		 */
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		// currentPosition = Robot.elevator.getRotaryEncoderPosition();
		allDone = true;
		part.setJagPosition(positionToSet);
		System.out.println("Setpoint: " + positionToSet);
		
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
