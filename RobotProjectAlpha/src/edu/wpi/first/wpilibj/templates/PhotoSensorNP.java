
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Nathan Peterson
 */
public class PhotoSensorNP {
    
    DigitalInput lights = new DigitalInput(1);
    

    public boolean get(){
        return lights.get();
    }
    
    public void getDashboard(){
        SmartDashboard.putBoolean("Photosensor value", lights.get());
    }
    
}
