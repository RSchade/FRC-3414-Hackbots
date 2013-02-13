package farmington.frameworks;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import farmington.ultimateascent.IRobot;
/**
 *
 * @author: Ben Feinstein
 */
public class ShooterScrew implements IRobot     //This is the lead screw. It basically uses a motor to move the shooter up and down.
{
        
    private Talon screwLift;     //This is the motor that controls the lead screw that controls the shooter movement
    private Encoder encoder;  //This encoder finds the angle of the shooter
    DigitalInput sensorLow, sensorHigh;

    public ShooterScrew(int lifterSlot, int encoderSlotA, int encoderSlotB, int dioSlotLow, int dioSlotHigh) {
        screwLift = new Talon(lifterSlot);
        encoder = new Encoder(encoderSlotA, encoderSlotB);
        sensorLow = new DigitalInput(dioSlotLow);
        sensorHigh = new DigitalInput(dioSlotHigh);
    }
    
    public void setMovement(boolean upButton, boolean downButton) {
        if (upButton && !downButton && !sensorHigh.get()) {
            screwLift.set(SPEED_FORWARD_HALF);
        } else if (downButton && !upButton && !sensorLow.get()) {
            screwLift.set(SPEED_REVERSE_HALF);
        } else {
            screwLift.set(SPEED_STOP);
        }
    }
    
    public void setSpeed(double speed) {
        if ((speed < 0 && !sensorLow.get()) || (speed > 0 && !sensorHigh.get())) {
            screwLift.set(speed);
        } else {
            screwLift.set(SPEED_STOP);
        }
    }
    
    public void setAngle(double speed) {
        if ((speed > 0) && !sensorHigh.get() && (encoder.get() <= AUTONOMOUS_SET_VALUE)) {
            screwLift.set(speed);
        } else if ((speed < 0) && !sensorLow.get() && (encoder.get() >= AUTONOMOUS_SET_VALUE)) {
            screwLift.set(speed);
        } else {
            screwLift.set(0);
        }        
        
        
    }
    
    public int getEncoderValue() {
        return encoder.get();
    }
    
    public double getScrewMotorSpeed() {
        return screwLift.get();
    }
    
    public boolean getSensorLowValue() {
        return sensorLow.get();
    }
    
    public boolean getSensorHighValue() {
        return sensorHigh.get();
    }
}
