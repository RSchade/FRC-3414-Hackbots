/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.frameworks;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import farmington.ultimateascent.IRobot;

/**
 * Controls the loader wheel and limit switch system.
 * @author 3414
 */
public class ShooterLoader implements IRobot {
    
    private Relay loaderWheel;
    private DigitalInput loaderSensor;
    private DigitalInput chamberSensor;
    private boolean logicControlA;
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
        logicControlA = true;
    }
    
    public void updateLoader(boolean manualTrigger) {
        
        //we invert chamberSensor.get() because false = there is a frisbee, and true = not a frisbee
        boolean oneInTheChamber = !chamberSensor.get();
        SmartDashboard.putBoolean("Chamber", chamberSensor.get());
        SmartDashboard.putBoolean("Loader", loaderSensor.get());
        if (manualTrigger) {
            this.turnOn();
        } else {
            this.turnOff();
            
            SmartDashboard.putBoolean("logicControlA", logicControlA);
            SmartDashboard.putBoolean("loadercontrol.timeup", loaderControl.timeUp());
            /*
             * This checks to see if a frisbee is not detected constantly for 1000ms.
             * If a frisbee has not been detected at the end of this time, it loads
             * one from the loader. This is to prevent double-loading because of jostling
             */
            if (loaderSensor.get()) {
                // If there is not a frisbee in the chamber, start waiting
                if (!oneInTheChamber && logicControlA) {
                    loaderControl.waitXLoops(20);   //DEBUG -20 loops
                    logicControlA = false;
                }
                // If we are waiting and a frisbee appears, cancel the wait.
                if (loaderControl.isWaiting() && oneInTheChamber) {
                    logicControlA = true;
                    loaderControl.reset();
                }
                /* 
                 * If we reach the end of the wait and the timer has not been cancelled, start loading.
                 * Load until a frisbee is detected in the chamber, then reset the logic to detect missing frisbees
                 */
                if (loaderControl.timeUp() && !logicControlA) {
                    if (!oneInTheChamber) {
                        this.turnOn();
                    } else {
                        logicControlA = true;
                    }
                }
            } else {
                this.turnOn();
            }
        }
    }
    
    public void turnOn() {
        loaderWheel.set(RELAY_FORWARD);
    }
    
    public void turnOff() {
        loaderWheel.set(RELAY_OFF);
    }
    
    public boolean getChamberSensor() {
        return chamberSensor.get();
    }
    
    public boolean getLoaderSensor() {
        return loaderSensor.get();
    }
    
    public void reset() {
        loaderControl.reset();
    }
}
