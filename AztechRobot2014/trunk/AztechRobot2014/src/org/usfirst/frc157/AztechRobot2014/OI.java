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
import org.usfirst.frc157.AztechRobot2014.subsystems.BallRetriever;

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
    public JoystickButton driverRightButtonTrigger;  // Button 1 -  Trigger
    public JoystickButton driverRightButton2;        // Button 2 -  Stick down
    public JoystickButton driverRightButton3;        // Button 3 -  Stick center
    public JoystickButton driverRightButton4;        // Button 4 -  Stick left
    public JoystickButton driverRightButton5;        // Button 5 -  Stick right
    public JoystickButton driverRightButton6;        // Button 6 -  Base Left Away 
    public JoystickButton driverRightButton7;        // Button 7 -  Base Left Close
    public JoystickButton driverRightButton8;        // Button 8 -  Base Near Left
    public JoystickButton driverRightButton9;        // Button 9 -  Base Near Right
    public JoystickButton driverRightButton10;       // Button 10 - Base Right Close
    public JoystickButton driverRightButton11;       // Button 11 - Base RIght Away

    public Joystick driverRight;
    public JoystickButton driverLeftButtonTrigger;  // Button 1 -  Trigger
    public JoystickButton driverLeftButton2;        // Button 2 -  Stick down
    public JoystickButton driverLeftButton3;        // Button 3 -  Stick center
    public JoystickButton driverLeftButton4;        // Button 4 -  Stick left
    public JoystickButton driverLeftButton5;        // Button 5 -  Stick right
    public JoystickButton driverLeftButton6;        // Button 6 -  Base Left Away 
    public JoystickButton driverLeftButton7;        // Button 7 -  Base Left Close
    public JoystickButton driverLeftButton8;        // Button 8 -  Base Near Left
    public JoystickButton driverLeftButton9;        // Button 9 -  Base Near Right
    public JoystickButton driverLeftButton10;       // Button 10 - Base Right Close
    public JoystickButton driverLeftButton11;       // Button 11 - Base RIght Away

    public LogitechController driver;
    public LogitechControllerButton driverButtonLeftB;         // Left Button (Above Trigger)
    public LogitechControllerButton driverButtonRightB;        // Right Button (Above Trigger)
    public LogitechControllerButton driverButtonLeftTop;       // Left Stick Pressing 
    public LogitechControllerButton driverButtonRightTop;      // Right Stick Pressing 
    public LogitechControllerButton driverButtonA;             // A Button (Green)
    public LogitechControllerButton driverButtonB;             // B Button (Red)
    public LogitechControllerButton driverButtonX;             // X Button (Blue)
    public LogitechControllerButton driverButtonY;             // Y Button (Yellow)
    public LogitechControllerButton driverButtonStart;         // Start Button
    public LogitechControllerButton driverButtonBack;          // Back Button
    public LogitechControllerButton driverButtonLeftTrigger;   // Left Trigger (Used as button)
    public LogitechControllerButton driverButtonRightTrigger;  // Right Trigger (Used as button)
    public LogitechControllerButton driverButtonGameUp;        // Game Pad Up
    public LogitechControllerButton driverButtonGameDown;      // Game Pad Down
    public LogitechControllerButton driverButtonGameLeft;      // Game Pad Left
    public LogitechControllerButton driverButtonGameRight;     // Game Pad Right

    public LogitechController coDriver;
    public LogitechControllerButton coDriverButtonLeftB;         // Left Button (Above Trigger)
    public LogitechControllerButton coDriverButtonRightB;        // Right Button (Above Trigger)
    public LogitechControllerButton coDriverButtonLeftTop;       // Left Stick Pressing 
    public LogitechControllerButton coDriverButtonRightTop;      // Right Stick Pressing 
    public LogitechControllerButton coDriverButtonA;             // A Button (Green)
    public LogitechControllerButton coDriverButtonB;             // B Button (Red)
    public LogitechControllerButton coDriverButtonX;             // X Button (Blue)
    public LogitechControllerButton coDriverButtonY;             // Y Button (Yellow)
    public LogitechControllerButton coDriverButtonStart;         // Start Button
    public LogitechControllerButton coDriverButtonBack;          // Back Button
    public LogitechControllerButton coDriverButtonLeftTrigger;   // Left Trigger (Used as button)
    public LogitechControllerButton coDriverButtonRightTrigger;  // Right Trigger (Used as button)
    public LogitechControllerButton coDriverButtonGameUp;        // Game Pad Up
    public LogitechControllerButton coDriverButtonGameDown;      // Game Pad Down
    public LogitechControllerButton coDriverButtonGameLeft;      // Game Pad Left
    public LogitechControllerButton coDriverButtonGameRight;     // Game Pad Right

    public LogitechControllerButton coDriverButton;
    public LogitechControllerButton driverButton;
    public LogitechControllerButton driverAlign;

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
        driverRight = new Joystick(RobotMap.CONTROLLERNUM_DriverRight);
        driver = new LogitechController(RobotMap.CONTROLLERNUM_Driver);
        coDriver = new LogitechController(RobotMap.CONTROLLERNUM_CoDriver);

        driverLeftButtonTrigger = new JoystickButton(driverLeft, 1);  // Button 1 -  Trigger
        driverLeftButton2 = new JoystickButton(driverLeft, 2);        // Button 2 -  Stick down
        driverLeftButton3 = new JoystickButton(driverLeft, 3);        // Button 3 -  Stick center
        driverLeftButton4 = new JoystickButton(driverLeft, 4);        // Button 4 -  Stick left
        driverLeftButton5 = new JoystickButton(driverLeft, 5);        // Button 5 -  Stick right
        driverLeftButton6 = new JoystickButton(driverLeft, 6);        // Button 6 -  Base Left Away 
        driverLeftButton7 = new JoystickButton(driverLeft, 7);        // Button 7 -  Base Left Close
        driverLeftButton8 = new JoystickButton(driverLeft, 8);        // Button 8 -  Base Near Left
        driverLeftButton9 = new JoystickButton(driverLeft, 9);        // Button 9 -  Base Near Right
        driverLeftButton10 = new JoystickButton(driverLeft, 10);      // Button 10 - Base Right Close
        driverLeftButton11 = new JoystickButton(driverLeft, 11);      // Button 11 - Base RIght Away

        driverLeftButtonTrigger.whenPressed(new DebugPrint("driverLeftButtonTrigger Pressed"));
        driverLeftButton2.whenPressed(new DebugPrint("driverLeftButton2 Pressed"));
        driverLeftButton3.whenPressed(new DebugPrint("driverLeftButton3 Pressed"));
        driverLeftButton4.whenPressed(new DebugPrint("driverLeftButton4 Pressed"));
        driverLeftButton5.whenPressed(new DebugPrint("driverLeftButton5 Pressed"));
        driverLeftButton6.whenPressed(new DebugPrint("driverLeftButton6 Pressed"));
        driverLeftButton7.whenPressed(new DebugPrint("driverLeftButton7 Pressed"));
        driverLeftButton8.whenPressed(new DebugPrint("driverLeftButton8 Pressed"));
        driverLeftButton9.whenPressed(new DebugPrint("driverLeftButton9 Pressed"));
        driverLeftButton10.whenPressed(new DebugPrint("driverLeftButton10 Pressed"));
        driverLeftButton11.whenPressed(new DebugPrint("driverLeftButton11 Pressed"));

        driverRightButtonTrigger = new JoystickButton(driverRight, 1);  // Button 1 -  Trigger
        driverRightButton2 = new JoystickButton(driverRight, 2);        // Button 2 -  Stick down
        driverRightButton3 = new JoystickButton(driverRight, 3);        // Button 3 -  Stick center
        driverRightButton4 = new JoystickButton(driverRight, 4);        // Button 4 -  Stick left
        driverRightButton5 = new JoystickButton(driverRight, 5);        // Button 5 -  Stick right
        driverRightButton6 = new JoystickButton(driverRight, 6);        // Button 6 -  Base Left Away 
        driverRightButton7 = new JoystickButton(driverRight, 7);        // Button 7 -  Base Left Close
        driverRightButton8 = new JoystickButton(driverRight, 8);        // Button 8 -  Base Near Left
        driverRightButton9 = new JoystickButton(driverRight, 9);        // Button 9 -  Base Near Right
        driverRightButton10 = new JoystickButton(driverRight, 10);      // Button 10 - Base Right Close
        driverRightButton11 = new JoystickButton(driverRight, 11);      // Button 11 - Base RIght Away

        driverRightButtonTrigger.whenPressed(new DebugPrint("driverRightButtonTrigger Pressed"));
        driverRightButton2.whenPressed(new DebugPrint("driverRightButton2 Pressed"));
        driverRightButton3.whenPressed(new DebugPrint("driverRightButton3 Pressed"));
        driverRightButton4.whenPressed(new DebugPrint("driverRightButton4 Pressed"));
        driverRightButton5.whenPressed(new DebugPrint("driverRightButton5 Pressed"));
        driverRightButton6.whenPressed(new DebugPrint("driverRightButton6 Pressed"));
        driverRightButton7.whenPressed(new DebugPrint("driverRightButton7 Pressed"));
        driverRightButton8.whenPressed(new DebugPrint("driverRightButton8 Pressed"));
        driverRightButton9.whenPressed(new DebugPrint("driverRightButton9 Pressed"));
        driverRightButton10.whenPressed(new DebugPrint("driverRightButton10 Pressed"));
        driverRightButton11.whenPressed(new DebugPrint("driverRightButton11 Pressed"));

        driverButtonLeftB = new LogitechControllerButton(driver, LogitechController.ButtonType.kButtonLeftB.value);               // Left Button (Above Trigger)
        driverButtonRightB = new LogitechControllerButton(driver, LogitechController.ButtonType.kButtonRightB.value);             // Right Button (Above Trigger)
        driverButtonLeftTop = new LogitechControllerButton(driver, LogitechController.ButtonType.kButtonLeftTop.value);           // Left Stick Pressing 
        driverButtonRightTop = new LogitechControllerButton(driver, LogitechController.ButtonType.kButtonRightTop.value);         // Right Stick Pressing 
        driverButtonA = new LogitechControllerButton(driver, LogitechController.ButtonType.kButtonA.value);                       // A Button (Green)
        driverButtonB = new LogitechControllerButton(driver, LogitechController.ButtonType.kButtonB.value);                       // B Button (Red)
        driverButtonX = new LogitechControllerButton(driver, LogitechController.ButtonType.kButtonX.value);                       // X Button (Blue)
        driverButtonY = new LogitechControllerButton(driver, LogitechController.ButtonType.kButtonY.value);                       // Y Button (Yellow)
        driverButtonStart = new LogitechControllerButton(driver, LogitechController.ButtonType.kButtonStart.value);               // Start Button
        driverButtonBack = new LogitechControllerButton(driver, LogitechController.ButtonType.kButtonBack.value);                 // Back Button
        driverButtonLeftTrigger = new LogitechControllerButton(driver, LogitechController.ButtonType.kButtonLeftTrigger.value);   // Left Trigger (Used as button)
        driverButtonRightTrigger = new LogitechControllerButton(driver, LogitechController.ButtonType.kButtonRightTrigger.value); // Right Trigger (Used as button)
        driverButtonGameUp = new LogitechControllerButton(driver, LogitechController.ButtonType.kButtonGameUp.value);             // Game Pad Up
        driverButtonGameDown = new LogitechControllerButton(driver, LogitechController.ButtonType.kButtonGameDown.value);         // Game Pad Down
        driverButtonGameLeft = new LogitechControllerButton(driver, LogitechController.ButtonType.kButtonGameLeft.value);         // Game Pad Left
        driverButtonGameRight = new LogitechControllerButton(driver, LogitechController.ButtonType.kButtonGameRight.value);       // Game Pad Right

        driverButtonLeftB.whenPressed(new DebugPrint("driverButtonLeftB Pressed"));
        driverButtonRightB.whenPressed(new DebugPrint("driverButtonRightB Pressed"));
        driverButtonLeftTop.whenPressed(new DebugPrint("driverButtonLeftTop Pressed"));
        driverButtonRightTop.whenPressed(new DebugPrint("driverButtonRightTop Pressed"));
        driverButtonA.whenPressed(new DebugPrint("driverButtonA Pressed"));
        driverButtonB.whenPressed(new DebugPrint("driverButtonB Pressed"));
        driverButtonX.whenPressed(new DebugPrint("driverButtonX Pressed"));
        driverButtonY.whenPressed(new DebugPrint("driverButtonY Pressed"));
        driverButtonStart.whenPressed(new DebugPrint("driverButtonStart Pressed"));
        driverButtonBack.whenPressed(new DebugPrint("driverButtonBack Pressed"));
        driverButtonLeftTrigger.whenPressed(new DebugPrint("driverButtonLeftTrigger Pressed"));
        driverButtonRightTrigger.whenPressed(new DebugPrint("driverButtonRightTrigger Pressed"));
        driverButtonGameUp.whenPressed(new DebugPrint("driverButtonGameUp Pressed"));
        driverButtonGameDown.whenPressed(new DebugPrint("driverButtonGameDown Pressed"));
        driverButtonGameLeft.whenPressed(new DebugPrint("driverButtonGameLeft Pressed"));
        driverButtonGameRight.whenPressed(new DebugPrint("driverButtonGameRight Pressed"));

        coDriverButtonLeftB = new LogitechControllerButton(coDriver, LogitechController.ButtonType.kButtonLeftB.value);               // Left Button (Above Trigger)
        coDriverButtonRightB = new LogitechControllerButton(coDriver, LogitechController.ButtonType.kButtonRightB.value);             // Right Button (Above Trigger)
        coDriverButtonLeftTop = new LogitechControllerButton(coDriver, LogitechController.ButtonType.kButtonLeftTop.value);           // Left Stick Pressing 
        coDriverButtonRightTop = new LogitechControllerButton(coDriver, LogitechController.ButtonType.kButtonRightTop.value);         // Right Stick Pressing 
        coDriverButtonA = new LogitechControllerButton(coDriver, LogitechController.ButtonType.kButtonA.value);                       // A Button (Green)
        coDriverButtonB = new LogitechControllerButton(coDriver, LogitechController.ButtonType.kButtonB.value);                       // B Button (Red)
        coDriverButtonX = new LogitechControllerButton(coDriver, LogitechController.ButtonType.kButtonX.value);                       // X Button (Blue)
        coDriverButtonY = new LogitechControllerButton(coDriver, LogitechController.ButtonType.kButtonY.value);                       // Y Button (Yellow)
        coDriverButtonStart = new LogitechControllerButton(coDriver, LogitechController.ButtonType.kButtonStart.value);               // Start Button
        coDriverButtonBack = new LogitechControllerButton(coDriver, LogitechController.ButtonType.kButtonBack.value);                 // Back Button
        coDriverButtonLeftTrigger = new LogitechControllerButton(coDriver, LogitechController.ButtonType.kButtonLeftTrigger.value);   // Left Trigger (Used as button)
        coDriverButtonRightTrigger = new LogitechControllerButton(coDriver, LogitechController.ButtonType.kButtonRightTrigger.value); // Right Trigger (Used as button)
        coDriverButtonGameUp = new LogitechControllerButton(coDriver, LogitechController.ButtonType.kButtonGameUp.value);             // Game Pad Up
        coDriverButtonGameDown = new LogitechControllerButton(coDriver, LogitechController.ButtonType.kButtonGameDown.value);         // Game Pad Down
        coDriverButtonGameLeft = new LogitechControllerButton(coDriver, LogitechController.ButtonType.kButtonGameLeft.value);         // Game Pad Left
        coDriverButtonGameRight = new LogitechControllerButton(coDriver, LogitechController.ButtonType.kButtonGameRight.value);       // Game Pad Right

        coDriverButtonLeftB.whenPressed(new DebugPrint("coDriverButtonLeftB Pressed"));
        coDriverButtonRightB.whenPressed(new SensorAlignToWall());
        coDriverButtonLeftTop.whenPressed(new DebugPrint("coDriverButtonLeftTop Pressed"));
        coDriverButtonRightTop.whenPressed(new DebugPrint("coDriverButtonRightTop Pressed"));
        coDriverButtonA.whenPressed(new DebugPrint("coDriverButtonA Pressed"));
        coDriverButtonB.whenPressed(new EjectBall());
        coDriverButtonX.whenPressed(new PickupBall());
        coDriverButtonY.whenPressed(new LoaderNeutral());
        coDriverButtonBack.whenPressed(new DebugPrint("coDriverButtonBack Pressed"));
        
        coDriverButtonLeftTrigger.whileHeld(new SpinRoller(Robot.ballRetriever.getSpinRollerDirection()));
        coDriverButtonLeftTrigger.whileHeld(new DebugPrint("coDriverLeftTrigger Pressed"));
        coDriverButtonLeftTrigger.whenReleased(new SpinRoller(SpinRoller.ROLLER_STOP));
        coDriverButtonRightTrigger.whenPressed(new Launch());
        
        coDriverButtonGameUp.whenPressed(new DebugPrint("coDriverButtonGameUp Pressed"));  //Not Working
        coDriverButtonGameDown.whenPressed(new DebugPrint("coDriverButtonGameDown Pressed"));
        //Check these 2 -- Right + Left
        coDriverButtonGameRight.whenPressed(new MoveBallRetriever(MoveBallRetriever.RETRIEVER_OUT));
        coDriverButtonGameLeft.whenPressed(new MoveBallRetriever(MoveBallRetriever.RETRIEVER_IN));
        coDriverButtonStart.whenPressed(new MoveBallRetriever(MoveBallRetriever.RETRIEVER_MIDDLE));
        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", Robot.getAutonomousCommand());

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
