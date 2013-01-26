/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author: rockbanddude a.k.a Ben Feinstein
 */
public class Screw implements IRobot     //This is the lead screw. It basically uses a motor to move the shooter up and down.
{
    Talon screwLift = new Talon(5);      //This is the motor that controls the lead screw that controls the shooter movement
    Angle encoder = new Angle(1, 1, 2);  //This encoder finds the angle of the shooter
    
    public void setMotor()
    {
        rightStick.getRawButton(1);
        rightStick.getRawButton(2);
        
        if (rightStick.getRawButton(1) && !rightStick.getRawButton(2) && encoder.pidGet()<=50.0)  //If we press button 1 and not button 2 and the shooter is <= 50 degrees
        {
            screwLift.set(0.5);                                                                   //The lead screw will lift the shooter up
        }
        else if (rightStick.getRawButton(2) && !rightStick.getRawButton(1) && encoder.pidGet()>=0.0) //If we press button 2 and not button 1 and the shooter is >= 0 degrees
        {
            screwLift.set(-0.5);                                                                     //The lead screw will set itself down
        }
        else                                                                           //If no buttons are currently pressed
        {
            screwLift.set(0.0);                                                        //The shooter will keep its current position
        }
    }
}
