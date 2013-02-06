/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author aztechs
 */
public class MotorControlAssembly {
 
    private SpeedController motor_;
    private Potentiometer pot_;
    private PIDController pid_;
    private DigitalInput limitLow_;
    private DigitalInput limitHigh_;
    private int motorDirection_;
    private double tolerance_;
    private double speed_;
	
    private final int POT_MIN_VALUE = 0;
    private final int POT_MAX_VALUE = 1023;
    private final double POT_MIN_ANGLE = -360.0;
    private final double POT_MAX_ANGLE = 360.0;

    private final double POT_TOLERANCE = .05; 
    
    public MotorControlAssembly(SpeedController motor) 
    {
        motor_ = motor;
        pot_ = null;
        limitLow_ = null;
        limitHigh_ = null;
        motorDirection_ = 1;
        speed_ = 1;
    }
	
    public MotorControlAssembly(SpeedController motor, 
                                int potChannel)
    {
        motor_ = motor;
        pot_ = new Potentiometer(potChannel, POT_MIN_VALUE, POT_MAX_VALUE, POT_MIN_ANGLE, POT_MAX_ANGLE);
        limitLow_ = null;
        limitHigh_ = null;
        motorDirection_ = 1;
        speed_ = 1;
    }
	
    public MotorControlAssembly(SpeedController motor, 
                                int potChannel, 
                                int limitLowChannel, 
                                int limitHighChannel)
    {
        motor_ = motor;
        pot_ = new Potentiometer(potChannel, POT_MIN_VALUE, POT_MAX_VALUE, POT_MIN_ANGLE, POT_MAX_ANGLE);
        limitLow_ = new DigitalInput(limitLowChannel);
        limitHigh_ = new DigitalInput(limitHighChannel);
        motorDirection_ = 1;
        speed_ = 1;
    }
        
    public SpeedController getMotor()
    {
        return motor_;
    }
	
    public Potentiometer getPot()
    {
        return pot_;
    }        

    public void setInvertedMotor(boolean isInverted)
    {
	motorDirection_ = isInverted ? -1 : 1;
    }

    public void setTolerance(int tolerance)
    {
	tolerance_ = tolerance;
    }

    public void setSpeed(double speed)
    {
	speed_ = speed;
    }

    public void setRange(int minValue, int maxValue, double minAngle, double maxAngle)
    {
        if (pot_ != null)
        {
            pot_.setRange(minValue, maxValue);
            pot_.setRangeAngle(minAngle, maxAngle);
        }
    }

    public boolean getLimit(double input)
    {
	boolean atLimit = false;
	if(input < -0.0)
	{
            if((limitLow_ != null) && (limitLow_.get() == false))
            {
                atLimit = true;
            }
            if((pot_ != null) && (pot_.get() <= -1.0 + POT_TOLERANCE))
            {
                atLimit = true;
            }
	}
	else if(input > 0.0)
	{
            if((limitHigh_ != null) && (limitHigh_.get() == false))
            {
                atLimit = true;
            }
            if((pot_ != null) && pot_.get() >= 1.0 - POT_TOLERANCE)
            {
                atLimit = true;
            }
	}
	return atLimit;
    }

    public double getAngle()
    {
	return pot_.getAngle();
    }

    public void runFromInput(double input)
    {
	if(getLimit(input))
	{
            motor_.set(0);	
	}
	else
	{
            motor_.set(input * motorDirection_);
	}
    }

    public boolean runToAngle(double angle)
    {
	double dist = (pot_.getAngle() - angle);
	if(Math.abs(dist) > tolerance_)
	{
            runFromInput(-dist / (pot_.getRangeAngle() / speed_));
//            printf("Running to %f from %f, %f distance\n", angle, pot_.getAngle(), dist);
            return false;
	}
	else
	{
//            printf("Arrived at %f from %f, %f distance\n", angle, pot_.getAngle(), dist);
            return true;
	}
	
//	if(!pid_p_->IsEnabled())
//	{
//		pid_p_->Enable();
//	}
//	pid_p_->SetSetpoint(angle);
//	if(pid_p_->OnTarget())
//	{
//		pid_p_->Disable();
//		return true;
//	}
//	else
//	{
//		return false;
//	}
}

    public void CancelRunToAngle()
    {
        //	pid_->Disable();
    }
}
