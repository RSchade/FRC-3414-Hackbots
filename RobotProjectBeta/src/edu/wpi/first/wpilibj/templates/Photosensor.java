/*
 * Digital Input
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * created by: Rahul Yalamanchili and Cooper Riehl. and SB
 */
public class Photosensor implements IRobot {
    
    private DigitalInput photosensor;
    
    
    public Photosensor(int photosensorSlot) {
        photosensor = new DigitalInput(photosensorSlot);
    }
    
    public boolean get() {
        return photosensor.get();
    }
}
