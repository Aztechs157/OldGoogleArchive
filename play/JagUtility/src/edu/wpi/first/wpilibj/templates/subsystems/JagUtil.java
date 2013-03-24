package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.can.JaguarCANDriver;
import edu.wpi.first.wpilibj.can.JaguarCANProtocol;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class JagUtil extends Subsystem {

    boolean useSmartDashboard = false;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void enumerateJaguars(boolean showMissing) {
        // enumerate jaguars
        String enumeration = "- Jaguars Found -\n";
        System.out.println(enumeration);
        if (useSmartDashboard) {
            SmartDashboard.putString("Enumerate", enumeration);
        }
        for (int jagID = 1; jagID <= 63; jagID++) {
            CANJaguar testMotor = null;
            boolean motorFound = true;
            try {
                testMotor = new CANJaguar(jagID);
            } catch (CANTimeoutException ex) {
                motorFound = false;
            }
            if (motorFound) {
                String description = "";
                int fwRev = 0;
                double busVoltage = 0.0;
                double outVoltage = 0.0;
                double outCurrent = 0.0;
                double position = 0.0;
                double speed = 0.0;
                double setPoint = 0.0;

                try {
                    description = testMotor.getDescription();
                    fwRev = testMotor.getFirmwareVersion();
                    busVoltage = testMotor.getBusVoltage();
                    outCurrent = testMotor.getOutputCurrent();
                    outVoltage = testMotor.getOutputVoltage();
                    position = testMotor.getPosition();
                    speed = testMotor.getSpeed();
                    setPoint = testMotor.getX();
                } catch (CANTimeoutException ex) {
                    String message = "Exception while reading JAG info for motor " + jagID;
                    System.out.println(message);
                    SmartDashboard.putString("ERROR", message);
                }

                String entry = "MOTOR ID " + jagID + ":";
                entry += "desc=<" + description + "> ";
                entry += "fwRev=<" + fwRev + "> ";
                entry += "busVoltage=<" + busVoltage + "> ";
                entry += "outVoltage=<" + outVoltage + "> ";
                entry += "outCurrent=<" + outCurrent + "> ";
                entry += "setPoint=<" + setPoint + "> ";
                entry += "position=<" + position + "> ";
                entry += "speed=<" + speed + "> ";
                entry += "\n";

                System.out.println(entry);
                enumeration += entry;
                if (useSmartDashboard) {
                    SmartDashboard.putString("Enumerate", enumeration);
                }

            } else {
                if (showMissing) {
                    System.out.println("MOTOR ID " + jagID + " Not Found");
                }
            }
        }
    }

    public void sendSetIDCANMessage(byte newID) {

        String message;
        
        CANJaguar testMotor = null;
        boolean motorFound = true;
        try {
            testMotor = new CANJaguar(newID);
        } catch (CANTimeoutException ex) {
            motorFound = false;
        }

        if (motorFound) {
            message = "There is already a Jaguar with ID " + newID;
            System.out.println(message);
            if (useSmartDashboard) SmartDashboard.putString("Set ID", message);
        } else {
            message = "Push the Jag Button _NOW_ to set the ID to " + newID;
            System.out.println(message);
            if (useSmartDashboard) SmartDashboard.putString("Set ID", message);
            try {
                byte[] cmd = {newID};
                JaguarCANDriver.sendMessage(JaguarCANProtocol.CAN_MSGID_API_DEVASSIGN, cmd, cmd.length);
            } catch (CANTimeoutException ex) {
                    String exMessage = "Exception while setting ID - Try Again" + newID;
                    System.out.println(exMessage);
                    SmartDashboard.putString("ERROR", exMessage);

                System.out.println();
            }
            Timer.delay(5);
            message = "Time is up for setting ID " + newID;
            System.out.println(message);
            if (useSmartDashboard) SmartDashboard.putString("Set ID", message);
        }
    }
}
