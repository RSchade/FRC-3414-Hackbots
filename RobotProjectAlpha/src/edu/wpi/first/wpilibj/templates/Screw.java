package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author: Ben Feinstein
 */
public class Screw implements IRobot     //This is the lead screw. It basically uses a motor to move the shooter up and down.
{
    final double MAX_ANGLE = 50.0;
    final double MIN_ANGLE = 0.0;
        
    Talon screwLift = new Talon(5);      //This is the motor that controls the lead screw that controls the shooter movement
    Angle encoder = new Angle(1, 1, 2);  //This encoder finds the angle of the shooter
    
    public void setMotor()
    {
        if (rightStick.getRawButton(1) && !rightStick.getRawButton(2) && encoder.pidGet()<=MAX_ANGLE) 
        {
            screwLift.set(0.5);
        }
        else if (rightStick.getRawButton(2) && !rightStick.getRawButton(1) && encoder.pidGet()>=MIN_ANGLE)
        {
            screwLift.set(-0.5);
        }
        else
        {
            screwLift.set(0.0);
        }
    }
}
