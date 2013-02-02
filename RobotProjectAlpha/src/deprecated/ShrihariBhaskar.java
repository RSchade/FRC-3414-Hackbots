/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Accelerometer;

/**
 *
 * @author 3414 Hackbots
 */
public class ShrihariBhaskar {
    Accelerometer k = new Accelerometer(1);
    double n;
    
    public double getAcceleration()
    {
        return k.getAcceleration();
    }
    
    public double pidGet()
    {
        return k.pidGet();
    }
    
    public void setSensitivity()
    {
        k.setSensitivity(n);
    }
}

























































