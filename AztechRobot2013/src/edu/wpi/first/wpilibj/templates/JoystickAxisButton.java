package edu.wpi.first.wpilibj.templates;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 *
 * @author matt
 */
public class JoystickAxisButton extends Button {

    Joystick m_controller;
    int m_axisNumber;
    boolean m_directionPositive;

    /**
     * Create a joystick button for triggering commands
     *
     * @param joystick The joystick object that has the button
     * @param buttonNumber The button number (see {@link Joystick#getRawButton(int)
     * }
     */
    public JoystickAxisButton(Joystick joystick, int axisNumber, boolean positive) {
        m_controller = joystick;
        m_axisNumber = axisNumber;
        m_directionPositive = positive;
    }

    /**
     * Gets the value of the joystick button
     *
     * @return The value of the joystick button
     */
    public boolean get() {
        if (m_directionPositive) {
            return (m_controller.getRawAxis(m_axisNumber) > 0.5);
        } else {
            return (m_controller.getRawAxis(m_axisNumber) < -0.5);
        }
    }
}
