package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

public class Spinner implements IRobot 
{
    private final int THREE_SECONDS = 300;
    private final int JOYSTICK_BUTTON_ONE = 1;
    private final int JOYSTICK_BUTTON_TWO = 2;
    Talon shooterMotor = new Talon(5);
    Solenoid shooterControlPiston = new Solenoid(1);
    Talon shooterControlMotor = new Talon(6);
    
    /* IN THIS CLASS, ALL METHODS NEED:
     * 
     * import edu.wpi.first.wpilibj.Talon;
     * import edu.wpi.first.wpilibj.Solenoid;
     * import edu.wpi.first.wpilibj.Joystick;
     * import edu.wpi.first.wpilibj.SimpleRobot;
     * import edu.wpi.first.wpilibj.Timer;
     * 
     * Joystick rightStick = new Joystick(2);                Right Joystick; button (see below at (.getRawButton(1/2)))
     * Talon shooterMotor = new Talon(5);                    Talon's 1-4 are for drive train
     * Solenoid shooterControlPiston = new Solenoid(1);      Piston: 1
     * Talon shooterControlMotor = new Talon (6);            Either this motor or the piston; depends on what we need 
     *                                                          to push the frisbee into the shooter                                        
     *  
     */
    private int motorPulse = 1;
    private boolean motorState = true;
    private int countTimer = 1;
    private boolean pushMotorState = false;
    private boolean pushPistonState = false;
            
    public void SetShooter() {
        if (rightStick.getRawButton(JOYSTICK_BUTTON_ONE) == true && motorState == true)                       // if the button is pressed once,                           i=     controller button     a=
        {                                                                   // and if arbitrary integer "a" is equal to 1                           
            motorPulse++;                                                   // "i" increases by one and "a" is set to 0                 1           deactivated      activated
            motorState = false;                                             //                                                          2           activated        activated
        }                                                                   //                                                          2           activated        deactivated
        if (rightStick.getRawButton(JOYSTICK_BUTTON_ONE) == false)          //                                                          2           deactivated      activated
        {                                                                   //                                                          3...        activated        activated
            motorState = true;                                              // "a" is set back to 1 to allow for the motor to change.
        }
        if ((motorPulse % 2) == 0)                                          // if "i" is even,
        {
            shooterMotor.set(1.0);                                          // motor runs at full power
        }                                                                   // ^ We need to use a double for the motor set value
        else                                                                // if "i" is odd,
        {
            shooterMotor.set(0.0);                                          // motor stops running                             
        }                                                                   // *end shooter code* *start piston code*
    }

    public void pushPiston() {                                              // *start shooter code*   
        if (rightStick.getRawButton(JOYSTICK_BUTTON_TWO) == true || pushPistonState == true)                  // 1. if the button is pressed    -or-   n = true :                                   
        {                                                                   // 2.                           
            shooterControlPiston.set(true);                                 // 3. piston is pushed out
            countTimer++;                                                   // 4. m increases by 1
            pushPistonState = true;                                         // 5. n = true
            if (countTimer == THREE_SECONDS) {                                        // 6.               (**if m =/= 300: loop restarts (16) and 
                                                                            // 7.                m increases for 3 seconds before running the next part)                 
                                                                            // 8. if m = 300:                 
                shooterControlPiston.set(false);                            // 9.       reset: piston is pulled back in
                pushPistonState = false;                                    // 10.      reset: n = false
                countTimer = 1;                                             // 11.      reset: m restarts back to 1
            }
        }
    }

    public void pushMotor() {
        if (rightStick.getRawButton(JOYSTICK_BUTTON_TWO) == true || pushMotorState == true)                   // 1. if the button is pressed    -or-   n = true :                                   
        {                                                                   // 2.                           
            shooterControlMotor.set(1);                                     // 3. piston is pushed out
            countTimer++;                                                   // 4. m increases by 1
            pushMotorState = true;                                          // 5. n = true
            if (countTimer == THREE_SECONDS) {                                        // 6.               (**if m =/= 300: loop restarts (16) and 
                                                                            // 7.                m increases for 3 seconds before running the next part)                 
                                                                            // 8. if m = 300:                 
                shooterControlMotor.set(0);                                 // 9.       reset: piston is pulled back in
                pushMotorState = false;                                     // 10.      reset: n = false
                countTimer = 1;                                             // 11.      reset: m restarts back to 1
            }
        }
    }
}