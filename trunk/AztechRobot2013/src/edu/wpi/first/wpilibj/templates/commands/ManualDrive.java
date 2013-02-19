/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.templates.AztechRobot;

/**
 *
 * @author MattKahn
 */
public class ManualDrive extends Command {

    public ManualDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(CommandBase.drive);
        System.out.println("Creating ManualDrive");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("Initializing ManualDrive");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
// old        double x = -CommandBase.oi.getDriverController().getTriggers();
        double x = -CommandBase.oi.getDriverController().getLeftX();
        double y = -CommandBase.oi.getDriverController().getLeftY();
// old        double rotation = -CommandBase.oi.getDriverController().getLeftY();
        double rotation = -CommandBase.oi.getDriverController().getRightX();

        
        double operatorRotation = CommandBase.oi.getOperatorJoystick().getX();
        if((operatorRotation * operatorRotation) < 0.025) operatorRotation = 0;
        double operatorRotationSpeed =  0.2 * operatorRotation;
        
        if(x*x < 0.01)
        {
            x = 0;
        }
        if(y*y < 0.01)
        {
            y = 0;
        }
        if(rotation*rotation < 0.01)
        {
            rotation = 0;
        }
        
        x = x * x * (x<0 ? -1.0 : 1.0);
        y = y * y * (y<0 ? -1.0 : 1.0);
        rotation = rotation * rotation * (rotation<0 ? -1.0 : 1.0);

        CommandBase.drive.mecanumDrive_Cartesian(x, y, rotation + operatorRotationSpeed);

        //        System.out.println("x="+x+",    y="+y+",    r="+rotation);
//                try {
//            System.out.print("FR.getX()=" + RobotMap.driveDriveFR.getX());
//            System.out.print(" FL.getX()=" + RobotMap.driveDriveFL.getX());
//            System.out.print(" RR.getX()=" + RobotMap.driveDriveRR.getX());
//            System.out.println(" RL.getX()=" + RobotMap.driveDriveRL.getX());
//        } catch (CANTimeoutException ex) {
            // don't care
//        }

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
