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
    
    private static double commandElevation;
    
    public ManualAim() {
        // Use requires() here to declare subsystem dependencies
        requires(CommandBase.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("MANUAL AIM ===================================================");
        commandElevation = CommandBase.shooter.getShooterElevation();
        CommandBase.shooter.enableElevation(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double rotationStick = oi.getOperatorJoystick().getX();
        double elevationStick = oi.getOperatorJoystick().getY();
        
        if((rotationStick * rotationStick) < 0.025) rotationStick = 0;
        if((elevationStick * elevationStick) < 0.025) elevationStick = 0;
 
        double elevationDelta = elevationStick * 0.1;
        commandElevation -= elevationDelta;

        double rotationSpeed =  0.2 * rotationStick;

//        System.out.println("ManualAim - elDelta=" + elevationDelta + "   comEl=" + commandElevation + "   rotSPeed=" + rotationSpeed);
        
//        CommandBase.shooter.setShooterElevation(commandElevation);
        CommandBase.drive.mecanumDrive_Cartesian(0, 0, rotationSpeed);
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
