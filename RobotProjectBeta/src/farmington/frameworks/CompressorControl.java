/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.frameworks;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import farmington.ultimateascent.IRobot;

/**
 *
 * @author Robotics
 */
public class CompressorControl implements IRobot {

    private Compressor myCompressor;
    
    public CompressorControl(int pressureSensorSlot, int compressorRelay) {
        myCompressor = new Compressor(pressureSensorSlot, compressorRelay);
    }
    
    public void runCompressor() {
        myCompressor.start();
    }
}
