// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc157.AztechRobot2014.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc157.AztechRobot2014.Robot;

/**
 *
 */
public class  OperatorDrive extends Command {

    public OperatorDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.drive);
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double range = Robot.sensor.getShortRangeIRDistance();
        SmartDashboard.putNumber("Range", range);
        System.out.println(/*"v= " + Robot.sensor.getLongRangeIRSensor1().getVoltage() +*/ " Range = " + Robot.sensor.getLongRangeIRDistance1());

        double left = Robot.oi.getDriver().getLeftY();
        double right = Robot.oi.getDriver().getRightY();
        
        left += Robot.oi.getDriverLeft().getAxis(Joystick.AxisType.kY);
        right += Robot.oi.getDriverRight().getAxis(Joystick.AxisType.kY);

        Robot.drive.tankDrive(left, right);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
