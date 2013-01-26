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
public class TestPID implements IRobot {
    
    double Kp, Ki, Kd;
    int encoderChannelOne, encoderChannelTwo, talonChannel;
    
    TestPID(int encoderChannelOne, int encoderChannelTwo, int talonChannel, double Kp, double Ki, double Kd) {
        this.encoderChannelOne = encoderChannelOne;
        this.encoderChannelTwo = encoderChannelTwo;
        this.talonChannel = talonChannel;
        this.Kp = Kp;
        this.Ki = Ki;
        this.Kd = Kd;
    }
    
    Encoder testEncoder = new Encoder(encoderChannelOne, encoderChannelTwo);
    Talon output = new Talon(talonChannel);
    PIDController testPID = new PIDController(Kp, Ki, Kd, testEncoder, output);
    
    public void start() {
        testEncoder.start();
        testPID.enable();
    }
    
    public void setTarget(double setpoint, double range) {
        testPID.setSetpoint(setpoint);
        testPID.setPercentTolerance(range);
    }
    
    public void setTarget(double setpoint) {
        testPID.setSetpoint(setpoint);
    }
    
    public boolean isOnTarget() {
        return testPID.onTarget();
    }
}
