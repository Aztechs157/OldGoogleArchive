// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.usfirst.frc157.BuiltRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc157.BuiltRobot.Robot;
import org.usfirst.frc157.BuiltRobot.subsystems.SimpleMotor;
/**
 *
 */
public class  MotorGo extends Command {
    private double quasiTime = 0;
    public MotorGo() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.simpleMotor);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        System.out.println("MotorGo Created");
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("About to turn on motor...");
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//        quasiTime += 0.01;
//        Robot.simpleMotor.setSpeed(Math.cos(quasiTime));
//        System.out.print("GX" + Robot.oi.getFirstController().getGameX());
//        System.out.print(" GY" + Robot.oi.getFirstController().getGameY());   //MS Y
//        System.out.print(" LX" + Robot.oi.getFirstController().getLeftX());   //MS X
//        System.out.print(" LY" + Robot.oi.getFirstController().getLeftY());
//        System.out.print(" RX" + Robot.oi.getFirstController().getRightX());
//        System.out.print(" RY" + Robot.oi.getFirstController().getRightY());
//        System.out.print(" TR" + Robot.oi.getFirstController().getTriggers());
//        System.out.println();
        //Robot.simpleMotor.setSpeed(Math.cos(quasiTime));
//    Robot.simpleMotor.setSpeed(Robot.oi.getFirstController().getLeftX());
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
