/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.frameworks;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author Robotics
 */
public class Potentiometer {
    
    private AnalogChannel potentiometer;
    double averagePot;
    
    public Potentiometer(int channel) {
        potentiometer = new AnalogChannel(channel);
    }
    
    public void updateAverageValues(int sampleRate) {
        averagePot = 0.0;
        double[] potValues = new double[sampleRate];
        for (int i=0; i<sampleRate; i++) {
            if (i<sampleRate-1) {
                potValues[i] = potValues[i+1];
            }
            potValues[sampleRate-1] = potentiometer.getVoltage();
            averagePot += potValues[i];
        }
        averagePot /= (double)sampleRate;
    }
    
    public double getAverageVoltage() {
        return averagePot;
    }
    
    public double getVoltage() {
        return potentiometer.getVoltage();
    }
}
