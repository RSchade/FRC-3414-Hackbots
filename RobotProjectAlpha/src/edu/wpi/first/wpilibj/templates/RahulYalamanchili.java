/*
 * Digital Input
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Dashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * created by: Rahul Yalamanchili
 */
public class RahulYalamanchili {
    
    DigitalInput PhotoSensor = new DigitalInput(1);
    
    public boolean get()
    {
        return PhotoSensor.get();
    }

   public void getDashboard(){
       SmartDashboard.getBoolean("PhotoSensor Value;", PhotoSensor.get);
   }
            
}
