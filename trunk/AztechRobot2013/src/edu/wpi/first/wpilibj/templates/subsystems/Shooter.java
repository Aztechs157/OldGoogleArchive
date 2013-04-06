/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

    public static double commandedElevation = 0;
    public static final double lowLimitAngle = 0;
    public static final double highLimitAngle = 50;
    public static final double loadingAngle = 20;
    public static final double climbAngle = 0;
//    public static final double basicShotAngle = 38.8;  // two orange wheels
    public static final double basicShotAngle = 39.2;    // grean 1st stage, orange second stage
    //             Sensor   Angle
    // Bottom Stop 0.856    0.00
    // Top Stop    0.652    50.9
    // Sensor values per degree .004 
    public static final double zeroSensorReading = .124;  //065;
    public static final double maxSensorReading =  .337; //276;
    public static final double zeroElevationAngle = 0;  //1.5
    public static final double maxElevationAngle = 50;   //51.
    public static final double sensorPerDegree = ((maxSensorReading - zeroSensorReading) / (maxElevationAngle - zeroElevationAngle));
    // Up PID Constants
    public static final double upP = 5000.0;
    public static final double upI = 0.1;
    public static final double upD = 0;
    // Down PID Constants
    public static final double dnP = 5000.0;
    public static final double dnI = 0.1;
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
    Solenoid redRelay = null;
    Solenoid blueRelay = null;
    Solenoid greenRelay = null;

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
            System.out.println("Create red relay");
            redRelay = new Solenoid(RobotMap.RedLightPort);
        }
        if (greenRelay == null) {
            System.out.println("Create green relay");
            greenRelay = new Solenoid(RobotMap.WhiteLightPort);
        }
        if (blueRelay == null) {
            System.out.println("Create blue relay");
            blueRelay = new Solenoid(RobotMap.BlueLightPort);
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
                shooterElevation.setPID(upP, upI, upD);
//                shooterElevation.enableControl();
            } catch (CANTimeoutException ex) {
                failed = true;
                System.out.println("Timeout " + tries + " - Instantiating Shoter Elevation JAG " + RobotMap.ShooterElevationMotorID);
//                ex.printStackTrace();
            }
        } while (failed && (tries++ < RobotMap.m_kMaxCANRetries));

        if (shooterFirstStage == null) {
            shooterFirstStage = new Talon(RobotMap.ShooterFirstStageDrivePWMPort);
        }
        if (shooterSecondStage == null) {
            shooterSecondStage = new Talon(RobotMap.ShooterSecondStageDrivePWMPort);
        }
        // set the shooter to a known state
        reset();
        setShooterElevation(getShooterElevation());

        SmartDashboard.putNumber("upP", upP);
        SmartDashboard.putNumber("upI", upI);
        SmartDashboard.putNumber("upD", upD);

        SmartDashboard.putNumber("dnP", dnP);
        SmartDashboard.putNumber("dnI", dnI);
        SmartDashboard.putNumber("dnD", dnD);

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

    private void setPIDConstants(double targetElevation) {
        double currentElevation = getShooterElevation();
        int tries = 0;
        boolean failed = false;

        double _upP = SmartDashboard.getNumber("upP", upP);
        double _upI = SmartDashboard.getNumber("upI", upI);
        double _upD = SmartDashboard.getNumber("upD", upD);

        double _dnP = SmartDashboard.getNumber("dnP", dnP);
        double _dnI = SmartDashboard.getNumber("dnI", dnI);
        double _dnD = SmartDashboard.getNumber("dnD", dnD);

//        double _upPhi = upPhi;
//        double _upPlo = upPhi;
//        double _upI = upI;
//        double _upD = upD;
//        
//        double _dnPhi = dnPhi;
//        double _dnPlo = dnPhi;
//        double _dnI = dnI;
//        double _dnD = dnD;

        do {
            try {
                shooterElevation.changeControlMode(CANJaguar.ControlMode.kPosition);
                shooterElevation.setPositionReference(CANJaguar.PositionReference.kPotentiometer);
                shooterElevation.configNeutralMode(CANJaguar.NeutralMode.kBrake);
                if ((targetElevation - currentElevation) > 0) {
                    shooterElevation.setPID(_upP, _upI, _upD);
                } else {
                    shooterElevation.setPID(_dnP, _dnI, _dnD);
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
       System.out.println("updateShooterElevation(" + elevationDegrees + ")");

        //fix command range
        if (elevationDegrees < Shooter.lowLimitAngle) {
            elevationDegrees = Shooter.lowLimitAngle;
        }
        if (elevationDegrees > Shooter.highLimitAngle) {
            elevationDegrees = Shooter.highLimitAngle;
        }

        // set the PID constants
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
        commandedElevation = elevation;
        //fix command range
        if (commandedElevation < Shooter.lowLimitAngle) {
            commandedElevation = Shooter.lowLimitAngle;
        }
        if (commandedElevation > Shooter.highLimitAngle) {
            commandedElevation = Shooter.highLimitAngle;
        }

        updateShooterElevation(commandedElevation);
    }

    public void adjustShooterElevation(double delta) {
        commandedElevation += delta;
        //fix command range
        if (commandedElevation < Shooter.lowLimitAngle) {
            commandedElevation = Shooter.lowLimitAngle;
        }
        if (commandedElevation > Shooter.highLimitAngle) {
            commandedElevation = Shooter.highLimitAngle;
        }

        updateShooterElevation(commandedElevation);
    }

    public double getShooterCommandedElevation() {
        return commandedElevation;
    }

    public void setColor(Color color) {
        switch (color.value) {
            case Color.m_kBlack:
                System.out.println("No Lighs");
                redRelay.set(false);
                greenRelay.set(false);
                blueRelay.set(false);
                break;
            case Color.m_kRed:
                System.out.println("Red Lights");
                redRelay.set(true);
                greenRelay.set(false);
                blueRelay.set(false);
                break;
            case Color.m_kWhite:
                System.out.println("White Lights");
                redRelay.set(false);
                greenRelay.set(true);
                blueRelay.set(false);
                break;
            case Color.m_kBlue:
                System.out.println("Blue Lights");
                redRelay.set(false);
                greenRelay.set(false);
                blueRelay.set(true);
                break;
        }

        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser2, 1, "EleStop @ " + CommandBase.shooter.getShooterElevation());
        DriverStationLCD.getInstance().updateLCD();

    }
}
