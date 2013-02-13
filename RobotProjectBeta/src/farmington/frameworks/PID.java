/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.frameworks;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;
import farmington.ultimateascent.IRobot;

/**
 *
 * @author Cooper Riehl
 * @version 1.0
 * Jan 26, 2013
 */
public class PID implements IRobot {
    
    private Encoder encoder;
    private PIDController controller;
    
    public PID(int encoderChannelOne, int encoderChannelTwo, Talon output, double Kp, double Ki, double Kd) {
        encoder = new Encoder(encoderChannelOne, encoderChannelTwo);
        encoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kRate);
        controller = new PIDController(Kp, Ki, Kd, encoder, output);
    }
    
    public void start() {
        encoder.start();
        controller.enable();
    }
    
    public void setTargetRate(double rateSetpoint, double percentRange) {
        controller.setSetpoint(rateSetpoint);
        controller.setPercentTolerance(percentRange);
    }
    
    public void setTargetRate(double rateSetpoint) {
        controller.setSetpoint(rateSetpoint);
    }
    
    public void setTargetSpeed(double speedSetpoint, double speedRange) {
        double rateSetpoint;
        double rateRange;       //FIXME: Find target RPM
        
        /** Convert speed into encoder values (multiply by some stuff relating
         * to the number of encoder "ticks" per motor revolution and the motor's
         * target RPM)
         */
        
//        controller.setAbsoluteTolerance(rateRange);
    }
    
    public void setTargetSpeed(double speedSetpoint) {
        double rateSetpoint;       //FIXME: Find target RPM
        
        /** Convert speed into encoder values (multiply by some stuff relating
         * to the number of encoder "ticks" per motor revolution and the motor's
         * target RPM)
         */
        
//        controller.setSetpoint(rateSetpoint);
    }
    
    public boolean isOnTarget() {
        return controller.onTarget();
    }
    
    public double getRate() {
        return controller.get();
    }
    
    public double getTarget() {
        return controller.getSetpoint();
    }
}
