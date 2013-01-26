/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import  com.sun.squawk.util.MathUtils;
import  edu.wpi.first.wpilibj.AnalogChannel;
import  edu.wpi.first.wpilibj.SensorBase;
import  edu.wpi.first.wpilibj.PIDSource;

/**
 *
 * @author charris
 */
public class Angle extends SensorBase implements PIDSource {

    private final double BIAS_VOLTAGE = 2.25;
    private AnalogChannel sinChannel;
    private AnalogChannel cosChannel;

    public Angle( int slotParam, int sinChannelParam, int cosChannelParam)
    {
        sinChannel = new AnalogChannel( slotParam, sinChannelParam );
        cosChannel = new AnalogChannel( slotParam, cosChannelParam );
    }

    protected double getCos()
    {
        return cosChannel.getVoltage() - BIAS_VOLTAGE;
    }

    protected double getSin()
    {
        return sinChannel.getVoltage() - BIAS_VOLTAGE;
    }

    public double getAngle()
    {
        // calculate the angle
        double theta = MathUtils.atan2( getSin(), getCos() );
        // convert to degrees
        return Math.toDegrees(theta) + 180.0;
    }

    /**
     * Get the angle of the encoder for use with PIDControllers
     * @return the current angle according to the encoder
     */
    public double pidGet() 
    {
        return getAngle();
    }


}