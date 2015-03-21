
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import org.usfirst.frc.team157.robot.RobotMap;
import org.usfirst.frc.team157.robot.commands.TurnGyro.ANGLES;
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
		
		if (switchPosition == 2)
		{
			// Grab and Drive Straight: Straight Pickup
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
			addSequential(new DriveGyroStraightForTicks(1600));
			addSequential(new Brake());
			
			// Put down and release
			addSequential(new VoltageSetPart(-12, Robot.elevator));
			addSequential(new VoltageSetPart(-12, Robot.forks));
		}
		else if (switchPosition == 3)
		{
			// Drive forward only
			
			addSequential(new DriveGyroStraightForTicks(1600));
			addSequential(new Brake());
		}
		else if (switchPosition == 4)
		{
			// TODO
			// 2 Yellow Tote Stack
			
			// Grab Tote
			addParallel(new SmartGrabForks());
			addSequential(new SleepUntilHasBox());
			
			// Lift it up
			addParallel(new VoltageSetPart(12, Robot.elevator));
			addSequential(new SleepForTime(1.5));
			addSequential(new VoltageStopPart(Robot.elevator));
			
			// Turn 180 to knock down the other bin
			addSequential(new TurnGyro(ANGLES.TOTE_180));
			addParallel(new Brake());
			
			// Drive forward for a little
			addSequential(new DriveGyroStraightForTicks(400));
			addParallel(new Brake());
			
			// Lower tote
			addParallel(new VoltageSetPart(-12, Robot.elevator));
			addSequential(new SleepForTime(0.5));
			addSequential(new VoltageStopPart(Robot.elevator));
			
			// Release tote
			addParallel(new VoltageSetPart(-12, Robot.forks));
			addSequential(new SleepForTime(1.5));
			addSequential(new VoltageStopPart(Robot.forks));
			
			// Drive back a little, stop, lower elevator, drive forward, and stop
			addSequential(new DriveSpeedForTime(-0.4, -0.5, 0.25));
			addSequential(new Brake());
			addSequential(new VoltageSetPart(-12, Robot.elevator));
			addSequential(new DriveSpeedForTime(0.4, 0.5, 0.25));
			addSequential(new Brake());
			
			// Grab the tote (now has another tote on top)!
			addParallel(new SmartGrabForks());
			addSequential(new SleepUntilHasBox());
			
			// Lift it up a little
			addParallel(new VoltageSetPart(12, Robot.elevator));
			addSequential(new SleepForTime(0.5));
			addSequential(new VoltageStopPart(Robot.elevator));
			
			// Turn left and stop
			addSequential(new TurnGyro(ANGLES.BOTH));
			addSequential(new Brake());
			
			// Drive Forward to auto zone
			addSequential(new DriveGyroStraightForTicks(1600));
			addSequential(new Brake());
		}
		else if (switchPosition == 5)
		{
			// Grab RC and move
			// Grab recycling container, move back, turn, and go forward
			
			// Grab recycling container
			addParallel(new SmartGrabForks());
			addSequential(new SleepUntilHasBox());
			
			// Lift it up
			addParallel(new VoltageSetPart(12, Robot.elevator));
			addSequential(new SleepForTime(0.25));
			addSequential(new VoltageStopPart(Robot.elevator));
			
			// Drive backward stop
			addSequential(new DriveSpeedForTime(-0.6, -0.9, 0.05));
			addSequential(new Brake());
			
			// Turn left and stop
			addSequential(new TurnGyro(ANGLES.RC));
			addSequential(new Brake());
			
			// Drive Forward, put down, and release
			addSequential(new DriveGyroStraightForTicks(1600));
			addSequential(new Brake());
			addSequential(new VoltageSetPart(-12, Robot.elevator));
			addSequential(new VoltageSetPart(-12, Robot.forks));
		}
		else if (switchPosition == 6)
		{
			// Grab tote and move
			// Grab tote, move back, turn opposite way, and go forward
			
			// Grab recycling container
			addParallel(new SmartGrabForks());
			addSequential(new SleepUntilHasBox());
			
			// Lift it up
			addParallel(new VoltageSetPart(12, Robot.elevator));
			addSequential(new SleepForTime(0.25));
			addSequential(new VoltageStopPart(Robot.elevator));
			
			// Drive backward stop
			addSequential(new DriveSpeedForTime(-0.6, -0.9, 0.05));
			addSequential(new Brake());
			
			// Turn right and stop
			addSequential(new TurnGyro(ANGLES.TOTE));
			addSequential(new Brake());
			
			// Drive Forward, put down, and release
			addSequential(new DriveGyroStraightForTicks(1600));
			addSequential(new Brake());
			addSequential(new VoltageSetPart(-12, Robot.elevator));
			addSequential(new VoltageSetPart(-12, Robot.forks));
		}
		else
		{
			// If switch = 1 OR switch is nonexistent:
			// Full Auto: Make container/tote stack, turn, drive forward
			
			// FIXME
			// Grab recycling container
			addParallel(new SmartGrabForks());
			addSequential(new SleepUntilHasBox());
			
			// Lift it up
			addParallel(new VoltageSetPart(12, Robot.elevator));
			addSequential(new SleepForTime(1.5));
			addSequential(new VoltageStopPart(Robot.elevator));
			
			// Drive forward to tote and stop
			addSequential(new DriveSpeedForTime(0.6, 0.9, 0.75));
			addParallel(new Brake());
			
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
			addSequential(new TurnGyro(ANGLES.BOTH));
			addSequential(new Brake());
			
			// Drive Forward and release
			addSequential(new DriveGyroStraightForTicks(1600));
			addSequential(new Brake());
			addSequential(new VoltageSetPart(-12, Robot.forks));
		}
	}
}
