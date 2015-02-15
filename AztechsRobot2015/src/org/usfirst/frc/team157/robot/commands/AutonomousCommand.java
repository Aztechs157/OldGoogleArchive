
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Teju Nareddy
 *
 */
public class AutonomousCommand extends CommandGroup
{
	
	public AutonomousCommand()
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drive);
		requires(Robot.forks);
		requires(Robot.elevator);
		
		addParallel(new SmartGrabForks());
		addSequential(new SleepForTime(1));
		
		addParallel(new VoltageSetPart(12, Robot.elevator));
		addSequential(new SleepForTime(2));
		addSequential(new VoltageStopPart(Robot.elevator));
		
		addSequential(new DriveSpeedForTime(0.5, 0.5, 0.5));
		
		addParallel(new VoltageSetPart(-12, Robot.elevator));
		addSequential(new SleepForTime(1));
		addSequential(new VoltageStopPart(Robot.elevator));
		
		addSequential(new VoltageSetPart(-12, Robot.forks));
		
		addParallel(new SmartGrabForks());
		addSequential(new SleepForTime(1));
		
	}
}
