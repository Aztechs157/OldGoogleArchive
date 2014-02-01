/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc157.AztechRobot2014;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.PIDSource;

/**
 *
 * @author mattkahn
 */
public abstract class RangeSensor extends AnalogChannel implements PIDSource {
   public RangeSensor(int channel) {
        super(channel);

    }

    public RangeSensor(int moduleNumber, int channel) {
        super(moduleNumber, channel);

    }

    public abstract double pidGet();

    public abstract double getRange();  // returns range in cm

    public abstract double getRange(int samples);  // returns range in cm
}
