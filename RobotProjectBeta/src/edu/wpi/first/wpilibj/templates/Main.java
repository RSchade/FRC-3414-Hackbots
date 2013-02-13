
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import farmington.frameworks.CompressorControl;
import farmington.frameworks.LoopHandler;
import farmington.ultimateascent.Autonomous;
import farmington.ultimateascent.IRobot;
import farmington.ultimateascent.OperatorControl;

public class Main extends SimpleRobot implements IRobot {
    
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
            
            loopCount++;
            Timer.delay(TIME_DELAY);
        }
    }
    
    public void test() {
        myCompressorControl = new CompressorControl(DIO_FOURTEEN, RELAY_TWO);
        myCompressorControl.runCompressor();
    }
}
