
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.DigitalSwitch;
import org.usfirst.frc.team157.robot.Robot;
import org.usfirst.frc.team157.robot.ScaledCANJaguar;
import edu.wpi.first.wpilibj.command.Command;

public class HomeFork extends Command
{
	private DigitalSwitch aSwitch;
	private DigitalSwitch bSwitch;
	private ScaledCANJaguar jag;
	
	private boolean lastA;
	private boolean lastB;
	private boolean allDone;
	
	public HomeFork()
	{
		requires(Robot.forklift);
		aSwitch = Robot.forklift.forkHomeA;
		bSwitch = Robot.forklift.forkHomeB;
		jag = Robot.forklift.forksJag;
	}
	
	@Override
	protected void initialize()
	{
		// TODO Auto-generated method stub
		jag.set(0);
		lastA = aSwitch.get();
		lastB = bSwitch.get();
		allDone = false;
	}
	
	@Override
	protected void execute()
	{
		// TODO Auto-generated method stub
		if (bSwitch.get())
		{
			jag.set(6);
		}
		else
		{
			jag.set(-6);
		}
		
		if ((lastA != aSwitch.get()) || (lastB != bSwitch.get()))
		{
			// Found a home
			jag.set(0);
			System.out.println("Found a home");
			
			if (!bSwitch.get() && lastA && !aSwitch.get())
			{
				System.out.println("Found Home 1");
			}
			else if (!aSwitch.get() && !lastB && bSwitch.get())
			{
				System.out.println("Found Home 2");
			}
			else if (bSwitch.get() && !lastA && aSwitch.get())
			{
				System.out.println("Found Home 3");
			}
			else
			{
				System.out.println("Something has gone wrong: Found nonexistant home");
			}
			System.out.println(Robot.forklift.GetLimitSwitchStates());
			allDone = true;
		}
	}
	
	@Override
	protected boolean isFinished()
	{
		// TODO Auto-generated method stub
		return allDone;
	}
	
	@Override
	protected void end()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void interrupted()
	{
		// TODO Auto-generated method stub
		
	}
	
}
