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
import edu.wpi.first.wpilibj.Timer;
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
    private static double X1 = 0;     //Angle Down
    private static double X2 = 104;    //Angle Up
    private static double Y1 = 0.14;      //Voltage Down
    private static double Y2 = 0.81;      //Voltage Up
    private static double slope;
    //Jag PID
    public static double PID_P = 300;
    public static double PID_I = 0.2;
    public static double PID_D = 10;
    // positional setpoint voltages
    public final static double Load = convertAngleToVoltage(MoveBallRetriever.RETRIEVER_OUT);
    public final static double Neutral = 0.50;      //CHANGE THIS
    public final static double Eject = convertAngleToVoltage(MoveBallRetriever.RETRIEVER_IN);

    public BallRetriever() {
        System.out.println("BallRetriever");
        slope = (Y2 - Y1) / (X2 - X1);
        //pot = new Potentiometer(Ro+botMap.ANALOG_PORT_BallRetrieverPot, -1, 1, 45, 135);
        boolean failed = true;
        int tries = 0;
        do {
            try {
                jag = new CANJaguar(RobotMap.JAGID_Retriever);
                failed = false;
            } catch (CANTimeoutException ex) {
                System.out.println("FAIL " + tries + " - Instantiating BallRetriever JAG ");
            }
        } while (failed && (tries++ < RobotMap.m_kMaxCANRetries));

        SmartDashboard.putNumber("Ball Retriever Arm PID P", PID_P);
        SmartDashboard.putNumber("Ball Retriever Arm PID I", PID_I);
        SmartDashboard.putNumber("Ball Retriever Arm PID D", PID_D);
        talon = new Talon(RobotMap.PWM_RetrieverTalon);

        setUpJag();
        updatePID();
    }

    public void pokeAtJaguar() {
        System.out.println("pokeAtJaguar");
        try {
            double setpoint = jag.getX();
            System.out.println("Jaguar thinks setpoint is " + setpoint);
            double position = jag.getPosition();
            System.out.println("Jaguar thinks position is " + position);
            double voltage = jag.getOutputVoltage();
            System.out.println("Jaguar output voltage is " + voltage);
            double pid_P = jag.getP();
            double pid_I = jag.getI();
            double pid_D = jag.getD();
            short faults = jag.getFaults();
            System.out.println("PID - " + pid_P + ":" + pid_I + ":" + pid_D + "   F=" + faults);
            if ((faults & CANJaguar.Faults.kBusVoltageFault.value) == CANJaguar.Faults.kBusVoltageFault.value) {
                System.out.println("FAULT - kBusVoltageFault");
            }
            if ((faults & CANJaguar.Faults.kCurrentFault.value) == CANJaguar.Faults.kCurrentFault.value) {
                System.out.println("FAULT - kCurrentFault");
            }
            if ((faults & CANJaguar.Faults.kGateDriverFault.value) == CANJaguar.Faults.kGateDriverFault.value) {
                System.out.println("FAULT - kGateDriverFault");
            }
            if ((faults & CANJaguar.Faults.kTemperatureFault.value) == CANJaguar.Faults.kTemperatureFault.value) {
                System.out.println("FAULT - kTemperatureFault");
            }

        } catch (CANTimeoutException ex) {
            System.out.println("FAIL - Poking Jaguar (don't poke jaguars, they get pissy)");
        }
    }

    public void setUpJag() {
        boolean failed = true;
        int tries = 0;
        do {
            if (null != jag) {
                try {
                    jag.setVoltageRampRate(0.2);
//                jag.configNeutralMode(CANJaguar.NeutralMode.kBrake);
                    jag.configNeutralMode(CANJaguar.NeutralMode.kCoast);
                    jag.changeControlMode(CANJaguar.ControlMode.kPosition);
                    jag.setPositionReference(CANJaguar.PositionReference.kPotentiometer);
                    jag.enableControl();
                    failed = false;
                } catch (CANTimeoutException ex) {
                    System.out.println("FAIL " + tries + " - Instantiating BallRetriever JAG ");
                }
            }
        } while (failed && (tries++ < RobotMap.m_kMaxCANRetries));
    }

    public final void updatePID() {
        PID_P = SmartDashboard.getNumber("Ball Retriever Arm PID P", PID_P);
        PID_I = SmartDashboard.getNumber("Ball Retriever Arm PID I", PID_I);
        PID_D = SmartDashboard.getNumber("Ball Retriever Arm PID D", PID_D);
        System.out.println("Updated PID: " + PID_P + " " + PID_I + " " + PID_D);
        boolean failed = true;
        int tries = 0;
        do {
            if (null != jag) {
                try {
                    jag.setPID(PID_P, PID_I, PID_D);
                    jag.enableControl();
                    failed = false;
                } catch (CANTimeoutException ex) {
                    System.out.println("FAIL " + tries + " - Failed to update PID constants");
                }
            }
        } while (failed && (tries++ < RobotMap.m_kMaxCANRetries));
    }

    public void initDefaultCommand() {
        System.out.println("initDefaultCommand");
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new DisplaySensors());
    }

    public void setVoltageSetpoint(double voltage) {
        System.out.println("setVoltageSetpoint(double voltage)");
        updatePID();
//        setUpJag();
        System.out.println("Want setpoint voltage " + voltage);
        boolean failed = true;
        int tries = 0;
        do {
            if (null != jag) {
                try {
                    jag.setX(voltage);
                    SmartDashboard.putNumber("Desired Pot Voltage", voltage);
                    System.out.println("Desired Pot Voltage Set to " + voltage);
                    failed = false;
                } catch (CANTimeoutException ex) {
                    System.out.println("FAIL " + tries + " - Failed to set setpoint voltage");
                }
            }
        } while (failed && (tries++ < RobotMap.m_kMaxCANRetries));

        /*
         try {
         double setpoint = jag.getX();
         System.out.println("-Jaguar thinks setpoint is " + setpoint);
         double position = jag.getPosition();
         System.out.println("-Jaguar thinks position is " + position);
         } catch (CANTimeoutException ex) {
         System.out.println("FAIL - Poking Jaguar (don't poke jaguars, they get pissy)");
         }
         */
    }

    public void jogSetpointVoltage(double vOffset)
    {
        
        double currentVoltage = getVoltage();
        double newVoltageSetpoint = currentVoltage + vOffset;        
        setVoltageSetpoint(newVoltageSetpoint);
        
        System.out.println("JOG - from " + currentVoltage + " to " + newVoltageSetpoint);
    }
    
    public void setAngle(double angle) {
        System.out.println("setAngle (double angle)");
        updatePID();
//        setUpJag();
        System.out.println("Want Angle Set to " + angle);
        boolean failed = true;
        int tries = 0;
        do {
            if (null != jag) {
                try {
                    double voltageToSet = convertAngleToVoltage(angle);
                    jag.setX(voltageToSet);
                    SmartDashboard.putNumber("Desired Angle", angle);
                    SmartDashboard.putNumber("Desired Voltage", voltageToSet);
                    System.out.println("Angle Set to " + angle);
                    failed = false;
                } catch (CANTimeoutException ex) {
                    System.out.println("FAIL " + tries + " - Failed to set angle from potentiometer");
                }
            }
        } while (failed && (tries++ < RobotMap.m_kMaxCANRetries));
    }

    public double getVoltage() {
        double voltage = 0;
        boolean failed = true;
        int tries = 0;
        do {
            if (null != jag) {
                try {
                    voltage = jag.getPosition();
                    SmartDashboard.putNumber("Current Pot Voltage", voltage);
                    SmartDashboard.putNumber("Desired Angle", convertVoltageToAngle(voltage));
                    failed = false;
                } catch (CANTimeoutException ex) {
                    System.out.println("FAIL " + tries + " - Failed to get voltage from jag Potentiometer");
                }
            }
        } while (failed && (tries++ < RobotMap.m_kMaxCANRetries));
        return voltage;
    }

    public double getAngle() {
        double varToReturn = 0;
        boolean failed = true;
        int tries = 0;
        do {
            if (null != jag) {
                try {
                    double potV = jag.getPosition();
                    SmartDashboard.putNumber("Current Pot Angle", convertVoltageToAngle(potV));
                    SmartDashboard.putNumber("Current Pot Voltage", potV);
                    SmartDashboard.putNumber("Current Jag Voltage", jag.getOutputVoltage());
                    varToReturn = convertVoltageToAngle(potV);
                    failed = false;
                } catch (CANTimeoutException ex) {
                    System.out.println("FAIL " + tries + " - Failed to get angle from Potentiometer");
                }
            }
        } while (failed && (tries++ < RobotMap.m_kMaxCANRetries));
        return varToReturn;
    }

    public void spinRoller(double speed) {
        talon.set(speed);
        SmartDashboard.putNumber("Set Roller Speed", speed);
    }

    public static double convertAngleToVoltage(double angle) {
        return slope * (angle - X1) + Y1;
    }

    public static double convertVoltageToAngle(double voltage) {
        return (voltage - Y1 + slope * X1) / slope;
    }
    /*
     public int getSpinRollerDirection() {
     int rollerDirection;
     if (jag.getX() == MoveBallRetriever.RETRIEVER_OUT) {
     rollerDirection = SpinRoller.ROLLER_IN;
     } else if (jag.getX() == MoveBallRetriever.RETRIEVER_IN) {
     rollerDirection = SpinRoller.ROLLER_OUT;
     } else {
     rollerDirection = SpinRoller.ROLLER_STOP;
     }
     System.out.println(rollerDirection);
     return rollerDirection;
     }*/
}
