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
    
    Relay loaderWheel;
    DigitalInput loaderSensor;
    boolean bayIsFull;
    boolean isFrisbeeDetected;
    Waiter loaderControl;
    
    /**
     * Main constructor for ShooterLoader.
     * @param loaderWheelRelay relay slot for the loader wheel relay
     * @param loaderSensorSlot DIO slot for the limit switch
     */
    public ShooterLoader(int loaderWheelRelay, int loaderSensorSlot) {
        loaderWheel = new Relay(loaderWheelRelay);
        loaderSensor = new DigitalInput(loaderSensorSlot);
        bayIsFull = false;
        loaderControl = new Waiter();
    }
    
    /**
     * Controls the loader wheel based on logic.
     * @param pistonIsExtended piston.get() method from the shooter piston
     */
    public void updateLoader(boolean pistonIsExtended) {
        //Inverts input from the loader sensor because sensor normally returns true.
        isFrisbeeDetected = !loaderSensor.get();
        
        
        if (isFrisbeeDetected && !bayIsFull) {
            loaderWheel.set(RELAY_ON);
            loaderControl.waitXLoops(20);   //FIXME: this value needs to be tuned for X number of ticks for one frisbee to be loaded
        }
        
        if (bayIsFull) {
            bayIsFull = !pistonIsExtended;
        } else {
            bayIsFull = isFrisbeeDetected;
        }
        
        if (loaderControl.timeUp()) {
            loaderWheel.set(RELAY_OFF);
        } else {
            loaderWheel.set(RELAY_FORWARD);
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
