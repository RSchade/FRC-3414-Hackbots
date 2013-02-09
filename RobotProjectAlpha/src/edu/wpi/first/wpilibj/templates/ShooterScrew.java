package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
/**
 *
 * @author: Ben Feinstein
 */
public class ShooterScrew implements IRobot     //This is the lead screw. It basically uses a motor to move the shooter up and down.
{
        
    private Talon screwLift;     //This is the motor that controls the lead screw that controls the shooter movement
    private Encoder encoder;  //This encoder finds the angle of the shooter
    private double n;

    public ShooterScrew(int lifterSlot, int encoderSlotA, int encoderSlotB) {
        screwLift = new Talon(lifterSlot);
        encoder = new Encoder(encoderSlotA, encoderSlotB);
    }
    
    public void updateManual(boolean upButton, boolean downButton) {
        if (upButton && !downButton && encoder.get()<MAX_ENCODER_VALUE) {
            screwLift.set(SPEED_FORWARD_HALF);
        } else if (downButton && !upButton && encoder.get()>MIN_ENCODER_VALUE) {
            screwLift.set(SPEED_REVERSE_HALF);
        } else {
            screwLift.set(SPEED_STOP);
        }
    }
    
    public void updateAuto(double speed) {
        if ((speed < 0 && encoder.get() >= MIN_ENCODER_VALUE) || (speed > 0 && encoder.get() <= MAX_ENCODER_VALUE)) {
            screwLift.set(speed);
        } else {
            screwLift.set(0.0);
        }
    }
    
    public int getEncoder() {
        return encoder.get();
    }
    
    public double getScrewMotor() {
        return screwLift.get();
    }
}
