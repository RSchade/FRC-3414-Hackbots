
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import farmington.frameworks.LoopHandler;
import farmington.ultimateascent.IRobot;
import farmington.ultimateascent.RobotControl;

public class Main extends SimpleRobot implements IRobot {
    
    RobotControl myRobotControl;
    Compressor myCompressor;
    
    public Main() {
        myRobotControl = new RobotControl();
        myCompressor = new Compressor(DIO_FOURTEEN, RELAY_TWO);
    }
    
    public void autonomous() {
    }

    public void operatorControl() {
        
        int loopCount = 0;
        myCompressor.start();
        
        while (isOperatorControl() && isEnabled()) {
            LoopHandler.updateLoopCount(loopCount);
            
            //Runs every 20 milliseconds
            if (loopCount%2 == 0) {
                myRobotControl.twentyMSLoop();
            }
            
            //Runs every 100 milliseconds
            if (loopCount%10 == 0) {
                myRobotControl.hundredMSLoop();
            }
            
            //Runs every 1000 milliseconds (every second)
            if (loopCount%100 == 0) {
                myRobotControl.thousandMSLoop();
            }
            
            loopCount++;
            Timer.delay(TIME_DELAY);
        }
    }
    
    public void disabled() {
    }
    
    public void test() {
    }
}
