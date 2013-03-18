/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.frameworks;

import edu.wpi.first.wpilibj.AnalogChannel;
import farmington.ultimateascent.IRobot;

/**
 *
 * @author Robotics
 */
public class Potentiometer implements IRobot {
    
    private AnalogChannel potentiometer;
    double averagePot;
    double[] potValues;
    
    public Potentiometer(int channel) {
        potentiometer = new AnalogChannel(channel);
        potValues = new double[POT_SAMPLING_RATE];
    }
    
    public double getAverageVoltage() {
        for (int i=0; i<POT_SAMPLING_RATE; i++) {
            if (i<POT_SAMPLING_RATE-1) {
                potValues[i] = potValues[i+1];
            }
            potValues[POT_SAMPLING_RATE-1] = potentiometer.getVoltage();
            averagePot += potValues[i];
        }
        averagePot /= (double)POT_SAMPLING_RATE;
        return averagePot;
    }
    
    public double getVoltage() {
        return potentiometer.getVoltage();
    }
}
