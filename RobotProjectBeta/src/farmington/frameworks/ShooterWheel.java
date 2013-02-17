package farmington.frameworks;                    // Needs to be reset:
                                                            // THREE_SECONDS;  solenoid, talon, and joystick buttons need to be changed
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import farmington.ultimateascent.IRobot;

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
    
    public void turnOn(boolean control) {
        if (control) {
            shooterPID.setTargetRate(SHOOTER_SPEED);
        } else {
            shooterPID.setTargetRate(0);
        }
    }
    
    public void turnOnMaxSpeed(boolean on) {
        if (on) {
            shooterMotor.set(1.0);
        }
    }
    
    public void setRate(int rate) {
        shooterPID.setTargetRate(rate);
    }
    
    public double getTrueSpeed() {
        return shooterMotor.get();
    }
    
    public double getRate() {
        return shooterPID.getRate();
    }
    
    public double getTarget() {
        return shooterPID.getTarget();
    }
    
    public boolean isOnTarget() {
        return shooterPID.isOnTarget();
    }
}