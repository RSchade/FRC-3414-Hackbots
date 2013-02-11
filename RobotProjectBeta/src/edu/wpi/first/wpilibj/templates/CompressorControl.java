/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Robotics
 */
public class CompressorControl implements IRobot {
    
    private Joystick leftStick;
    private Compressor myCompressor;
    
    public CompressorControl() {
        leftStick = new Joystick(USB_ONE);
        myCompressor = new Compressor(DIO_FOURTEEN, RELAY_ONE);
    }
    
    public void runCompressor() {
        myCompressor.start();
        while(!leftStick.getRawButton(LEFT_TRIGGER)) {
            Timer.delay(0.010);
        }
        myCompressor.stop();
    }
}
