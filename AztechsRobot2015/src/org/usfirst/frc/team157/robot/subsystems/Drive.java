// FIXME undo the comments of Drive Jaguars being null

package org.usfirst.frc.team157.robot.subsystems;

import org.usfirst.frc.team157.robot.RobotMap;
import org.usfirst.frc.team157.robot.ScaledCANJaguar;
import org.usfirst.frc.team157.robot.commands.OperatorDrive;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Drive subsystem of the robot.
 *
 * @author Teju Nareddy
 *
 */
public class Drive extends Subsystem
{
	private ScaledCANJaguar leftJag1 = RobotMap.driveLeftJag1;
	private ScaledCANJaguar leftJag2 = RobotMap.driveLeftJag2;
	private ScaledCANJaguar rightJag1 = RobotMap.driveRightJag1;
	private ScaledCANJaguar rightJag2 = RobotMap.driveRightJag2;
	
	public void tankDrive(double left, double right)
	{
		byte group = 22; // Some random number
		
		if (leftJag1 != null)
		{
			leftJag1.set(left, group);
		}
		else
		{
			// System.out.println("Drive Left Jag 1 is null!");
		}
		
		if (leftJag2 != null)
		{
			leftJag2.set(left, group);
		}
		else
		{
			// System.out.println("Drive Left Jag 2 is null!");
		}
		
		if (rightJag1 != null)
		{
			rightJag1.set(right, group);
		}
		else
		{
			// System.out.println("Drive Right Jag 1 is null!");
		}
		
		if (rightJag2 != null)
		{
			rightJag2.set(right, group);
		}
		else
		{
			// System.out.println("Drive Right Jag 2 is null!");
		}
		
		// System.out.println("LEFT = " + left + ", RIGHT = " + right);
		CANJaguar.updateSyncGroup(group);
		
	}
	
	@Override
	protected void initDefaultCommand()
	{
		// Set the subsystem's default command here: Will call this command repeatedly
		
		setDefaultCommand(new OperatorDrive());
		
	}
}
