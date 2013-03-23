/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matt
 */

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.can.*;

public class JagManagement {
    
    public static void enumerateJaguars(boolean showMissing) {
        // enumerate jaguars
        System.out.println("- Enumerating Jaguars -");
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
                    System.out.println("Exception while reading JAG info for motor " + jagID);
                }
                System.out.print("MOTOR ID " + jagID + ":");
                System.out.print("desc=<" + description + "> ");
                System.out.print("fwRev=<" + fwRev + "> ");
                System.out.print("busVoltage=<" + busVoltage + "> ");
                System.out.print("outVoltage=<" + outVoltage + "> ");
                System.out.print("outCurrent=<" + outCurrent + "> ");
                System.out.print("setPoint=<" + setPoint + "> ");
                System.out.print("position=<" + position + "> ");
                System.out.print("speed=<" + speed + "> ");
                System.out.println("");
            } else {
                if (showMissing) {
                    System.out.println("MOTOR ID " + jagID + " Not Found");
                }
            }
        }
    }

    public static void sendSetIDCANMessage(byte newID) {

        CANJaguar testMotor = null;
        boolean motorFound = true;
        try {
            testMotor = new CANJaguar(newID);
        } catch (CANTimeoutException ex) {
            motorFound = false;
        }

        if (motorFound) {
            System.out.println("There is already a motor on ID " + newID);
        } else {
            System.out.println("Push the paperclip now! to set the ID to " + newID);
            try {
                byte[] cmd = {newID};
                JaguarCANDriver.sendMessage(JaguarCANProtocol.CAN_MSGID_API_DEVASSIGN, cmd, cmd.length);
            } catch (CANTimeoutException ex) {
                System.out.println("Exception while configuring speed");
            }
            Timer.delay(5);
            System.out.println("Times up for ID setting");
        }
    }
}
