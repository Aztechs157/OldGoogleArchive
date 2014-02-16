/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc157.AztechRobot2014.commands;

import java.util.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc157.AztechRobot2014.Robot;

/**
 *
 * @author Teju
 */
public class MoveBallRetriever extends Command {
    
    /*
    public enum RetrieverPosition
    {
        RETRIEVER_UP, RETRIEVER_MIDDLE, RETRIEVER_DOWN
    }*/
    
    public static double[] ANGLES = {135, 90, 45};
    public static final int RETRIEVER_UP = 0;
    public static final int RETRIEVER_MIDDLE = 1;
    public static final int RETRIEVER_DOWN = 2;
    
    private double m_desiredAngle;
    
    public MoveBallRetriever(int position) {
        try{
            m_desiredAngle = ANGLES[position];
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            e.printStackTrace();
        }
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        //requires(Robot.ballRetriever);
        System.out.println("Created MoveBallRetriever @ " + m_desiredAngle);
    }

    // Called just before this Command runs the first time
    protected void initialize() {    
        System.out.println("MoveBallRetriever.initialize @ "+ m_desiredAngle);
        Robot.ballRetriever.updatePID();
        Robot.ballRetriever.setAngle(m_desiredAngle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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