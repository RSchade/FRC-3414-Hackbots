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
<<<<<<< HEAD
    Encoder encoder = new Encoder(1,2);  //This encoder finds the angle of the shooter
    private double n;
=======
    Angle encoder = new Angle(1, 1, 2);  //This encoder finds the angle of the shooter
>>>>>>> More vision stuff!
    
    public Screw(int lifterSlot) {
        screwLift = new Talon(lifterSlot);
    }
    
<<<<<<< HEAD
    public void update(boolean upButton, boolean downButton) {
        if (upButton && !downButton && encoder.get()<MAX_ENCODER_VALUE) {
            screwLift.set(SPEED_FORWARD_HALF);
        } else if (downButton && !upButton && encoder.get()>MIN_ENCODER_VALUE) {
=======
    public void updateManual(boolean upButton, boolean downButton) {
        if (upButton && !downButton && encoder.pidGet() <= MAX_ANGLE) {
            screwLift.set(SPEED_FORWARD_HALF);
        } else if (downButton && !upButton && encoder.pidGet() >= MIN_ANGLE) {
>>>>>>> More vision stuff!
            screwLift.set(SPEED_REVERSE_HALF);
        } else {
            screwLift.set(SPEED_STOP);
        }
    }
    
    public void updateAuto(double speed) {
        if ((speed < 0 && encoder.pidGet() >= MIN_ANGLE) || (speed > 0 && encoder.pidGet() <= MAX_ANGLE)) {
            screwLift.set(speed);
        } else {
            screwLift.set(0.0);
        }
    }
}
