/*----------------------------------------------------------------------------*/
/*                         Shooter/launcher/aimer/ish                         */
/*                                 Controls                                   */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Accelerometer;

/*
 * @author Alex S. <3
 */

public class RobotTemplate extends SimpleRobot
{

    private Joystick right_stick = new Joystick(1);
    private Joystick left_stick = new Joystick(2);
    private Talon firewheel = new Talon(1);
    private Talon firewheel_2 = new Talon(2);
    private Talon angler = new Talon(3);
    private Solenoid loader = new Solenoid(1);
    private Encoder anglereader[];
    
    private final int CENTER_BUTTON = 3;
    private final int TRIGGER = 1;
    private final int LEFT_BUTTON = 4;
    private final int RIGHT_BUTTON = 5;
    private final int OFF = 0;
    private int SHOOTER_ANGLE_VALUE;
    private final int BOTTOM_OUT = 40;  //These constants need 
    private final int TOP_OUT = -40;    //to be fine tuned
    private final int FULL_FORWARD = 1;
    private final int FULL_REVERSE = -1;
    
    private final double HALF_FORWARD = 0.50;
    private final double HALF_REVERSE = -0.50;

    public void autonomous()
    {
        
    }

    public void operatorControl()
    {

        while (isOperatorControl() && isEnabled())
        {
            controls();
            angle();   
            Timer.delay(0.010);
        }

    }
    
    private void controls()
    {
        if (right_stick.getRawButton(TRIGGER) == true)
        {
            System.out.println("Fire!");
            firewheel.set(FULL_FORWARD);
            firewheel_2.set(FULL_FORWARD);
        } else {
            firewheel.set(OFF);
            firewheel_2.set(OFF);
        }

        if (left_stick.getRawButton(TRIGGER) == true)
        {
            loader.set(true);
        } 
        else {
            loader.set(false);
        }

        if (right_stick.getRawButton(CENTER_BUTTON) == true)
        {
            angler.set(FULL_FORWARD);
            System.out.println("Raising!");
        }

        else  {
            angler.set(OFF);
        }

        if (left_stick.getRawButton(CENTER_BUTTON) == true)
        {
            angler.set(FULL_REVERSE);
            System.out.println("Lowering!");
        }

        if (left_stick.getRawButton(CENTER_BUTTON) == false
                && right_stick.getRawButton(CENTER_BUTTON) == false)
        {
            angler.set(OFF);
        }

    }
    
    private void angle()
    {
        
        anglereader = new Encoder[SHOOTER_ANGLE_VALUE];
        
        System.out.println(SHOOTER_ANGLE_VALUE);
        
        if(SHOOTER_ANGLE_VALUE -10 >= TOP_OUT)
        {
            angler.set(OFF);
        }
        if(SHOOTER_ANGLE_VALUE +10 <= BOTTOM_OUT)
        {
            angler.set(OFF);
        }        
    }

    public void test()
    {
        
    }
    
}
