/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.templates.AztechRobot;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author MattKahn
 */
public class ManualDrive extends Command {

    public ManualDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(AztechRobot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double rotation = -AztechRobot.oi.getFirstController().getRightY();
        double y = -AztechRobot.oi.getFirstController().getRightX();
        double x = -AztechRobot.oi.getFirstController().getTriggers();
        double gyroAngle = 0;

//        RobotMap.driveMechanumDrive.mecanumDrive_Cartesian( rotation, 0, 0, 0);
        RobotMap.driveMechanumDrive.mecanumDrive_Cartesian(x, y, rotation, gyroAngle);
        System.out.println("X= " + x + " Y= " + y + " 0= " + rotation);
                try {
            System.out.print("FR.getX()=" + RobotMap.driveDriveFR.getX());
            System.out.print(" FL.getX()=" + RobotMap.driveDriveFL.getX());
            System.out.print(" RR.getX()=" + RobotMap.driveDriveRR.getX());
            System.out.println(" RL.getX()=" + RobotMap.driveDriveRL.getX());
        } catch (CANTimeoutException ex) {
            // don't care
        }

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
