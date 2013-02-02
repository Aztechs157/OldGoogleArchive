
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;



public class VisionSampleProject2013 extends SimpleRobot {

    VisionThread vision_;
    Jaguar yMotor_;
    MotorControlAssembly yControl_;
 
    public void robotInit() {
        System.out.println("robotInit() ================================================================================");
        yMotor_ = new Jaguar(1);

        yControl_ = new MotorControlAssembly(yMotor_, 2);
        yControl_.setRange(300, 900,0,45);
        yControl_.setInvertedMotor(false);

        vision_ = new VisionThread();
        vision_.start();
    }

    public void autonomous() {
        while (isAutonomous() && isEnabled()) {
            vision_.enable();
            if(vision_.goalFound())
            {
                System.out.println("angle = "+yControl_.getAngle()+", vision = "+vision_.getYError());
                yControl_.runFromInput(vision_.getYError()/28.0);
            }
            else
            {
                yControl_.runFromInput(0);
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
        