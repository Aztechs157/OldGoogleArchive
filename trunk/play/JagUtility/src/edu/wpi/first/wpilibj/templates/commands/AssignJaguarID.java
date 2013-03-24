/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author matt
 */
public class AssignJaguarID extends CommandBase {
    private static int MIN_JAG_ID = 1;
    private static int MAX_JAG_ID = 63;
    
    
    public AssignJaguarID() {
        // Use requires() here to declare subsystem dependencies
        requires(jagUtil);
            }

    // Called just before this Command runs the first time
    protected void initialize() {

        int desiredID = (int)SmartDashboard.getNumber("Jag ID", 0);
        if((desiredID < MIN_JAG_ID) || (desiredID > MAX_JAG_ID))
        {
            SmartDashboard.putString("ERROR", "JAG ID must " + MIN_JAG_ID + " <= " + desiredID + " <= " + MAX_JAG_ID);
        }
        else 
        {
            jagUtil.sendSetIDCANMessage((byte)desiredID);
        }       
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
    }
}
