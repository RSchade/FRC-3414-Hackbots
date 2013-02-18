/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.frameworks;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import farmington.ultimateascent.IRobot;

/**
 * Controls the loader wheel and limit switch system.
 * @author 3414
 */
public class ShooterLoader implements IRobot {
    
    private Relay loaderWheel;
    private DigitalInput loaderSensor;
    private boolean bayIsFull;
    private boolean frisbeeIsDetected;
    private boolean logicControlA;
    //state: 0 = inactive, 1 = waiting for frisbee, 2 = loading
    private int state;
    private Waiter loaderControl;
    private boolean logicControlB;
    private boolean logicControlC;
    
    /**
     * Main constructor for ShooterLoader.
     * @param loaderWheelRelay relay slot for the loader wheel relay
     * @param loaderSensorSlot DIO slot for the limit switch
     */
    public ShooterLoader(int loaderWheelRelay, int loaderSensorSlot) {
        loaderWheel = new Relay(loaderWheelRelay);
        loaderSensor = new DigitalInput(loaderSensorSlot);
        bayIsFull = false;
        logicControlA = false;
        state = 0;
        loaderControl = new Waiter();
        logicControlB = false;
        logicControlC = true;
    }
    
    /**
     * Controls the loader wheel based on logic.
     * @param pistonIsExtended piston.get() method from the shooter piston
     */
    public void updateLoader(boolean pistonIsExtended) {
        //Inverts input from the loader sensor because sensor normally returns true.
        frisbeeIsDetected = !loaderSensor.get();
        
        if (pistonIsExtended) {
            logicControlA = true;
            logicControlC = false;
        } else {
            if (logicControlA) {
                loaderControl.waitXLoops(30);
                state = 0;
                logicControlA = false;
                logicControlB = true;
            }
            if (logicControlB) {
                if (loaderControl.timeUp()) {
                    state = 1;
                    logicControlB = false;
                    logicControlC = true;
                }
            }
            if (logicControlC) {
                if (state == 1 && frisbeeIsDetected) {
                    loaderWheel.set(RELAY_ON);
                    loaderControl.waitXLoops(20);
                    state = 2;
                } else if (loaderControl.timeUp() && state == 2) {
                    loaderWheel.set(RELAY_OFF);
                    state = 0;
                }
            }
        }
    }
    
    /**
     * Checks to see if there is a frisbee waiting to be loaded.
     * @return true if the limit switch is tripped
     */
    public boolean getFrisbeeSensor() {
        return loaderSensor.get();
    }
}
