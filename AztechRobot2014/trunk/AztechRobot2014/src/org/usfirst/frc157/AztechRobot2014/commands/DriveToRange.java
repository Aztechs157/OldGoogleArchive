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

    private static final double RangeTolerance = 5; //cm

    private double stopTime;
    private final double maxAlignmentTime = 20.0;

    private double error = 0;

    private double controlFinishTime = 0;
    private final static double BrakeTime = 0.5; //seconds

    private final static int NumSamples = 5;
    private int index = 0;
    private double[] dR_squared = new double[NumSamples];

    public DriveToRange(double _desiredRange) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
        setInterruptible(true);

        desiredRange = 100 * _desiredRange;

        // set motors to brake mode
        Robot.drive.setBrakeMode();

    }

    // Called just before this Command runs the first time
    protected void initialize() {
        stopTime = Timer.getFPGATimestamp() + maxAlignmentTime;
        error = 0;
        controlFinishTime = 0;
        index = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (controlFinishTime == 0) {
            double range = getRangeToWall();
            double rangeDelta = desiredRange - range;

            dR_squared[index % NumSamples] = rangeDelta * rangeDelta;
            index++;

            error = 0.005 * rangeDelta + error;

            double drive = 0.05 * error + (8 * rangeDelta) / Robot.sensor.getUltrasonicSensor1().getMaxRange();

            if (drive > 0.6) {
                drive = 0.6;
                error = 0;
            };
            if (drive < -0.6) {
                drive = -0.6;
                error = 0;
            };

            Robot.drive.tankDrive(drive, drive);

//            System.out.println(controlFinishTime + " T= " + desiredRange + " E= " + error + " R= " + range + "  d= " + drive);
            double dRS_sum = 0;
            for (int idx = 0; idx < NumSamples; idx++) {
                dRS_sum += dR_squared[idx];
            }
            System.out.println(controlFinishTime + " dRSs= " + dRS_sum + " T= " + desiredRange + " E= " + error + " R= " + range + "  d= " + drive);
            if (dRS_sum < 20) {
                // done with control
                System.out.println("Range to Wall - Close Enough @ " + (Math.abs(getRangeToWall() - desiredRange)));
                System.out.println("loop done at " + Timer.getFPGATimestamp());
                controlFinishTime = Timer.getFPGATimestamp();
                Robot.drive.tankDrive(0, 0);
            }
        }
        Robot.sensor.sensorRangesToSmartDashboard();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if ((controlFinishTime != 0) && (Timer.getFPGATimestamp() > (controlFinishTime + BrakeTime))) {
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
        Robot.drive.setCoastMode();
        System.out.println("ending at " + Timer.getFPGATimestamp());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        // set motors to coast mode
        Robot.drive.setCoastMode();
        System.out.println("interrupting at " + Timer.getFPGATimestamp());
    }

    public double getRangeToWall() {
        double range = Robot.sensor.getUltrasonicDistance1();
        return range;
    }
}
