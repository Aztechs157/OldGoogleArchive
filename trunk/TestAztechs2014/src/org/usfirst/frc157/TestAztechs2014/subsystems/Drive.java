// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.usfirst.frc157.TestAztechs2014.subsystems;
import org.usfirst.frc157.TestAztechs2014.RobotMap;
import org.usfirst.frc157.TestAztechs2014.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.can.*;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class Drive extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    CANJaguar frontLeft = RobotMap.driveFrontLeftSpeedController;
    CANJaguar frontRight = RobotMap.driveFrontRightSpeedController;
    CANJaguar backLeft = RobotMap.driveBackLeftSpeedController;
    CANJaguar backRight = RobotMap.driveBackRightSpeedController;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	setDefaultCommand(new Move());
    }
    
    public void setupJagForSpeedControl(CANJaguar jag) {
        if (jag != null) {
            int tries = 0;
            boolean failed = false;
            do {
                try {
                    jag.setVoltageRampRate(0.1);
                    jag.configNeutralMode(CANJaguar.NeutralMode.kCoast);
                    jag.changeControlMode(CANJaguar.ControlMode.kSpeed);
                    jag.setSpeedReference(CANJaguar.SpeedReference.kQuadEncoder);
                    jag.configEncoderCodesPerRev(360 * 3);
                    jag.setPID(.6, .2, 0);
                    jag.enableControl();
                } catch (CANTimeoutException ex) {
                    failed = true;
                    System.out.println("Exception " + tries + " while configuring speed");
                }
            } while (failed && (tries++ < RobotMap.m_kMaxCANRetries));

        }
    }
    
    public void setupJagForVoltageControl(CANJaguar jag) {
        if (jag != null) {
            int tries = 0;
            boolean failed = false;
            do {
                try {
                    jag.setVoltageRampRate(0.02);
                    jag.configNeutralMode(CANJaguar.NeutralMode.kCoast);
                    jag.changeControlMode(CANJaguar.ControlMode.kVoltage);
                    jag.enableControl();
                } catch (CANTimeoutException ex) {
                    failed = true;
                    System.out.println("Exception " + tries + " while configuring position");
                }
            } while (failed && (tries++ < RobotMap.m_kMaxCANRetries));

        }
        
    }
    
    public void drive(double left, double right) {

//        if (!  (   (Math.abs(x) < 0.1)
//                && (Math.abs(y) < 0.1)
//                && (Math.abs(rotation) < 0.1))) 
        {
            //        if (mechanumDrive != null) {
//            mechanumDrive.mecanumDrive_Cartesian(x, y, rotation, 0);
// 

//        System.out.println("xGo:" + xGo + "  yGo:" + yGo + "  rotGo:" + rotGo);
//        System.out.println("FL: " + ( -xGo + -rotGo +  yGo) + "      FR: " + ( -xGo + -rotGo + -yGo));
//        System.out.println("RL: " + (  xGo + -rotGo +  yGo) + "      RR: " + (  xGo + -rotGo + -yGo));

            byte group = 22;
            try {
                if(null!= frontLeft) frontLeft.setX(left, group);
                if(null!= frontRight) frontRight.setX(right, group);
                if(null!= backLeft) backLeft.setX(left, group);
                if(null!= backRight) backRight.setX(right, group);
                CANJaguar.updateSyncGroup(group);
            } catch (CANTimeoutException ex) {
            System.out.println("oops");
            }
        }
    }
}
