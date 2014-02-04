// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc157.AztechRobot2014;

import org.usfirst.frc157.AztechRobot2014.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public Joystick driverLeft;
    public JoystickButton driverLeftButton;
    public Joystick driverRight;
    public JoystickButton driverRightButton;

    public LogitechController driver;
    public LogitechControllerButton driverButton;
    public LogitechControllerButton driverAlign;

    public LogitechController coDriver;
    public LogitechControllerButton coDriverButton;

    public LogitechControllerButton rollerInButton;
    public LogitechControllerButton rollerStopButton;
    public LogitechControllerButton rollerOutButton;
    public LogitechControllerButton armUpButton;
    public LogitechControllerButton armMiddleButton;
    public LogitechControllerButton armDownButton;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        driverLeft = new Joystick(RobotMap.CONTROLLERNUM_DriverLeft);
        driverLeftButton = new JoystickButton(driverLeft, 1);  // 1 is trigger on Attack 3 Stick
        driverLeftButton.whenPressed(new DebugPrint("DriverLeft Trigger"));

        driverRight = new Joystick(RobotMap.CONTROLLERNUM_DriverRight);
        driverRightButton = new JoystickButton(driverRight, 1); // 1 is trigger on Attack 3 Stick
        driverRightButton.whenPressed(new DebugPrint("DriverRight Trigger"));

        driver = new LogitechController(RobotMap.CONTROLLERNUM_Driver);
        driverButton = new LogitechControllerButton(driver, LogitechController.ButtonType.kButtonA.value);
        driverButton.whenPressed(new DebugPrint("Driver A button"));
        driverAlign = new LogitechControllerButton(driver, LogitechController.ButtonType.kButtonLeftTop.value);
        driverAlign.whenPressed(new SensorAlignToWall());

        coDriver = new LogitechController(RobotMap.CONTROLLERNUM_CoDriver);
        coDriverButton = new LogitechControllerButton(coDriver, LogitechController.ButtonType.kButtonA.value);
        coDriverButton.whenPressed(new DebugPrint("CoDRiver A button"));

        rollerInButton = new LogitechControllerButton(coDriver, LogitechController.ButtonType.kButtonX.value);
        rollerInButton.whenPressed(new SpinRoller(SpinRoller.ROLLER_IN));
        rollerStopButton = new LogitechControllerButton(coDriver, LogitechController.ButtonType.kButtonY.value);
        rollerStopButton.whenPressed(new SpinRoller(SpinRoller.ROLLER_STOP));
        rollerOutButton = new LogitechControllerButton(coDriver, LogitechController.ButtonType.kButtonB.value);
        rollerOutButton.whenPressed(new SpinRoller(SpinRoller.ROLLER_OUT));

        armUpButton = new LogitechControllerButton(coDriver, LogitechController.ButtonType.kButtonGameLeft.value);
        armUpButton.whenPressed(new MoveBallRetriever(MoveBallRetriever.RETRIEVER_UP));
        armMiddleButton = new LogitechControllerButton(coDriver, LogitechController.ButtonType.kButtonGameUp.value);
        armMiddleButton.whenPressed(new MoveBallRetriever(MoveBallRetriever.RETRIEVER_MIDDLE));
        armDownButton = new LogitechControllerButton(coDriver, LogitechController.ButtonType.kButtonGameRight.value);
        armDownButton.whenPressed(new MoveBallRetriever(MoveBallRetriever.RETRIEVER_DOWN));
        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("OperatorDrive", new OperatorDrive());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public void setOIDigitalOutput1(boolean value) {
        DriverStation.getInstance().setDigitalOut(1, value);
    }

    public LogitechController getCoDriver() {
        return coDriver;
    }

    public LogitechController getDriver() {
        return driver;
    }

    public Joystick getDriverLeft() {
        return driverLeft;
    }

    public Joystick getDriverRight() {
        return driverRight;
    }

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}
