package farmington.frameworks;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;
import farmington.ultimateascent.IRobot;

/**
 * Each instance of this class controls one wheel on the shooter.
 * @author 3414
 */
public class ShooterWheel implements IRobot {

    private PIDController shooterPID;
    private Talon shooterMotor;
    private Encoder shooterEncoder;
    
    /**
     * Main constructor for ShooterWheel.
     * @param encoderChannelA   DIO A-channel for the encoder attached to the wheel
     * @param encoderChannelB   DIO B-channel for the encoder attached to the wheel
     * @param motorSlot         PWM slot for the motor Talon
     * @param Kp                Kp for the PID controller
     * @param Ki                Ki for the PID controller
     * @param Kd                Kd for the PID controller
     */
    public ShooterWheel(int encoderChannelA, int encoderChannelB, int motorSlot, double Kp, double Ki, double Kd) {
        shooterMotor = new Talon(motorSlot);
        shooterEncoder = new Encoder(encoderChannelA, encoderChannelB);
        shooterEncoder.start();
        shooterEncoder.setDistancePerPulse(0.000623);        //DEBUG change this to the correct value
        shooterEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kRate);
        //DEBUG reenable PID
        shooterPID = new PIDController(KP, KI, KD, shooterEncoder, shooterMotor);
        shooterPID.enable();
        shooterPID.setPercentTolerance(PID_TOLERANCE);
        shooterPID.setSetpoint(0.0);
    }
    
    /**
     * Sets a custom PID rate for the wheel.
     * @param rate the target encoder rate
     */
    public void setRate(double rate) {
        shooterPID.setSetpoint(rate);
    }
    
    public void turnOff() {
        shooterPID.setSetpoint(0.0);
        shooterMotor.set(0.0);
    }
    
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
    
    /**
     * Tells us if the encoder is at its set rate +/- tolerance.
     * @return true if the encoder rate is within its tolerance around the target rate
     */
    public boolean isOnTarget() {
        return shooterPID.onTarget();
    }
}