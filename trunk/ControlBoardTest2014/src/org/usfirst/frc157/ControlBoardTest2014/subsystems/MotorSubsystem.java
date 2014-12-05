// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc157.ControlBoardTest2014.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import org.usfirst.frc157.ControlBoardTest2014.RobotMap;
import org.usfirst.frc157.ControlBoardTest2014.commands.*;
import edu.wpi.first.wpilibj.can.*;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc157.ControlBoardTest2014.Robot;


/**
 *
 */
public class MotorSubsystem extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    CANJaguar jag1 = RobotMap.motorSubsystemCANJaguar1;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        
        Robot.setupJagForVoltageControl(jag1);
    }
    
    public void setJagVoltage(double voltage) {
//        System.out.println(left + " - " + right);
        try {
            if (null != jag1) {
                jag1.setX(voltage);
            }
        } catch (CANTimeoutException ex) {
//            System.out.println("tank Drive Timeout");
//            ex.printStackTrace();
        }
    }
}

