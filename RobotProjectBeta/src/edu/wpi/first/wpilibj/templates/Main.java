
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import farmington.frameworks.CompressorControl;
import farmington.frameworks.LoopHandler;
import farmington.ultimateascent.Autonomous;
import farmington.ultimateascent.IRobot;
import farmington.ultimateascent.RobotControl;

public class Main extends SimpleRobot implements IRobot {
    
    RobotControl myRobotControl;
    Autonomous myAutonomous;
    CompressorControl myCompressorControl;
    
    public void robotInit() {
        myAutonomous = new Autonomous;
    }
    
    public void autonomous() {
        
    }

    public void operatorControl() {
        
        int loopCount = 0;
        
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
        myRobotControl.free();
    }
    
    public void test() {
        myCompressorControl = new CompressorControl(DIO_FOURTEEN, RELAY_TWO);
        myCompressorControl.runCompressor();
    }
}
