
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;

public class RobotTemplate extends SimpleRobot implements IRobot {
    
    Autonomous myAuto;
    OperatorControl myOpControl;
    CompressorControl myCompressorControl;
    
    public void autonomous() {
        myAuto = new Autonomous();
    }

    public void operatorControl() {
        
        myOpControl = new OperatorControl();
        int loopCount = 0;
        
        while (isOperatorControl() && isEnabled()) {
            LoopHandler.updateLoopCount(loopCount);
            
            //Runs every 10 milliseconds
            myOpControl.tenMSLoop();
            
            //Runs every 100 milliseconds
            if (loopCount%10 == 0) {
                myOpControl.hundredMSLoop();
            }
            
            //Runs every 1000 milliseconds (every second)
            if (loopCount%100 == 0) {
                myOpControl.thousandMSLoop();
            }
            
            Timer.delay(TIME_DELAY);
            loopCount++;
        }
    }
    
    public void test() {
        myCompressorControl = new CompressorControl();
        myCompressorControl.runCompressor();
    }
}
