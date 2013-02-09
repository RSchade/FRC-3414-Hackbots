
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;

public class Main extends SimpleRobot implements IRobot {
    
    Autonomous myAuto;
    OperatorControl myOpControl;
    CompressorControl myCompressorControl;
    
    public void autonomous() {
        myAuto = new Autonomous();
    }

    public void operatorControl() {
        
        myOpControl = new OperatorControl();
        
        /**
         * This loop runs every 10 milliseconds
         */
        int loopCount = 0;
        while (isOperatorControl() && isEnabled()) {
            myOpControl.tenMSLoop(loopCount);
            if (loopCount%10 == 0) {
                myOpControl.hundredMSLoop(loopCount);
            }
            if (loopCount%100 == 0) {
                myOpControl.thousandMSLoop(loopCount);
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
