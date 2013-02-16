/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.AztechRobot;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.ManualAim;

/**
 *
 * @author MattKahn
 */
public class Shooter extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public static CANJaguar shooterElevation;
    public static Talon shooterFirstStage;   // PWM
    public static Talon shooterSecondStage;
    // Pneumatics
    public static Compressor compressor;
    Solenoid loaderRetract;
    Solenoid loaderExtend;
    Solenoid shooterRetract;
    Solenoid shooterExtend;
    
    Relay redRelay;
    Relay blueRelay;
    Relay greenRelay;

        public static class Color {

        public final int value;
        // Drive Mode enumeration constants
        protected static final int m_kBlack = 0;
        protected static final int m_kRed = 1;
        protected static final int m_kWhite = 2;
        protected static final int m_kBlue = 3;
        
        public static final Color Black  = new Color(m_kBlack);
        public static final Color Red    = new Color(m_kRed);
        public static final Color White  = new Color(m_kWhite);
        public static final Color Blue   = new Color(m_kBlue);

        private Color(int Color) {
            this.value = Color;
        }
    }
    
    public void init() {
        System.out.println("== Initializing Shooter ==");
        compressor = new Compressor(RobotMap.PressureSwitchGPIOPort, RobotMap.CompressorRelayChannel);
        compressor.start();

        loaderRetract = new Solenoid(RobotMap.LoaderRetractPort);
        loaderExtend = new Solenoid(RobotMap.LoaderExtendPort);
        shooterRetract = new Solenoid(RobotMap.ShooterRetractPort);
        shooterExtend = new Solenoid(RobotMap.ShooterExtendPort);

        redRelay = new Relay(RobotMap.RedLightPort);
        greenRelay = new Relay(RobotMap.WhiteLightPort);
        blueRelay = new Relay(RobotMap.BlueLightPort);
        
        try {
            shooterElevation = new CANJaguar(RobotMap.ShooterElevationMotorID);
            shooterElevation.changeControlMode(CANJaguar.ControlMode.kPosition);
            shooterElevation.setPositionReference(CANJaguar.PositionReference.kPotentiometer);
            shooterElevation.configNeutralMode(CANJaguar.NeutralMode.kBrake);
            shooterElevation.setPID(.1, 0, 0);  //TODO: Set PID Constants for elevation conrol
            shooterElevation.enableControl();
        } catch (CANTimeoutException ex) {
            System.out.println("FAIL - Instantiating Shoter Elevation JAG " + RobotMap.ShooterElevationMotorID);
            ex.printStackTrace();
        }

        shooterFirstStage = new Talon(RobotMap.ShooterFirstStageDrivePWMPort);
        shooterSecondStage = new Talon(RobotMap.ShooterSecondStageDrivePWMPort);

        // set the shooter to a known state
        reset();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ManualAim());
    }

    public void spinLaunchWheels(double power) {
        shooterFirstStage.set(power);
        shooterSecondStage.set(power);
    }

    public void stopLaunchWheels() {
        spinLaunchWheels(0);
    }

    protected void reset() {
        retractLoader();
        retractShooter();
        stopLaunchWheels();
    }

    public void extendLoader() {
        setLoaderExtended(true);
    }

    public void retractLoader() {
        setLoaderExtended(false);
    }

    public void extendShooter() {
        setShooterExtended(true);
    }

    public void retractShooter() {
        setShooterExtended(false);
    }

    private void setLoaderExtended(boolean extend) {
        if (extend) {
            if ((loaderRetract != null) && (loaderExtend != null)) {
                loaderExtend.set(true);
                loaderRetract.set(false);
            } else {
                if ((loaderRetract != null) && (loaderExtend != null)) {
                    loaderRetract.set(true);
                    loaderExtend.set(false);
                }
            }
        }
    }

    public void adjustShooterElevation(double delta) {
        setShooterElevation(getShooterElevation() + delta);
    }

    public void setShooterElevation(double elevation) {
        if (null != shooterElevation) {
            try {
                shooterElevation.setX(elevation);
            } catch (edu.wpi.first.wpilibj.can.CANTimeoutException e) {
                System.out.println("CAN Timeout setting shooter elevation (motor " + RobotMap.ShooterElevationMotorID + ")");
            }
        }
    }
    private static double lastValidResult = 0;

    public double getShooterElevation() {
        double result = lastValidResult;
        if (null != shooterElevation) {
            try {
                result = shooterElevation.getX();
            } catch (edu.wpi.first.wpilibj.can.CANTimeoutException e) {
                System.out.println("CAN Timeout reading shooter elevation (motor " + RobotMap.ShooterElevationMotorID + ")");
            }
        }
        return result;
    }

    private void setShooterExtended(boolean extend) {
        if (extend) {
            if ((shooterRetract != null) && (shooterExtend != null)) {
                shooterExtend.set(true);
                shooterRetract.set(false);
            } else {
                if ((shooterRetract != null) && (shooterExtend != null)) {
                    shooterRetract.set(true);
                    shooterExtend.set(false);
                }
            }
        }
    }
    
    public void setColor(Color color)
    {
        switch (color.value) {
            case Color.m_kBlack:
                redRelay.set(Relay.Value.kOff);
                greenRelay.set(Relay.Value.kOff);
                blueRelay.set(Relay.Value.kOff);
                break;
            case Color.m_kRed:
                redRelay.set(Relay.Value.kOn);
                greenRelay.set(Relay.Value.kOff);
                blueRelay.set(Relay.Value.kOff);
                break;
            case Color.m_kWhite:
                redRelay.set(Relay.Value.kOff);
                greenRelay.set(Relay.Value.kOn);
                blueRelay.set(Relay.Value.kOff);
                break;
            case Color.m_kBlue:
                redRelay.set(Relay.Value.kOff);
                greenRelay.set(Relay.Value.kOff);
                blueRelay.set(Relay.Value.kOn);
                break;
        }
    }
}
