/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * Created by Shrihari Bhaskaramurthi
 */
package deprecated;

import edu.wpi.first.wpilibj.Accelerometer;

/**
 *
 * @author 3414 Hackbots
 */
public class CustomAccelerometer {
    Accelerometer k = new Accelerometer(6);
    
    //public double setZero(double zero)
    //{
        //k.setZero(0);
    //}
    public double getAcceleration()
    {
        System.out.println(k.getAcceleration());
        return k.getAcceleration();
    }
    
   // public double pidGet()
    //{
        //return k.pidGet();
    //}
    
   // public void setSensitivity()
   // {
   //     k.setSensitivity(30);
    //}
}

