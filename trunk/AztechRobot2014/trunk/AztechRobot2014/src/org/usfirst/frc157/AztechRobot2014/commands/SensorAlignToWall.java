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
    private final static double MinTurnNeeded = 0.03;

    private double stopTime;
    private final double maxAlignmentTime = 20.0;

    private double error = 0;

    public SensorAlignToWall() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
        setInterruptible(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        stopTime = Timer.getFPGATimestamp() + maxAlignmentTime;
        error = 0;
        Robot.drive.setTerminateAutoCommands(false);
        SmartDashboard.putBoolean("Aligned To Wall", false);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double turnNeeded = getTurnNeeded();

        error = 0.6 * turnNeeded + error;

        double drive = turnNeeded * 4 + error;
        Robot.drive.tankDrive(-drive, drive);
        Robot.sensor.sensorRangesToSmartDashboard();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        double turnNeeded = getTurnNeeded();
        turnNeeded = Math.abs(turnNeeded);
        if (turnNeeded < MinTurnNeeded) {
            SmartDashboard.putBoolean("Aligned To Wall", true);
            return true;
        } else if (Timer.getFPGATimestamp() > stopTime) {
            return true;
        } else if (Robot.drive.terminateAutoCommands() == true) {
            return true;
        } else {
            return false;
        }

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

        SmartDashboard.putNumber("Left", leftRange);
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
