/*
 * Digital Input
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * created by: Rahul Yalamanchili
 */
public class LightSensor implements IRobot {
    
    DigitalInput photosensor = new DigitalInput(DIO_ONE);
    Talon loaderWheel = new Talon(PWM_SLOT_FIVE);
    
    public boolean get() {
        return photosensor.get();
    }

    public void getDashboard() {
        SmartDashboard.putBoolean("PhotoSensor Value: ", photosensor.get());
    }
            
    public void startMotor() {
        if (photosensor.get() == true) {
            loaderWheel.set(SPEED_FORWARD_HALF);
        } else {
            loaderWheel.set(SPEED_STOP);
        }
    }
}
