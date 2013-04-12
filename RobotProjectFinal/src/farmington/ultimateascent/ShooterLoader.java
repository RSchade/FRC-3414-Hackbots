/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.ultimateascent;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;

/**
 * Controls the loader wheel and limit switch system.
 * @author 3414
 */
public class ShooterLoader implements IRobot {
    
    private Relay loaderWheel;
    private DigitalInput loaderSensor;
    private DigitalInput chamberSensor;
    private boolean waiting;
    private Waiter loaderControl;
    
    /**
     * Main constructor for ShooterLoader.
     * @param loaderWheelRelay relay slot for the loader wheel relay
     * @param loaderSensorSlot DIO slot for the limit switch
     */
    public ShooterLoader(int loaderWheelRelay, int loaderSensorSlot, int chamberSensorSlot) {
        loaderWheel = new Relay(loaderWheelRelay);
        loaderSensor = new DigitalInput(loaderSensorSlot);
        chamberSensor = new DigitalInput(chamberSensorSlot);
        loaderControl = new Waiter();
        waiting = false;
    }
    
    public void updateLoader(boolean manualTrigger) {
        
        if (manualTrigger) {
            this.turnOn();
        } else {
            this.turnOff();
            
            
            /*
             * This checks to see if a frisbee is not detected constantly for 200ms.
             * If a frisbee has not been detected at the end of this time, it loads
             * one from the loader. This is to prevent double-loading because of jostling
             */
            if (loaderSensor.get()) {
                // If there is not a frisbee in the chamber, start waiting
                if (!getChamberSensor() && !waiting) {
                    loaderControl.waitXms(700);   //DEBUG: 200 ms when the old sensor works again
                    waiting = true;
                }
                // If we are waiting and a frisbee appears, cancel the wait.
                if (loaderControl.isWaiting() && getChamberSensor()) {
                    waiting = false;
                    loaderControl.reset();
                }
                /* 
                 * If we reach the end of the wait and the timer has not been cancelled, start loading.
                 * Load until a frisbee is detected in the chamber, then reset the logic to detect missing frisbees
                 */
                if (loaderControl.timeUp() && waiting) {
                    if (!getChamberSensor()) {
                        this.turnOn();
                    } else {
                        waiting = false;
                    }
                }
            } else {
                this.turnOn();
            }
        }
    }
    
    public void manualControl(boolean manualTrigger) {
        
        if (manualTrigger) {
            this.turnOn();
        } else {
            this.turnOff();
        }
    }
    
    public void turnOn() {
        loaderWheel.set(RELAY_FORWARD);
    }
    
    public void turnOff() {
        loaderWheel.set(RELAY_OFF);
    }
    
    public boolean getChamberSensor() {
        return !chamberSensor.get();
    }
    
    public boolean getLoaderSensor() {
        return loaderSensor.get();
    }
    
    public void reset() {
        loaderControl.reset();
    }
}
