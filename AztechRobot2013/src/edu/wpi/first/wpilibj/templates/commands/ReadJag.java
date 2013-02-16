/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author MattKahn
 */
public class ReadJag extends Command {
    
    CANJaguar theJag;
    boolean continuous;
    int jagID = -1;
    
    public ReadJag(CANJaguar inJag, boolean inContinuous) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        System.out.println("Setting up ReadJag for - " + inJag.getDescription());
        theJag = inJag;
        System.out.println("Setting up ReadJag for = " + theJag.getDescription());
        
        continuous = inContinuous;
    }

    public ReadJag(int motorID, boolean inContinuous) {
        try {
            theJag = new CANJaguar(motorID);
        } catch (CANTimeoutException ex) {
            System.out.println("Exception while creating JAG for reading");
        }
        continuous = inContinuous;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        String description = "";
        int fwRev = 0;
        double busVoltage = 0.0;
        double outVoltage = 0.0;
        double outCurrent = 0.0;
        double position = 0.0;
        double speed = 0.0;
        double setPoint = 0.0;
        CANJaguar.ControlMode ctlMode = CANJaguar.ControlMode.kCurrent;
        
        if(theJag != null)
        {
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
        } else {
            System.out.print("Read Jag trying to read null jag ");
            if (jagID == -1) {
                System.out.println("- null jag provided");
            } else {
                System.out.println("- jag ID " + jagID + " not instantiated");
            }
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !continuous;
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
