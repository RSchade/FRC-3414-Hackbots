/*
 * Digital Input
 */
package farmington.frameworks;

import edu.wpi.first.wpilibj.DigitalInput;
import farmington.ultimateascent.IRobot;

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
