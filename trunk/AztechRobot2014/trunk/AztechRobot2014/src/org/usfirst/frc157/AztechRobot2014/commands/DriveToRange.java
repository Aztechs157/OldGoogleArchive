/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc157.AztechRobot2014.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc157.AztechRobot2014.Robot;

/**
 *
 * @author mattkahn
 */
public class DriveToRange extends Command {

    private double desiredRange;

    private static final double RangeTolerance = 10; //cm

    private double stopTime;
    private final double maxAlignmentTime = 2.0;

    public DriveToRange(double _desiredRange) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
        // eg. requires(chassis);

        desiredRange = 100 * _desiredRange;
        
        // set motors to brake mode
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        stopTime = Timer.getFPGATimestamp() + maxAlignmentTime;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double range = getRangeToWall();
        double rangeDelta = desiredRange - range;
        double drive = (10 * rangeDelta) / Robot.sensor.getUltrasonicSensor1().getMaxRange();
        Robot.drive.tankDrive(drive, drive);

        System.out.println("T= " + desiredRange + "R= " + range + "  d= " + drive);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (Math.abs(getRangeToWall() - desiredRange) < RangeTolerance) {
            return true;
        } else if (Timer.getFPGATimestamp() > stopTime) {
            return true;
        } else {
            return false;
        }
    }

// Called once after isFinished returns true
    protected void end() {
        Robot.drive.tankDrive(0, 0);
        
        // set motors to coast mode
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        // set motors to coast mode
    }

    public double getRangeToWall() {
        double range = Robot.sensor.getUltrasonicDistance1();
        return range;
    }
}
