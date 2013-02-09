
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;

public class Main extends SimpleRobot implements IRobot {
    
    Autonomous myAuto = new Autonomous();
    OperatorControl myOpControl = new OperatorControl();
    
    public void autonomous() {
        
    }

    public void operatorControl() {
        
        /**
         * This loop runs every 10 milliseconds
         */
        int i = 0;
        while (isOperatorControl() && isEnabled()) {
            myOpControl.tenMSLoop();
            if (i%10 == 0) {
                myOpControl.hundredMSLoop();
            }
            if (i%100 == 0) {
                myOpControl.thousandMSLoop();
            }
            Timer.delay(TIME_DELAY);
            i++;
        }
    }
    
    public void test() {

    }
}
