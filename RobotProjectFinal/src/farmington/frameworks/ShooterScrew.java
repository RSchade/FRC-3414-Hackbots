package farmington.frameworks;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Talon;
import farmington.ultimateascent.IRobot;

/**
 * Controls the up/down screw system.
 * @author: 3414
 */
public class ShooterScrew implements IRobot//This is the lead screw. It basically uses a motor to move the shooter up and down.
{
        
    private Talon screwLift;
    DigitalInput sensorLow, sensorHigh;
    Counter screwCounter;

    /**
     * Main constructor for ShooterScrew.
     * @param lifterSlot    PWM slot for lifter talon
     * @param encoderSlotA  A-channel DIO for encoder
     * @param encoderSlotB  B-channel DIO for encoder
     * @param dioSlotLow    DIO slot for back limit switch
     * @param dioSlotHigh   Dio slot for front limit switch
     */
    public ShooterScrew(int lifterSlot, int encoderSlotA, int encoderSlotB, int dioSlotLow, int dioSlotHigh) {
        screwLift = new Talon(lifterSlot);
        sensorLow = new DigitalInput(dioSlotLow);
        sensorHigh = new DigitalInput(dioSlotHigh);
        DigitalInput encoderA = new DigitalInput(encoderSlotA);
        DigitalInput encoderB = new DigitalInput(encoderSlotB);
        screwCounter = new Counter(CounterBase.EncodingType.k2X, encoderA, encoderB, false);
    }
    
    public void start() {
        screwCounter.start();
    }
    
    /**
     * Sets movement based on two joystick buttons
     * @param upButton      this makes it go up
     * @param downButton    this makes it go down!!
     */
    public void setMovement(boolean upButton, boolean downButton) {
        if (upButton && !downButton && this.getSensorHighValue()) {
            screwLift.set(SPEED_FORWARD_FULL);
        } else if (downButton && !upButton && this.getSensorLowValue()) {
            screwLift.set(SPEED_REVERSE_FULL);
        } else {
            screwLift.set(SPEED_STOP);
        }
    }
    
    /**
     * Do set speed without a joystick button
     * @param speed speed
     */
    public void setSpeed(double speed) {
        if ((speed < 0 && !this.getSensorLowValue()) || (speed > 0 && !this.getSensorHighValue())) {
            screwLift.set(speed);
        } else {
            screwLift.set(SPEED_STOP);
        }
    }
    
    public int getEncoderValue() {
        return screwCounter.get();
    }
    
    public double getScrewMotorSpeed() {
        return screwLift.get();
    }
    
    public boolean getSensorLowValue() {
        return sensorLow.get();
    }
    
    public boolean getSensorHighValue() {
        if (sensorHigh.get()) {
            screwCounter.reset();
        }
        return sensorHigh.get();
    }
}
