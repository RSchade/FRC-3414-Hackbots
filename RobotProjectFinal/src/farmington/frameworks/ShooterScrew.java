package farmington.frameworks;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;
import farmington.ultimateascent.IRobot;

/**
 * Controls the up/down screw system.
 * @author: 3414
 */
public class ShooterScrew implements IRobot {
        
    private Talon screwLift;
    DigitalInput sensorLow, sensorHigh;
    Encoder screwEncoder;
    PIDController screwPID;

    /**
     * Main constructor for ShooterScrew.
     * @param lifterSlot    PWM slot for lifter talon
     * @param encoderChannelA  A-channel DIO for encoder
     * @param encoderChannelB  B-channel DIO for encoder
     * @param dioSlotLow    DIO slot for back limit switch
     * @param dioSlotHigh   Dio slot for front limit switch
     */
    public ShooterScrew(int lifterSlot, int encoderChannelA, int encoderChannelB, int dioSlotLow, int dioSlotHigh) {
        screwLift = new Talon(lifterSlot);
        sensorLow = new DigitalInput(dioSlotLow);
        sensorHigh = new DigitalInput(dioSlotHigh);
        screwEncoder = new Encoder(encoderChannelA, encoderChannelB);
        screwEncoder.start();
        screwEncoder.setDistancePerPulse(0.000623);        //DEBUG change this to an angular value
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
    
    public double getAngle() {
        return screwEncoder.getDistance();
    }
    
    public double getScrewMotorSpeed() {
        return screwLift.get();
    }
    
    public boolean getSensorLowValue() {
        return sensorLow.get();
    }
    
    public boolean getSensorHighValue() {
        if (sensorHigh.get()) {
            screwEncoder.stop();
            screwEncoder.reset();
        }
        return sensorHigh.get();
    }
}
