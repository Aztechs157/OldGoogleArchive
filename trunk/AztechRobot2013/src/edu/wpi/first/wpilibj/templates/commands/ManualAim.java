/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.templates.AztechRobot;
import edu.wpi.first.wpilibj.templates.subsystems.Shooter;

/**
 *
 * @author MattKahn
 */
public class ManualAim extends CommandBase {
    
    public ManualAim() {
        // Use requires() here to declare subsystem dependencies
        requires(AztechRobot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("MANUAL AIM ===================================================");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double rotation;
        double elevation;
 
        rotation = 0.2 * oi.getOperatorJoystick().getX();
        elevation = oi.getOperatorJoystick().getY();

//        AztechRobot.drive.mecanumDrive_Cartesian(0, 0, rotation);
//        AztechRobot.shooter.adjustShooterElevation(elevation);
//        System.out.println("Elevation = " + AztechRobot.shooter.getShooterElevation() + " + " + elevation + "  -- Az  += " + rotation);
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
