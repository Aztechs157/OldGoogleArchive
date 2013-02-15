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
    
    public ScaledCANJaguar(int deviceNumber) throws CANTimeoutException
    {
        super(deviceNumber);
    }
    
    public ScaledCANJaguar(int deviceNumber, ControlMode controlMode) throws CANTimeoutException 
    {
        super(deviceNumber, controlMode);
    }
    
    public void setScalingFactor(double scale)
    {
        scalingFactor = scale;
    }
    
    public void setX(double setPoint) throws CANTimeoutException
    {
//        System.out.println("SX");
        super.setX(setPoint * scalingFactor);
    }

    public void set(double setPoint)
    {
//        System.out.println("S");
        super.set(setPoint * scalingFactor);
    }
    
    public void set(double setPoint, byte b)
    {
//        System.out.println("S..");
        super.set(setPoint * scalingFactor, b);        
    }

    public double get()
    {      
        return  (super.get() / scalingFactor);
    }

    public double getX() throws CANTimeoutException
    {      
        return  super.getX();// / scalingFactor;
    }
}
