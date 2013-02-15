/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

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
    Solenoid loaderDeploy;
    Solenoid shooterRetract;
    Solenoid shooterDeploy;
            
    public void init()
    {
        compressor = new Compressor(RobotMap.PressureSwitchGPIOPort, RobotMap.CompressorRelayChannel);
        compressor.start();

        loaderRetract = new Solenoid(RobotMap.LoaderRetractPort);
        loaderDeploy = new Solenoid(RobotMap.LoaderDeployPort);
        shooterRetract = new Solenoid(RobotMap.ShooterRetractPort);
        shooterDeploy = new Solenoid(RobotMap.ShooterDeployPort);
         
        try {
            shooterElevation = new CANJaguar(RobotMap.ShooterElevationMotorID);
            shooterElevation.changeControlMode(CANJaguar.ControlMode.kPosition);
            shooterElevation.setPositionReference(CANJaguar.PositionReference.kPotentiometer);
            shooterElevation.configNeutralMode(CANJaguar.NeutralMode.kBrake);
            shooterElevation.setPID(1, 0, 0);  //TODO: Set PID Constants for elevation conrol
            shooterElevation.enableControl();
        } catch (CANTimeoutException ex) {
            System.out.println("FAIL - Instantiating Shoter Elevation JAG " + RobotMap.ShooterElevationMotorID);
            ex.printStackTrace();
        }

        shooterFirstStage = new Talon(RobotMap.ShooterFirstStageDrivePWMPort);
        shooterSecondStage = new Talon(RobotMap.ShooterSecondStageDrivePWMPort);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void spinLaunchWheels(double power)
    {
        shooterFirstStage.set(power);
        shooterSecondStage.set(power);
    }
    
    public void stopLaunchWheels()
    {
        spinLaunchWheels(0);
    }
   
    public void extendLoader()
    {
        setLoaderExtended(true);
    }
    public void retractLoader()
    {
        setLoaderExtended(false);        
    }
    public void extendShooter()
    {
        setShooterExtended(true);
    }
    public void retractShooter()
    {
        setShooterExtended(false);        
    }
    
    private void setLoaderExtended(boolean extend) {
        if (extend) {
            if ((loaderRetract != null) && (loaderDeploy != null)) {
                loaderDeploy.set(true);
                loaderRetract.set(false);
            } else {
                if ((loaderRetract != null) && (loaderDeploy != null)) {
                    loaderRetract.set(true);
                    loaderDeploy.set(false);
                }
            }
        }
    }

    public void adjustShooterElevation(double delta)
    {
        setShooterElevation(getShooterElevation() + delta);
    }
    
    public void setShooterElevation(double elevation)
    {
        if (null != shooterElevation) {
            try {
                shooterElevation.setX(elevation);
            } catch (edu.wpi.first.wpilibj.can.CANTimeoutException e) {
                System.out.println("CAN Timeout setting shooter elevation (motor " + RobotMap.ShooterElevationMotorID + ")");
            }
        }
    }
    
    private static double lastValidResult = 0;
    public double getShooterElevation()
    {
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
            if ((shooterRetract != null) && (shooterDeploy != null)) {
                shooterDeploy.set(true);
                shooterRetract.set(false);
            } else {
                if ((shooterRetract != null) && (shooterDeploy != null)) {
                    shooterRetract.set(true);
                    shooterDeploy.set(false);
                }
            }
        }
    }
}
