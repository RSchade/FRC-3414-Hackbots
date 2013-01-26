package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;

public class ForkLift extends SimpleRobot {

    /* Created by Josh Kavner
     * IN THIS CLASS, ALL METHODS NEED:
     * import edu.wpi.first.wpilibj.Talon;
     * import edu.wpi.first.wpilibj.Joystick;
     * import edu.wpi.first.wpilibj.SimpleRobot;
     * import edu.wpi.first.wpilibj.Timer;
     * 
     * Joystick controller = new Joystick(1);
     * Talon testMotor = new Talon(1);
     */
    
    Joystick controller = new Joystick(1);
    Talon testMotor = new Talon(1);
    
    private int motorPulseUp = 0;
    private boolean motorStateA = false;
    private boolean motorStateB = false;
    
    private boolean motorStateC = false;
    private int motorPulseDown = 0;
    
    private int inverseChange = 2;
    private boolean y = false;
    private boolean inverseA;
    private boolean inverseB;
    
    private final int JOYSTICK_BUTTON_ONE = 1;
    private final int JOYSTICK_BUTTON_TWO = 2;

    public void inverse() 
    {
        if ((inverseChange % 2) == 0) {
            inverseA = true;
            inverseB = false;
        } else {
            inverseA = false;
            inverseB = true;
        }
    }

    public void goingUp() 
    {
        if ((inverseA == true && controller.getRawButton(JOYSTICK_BUTTON_ONE)) || motorStateA == true) {
            testMotor.set(1.0);
            motorPulseUp++;
            motorStateA = true;
            if (motorPulseUp == 300) {                     //need to be changed into some amount as battery drains, not time
                testMotor.set(0.0);
                motorStateA = false;
                motorPulseUp = 1;
                motorStateB = true;
                inverseChange++;
            }
        }
    }

    public void goingDown() 
    {
        if ((inverseB == true && motorStateB == true && controller.getRawButton(JOYSTICK_BUTTON_TWO)) || motorStateC == true) {
            testMotor.set(-1.0);
            motorPulseDown++;
            motorStateC = true;
            if (motorPulseDown == 300) {                     // same**
                testMotor.set(0.0);
                motorStateC = false;
                motorStateB = false;
                motorPulseDown = 1;
                inverseChange++;
            }
        }
    }
}
