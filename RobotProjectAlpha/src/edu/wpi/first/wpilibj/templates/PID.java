/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author Cooper Riehl
 * @version 1.0
 * Jan 26, 2013
 */
public class PID implements IRobot {
    
    private Encoder encoder;
    private Talon output;
    private PIDController controller;
    
    PID(int encoderChannelOne, int encoderChannelTwo, int talonChannel, double Kp, double Ki, double Kd) {
        encoder = new Encoder(encoderChannelOne, encoderChannelTwo);
        encoder.start();
        encoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kRate);
        output = new Talon(talonChannel);
        controller = new PIDController(Kp, Ki, Kd, encoder, output);
    }
    
    public void start() {
        encoder.start();
        controller.enable();
    }
    
    public void setTargetEncoderRate(double rateSetpoint, double rateRange) {
        controller.setSetpoint(rateSetpoint);
        controller.setPercentTolerance(rateRange);
    }
    
    public void setTargetEncoderRate(double rateSetpoint) {
        controller.setSetpoint(rateSetpoint);
    }
    
    public void setTargetMotorSpeed(double speedSetpoint, double speedRange) {
        double rateSetpoint;
        double rateRange;
        
        /** Convert speed into encoder values (multiply by some stuff relating
         * to the number of encoder "ticks" per motor revolution and the motor's
         * target RPM)
         */
        
//        controller.setSetpoint(rateSetpoint, rateRange);
    }
    
    public void setTargetMotorSpeed(double speedSetpoint) {
        double rateSetpoint;
        
        /** Convert speed into encoder values (multiply by some stuff relating
         * to the number of encoder "ticks" per motor revolution and the motor's
         * target RPM)
         */
        
//        controller.setSetpoint(rateSetpoint);
    }
    
    public boolean isOnTarget() {
        return controller.onTarget();
    }
}
