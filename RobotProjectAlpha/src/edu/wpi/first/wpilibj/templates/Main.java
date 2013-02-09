
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;

public class Main extends SimpleRobot implements IRobot {
    
    Autonomous myAuto;
    OperatorControl myOpControl;
    
    public void autonomous() {
        myAuto = new Autonomous();
    }

    public void operatorControl() {
        
        myOpControl = new OperatorControl();
        
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
