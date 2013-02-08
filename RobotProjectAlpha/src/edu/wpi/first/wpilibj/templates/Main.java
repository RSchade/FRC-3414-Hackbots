
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
        while (isOperatorControl() && isEnabled()) {
            myOpControl.loop();
            Timer.delay(TIME_DELAY);
        }
    }
    
    public void test() {

    }
}
