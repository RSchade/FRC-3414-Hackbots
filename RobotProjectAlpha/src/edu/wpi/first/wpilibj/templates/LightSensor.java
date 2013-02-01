/*
 * Digital Input
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * created by: Rahul Yalamanchili
 */
public class LightSensor {
    
    DigitalInput photosensor = new DigitalInput(1);
    
    public boolean get()
    {
        return photosensor.get();
    }

   public void getDashboard(){
       SmartDashboard.putBoolean("PhotoSensor Value;", photosensor.get());
   }
            
}
