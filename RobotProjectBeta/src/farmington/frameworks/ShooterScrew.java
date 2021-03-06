package farmington.frameworks;

import edu.wpi.first.wpilibj.DigitalInput;
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
    PID angleController;
    double winningSpeed;

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
        angleController = new PID(encoderSlotA, encoderSlotB, screwLift, 0.0, 0.0, 0.0);
        winningSpeed = SPEED_FORWARD_FULL;
    }
    
    /**
     * Sets movement based on two joystick buttons
     * @param upButton      this makes it go up
     * @param downButton    this makes it go down!!
     */
    public void setMovement(boolean upButton, boolean downButton) {
        if (upButton && !downButton && sensorHigh.get()) {
            screwLift.set(SPEED_FORWARD_FULL);
        } else if (downButton && !upButton && sensorLow.get()) {
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
        if ((speed < 0 && !sensorLow.get()) || (speed > 0 && !sensorHigh.get())) {
            screwLift.set(speed);
        } else {
            screwLift.set(SPEED_STOP);
        }
    }
    
    public void win() {
        if (sensorHigh.get() == true) {
            winningSpeed = SPEED_REVERSE_FULL;
        } else if (sensorLow.get() == true) {
            winningSpeed = SPEED_FORWARD_FULL;
        }
        this.setSpeed(winningSpeed);
    }
    
    public double getEncoderValue() {
        return angleController.getRate();
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
