package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * created by Rahul Yalamanchili and Shrihari Baskaramurthi
 */
public class OldRobotShooter {
    
    Joystick n = new Joystick(1);
    Jaguar k = new Jaguar(1);
    Jaguar m = new Jaguar(2);
    
    public void axis()
    {
       if(n.getRawAxis(2)>=.05)
       {
           k.set(1);
           m.set(1);
       }
    }
    
    public void negativeAxis()
    {
        if(n.getRawAxis(2)<=.05)
        {
            k.set(-1);
            m.set(-1);
        }
    }
}

