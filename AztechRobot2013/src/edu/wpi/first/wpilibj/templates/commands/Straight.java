/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author aztechs
 */
public class Straight extends CommandBase {

    double feetPerSecond = 3.0;
    double feetForward = 0;
    double feetRight = 0;
    double speedX = -0.5;
    double speedY = 0.5;
    double timeX = 0;
    double timeY = 0;
    double stopTimeX, stopTimeY;
    
    public Straight(double inFeetForward, double inFeetRight) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(CommandBase.drive);
        
        feetForward = inFeetForward;
        feetRight = inFeetRight;
        
        timeX = inFeetRight / feetPerSecond;
        timeY = inFeetForward / feetPerSecond;
        
        if(inFeetRight < 0)
        {
            timeX *= -1;
            speedX *= -1;
        }
        
        if(inFeetForward < 0)
        {
            timeY *= -1;
            speedY *= -1;
        }

    }

    // Called just before this Command runs the first time
    protected void initialize() {
        stopTimeX = Timer.getFPGATimestamp() + timeX;
        stopTimeY = Timer.getFPGATimestamp() + timeY;
        System.out.println("Straight " + feetRight + " feet x, " + feetForward  + " feet y");
        System.out.println("  ** speedX = "+speedX+", speedY = "+speedY+", timeX = "+timeX+", timeY = "+timeY);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        CommandBase.drive.mecanumDrive_Cartesian(speedX, speedY, 0);
        if(Timer.getFPGATimestamp() > stopTimeX)
        {
            speedX = 0;
        }
        if(Timer.getFPGATimestamp() > stopTimeY)
        {
            speedY = 0;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Timer.getFPGATimestamp() > stopTimeX) && (Timer.getFPGATimestamp() > stopTimeY);
    }
    
    // Called once after isFinished returns true
    protected void end() {
        System.out.println("  ** Straight finished!");
        CommandBase.drive.mecanumDrive_Cartesian(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
    
    
}
