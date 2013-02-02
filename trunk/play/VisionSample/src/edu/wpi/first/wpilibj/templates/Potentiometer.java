/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.PIDSource;

/**
 *
 * @author aztechs
 */
public class Potentiometer extends AnalogChannel implements PIDSource {
    private int min_, max_, range_;
    private double minAngle_, maxAngle_, rangeAngle_;

    public Potentiometer(int channel, int min, int max, double minAngle, double maxAngle)
    {
        super(channel);
	setRange(min, max);
	setRangeAngle(minAngle, maxAngle);
    }

    public double get()
    {
	double value = (getRaw() - (double)min_) / range_ * 2 - 1;
	if(value > 1.0)
        {
            value = 1.0;
        }
	if(value < -1.0)
        {
            value = -1.0;
        }

	return value;
    }

    public double getAngle()
    {
	double angle = (getRaw() - (double)min_) / range_ * rangeAngle_ + minAngle_;
	if(angle > maxAngle_)
        {
		angle = maxAngle_;
        }
	if(angle < minAngle_)
        {
		angle = minAngle_;
        }
        
	return angle;
    }

    public int getRaw()
    {
        return getValue();
    }

    public double pidGet()
    {
        double angle = (super.pidGet() - (double)min_) / range_ * rangeAngle_ + minAngle_;
	if(angle > maxAngle_)
        {
            angle = maxAngle_;
        }
	if(angle < minAngle_)
        {
            angle = minAngle_;
        }

        return angle;
    }

    public final void setRange(int min, int max)
    {
	min_ = min;
	max_ = max;
	range_ = max - min;
    }

    public final void setRangeAngle(double min, double max)
    {
	minAngle_ = min;
	maxAngle_ = max;
	rangeAngle_ = max - min;
    }

    public int getRange()
    {
	return range_;
    }

    public double getRangeAngle()
    {
	return rangeAngle_;
    }

}
