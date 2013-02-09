package edu.wpi.first.wpilibj.templates;                    // Needs to be reset:
                                                            // THREE_SECONDS;  solenoid, talon, and joystick buttons need to be changed
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

/**
 * 
 * @author Josh Kavner and Cooper Riehl
 */
public class ShooterWheel implements IRobot {

    private PID shooterPID;
    private Talon shooterMotor;
    
    public ShooterWheel(int encoderChannelA, int encoderChannelB, int motorSlot, double Kp, double Ki, double Kd) {
        shooterMotor = new Talon(motorSlot);
        shooterPID = new PID(encoderChannelA, encoderChannelB, shooterMotor, Kp, Ki, Kd);
        shooterPID.start();
    }
    
    public void setTargetSpeed(double speed) {
        shooterPID.setTargetSpeed(speed);
    }
}