/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc157.ControlBoardTest2014.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc157.ControlBoardTest2014.RobotMap;

/**
 *
 * @author Teju
 */
public class PneumaticSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public static Compressor compressor;
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        if (compressor == null) {
            compressor = new Compressor(RobotMap.DIGITAL_PORT_PressureSwitch, RobotMap.RELAY_PORT_Compressor);
        }
        compressor.start();
    }
}