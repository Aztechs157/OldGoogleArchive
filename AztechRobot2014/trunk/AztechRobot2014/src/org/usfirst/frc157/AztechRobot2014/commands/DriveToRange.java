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
public class DriveToRange extends Command {

    private double desiredRange;

    private static final double RangeTolerance = 250;

    private double stopTime;
    private final double maxAlignmentTime = 10.0;

    private double error = 0;

    private double controlFinishTime = 0;
    private final static double BrakeTime = 0.5; //seconds

    private final static int NumSamples = 5;
    private int index = 0;
    private double[] dR_squared = new double[NumSamples];

    // stuff to sort out if range sensor is wonky and behave reasonably
    private double startRange;
    private boolean badRangeSensor;

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
        Robot.drive.setTerminateAutoCommands(false);
        SmartDashboard.putBoolean("At Optimum Range", false);

        startRange = getRangeToWall();
        if (startRange < 0.5) {
            System.out.println("Range Sensor is Wonky EEEK");
            badRangeSensor = true;
        } else {
            System.out.println("Range Sensor is Happy!");
            badRangeSensor = false;
        }
        SmartDashboard.putBoolean("BAD RANGE SENSOR", badRangeSensor);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (controlFinishTime == 0) {
            double range = getRangeToWall();
            double rangeDelta = desiredRange - range;

            dR_squared[index % NumSamples] = rangeDelta * rangeDelta;
            index++;

            error = 0.005 * rangeDelta + error;

            double drive = 0.002 * error + (4 * rangeDelta) / Robot.sensor.getUltrasonicSensor1().getMaxRange();

            if (drive > 0.6) {
                drive = 0.6;
                error = 0;
            }
            if (drive < -0.6) {
                drive = -0.6;
                error = 0;
            }

            if(badRangeSensor == false)
            {
                Robot.drive.tankDrive(-drive, -drive);
            }
            else
            {
                Robot.drive.tankDrive(0.25, 0.25);
            }
            
//            System.out.println(controlFinishTime + " T= " + desiredRange + " E= " + error + " R= " + range + "  d= " + drive);
            double dRS_sum = 0;
            for (int idx = 0; idx < NumSamples; idx++) {
                dRS_sum += dR_squared[idx];
            }
            System.out.println(controlFinishTime + " dRSs= " + dRS_sum + " T= " + desiredRange + " E= " + error + " R= " + range + "  d= " + drive);
            if (dRS_sum < RangeTolerance) {
                // done with control
                SmartDashboard.putBoolean("At Optimum Range", true);
                System.out.println("Range to Wall - Close Enough @ " + (Math.abs(getRangeToWall() - desiredRange)));
//                System.out.println("loop done at " + Timer.getFPGATimestamp());
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
        } else if (Robot.drive.terminateAutoCommands() == true) {
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
//        System.out.println("ending at " + Timer.getFPGATimestamp());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        // set motors to coast mode
        Robot.drive.setCoastMode();
//        System.out.println("interrupting at " + Timer.getFPGATimestamp());
    }

    public double getRangeToWall() {
        double range = Robot.sensor.getUltrasonicDistance1();
        return range;
    }
}
