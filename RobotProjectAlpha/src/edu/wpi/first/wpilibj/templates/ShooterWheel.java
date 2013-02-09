package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Talon;

/**
 * 
 * @author Josh Kavner and Cooper Riehl
 */
public class ShooterWheel implements IRobot {

    private PID shooterPID;
    private Talon shooterMotor;
    private int motorID;
    
    public ShooterWheel(int ID, int encoderChannelA, int encoderChannelB, int motorSlot, double Kp, double Ki, double Kd) {
        shooterMotor = new Talon(motorSlot);
        shooterPID = new PID(encoderChannelA, encoderChannelB, shooterMotor, Kp, Ki, Kd);
        shooterPID.start();
        motorID = ID;
    }
    
    public void turnOn() {                              /* Sets the encoder rate
                                                         * of our motor to 2000
                                                         * "ticks" per (minute/second?)
                                                         * with a tolerance of 5%
                                                         */
        if (motorID == WHEEL_ONE) {
            shooterPID.setTargetRate(2000.0, 0.05);     
        } else if (motorID == WHEEL_TWO) {
            shooterPID.setTargetRate(3000.0, 0.05);
        } else {
            System.out.println("ERROR in ShooterWheel.java, motorID mismatch.");
        }
    }
}