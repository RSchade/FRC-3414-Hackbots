/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Main extends SimpleRobot implements IRobot {
    
    DriveTrain myDrive = new DriveTrain();
    Camera myCamera = new Camera();
    LightSensor photosensor = new LightSensor();
    
    public void dashboardUpdate() {
        photosensor.getDashboard();
        myDrive.getDashboard();
    }
    
    public void autonomous() {
        
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        /**
         * This loops every 10 milliseconds
         */
        
        int i = 1;
        boolean a = true;
        
        while(isOperatorControl() && isEnabled()) {
            myDrive.drive();        //Drive Train update
            
            if (rightStick.getRawButton(RIGHT_TRIGGER) && a) {      //Take a picture
                myCamera.centerCalculate();
                a = false;
            }
            if (!rightStick.getRawButton(RIGHT_TRIGGER) && !a) {
                a = true;
            }
            i++;
            if (i==11) {
                i = 1;
            }
            
            dashboardUpdate();
            Timer.delay(TIME_DELAY);
        }
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {

    }
}
