
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.HomeSensor;
import org.usfirst.frc.team157.robot.HomeSensor.Zone;
import org.usfirst.frc.team157.robot.Robot;
import org.usfirst.frc.team157.robot.ScaledCANJaguar;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author Teju Nareddy
 *
 */

public class HomeFork extends Command
{
	private ScaledCANJaguar jag;
	private HomeSensor homeSensor;

	private Zone currentZone;
	private Zone targetZone;
	private boolean allDone;

	public HomeFork(Zone target)
	{
		requires(Robot.forklift);
		jag = Robot.forklift.forksJag;
		homeSensor = Robot.forklift.forksHome;
		targetZone = target;
	}

	@Override
	protected void end()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute()
	{
		// TODO Auto-generated method stub
		currentZone = homeSensor.getZone();
		if (!currentZone.equals(targetZone))
		{
			System.out.println(HomeSensor.zoneToNumber(currentZone));
			if (HomeSensor.zoneToNumber(currentZone) > HomeSensor.zoneToNumber(targetZone))
			{
				jag.set(6);
			}
			else
			{
				jag.set(-6);
			}
		}
		else
		{
			jag.set(0);
			allDone = true;
		}

		// System.out.println(currentZone.toString() + " going to " + targetZone.toString());
	}

	@Override
	protected void initialize()
	{
		// TODO Auto-generated method stub
		allDone = false;
	}

	@Override
	protected void interrupted()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isFinished()
	{
		// TODO Auto-generated method stub
		return allDone;
	}

}
