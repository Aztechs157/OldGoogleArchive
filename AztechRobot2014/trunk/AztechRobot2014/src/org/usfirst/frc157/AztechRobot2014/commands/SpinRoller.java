/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc157.AztechRobot2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc157.AztechRobot2014.Robot;

/**
 *
 * @author Teju
 */
public class SpinRoller extends Command {
    
    public static final double[] SPEEDS = {-1, 0, 1};
    public static final int ROLLER_IN = 0;
    public static final int ROLLER_STOP = 1;
    public static final int ROLLER_OUT = 2;
    
    private double m_desiredSpeed;
    
    public SpinRoller(int direction) {
        System.out.println(direction);
        try{
            m_desiredSpeed = SPEEDS[direction];
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            e.printStackTrace();
        }
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
     
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.ballRetriever.spinRoller(m_desiredSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}