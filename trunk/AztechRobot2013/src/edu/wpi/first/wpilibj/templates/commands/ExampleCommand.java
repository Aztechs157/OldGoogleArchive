
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.templates.AztechRobot;

/**
 *
 * @author bradmiller
 */
public class ExampleCommand extends CommandBase {

    public ExampleCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//        CommandBase.drive.mecanumDrive_Cartesian(1,0,0);
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
