package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.templates.LogitechController;
import edu.wpi.first.wpilibj.templates.LogitechControllerButton;
import edu.wpi.first.wpilibj.templates.commands.*;
import edu.wpi.first.wpilibj.templates.subsystems.Drive;
import edu.wpi.first.wpilibj.templates.subsystems.Shooter;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    // Controller Ports
    public static final int kLogitechFirstPort = 1;
    public static final int kOperatorJoystickPort = 2;
    
    // Controllers
    private LogitechController m_FirstGamePad;
    private Joystick m_OperatorJoystick;
    
    //// CREATING Logitech BUTTONS
    // Trigger Buttons (at index fingers)
    private LogitechControllerButton m_setFirst_LeftShoulderButton;
    private LogitechControllerButton m_setFirst_LeftTrigger;
    private LogitechControllerButton m_setFirst_RightShoulderButton;
    private LogitechControllerButton m_setFirst_RightTrigger;
    // Gamepad
    private LogitechControllerButton m_setFirst_GamepadLeft;
    private LogitechControllerButton m_setFirst_GamepadRight;
    private LogitechControllerButton m_setFirst_GamepadUp;
    private LogitechControllerButton m_setFirst_GamepadDown;
    // X,Y,A,B Buttons
    private LogitechControllerButton m_setFirst_X;
    private LogitechControllerButton m_setFirst_Y;
    private LogitechControllerButton m_setFirst_A;
    private LogitechControllerButton m_setFirst_B;
    // Start/Back Buttoins
    private LogitechControllerButton m_setFirst_Start;
    private LogitechControllerButton m_setFirst_Back;
    // Stick Buttons (Push down on stick)
    private LogitechControllerButton m_setFirst_LeftStickButton;
    private LogitechControllerButton m_setFirst_RightStickButton;

    //// CREATING Joystick BUTTONS
    private JoystickButton m_OperatorButton_Trigger;
    private JoystickButton m_OperatorButton_Side;
    private JoystickButton m_OperatorButton_3;
    private JoystickButton m_OperatorButton_4;
    private JoystickButton m_OperatorButton_5;
    private JoystickButton m_OperatorButton_6;
    private JoystickButton m_OperatorButton_7;
    private JoystickButton m_OperatorButton_8;
    private JoystickButton m_OperatorButton_9;
    private JoystickButton m_OperatorButton_10;
    private JoystickButton m_OperatorButton_11;
    private JoystickButton m_OperatorButton_12;

    public OI() {

        // Create the Controller
        m_FirstGamePad = new LogitechController(kLogitechFirstPort);
        // Create the Buttons
        // Trigger Buttons (at index fingers)
        m_setFirst_LeftShoulderButton = new LogitechControllerButton(m_FirstGamePad, LogitechController.ButtonType.kButtonLeftB.value);
        m_setFirst_LeftTrigger = new LogitechControllerButton(m_FirstGamePad, LogitechController.ButtonType.kButtonLeftTrigger.value);
        m_setFirst_RightShoulderButton = new LogitechControllerButton(m_FirstGamePad, LogitechController.ButtonType.kButtonRightB.value);
        m_setFirst_RightTrigger = new LogitechControllerButton(m_FirstGamePad, LogitechController.ButtonType.kButtonRightTrigger.value);
        // Gamepad
        m_setFirst_GamepadLeft = new LogitechControllerButton(m_FirstGamePad, LogitechController.ButtonType.kButtonGameLeft.value);
        m_setFirst_GamepadRight = new LogitechControllerButton(m_FirstGamePad, LogitechController.ButtonType.kButtonGameRight.value);
        m_setFirst_GamepadUp = new LogitechControllerButton(m_FirstGamePad, LogitechController.ButtonType.kButtonGameUp.value);
        m_setFirst_GamepadDown = new LogitechControllerButton(m_FirstGamePad, LogitechController.ButtonType.kButtonGameDown.value);
        // X,Y,A,B Buttons
        m_setFirst_X = new LogitechControllerButton(m_FirstGamePad, LogitechController.ButtonType.kButtonX.value);
        m_setFirst_Y = new LogitechControllerButton(m_FirstGamePad, LogitechController.ButtonType.kButtonY.value);
        m_setFirst_A = new LogitechControllerButton(m_FirstGamePad, LogitechController.ButtonType.kButtonA.value);
        m_setFirst_B = new LogitechControllerButton(m_FirstGamePad, LogitechController.ButtonType.kButtonB.value);
        // Start/Back Buttoins
        m_setFirst_Start = new LogitechControllerButton(m_FirstGamePad, LogitechController.ButtonType.kButtonStart.value);
        m_setFirst_Back = new LogitechControllerButton(m_FirstGamePad, LogitechController.ButtonType.kButtonBack.value);
        // Stick Buttons (Push down on stick)
        m_setFirst_LeftStickButton = new LogitechControllerButton(m_FirstGamePad, LogitechController.ButtonType.kButtonLeftTop.value);
        m_setFirst_RightStickButton = new LogitechControllerButton(m_FirstGamePad, LogitechController.ButtonType.kButtonRightTop.value);

        // Associate the Buttons with Commands
        m_setFirst_LeftShoulderButton.whenPressed(new PrintMessage("Button <m_setFirst_LeftShoulderButton> pressed\n"));
        m_setFirst_LeftTrigger.whenPressed(new PrintMessage("Button <m_setFirst_LeftTrigger> pressed\n"));
        m_setFirst_RightShoulderButton.whenPressed(new PrintMessage("Button <m_setFirst_RightShoulderButton> pressed\n"));
        m_setFirst_RightTrigger.whenPressed(new PrintMessage("Button <m_setFirst_RightTrigger> pressed\n"));
        // Gamepad
        m_setFirst_GamepadLeft.whenPressed(new PrintMessage("Button <m_setFirst_GamepadLeft> pressed\n"));
        m_setFirst_GamepadRight.whenPressed(new PrintMessage("Button <m_setFirst_GamepadRight> pressed\n"));
        m_setFirst_GamepadUp.whenPressed(new PrintMessage("Button <m_setFirst_GamepadUp> pressed\n"));
        m_setFirst_GamepadDown.whenPressed(new PrintMessage("Button <m_setFirst_GamepadDown> pressed\n"));
        // X,Y,A,B Buttons
        m_setFirst_X.whenPressed(new PrintMessage("Button <m_setFirst_X> pressed\n"));
        m_setFirst_Y.whenPressed(new VisionAlign());
//        m_setFirst_Y.whenPressed(new PrintMessage("Button <m_setFirst_Y> pressed\n"));
//        m_setFirst_A.whenPressed(new PrintMessage("Button <m_setFirst_A> pressed\n"));//Lift Retract
//        m_setFirst_B.whenPressed(new PrintMessage("Button <m_setFirst_B> pressed\n"));// Lift Extend
        // Start/Back Buttoins
        m_setFirst_Start.whenPressed(new PrintMessage("Button <m_setFirst_Start> pressed\n"));
        m_setFirst_Back.whenPressed(new PrintMessage("Button <m_setFirst_Back> pressed\n"));
        // Stick Buttons (Push down on stick)
        m_setFirst_LeftStickButton.whenPressed(new PrintMessage("Button <m_setFirst_LeftStickButton> pressed\n"));
        m_setFirst_RightStickButton.whenPressed(new PrintMessage("Button <m_setFirst_RightStickButton> pressed\n"));

        m_FirstGamePad.recenter(LogitechController.AxisType.kAxisGameX.value);
        m_FirstGamePad.recenter(LogitechController.AxisType.kAxisGameY.value);
        m_FirstGamePad.recenter(LogitechController.AxisType.kAxisLeftX.value);
        m_FirstGamePad.recenter(LogitechController.AxisType.kAxisLeftY.value);
        m_FirstGamePad.recenter(LogitechController.AxisType.kAxisRightX.value);
        m_FirstGamePad.recenter(LogitechController.AxisType.kAxisRightY.value);
        m_FirstGamePad.recenter(LogitechController.AxisType.kAxisTriggers.value);
       
        // Create the Joystick
        m_OperatorJoystick = new Joystick(kOperatorJoystickPort);

        m_OperatorButton_Trigger = new JoystickButton(m_OperatorJoystick, 1);
        m_OperatorButton_Side = new JoystickButton(m_OperatorJoystick, 2);
        m_OperatorButton_3 = new JoystickButton(m_OperatorJoystick, 3);
        m_OperatorButton_4 = new JoystickButton(m_OperatorJoystick, 4);
        m_OperatorButton_5 = new JoystickButton(m_OperatorJoystick, 5);
        m_OperatorButton_6 = new JoystickButton(m_OperatorJoystick, 6);
        m_OperatorButton_7 = new JoystickButton(m_OperatorJoystick, 7);
        m_OperatorButton_8 = new JoystickButton(m_OperatorJoystick, 8);
        m_OperatorButton_9 = new JoystickButton(m_OperatorJoystick, 9);
        m_OperatorButton_10 = new JoystickButton(m_OperatorJoystick, 10);
        m_OperatorButton_11 = new JoystickButton(m_OperatorJoystick, 11);
        m_OperatorButton_12 = new JoystickButton(m_OperatorJoystick, 12);

        m_OperatorButton_Trigger.whenPressed(new LaunchDisk());
        m_OperatorButton_Side.whenPressed(new SetElevation(Shooter.loadingAngle));
        m_OperatorButton_3.whenPressed(new SetElevation(Shooter.climbAngle));
        m_OperatorButton_4.whenPressed(new AdjustElevation(-5.0));
        m_OperatorButton_5.whenPressed(new SetElevation(Shooter.basicShotAngle));
        m_OperatorButton_6.whenPressed(new AdjustElevation(5.0));
        m_OperatorButton_8.whenPressed(new ExampleCommand());

        m_OperatorButton_7.whenPressed(new SetColor(Shooter.Color.Red));
        m_OperatorButton_9.whenPressed(new SetColor(Shooter.Color.Blue));
        m_OperatorButton_11.whenPressed(new SetColor(Shooter.Color.White));

        m_OperatorButton_10.whenPressed(new ExampleCommand());
        m_OperatorButton_12.whenPressed(new ReadJag(Shooter.shooterElevation, false));
        m_OperatorButton_12.whenPressed(new ReadJag(Drive.driveFL, false));
        m_OperatorButton_12.whenPressed(new ReadJag(Drive.driveFR, false));
        m_OperatorButton_12.whenPressed(new ReadJag(Drive.driveRL, false));
        m_OperatorButton_12.whenPressed(new ReadJag(Drive.driveRR, false));
    }

    public LogitechController getDriverController() {
        return m_FirstGamePad;
    }

    public Joystick getOperatorJoystick() {
        return m_OperatorJoystick;
    }
}
