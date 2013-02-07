
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;

public class Main extends SimpleRobot implements IRobot {
    
//    public void findVisionTarget(ParticleAnalysisReport target) {
//        boolean isOnTargetX = false;
//        boolean isOnTargetY = false;
//        
//        if (target.center_mass_x_normalized < -0.9) {
//            myDrive.setSpeed(0.25, -0.25);
//        } else if (target.center_mass_x_normalized > 0.9) {
//            myDrive.setSpeed(-0.25, 0.25);
//        } else {
//            isOnTargetX = true;
//        }
//        
//        if (target.center_mass_y_normalized < -0.9) {
//            myScrew.updateAuto(0.25);
//        } else if (target.center_mass_y_normalized > 0.9) {
//            myScrew.updateAuto(-0.25);
//        } else {
//            isOnTargetY = true;
//        }
//        
//        if (isOnTargetX && isOnTargetY) {
//            SmartDashboard.putBoolean("Ready for shooting?", true);
//        } else {
//            SmartDashboard.putBoolean("Ready for shooting?", false);
//        }
//    }
    
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
