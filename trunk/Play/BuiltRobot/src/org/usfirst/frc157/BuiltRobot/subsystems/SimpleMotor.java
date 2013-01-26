// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.usfirst.frc157.BuiltRobot.subsystems;
import org.usfirst.frc157.BuiltRobot.RobotMap;
import org.usfirst.frc157.BuiltRobot.commands.*;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class SimpleMotor extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    CANJaguar cimJag = RobotMap.simpleMotorCimJag;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    CANJaguar winJag = RobotMap.simpleMotorWinJag;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
//        setDefaultCommand(new MotorGo());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void go()
    {        
        System.out.println("SimpleMotor.go()");
        try 
        {
          cimJag.setX(-1.0); 
          winJag.setX(-1.0); 
        }
        catch (Exception all) {
            System.out.println("*** SimpleMotor.go() OOPS!");
        }
    }
    public void setSpeed(double speed)
    {
        try 
        {
          cimJag.setX(speed); 
          winJag.setX(speed); 
        }
        catch (Exception all) {
            System.out.println("*** SimpleMotor.setSpeed() OOPS!");
        }
    }
    
    public void stop()
    {
        System.out.println("SimpleMotor.stop()");
        try 
        {
          cimJag.setX(0); 
          winJag.setX(0); 
        }
        catch (Exception all) {
            System.out.println("*** SimpleMotor.go() OOPS!");
        }
    }
}
