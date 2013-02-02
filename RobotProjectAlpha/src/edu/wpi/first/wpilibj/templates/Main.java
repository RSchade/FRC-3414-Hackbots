/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.Joystick;
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
    
    Joystick leftStick = new Joystick(USB_ONE);
    Joystick rightStick = new Joystick(USB_TWO);
    
    DriveTrain myDrive = new DriveTrain(1, 3, 2, 4);
    Camera myCamera = new Camera();
    LightSensor photosensor = new LightSensor();
    Shooter myShooter = new Shooter();
    
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
            
            
            // Take a picture with the Camera for processing
            if (rightStick.getRawButton(RIGHT_TRIGGER) && a) {
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
            
            //Turn the shooter on or off
            if (leftStick.getRawButton(LEFT_BUTTON_TWO)) {
                myShooter.startMotor();
            } else {
                myShooter.stopMotor();
            }
            
            if (leftStick.getRawButton(LEFT_TRIGGER)) {
                myShooter.setPiston(EXTENDED);
            } else {
                myShooter.setPiston(RETRACTED);
            }
            
            //Update systems
            myDrive.update(leftStick.getRawAxis(VERTICAL_AXIS), -1*rightStick.getRawAxis(VERTICAL_AXIS));
            myShooter.update();
            
            //Update the dashboard
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
