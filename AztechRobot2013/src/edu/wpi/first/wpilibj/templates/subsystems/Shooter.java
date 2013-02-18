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

    public static final double loadingAngle = 25;
    public static final double climbAngle = 0;
    public static final double basicShotAngle = 45;
    //             Sensor   Angle
    // Bottom Stop 0.856    0.00
    // Top Stop    0.652    50.9
    // Sensor values per degree .004 
    public static final double zeroSensorReading = 0.482;
    public static final double maxSensorReading = 0.697;
    public static final double zeroElevationAngle = 0.9;
    public static final double maxElevationAngle = 51.2;
    public static final double sensorPerDegree = ((maxSensorReading - zeroSensorReading) / (maxElevationAngle - zeroElevationAngle));
    // Up PID Constants
    public static final double upPhi = -5000.0;
    public static final double upPlo = -1000.0;
    public static final double upI = -3.0;
    public static final double upD = 5.0;
    // Down PID Constants
    public static final double dnPhi = -1400.0;
    public static final double dnPlo = -800.0;
    public static final double dnI = -1.0;
    public static final double dnD = 0;
    // PID Constant Domain Boundary
    public static final double domainBound = 40.0; //degrees
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
        public static final Color Black = new Color(m_kBlack);
        public static final Color Red = new Color(m_kRed);
        public static final Color White = new Color(m_kWhite);
        public static final Color Blue = new Color(m_kBlue);

        private Color(int Color) {
            this.value = Color;
        }
    }

    public Shooter() {
        init();
    }

    public final void init() {
        System.out.println("== Initializing Shooter ==");
        compressor = new Compressor(RobotMap.PressureSwitchGPIOPort, RobotMap.CompressorRelayChannel);
        compressor.start();

        loaderRetract = new Solenoid(RobotMap.LoaderRetractPort);
        loaderExtend = new Solenoid(RobotMap.LoaderExtendPort);
        shooterRetract = new Solenoid(RobotMap.ShooterRetractPort);
        shooterExtend = new Solenoid(RobotMap.ShooterExtendPort);

        shooterExtend.set(false);
        shooterRetract.set(false);
        shooterRetract.set(false);
        shooterExtend.set(false);


        redRelay = new Relay(RobotMap.RedLightPort);
        greenRelay = new Relay(RobotMap.WhiteLightPort);
        blueRelay = new Relay(RobotMap.BlueLightPort);

        int tries = 0;
        boolean failed = false;
        do {
            try {
                shooterElevation = new CANJaguar(RobotMap.ShooterElevationMotorID);
                shooterElevation.changeControlMode(CANJaguar.ControlMode.kPosition);
                shooterElevation.setPositionReference(CANJaguar.PositionReference.kPotentiometer);
                shooterElevation.configNeutralMode(CANJaguar.NeutralMode.kBrake);
                shooterElevation.setPID(upPlo, upI, upD);
                shooterElevation.enableControl();
            } catch (CANTimeoutException ex) {
                failed = true;
                System.out.println("FAIL " + tries + " - Instantiating Shoter Elevation JAG " + RobotMap.ShooterElevationMotorID);
                ex.printStackTrace();
            }
        } while (failed && (tries++ < RobotMap.m_kMaxCANRetries));

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
        if(power == 0)
        {
            compressor.start();
        }
        else
        {
            compressor.stop();
        }
        shooterFirstStage.set(power);
        shooterSecondStage.set(power);
    }

    protected void reset() {
        retractLoader();
        retractShooter();
        spinLaunchWheels(0);
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
            }
        } else {
            if ((loaderRetract != null) && (loaderExtend != null)) {
                loaderRetract.set(true);
                loaderExtend.set(false);
            }
        }
    }

    public void enableElevation(boolean enable) {
        try {
            if (enable) {
                shooterElevation.enableControl();
            } else {
                shooterElevation.disableControl();
            }
        } catch (CANTimeoutException ex) {
            System.out.println("FAIL - " + (enable ? "Enabling" : "Disabling") + " Shoter Elevation Control " + RobotMap.ShooterElevationMotorID);
            ex.printStackTrace();
        }
    }

    private void setPIDConstants(double targetElevation) {
        double currentElevation = getShooterElevation();
        int tries = 0;
        boolean failed = false;
        do {
            try {
                shooterElevation.changeControlMode(CANJaguar.ControlMode.kPosition);
                shooterElevation.setPositionReference(CANJaguar.PositionReference.kPotentiometer);
                shooterElevation.configNeutralMode(CANJaguar.NeutralMode.kBrake);
                if ((targetElevation - currentElevation) > 0) {
                    if (targetElevation > domainBound) {
                        shooterElevation.setPID(upPhi, upI, upD);
                    } else {
                        shooterElevation.setPID(upPlo, upI, upD);
                    }
                } else {
                    if (targetElevation > domainBound) {
                        shooterElevation.setPID(dnPhi, dnI, dnD);
                    } else {
                        shooterElevation.setPID(dnPlo, dnI, dnD);
                    }
                }
                shooterElevation.enableControl();
            } catch (CANTimeoutException ex) {
                failed = true;
                System.out.println("Timeout " + tries + " - Setting Elevation Constants on JAG " + RobotMap.ShooterElevationMotorID);
//            ex.printStackTrace();
            }
        } while (failed && (tries++ < RobotMap.m_kMaxCANRetries));


    }

    public void adjustShooterElevation(double delta) {
        setShooterElevation(getShooterElevation() + delta);
    }

    public void setShooterElevation(double elevationDegrees) {
        setPIDConstants(elevationDegrees);
        double elevation = (elevationDegrees * sensorPerDegree) + zeroSensorReading;
        if (null != shooterElevation) {
            int tries = 0;
            boolean failed = false;
            do {
                try {
                    shooterElevation.setX(elevation);
                } catch (edu.wpi.first.wpilibj.can.CANTimeoutException e) {
                    failed = true;
                    System.out.println("CAN Timeout " + tries + " setting shooter elevation (motor " + RobotMap.ShooterElevationMotorID + ")");
                }
            } while (failed && (tries++ < RobotMap.m_kMaxCANRetries));

        }

    }
    private static double lastValidResult = 0;

    public double getShooterElevation() {
        double result = lastValidResult;
        if (null != shooterElevation) {
            try {
                result = (shooterElevation.getPosition() - zeroSensorReading) / sensorPerDegree;
            } catch (edu.wpi.first.wpilibj.can.CANTimeoutException e) {
                System.out.println("CAN Timeout reading shooter elevation (motor " + RobotMap.ShooterElevationMotorID + ")");
            }
        }
//        System.out.println("Read Elevation at " + result + " degrees");
        return result;
    }

    private void setShooterExtended(boolean extend) {
        if (extend) {
            if ((shooterRetract != null) && (shooterExtend != null)) {
                shooterExtend.set(true);
                shooterRetract.set(false);
            }
        } else {
            if ((shooterRetract != null) && (shooterExtend != null)) {
                shooterRetract.set(true);
                shooterExtend.set(false);
            }
        }
    }

    public void setColor(Color color) {
        switch (color.value) {
            case Color.m_kBlack:
                System.out.println("No Lighs");
                redRelay.set(Relay.Value.kOff);
                greenRelay.set(Relay.Value.kOff);
                blueRelay.set(Relay.Value.kOff);
                break;
            case Color.m_kRed:
                System.out.println("Red Lights");
                redRelay.set(Relay.Value.kOn);
                greenRelay.set(Relay.Value.kOff);
                blueRelay.set(Relay.Value.kOff);
                break;
            case Color.m_kWhite:
                System.out.println("White Lights");
                redRelay.set(Relay.Value.kOff);
                greenRelay.set(Relay.Value.kOn);
                blueRelay.set(Relay.Value.kOff);
                break;
            case Color.m_kBlue:
                System.out.println("Blue Lights");
                redRelay.set(Relay.Value.kOff);
                greenRelay.set(Relay.Value.kOff);
                blueRelay.set(Relay.Value.kOn);
                break;
        }
    }
}
