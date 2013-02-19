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
 * Each instance handles one PID controller.
 * @author 3414
 */
public class PID implements IRobot {
    
    private Encoder encoder;
    private PIDController controller;
    
    /**
     * Main constructor for PID.
     * @param encoderChannelOne A-channel of the connected encoder
     * @param encoderChannelTwo B-channel of the connected encoder
     * @param output            attached Talon
     * @param Kp                Kp value for the PID
     * @param Ki                Ki value for the PID
     * @param Kd                Kd value for the PID
     */
    public PID(int encoderChannelOne, int encoderChannelTwo, Talon output, double Kp, double Ki, double Kd) {
        encoder = new Encoder(encoderChannelOne, encoderChannelTwo);
        encoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kRate);
        controller = new PIDController(Kp, Ki, Kd, encoder, output);
    }
    
    /**
     * Initializes the controller.
     */
    public void start() {
        encoder.start();
        controller.enable();
    }
    
    /**
     * Modifies the target and tolerance of the PId controller.
     * @param rateSetpoint target encoder rate
     * @param percentRange percentage tolerance (0.05 = 5%)
     */
    public void setTargetRate(double rateSetpoint, double percentRange) {
        controller.setSetpoint(rateSetpoint);
        controller.setPercentTolerance(percentRange);
    }
    
    /**
     * Modifies the target of the PID controller without changing the tolerance.
     * @param rateSetpoint target encoder rate
     */
    public void setTargetRate(double rateSetpoint) {
        controller.setSetpoint(rateSetpoint);
    }
    
    /**
     * Checks to see if the encoder rate is within the tolerance around the target rate.
     * @return true if the encoder is within tolerance
     */
    public boolean isOnTarget() {
        return controller.onTarget();
    }
    
    public void tune(double Kp, double Ki, double Kd) {
        controller.setPID(Kp, Ki, Kd);
    }
    
    /**
     * Returns the encoder rate.
     * @return current encoder rate
     */
    public double getRate() {
        return encoder.get();
    }
    
    /**
     * Returns the current setpoint.
     * @return target encoder rate
     */
    public double getTarget() {
        return controller.getSetpoint();
    }
}
