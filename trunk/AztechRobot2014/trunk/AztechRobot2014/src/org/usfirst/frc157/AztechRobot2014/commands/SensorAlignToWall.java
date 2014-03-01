/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc157.AztechRobot2014.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc157.AztechRobot2014.Robot;

/**
 *
 * @author mattkahn
 */
public class SensorAlignToWall extends Command {

    private final static double RobotWidth = 60;
    private final static double MinTurnNeeded = 0.1;

    private double stopTime;
    private final double maxAlignmentTime = 20.0;

    public SensorAlignToWall() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        stopTime = Timer.getFPGATimestamp() + maxAlignmentTime;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double turnNeeded = getTurnNeeded();
        double drive = turnNeeded * 2;
        Robot.drive.tankDrive(-drive, drive);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        double turnNeeded = getTurnNeeded();
        turnNeeded = Math.abs(turnNeeded);
        return ((turnNeeded < MinTurnNeeded) || (Timer.getFPGATimestamp() > stopTime));
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drive.tankDrive(0, 0); // stop the robot
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

    // return a number proportional to the amount of turn needed in the range
    //  of approximately -1 to 1 or so
    //  -1 is turn counterclockwiaw
    private double getTurnNeeded() {

        double leftRange = Robot.sensor.getLongRangeIRDistanceLeft();
        double rightRange = Robot.sensor.getLongRangeIRDistanceRight();
        double deltaRange = leftRange - rightRange;

        SmartDashboard.putNumber("Left",  leftRange);
        SmartDashboard.putNumber("Right", rightRange);
        SmartDashboard.putNumber("Delta", deltaRange);
        
        while (deltaRange > RobotWidth) {
            leftRange = Robot.sensor.getLongRangeIRDistanceLeft();
            rightRange = Robot.sensor.getLongRangeIRDistanceRight();
            deltaRange = leftRange - rightRange;
        }

        return deltaRange / RobotWidth;
    }
}
