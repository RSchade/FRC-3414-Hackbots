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
    double[] potValues;
    
    public Potentiometer(int channel) {
        potentiometer = new AnalogChannel(channel);
        potValues = new double[10];
    }
    
    public double getAverageVoltage() {
        averagePot = 0.0;
        for (int i=0; i<10; i++) {
            if (i<10-1) {
                potValues[i] = potValues[i+1];
            }
            potValues[10-1] = potentiometer.getVoltage();
            averagePot += potValues[i];
        }
        averagePot /= 10.0;
        return averagePot;
    }
    
    public double getVoltage() {
        return potentiometer.getVoltage();
    }
}
