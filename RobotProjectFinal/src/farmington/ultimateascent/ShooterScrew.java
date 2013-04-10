package farmington.ultimateascent;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;

/**
 * Controls the up/down screw system.
 * @author: 3414
 */
public class ShooterScrew implements IRobot {
        
    private Talon screwLift;
    DigitalInput sensorLow, sensorHigh;
    Potentiometer screwPot;

    /**
     * Main constructor for ShooterScrew.
     * @param lifterSlot    PWM slot for lifter talon
     * @param potSlot       Analog channel for potentiometer
     * @param dioSlotLow    DIO slot for back limit switch
     * @param dioSlotHigh   Dio slot for front limit switch
     */
    public ShooterScrew(int lifterSlot, int potSlot, int dioSlotLow, int dioSlotHigh) {
        screwLift = new Talon(lifterSlot);
        sensorLow = new DigitalInput(dioSlotLow);
        sensorHigh = new DigitalInput(dioSlotHigh);
        screwPot = new Potentiometer(potSlot);
    }
    
    /**
     * Sets movement based on two joystick buttons
     * @param upButton      this makes it go up
     * @param downButton    this makes it go down!!
     */
    public void setMovement(boolean upButton, boolean downButton, double speedFactor) {
        if (upButton && !downButton && this.getSensorHighValue()) {
            screwLift.set(SPEED_FORWARD_FULL * speedFactor);
        } else if (downButton && !upButton && this.getSensorLowValue()) {
            screwLift.set(SPEED_REVERSE_FULL * speedFactor);
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
    
    public int isOnTarget(double targetVoltage, double offset) {
        if (this.getVoltage() < targetVoltage-offset) {             //Change both getVoltages to getAverageVoltage when that is ready
            return -1;
        } else if (this.getVoltage() > targetVoltage+offset) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public double getVoltage() {
        return screwPot.getVoltage();
    }
    
    public double getAverageVoltage() {
        return screwPot.getAverageVoltage();
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
