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
    
    public static double[] ANGLES = {110, 90, 39};
    //public static double[] ANGLES = {115, 80, 50};
    public static final int RETRIEVER_IN = 0;
    public static final int RETRIEVER_MIDDLE = 1;
    public static final int RETRIEVER_OUT = 2;
    
    private boolean isAngleMode = true;
    private double m_desiredAngle = 0;
    private double m_desiredVoltage = 0;
    
    public MoveBallRetriever(double _target_voltage)
    {
           System.out.println("Created MoveBallRetriever (Voltage Style) @ " + _target_voltage);
           m_desiredVoltage = _target_voltage;
           isAngleMode = false;
    }
    
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
        isAngleMode = true;
    }

    // Called just before this Command runs the first time
    protected void initialize() {    
        System.out.println("MoveBallRetriever.initialize @ "+ m_desiredAngle);
        Robot.ballRetriever.updatePID();
        if(isAngleMode)
        {
            Robot.ballRetriever.setAngle(m_desiredAngle);
        }
        else
        {
            Robot.ballRetriever.setVoltageSetpoint(m_desiredVoltage);
        }
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