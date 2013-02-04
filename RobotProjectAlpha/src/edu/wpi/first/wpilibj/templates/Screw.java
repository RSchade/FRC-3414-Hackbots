package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author: Ben Feinstein
 */
public class Screw implements IRobot     //This is the lead screw. It basically uses a motor to move the shooter up and down.
{
        
    Talon screwLift;     //This is the motor that controls the lead screw that controls the shooter movement
    Angle encoder = new Angle(1, 1, 2);  //This encoder finds the angle of the shooter
    private double screwSpeed;
    
    public Screw(int lifterSlot) {
        screwLift = new Talon(lifterSlot);
    }
    
    public void update(boolean upButton, boolean downButton) {
        if (upButton && !downButton && encoder.pidGet()<=MAX_ANGLE) {
            screwLift.set(SPEED_FORWARD_HALF);
        } else if (downButton && !upButton && encoder.pidGet()>=MIN_ANGLE) {
            screwLift.set(SPEED_REVERSE_HALF);
        } else {
            screwLift.set(SPEED_STOP);
        }
    }
}
