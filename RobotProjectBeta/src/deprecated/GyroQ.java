
package deprecated;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Timer;
import farmington.ultimateascent.IRobot;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shrihari, Alex
 */

public class GyroQ implements IRobot{
    Gyro myGyro = new Gyro(7);
    Timer timer = new Timer();
    
    public void Callibrate()
    {
        myGyro.reset();
    }
    
    public double Angle()
    {
        return myGyro.getAngle();
    }
}
