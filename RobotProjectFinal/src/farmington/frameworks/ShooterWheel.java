package farmington.frameworks;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import farmington.ultimateascent.IRobot;

/**
 * Each instance of this class controls one wheel on the shooter.
 * @author 3414
 */
public class ShooterWheel implements IRobot {

    private PIDController shooterPID;
    private Talon shooterMotor;
    private Encoder shooterEncoder;
    private double oldEncoderValue;
    private double oldSpeed;
    private double targetRate;
    
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
        oldSpeed = 0.0;
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
}