
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.AztechRobot;
import edu.wpi.first.wpilibj.templates.subsystems.VisionSubsystem;

/**
 *
 * @author bradmiller
 */
public class AutonomousCommand extends CommandBase {

    VisionSubsystem vision_;
//    MotorControlAssembly tiltAssembly_;
//    RobotDrive drive_;
    
    public AutonomousCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        vision_ = AztechRobot.vision; 
//        tiltAssembly_ = RobotMap.tiltAssembly; 
//        drive_ = RobotMap.driveMechanumDrive;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        vision_.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
            if(vision_.goalFound() != VisionSubsystem.GOAL__NONE) {
                switch(vision_.goalFound())
                {
                    case VisionSubsystem.GOAL__HIGH:
                        System.out.println("Found High Goal");
                        break;
                    case VisionSubsystem.GOAL__MIDDLE:
                        System.out.println("Found Middle Goal");
                        break;
                    case VisionSubsystem.GOAL__LOW:
                        System.out.println("Found Low Goal");
                        break;
                }

                //System.out.println("angle = "+tiltAssembly_.getAngle()+", vision = "+vision_.getYError());
//                tiltAssembly_.runFromInput(vision_.getYErrorNorm()*.8);
//                drive_.mecanumDrive_Polar(0.0, 0.0, vision_.getXError()/28.0);
//                RobotMap.driveFLMotor.set(vision_.getXErrorNorm()*.8);
            }
            else
            {
//                tiltAssembly_.runFromInput(0);
//                drive_.mecanumDrive_Polar(0.0, 0.0, vision_.getXError()/28.0);
//                RobotMap.driveFLMotor.set(0.0);
            }    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        System.out.println("Autonomous Command ended!!!!!!!! Disabling Vision!!!!");
         vision_.disable();    
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
