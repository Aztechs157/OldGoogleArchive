
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;



public class VisionSampleProject2013 extends SimpleRobot {

    VisionThread vision_;
    Potentiometer yAngle_;
    Victor yMotor_;
    PIDController yPid_;
 
    public void robotInit() {
        System.out.println("robotInit() ================================================================================");
        yAngle_ = new Potentiometer(2,0,1024,-135,135);
        yMotor_ = new Victor(1);
        yPid_ = new PIDController(1,0,0,yAngle_,yMotor_);
        vision_ = new VisionThread();
        vision_.start();
    }

    public void autonomous() {
        while (isAutonomous() && isEnabled()) {
            vision_.enable();
            if(vision_.goalFound())
            {
                System.out.println("pot = "+yAngle_.getAngle()+", vision = "+vision_.getYError());
                yMotor_.set(vision_.getYError() / 28.0);
//                    yPid_.setSetpoint(yAngle_.getAngle() + vision_.getYAngle());
 //                   if(!yPid_.isEnable())
 //                   {
 //                       yPid_.enable();
 //                   }
            }
            else
            {
//                    yPid_.disable();
                yMotor_.set(0.0);
                System.out.println("Cannot see goal!");
            }
        }
        System.out.println("Out of loop... Finished");
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {
            Timer.delay(1);
        }
    }
    

}
        