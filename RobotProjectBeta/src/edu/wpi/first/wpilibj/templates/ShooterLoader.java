/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author Robotics
 */
public class ShooterLoader implements IRobot {
    
    Relay loaderWheel;
    DIOSensor loaderSensor;
    boolean bayIsFull;
    Waiter loaderControl;
    
    public ShooterLoader(int loaderWheelRelay, int loaderSensorSlot) {
        loaderWheel = new Relay(loaderWheelRelay);
        loaderSensor = new DIOSensor(loaderSensorSlot);
        bayIsFull = false;
        loaderControl = new Waiter();
    }
    
    public void updateLoader(boolean pistonIsExtended) {
        if (loaderSensor.get() && !bayIsFull) {
            loaderWheel.set(RELAY_ON);
            loaderControl.waitXLoops(20);   //FIXME: this value needs to be tuned for X number of ticks for one frisbee to be loaded
        }
        //
        if (bayIsFull) {
            bayIsFull = !pistonIsExtended;
        } else {
            bayIsFull = loaderSensor.get();
        }
        
        if (loaderControl.timeUp()) {
            loaderWheel.set(RELAY_OFF);
        } else {
            loaderWheel.set(RELAY_ON);
        }
    }
}
