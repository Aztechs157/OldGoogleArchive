/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.InternalButton;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.subsystems.VisionSubsystem;

/**
 *
 * @author aztechs
 */
public class VisionAlign extends CommandBase {

    double timeoutTime;
    
    public VisionAlign() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(vision);
        requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("VisionAlign commanded.");
        vision.enable();
        timeoutTime = Timer.getFPGATimestamp() + 2.0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(vision.goalFound() != VisionSubsystem.GOAL__NONE) {
            System.out.println("Aligning... x="+vision.getXErrorDeg()+",  y="+vision.getYErrorDeg()+",   quality="+vision.getQuality()+"");

            InternalButton doTurn = new InternalButton();
            doTurn.whenPressed(new Turn(-vision.getXErrorDeg()));
            doTurn.setPressed(true);
            
            InternalButton doElevation = new InternalButton();
            doElevation.whenPressed(new AdjustElevation(vision.getYErrorDeg()));
            doElevation.setPressed(true);
            
            //            drive.mecanumDrive_Cartesian(0, 0, -vision.getXErrorNorm()*0.2);
        }
        
    }    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // quit if ...
        //    a. cycled for min frames and still have no target, or
        //    b. have the target and are within the required alignment tolerance
        return (Timer.getFPGATimestamp() > timeoutTime) ||
               ((vision.reachedMinFrames() && 
                (vision.goalFound() == VisionSubsystem.GOAL__NONE))) ||
               ((vision.goalFound() != VisionSubsystem.GOAL__NONE) && 
                (vision.getXErrorDeg() < RobotMap.AlignToleranceXDeg) &&
                (vision.getYErrorDeg() < RobotMap.AlignToleranceYDeg));
    }

    // Called once after isFinished returns true
    protected void end() {
        switch(vision.goalFound())
        {
            case VisionSubsystem.GOAL__HIGH:
                System.out.println("Found High Goal: Quality = "+vision.getQuality());
                break;
            case VisionSubsystem.GOAL__MIDDLE:
                System.out.println("Found Middle Goal: Quality = "+vision.getQuality());
                break;
            case VisionSubsystem.GOAL__LOW:
                System.out.println("Found Low Goal: Quality = "+vision.getQuality());
                break;
            default:
                System.out.println("Found No Goal: Quality = "+vision.getQuality());
        }
        vision.disable();
//        drive.mecanumDrive_Cartesian(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }    
}
