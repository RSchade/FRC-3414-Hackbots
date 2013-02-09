package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Encoder;
/**
 *
 * @author: Ben Feinstein
 */
public class Screw implements IRobot     //This is the lead screw. It basically uses a motor to move the shooter up and down.
{
        
    Talon screwLift;     //This is the motor that controls the lead screw that controls the shooter movement
    Encoder encoder = new Encoder(1,2);  //This encoder finds the angle of the shooter
    
    public Screw(int lifterSlot) {
        screwLift = new Talon(lifterSlot);
    }
    
    public void update(boolean upButton, boolean downButton) {
        if (upButton && !downButton && encoder.get()<MAX_ENCODER_VALUE) {
            screwLift.set(SPEED_FORWARD_HALF);
        } else if (downButton && !upButton && encoder.get()>MIN_ENCODER_VALUE) {
            screwLift.set(SPEED_REVERSE_HALF);
        } else {
            screwLift.set(SPEED_STOP);
        }
    }
}
