package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author: Ben Feinstein
 */
public class Screw implements IRobot     //This is the lead screw. It basically uses a motor to move the shooter up and down.
{
        
    Talon screwLift = new Talon(PWM_SLOT_SIX);      //This is the motor that controls the lead screw that controls the shooter movement
    Angle encoder = new Angle(1, 1, 2);  //This encoder finds the angle of the shooter
    
    public void setMotor()
    {
        if (rightStick.getRawButton(RIGHT_BUTTON_TWO) && !rightStick.getRawButton(RIGHT_BUTTON_THREE)
        && encoder.pidGet()<=MAX_ANGLE) {
            screwLift.set(SPEED_FORWARD_HALF);
        }
        else if (rightStick.getRawButton(RIGHT_BUTTON_THREE) && !rightStick.getRawButton(RIGHT_BUTTON_TWO)
        && encoder.pidGet()>=MIN_ANGLE) {
            screwLift.set(SPEED_REVERSE_HALF);
        }
        else{
            screwLift.set(SPEED_STOP);
        }
    }
}
