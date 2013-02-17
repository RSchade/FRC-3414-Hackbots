/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.frameworks;

import edu.wpi.first.wpilibj.Relay;
import farmington.ultimateascent.IRobot;

/**
 *
 * @author Robotics
 */
public class ShooterLoader implements IRobot {
    
    Relay loaderWheel;
    DIOSensor loaderSensor;
    boolean bayIsFull;
    boolean isFrisbeePassing;
    Waiter loaderControl;
    
    public ShooterLoader(int loaderWheelRelay, int loaderSensorSlot) {
        loaderWheel = new Relay(loaderWheelRelay);
        loaderSensor = new DIOSensor(loaderSensorSlot);
        bayIsFull = false;
        loaderControl = new Waiter();
    }
    
    public void updateLoader(boolean pistonIsExtended) {
        //Inverts input from the loader sensor
        isFrisbeePassing = !loaderSensor.get();
        
        
        if (isFrisbeePassing && !bayIsFull) {
            loaderWheel.set(RELAY_ON);
            loaderControl.waitXLoops(20);   //FIXME: this value needs to be tuned for X number of ticks for one frisbee to be loaded
        }
        
        if (bayIsFull) {
            bayIsFull = !pistonIsExtended;
        } else {
            bayIsFull = isFrisbeePassing;
        }
        
        if (loaderControl.timeUp()) {
            loaderWheel.set(RELAY_OFF);
        } else {
            loaderWheel.set(RELAY_ON);
        }
    }
    
    public boolean getFrisbeeSensor() {
        return loaderSensor.get();
    }
}
