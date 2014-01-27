/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc157.AztechRobot2014.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc157.AztechRobot2014.RangeSensor;
import org.usfirst.frc157.AztechRobot2014.RobotMap;

/**
 *
 * @author mattkahn
 */
public class Sensor extends Subsystem {

    private RangeSensor shortIR;
    private RangeSensor longIR1;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public Sensor() {
      System.out.println("Configuring Sensors");
      // Set up the short range IR sensor  
      System.out.println(" - Short Range IR");
      RangeSensor.CalibrationData shortIRCalData = new RangeSensor.CalibrationData();
      shortIRCalData.closeDistance = 6;
      shortIRCalData.closeVoltage = 3;
      shortIRCalData.farDistance = 8.0;
      shortIRCalData.farVoltage = 0.4;
      shortIR = new RangeSensor(RobotMap.ANALOG_PORT_ShortRangeIR, shortIRCalData);
      
      System.out.println(" - Long Range IR #1");
      RangeSensor.CalibrationData longIRCalData = new RangeSensor.CalibrationData();
      longIRCalData.closeDistance = 60;
      longIRCalData.closeVoltage = 3;
      longIRCalData.farDistance = 355;
      longIRCalData.farVoltage = 1.45;
      longIR1 = new RangeSensor(RobotMap.ANALOG_PORT_LongRangeIR_1, longIRCalData);
          
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public RangeSensor getShortRangeIRSensor()
    {
        return shortIR;
    }
    
    public double getShortRangeIRDistance()
    {
        return shortIR.getRange();
    }

    public RangeSensor getLongRangeIRSensor1()
    {
        return longIR1;
    }
    
    public double getLongRangeIRDistance1()
    {
        return longIR1.getRange();
    }

}
