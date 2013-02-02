package edu.wpi.first.wpilibj.templates;                    // Needs to be reset:
                                                            // THREE_SECONDS;  solenoid, talon, and joystick buttons need to be changed
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

/**
 * 
 * @author Josh Kavner and Cooper Riehl
 */
public class Shooter implements IRobot 
{
    
    Talon shooterMotorFirst = new Talon(PWM_SLOT_FIVE);
    Talon shooterMotorSecond = new Talon(PWM_SLOT_SIX);
    Solenoid shooterPiston = new Solenoid(SOLENOID_ONE);
    
    private double shooterSpeedFirst = 0.0;
    private double shooterSpeedSecond = 0.0;
            
    public void startMotor() {
        shooterSpeedFirst = SPEED_FORWARD_HALF;
        shooterSpeedSecond = SPEED_FORWARD_FULL;
    }
    
    public void stopMotor() {
        shooterSpeedFirst = SPEED_STOP;
        shooterSpeedSecond = SPEED_STOP;
    }

    public void update() {
        shooterMotorFirst.set(shooterSpeedFirst);
        shooterMotorSecond.set(shooterSpeedSecond);
    }
    
    public void setPiston(boolean isExtended) {
        shooterPiston.set(isExtended);
    }
}