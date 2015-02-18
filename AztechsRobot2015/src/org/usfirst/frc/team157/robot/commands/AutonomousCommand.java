
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import org.usfirst.frc.team157.robot.RobotMap;
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
		
		int switchPosition = RobotMap.autoSwitch.getCase();
		
		// System.out.println("Switch = " + switchPosition);
		if (switchPosition == 2)
		{
			// Grab, lift, drive forward, put down, release
			
			// Drive forward to tote
			addSequential(new DriveSpeedForTime(0.6, 0.9, 0.75));
			addParallel(new DriveSpeedForTime(0, 0, 0.1));
			
			// Grab container/tote
			addParallel(new SmartGrabForks());
			// addSequential(new SleepForTime(1.5));
			addSequential(new SleepUntilHasBox());
			
			// Lift it up
			addParallel(new VoltageSetPart(12, Robot.elevator));
			addSequential(new SleepForTime(0.5));
			addSequential(new VoltageStopPart(Robot.elevator));
			
			// Drive Forward
			addSequential(new DriveStraightForTicks(1650));
			addSequential(new Brake());
			
			// Put down and release
			addSequential(new VoltageSetPart(-12, Robot.elevator));
			addSequential(new VoltageSetPart(-12, Robot.forks));
		}
		else if (switchPosition == 3)
		{
			// Drive forward only
			
			addSequential(new DriveStraightForTicks(1600));
			addSequential(new Brake());
		}
		else if (switchPosition == 4)
		{
			// Should do nothing
		}
		else if (switchPosition == 5)
		{
			// Grab recycling container, move back, turn, and go forward
			
			// Grab recycling container
			addParallel(new SmartGrabForks());
			addSequential(new SleepUntilHasBox());
			
			// Lift it up
			addParallel(new VoltageSetPart(12, Robot.elevator));
			addSequential(new SleepForTime(0.25));
			addSequential(new VoltageStopPart(Robot.elevator));
			
			// Drive backward stop
			addSequential(new DriveSpeedForTime(-0.6, -0.9, 0.1));
			addSequential(new Brake());
			
			// Turn left and stop
			addSequential(new TurnLeftForTicks(92));
			addSequential(new Brake());
			
			// Drive Forward, put down, and release
			addSequential(new DriveStraightForTicks(1400));
			addSequential(new Brake());
			addSequential(new VoltageSetPart(-12, Robot.elevator));
			addSequential(new VoltageSetPart(-12, Robot.forks));
		}
		else if (switchPosition == 6)
		{
			// Grab tote, move back, turn opposite way, and go forward
			
			// Grab recycling container
			addParallel(new SmartGrabForks());
			addSequential(new SleepUntilHasBox());
			
			// Lift it up
			addParallel(new VoltageSetPart(12, Robot.elevator));
			addSequential(new SleepForTime(0.25));
			addSequential(new VoltageStopPart(Robot.elevator));
			
			// Drive backward stop
			addSequential(new DriveSpeedForTime(-0.6, -0.9, 0.1));
			addSequential(new Brake());
			
			// Turn right and stop
			addSequential(new TurnRightForTicks(120));
			addSequential(new Brake());
			
			// Drive Forward, put down, and release
			addSequential(new DriveStraightForTicks(1400));
			addSequential(new Brake());
			addSequential(new VoltageSetPart(-12, Robot.elevator));
			addSequential(new VoltageSetPart(-12, Robot.forks));
		}
		else
		{
			// If switch = 1 OR switch is nonexistent: Grab container/tote stack, turn, drive forward
			
			// Grab recycling container
			addParallel(new SmartGrabForks());
			addSequential(new SleepUntilHasBox());
			
			// Lift it up
			addParallel(new VoltageSetPart(12, Robot.elevator));
			addSequential(new SleepForTime(1.5));
			addSequential(new VoltageStopPart(Robot.elevator));
			
			// Drive forward to tote and stop
			addSequential(new DriveSpeedForTime(0.6, 0.9, 0.75));
			addParallel(new DriveSpeedForTime(0, 0, 0.1));
			
			// Lower container
			addParallel(new VoltageSetPart(-12, Robot.elevator));
			addSequential(new SleepForTime(0.5));
			addSequential(new VoltageStopPart(Robot.elevator));
			
			// Release container
			addParallel(new VoltageSetPart(-12, Robot.forks));
			addSequential(new SleepForTime(1.5));
			addSequential(new VoltageStopPart(Robot.forks));
			
			// Drive back a little, stop, lower elevator, drive forward, and stop
			addSequential(new DriveSpeedForTime(-0.4, -0.5, 0.25));
			addSequential(new Brake());
			addSequential(new VoltageSetPart(-12, Robot.elevator));
			addSequential(new DriveSpeedForTime(0.4, 0.5, 0.25));
			addSequential(new Brake());
			
			// Grab the tote (now has container on top!)
			addParallel(new SmartGrabForks());
			addSequential(new SleepUntilHasBox());
			
			// Turn left and stop
			addSequential(new TurnLeftForTicks(92));
			addSequential(new Brake());
			
			// Drive Forward and release
			addSequential(new DriveStraightForTicks(1625));
			addSequential(new Brake());
			addSequential(new VoltageSetPart(-12, Robot.forks));
		}
	}
}
