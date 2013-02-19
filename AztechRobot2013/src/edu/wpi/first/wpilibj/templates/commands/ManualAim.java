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
        double elevationStick = oi.getOperatorJoystick().getY();
        
        if((elevationStick * elevationStick) < 0.025) elevationStick = 0;
 
        double elevationDelta = elevationStick * 0.05;
        commandElevation -= elevationDelta;

//        System.out.println("ManualAim - elDelta=" + elevationDelta + "   comEl=" + commandElevation + "   rotSPeed=" + rotationSpeed);
        
//        CommandBase.shooter.setShooterElevation(commandElevation);
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
    
    public static void setManualElevation(double elevation)  //degrees
    {
        commandElevation = elevation;
    }
}
