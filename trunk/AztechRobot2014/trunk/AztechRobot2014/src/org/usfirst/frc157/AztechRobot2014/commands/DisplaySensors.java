/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc157.AztechRobot2014.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc157.AztechRobot2014.Robot;

/**
 *
 * @author Teju
 */
public class DisplaySensors extends Command {
    
    double initTime;
    
    public DisplaySensors() {
        // Use requires(                                        ) here to declare subsystem dependencies
        requires(Robot.ballRetriever);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        initTime = Timer.getFPGATimestamp();
        System.out.println("DisplaySensors Command INITIALIZE");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        /*if(Timer.getFPGATimestamp() - initTime >= 1)
        {
            System.out.println("DisplaySensors RUN");
            
            initTime = Timer.getFPGATimestamp();
        }*/
        Robot.ballRetriever.getAngle();     //Outputs data to the SmartDashboard
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
        System.out.println("Display Sensors Interrupted");
    }
}