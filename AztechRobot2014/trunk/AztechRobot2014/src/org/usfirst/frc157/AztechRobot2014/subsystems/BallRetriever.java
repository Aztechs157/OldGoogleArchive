// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc157.AztechRobot2014.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import org.usfirst.frc157.AztechRobot2014.RobotMap;
import org.usfirst.frc157.AztechRobot2014.commands.*;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc157.AztechRobot2014.Potentiometer;
import org.usfirst.frc157.AztechRobot2014.Robot;


/**
 *
 */
public class BallRetriever extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private CANJaguar jag;
    private Talon talon;
    
    //Potentiometer angle / voltage constants
    private static double X1 = 45;     //Angle Down - TODO
    private static double X2 = 135;    //Angle Up - TODO
    private static double Y1 = 0.1;      //Voltage Down - TODO
    private static double Y2 = 0.9;      //Voltage Up - TODO
    private static double slope;
    
    //Jag PID
    public static double PID_P = 10;   //TODO
    public static double PID_I = 0;     //TODO
    public static double PID_D = 0;     //TODO
    
    public BallRetriever()
    {             
        slope = (Y2 - Y1)/(X2 - X1);
        //pot = new Potentiometer(Ro+botMap.ANALOG_PORT_BallRetrieverPot, -1, 1, 45, 135);
        boolean failed = true;
        int tries = 0;
        do{
            try {
                jag = new CANJaguar(RobotMap.JAGID_Retriever);
                jag.setVoltageRampRate(0.2);
                jag.configNeutralMode(CANJaguar.NeutralMode.kBrake);
                jag.changeControlMode(CANJaguar.ControlMode.kPosition);
                jag.setPositionReference(CANJaguar.PositionReference.kPotentiometer);
                jag.setPID(PID_P, PID_I, PID_D);    //TODO
                jag.enableControl();
                failed = false;
            } catch (CANTimeoutException ex) {
                  System.out.println("FAIL " + tries + " - Instantiating BallRetriever JAG ");
            }
        } while (failed && (tries++ < RobotMap.m_kMaxCANRetries));
       talon = new Talon(RobotMap.PWM_RetrieverTalon);
       
       updatePID();
    }
    
    public final void updatePID()
    {
       SmartDashboard.putNumber("Ball Retriever Arm PID P", PID_P);
       SmartDashboard.putNumber("Ball Retriever Arm PID I", PID_I);
       SmartDashboard.putNumber("Ball Retriever Arm PID D", PID_D);
       System.out.println("Updated PID: " + PID_P + " " + PID_I + " " + PID_D);
       boolean failed = true;
       int tries = 0;
       do{
           if (null != jag){
                try {
                    jag.setPID(PID_P, PID_I, PID_D);
                    failed = false;
                } catch (CANTimeoutException ex) {
                      System.out.println("FAIL " + tries + " - Failed to update PID constants");
                }
           }
       } while (failed && (tries++ < RobotMap.m_kMaxCANRetries));
    }
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setAngle(double angle)
    {
       System.out.println("Want Angle Set to " + angle);
       boolean failed = true;
       int tries = 0;
        do{
            if (null != jag)
            {
                try {
                    double voltageToSet = convertAngleToVoltage(angle);
                    jag.setX(voltageToSet);
                    SmartDashboard.putNumber("Desired Angle", angle);
                    System.out.println("Angle Set to " + angle);
                    failed = false;
                } catch (CANTimeoutException ex) {
                    System.out.println("FAIL " + tries + " - Failed to set angle from potentiometer");
                }
            }
       } while (failed && (tries++ < RobotMap.m_kMaxCANRetries));
    }
    
    public double getAngle()
    {
        double varToReturn = 0;
        boolean failed = true;
        int tries = 0;
        do{
            if (null != jag){
                try {
                    SmartDashboard.putNumber("Current Pot Angle", convertVoltageToAngle(jag.getPosition()));
                    SmartDashboard.putNumber("Current Pot Voltage", jag.getPosition());
                    SmartDashboard.putNumber("Current Jag Voltage", jag.getX());
                    varToReturn = convertVoltageToAngle(jag.getPosition());
                    failed = false;
                } catch (CANTimeoutException ex) {
                    System.out.println("FAIL " + tries + " - Failed to get angle from Potentiometer");
                }
            }
        } while (failed && (tries++ < RobotMap.m_kMaxCANRetries));
       return varToReturn;
    }
    
    public void spinRoller(double speed)
    {
        talon.set(speed);
        SmartDashboard.putNumber("Set Roller Speed", speed);
    }
    
    public static double convertAngleToVoltage(double angle)
    {
        return slope * (angle - X1) + Y1;
    }
    
    public static double convertVoltageToAngle(double voltage)
    {   
        return (voltage - Y1 + slope*X1) / slope;
    }
}

