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
public class Photosensor implements IRobot {
    
    DigitalInput photosensor;
    Talon loaderMotor;
    
    public Photosensor(int photosensorSlot, int loaderSlot) {
        photosensor = new DigitalInput(photosensorSlot);
        loaderMotor = new Talon(loaderSlot);
    }
    
    public boolean get() {
        return photosensor.get();
    }
            
    public void startMotor() {
        if (photosensor.get() == true) {
            loaderMotor.set(SPEED_FORWARD_HALF);
        } else {
            loaderMotor.set(SPEED_STOP);
        }
    }
}
