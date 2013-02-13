/*
 * Digital Input
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * created by: Rahul Yalamanchili and Cooper Riehl. and SB
 */
public class DIOSensor implements IRobot {
    
    private DigitalInput sensor;
    
    public DIOSensor(int DIOSlot) {
        sensor = new DigitalInput(DIOSlot);
    }
    
    public boolean get() {
        return sensor.get();
    }
}
