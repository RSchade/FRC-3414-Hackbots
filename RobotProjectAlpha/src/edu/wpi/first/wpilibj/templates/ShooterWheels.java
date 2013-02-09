package edu.wpi.first.wpilibj.templates;                    // Needs to be reset:
                                                            // THREE_SECONDS;  solenoid, talon, and joystick buttons need to be changed
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

/**
 * 
 * @author Josh Kavner and Cooper Riehl
 */
public class ShooterWheels implements IRobot {
    
    private Talon shooterFirstMotor;
    private Talon shooterSecondMotor;
    private Solenoid shooterPiston;
    private double firstSpeed, secondSpeed;
    private boolean solenoidPosition;
    
    public ShooterWheels(int firstMotorSlot, int secondMotorSlot, int solenoidSlot) {
        shooterFirstMotor = new Talon(firstMotorSlot);
        shooterSecondMotor = new Talon(secondMotorSlot);
        shooterPiston = new Solenoid(solenoidSlot);
    }
    
    public void setSpeed(double firstSpeed, double secondSpeed) {
        this.firstSpeed = firstSpeed;
        this.secondSpeed = secondSpeed;
    }
    
    public void setSolenoid(boolean position) {
        solenoidPosition = position;
    }
    
    public void update() {
        shooterFirstMotor.set(firstSpeed);
        shooterSecondMotor.set(secondSpeed);
        shooterPiston.set(solenoidPosition);
    }
}