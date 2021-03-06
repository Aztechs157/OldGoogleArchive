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
        System.out.println("Starting ManualDrive");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

//        // Driver Control    
//        double x = -CommandBase.oi.getDriverController().getLeftX();
//        double y = -CommandBase.oi.getDriverController().getLeftY();
//        double rotation = -CommandBase.oi.getDriverController().getRightX();
//        double strafe = CommandBase.oi.getDriverController().getTriggers();
//        
//        double minSpeedLimit = 1.0;//0.75;//0.5;
//        double speedLimit = minSpeedLimit + 0;
//
//        // if the left stick is pressed down
//        if (CommandBase.oi.getDriverController().getButtonLeftTop())
//        {
//            speedLimit = 1.0;           // set top speed
//            x = 0.5 * x;                // reduce the strafeing rate
//            rotation = 0.5 * rotation;  // reduce the turning rate
//                                        // but leave button strafe alone
//        }
//        
//        if (speedLimit > 1.0) speedLimit = 1.0;        
//                
//        if(x*x < 0.01)
//        {
//            x = 0;
//        }
//        if(y*y < 0.01)
//        {
//            y = 0;
//        }
//        if(strafe*strafe < 0.1)
//        {
//            strafe = 0;
//        }
//        if(rotation*rotation < 0.01)
//        {
//            rotation = 0;
//        }
//        
//        x = strafe + speedLimit * (x * x * (x<0 ? -1.0 : 1.0));
//        y = speedLimit * (y * y * (y<0 ? -1.0 : 1.0));
//        rotation = speedLimit * (rotation * rotation * (rotation<0 ? -1.0 : 1.0));
//
//        // Operator Control
//        double operatorSpeed = 0.4;
//        double operatorRotation = CommandBase.oi.getOperatorJoystick().getX();
//        double operatorForeAft = CommandBase.oi.getOperatorJoystick().getY();
//        
//        if((operatorRotation * operatorRotation) < 0.025) operatorRotation = 0;
//        double operatorRotationSpeed =  operatorSpeed * operatorRotation;
//
//        if((operatorForeAft * operatorForeAft) < 0.025) operatorForeAft = 0;
//        double operatorForeAftSpeed = operatorSpeed * operatorForeAft;
//        
        double left = CommandBase.oi.getDriverController().getLeftY();
        double right = CommandBase.oi.getDriverController().getRightY();
        double scLeft = (left * left) * (left < 0 ? -1 : 1);
        double scRight = (right * right) * (right < 0 ? -1 : 1);
        CommandBase.drive.tankDrive(-scLeft, scRight);
//        CommandBase.drive.mecanumDrive_Cartesian(x, y + operatorForeAftSpeed, rotation + operatorRotationSpeed);

//        System.out.println("x="+x+",    y="+y+",    r="+rotation+",    or="+operatorRotationSpeed);
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
        System.out.println("Stopping Manual Drive");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
