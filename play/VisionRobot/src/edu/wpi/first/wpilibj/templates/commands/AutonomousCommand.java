
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.templates.MotorControlAssembly;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.RobotTemplate;
import edu.wpi.first.wpilibj.templates.subsystems.VisionSubsystem;

/**
 *
 * @author bradmiller
 */
public class AutonomousCommand extends CommandBase {

    VisionSubsystem vision_;
    MotorControlAssembly tiltAssembly_;
    
    public AutonomousCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        vision_ = RobotTemplate.visionSubsystem; 
        tiltAssembly_ = RobotMap.tiltAssembly_; 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        vision_.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        
            if(vision_.goalFound())
            {
                //System.out.println("angle = "+tiltAssembly_.getAngle()+", vision = "+vision_.getYError());
                tiltAssembly_.runFromInput(vision_.getYError()/34.0);
                
            }
            else
            {
                tiltAssembly_.runFromInput(0);
            }
    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
         vision_.disable();
    
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
