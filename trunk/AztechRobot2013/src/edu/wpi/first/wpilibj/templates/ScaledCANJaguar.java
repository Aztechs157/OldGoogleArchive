/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 *
 * @author MattKahn
 */
public class ScaledCANJaguar extends CANJaguar {

    private double scalingFactor = 1;

    public ScaledCANJaguar(int deviceNumber) throws CANTimeoutException {
        super(deviceNumber);
    }

    public ScaledCANJaguar(int deviceNumber, ControlMode controlMode) throws CANTimeoutException {
        super(deviceNumber, controlMode);
    }

    public void setScalingFactor(double scale) {
        scalingFactor = scale;
    }

    public void setX(double setPoint) throws CANTimeoutException {
//        System.out.println("SX");
        super.setX(setPoint * scalingFactor);
    }

    public void set(double setPoint) {
//        System.out.println("S");
        super.set(setPoint * scalingFactor);
    }

    public void set(double setPoint, byte b) {
//        System.out.println("S..");
        super.set(setPoint * scalingFactor, b);
    }

    public double get() {
        return (super.get() / scalingFactor);
    }

    public double getX() throws CANTimeoutException {
        return super.getX();// / scalingFactor;
    }
    ControlMode controlMode;

    public void changeControlMode(ControlMode newControlMode) throws CANTimeoutException {
        controlMode = newControlMode;
        super.changeControlMode(controlMode);
    }

    public ControlMode getCommandedControlMode()
    {
        return controlMode;
    }
    
    public boolean isInCommandedControlMode() {
        ControlMode currentControlMode = CANJaguar.ControlMode.kCurrent;;
        int tries = 0;
        boolean failed = false;
        do {
            try {
                currentControlMode = super.getControlMode();
            } catch (CANTimeoutException ex) {
                failed = true;
            }
        } while (failed && (tries++ < RobotMap.m_kMaxCANRetries));

        if (tries >= RobotMap.m_kMaxCANRetries) {
            return false;  // can't actually tell, so assume bad
        }

        return (currentControlMode == controlMode);
//      return (controlMode
    }
}
