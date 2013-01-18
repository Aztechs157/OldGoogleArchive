/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc157.BuiltRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc157.BuiltRobot.RobotMap;
import org.usfirst.frc157.BuiltRobot.Robot;


/**
 *
 * @author MattKahn
 */
public class ManualDrive extends Command {
    
    public ManualDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double x = Robot.oi.getFirstController().getLeftX();
        double y = Robot.oi.getFirstController().getGameY();
        double rotation = Robot.oi.getFirstController().getTriggers();
        double gyroAngle = 0;
        RobotMap.driveMechanumDrive.mecanumDrive_Cartesian(x, y, rotation, gyroAngle);
        System.out.println("X= " + x + " Y= " + y + "0= " + rotation);
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
