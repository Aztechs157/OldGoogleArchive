/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.InternalButton;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.subsystems.VisionSubsystem;

/**
 *
 * @author aztechs
 */
public class VisionAlign extends CommandBase {

    double timeoutDuration;
    double timeoutTime;
    static final double azAimOffset = 1;    // + is CCW
    static final double elAimOffset = -4.0; // + is down
    boolean move;

    public VisionAlign(double maxSearchDuration) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(vision);
        requires(drive);
        timeoutDuration = maxSearchDuration;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser3, 1, "Vision -searching");
        DriverStationLCD.getInstance().updateLCD();
        System.out.println("VisionAlign commanded.");
        vision.enable();
        timeoutTime = Timer.getFPGATimestamp() + timeoutDuration;
        move = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (vision.goalFound() != VisionSubsystem.GOAL__NONE) {

            if (move) {
                move = false;  // so this only gets done once...
                
                double azAdjust = vision.getXErrorDeg() - azAimOffset;
                double elAdjust = vision.getYErrorDeg() - elAimOffset;

                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser3, 1, "Vision -adjust az:" + azAdjust + " el:" + elAdjust);
                DriverStationLCD.getInstance().updateLCD();

                InternalButton doTurn = new InternalButton();
                doTurn.whenPressed(new Turn(-azAdjust));  // to get turn direction sense correct
                doTurn.setPressed(true);

                InternalButton doElevation = new InternalButton();
                doElevation.whenPressed(new AdjustElevation(elAdjust));
                doElevation.setPressed(true);

                // make sure we give commands at least 2 seconds to complete
                timeoutTime = Timer.getFPGATimestamp() + 2.0;

            }
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // quit if ...
        //    a. cycled for min frames and still have no target, or
        //    b. have the target and are within the required alignment tolerance
        return (Timer.getFPGATimestamp() > timeoutTime)
                || ((vision.reachedMinFrames()
                && (vision.goalFound() == VisionSubsystem.GOAL__NONE)))
                || ((vision.goalFound() != VisionSubsystem.GOAL__NONE)
                && (vision.getXErrorDeg() < RobotMap.AlignToleranceXDeg)
                && (vision.getYErrorDeg() < RobotMap.AlignToleranceYDeg));
    }

    // Called once after isFinished returns true
    protected void end() {
        switch (vision.goalFound()) {
            case VisionSubsystem.GOAL__HIGH:
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser3, 1, "Vision -found High       ");
                System.out.println("Found High Goal: Quality = " + vision.getQuality());
                break;
            case VisionSubsystem.GOAL__MIDDLE:
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser3, 1, "Vision -found Mid        ");
                System.out.println("Found Middle Goal: Quality = " + vision.getQuality());
                break;
            case VisionSubsystem.GOAL__LOW:
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser3, 1, "Vision -found Low        ");
                System.out.println("Found Low Goal: Quality = " + vision.getQuality());
                break;
            default:
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser3, 1, "Vision -found Nothing    ");
                System.out.println("Found No Goal: Quality = " + vision.getQuality());
        }
        DriverStationLCD.getInstance().updateLCD();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
