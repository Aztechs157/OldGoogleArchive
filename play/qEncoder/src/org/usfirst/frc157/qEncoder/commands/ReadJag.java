/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc157.qEncoder.commands;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author MattKahn
 */
public class ReadJag extends Command {
    
    CANJaguar theJag;
    
    public ReadJag(CANJaguar inJag) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        System.out.println("Setting up ReadJag for - " + inJag.getDescription());
        theJag = inJag;
        System.out.println("Setting up ReadJag for = " + theJag.getDescription());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        String description = "";
        int fwRev = 0;
        double busVoltage = 0.0;
        double outVoltage = 0.0;
        double outCurrent = 0.0;
        double position = 0.0;
        double speed = 0.0;
        double setPoint = 0.0;
        CANJaguar.ControlMode ctlMode = CANJaguar.ControlMode.kPercentVbus;
        
        try{
            description = theJag.getDescription();
            fwRev = theJag.getFirmwareVersion();
            busVoltage = theJag.getBusVoltage();
            outCurrent = theJag.getOutputCurrent();
            outVoltage = theJag.getOutputVoltage();
            position = theJag.getPosition();
            speed = theJag.getSpeed();
            setPoint = theJag.getX();
            ctlMode = theJag.getControlMode();
        } catch (CANTimeoutException ex) {
            System.out.println("Exception while reading JAG info");
        }
        System.out.print("desc=<" + description + "> ");
        System.out.print("fwRev=<" + fwRev + "> ");
        System.out.print("busVoltage=<" + busVoltage + "> ");
        System.out.print("outVoltage=<" + outVoltage + "> ");
        System.out.print("outCurrent=<" + outCurrent + "> ");        
        System.out.print("setPoint=<" + setPoint + "> ");
        System.out.print("position=<" + position + "> ");
        System.out.print("speed=<" + speed + "> ");
        System.out.print("ctlMode=<" + ControlMode2String(ctlMode) + ">");
        System.out.println("");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

    String ControlMode2String(CANJaguar.ControlMode mode) {
        String theString = "UNKNOWN";
        switch (mode.value) {
            case 0:
                theString = "PercentVBus";
                break;
            case 1:
                theString = "Current";
                break;
            case 2:
                theString = "Speed";
                break;
            case 3:
                theString = "Position";
                break;
            case 4:
                theString = "Voltage";
                break;
            default:
                theString = "UNKNOWN";
                break;
        }
        return theString;
    }

}
