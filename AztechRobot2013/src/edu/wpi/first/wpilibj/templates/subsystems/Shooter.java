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
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.ManualAim;

/**
 *
 * @author MattKahn
 */
public class Shooter extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public static final double lowLimitAngle = 0;
    public static final double highLimitAngle = 50;
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
    public static final double upD = 5.0;   // should use LARGE negative constants here
    // Down PID Constants
    public static final double dnPhi = -1400.0;
    public static final double dnPlo = -800.0;
    public static final double dnI = -1.0;
    public static final double dnD = 0;   // should use LARGE negative constants here (or 0)
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
        if (compressor == null) {
            compressor = new Compressor(RobotMap.PressureSwitchGPIOPort, RobotMap.CompressorRelayChannel);
        }
        compressor.start();

        if (loaderRetract == null) {
            loaderRetract = new Solenoid(RobotMap.LoaderRetractPort);
        }
        if (loaderExtend == null) {
            loaderExtend = new Solenoid(RobotMap.LoaderExtendPort);
        }
        if (shooterRetract == null) {
            shooterRetract = new Solenoid(RobotMap.ShooterRetractPort);
        }
        if (shooterExtend == null) {
            shooterExtend = new Solenoid(RobotMap.ShooterExtendPort);
        }
        shooterExtend.set(false);
        shooterRetract.set(false);
        shooterRetract.set(false);
        shooterExtend.set(false);


        if (redRelay == null) {
            redRelay = new Relay(RobotMap.RedLightPort);
        }
        if (greenRelay == null) {
            greenRelay = new Relay(RobotMap.WhiteLightPort);
        }
        if (blueRelay == null) {
            blueRelay = new Relay(RobotMap.BlueLightPort);
        }
        int tries = 0;
        boolean failed = false;
        do {
            try {
                shooterElevation = new CANJaguar(RobotMap.ShooterElevationMotorID);
                shooterElevation.changeControlMode(CANJaguar.ControlMode.kPosition);
                shooterElevation.setPositionReference(CANJaguar.PositionReference.kPotentiometer);
                shooterElevation.configNeutralMode(CANJaguar.NeutralMode.kBrake);
                shooterElevation.setVoltageRampRate(0.02);
                shooterElevation.setPID(upPlo, upI, upD);
//                shooterElevation.enableControl();
            } catch (CANTimeoutException ex) {
                failed = true;
                System.out.println("Timeout " + tries + " - Instantiating Shoter Elevation JAG " + RobotMap.ShooterElevationMotorID);
//                ex.printStackTrace();
            }
        } while (failed && (tries++ < RobotMap.m_kMaxCANRetries));

        ElevationControl.getInstance().startThread();


        if (shooterFirstStage == null) {
            shooterFirstStage = new Talon(RobotMap.ShooterFirstStageDrivePWMPort);
        }
        if (shooterSecondStage == null) {
            shooterSecondStage = new Talon(RobotMap.ShooterSecondStageDrivePWMPort);
        }
        // set the shooter to a known state
        reset();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
//        setDefaultCommand(new ManualAim());
    }

    public void spinLaunchWheels(double power) {
        if (power == 0) {
            compressor.start();
        } else {
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

    public void enableElevation(boolean enable) {
        try {
            if (enable) {
//                System.out.println("enable control");
                shooterElevation.enableControl();
            } else {
//                System.out.println("disable control");
                shooterElevation.disableControl();
            }
        } catch (CANTimeoutException ex) {
            System.out.println("TIMEOUT - " + (enable ? "Enabling" : "Disabling") + " Shoter Elevation Control " + RobotMap.ShooterElevationMotorID);
//            ex.printStackTrace();
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

    public void updateShooterElevation(double elevationDegrees) {
//        System.out.println("updateShooterElevation(" + elevationDegrees + ")");
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

    public void setShooterElevation(double elevation) {
        ElevationControl.getInstance().setElevation(elevation);
    }

    public void adjustShooterElevation(double delta) {
        ElevationControl.getInstance().setElevation(ElevationControl.getInstance().getCmdElevation() + delta);
    }

    public double getShooterCommandedElevation() {
        return ElevationControl.getInstance().getCmdElevation();
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

    class ElevationControl implements Runnable {

        // Singleton Stuff
        private final static ElevationControl controlSingleton = new ElevationControl();

        public static ElevationControl getInstance() {
            return controlSingleton;
        }
        // ==============================
        // thread stuff
        private static boolean started = false;

        public void startThread() {
            if (!started) {
                started = true;
                (new Thread(new ElevationControl())).start();
                System.out.println("ElevationControl - Started");
            }
        }
        // ==============================
        private static double commandElevation = 25;
        private static boolean commandUpdated = false;
        private static double lastCommandTime = 0;
        private static double elevationEnableTime = 1; //second
        private static double loopDelay = 0.05;        //seconds
        private static boolean enable = false;
        
        public void setElevation(double elevation) {
//            System.out.println("thread-setElevation(" + elevation + ")");
            commandElevation = elevation;
            if (commandElevation < Shooter.lowLimitAngle)
            {
                commandElevation = Shooter.lowLimitAngle;
            }
            if (commandElevation > Shooter.highLimitAngle)
            {
                commandElevation = Shooter.highLimitAngle;
            }
            lastCommandTime = Timer.getFPGATimestamp();
            enable = true;
            CommandBase.shooter.enableElevation(enable);
            commandUpdated = true;
        }

        public double getCmdElevation() {
            return commandElevation;
        }

        public void run() {
            System.out.println("Elevation Control Thread - RUNNING");
            while (true) {
                if (commandUpdated) {
                    commandUpdated = false; // reset until next command
//                    System.out.println("thread-updateElevation(" + commandElevation + ")");                   
                    CommandBase.shooter.updateShooterElevation(commandElevation);
                }
                if (enable && (Timer.getFPGATimestamp() > (lastCommandTime + elevationEnableTime))){
                    enable = false;
                    // when we stop, set the command to where the shooter is
                    //  that way the next command behaves as expected
                    commandElevation = CommandBase.shooter.getShooterElevation();
                    // --
                    CommandBase.shooter.enableElevation(enable);
                }
                Timer.delay(loopDelay);
            }
        }
    }
