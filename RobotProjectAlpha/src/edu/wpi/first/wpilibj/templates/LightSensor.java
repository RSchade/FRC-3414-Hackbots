/*
 * Digital Input
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * created by: Rahul Yalamanchili
 */
public class LightSensor {
    
    DigitalInput photosensor = new DigitalInput(1);
    Talon loaderWheel = new Talon(1);
    
    public boolean get()
    {
        return photosensor.get();
    }

   public void getDashboard(){
       SmartDashboard.putBoolean("PhotoSensor Value: ", photosensor.get());
   }
            
   public void startMotor()
   {
    if (photosensor.get() == true){
        loaderWheel.set(0.5);
    } else {
        loaderWheel.set(0.0);
    }
   
   }
   
}
