
package org.usfirst.frc.team157.robot.commands;

import org.usfirst.frc.team157.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class GetSwitchStates extends Command
{
	
	public GetSwitchStates()
	{
		requires(Robot.forklift);
	}
	
	@Override
	protected void initialize()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void execute()
	{
		// TODO Auto-generated method stub
		System.out.println(Robot.forklift.GetLimitSwitchStates());
	}
	
	@Override
	protected boolean isFinished()
	{
		// TODO Auto-generated method stub
		return false;
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
