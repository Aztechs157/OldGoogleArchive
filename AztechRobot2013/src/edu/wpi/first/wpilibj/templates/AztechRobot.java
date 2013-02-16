/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.AutonomousCommand;
import edu.wpi.first.wpilibj.templates.subsystems.Drive;
import edu.wpi.first.wpilibj.templates.subsystems.LiftSubsystem;
import edu.wpi.first.wpilibj.templates.subsystems.Shooter;
import edu.wpi.first.wpilibj.templates.subsystems.VisionSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class AztechRobot extends IterativeRobot {

    Command autonomousCommand;

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Drive drive;
    public static LiftSubsystem lift;
    public static VisionSubsystem vision;
    public static Shooter shooter;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // make the robot pieces
        RobotMap.init();
               
        // instantiate the command used for the autonomous period
        autonomousCommand = new AutonomousCommand();
        
        // make the operator interfaces
        oi = new OI();
        
        // make the robot subsystems
        vision = new VisionSubsystem();
        drive = new Drive();
        shooter = new Shooter();
        lift = new LiftSubsystem();
        
        shooter.init();
        drive.init();
        lift.init();        

        // Recenter all the axis (assumes they are being left alone at start of robot...)
        oi.getDriverController().recenter(LogitechController.AxisType.kAxisGameX.value);
        oi.getDriverController().recenter(LogitechController.AxisType.kAxisGameY.value);
        oi.getDriverController().recenter(LogitechController.AxisType.kAxisLeftX.value);
        oi.getDriverController().recenter(LogitechController.AxisType.kAxisLeftY.value);
        oi.getDriverController().recenter(LogitechController.AxisType.kAxisRightX.value);
        oi.getDriverController().recenter(LogitechController.AxisType.kAxisRightY.value);
        oi.getDriverController().recenter(LogitechController.AxisType.kAxisTriggers.value);


        // Initialize all subsystems
        CommandBase.init();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        // Recenter all the axis (assumes they are being left alone at start of teleop...)
        oi.getDriverController().recenter(LogitechController.AxisType.kAxisGameX.value);
        oi.getDriverController().recenter(LogitechController.AxisType.kAxisGameY.value);
        oi.getDriverController().recenter(LogitechController.AxisType.kAxisLeftX.value);
        oi.getDriverController().recenter(LogitechController.AxisType.kAxisLeftY.value);
        oi.getDriverController().recenter(LogitechController.AxisType.kAxisRightX.value);
        oi.getDriverController().recenter(LogitechController.AxisType.kAxisRightY.value);
        oi.getDriverController().recenter(LogitechController.AxisType.kAxisTriggers.value);

        
        autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
