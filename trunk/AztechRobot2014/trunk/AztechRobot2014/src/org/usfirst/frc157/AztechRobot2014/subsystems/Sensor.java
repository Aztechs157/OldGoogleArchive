/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc157.AztechRobot2014.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc157.AztechRobot2014.InfraredRangeSensor;
import org.usfirst.frc157.AztechRobot2014.RangeSensor;
import org.usfirst.frc157.AztechRobot2014.RobotMap;
import org.usfirst.frc157.AztechRobot2014.UltrasonicRangeSensor;

/**
 *
 * @author mattkahn
 */
public class Sensor extends Subsystem {

    private InfraredRangeSensor shortIR;
    private InfraredRangeSensor longIR1;
    private InfraredRangeSensor longIR2;
    private UltrasonicRangeSensor ultrasonic;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public Sensor() {
        System.out.println("Configuring Sensors");
        // Set up the short range IR sensor  
        System.out.println(" - Short Range IR");
        InfraredRangeSensor.CalibrationData shortIRCalData = new InfraredRangeSensor.CalibrationData();
        shortIRCalData.closeDistance = 6;
        shortIRCalData.closeVoltage = 3;
        shortIRCalData.farDistance = 94.0;
        shortIRCalData.farVoltage = 0.31;
        shortIR = new InfraredRangeSensor(RobotMap.ANALOG_PORT_ShortRangeIR, shortIRCalData);

        System.out.println(" - Long Range IR #1");
        InfraredRangeSensor.CalibrationData longIRCalData = new InfraredRangeSensor.CalibrationData();
        longIRCalData.closeDistance = 60;
        longIRCalData.closeVoltage = 3;
        longIRCalData.farDistance = 355;
        longIRCalData.farVoltage = 1.45;
        longIR1 = new InfraredRangeSensor(RobotMap.ANALOG_PORT_LongRangeIR_1, longIRCalData);

        System.out.println(" - Long Range IR #2");
        // uses same cal data as #1
        longIRCalData.closeDistance = 60;
        longIRCalData.closeVoltage = 3;
        longIRCalData.farDistance = 355;
        longIRCalData.farVoltage = 1.45;
        longIR2 = new InfraredRangeSensor(RobotMap.ANALOG_PORT_LongRangeIR_2, longIRCalData);

        System.out.println(" - Ultrasonic #1");
        UltrasonicRangeSensor.CalibrationData ultrasonicCalData = new UltrasonicRangeSensor.CalibrationData();
        ultrasonicCalData.voltsPerInch = 0.0098;
        ultrasonicCalData.minRangeInches = 6.0;
        ultrasonicCalData.maxRangeInches = 254;
        ultrasonic = new UltrasonicRangeSensor(RobotMap.ANALOG_PORT_Ultrasonic_1, ultrasonicCalData);

    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public RangeSensor getShortRangeIRSensor() {
        return shortIR;
    }

    public double getShortRangeIRDistance() {
        return shortIR.getRange();
    }

    public RangeSensor getLongRangeIRSensor1() {
        return longIR1;
    }
    public RangeSensor getLongRangeIRSensor2() {
        return longIR2;
    }

    public double getLongRangeIRDistance1() {
        return longIR1.getRange();
    }
    public double getLongRangeIRDistance2() {
        return longIR2.getRange();
    }

   public RangeSensor getUltrasonicSensor1() {
       return ultrasonic;
   }
    public double getUltrasonicDistance1() {
        return ultrasonic.getRange();
    }
}
