package farmington.ultimateascent;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

/**
 * Each instance of this class controls one wheel on the shooter.
 * @author 3414
 */
public class ShooterWheel implements IRobot {

    private Talon shooterMotor;
    private Encoder shooterEncoder;
    
    /**
     * Main constructor for ShooterWheel.
     * @param encoderChannelA   DIO A-channel for the encoder attached to the wheel
     * @param encoderChannelB   DIO B-channel for the encoder attached to the wheel
     * @param motorSlot         PWM slot for the motor Talon
     */
    public ShooterWheel(int encoderChannelA, int encoderChannelB, int motorSlot) {
        shooterMotor = new Talon(motorSlot);
        shooterEncoder = new Encoder(encoderChannelA, encoderChannelB);
        shooterEncoder.setDistancePerPulse(0.00277778);        //Currently set to 1/360 so 360 pulses = 1 rotation
        shooterEncoder.start();
        
    }
    
//    public void updatePID() {
//        double offset = (shooterPID.getSetpoint() - shooterEncoder.getRate())/maxRPM;
//        double targetSpeed = shooterMotor.get() + offset;
//        shooterMotor.set(targetSpeed);
//    }
    
    public void setTrueSpeed(double speed) {
        shooterMotor.set(speed);
    }
    
    /**
     * Returns the motor speed according to battery voltage.
     * @return the speed of the motor from -1.0 to 1.0
     */
    public double getTrueSpeed() {
        return shooterMotor.get();
    }
    
    /**
     * Returns the current encoder rate of the wheel.
     * @return the encoder rate of the wheel
     */
    public double getRate() {
        return shooterEncoder.getRate();
    }
}