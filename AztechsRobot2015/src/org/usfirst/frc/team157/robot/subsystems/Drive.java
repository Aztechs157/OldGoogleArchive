// FIXME undo the comments of Drive Jaguars being null

package org.usfirst.frc.team157.robot.subsystems;

import org.usfirst.frc.team157.robot.RobotMap;
import org.usfirst.frc.team157.robot.ScaledCANJaguar;
import org.usfirst.frc.team157.robot.commands.OperatorDrive;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
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
	
	private Encoder leftQuadEncoder = RobotMap.driveQuadEncoderLeft;
	private Encoder rightQuadEncoder = RobotMap.driveQuadEncoderRight;
	
	private Gyro gyro = RobotMap.gyro;
	
	public Drive()
	{
		ScaledCANJaguar.setupJagForPercentControl(leftJag1, CANJaguar.NeutralMode.Coast);
		ScaledCANJaguar.setupJagForPercentControl(leftJag2, CANJaguar.NeutralMode.Coast);
		ScaledCANJaguar.setupJagForPercentControl(rightJag1, CANJaguar.NeutralMode.Coast);
		ScaledCANJaguar.setupJagForPercentControl(rightJag2, CANJaguar.NeutralMode.Coast);
	}
	
	public void brake()
	{
		leftJag1.configNeutralMode(CANJaguar.NeutralMode.Brake);
		leftJag2.configNeutralMode(CANJaguar.NeutralMode.Brake);
		rightJag1.configNeutralMode(CANJaguar.NeutralMode.Brake);
		rightJag2.configNeutralMode(CANJaguar.NeutralMode.Brake);
		
		byte group = 24;
		
		if (leftJag1 != null)
		{
			leftJag1.set(0, group);
		}
		else
		{
			System.out.println("Drive Left Jag 1 is null!");
		}
		
		if (leftJag2 != null)
		{
			leftJag2.set(0, group);
		}
		else
		{
			System.out.println("Drive Left Jag 2 is null!");
		}
		
		if (rightJag1 != null)
		{
			rightJag1.set(0, group);
		}
		else
		{
			System.out.println("Drive Right Jag 1 is null!");
		}
		
		if (rightJag2 != null)
		{
			rightJag2.set(0, group);
		}
		else
		{
			System.out.println("Drive Right Jag 2 is null!");
		}
		
		CANJaguar.updateSyncGroup(group);
		
		leftJag1.configNeutralMode(CANJaguar.NeutralMode.Coast);
		leftJag2.configNeutralMode(CANJaguar.NeutralMode.Coast);
		rightJag1.configNeutralMode(CANJaguar.NeutralMode.Coast);
		rightJag2.configNeutralMode(CANJaguar.NeutralMode.Coast);
	}
	
	public double getAngle()
	{
		return gyro.getAngle();
	}
	
	public int getLeftEncoderTicks()
	{
		return leftQuadEncoder.get();
	}
	
	public int getRightEncoderTicks()
	{
		return rightQuadEncoder.get();
	}
	
	public void resetEncoders()
	{
		leftQuadEncoder.reset();
		rightQuadEncoder.reset();
	}
	
	public void resetGyro()
	{
		gyro.reset();
		System.out.println("Gyro Reset: " + gyro.getAngle());
	}
	
	public void tankDrive(double left, double right)
	{
		byte group = 22; // Some random number
		
		if (leftJag1 != null)
		{
			leftJag1.set(left, group);
		}
		else
		{
			System.out.println("Drive Left Jag 1 is null!");
		}
		
		if (leftJag2 != null)
		{
			leftJag2.set(left, group);
		}
		else
		{
			System.out.println("Drive Left Jag 2 is null!");
		}
		
		if (rightJag1 != null)
		{
			rightJag1.set(right, group);
		}
		else
		{
			System.out.println("Drive Right Jag 1 is null!");
		}
		
		if (rightJag2 != null)
		{
			rightJag2.set(right, group);
		}
		else
		{
			System.out.println("Drive Right Jag 2 is null!");
		}
		
		CANJaguar.updateSyncGroup(group);
		
	}
	
	@Override
	protected void initDefaultCommand()
	{
		// Set the subsystem's default command here: Will call this command repeatedly
		setDefaultCommand(new OperatorDrive());
		
	}
}
