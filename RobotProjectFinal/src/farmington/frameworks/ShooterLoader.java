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
    public ShooterLoader(int loaderWheelRelay, int loaderSensorSlot, int oneInTheChamberSensorSlot) {
        loaderWheel = new Relay(loaderWheelRelay);
        loaderSensor = new DigitalInput(loaderSensorSlot);
        chamberSensor = new DigitalInput(oneInTheChamberSensorSlot);
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
                    loaderControl.waitXLoops(40);
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
    
    /**
     * Controls the loader wheel based on logic.
     * @param pistonIsExtended piston.get() method from the shooter piston
     */
//    public void updateLoader(boolean pistonIsExtended, boolean manualTrigger) {
//        //Inverts input from the loader sensor because sensor returns false when a frisbee is detected.
//        frisbeeIsDetected = !loaderSensor.get();
//        
//        if (pistonIsExtended) {
//            logicControlA = true;
//            logicControlC = false;
//        } else {
//            if (logicControlA) {
//                loaderControl.waitXLoops(25);   //Waits 500 ms for the loading bay to be ready
//                state = 0;
//                logicControlA = false;
//                logicControlB = true;
//            }
//            if (logicControlB && loaderControl.timeUp()) {
//                state = 1;
//                logicControlB = false;
//                logicControlC = true;
//            }
//            if (logicControlC) {
//                if (state == 1 && frisbeeIsDetected) {
//                    this.turnOn();
//                    loaderControl.waitXLoops(40);               //Turns on the loader for 40*20 = 800 ms
//                    state = 2;
//                }
//                if (loaderControl.timeUp() && state == 2) {
//                    this.turnOff();
//                    state = 0;
//                }
//                if (state == 0) {
//                    if (manualTrigger) {
//                        this.turnOn();
//                    } else {
//                        this.turnOff();
//                    }
//                }
//            }
//        }
//        SmartDashboard.putBoolean("pistonIsExtended", pistonIsExtended);
//        SmartDashboard.putNumber("state", state);
//        SmartDashboard.putBoolean("loaderControl", loaderControl.timeUp());
//        SmartDashboard.putBoolean("logicControlA", logicControlA);
//        SmartDashboard.putBoolean("logicControlB", logicControlB);
//        SmartDashboard.putBoolean("logicControlC", logicControlC);
//    }
    
    public void turnOn() {
        loaderWheel.set(RELAY_FORWARD);
    }
    
    public void turnOff() {
        loaderWheel.set(RELAY_OFF);
    }
    
    public boolean getChamberSensor() {
        return chamberSensor.get();
    }
    
    public void reset() {
        loaderControl.reset();
    }
}
